import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid, AnchorPane player2, AnchorPane player1){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.correctImage();
        this.pieceType = "Rook";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }


    @Override
    boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;

        if (this.horizontalVertical()){ // Was the movment a horizontal or vertical movement! (in the rook pattern)
            if (this.pieceTeam == "White")
                return this.checkRookMoves(wPieces, bPieces);
            else if (this.pieceTeam == "Black")
                return this.checkRookMoves(bPieces, wPieces);
        }

        this.inValidMovement();
        return false;
    }

    /**
     * checkRookMoves - Method that returns a true or false statement regarding a rook movement pattern and this piece.
     * It requires specified "friendly" and "enemy" teams to evaluate any possible incorrect patterns in the movement
     * (it returns true by default, however it must not return false after all the checking/detections). To do this, it
     * first checks that the move does not infringe/go through or even land on a friendly piece. Then it moves onto detecting
     * if a capture should take place or not, canceling the move if an enemy piece is blocking the way of the requested
     * movement from xMove & yMove of this specific piece.
     * 
     * @param myTeam A list of pieces representing this piece's team
     * @param enemyTeam Another list of pieces that represent the enemy team
     * @return A true or false statement on if the requested movement is valid
     */
    private boolean checkRookMoves(ArrayList<Piece> myTeam, ArrayList<Piece> enemyTeam){
        for (int i=0; i<myTeam.size(); i++){ //Movement cancellation section (for same-team pieces)
            int pieceX = myTeam.get(i).getX();
            int pieceY = myTeam.get(i).getY();
            if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove && myTeam.get(i).getAliveDead() == true
            || this.yPos > pieceY && pieceY >= this.yMove && myTeam.get(i).getAliveDead() == true) ){
                this.inValidMovement();
                return false;
            } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove && myTeam.get(i).getAliveDead() == true
            || this.xPos > pieceX && pieceX >= this.xMove && myTeam.get(i).getAliveDead() == true) ){
                this.inValidMovement();
                return false;
            }
        }
        for (int i=0; i<enemyTeam.size(); i++){ //Capturing section
            int pieceX = enemyTeam.get(i).getX();
            int pieceY = enemyTeam.get(i).getY();

            /* 
            Do a similar check if there are any other black pieces in-between 
            the movemnet, but don't count the square the capture is trying to take place
            (no <= or >= signs, replaced with < & >)
             */
            if (pieceX == this.xPos && (this.yPos < pieceY && pieceY < this.yMove && enemyTeam.get(i).getAliveDead() == true
            || this.yPos > pieceY && pieceY > this.yMove && enemyTeam.get(i).getAliveDead() == true) ){
                this.inValidMovement();
                return false;
            } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX < this.xMove && enemyTeam.get(i).getAliveDead() == true 
            || this.xPos > pieceX && pieceX > this.xMove && enemyTeam.get(i).getAliveDead() == true) ){
                this.inValidMovement();
                return false;
            }
        }

        // Finally, if no black or white pieces are in the way of the movement, check if there is an enemy piece in the way
        for (int i=0; i<enemyTeam.size(); i++){
            Piece tempPiece = enemyTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove && tempPiece.getAliveDead() == true){
                captureEnemy(tempPiece.getX(), tempPiece.getY(), tempPiece.getImage(), tempPiece.getType(), tempPiece.getTeam());
                tempPiece.setIsAlive(false);
            }
        }
        return true;
    }
}