import java.util.ArrayList;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Pawn extends Piece {

    boolean isFirstMove = false;
    int pDirection = 1; // pDirection, an int variable that is special to pawns (pawns cannot move backwards, needs something to tell direction)

    public Pawn(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox player2, HBox player1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        if (this.pieceTeam == "Black")
            this.pDirection = 1;
        else
            this.pDirection = -1;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.turnBanner = inputBanner;
        this.correctImage();
        this.pieceType = "Pawn";
        this.isAlive = true;
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
            //Below uses checkInWay, cannot return checkInWay because the other team always needs to be checked
            this.isFirstMove = false;
            if (this.checkInWay(wPieces) == false){
                this.inValidMovement();
                return false;
            } else if (this.checkInWay(bPieces) == false){
                this.inValidMovement();
                return false;
            }
            return true;
        } 
        // Capturing of enemy pieces
        else if ( (this.xPos+1 == this.xMove || this.xPos-1 == this.xMove) && this.yPos+this.pDirection == this.yMove){
            if (this.pieceTeam == "Black"){
                for (int i=0; i<wPieces.size(); i++){
                    Piece tempPiece = wPieces.get(i);
                    if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove && tempPiece.getAliveDead() == true){
                        this.isFirstMove = false;
                        // wPieces.remove(i+1); TODO: Find out why specific "this" data is transfered by removal of object from piece list
                        captureEnemy(tempPiece.getX(), tempPiece.getY(), tempPiece.getImage(), tempPiece.getType(), tempPiece.getTeam());
                        tempPiece.setIsAlive(false);
                        return true;
                    }
                }
            } else if (this.pieceTeam == "White"){
                for (int i=0; i<bPieces.size(); i++){
                    Piece tempPiece = bPieces.get(i);
                    if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove && tempPiece.getAliveDead() == true){
                        this.isFirstMove = false;
                        // bPieces.remove(i+1);
                        captureEnemy(tempPiece.getX(), tempPiece.getY(), tempPiece.getImage(), tempPiece.getType(), tempPiece.getTeam());
                        tempPiece.setIsAlive(false);
                        return true;
                    }
                }
            }
        } 
        // Double movement/"first move" code
        else if (this.isFirstMove == true){
            if (this.xPos == this.xMove && this.yPos+(2*this.pDirection) == this.yMove){
                this.isFirstMove = false;
                if (this.checkInWay(wPieces) == false){
                    this.inValidMovement();
                    return false;
                } else if (this.checkInWay(bPieces) == false){
                    this.inValidMovement();
                    return false;
                }
                return true;
            }
        }
        this.inValidMovement();
        return false;
    }

    /**
     * checkInWay - Boolean method that returns false if a piece is in the way
     * of a current movement, does all resets if so, returns true if no piece
     * on the specified team is in the way
     * 
     * @param listOfTeam the list of the team to be checked
     * @return an flipped or inverse true/false statement on if a piece is in the way
     */
    private boolean checkInWay(ArrayList<Piece> listOfTeam){
        for (int i=0; i<listOfTeam.size(); i++){
            if (listOfTeam.get(i).getX() == this.xMove && listOfTeam.get(i).getY() == this.yMove && listOfTeam.get(i).getAliveDead() == true){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean isCheck(){
        if (this.pieceTeam == "Black"){
            this.pDirection = 1; 
        
            for (int i=0; i<wPieces.size(); i++){ 
                Piece tempPiece = wPieces.get(i);
                if (tempPiece.getType() == "King"){
                    if ((this.xPos+1 == tempPiece.getX() || this.xPos-1 == tempPiece.getX()) && this.xPos+this.pDirection == tempPiece.getY()){
                        return true;
                    }
                }
            }
        }
        else{
            this.pDirection = -1; 
            for (int i=0; i<bPieces.size(); i++){ 
                Piece tempPiece = bPieces.get(i);
                if (tempPiece.getType() == "King"){
                    if ((this.xPos+1 == tempPiece.getX() || this.xPos-1 == tempPiece.getX()) && this.xPos+this.pDirection == tempPiece.getY()){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}