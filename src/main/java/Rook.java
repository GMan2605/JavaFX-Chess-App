import java.util.ArrayList;

import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.correctImage();
        this.pieceType = "Rook";
        this.isSelected = false;
        this.drawPiece();
    }


    @Override
    boolean moveValid(ArrayList<Piece> wPieces, ArrayList<Piece> bPieces) {
        pieceChosen = false;
        this.isSelected = false;

        if (this.horizontalVertical()){
            if (this.pieceTeam == "White"){
                for (int i=0; i<wPieces.size(); i++){
                    int pieceX = wPieces.get(i).getX();
                    int pieceY = wPieces.get(i).getY();
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove || this.yPos > pieceY && pieceY >= this.yMove) ){
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove || this.xPos > pieceX && pieceX >= this.xMove) ){
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    }
                }
                for (int i=0; i<bPieces.size(); i++){
                    int pieceX = bPieces.get(i).getX();
                    int pieceY = bPieces.get(i).getY();
                    
                }
                return true;
            } else if (this.pieceTeam == "Black"){
                for (int i=0; i<bPieces.size(); i++){
                    int pieceX = bPieces.get(i).getX();
                    int pieceY = bPieces.get(i).getY();
                    if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove || this.yPos > pieceY && pieceY >= this.yMove) ){
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove || this.xPos > pieceX && pieceX >= this.xMove) ){
                        this.removeMyImage();
                        this.xMove = 0;
                        this.yMove = 0;
                        this.unhighlightPiece();
                        return false;
                    }
                }
                for (int i=0; i<wPieces.size(); i++){
                    int pieceX = wPieces.get(i).getX();
                    int pieceY = wPieces.get(i).getY();
                    
                }
                return true;
            }
        }

        this.removeMyImage();
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
        return false;
    }
}