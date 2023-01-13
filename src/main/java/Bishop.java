import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Bishop extends Piece {

    public Bishop(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid, HBox score2, HBox score1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.score2 = score2;
        this.score1 = score1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "Bishop";
        this.isSelected = false;
        this.drawPiece();
    }


    @Override
    public boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;
        if (this.diagonal() == true){
            if (this.pieceTeam == "White"){
                if (this.checkBishopMoves(wPieces, bPieces)){
                    return true;
                }
            }
            if (this.pieceTeam == "Black"){
                if (this.checkBishopMoves(bPieces, wPieces)){
                    return true;
                }
            }
        }
        this.inValidMovement();
        return false;
    }


    // @Override
    // public boolean isCheck() {
    //     if (this.pieceTeam == "Black"){
    //         for (int i=0; i<wPieces.size(); i++){ 
    //             Piece tempPiece = wPieces.get(i);
    //             if (tempPiece.getType() == "King"){
    //                 this.xMove = tempPiece.getX();
    //                 this.yMove = tempPiece.getY();
    //                 if (checkBishopMoves(bPieces, wPieces))
    //                     return true;
    //                 }
    //             }
    //         }
        
    //     else {
    //         for (int i=0; i<bPieces.size(); i++){ 
    //             Piece tempPiece = bPieces.get(i);
    //             if (tempPiece.getType() == "King"){
    //                 this.xMove = tempPiece.getX();
    //                 this.yMove = tempPiece.getY();
    //                 if (checkBishopMoves(wPieces, bPieces))
    //                     return true;
    //             }
    //         }
    //     }
    //     return false;
    // }
}