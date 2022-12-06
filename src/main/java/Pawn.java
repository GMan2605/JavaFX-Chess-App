import javafx.scene.shape.Rectangle;

public class Pawn extends Piece {

    public Pawn(int inputX, int inputY, Rectangle inputRect){
        xPos = inputX;
        yPos = inputY;
        myImage = inputRect;
    }

    int firstMove = 0;

    @Override
    boolean moveValid() {

        if (firstMove == 0) {
            if (super.horizontalVertical() == true) {
                if (xPos == xMove) {
                    if ((yMove - yPos) <= 2) {
                        firstMove = 1;
                        return true;
                    }
                }
            }
        } else if (super.horizontalVertical() == true) {
            if (xPos == xMove) {
                if ((yMove - yPos) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public void givePos(){
        System.out.println(xPos);
        System.out.println(yPos);
    }
}