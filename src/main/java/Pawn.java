import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Pawn extends Piece {

    boolean isFirstMove = false;
    int pDirection = 1;

    public Pawn(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, AnchorPane player2, AnchorPane player1){
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
            if (this.checkInWay(wPieces) == false)
                return false;
            else if (this.checkInWay(bPieces) == false)
                return false;
            
            this.isFirstMove = false;
            return true;
        } 
        // Double movement/"first move" code
        else if (this.isFirstMove == true){
            if (this.xPos == this.xMove && this.yPos+(2*this.pDirection) == this.yMove){
                if (this.checkInWay(wPieces) == false)
                    return false;
                else if (this.checkInWay(bPieces) == false)
                    return false;
                this.isFirstMove = false;
                return true;
            }
        }
        // Capturing of enemy pieces
        else if ( (this.xPos+1 == this.xMove || this.xPos-1 == this.xMove) && (this.yPos)+this.pDirection == this.yMove){
            if (this.pieceTeam == "Black"){
                for (int i=0; i<wPieces.size(); i++){
                    if (wPieces.get(i).getX() == this.xMove && wPieces.get(i).getY() == this.yMove && wPieces.get(i).getAliveDead() == true){
                        this.isFirstMove = false;
                        // wPieces.remove(i+1); TODO: Find out why specific "this" data is transfered by removal of object from piece list
                        captureEnemy(wPieces.get(i).getX(), wPieces.get(i).getY(), wPieces.get(i).getImage(), wPieces.get(i).getType(), wPieces.get(i).getTeam());
                        wPieces.get(i).setIsAlive(false);
                        return true;
                    }
                }
            } else if (this.pieceTeam == "White"){
                for (int i=0; i<bPieces.size(); i++){
                    if (bPieces.get(i).getX() == this.xMove && bPieces.get(i).getY() == this.yMove && bPieces.get(i).getAliveDead() == true){
                        this.isFirstMove = false;
                        // bPieces.remove(i+1);
                        captureEnemy(bPieces.get(i).getX(), bPieces.get(i).getY(), bPieces.get(i).getImage(), bPieces.get(i).getType(), bPieces.get(i).getTeam());
                        bPieces.get(i).setIsAlive(false);
                        return true;
                    }
                }

            }
        }
        
        this.isFirstMove = false;
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
                this.isFirstMove = false;
                this.inValidMovement();
                return false;
            }
        }
        return true;
    }
}