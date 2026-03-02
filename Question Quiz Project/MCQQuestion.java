package Question;

import java.util.Arrays;

public class MCQQuestion implements Question<String> { 
    private String question;
    private String[] answers;
    private String correctAnswer;

    /*
     * Constractor MCQQuestion
     */
    public MCQQuestion(String question, String[] answers, String correctAnswer) {
        this.question = question;
        this.answers = answers;
        this.correctAnswer = correctAnswer;
    }

    /*
     * Getters
     */
    @Override
    public String getQuestion() {return question;}
    @Override
    public String[] getAllAnswers() {return answers;}
    @Override
    public String getCorrectAnswer() {return correctAnswer;}

    /*
     * Setters
     */
    public void setQuestion(String question) {this.question = question;}
    public void setAnswers(String[] answers) {this.answers = answers;}
    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}
    
    /*
     * String Method
     */
    @Override
    public String toString() {
    return "MCQQuestion{" + "question='" + question + '\'' + ", answers=" + Arrays.toString(answers) + ", correctAnswer='" + correctAnswer + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
    if (obj instanceof MCQQuestion) {
        MCQQuestion other = (MCQQuestion) obj;
        return this.question.equals(other.getQuestion()) && Arrays.equals(this.answers, other.getAllAnswers()) && this.correctAnswer.equals(other.getCorrectAnswer());
    }
    return false;
    }
}
