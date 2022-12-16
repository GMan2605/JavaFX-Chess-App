import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Pawn extends Piece {

    boolean firstMove = true;

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


    @Override
    boolean moveValid() {
        System.out.println(this.yMove);
        System.out.println((this.yPos-1));

        // if (this.pieceTeam == "White"){
        //     if (this.xPos == this.xMove && (this.yPos-1) == this.yMove){
        //         return true;
        //     }

        // } else if (this.pieceTeam == "Black") {
            
        // }
        // return false;

        if (this.firstMove) {
            if (super.horizontalVertical() == true) {
                if (Math.abs(this.yMove - this.yPos) <= 2 && Math.abs(this.yMove - this.yPos)!= 0) {
                    this.firstMove = false;
                    return true;
                }
            }
        } else if (super.horizontalVertical() == true) {
            if (enemyPiece && diagonal()){
                if (Math.abs(xMove - xPos) == 1 && Math.abs(yMove - yPos) == 1)
                    return true;
            }
            if (Math.abs(this.yMove - this.yPos) == 1 ) {
                return true;
            }
            
        }
        return false;
    }
}