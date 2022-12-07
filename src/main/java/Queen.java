import javafx.scene.shape.Rectangle;

public class Queen extends Piece {

    public Queen(int inputX, int inputY, Rectangle inputRect){
        xPos = inputX;
        yPos = inputY;
        myImage = inputRect;
    }

    @Override
    boolean moveValid() {

        if (super.horizontalVertical() == true){
            return true;
        }else if (super.diagonal() == true){
            return true;
        }
        return false;
    }

    public void givePos(){
        System.out.println(xPos);
        System.out.println(yPos);
    }
}