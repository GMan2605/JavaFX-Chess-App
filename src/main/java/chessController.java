import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    StackPane[][] gridSpot = new StackPane[8][8];
    Rectangle[][] tiles = new Rectangle[8][8];
    HBox[] gridRows = new HBox[8];

    @FXML
    VBox chessGrid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // For-loop section that adds all components to make a visual board
        for (int i=0; i<8; i++){ // y dimension loop
            gridRows[i] = new HBox(0);
            gridRows[i].setId(Integer.toString(i));
            for (int j=0; j<8; j++){ // x dimension loop
                gridSpot[i][j] = new StackPane();
                tiles[i][j] = new Rectangle(70, 70);

                if ( (j+i) % 2 == 0) // Is the sum of the x & y even? (creates "checker" pattern)
                    tiles[i][j].setFill(Color.GHOSTWHITE);
                else
                    tiles[i][j].setFill(Color.GREEN);

                gridSpot[i][j].getChildren().add(tiles[i][j]);
                gridRows[i].getChildren().add(gridSpot[i][j]);
            }
            chessGrid.getChildren().add(gridRows[i]);
        }

        Rectangle a = new Rectangle(35, 35);
        a.setFill(Color.DARKBLUE);
        // a.setOnMouseClicked(e -> System.out.println("a"));
        // a.setOnMouseClicked(e -> dumbClick());

        // Node boardNode = chessGrid.getChildren().get(1);
        Pawn beanstalkBrewers = new Pawn(2, 2, a);


        beanstalkBrewers.givePos();
        beanstalkBrewers.drawPiece(chessGrid);
        // beanstalkBrewers.drawPiece(1, 1, a);
        // beanstalkBrewers.drawPiece(0, 0, a);

        // ((Pane) (( (HBox)boardNode) ).getChildren().get(1)).getChildren().add(b);
        Rectangle b = new Rectangle(35, 35);
        ((StackPane) (( (HBox)chessGrid.getChildren().get(4)) ).getChildren().get(4)).getChildren().add(b);
    }

    // private void dumbClick(){
    //     ((Pane) (( (HBox)chessGrid.getChildren().get(3)) ).getChildren().get(4)).getChildren().add(a);
    // }
}
