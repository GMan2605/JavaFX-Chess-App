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
            if (this.pieceTeam == "White"){
                for (int i=0; i<wPieces.size(); i++){ //Movement section
                    int pieceX = wPieces.get(i).getX();
                    int pieceY = wPieces.get(i).getY();
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove || this.yPos > pieceY && pieceY >= this.yMove && wPieces.get(i).getAliveDead() == true) ){
                        this.inValidMovement();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove || this.xPos > pieceX && pieceX >= this.xMove && wPieces.get(i).getAliveDead() == true) ){
                        this.inValidMovement();
                        return false;
                    }
                }
                for (int i=0; i<bPieces.size(); i++){ //Capturing section
                    int pieceX = bPieces.get(i).getX();
                    int pieceY = bPieces.get(i).getY();

                    /* 
                    Do a similar check if there are any other black pieces in-between 
                    the movemnet, but don't count the square the capture is trying to take place
                    (no <= or >= signs, replaced with < & >)
                     */
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY < this.yMove || this.yPos > pieceY && pieceY > this.yMove && bPieces.get(i).getAliveDead() == true) ){
                        this.inValidMovement();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX < this.xMove || this.xPos > pieceX && pieceX > this.xMove && bPieces.get(i).getAliveDead() == true) ){
                        this.inValidMovement();
                        return false;
                    }
                }

                // Finally, if no black or white pieces are in the way of the movement, check if there is an enemy piece in the way
                for (int j=0; j<bPieces.size(); j++){
                    int pieceX = bPieces.get(j).getX();
                    int pieceY = bPieces.get(j).getY();
                    if (pieceX == this.xMove && pieceY == this.yMove && bPieces.get(j).getAliveDead() == true){
                        captureEnemy(bPieces.get(j).getX(), bPieces.get(j).getY(), bPieces.get(j).getImage(), bPieces.get(j).getType(), bPieces.get(j).getTeam());
                        bPieces.get(j).setIsAlive(false);
                    }
                }

                return true;
            } else if (this.pieceTeam == "Black"){
                for (int i=0; i<bPieces.size(); i++){ // Movement section
                    int pieceX = bPieces.get(i).getX();
                    int pieceY = bPieces.get(i).getY();
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove || this.yPos > pieceY && pieceY >= this.yMove && bPieces.get(i).getAliveDead() == true) ){
                        this.inValidMovement();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove || this.xPos > pieceX && pieceX >= this.xMove) && bPieces.get(i).getAliveDead() == true ){
                        this.inValidMovement();
                        return false;
                    }
                }
                for (int i=0; i<wPieces.size(); i++){ // Capturing section
                    int pieceX = wPieces.get(i).getX();
                    int pieceY = wPieces.get(i).getY();

                    /* 
                    Do a similar check if there are any other black pieces in-between 
                    the movemnet, but don't count the square the capture is trying to take place
                    (no <= or >= signs, replaced with < & >)
                     */
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY < this.yMove || this.yPos > pieceY && pieceY > this.yMove) && wPieces.get(i).getAliveDead() == true ){
                        this.inValidMovement();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX < this.xMove || this.xPos > pieceX && pieceX > this.xMove) && wPieces.get(i).getAliveDead() == true ){
                        this.inValidMovement();
                        return false;
                    }
                }

                // Finally, if no black or white pieces are in the way of the movement, check if there is an enemy piece in the way
                for (int j=0; j<wPieces.size(); j++){
                    int pieceX = wPieces.get(j).getX();
                    int pieceY = wPieces.get(j).getY();
                    if (pieceX == this.xMove && pieceY == this.yMove && wPieces.get(j).getAliveDead() == true){
                        captureEnemy(wPieces.get(j).getX(), wPieces.get(j).getY(), wPieces.get(j).getImage(), wPieces.get(j).getType(), wPieces.get(j).getTeam());
                        wPieces.get(j).setIsAlive(false);
                    }
                }
                return true;
            }
        }

        this.inValidMovement();
        return false;
    }
}