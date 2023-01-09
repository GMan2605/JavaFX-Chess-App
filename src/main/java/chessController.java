import java.util.*;
import java.net.URL;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    public static ArrayList<Piece> wPieces = new ArrayList<>();
    public static ArrayList<Piece> bPieces = new ArrayList<>();
    public static boolean pieceChosen = false;
    public static String turnString = "White's Turn";
    int tileSize = 45;
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
    Boolean wPawnDied = false;
    Boolean bPawnDied = false;

    @FXML
    VBox chessGrid;

    @FXML
    Label turnBanner;

    @FXML
    HBox player1;

    @FXML
    HBox player2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //TODO: Fix this to be more efficient, make player1 and player2 variables differnet names
        ImageView profilePicImg = new ImageView("Images/Profile_Pic.png");
        ImageView profilePicImg2 = new ImageView("Images/Profile_Pic_2.png");
        profilePicImg.setFitHeight(tileSize);
        profilePicImg2.setFitHeight(tileSize);
        profilePicImg.setFitWidth(tileSize);
        profilePicImg2.setFitWidth(tileSize);
        profilePicImg.setX(201);
        profilePicImg2.setX(201);
        profilePicImg.setY(20);
        profilePicImg2.setY(0);
        player1.getChildren().add(profilePicImg);
        player2.getChildren().add(profilePicImg2);
        
        // For-loop section that adds all components to make a visual board
        for (int i=0; i<8; i++){ // y dimension loop
            gridRows[i] = new HBox(0);
            gridRows[i].setId(Integer.toString(i));
            for (int j=0; j<8; j++){ // x dimension loop
                gridSpot[i][j] = new StackPane();
                tiles[i][j] = new Rectangle(tileSize, tileSize);

                if ( (j+i) % 2 == 0) // Is the sum of the x & y even? (creates "checker" pattern)
                    tiles[i][j].setFill(Color.web("#EEEED2"));
                else
                    tiles[i][j].setFill(Color.web("#769656"));

                tiles[i][j].setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent ae){
                        if (pieceChosen){ //Only Check for tile input if a piece has been selected
                            for (int i=0; i<8; i++){ //Search all y's
                                for (int j=0; j<8; j++){ //Search all x's

                                    if (ae.getSource().equals(tiles[i][j])){ //Check if a tile at [i][j] was the one that was clicked
                                        // System.out.println("A tile at position: " + j + ", " + i + " was chosen for movement!");
                                        for (int k=0; k<wPieces.size(); k++){
                                            if (turnString == "White's Turn"){
                                                if (wPieces.get(k).getHightlight()){ // Using the loop, find out if this team has a highlighted piece, and try to move!
                                                    wPieces.get(k).setMovements(j, i);
                                                    if (wPieces.get(k).moveValid()){
                                                        wPieces.get(k).move();
                                                        turnString = "Black's Turn";
                                                        turnBanner.setText("Black Team's Turn!");
                                                        turnBanner.setTextFill(Color.BLACK);
                                                            if (wPieces.get(k).isCheck()){
                                                                System.out.println("Black's In Check");
                                                            }
                                                    }
                                                    
                                                }
                                            }
                                        }
                                        for (int l=0; l<bPieces.size(); l++){
                                            if (turnString == "Black's Turn"){
                                                if (bPieces.get(l).getHightlight()){
                                                    bPieces.get(l).setMovements(j, i);
                                                    if (bPieces.get(l).moveValid()){
                                                        bPieces.get(l).move();
                                                        turnString = "White's Turn";
                                                        turnBanner.setText("White Team's Turn!");
                                                        turnBanner.setTextFill(Color.WHITE);
                                                            if (bPieces.get(l).isCheck()){
                                                                System.out.println("White's In Check");
                                                            }
                                                    }
                                                
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
                gridSpot[i][j].getChildren().add(tiles[i][j]);
                gridRows[i].setAlignment(Pos.CENTER);
                gridRows[i].getChildren().add(gridSpot[i][j]);
            }
            chessGrid.getChildren().add(gridRows[i]);
        }

        // Creation of Kings & Queens
        King bKing = new King(4, 0, new ImageView("Images/B_King.png"), "Black", chessGrid, player2, player1, turnBanner);
        King wKing = new King(4, 7, new ImageView("Images/W_King.png"), "White", chessGrid, player2, player1, turnBanner);
        Queen bQueen = new Queen(3, 0, new ImageView("Images/B_Queen.png"), "Black", chessGrid, player2, player1, turnBanner);
        Queen wQueen = new Queen(3, 7, new ImageView("Images/W_Queen.png"), "White", chessGrid, player2, player1, turnBanner);

        // Creation of the pawn layout
        for (int i=0; i<8; i++){
            pawnImage[0][i] = new ImageView("Images/W_Pawn.png");
            pawnImage[1][i] = new ImageView("Images/B_Pawn.png");
            wPawns[i] = new Pawn(i, 6, pawnImage[0][i], "White", chessGrid, player2, player1, turnBanner);
            bPawns[i] = new Pawn(i, 1, pawnImage[1][i], "Black", chessGrid, player2, player1, turnBanner);
        }

        // Creation of all "dual" pieces, pieces that occur twice on each team
        for (int i=0; i<2; i++){
            rookImage[0][i] = new ImageView("Images/W_Rook.png");
            rookImage[1][i] = new ImageView("Images/B_Rook.png");
            wRooks[i] = new Rook(i*7, 7, rookImage[0][i], "White", chessGrid, player2, player1, turnBanner);
            bRooks[i] = new Rook(i*7, 0, rookImage[1][i], "Black", chessGrid, player2, player1, turnBanner);

            bishopImage[0][i] = new ImageView("Images/W_Bishop.png");
            bishopImage[1][i] = new ImageView("Images/B_Bishop.png");
            wBishops[i] = new Bishop( (i+2*(i+1)), 7, bishopImage[0][i], "White", chessGrid, player2, player1, turnBanner);
            bBishops[i] = new Bishop( (i+2*(i+1)), 0, bishopImage[1][i], "Black", chessGrid, player2, player1, turnBanner);

            knightImage[0][i] = new ImageView("Images/W_Knight.png");
            knightImage[1][i] = new ImageView("Images/B_Knight.png");
            wKnights[i] = new Knight((i*5+1), 7, knightImage[0][i], "White", chessGrid, player2, player1, turnBanner);
            bKnights[i] = new Knight((i*5+1), 0, knightImage[1][i], "Black", chessGrid, player2, player1, turnBanner);
        }
        
        // Adding of all pieces to lists (For future events)
        wPieces.addAll(Arrays.asList(wPawns));
        wPieces.addAll(Arrays.asList(wRooks));
        wPieces.addAll(Arrays.asList(wBishops));
        wPieces.addAll(Arrays.asList(wKnights));
        wPieces.add(wKing);
        wPieces.add(wQueen);
        bPieces.addAll(Arrays.asList(bPawns));
        bPieces.addAll(Arrays.asList(bRooks));
        bPieces.addAll(Arrays.asList(bBishops));
        bPieces.addAll(Arrays.asList(bKnights));
        bPieces.add(bKing);
        bPieces.add(bQueen);

    }

    /**
     * getBanner - Returns the "turnBanner" label object. It is used
     * for getting turnBanner inside of piece objects, which only
     * require its use about twice (this just avoids adding another
     * parameter to all constructors)
     * 
     * @return the current turnBanner label object
     */
    public Label getBanner(){
        return turnBanner;
    }
}
