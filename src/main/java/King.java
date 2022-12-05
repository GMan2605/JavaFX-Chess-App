
public class King extends Piece {

    @Override
    boolean moveValid(int xPos, int yPos, int xMove, int yMove) {

        if ((super.horizontalVertical(yMove, yMove, yMove, yMove) == true) || 
            (super.diagonal(xPos, yPos, xMove, yMove) == true)) {
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