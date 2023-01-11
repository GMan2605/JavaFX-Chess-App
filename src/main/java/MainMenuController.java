import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController{

    @FXML
    Button instructionButton;

    @FXML
    Button exitButton;

    //-----Help-Button-Methods-----

    @FXML
    private void helpClicked() throws IOException{
        App.setRoot("helpMenu");
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
