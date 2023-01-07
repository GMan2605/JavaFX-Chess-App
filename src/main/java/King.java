import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class King extends Piece {

    public King(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox player2, HBox player1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "King";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }

    @Override
    boolean moveValid() {
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
            // if (this.pieceTeam == "White"){
            //     System.out.println(isCheck(bPieces));
            //     if(isCheck(bPieces))
            //         System.out.println("In Check");
            // }
            // if (this.pieceTeam == "Black"){
            //     System.out.println(isCheck(wPieces));
            //     if(isCheck(wPieces))
            //         System.out.println("In Check");
            // }
            
            this.inValidMovement();
            return false;
            
        }
        this.inValidMovement();
        return false;
    }

    @Override
    public boolean isCheck() {
        if (this.pieceTeam == "Black"){
            for (int i=0; i<wPieces.size(); i++){ 
                Piece tempPiece = wPieces.get(i);
                if (tempPiece.getType() == "King"){
                    // TODO
                    //return true;
                    }
                }
            }
        
        else{
            for (int i=0; i<bPieces.size(); i++){ 
                Piece tempPiece = bPieces.get(i);
                if (tempPiece.getType() == "King"){
                    // TODO
                    //return true;
                }
            }
        }
        return false;
    }
}