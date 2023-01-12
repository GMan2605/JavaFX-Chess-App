import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class HelpMenu extends MainMenu{

    @FXML
    Button returnButton;

    //-----Return-to-Menu-Button-Methods-----

    @FXML
    private void returnEntered(){
        returnButton.setStyle("-fx-background-color: #700000");
    }

    @FXML
    private void returnExited(){
        returnButton.setStyle("-fx-background-color: #a60000");
    }
}
