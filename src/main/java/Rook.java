import javafx.scene.image.ImageView;

public class Rook extends Piece {

    public Rook(int inputX, int inputY, ImageView inputRect){
        xPos = inputX;
        yPos = inputY;
        myImage = inputRect;
    }


    @Override
    boolean moveValid() {

        if (super.horizontalVertical() == true){
            return true;
        }
        return false;
    }

    public void givePos(){
        System.out.println(xPos);
        System.out.println(yPos);
    }
}