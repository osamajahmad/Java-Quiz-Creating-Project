package Question;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;

public class QuizProject extends Application {

    private static final String WELCOME_TEXT        = "Welcome to this quiz. Please enter your username: ";
    private static final String RULES_TEXT          = "Rules:\n1. Number of questions: 10\n2. Difficulty: Medium\n3. Time limit: 2 minutes (will auto‑submit when time expires)\n\nClick Start to begin";
    private static final String TIME_LEFT_TEXT      = "Time Left: ";
    private static final String SECONDS_TEXT        = " seconds";
    private static final String QUIZ_COMPLETE_TEXT  = "Quiz Completed!\n";
    private static final String USERNAME_TEXT       = "Username: ";
    private static final String SCORE_TEXT          = "Score: ";
    private static final String TOP_SCORES_TEXT     = "Top 3 Scores:\n";
    private static final String CONNECTING_TEXT     = "Connecting to leaderboard server...";
    private static final String SERVER_ERROR_TEXT   = "Error connecting to server. Showing local results.";
    private static final String SERVER_HOST         = "localhost";
    private static final int    SERVER_PORT         = 12345;

    private String username;
    private int score = 0;
    private QuizQuestions questions;
    private Question<String> currentQuestion;
    // Total quiz duration in seconds
    private static final int TOTAL_TIME = 120;
    private int timeRemaining = TOTAL_TIME;
    private Label timerLabel;
    private Timeline timeline;
    private boolean quizEnded = false;
    private QuizTimer threadedTimer; 
    private long startTime;   
    private int currentQuestionIndex = 0;
    private StackPane timerPane;
    private HBox navigationBox;
    private ToggleGroup answerGroup;

    /**
     * The main entry point for the JavaFX application.
     * @param stage The primary stage for the application.
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Java Concepts Quiz");

        Label greeting = labelFactory("Welcome to this quiz. Please enter your username: ", Color.WHITE, Color.BLUE);
        TextField userinput = new TextField();
        Button enterButton = new Button("Enter");
        enterButton.setTextFill(Color.WHITE);
        enterButton.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        enterButton.setFont(new Font("Arial", 18));

        enterButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                username = userinput.getText();
                if (!username.isEmpty()) {
                    showRules(stage);
                } else {
                    greeting.setText("Please enter a valid username.");
                    greeting.setTextFill(Color.RED);
                }
            }
        });

        /*
         * Improvement number 1
         */
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(javafx.geometry.Pos.CENTER); // Center the grid pane
        gridPane.add(greeting, 1, 1);
        gridPane.add(userinput, 1, 2);
        gridPane.add(enterButton, 1, 3);
        gridPane.setPadding(new Insets(50, 20, 50, 20)); // Same padding
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Creates a styled label with the specified text, text color, and background color.
     * @param text The text to display on the label.
     * @param textColor The color of the text.
     * @param backgroundColor The background color of the label.
     * @return A styled Label object.
     */
    public Label labelFactory(String text, Color textColor, Color backgroundColor) {
        Label label = new Label(text);
        label.setFont(new Font("Arial", 18));
        label.setTextFill(textColor);
        label.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        return label;
    }

    /**
     * Displays the rules screen to the user.
     * @param stage The primary stage of the application.
     */
    private void showRules(Stage stage) {
        Label rulesLabel = labelFactory("Rules:\n1. Number of questions: 10\n2. Difficulty: Medium\n3. Time limit: 2 minutes (will auto‑submit when time expires)\n\nClick Start to begin", Color.WHITE, Color.BLUE);
        Button startButton = new Button("Start Quiz");
        startButton.setTextFill(Color.WHITE);
        startButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        startButton.setFont(new Font("Arial", 18));

        /*
         * Timer using Threads also using lambda expression calling run method in LeaderBoardServer
         */
        startButton.setOnAction(event -> {
            // load questions
            questions = new QuizQuestions();
            questions.load("questionsBase.txt");
            questions.select(10);

            startTime = System.currentTimeMillis();

            // inform user about timer
            System.out.println("You have " + TOTAL_TIME + " seconds to finish. Quiz auto-submits when times up.");

            // start quiz UI
            startQuiz(stage);
            startTimer(stage);

            // launch thread timer using lambda
            threadedTimer = new QuizTimer(TOTAL_TIME, () -> {
                quizEnded = true;
                showScore(stage);
            });
            threadedTimer.start();
        });

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(javafx.geometry.Pos.CENTER); // Center the grid pane
        gridPane.add(rulesLabel, 1, 1);
        gridPane.add(startButton, 1, 2);
        gridPane.setPadding(new Insets(50, 20, 50, 20)); // Same padding
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(gridPane, 800, 600);
        stage.setScene(scene);
    }

    /**
     * Starts the quiz by displaying the current question and answer options.
     * @param stage The primary stage of the application.
     */
    private void startQuiz(Stage stage) {
        if (quizEnded) {
            return;
        }

        currentQuestion = questions.getSelectedQuestions().get(currentQuestionIndex);

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(20));

