import javafx.scene.image.ImageView;


public class Bishop extends Piece {

    public Bishop(int inputX, int inputY, ImageView inputRect){
        xPos = inputX;
        yPos = inputY;
        myImage = inputRect;
    }


    @Override
    boolean moveValid() {

        if (super.diagonal() == true){
            return true;
        }
        return false;
    }

    public void givePos(){
        System.out.println(xPos);
        System.out.println(yPos);
    }
}