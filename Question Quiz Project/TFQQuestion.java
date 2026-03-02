package Question;

import java.util.Arrays;

public class TFQQuestion implements Question<String> {
    private String question;
    private String[] answers;
    private String correctAnswer;

    
    public TFQQuestion(String question, String correctAnswer) {
        this.question = question;
        this.answers = new String[]{"True", "False"}; 
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
    public void setCorrectAnswer(String correctAnswer) {this.correctAnswer = correctAnswer;}

    @Override
    public String toString() {
    return "TFQQuestion{" + "question='" + question + '\'' + ", answers=" + Arrays.toString(answers) + ", correctAnswer='" + correctAnswer + '\'' + '}';
    }
    /*
     * Checks if 
     */
    @Override
    public boolean equals(Object obj) {
    if (obj instanceof TFQQuestion) {
        TFQQuestion other = (TFQQuestion) obj;
        return this.question.equals(other.getQuestion()) && Arrays.equals(this.answers, other.getAllAnswers()) && this.correctAnswer.equals(other.getCorrectAnswer());
    }
    return false;
    }
}