        // Timer in top right
        timerPane = new StackPane();
        timerLabel = new Label("Time Left: " + timeRemaining + " seconds");
        timerLabel.setTextFill(Color.RED);
        timerLabel.setFont(new Font("Arial", 22));
        /*
         * Improvemnt number 2
         */
        timerPane.getChildren().add(timerLabel);
        StackPane.setAlignment(timerLabel, Pos.TOP_RIGHT);
        mainPane.setTop(timerPane);
        BorderPane.setAlignment(timerPane, Pos.TOP_RIGHT);

        VBox quizBox = new VBox(10);
        quizBox.setPadding(new Insets(20));

        Label questionLabel = new Label((currentQuestionIndex + 1) + ". " + currentQuestion.getQuestion());
        questionLabel.setFont(new Font("Arial", 22));
        questionLabel.setTextFill(Color.WHITE);
        quizBox.getChildren().add(questionLabel);

        answerGroup = new ToggleGroup();
        String[] answers = currentQuestion.getAllAnswers();
        for (int i = 0; i < answers.length; i++) {
            RadioButton option = new RadioButton(answers[i]);
            option.setToggleGroup(answerGroup);
            option.setTextFill(Color.WHITE);
            option.setFont(new Font("Arial", 16));   
            quizBox.getChildren().add(option);
            
        }

