import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class WinMenu extends MainMenu implements Initializable{

    /*
     * "Play Another Game" button uses existing
     * commands in superClass MainMenu!
     */

    @FXML
    Label winLabel;

    @FXML
    Button winReturn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // if (App.wString == "Black"){
        //     winLabel.setTextFill(null);
        // } else if (App.wString == "White") {
        //     winLabel.setTextFill(null);
        // }
        System.out.println(App.wString);
        winLabel.setText( "..." + App.wString);
        
    }

    //-----Win-Return-Methods-----

    @FXML
    private void winReturnEntered(){
        winReturn.setStyle("-fx-background-color: #5e5e5e");
    }

    @FXML
    private void winReturnExited(){
        winReturn.setStyle("-fx-background-color: #a3a39b");
    }
}
