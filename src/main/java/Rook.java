import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid, AnchorPane player2, AnchorPane player1){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.correctImage();
        this.pieceType = "Rook";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }


    @Override
    boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        if (this.horizontalVertical()){ // Was the movment a horizontal or vertical movement! (in the rook pattern)
            if (this.pieceTeam == "White")
                return this.checkRookMoves(wPieces, bPieces);
            else if (this.pieceTeam == "Black")
                return this.checkRookMoves(bPieces, wPieces);
        }

        this.inValidMovement();
        return false;
    }
}