import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Pawn extends Piece {

    boolean isFirstMove = false;
    int pDirection = 1;

    public Pawn(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        if (this.pieceTeam == "Black")
            this.pDirection = 1;
        else
            this.pDirection = -1;
        this.referenceGrid = chessGrid;
        this.correctImage();
        this.pieceType = "Pawn";
        this.isFirstMove = true;
        this.isSelected = false;
        this.drawPiece();
    }
    
    // Adjusted Movement Code (to be review over for directional movement of different pawn teams)
    @Override
    boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        // Basic 1 tile movment
        if (this.xPos == this.xMove && this.yPos+this.pDirection == this.yMove){
            this.isFirstMove = false;
            return true;
        } 
        // Double movement/"first move" code
        else if (this.isFirstMove == true){
            if (this.xPos == this.xMove && this.yPos+(2*this.pDirection) == this.yMove){
                this.isFirstMove = false;
                return true;
            }
        }
        // Capturing of enemy pieces
        else if ( (enemyPiece == true && this.xPos+1 == this.xMove || this.xPos-1 == this.xMove) && (this.yPos)+this.pDirection == this.yMove){
            this.isFirstMove = false;
            return true;
        }
        this.isFirstMove = true;
        this.removeImageAtPos();
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
        return false;
    }

    // @Override
    // boolean moveValid() {
    //     this.isSelected = false;
    //     if (this.isFirstMove) {
    //         if (super.horizontalVertical() == true) {
    //             if (this.xPos == this.xMove) {
    //                 if (Math.abs(this.yMove - this.yPos) <= 2 && Math.abs(this.yMove - this.yPos) != 0) {
    //                     this.isFirstMove = false;
    //                     pieceChosen = false;
    //                     return true;
    //                 }
    //             }
    //         }
    //     } else if (super.horizontalVertical() == true) {
    //         if (this.xPos == this.xMove) {
    //             if (Math.abs(this.yMove - this.yPos) == 1 ) {
    //                 this.isFirstMove = false;
    //                 pieceChosen = false;
    //                 return true;
    //             }
    //         }
    //     }
    //     this.isFirstMove = false;
    //     this.xMove = 0;
    //     this.yMove = 0;
    //     this.unhighlightPiece();
    //     return false;
    // }
}