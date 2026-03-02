package Question;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.*;
import java.util.*;

/**
 * JUnit tests for the Quiz class that verify question handling using actual questions
 * from the questionsBase.txt file.
 */
public class QuizTest {
    
    private QuizQuestions quizQuestions;
    private Quiz quiz;
    
    /**
     * Tests that answering correctly increases the score by 1
     * using actual questions from questionsBase.txt
     * @throws IOException if the questions file cannot be read
     */
    @Test
    public void testCorrectAnswerIncreasesScore() throws IOException {
        // Setup
        quiz = new Quiz();
        quizQuestions = new QuizQuestions();
        quizQuestions.load("questionsBase.txt");
        quizQuestions.select(1);
        
        Question<String> currentQuestion = quizQuestions.getSelectedQuestions().get(0);
        String[] answers = currentQuestion.getAllAnswers();
        String correctAnswer = currentQuestion.getCorrectAnswer();
        
        // Find index of correct answer
        int correctChoice = 1;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i].equals(correctAnswer)) {
                correctChoice = i + 1; // Convert to 1-based index
                break;
            }
        }
        
        String input = Integer.toString(correctChoice);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        
        int initialScore = quiz.getScore();
        
        quiz.checkAnswer(currentQuestion, scanner);
    
        assertEquals("Score should increase by 1 for correct answer", 
                   initialScore + 1, quiz.getScore());
    }

    /**
     * Tests that wrong answers don't increase the score
     * using actual questions from questionsBase.txt
     * @throws IOException if the questions file cannot be read
     */
    @Test
    public void testWrongAnswerDoesNotIncreaseScore() throws IOException {
        // Setup
        quiz = new Quiz();
        quizQuestions = new QuizQuestions();
        quizQuestions.load("questionsBase.txt");
        quizQuestions.select(1);
        
        Question<String> currentQuestion = quizQuestions.getSelectedQuestions().get(0);
        String[] answers = currentQuestion.getAllAnswers();
        String correctAnswer = currentQuestion.getCorrectAnswer();
        
        // Find a wrong answer choice
        int wrongChoice = 1;
        if (answers[0].equals(correctAnswer)) {
            wrongChoice = 2; // Pick second option if first is correct
        }
        
        String input = Integer.toString(wrongChoice);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        
        int initialScore = quiz.getScore();
        
        quiz.checkAnswer(currentQuestion, scanner);
        
        assertEquals("Score should not change for wrong answer",
                   initialScore, quiz.getScore());
    }

    /**
     * Tests whether empty input is handled
     * using actual questions from questionsBase.txt
     * @throws IOException if the questions file cannot be read
     */
    @Test
    public void testEmptyInputHandling() throws IOException {
        // Setup
        quiz = new Quiz();
        quizQuestions = new QuizQuestions();
        quizQuestions.load("questionsBase.txt");
        quizQuestions.select(1);
        
        Question<String> currentQuestion = quizQuestions.getSelectedQuestions().get(0);
        
        String input = "\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(in);
        
        int initialScore = quiz.getScore();
        quiz.checkAnswer(currentQuestion, scanner);
        assertEquals("Score should not change for empty input",
                   initialScore, quiz.getScore());
    }
}