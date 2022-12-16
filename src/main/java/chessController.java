import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    public boolean enemyPiece = false;
    public static boolean pieceChosen = false;
    StackPane[][] gridSpot = new StackPane[8][8];
    Rectangle[][] tiles = new Rectangle[8][8];
    HBox[] gridRows = new HBox[8];
    Pawn[] wPawns = new Pawn[8];
    Pawn[] bPawns = new Pawn[8];
    Rook[] wRooks = new Rook[2];
    Rook[] bRooks = new Rook[2];
    Bishop[] wBishops = new Bishop[2];
    Bishop[] bBishops = new Bishop[2];
    Knight[] wKnights = new Knight[2];
    Knight[] bKnights = new Knight[2];
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

                tiles[i][j].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent ae){
                        if (pieceChosen){ //Only Check for tile input if a piece has been selected
                            for (int i=0; i<8; i++){ //Search all y's
                                for (int j=0; j<8; j++){ //Search all x's

                                    if (ae.getSource().equals(gridSpot[i][j])){
                                        System.out.println(gridSpot[i][j].getChildren());
                                    }
                                    if (ae.getSource().equals(tiles[i][j])){ //Check if a tile at [i][j] was the one that was clicked
                                        // System.out.println("A tile at position: " + i + ", " + j + " was chosen for movement!");

                                        for (int k=0; k<8; k++){
                                            if (wPawns[k].getHightlight()){
                                                wPawns[k].setMovements(j, i);
                                                if (wPawns[k].moveValid())
                                                    wPawns[k].move();
                                            } else if (bPawns[k].getHightlight()){
                                                bPawns[k].setMovements(j, i);
                                                if (bPawns[k].moveValid())
                                                    bPawns[k].move();
                                            }
                                        }

                                    }
                                }
                            }
                        }
                    }
                });
                gridSpot[i][j].getChildren().add(tiles[i][j]);
                gridRows[i].getChildren().add(gridSpot[i][j]);
            }
            chessGrid.getChildren().add(gridRows[i]);
        }

        // Creation of Kings & Queens
        King bKing = new King(4, 0, new ImageView("Images/B_King.png"), "Black", chessGrid);
        King wKing = new King(4, 7, new ImageView("Images/W_King.png"), "White", chessGrid);
        Queen bQueen = new Queen(3, 0, new ImageView("Images/B_Queen.png"), "Black", chessGrid);
        Queen wQueen = new Queen(3, 7, new ImageView("Images/W_Queen.png"), "White", chessGrid);

        // Creation of the pawn layout
        for (int i=0; i<8; i++){
            pawnImage[0][i] = new ImageView("Images/W_Pawn.png");
            pawnImage[1][i] = new ImageView("Images/B_Pawn.png");
            wPawns[i] = new Pawn(i, 6, pawnImage[0][i], "White", chessGrid);
            bPawns[i] = new Pawn(i, 1, pawnImage[1][i], "Black", chessGrid);
        }

        // Creation of all "dual" pieces, pieces that occur twice on each team
        for (int i=0; i<2; i++){
            rookImage[0][i] = new ImageView("Images/W_Rook.png");
            rookImage[1][i] = new ImageView("Images/B_Rook.png");
            wRooks[i] = new Rook(i*7, 7, rookImage[0][i], "White", chessGrid);
            bRooks[i] = new Rook(i*7, 0, rookImage[1][i], "Black", chessGrid);

            bishopImage[0][i] = new ImageView("Images/W_Bishop.png");
            bishopImage[1][i] = new ImageView("Images/B_Bishop.png");
            wBishops[i] = new Bishop( (i+2*(i+1)), 7, bishopImage[0][i], "White", chessGrid);
            bBishops[i] = new Bishop( (i+2*(i+1)), 0, bishopImage[1][i], "Black", chessGrid);

            knightImage[0][i] = new ImageView("Images/W_Knight.png");
            knightImage[1][i] = new ImageView("Images/B_Knight.png");
            wKnights[i] = new Knight((i*5+1), 7, knightImage[0][i], "White", chessGrid);
            bKnights[i] = new Knight((i*5+1), 0, knightImage[1][i], "Black", chessGrid);
        }
        // allPieces.addAll(Arrays.asList(wKnights));
        // allPieces.addAll(Arrays.asList(wPawns, bPawns, wRooks, bRooks, wBishops, bBishops, wKnights, bKnights));
    }
}
