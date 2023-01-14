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
}