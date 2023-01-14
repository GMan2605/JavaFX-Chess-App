import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Queen extends Piece {

    public Queen(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox score2, HBox score1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.score2 = score2;
        this.score1 = score1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "Queen";
        this.isSelected = false;
        this.drawPiece();
    }

    @Override
    public boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        if (this.horizontalVertical()){ // Was the movment a horizontal or vertical movement! (in the rook pattern)
            if (this.pieceTeam == "White")
                return this.checkRookMoves(wPieces, bPieces);
            else if (this.pieceTeam == "Black")
                return this.checkRookMoves(bPieces, wPieces);
        }
        else if (super.diagonal() == true){ // Was the movment a diagonal movement! (in the bishop pattern)
            if (this.pieceTeam == "White")
                return this.checkBishopMoves(wPieces, bPieces);
            else if (this.pieceTeam == "Black")
                return this.checkBishopMoves(bPieces, wPieces);
        }
        this.inValidMovement();
        return false;
    }
}