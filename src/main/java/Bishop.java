import java.util.ArrayList;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;


public class Bishop extends Piece {

    public Bishop(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid, AnchorPane player2, AnchorPane player1){
        this.xPos = inputX;
        this.yPos = inputY;
        this.myImage = inputImage;
        this.pieceTeam = inputTeam;
        this.referenceGrid = chessGrid;
        this.player2 = player2;
        this.player1 = player1;
        this.correctImage();
        this.pieceType = "Bishop";
        this.isAlive = true;
        this.isSelected = false;
        this.drawPiece();
    }


    @Override
    boolean moveValid() {
        pieceChosen = false;
        this.isSelected = false;
        if (diagonal() == true){
            if (pieceInTheWay()){
                this.inValidMovement();
                return false;
            }
            
            else if (pieceTeam == "White"){
                for (int i=0; i<bPieces.size(); i++){
                    int pieceX = bPieces.get(i).getX();
                    int pieceY = bPieces.get(i).getY();
                    if (pieceX == this.xMove && pieceY == this.yMove && bPieces.get(i).getAliveDead() == true){
                        captureEnemy(bPieces.get(i).getX(), bPieces.get(i).getY(), bPieces.get(i).getImage(), bPieces.get(i).getType(), bPieces.get(i).getTeam());
                        bPieces.get(i).setIsAlive(false);
                        return true;
                    }
                }
            }
            else if (pieceTeam == "Black"){
                for (int i=0; i<wPieces.size(); i++){
                    int pieceX = wPieces.get(i).getX();
                    int pieceY = wPieces.get(i).getY();
                    if (pieceX == this.xMove && pieceY == this.yMove && wPieces.get(i).getAliveDead() == true){
                        captureEnemy(wPieces.get(i).getX(), wPieces.get(i).getY(), wPieces.get(i).getImage(), wPieces.get(i).getType(), wPieces.get(i).getTeam());
                        wPieces.get(i).setIsAlive(false);
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }
}