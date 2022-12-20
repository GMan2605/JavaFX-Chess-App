import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Knight extends Piece {

    public Knight(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;
        this.correctImage();
        pieceType = "Knight";
        isSelected = false;
        this.drawPiece();
    }
    
    @Override
    boolean moveValid(ArrayList<Piece> wPieces, ArrayList<Piece> bPieces) {
        if (Math.abs(this.xMove - this.xPos) == 1 && Math.abs(this.yPos - this.yMove) == 2){
            return true;
        }
        else if (Math.abs(this.xMove - this.xPos) == 2 && Math.abs(this.yPos - this.yMove) == 1){
            return true;
        }
    return false;
    }
}