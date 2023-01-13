import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenu{

    @FXML
    Button instructionButton;

    @FXML
    Button exitButton;

    @FXML
    Button playButton;

    @FXML
    Button returnButton;

    //-----Play-Button-Methods-----
    // (Used for replay & play buttons!)

    /**
     * 
     * @throws IOException
     */
    @FXML
    public void playClicked() throws IOException{
        App.setRoot("chess");
    }

    @FXML
    public void playEntered(){
        playButton.setStyle("-fx-background-color: #708c37");
    }

    @FXML
    public void playExited(){
        playButton.setStyle("-fx-background-color: #95BB4A");
    }

    //-----Help-Button-Methods-----

    @FXML
    private void helpClicked() throws IOException{
        App.setRoot("helpMenu");
    }

    @FXML
    private void helpEntered(){
        instructionButton.setStyle("-fx-background-color: #6b6b00");
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

    //-----Return-to-Menu-Button-Method-----

    @FXML
    public void returnClicked() throws IOException{
        App.setRoot("mainMenu");
    }

}
