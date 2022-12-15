import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class King extends Piece {

    public King(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;
        this.correctImage();
        pieceType = "King";
        isSelected = false;
        this.drawPiece();
    }

    @Override
    boolean moveValid() {

        if ((super.horizontalVertical() == true) || 
            (super.diagonal() == true)) {
            if (((xMove - xPos) == 1) && (yMove - yPos) == 1){
                return true;
            }
            if ((xMove - xPos) == 1 && (yMove == yPos)){
                return true;
            }
            if ((xMove == xPos) && (yMove - yPos) == 1){
                return true;
            }
        }
        return false;
    }

}