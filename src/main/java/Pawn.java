import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Pawn extends Piece {

    public Pawn(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;
        this.correctImage();
        pieceType = "Pawn";
        isSelected = false;
        this.drawPiece();
    }

    int firstMove = 0;

    @Override
    boolean moveValid() {
        //System.out.println(yMove);
        if (firstMove == 0) {
            if (super.horizontalVertical() == true) {
                if (this.xPos == xMove) {
                    if (Math.abs(yMove - this.yPos) <= 2 && Math.abs(yMove - this.yPos)!= 0) {
                        firstMove = 1;
                        return true;
                    }
                }
            }
        } else if (super.horizontalVertical() == true) {
                if (Math.abs(yMove - this.yPos) == 1 ) {
                    return true;
                
            }
        }
        return false;
    }
}