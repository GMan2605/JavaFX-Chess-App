import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class Pawn extends Piece {

    public Pawn(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;

        myImage.setOnMouseClicked(e -> pieceClicked());
        this.drawPiece();
    }

    int firstMove = 0;

    @Override
    boolean moveValid() {

        if (firstMove == 0) {
            if (super.horizontalVertical() == true) {
                if (xPos == xMove) {
                    if ((yMove - yPos) <= 2) {
                        firstMove = 1;
                        return true;
                    }
                }
            }
        } else if (super.horizontalVertical() == true) {
            if (xPos == xMove) {
                if ((yMove - yPos) == 1) {
                    return true;
                }
            }
        }
        return false;
    }
}