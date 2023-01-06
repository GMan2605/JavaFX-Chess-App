import java.util.ArrayList;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class King extends Piece {

    public King(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, AnchorPane player2, AnchorPane player1, Label inputBanner){
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
            this.inValidMovement();
            return false;
        }
        this.inValidMovement();
        return false;
    }

    // TODO: FIND WAY TO RELATE BACK TO KNIGHT"S METHOD/REPLICATE ACROSS ALL PIECES
     /**
     * moveCancelCheck - Method specific to knight, it simply returns the proper movement
     * if a valid move was already detected (checks if it should capture, captures if so, also
     * stops a movement if a piece of the same team is in the way).
     * 
     * @param myTeam list of pieces on the same team as this piece
     * @param enemyTeam list of pieces on the enemy team as this piece
     */
    private boolean moveCancelCheck(ArrayList<Piece> myTeam, ArrayList<Piece> enemyTeam){
        for (int i=0; i<myTeam.size(); i++){ // Check if any piece on this piece's team is in the way
            Piece tempPiece = myTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove && tempPiece.getAliveDead() == true){
                this.inValidMovement();
                return false;
            }
        }
        for (int i=0; i<enemyTeam.size(); i++){ // Check if this piece can capture, capture if so (enemy piece in movment space)
            Piece tempPiece = enemyTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove && tempPiece.getAliveDead() == true){
                captureEnemy(tempPiece.getX(), tempPiece.getY(), tempPiece.getImage(), tempPiece.getType(), tempPiece.getTeam());
                tempPiece.setIsAlive(false);
                return true;
            }
        }
        
        return true; // Return true if no invalid move was found, or no capture was found (no capture code here)
    }
}