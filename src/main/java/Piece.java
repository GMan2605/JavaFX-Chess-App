
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class Piece {

    VBox referenceGrid;
    ImageView myImage;
    int yPos;
    int xPos;
    int xMove; 
    int yMove;
    String pieceTeam;
    
    /**
     * moveValid - An abstact boolean used as a template for subclasses
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    abstract boolean moveValid();

    /**
     * horizontalVertical - A static ?
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public boolean horizontalVertical(){
        return ((xPos == xMove) || (yPos == yMove));
    }

    /**
     * 
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public boolean diagonal(){
        return (Math.abs(xMove - xPos) == Math.abs(yMove - yPos));
    }

    public void drawPiece(){
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(yPos)) ).getChildren().get(xPos)).getChildren().add(myImage);
    }

    public void pieceClicked(){
        if (pieceTeam == "White")
            this.givePos();
        else if (pieceTeam == "Black")
            this.givePos();
        System.out.println(pieceTeam);
    }

    public void givePos(){
        System.out.println();
        System.out.print(xPos + ", " + yPos);
        System.out.println();
    }
}

