package Question;

import java.util.Scanner;

public class Quiz {
    private QuizQuestions quizQuestions;
    private int score;

    public Quiz() {
        this.quizQuestions = new QuizQuestions();
        this.score = 0;
    }

    /**
     * Runs a console quiz using the specified file and number of questions.
     */
    public void start(String filename, int numberOfQuestions) {
        Scanner scan = new Scanner(System.in);

        // Load and select questions
        quizQuestions.load(filename);
        quizQuestions.select(numberOfQuestions);
        System.out.println("Welcome to the Quiz! Answer the following questions:");

        // Loop through selected questions
        for (Question<String> q : quizQuestions.getSelectedQuestions()) {
            System.out.println("\n" + q.getQuestion());
            String[] answers = q.getAllAnswers();
            for (int i = 0; i < answers.length; i++) {
                System.out.println((i + 1) + ". " + answers[i]);
            }

            System.out.print("Enter your choice (1-" + answers.length + "): ");
            int choice = scan.nextInt();

            if (choice >= 1 
                && choice <= answers.length
                && answers[choice - 1]
                       .equalsIgnoreCase(q.getCorrectAnswer())) {
                System.out.println("Correct!");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was: " 
                                   + q.getCorrectAnswer());
            }
        }

        System.out.println("\nQuiz finished! Your score: " 
                           + score + "/" + numberOfQuestions);
        scan.close();
    }

    /**
     * Hook for JUnit: check one Question using the provided Scanner.
     * Invalid or missing input simply leaves score unchanged.
     */
    public void checkAnswer(Question<String> question, Scanner scanner) {
        String[] answers = question.getAllAnswers();
        int choice;
        try {
            choice = scanner.nextInt();
        } catch (Exception e) {
            return;  // invalid or no input → no score change
        }
        if (choice >= 1 
            && choice <= answers.length
            && answers[choice - 1]
                   .equalsIgnoreCase(question.getCorrectAnswer())) {
            score++;
        }
    }

    /** Expose the current score so JUnit can assert against it. */
    public int getScore() {
        return score;
    }
}
