import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Knight extends Piece {

    public Knight(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.correctImage();
        this.pieceType = "Knight";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }
    
    @Override
    boolean moveValid() {
        if (Math.abs(this.xMove - this.xPos) == 1 && Math.abs(this.yPos - this.yMove) == 2){
            return true;
        }
        else if (Math.abs(this.xMove - this.xPos) == 2 && Math.abs(this.yPos - this.yMove) == 1){
            return true;
        }
    return false;
    }
}