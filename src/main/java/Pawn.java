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
        this.isFirstMove = true;
        this.isSelected = false;
        this.drawPiece();
    }
    
    // Adjusted Movement Code (to be review over for directional movement of different pawn teams)
    @Override
    boolean moveValid(ArrayList<Piece> wPieces, ArrayList<Piece> bPieces) {
        pieceChosen = false;
        this.isSelected = false;

        // Basic 1 tile movment
        if (this.xPos == this.xMove && this.yPos+this.pDirection == this.yMove){
            for (int i=0; i<wPieces.size(); i++){
                if (wPieces.get(i).getX() == this.xMove && wPieces.get(i).getY() == this.yMove){
                    this.isFirstMove = false;
                    this.removeMyImage();
                    this.xMove = 0;
                    this.yMove = 0;
                    this.unhighlightPiece();
                    return false;
                }
            }
            for (int i=0; i<bPieces.size(); i++){
                if (bPieces.get(i).getX() == this.xMove && bPieces.get(i).getY() == this.yMove){
                    this.isFirstMove = false;
                    this.removeMyImage();
                    this.xMove = 0;
                    this.yMove = 0;
                    this.unhighlightPiece();
                    return false;
                }
            }
            this.isFirstMove = false;
            return true;
        } 
        // Double movement/"first move" code
        else if (this.isFirstMove == true){
            if (this.xPos == this.xMove && this.yPos+(2*this.pDirection) == this.yMove){
                for (int i=0; i<wPieces.size(); i++){
                    if (wPieces.get(i).getX() == this.xMove && wPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    }
                }
                for (int i=0; i<bPieces.size(); i++){
                    if (bPieces.get(i).getX() == this.xMove && bPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    
                    }
                }
                this.isFirstMove = false;
                return true;
            }
        }
        // Capturing of enemy pieces
        else if ( (this.xPos+1 == this.xMove || this.xPos-1 == this.xMove) && (this.yPos)+this.pDirection == this.yMove){
            if (this.pieceTeam == "Black"){
                for (int i=0; i<wPieces.size(); i++){
                    if (wPieces.get(i).getX() == this.xMove && wPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                        // wPieces.remove(i+1); //TODO: Change this, there might be issues here!
                        captureEnemy(wPieces.get(i).getX(), wPieces.get(i).getY(), wPieces.get(i).getImage(), wPieces.get(i).getType(), wPieces.get(i).getTeam());
                        return true;
                    }
                }
                for (int i=0; i<bPieces.size(); i++){
                    if (bPieces.get(i).getX() == this.xMove && bPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    }
                }
            } else if (this.pieceTeam == "White"){
                for (int i=0; i<bPieces.size(); i++){
                    if (bPieces.get(i).getX() == this.xMove && bPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                         // bPieces.remove(i+1); //TODO: Change this, there might be issues here!
                        captureEnemy(bPieces.get(i).getX(), bPieces.get(i).getY(), bPieces.get(i).getImage(), bPieces.get(i).getType(), bPieces.get(i).getTeam());
                        return true;
                    }
                }
                for (int i=0; i<wPieces.size(); i++){
                    if (wPieces.get(i).getX() == this.xMove && wPieces.get(i).getY() == this.yMove){
                        this.isFirstMove = false;
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    }
                }
            }
        }
        
        this.isFirstMove = false;
        this.removeMyImage();
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
        return false;
    }
}