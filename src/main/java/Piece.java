
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class Piece extends chessController{

    VBox referenceGrid;
    ImageView myImage;
    int yPos;
    int xPos;
    int xMove; 
    int yMove;
    String pieceTeam;
    String pieceType;
    
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

    /**
     * 
     */
    public void drawPiece(){
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(yPos)) ).getChildren().get(xPos)).getChildren().add(myImage);
    }

    /**
     * 
     */
    public void pieceClicked(){
        System.out.println(pieceChosen);
        
        if (pieceChosen == false){
            if (pieceTeam == "White"){
                pieceChosen = true;
                this.highlightPiece();
                this.givePos();
            }
            else if (pieceTeam == "Black"){
                pieceChosen = true;
                this.highlightPiece();
                this.givePos();
            }
        }
    }

    //Temp method:
    public void givePos(){
        System.out.println();
        System.out.print(xPos + ", " + yPos);
        System.out.println();
    }

    /**
     * 
     */
    private void highlightPiece(){
        if (pieceType == "Pawn")
            myImage = new ImageView("Images/H_Pawn.png");
        else if (pieceType == "King")
            myImage = new ImageView("Images/H_King.png");
        else if (pieceType == "Queen")
            myImage = new ImageView("Images/H_Queen.png");
        else if (pieceType == "Bishop")
            myImage = new ImageView("Images/H_Bishop.png");
        else if (pieceType == "Knight")
            myImage = new ImageView("Images/H_Knight.png");
        else if (pieceType == "Rook")
            myImage = new ImageView("Images/H_Rook.png");
        this.correctImage();
        this.drawPiece();
    }

    /**
     * 
     */
    public void correctImage(){
        myImage.setFitHeight(70);
        myImage.setFitHeight(70);
        myImage.setPreserveRatio(true);
        myImage.setOnMouseClicked(e -> pieceClicked());
    }
}

