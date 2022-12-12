import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    public boolean pieceChosen = false;
    StackPane[][] gridSpot = new StackPane[8][8];
    Rectangle[][] tiles = new Rectangle[8][8];
    HBox[] gridRows = new HBox[8];
    Pawn[] whitePawns = new Pawn[8];
    Pawn[] blackPawns = new Pawn[8];
    Rook[] whiteRooks = new Rook[2];
    Rook[] blackRooks = new Rook[2];
    Bishop[] whiteBishops = new Bishop[2];
    Bishop[] blackBishops = new Bishop[2];
    Knight[] whiteKnights = new Knight[2];
    Knight[] blackKnights = new Knight[2];
    ImageView[][] pawnImage = new ImageView[2][8];
    ImageView[][] rookImage = new ImageView[2][2];
    ImageView[][] bishopImage = new ImageView[2][2];
    ImageView[][] knightImage = new ImageView[2][2];

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

                tiles[i][j].setOnMouseClicked(e -> tileClicked(null));
                gridSpot[i][j].getChildren().add(tiles[i][j]);
                gridRows[i].getChildren().add(gridSpot[i][j]);
            }
            chessGrid.getChildren().add(gridRows[i]);
        }

        // Creation of the pawn layout
        for (int i=0; i<8; i++){
            pawnImage[0][i] = new ImageView("Images/W_Pawn.png");
            pawnImage[1][i] = new ImageView("Images/B_Pawn.png");
            whitePawns[i] = new Pawn(i, 6, pawnImage[0][i], "White", chessGrid);
            blackPawns[i] = new Pawn(i, 1, pawnImage[1][i], "Black", chessGrid);
        }

        // Creation of all "dual" pieces, pieces that occur twice on each team
        for (int i=0; i<2; i++){
            rookImage[0][i] = new ImageView("Images/W_Rook.png");
            rookImage[1][i] = new ImageView("Images/B_Rook.png");
            whiteRooks[i] = new Rook(i*7, 7, rookImage[0][i], "White", chessGrid);
            blackRooks[i] = new Rook(i*7, 0, rookImage[1][i], "Black", chessGrid);

            bishopImage[0][i] = new ImageView("Images/W_Bishop.png");
            bishopImage[1][i] = new ImageView("Images/B_Bishop.png");
            whiteBishops[i] = new Bishop( (i+2*(i+1)), 7, bishopImage[0][i], "White", chessGrid);
            blackBishops[i] = new Bishop( (i+2*(i+1)), 0, bishopImage[1][i], "Black", chessGrid);

            // knightImage[0][i] = new ImageView("Images/W_Bishop.png");
            // knightImage[1][i] = new ImageView("Images/B_Bishop.png");
            // whiteKnights[i] = new Bishop((i+2), 7, bishopImage[0][i], "White", chessGrid);
            // blackKnights[i] = new Bishop((i+2), 0, bishopImage[1][i], "Black", chessGrid);

        }
    }
    void tileClicked(ActionEvent ae){
        System.out.println(ae);
        Rectangle tileMove = (Rectangle) (ae.getSource());
        tileMove.getX();
    }
}
