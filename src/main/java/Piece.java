
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
    boolean isSelected;
    
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
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yPos)) ).getChildren().get(this.xPos)).getChildren().add(this.myImage);
    }

    /**
     * 
     */
    public void pieceClicked(){
        if (pieceChosen == false){
            if (this.pieceTeam == "White" && turnString == "White's Turn"){
                pieceChosen = true;
                this.isSelected = true;
                this.highlightPiece();
            }
            else if (this.pieceTeam == "Black" && turnString == "Black's Turn"){
                pieceChosen = true;
                this.isSelected = true;
                this.highlightPiece();
            }
        }
    }

    /**
     * 
     */
    private void highlightPiece(){
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yPos)) ).getChildren().get(this.xPos)).getChildren().remove(this.myImage);
        if (this.pieceType == "Pawn")
            this.myImage = new ImageView("Images/H_Pawn.png");
        else if (this.pieceType == "King")
            this.myImage = new ImageView("Images/H_King.png");
        else if (this.pieceType == "Queen")
            this.myImage = new ImageView("Images/H_Queen.png");
        else if (this.pieceType == "Bishop")
            this.myImage = new ImageView("Images/H_Bishop.png");
        else if (this.pieceType == "Knight")
            this.myImage = new ImageView("Images/H_Knight.png");
        else if (pieceType == "Rook")
            this.myImage = new ImageView("Images/H_Rook.png");
        this.correctImage();
        this.drawPiece();
    }

    protected void unhighlightPiece(){
        if (this.pieceType == "Pawn")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_Pawn.png");
        else if (this.pieceType == "King")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_King.png");
        else if (this.pieceType == "Queen")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_Queen.png");
        else if (this.pieceType == "Bishop")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_Bishop.png");
        else if (this.pieceType == "Knight")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_Knight.png");
        else if (this.pieceType == "Rook")
            this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_Rook.png");
        this.correctImage();
        this.drawPiece();
    }

    /**
     * 
     */
    protected void correctImage(){
        this.myImage.setFitHeight(tileSize);
        this.myImage.setFitHeight(tileSize);
        this.myImage.setPreserveRatio(true);
        this.myImage.setOnMouseClicked(e -> pieceClicked());
    }

    /**
     * getHighlight - Simple boolean return method that returns a
     * statement on wheter or not this piece is highlighted (returns 
     * isSelected variable for this specific piece)
     * 
     * @return true or false statement on if this piece is highlighted
     */
    public boolean getHightlight(){
        return this.isSelected;
    }

    /**
     * 
     * @param inputXMove
     * @param inputYMove
     */
    public void setMovements(int inputXMove, int inputYMove){
        this.xMove = inputXMove;
        this.yMove = inputYMove;
    }

    /**
     * 
     */
    public void move(){
        this.removeImageAtPos();
        this.xPos = this.xMove;
        this.yPos = this.yMove;
        // System.out.println(((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yMove)) ).getChildren().get(this.xMove)).getChildren());
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
    }

    protected void removeImageAtPos(){
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yPos)) ).getChildren().get(this.xPos)).getChildren().remove(this.myImage);
    }
    
    public void enemyPiece(){
        if (gridSpot[this.xMove][this.yMove].getChildren().size() == 2){
            if  (wPawns[yMove].pieceTeam.equals(this.pieceTeam))
                enemyPiece = true;
            enemyPiece = false;
        }
    }
}

