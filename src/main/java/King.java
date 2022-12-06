
public class King extends Piece {

    @Override
    boolean moveValid() {

        if ((super.horizontalVertical() == true) || 
            (super.diagonal() == true)) {
            if (((xMove - xPos) == 1) && (yMove - yPos) == 1){
                return true;
            }
            if ((xMove - xPos) == 1 && (yMove == yPos)){
                return true;
            }
            if ((xMove == xPos) && (yMove - yPos) == 1){
                return true;
            }
        }
        return false;
    }

}