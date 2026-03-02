package Question;

public interface Question<T> {
    String getQuestion();
    T[] getAllAnswers();
    String getCorrectAnswer();
}

