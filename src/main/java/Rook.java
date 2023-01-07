import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputImage, String inputTeam, VBox chessGrid, HBox player2, HBox player1, Label inputBanner){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.turnBanner = inputBanner;
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


    @Override
    public boolean isCheck() {
        if (this.pieceTeam == "Black"){
            for (int i=0; i<wPieces.size(); i++){ 
                Piece tempPiece = wPieces.get(i);
                if (tempPiece.getType() == "King"){
                    this.xMove = tempPiece.getX();
                    this.yMove = tempPiece.getY();
                    if (checkRookMoves(bPieces, wPieces) == true){
                        return true;
                    }
                    }
                }
            }
        
        else{
            for (int i=0; i<bPieces.size(); i++){ 
                Piece tempPiece = bPieces.get(i);
                if (tempPiece.getType() == "King"){
                    this.xMove = tempPiece.getX();
                    this.yMove = tempPiece.getY();
                    if (checkRookMoves(wPieces, bPieces) == true){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}