import java.util.*;
import java.io.IOException;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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

    public static ArrayList<Piece> wPieces = new ArrayList<>();
    public static ArrayList<Piece> bPieces = new ArrayList<>();
    public static boolean pieceChosen = false;
    public static String turnString = "White's Turn";
    public static String winTeam = null;
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

    Button returnToMenu = new Button("Return to Menu.");
    Button replayB = new Button("Start Another Game!");

    String pawnText = "Can only move once\nexcept when it makes\nits first move and\nthen it canmove\n2 times can only go\nforward and capture\ndiagonally";
    String knightText = "Text Moves in an\nl shape: 2 up 1 left\nor right-or-1 up\n2 left or tight\n\nOnly piece that can\nonly capture\nwhen jumping";
    String bishopText = "A bishop may only\nmove diagonally and\ncan move as far\nas its line of sight";
    String rookText = "A rook may only move\nstraight and can move\nas far as its\nline of sight be\nit forward/backward,\nleft/right";
    String queenText = "She can move on\nthe straights and on\nthe diagonals in\nline of sight";
    String kingText = "Can move and capture\non any square\nrestricted to one\nmove per turn-can\nmove in any direction\n- straights or diagonals\nmay capture in any\ndirection that's within\nits legal move range";


    @FXML
    HBox score1;

    @FXML
    HBox score2;

    @FXML
    AnchorPane mainAnchor;

    @FXML 
    Label pieceInfo;

    @FXML 
    VBox helpMenu;

    @FXML
    VBox chessGrid;

    @FXML
    Label turnBanner;

    @FXML
    StackPane player1;

    @FXML
    StackPane player2;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /* Confirm variables are what they should be for a new game to start
            (To avoid bugs/reset game for a retry game/when user has pressed a replay button)
        */
        wPieces.clear();;
        bPieces.clear();
        pieceChosen = false;
        turnString = "White's Turn";
        winTeam = null;

        ImageView[] profilePicImgs = new ImageView[2];
        for (int i=0; i<profilePicImgs.length; i++){
            profilePicImgs[i] = new ImageView("Images/Profile_Pic_" + (i+1) + ".png");
            profilePicImgs[i].setFitHeight(tileSize);
            profilePicImgs[i].setFitWidth(tileSize);
            profilePicImgs[i].setX(0);
        }
        player1.getChildren().add(profilePicImgs[0]);
        player2.getChildren().add(profilePicImgs[1]);

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
        King bKing = new King(4, 0, new ImageView("Images/B_King.png"), "Black", chessGrid, score2, score1, turnBanner);
        King wKing = new King(4, 7, new ImageView("Images/W_King.png"), "White", chessGrid, score2, score1, turnBanner);
        Queen bQueen = new Queen(3, 0, new ImageView("Images/B_Queen.png"), "Black", chessGrid, score2, score1, turnBanner);
        Queen wQueen = new Queen(3, 7, new ImageView("Images/W_Queen.png"), "White", chessGrid, score2, score1, turnBanner);

        // Creation of the pawn layout
        for (int i=0; i<8; i++){
            pawnImage[0][i] = new ImageView("Images/W_Pawn.png");
            pawnImage[1][i] = new ImageView("Images/B_Pawn.png");
            wPawns[i] = new Pawn(i, 6, pawnImage[0][i], "White", chessGrid, score2, score1, turnBanner);
            bPawns[i] = new Pawn(i, 1, pawnImage[1][i], "Black", chessGrid, score2, score1, turnBanner);
        }

        // Creation of all "dual" pieces, pieces that occur twice on each team
        for (int i=0; i<2; i++){
            rookImage[0][i] = new ImageView("Images/W_Rook.png");
            rookImage[1][i] = new ImageView("Images/B_Rook.png");
            wRooks[i] = new Rook(i*7, 7, rookImage[0][i], "White", chessGrid, score2, score1, turnBanner);
            bRooks[i] = new Rook(i*7, 0, rookImage[1][i], "Black", chessGrid, score2, score1, turnBanner);

            bishopImage[0][i] = new ImageView("Images/W_Bishop.png");
            bishopImage[1][i] = new ImageView("Images/B_Bishop.png");
            wBishops[i] = new Bishop( (i+2*(i+1)), 7, bishopImage[0][i], "White", chessGrid, score2, score1, turnBanner);
            bBishops[i] = new Bishop( (i+2*(i+1)), 0, bishopImage[1][i], "Black", chessGrid, score2, score1, turnBanner);

            knightImage[0][i] = new ImageView("Images/W_Knight.png");
            knightImage[1][i] = new ImageView("Images/B_Knight.png");
            wKnights[i] = new Knight((i*5+1), 7, knightImage[0][i], "White", chessGrid, score2, score1, turnBanner);
            bKnights[i] = new Knight((i*5+1), 0, knightImage[1][i], "Black", chessGrid, score2, score1, turnBanner);
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
     * 
     * @param winner
     */
    public void displayWinner(String winner){
        App.wString = winner;
        try {
            App.setRoot("winScreen");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buttonClicked(ActionEvent ae) { 
        Button tempButton = (Button)(ae.getSource());
        //System.out.println(tempButton.getId());

        if (tempButton.getId().equals("pawn")){

            pieceInfo.setText(pawnText);
        }
        if (tempButton.getId().equals("knight")){
            pieceInfo.setText(knightText);
        }
        if (tempButton.getId().equals("bishop")){
            pieceInfo.setText(bishopText);
        }
        if (tempButton.getId().equals("rook")){
            pieceInfo.setText(rookText);
        }
        if (tempButton.getId().equals("king")){
            pieceInfo.setText(kingText);
        }
        if (tempButton.getId().equals("queen")){
            pieceInfo.setText(queenText);
        }
    }
}
