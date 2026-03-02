package Question;

import java.io.*;
import java.util.*;

public class QuizQuestions {
    private ArrayList<Question<String>> allQuestions = new ArrayList<>();
    private ArrayList<Question<String>> selectedQuestions = new ArrayList<>();
    private int currentQuestionIndex = 0;

    /**
     * Loads questions from a file.
     * @param filename The name of the file containing questions.
     */
    public void load(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String questionText = line.trim();
                String[] answers = br.readLine().split(",");
                String correctAnswer = br.readLine().trim();

                if (answers.length == 2 && (answers[0].trim().equalsIgnoreCase("True") || answers[1].trim().equalsIgnoreCase("False"))) {
                    allQuestions.add(new TFQQuestion(questionText, correctAnswer));
                } else {
                    allQuestions.add(new MCQQuestion(questionText, answers, correctAnswer));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Selects a specified number of questions randomly.
     * @param numberOfQuestions The number of questions to select.
     */
    public void select(int numberOfQuestions) {
        Collections.shuffle(allQuestions);
        selectedQuestions.clear();
        for (int i = 0; i < Math.min(numberOfQuestions, allQuestions.size()); i++) {
            selectedQuestions.add(allQuestions.get(i));
        }
    }

    /**
     * Retrieves the next question in the selected list.
     * @return The next question, or null if no more questions are available.
     */
    public Question<String> getNextQuestion() {
        if (currentQuestionIndex < selectedQuestions.size()) {
            return selectedQuestions.get(currentQuestionIndex++);
        }
        return null;
    }

    /**
     * Checks if there are more questions remaining.
     * @return True if more questions are available, false otherwise.
     */
    public boolean hasNextQuestion() {
        return currentQuestionIndex < selectedQuestions.size();
    }

    /**
     * Retrieves all selected questions.
     * @return A list of selected questions.
     */
    public ArrayList<Question<String>> getSelectedQuestions() {
        return selectedQuestions;
    }
}