        /*
         * Improvement number 2
         */
        navigationBox = new HBox(10);
        Button prevButton = new Button("Previous");
        prevButton.setTextFill(Color.WHITE);
        prevButton.setBackground(new Background(new BackgroundFill(Color.GRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        prevButton.setDisable(currentQuestionIndex == 0);

        Button nextButton = new Button(currentQuestionIndex == questions.getSelectedQuestions().size() - 1 ? "Submit" : "Next");
        nextButton.setTextFill(Color.WHITE);
        nextButton.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));

        prevButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    startQuiz(stage);
                }
            }
        });

        nextButton.setOnAction(new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                if (answerGroup.getSelectedToggle() != null) {
                    String selectedAnswer = ((RadioButton) answerGroup.getSelectedToggle()).getText();
                    if (selectedAnswer.equals(currentQuestion.getCorrectAnswer())) {
                        score++;
                    }
                }

                if (currentQuestionIndex < questions.getSelectedQuestions().size() - 1) {
                    currentQuestionIndex++;
                    startQuiz(stage);
                } else {
                    quizEnded = true;
                    if (threadedTimer != null) threadedTimer.stopTimer();
                    showScore(stage);
                }
            }
        });

        navigationBox.getChildren().addAll(prevButton, nextButton);
        /*
         * Improvment number 3
         */
        navigationBox.setAlignment(Pos.CENTER);
        quizBox.getChildren().add(navigationBox);

        mainPane.setCenter(quizBox);
        mainPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Scene scene = new Scene(mainPane, 800, 600);
        stage.setScene(scene);
    }

    /**
     * Starts the timer for the quiz.
     * @param stage The primary stage of the application.
     */
    private void startTimer(Stage stage) {
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), new javafx.event.EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                timeRemaining--;
                timerLabel.setText("Time Left: " + timeRemaining + " seconds");

                if (timeRemaining <= 0) {
                    quizEnded = true;
                    timeline.stop();
                    showScore(stage);
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * Send results to leaderboard server and return its reply.
     
    private String queryLeaderboardServer(String host, int port, String user, int score, long timeTaken) {
        StringBuilder resp = new StringBuilder();
        try (
            Socket sock = new Socket(host, port);
            PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(sock.getInputStream()))
        ) {
            out.println(user + "," + score + "," + timeTaken);
            String line;
            while ((line = in.readLine()) != null) {
                resp.append(line).append("\n");
            }
        } catch (IOException e) {
            return "Error connecting to leaderboard server: " + e.getMessage();
            
        }
        return resp.toString();
    }
    */

    
    /**
     * Displays the final score and leaderboard to the user.
     * @param stage The primary stage of the application.
     */
    private void showScore(Stage stage) {
        if (threadedTimer != null) threadedTimer.stopTimer();
        if (timeline != null) timeline.stop();
        long timeTaken = (System.currentTimeMillis() - startTime) / 1000;
    
        // loading screen
        Label serverStatusLabel = new Label(CONNECTING_TEXT);
        serverStatusLabel.setTextFill(Color.WHITE);
        serverStatusLabel.setFont(new Font("Arial", 16));
        VBox loadingBox = new VBox(20, serverStatusLabel);
        loadingBox.setAlignment(Pos.CENTER);
        GridPane loadPane = new GridPane();
        loadPane.setAlignment(Pos.CENTER);
        loadPane.add(loadingBox, 0, 0);
        loadPane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene loadingScene = new Scene(loadPane, 800, 600);
        stage.setScene(loadingScene);

        /*
         * lambda used to call run method (new Runnable() and run() which means the code will be executed when time comes) to get the info from the socket
         */
        new Thread(() -> {
            try {
                // Sleep for a short period to keep the loading screen visible
                Thread.sleep(3000); 

            Socket sock = new Socket();
            // Set 5 second timeout
            sock.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT), 5000);
            
            try (PrintWriter out = new PrintWriter(sock.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()))) {
                out.println(username + "," + score + "," + timeTaken);
                String response = in.readLine();
                Platform.runLater(() -> displayFinalResults(stage, response));
            }
        } catch (IOException | InterruptedException ex) {
            Platform.runLater(() -> {
                serverStatusLabel.setText(SERVER_ERROR_TEXT);
                displayFinalResults(stage, null);
            });
            // Save results locally if server fails
            saveLeaderboard(username, score, timeTaken);
         }
        }).start(); 
    }
                                                                     
    /**
     * Displays the rank & leaderboard highlights in GUI
     */
    private void displayFinalResults(Stage stage, String serverResponse) {
        String msg = QUIZ_COMPLETE_TEXT
            + USERNAME_TEXT + username + "\n"
            + SCORE_TEXT + score + "/" + questions.getSelectedQuestions().size() + "\n\n";
    
        if (serverResponse != null) {
            String[] parts = serverResponse.split(";", 2);
            if (parts.length >= 1) {
                msg += "Your Rank: " + parts[0] + "\n\n";
            }
            
            if (parts.length >= 2) {
                msg += TOP_SCORES_TEXT;
                // Split entries and reformat each one
                String[] entries = parts[1].split("\\\\n");
                for (String entry : entries) {
                    if (!entry.trim().isEmpty()) {
                        // split (format: "1. User:Score:Time")
                        String[] entryParts = entry.split("\\.")[1].split(":");
                        String rank = entry.split("\\.")[0].trim();
                        String user = entryParts[0].trim();
                        int score = Integer.parseInt(entryParts[1].trim());
                        int time = Integer.parseInt(entryParts[2].trim());
                        
                        // Format as "Rank. User - X pts (Y sec)"
                        msg += rank + ". " + user + " - " + score + " pts (" + time + " sec)\n";
                    }
                }
            }
        }
        
        // Rest of your display code
        Label result = new Label(msg);
        result.setTextFill(Color.WHITE);
        result.setFont(new Font("Arial", 18));
        Button back = new Button("Back to Home");
        back.setTextFill(Color.WHITE);
        back.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        back.setFont(new Font("Arial", 18));
        back.setOnAction(e -> {
            resetQuiz(); start(stage);
        });
        VBox box = new VBox(20, result, back);
        box.setAlignment(Pos.CENTER);
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.add(box, 0, 0);
        pane.setPadding(new Insets(50,20,50,20));
        pane.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        stage.setScene(new Scene(pane, 800, 600));
    }

    /**
     * Saves the user's score to the leaderboard file.
     * @param username The username of the player.
     * @param score The score achieved by the player.
     */
    // new version—takes username, score AND timeTaken
    private void saveLeaderboard(String username, int score, long timeTaken) {
    try (BufferedWriter w = new BufferedWriter(new FileWriter("leaderboard.txt", true))) {
        w.write(username + ": " + score + ": " + timeTaken);
        w.newLine();
    } catch (IOException ex) {
        System.out.println("Error saving leaderboard: " + ex.getMessage());
        }
    }


    /**
     * Loads the leaderboard from the file.
     * @return A list of all entries in the leaderboard.
     
    private List<String> loadLeaderboard() {
        List<String> leaderboard = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                leaderboard.add(line);
            }
        } catch (IOException ex) {
            System.out.println("Error reading leaderboard: " + ex.getMessage());
        }
        return leaderboard;
    }

    /**
     * Sorts the leaderboard and returns the top five scores.
     * @param leaderboard The list of all leaderboard entries.
     * @return A list of the top five scores in descending order.
     
    private List<String> getTopFiveScores(List<String> leaderboard) {
        Collections.sort(leaderboard, new Comparator<String>() {
            @Override
            public int compare(String e1, String e2) {
                String[] p1 = e1.split(": ", 3);
                String[] p2 = e2.split(": ", 3);
        
                int score1 = Integer.parseInt(p1[1]);
                int score2 = Integer.parseInt(p2[1]);
                //higher score first
                if (score1 != score2) {
                    return Integer.compare(score2, score1);
                }
    
                //secondary: lower time first
                int time1 = Integer.parseInt(p1[2]);
                int time2 = Integer.parseInt(p2[2]);
                return Integer.compare(time1, time2);
            }
        });        

        return leaderboard.subList(0, Math.min(5, leaderboard.size()));
    }
    */
    
    /**
     * Resets the quiz state to allow the user to start over.
     */
    private void resetQuiz() {
        username = "";
        score = 0;
        timeRemaining = 120;
        quizEnded = false;
    }

    /**
     * The main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Added an additional class, QuizTest class, to test whether the program functions properly
     */
}