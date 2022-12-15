import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Queen extends Piece {

    public Queen(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;
        this.correctImage();
        pieceType = "Queen";
        isSelected = false;
        this.drawPiece();
    }

    @Override
    boolean moveValid() {

        if (super.horizontalVertical() == true){
            return true;
        }else if (super.diagonal() == true){
            return true;
        }
        return false;
    }
}