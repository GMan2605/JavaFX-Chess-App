import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class King extends Piece {

    public King(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox score2, HBox score1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.score2 = score2;
        this.score1 = score1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "King";
        this.isSelected = false;
        this.drawPiece();
    }

    @Override
    public boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        if (this.horizontalVertical() || this.diagonal()){ // Check if it was a horizontal or vertical movment first (excludes some spaces by default)

            // Check three scenarios including: horiontal (left/right) move by 1 space, vertically (up/down) move by 1 space, & any diagonal movement
            if (Math.abs(this.xPos-this.xMove) == 1 && this.yPos == this.yMove
            || this.xPos == this.xMove && Math.abs(this.yPos-this.yMove) == 1
            || Math.abs(this.xPos-this.xMove) == 1 && Math.abs(this.yPos-this.yMove) == 1){
                
                if (this.pieceTeam == "White")
                    return this.moveCancelCheck(wPieces, bPieces);
                else if (this.pieceTeam == "Black")
                    return this.moveCancelCheck(bPieces, wPieces);
            }
            this.inValidMovement();
            return false;
            
        }
        this.inValidMovement();
        return false;
    }
}