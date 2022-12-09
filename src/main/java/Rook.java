import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputImage, String inputTeam,  VBox chessGrid){
        xPos = inputX;
        yPos = inputY;
        myImage = inputImage;
        pieceTeam = inputTeam;
        referenceGrid = chessGrid;
        myImage.setFitHeight(70);
        myImage.setFitHeight(70);
        myImage.setPreserveRatio(true);

        myImage.setOnMouseClicked(e -> pieceClicked());
        this.drawPiece();
    }


    @Override
    boolean moveValid() {

        if (super.horizontalVertical() == true){
            return true;
        }
        return false;
    }

    public void givePos(){
        System.out.println();
        System.out.print(xPos + ", " + yPos);
        System.out.println();
    }
}