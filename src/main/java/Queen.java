import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Queen extends Piece {

    public Queen(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.correctImage();
        this.pieceType = "Queen";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }

    @Override
    boolean moveValid() {

        if (super.horizontalVertical() == true){
            return true;
        } else if (super.diagonal() == true){
            return true;
        }
        return false;
    }
}