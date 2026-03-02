package Question;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class QuizEventHandler implements EventHandler<ActionEvent> {
    private TextField userinput;
    private Label greeting;  

    /*
    Constructor to initialize the TextField, Label, and Button
    */
    public QuizEventHandler(TextField userinput, Label greeting, Button button) {
        this.userinput = userinput;
        this.greeting = greeting;
    }

    @Override
    public void handle(ActionEvent event) {
        String inputTextValue = userinput.getText();  
        
        if (inputTextValue != null && !inputTextValue.isEmpty()) {
            greeting.setText("Welcome, " + inputTextValue + "!");  
            greeting.setTextFill(Color.WHITE); 
        } else {
            greeting.setText("Please enter a valid username."); 
            greeting.setTextFill(Color.RED); 
        }
    }
}
