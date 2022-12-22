import java.util.*;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class chessController implements Initializable{

    public ArrayList<Piece> whitePieces = new ArrayList<>();
    public ArrayList<Piece> blackPieces = new ArrayList<>();
    public static boolean pieceChosen = false;
    public static String turnString = "White's Turn";
    int tileSize = 55;
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
    int whiteLeft;
    int blackLeft;

    @FXML
    VBox chessGrid;

    @FXML
    Label turnBanner;

    @FXML
    AnchorPane player1;

    @FXML
    AnchorPane player2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // For-loop section that adds all components to make a visual board

        ImageView wPawnImage = new ImageView("Images/W_Pawn.png");
        ImageView bPawnImage = new ImageView("Images/W_Pawn.png");
        wPawnImage.setFitHeight(25);
        bPawnImage.setFitHeight(25);
        wPawnImage.setFitWidth(25);
        bPawnImage.setFitWidth(25);

        wPawnImage.setX(75);
        bPawnImage.setX(75);
        wPawnImage.setY(26);
        bPawnImage.setY(16);

        System.out.println(wPawnDied);

       
        ///////////////////////////////////////////////////////////////////////////////////////

        ImageView profilePicImg = new ImageView("Images/Profile_Pic.png");
        ImageView profilePicImg2 = new ImageView("Images/Profile_Pic_2.png");

        profilePicImg.setFitHeight(55);
        profilePicImg2.setFitHeight(55);
        profilePicImg.setFitWidth(55);
        profilePicImg2.setFitWidth(55);

        profilePicImg.setX(8);
        profilePicImg2.setX(8);
        profilePicImg.setY(16);
        profilePicImg2.setY(0);
        //ImageView profilePicImg3 = new ImageView("Images/Profile_Pic_3.png"); 

        player1.getChildren().add(profilePicImg);
        player2.getChildren().add(profilePicImg2);

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

                                    if (ae.getSource().equals(gridSpot[i][j])){
                                        System.out.println(gridSpot[i][j].getChildren());
                                    }
                                    if (ae.getSource().equals(tiles[i][j])){ //Check if a tile at [i][j] was the one that was clicked
                                        // System.out.println("A tile at position: " + i + ", " + j + " was chosen for movement!");

                                        for (int k=0; k<whitePieces.size(); k++){
                                            if (turnString == "White's Turn"){
                                                if (whitePieces.get(k).getHightlight()){
                                                    whitePieces.get(k).setMovements(j, i);
                                                    if (whitePieces.get(k).moveValid(whitePieces, blackPieces)){
                                                        whitePieces.get(k).move();
                                                        turnString = "Black's Turn";
                                                        turnBanner.setText("Black Team's Turn!");
                                                        turnBanner.setTextFill(Color.BLACK);
                                                    }
                                                    
                                                }
                                            }
                                        }
                                        for (int l=0; l<blackPieces.size(); l++){
                                            if (turnString == "Black's Turn"){
                                                if (blackPieces.get(l).getHightlight()){
                                                    blackPieces.get(l).setMovements(j, i);
                                                    if (blackPieces.get(l).moveValid(whitePieces, blackPieces)){
                                                        blackPieces.get(l).move();
                                                        turnString = "White's Turn";
                                                        turnBanner.setText("White Team's Turn!");
                                                        turnBanner.setTextFill(Color.WHITE);
                                                    }
                                                    System.out.println(wPawnDied);
                                                    if (wPawnDied){
                                                        player2.getChildren().add(wPawnImage);
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
        
        // Adding of all pieces to lists (For future events)
        whitePieces.addAll(Arrays.asList(wPawns));
        whitePieces.addAll(Arrays.asList(wRooks));
        whitePieces.addAll(Arrays.asList(wBishops));
        whitePieces.addAll(Arrays.asList(wKnights));
        whitePieces.add(wKing);
        whitePieces.add(wQueen);
        blackPieces.addAll(Arrays.asList(bPawns));
        blackPieces.addAll(Arrays.asList(bRooks));
        blackPieces.addAll(Arrays.asList(bBishops));
        blackPieces.addAll(Arrays.asList(bKnights));
        blackPieces.add(bKing);
        blackPieces.add(bQueen);

       
       
        // if (blackPieces.size() < whiteLeft){

        // }
        // whiteLeft = whitePieces.size();

        // if (blackPieces.size() < blackLeft){

        // }
        // blackLeft = blackPieces.size();
    }
}
