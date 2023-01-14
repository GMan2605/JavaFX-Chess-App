import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Knight extends Piece {

    public Knight(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox score2, HBox score1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.score2 = score2;
        this.score1 = score1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "Knight";
        this.isSelected = false;
        this.drawPiece();
    }
    
    @Override
    public boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        // If statement to check both pathways for a knight movment
        if (Math.abs(this.xMove - this.xPos) == 1 && Math.abs(this.yPos - this.yMove) == 2
        || Math.abs(this.xMove - this.xPos) == 2 && Math.abs(this.yPos - this.yMove) == 1){
            if (this.pieceTeam == "White")
                return this.moveCancelCheck(wPieces, bPieces);
            else if (this.pieceTeam == "Black")
                return this.moveCancelCheck(bPieces, wPieces);
        }
        this.inValidMovement();
        return false;
    }
}