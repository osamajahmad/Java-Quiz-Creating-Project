package Question;

import javafx.application.Platform;

public class QuizTimer extends Thread{
    private int seconds;
    private Runnable onTimeout;
    private volatile boolean stopped = false;

    /** 
     * @param seconds total countdown seconds 
     * @param onTimeout callback to run on the JavaFX thread when times up 
     */
    public QuizTimer(int seconds, Runnable onTimeout) {
        this.seconds   = seconds;
        this.onTimeout = onTimeout;
    }

    /** Safely stop the timer early */
    public void stopTimer() {
        stopped = true;
        interrupt();
    }

    @Override
    public void run() {
        try {
            for (int i = seconds; i > 0 && !stopped; i--) {
                Thread.sleep(1000);
            }
            if (!stopped) {
                // runs timeout on javafx thread using platform which is needed for GUI changes
                Platform.runLater(onTimeout);
            }
        } catch (InterruptedException ignored) {
                // timer was stopped manually
        }
    }
}
