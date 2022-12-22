
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class Piece extends chessController{

    @FXML
    AnchorPane player1;

    @FXML
    AnchorPane player2;

    VBox referenceGrid;
    ImageView myImage;
    int yPos;
    int xPos;
    int xMove; 
    int yMove;
    String pieceTeam;
    String pieceType;
    boolean isSelected;
    int pawnDead;
    

    
    /**
     * moveValid - An abstact boolean used as a template for subclasses
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    abstract boolean moveValid(ArrayList<Piece> wPieces, ArrayList<Piece> bPieces);

    /**
     * horizontalVertical - A static ?
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public boolean horizontalVertical(){
        return ((this.xPos == this.xMove) || (this.yPos == this.yMove));
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
        return (Math.abs(this.xMove - this.xPos) == Math.abs(this.yMove - this.yPos));
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
     * @return
     */
    public int getX(){
        return this.xPos;
    }

    /**
     * 
     * @return
     */
    public int getY(){
        return this.yPos;
    }

    /**
     * 
     */
    public ImageView getImage(){
        return this.myImage;
    }

    public String getType(){
        return this.pieceType;
    }

    public String getTeam(){
        return this.pieceTeam;
    }

    /**
     * 
     */
    public void move(){
        this.removeMyImage();
        this.xPos = this.xMove;
        this.yPos = this.yMove;
        // System.out.println(((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yMove)) ).getChildren().get(this.xMove)).getChildren());
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
    }

    protected void removeMyImage(){
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(this.yPos)) ).getChildren().get(this.xPos)).getChildren().remove(this.myImage);
    }

    protected void captureEnemy(int enemyX, int enemyY, ImageView enemyImage, String enemyType, String enemyTeam){
        this.addScore(enemyType);
        ((StackPane) (( (HBox)referenceGrid.getChildren().get(enemyY)) ).getChildren().get(enemyX)).getChildren().remove(enemyImage);

        
        
    }
    private void addScore(String enemyType){
        ImageView deadImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_" + enemyType + ".png");
        deadImage.setFitHeight(25);
        deadImage.setFitWidth(25);
        deadImage.setX(75);
        deadImage.setY(26);
        if (this.pieceTeam == "White")
            player1.getChildren().add(deadImage);
        if (this.pieceTeam == "Black")
            player2.getChildren().add(deadImage);
    }
}

