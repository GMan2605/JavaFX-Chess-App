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
        System.out.println(this.yMove);
        System.out.println((this.yPos-1));

        if (this.pieceTeam == "White"){
            if (this.xPos == this.xMove && (this.yPos-1) == this.yMove){
                return true;
            }

        } else if (this.pieceTeam == "Black") {
            
        }
        return false;

    //     if (firstMove == 0) {
    //         if (super.horizontalVertical() == true) {
    //             if (xPos == xMove) {
    //                 if ((yMove - yPos) <= 2 && (yMove - yPos)!= 0) {
    //                     firstMove = 1;
    //                     return true;
    //                 }
    //             }
    //         }
    //     } else if (super.horizontalVertical() == true) {
    //         if (xPos == xMove) {
    //             if (Math.abs(yMove - yPos) == 1 ) {
    //                 return true;
    //             }
    //         }
    //     }
    //     return false;
    }
}