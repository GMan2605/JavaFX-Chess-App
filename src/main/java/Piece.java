import javafx.scene.image.ImageView;
import java.util.ArrayList;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public abstract class Piece extends chessController {

    // public static ArrayList<Piece> wPieces;
    // public static ArrayList<Piece> bPieces;

    AnchorPane player1;
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
    boolean isAlive; // May/may not be temp boolean (have to ask for a solution to a specific problem in removing objects)

    /**
     * moveValid - An abstact boolean used as a template for subclasses
     * 
     * @param xPos  - The x-coordinate of piece selected
     * @param yPos  - The y-coordinate of piece selected
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    abstract boolean moveValid();

    // Temp method/needed because of problem that cannot be resolved at the moment
    public void setIsAlive(boolean inputBoolean){
        this.isAlive = inputBoolean;
    }

    public boolean getAliveDead(){
        return this.isAlive;
    }

    /**
     * 
     * @return
     */
    public int getWPiecesSize(){
        return wPieces.size();
    }

    /**
     * 
     * @return
     */
    public int getBPiecesSize(){
        return bPieces.size();
    }

    /**
     * horizontalVertical - TODO: *NEEDS DESCRIPTION*
     * 
     * @param xPos  - The x-coordinate of piece selected
     * @param yPos  - The y-coordinate of piece selected
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public boolean horizontalVertical() {
        return ((this.xPos == this.xMove) || (this.yPos == this.yMove));
    }

    /**
     * diagonal - TODO: *NEEDS DESCRIPTION*
     * 
     * @param xPos  - The x-coordinate of piece selected
     * @param yPos  - The y-coordinate of piece selected
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public boolean diagonal() {
        return (Math.abs(this.xMove - this.xPos) == Math.abs(this.yMove - this.yPos));
    }

    /**
     * drawPiece - Inserts the image associated with this piece into the referenceGrid
     * (the chessGrid that is on the screen) onto the tile/stackpane with the indexes
     * equal to this piece's x & y values.
     */
    public void drawPiece() {
        ((StackPane) (((HBox) referenceGrid.getChildren().get(this.yPos))).getChildren().get(this.xPos)).getChildren().add(this.myImage);
    }

    /**
     * pieceClicked - Void method that detects when a piece has been clicked/chosen to
     * be higlighted. It will only highlight them and change the data for the movement phase
     * if it is the right team chosen on their turn.
     */
    public void pieceClicked() {
        if (pieceChosen == false) {
            if (this.pieceTeam == "White" && turnString == "White's Turn") {
                pieceChosen = true;
                this.isSelected = true;
                this.highlightPiece();
            } else if (this.pieceTeam == "Black" && turnString == "Black's Turn") {
                pieceChosen = true;
                this.isSelected = true;
                this.highlightPiece();
            }
        }
    }

    /**
     * highlightPiece - Removes the previous image of this piece, then searches through
     * the image folder to find the respective highlighted variant of this piece to replace
     * the image with, then uses other methods, "correctImage" & "drawPiece" to make the image
     * look right and the draw it on the board again.
     */
    private void highlightPiece() {
        ((StackPane) (((HBox) referenceGrid.getChildren().get(this.yPos))).getChildren().get(this.xPos)).getChildren().remove(this.myImage);
        this.myImage = new ImageView("Images/H_" + this.pieceType + ".png");
        this.correctImage();
        this.drawPiece();
    }

    /**
     * unhighlightPiece - Similar to highlightPiece method, but instead of finding
     * a highlighted variant of this piece, it finds the normal one by using
     * this piece's variables associated with team and type of piece. It then
     * also corrects and draws the new image.
     */
    protected void unhighlightPiece() {
        this.myImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_" + this.pieceType + ".png");
        this.correctImage();
        this.drawPiece();
    }

    /**
     * correctImage - method that resizes this piece's image 
     */
    protected void correctImage() {
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
    public boolean getHightlight() {
        return this.isSelected;
    }

    /**
     * setMovements - Sets the current xMove and yMove (the requested moves,
     * to be used/checked in moveValid() ) to the inputed parameters
     * 
     * @param inputXMove input int used to replace the current xMove
     * @param inputYMove input int used to replace the current yMove
     */
    public void setMovements(int inputXMove, int inputYMove) {
        this.xMove = inputXMove;
        this.yMove = inputYMove;
    }

    /**
     * getX - Returns an int of the x position
     * 
     * @return the xPos variable (x position) of this piece
     */
    public int getX() {
        return this.xPos;
    }

    /**
     * getY - Returns an int of the y position
     * 
     * @return the yPos variable (y position) of this piece
     */
    public int getY() {
        return this.yPos;
    }

    /**
     * getImage - Returns an ImageView/the piece's image
     * 
     * @return the ImageView of this specific piece's image that is on the screen
     */
    public ImageView getImage() {
        return this.myImage;
    }

    /**
     * getType - Gets and returns a string related to the type of piece
     * 
     * @return a string arguement of the piece type (eg. "Pawn", "King")
     */
    public String getType() {
        return this.pieceType;
    }

    /**
     * getTeam - Returns a string of the piece's team (eg. "White" or "Black")
     * 
     * @return a string regarding which team this specific piece is a part of
     */
    public String getTeam() {
        return this.pieceTeam;
    }

    /**
     * move - Method that removes the previous image of a piece
     * when a move is valid (confirmed by moveValid()), then sets
     * the positions to the requested moves, then resets the moves &
     * finally adds an unhighlighted image to the new position
     */
    public void move() {
        this.removeMyImage();
        this.xPos = this.xMove;
        this.yPos = this.yMove;
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
    }

    /**
     * removeMyImage - Void method that removes the current image at
     * the current x & y position on the board
     */
    protected void removeMyImage() {
        ((StackPane) (((HBox) referenceGrid.getChildren().get(this.yPos))).getChildren().get(this.xPos)).getChildren().remove(this.myImage);
    }

    /**
     * captureEnemy - TODO: *NEEDS DESCRIPTION*
     * 
     * @param enemyX
     * @param enemyY
     * @param enemyImage
     * @param enemyType
     * @param enemyTeam
     */
    protected void captureEnemy(int enemyX, int enemyY, ImageView enemyImage, String enemyType, String enemyTeam) {
        this.addScore(enemyType);
        ((StackPane) (((HBox) referenceGrid.getChildren().get(enemyY))).getChildren().get(enemyX)).getChildren().remove(enemyImage);
    }

    /**
     * inValidMovement - A set of reset code that is used when a 
     * movement is deemed invalid. It removes the current image,
     * then it resets the movements to 0, 0 (any evaluations of
     * movements will be rendered useless, helps with bugs). Then
     * it finally unhighlights the piece using the unHighlight
     * method.
     */
    protected void inValidMovement(){
        this.removeMyImage();
        this.xMove = 0;
        this.yMove = 0;
        this.unhighlightPiece();
    }

    /**
     * addScore - TODO: *NEEDS DESCRIPTION*
     * 
     * @param enemyType
     */
    private void addScore(String enemyType) {
        ImageView deadImage = new ImageView("Images/" + this.pieceTeam.charAt(0) + "_" + enemyType + ".png");
        deadImage.setFitHeight(25);
        deadImage.setFitWidth(25);
        deadImage.setX(250);
        deadImage.setY(36);
        if (this.pieceTeam == "White")
            player1.getChildren().add(deadImage);
        if (this.pieceTeam == "Black")
            player2.getChildren().add(deadImage);
    }
    
    /**
     * pieceInTheWay - A boolean that reads true if there's 
     * another piece in between of the chosen piece's position
     * and position of move
     * 
     */
    protected Boolean pieceInTheWay(){ // DO NOT TOUCH GAVIN!!!
        if(pieceTeam == "White" || pieceTeam == "Black"){
            for (int i=0; i<wPieces.size(); i++){ 
                int pieceX = wPieces.get(i).getX();
                int pieceY = wPieces.get(i).getY();
                System.out.println(pieceX);
                System.out.println(pieceY);
                System.out.println(this.xPos);
                System.out.println(this.yPos);

                if (wPieces.get(i).getAliveDead()){ // Checks if the piece is alive or not
                    if(diagonal()){

                        if (this.yPos > this.yMove){ // If it's going up on the board
                            if (pieceX > this.xPos && pieceX <= this.xMove && (pieceY < this.yPos && pieceY >= this.yMove)){ // If it's going right on the board
                                return true;
                            }
                            else if (pieceX < this.xPos && pieceX >= this.xMove && (pieceY < this.yPos && pieceY >= this.yMove)){ // If it's going left on the board
                                return true;
                            }
                        }
                        if (this.yPos < this.yMove){ // If it's going down on the board
                            if (pieceX > this.xPos && pieceX <= this.xMove && (pieceY > this.yPos && pieceY <= this.yMove)){ // If it's going right on the board 
                                return true;
                            }
                            else if (pieceX < this.xPos && pieceX >= this.xMove && (pieceY > this.yPos && pieceY <= this.yMove)){ // If it's going left on the board
                                return true;
                            }
                        }
                    }
                }
            }
        }
        if(pieceTeam == "Black" || pieceTeam == "White"){
            for (int j=0; j<bPieces.size(); j++){ 
                int pieceX = bPieces.get(j).getX();
                int pieceY = bPieces.get(j).getY();

                if (bPieces.get(j).getAliveDead()){ // Checks if the piece is alive or not
                    if(diagonal()){
                        if (this.yPos < this.yMove){
                            if (pieceX > this.xPos && pieceX <= this.xMove && (pieceY < this.yPos && pieceY >= this.yMove)){ // If it's going right on the board
                                return true;
                            }
                            else if (pieceX < this.xPos && pieceX >= this.xMove && (pieceY < this.yPos && pieceY >= this.yMove)){ // If it's going left on the board
                                return true;
                            }
                        }
                        if (this.yPos > this.yMove){
                            if (pieceX > this.xPos && pieceX <= this.xMove && (pieceY > this.yPos && pieceY <= this.yMove)){ // If it's going right on the board
                                return true;
                            }
                            else if (pieceX < this.xPos && pieceX >= this.xMove && (pieceY > this.yPos && pieceY <= this.yMove)){ // If it's going left on the board
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
        }
}
