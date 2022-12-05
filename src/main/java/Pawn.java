
public class Pawn extends Piece {

    int firstMove = 0;

    @Override
    boolean moveValid(int xPos, int yPos, int xMove, int yMove) {

        if (firstMove == 0) {
            if (super.horizontalVertical(yMove, yMove, yMove, yMove) == true) {
                if (yPos == yMove) {
                    if ((xMove - xPos) == 1) {
                        firstMove = 1;
                        return true;
                    }
                }
            }
        } else if (super.horizontalVertical(yMove, yMove, yMove, yMove) == true) {
            if (yPos == yMove) {
                if ((xMove - xPos) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

}