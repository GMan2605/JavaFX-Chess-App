import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Knight extends Piece {

    public Knight(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, AnchorPane player2, AnchorPane player1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "Knight";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }
    
    @Override
    boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        if (Math.abs(this.xMove - this.xPos) == 1 && Math.abs(this.yPos - this.yMove) == 2){
            return true;
        }
        else if (Math.abs(this.xMove - this.xPos) == 2 && Math.abs(this.yPos - this.yMove) == 1){
            return true;
        }
        this.inValidMovement();
        return false;
    }
}