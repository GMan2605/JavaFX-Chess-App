import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class MainMenuController implements Initializable{

    @FXML 
    Button playButton;

    @FXML
    Button instructionButton;

    @FXML
    Button exitButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //-----Play-Button-Methods-----

    /**
     * 
     * @throws IOException
     */
    @FXML
    private void playClicked() throws IOException{
        App.setRoot("chess");
    }

    @FXML
    private void playEntered(){
        playButton.setStyle("-fx-background-color: #0a6100");
    }

    @FXML
    private void playExited(){
        playButton.setStyle("-fx-background-color: #00d607");
    }

    //-----Help-Button-Methods-----

    @FXML
    private void helpClicked(){
        System.out.println("Yay");
    }

    @FXML
    private void helpEntered(){
        instructionButton.setStyle("-fx-background-color: #bfff00");
    }

    @FXML
    private void helpExited(){
        instructionButton.setStyle("-fx-background-color: #aee300");
    }

    //-----Exit-Button-Methods-----

    @FXML
    private void exitClicked(){
        Platform.exit();
    }

    @FXML
    private void exitEntered(){
        exitButton.setStyle("-fx-background-color: #700700");
    }

    @FXML
    private void exitExited(){
        exitButton.setStyle("-fx-background-color: #a60000");
    }
}
