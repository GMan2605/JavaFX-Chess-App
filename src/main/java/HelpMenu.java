import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class HelpMenu implements Initializable{

    @FXML
    Button returnButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    //-----Return-to-Menu-Button-Methods-----

    @FXML
    private void returnClicked() throws IOException{
        App.setRoot("mainMenu");
    }
}
