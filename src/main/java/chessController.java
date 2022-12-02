import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    StackPane[][] gridSpot = new StackPane[8][8];
    Rectangle[][] tiles = new Rectangle[8][8];
    HBox[] gridRows = new HBox[8];

    @FXML
    VBox chessGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Tim is dead");

        for (int i=0; i<8; i++){
            gridRows[i] = new HBox(1);
            Rectangle a = new Rectangle(60, 60);
            gridRows[i].getChildren().add(a);
            // for (int j=0; i<8; j++){

            // }

            chessGrid.getChildren().add(gridRows[i]);
        }
    }
    
}
