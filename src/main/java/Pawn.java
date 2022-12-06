
public class Pawn extends Piece {

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
}