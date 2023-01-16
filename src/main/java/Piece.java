import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Comparator;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public abstract class Piece extends chessController {

    public static ArrayList<ImageView> wTaken = new ArrayList<>();
    public static ArrayList<ImageView> bTaken = new ArrayList<>();
    protected HBox score1;
    protected HBox score2;
    protected StackPane player1;
    protected StackPane player2;
    protected VBox referenceGrid;
    protected ImageView myImage;
    protected int yPos;
    protected int xPos;
    protected int xMove;
    protected int yMove;
    protected String pieceTeam;
    protected String pieceType;
    protected boolean isSelected;

    /**
     * moveValid - An abstact boolean used as a template for subclasses to validate their
     * movements 
     * 
     * @param xPos  - The x-coordinate of piece selected
     * @param yPos  - The y-coordinate of piece selected
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public abstract boolean moveValid();

    /**
     * horizontalVertical - A boolean that's used to check if the movement is in a stright line
     * on not. Therefore, it would return true if the movement was straight, but false if the
     * movement was diagonal or something else.
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
     * diagonal - A boolean that's used to check if the movement is in a diagonal line
     * on not. Therefore, it would return true if the movement was diagonal, but false if the
     * movement was straight  or something else.
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
     * be higlighted. It is used for two main purposes: highlighting pieces, and
     * deselecting/unhighlighting other pieces if they is another that is selected. It
     * is currently designed to accomidate conditions for white and black pieces separately.
     */
    public void pieceClicked() {
        if (this.pieceTeam == "White"){ // White piece clicked section
            if (turnString == "White's Turn"){
                if (pieceChosen == false){
                    pieceChosen = true;
                    this.isSelected = true;
                    this.highlightPiece();
                } else {
                    for (int i=0; i<wPieces.size(); i++){
                        Piece tempPiece = wPieces.get(i);
                        if (tempPiece.getHightlight()){
                            pieceChosen = false;
                            tempPiece.removeMyImage();
                            tempPiece.setHighlight(false);
                            tempPiece.unhighlightPiece();
                            tempPiece.setMovements(0, 0);
                        }
                    }
                }
            } else if (turnString == "Black's Turn" && pieceChosen == true){
                for (int i=0; i<bPieces.size(); i++){ // Similar for loop section to one inside of chessController!
                    if (bPieces.get(i).getHightlight()){
                        bPieces.get(i).setMovements(this.xPos, this.yPos);
                        if (bPieces.get(i).moveValid()){
                            bPieces.get(i).move();
                            turnString = "White's Turn";
                            turnBanner.setText("White Team's Turn!");
                            turnBanner.setTextFill(Color.WHITE);
                        }
                    } 
                }
            }
        } else if (this.pieceTeam == "Black"){ // Black piece clicked section
            if (turnString == "White's Turn" && pieceChosen == true){
                for (int i=0; i<wPieces.size(); i++){ // Similar for loop section to one inside of chessController!
                    if (wPieces.get(i).getHightlight()){
                        wPieces.get(i).setMovements(this.xPos, this.yPos);
                        if (wPieces.get(i).moveValid()){
                            wPieces.get(i).move();
                            turnString = "Black's Turn";
                            turnBanner.setText("Black Team's Turn!");
                            turnBanner.setTextFill(Color.BLACK);
                        }
                    } 
                }
            } else if (turnString == "Black's Turn"){
                if (pieceChosen == false){
                    pieceChosen = true;
                    this.isSelected = true;
                    this.highlightPiece();
                } else {
                    for (int i=0; i<bPieces.size(); i++){
                        Piece tempPiece = bPieces.get(i);
                        if (tempPiece.getHightlight()){
                            pieceChosen = false;
                            tempPiece.removeMyImage();
                            tempPiece.setHighlight(false);
                            tempPiece.unhighlightPiece();
                            tempPiece.setMovements(0, 0);
                        }
                    }
                }
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
     * correctImage - method that resizes this piece's image and
     * sets its to have a mouse click event.
     */
    protected void correctImage() {
        this.myImage.setFitHeight(tileSize);
        this.myImage.setFitHeight(tileSize);
        this.myImage.setPreserveRatio(true);
        this.myImage.setOnMouseClicked(e -> pieceClicked());
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
     * setHighlight - Sets the current isSelected boolean to
     * whatever the input was.
     * 
     * @param inputBoolean the boolean to change isSelected to
     */
    public void setHighlight(boolean inputBoolean){
        this.isSelected = inputBoolean;
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
     * captureEnemy - Removes specified enemy at enemyIndex in it's respective piece list. Also adds
     * the score image via the score method of the specified enemy that is being captured. It additionally
     * removes the enemy's image as well as display the winner/activate win screen should the captured piece
     * be a king piece.
     * 
     * @param enemy the enemy piece that was confirmed to be captured
     * @param enemyIndex the index of the enemy found in respect to the piece list with it
     */
    protected void captureEnemy(Piece enemy, int enemyIndex) {

        if (enemy.equals(bPieces.get(enemyIndex)))
            bPieces.remove(enemy);
        else if (enemy.equals(wPieces.get(enemyIndex)))
            wPieces.remove(enemy);

        this.addScore(enemy.getType());
        ((StackPane) (((HBox) referenceGrid.getChildren().get(enemy.getY()))).getChildren().get(enemy.getX())).getChildren().remove(enemy.getImage());

        if (enemy.getType() == "King"){
            if (enemy.getTeam() == "White"){
                displayWinner("Black"); // Black team wins the game (white's king is captured)
            } else if (enemy.getTeam() == "Black"){
                displayWinner("White"); // White team wins the game (black's king is captured)
            }
        }
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
     * addScore - A method that adds the taken pieces images next to the name tag
     * 
     * @param enemyType A string of of type of piece that was taken out
     */
    private void addScore(String enemyType) {
        ImageView deadImage;
        
        if (this.pieceTeam == "White"){
            deadImage = new ImageView("Images/B_" + enemyType + ".png");
            deadImage.setId(enemyType);
            deadImage.setFitHeight(25);
            deadImage.setFitWidth(25);
            deadImage.setTranslateX(0);
            score1.getChildren().add(deadImage);
            
        } else if (this.pieceTeam == "Black"){
            deadImage = new ImageView("Images/W_" + enemyType + ".png");
            deadImage.setId(enemyType);
            deadImage.setFitHeight(25);
            deadImage.setFitWidth(25);
            deadImage.setTranslateX(0);
            deadImage.setTranslateY(-6);
            score2.getChildren().add(deadImage);
            
        }
    }

    /**
     * checkRookMoves - Method that returns a true or false statement regarding a rook movement pattern and this piece.
     * It requires specified "friendly" and "enemy" teams to evaluate any possible incorrect patterns in the movement
     * (it returns true by default, however it must not return false after all the checking/detections). To do this, it
     * first checks that the move does not infringe/go through or even land on a friendly piece. Then it moves onto detecting
     * if a capture should take place or not, canceling the move if an enemy piece is blocking the way of the requested
     * movement from xMove & yMove of this specific piece.
     * 
     * @param myTeam A list of pieces representing this piece's team
     * @param enemyTeam Another list of pieces that represent the enemy team
     * @return A true or false statement on if the requested movement is valid
     */
    protected boolean checkRookMoves(ArrayList<Piece> myTeam, ArrayList<Piece> enemyTeam){
        if (this.horizontalVertical()){ // Was the movment a horizontal or vertical movement! (in the rook pattern)
            for (int i=0; i<myTeam.size(); i++){ //Movement cancellation section (for same-team pieces)
                int pieceX = myTeam.get(i).getX();
                int pieceY = myTeam.get(i).getY();
                if (pieceX == this.xPos && (this.yPos < pieceY && pieceY <= this.yMove
                || this.yPos > pieceY && pieceY >= this.yMove) ){
                    this.inValidMovement();
                    return false;
                } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX <= this.xMove
                || this.xPos > pieceX && pieceX >= this.xMove) ){
                    this.inValidMovement();
                    return false;
                }
            }
            for (int i=0; i<enemyTeam.size(); i++){ //Capturing section
                int pieceX = enemyTeam.get(i).getX();
                int pieceY = enemyTeam.get(i).getY();

                /* 
                Do a similar check if there are any other black pieces in-between 
                the movemnet, but don't count the square the capture is trying to take place
                (no <= or >= signs, replaced with < & >)
                */
                if (pieceX == this.xPos && (this.yPos < pieceY && pieceY < this.yMove
                || this.yPos > pieceY && pieceY > this.yMove) ){
                    this.inValidMovement();
                    return false;
                } else if (pieceY == this.yPos && (this.xPos < pieceX && pieceX < this.xMove 
                || this.xPos > pieceX && pieceX > this.xMove) ){
                    this.inValidMovement();
                    return false;
                }
            }

            // Finally, if no black or white pieces are in the way of the movement, check if there is an enemy piece in the way
            for (int i=0; i<enemyTeam.size(); i++){
                Piece tempPiece = enemyTeam.get(i);
                if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove){
                    captureEnemy(tempPiece, i);
                }
            }
            return true;
        }
        this.inValidMovement();
        return false;
    }

    /** THIS METHOD IS ONLY USED BY KING & KNIGHT! 
     * 
     * moveCancelCheck - Method specific to knight, it simply returns the proper movement
     * if a valid move was already detected (checks if it should capture, captures if so, also
     * stops a movement if a piece of the same team is in the way).
     * 
     * @param myTeam list of pieces on the same team as this piece
     * @param enemyTeam list of pieces on the enemy team as this piece
     */
    protected boolean moveCancelCheck(ArrayList<Piece> myTeam, ArrayList<Piece> enemyTeam){
        for (int i=0; i<myTeam.size(); i++){ // Check if any piece on this piece's team is in the way
            Piece tempPiece = myTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove){
                this.inValidMovement();
                return false;
            }
        }
        for (int i=0; i<enemyTeam.size(); i++){ // Check if this piece can capture, capture if so (enemy piece in movment space)
            Piece tempPiece = enemyTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove){
                captureEnemy(tempPiece, i);
                return true;
            }
        }

        return true; // Return true if no invalid move was found, or no capture was found (no capture code here)
    }

    /**
     * conflictedMovement - Boolean return method that returns true if the specified team
     * has any piece that is alive, and has its x & y in the way of a current movement by a piece
     * 
     * @param checkTeam the team to be checked for a confliction in the movement
     * @return a true or false statement depending on if a piece in the list is in the way of the currently requested movement
     */
    protected boolean conflictedMovement(ArrayList<Piece> checkTeam){
        for (int i=0; i<checkTeam.size(); i++){
            Piece tempPiece = checkTeam.get(i);
            if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove){
                return true;
            }
        }
        return false;
    }

    /**
     * checkBishopMoves - A method that returns a true or false statement through the bishop pattern.
     * It use is to figure out if the move is valid or not and through the use of 'myTeam' & 'enemyTeam' 
     * it can evaluate if the move can capture or not. Not only that though, it uses my 'myTeam' & 'enemyTeam'
     * to check if there are any pieces in the way of it's movement (bishop pattern / diagonal) through the use
     * of lots of if statements & for loops. If checkBishopMoves returns true, that means that the piece can 
     * capture if it's trying to capture or there's nothing in the way the of the piece chosen moving to it's 
     * chosen tile therefore making the move valid. If checkBishopMoves returns false, that means that there's
     * a piece in the way of the move or the piece that your trying to capture is on the same team as the chosen 
     * piece therefore making it an invalid move.
     * 
     * @param myTeam A list of pieces representing this piece's team
     * @param enemyTeam Another list of pieces that represent the enemy team
     * @return A true or false statement on if the requested movement is valid
     */
    protected Boolean checkBishopMoves(ArrayList<Piece> myTeam, ArrayList<Piece> enemyTeam){ // Finished!!!
        for (int i=0; i<myTeam.size(); i++){ 
            int pieceX = myTeam.get(i).getX();
            int pieceY = myTeam.get(i).getY();
            
            if (diagonal()){
                for (int k=1; k<8; k++){ 
                    if (this.yPos > this.yMove){ // If it's going up on the board
                        if (this.xPos < this.xMove && this.xMove > pieceX){
                            if (pieceX - k == this.xPos && pieceY + k == this.yPos){ // If it's going right on the board
                                return false;    
                            }
                        }
                        if (this.xPos > this.xMove && this.xMove < pieceX){
                            if (pieceX + k == this.xPos && pieceY + k == this.yPos){ // If it's going left on the board
                                return false;
                            }
                        }
                    }
                    
                    if (this.yPos < this.yMove){ // If it's going down on the board
                        if (this.xPos < this.xMove && this.xMove > pieceX){
                            if (pieceX - k == this.xPos && pieceY - k == this.yPos){ // If it's going right on the board
                            return false;
                            }
                    }
                        if (this.xPos > this.xMove && this.xMove < pieceX){
                            if (pieceX + k == this.xPos && pieceY - k == this.yPos){ // If it's going left on the board
                                return false;
                            }
                        }
                    }
                }
            }
        }

        for (int j=0; j<enemyTeam.size(); j++){ 
            int pieceX = enemyTeam.get(j).getX();
            int pieceY = enemyTeam.get(j).getY();
            Piece tempPiece = enemyTeam.get(j);

            if (diagonal()){
                
                for (int k=1; k<8; k++){
                    if (this.yPos > this.yMove){ // If it's going up on the board
                        if (this.xPos < this.xMove && this.xMove > pieceX){
                            if (pieceX - k == this.xPos && pieceY + k == this.yPos){ // If it's going right on the board
                                return false;    
                            }
                        }
                        if (this.xPos > this.xMove && this.xMove < pieceX){
                            if (pieceX + k == this.xPos && pieceY + k == this.yPos){ // If it's going left on the board
                                return false;
                            }
                        }
                    }
                    
                    if (this.yPos < this.yMove){ // If it's going down on the board
                        if (this.xPos < this.xMove && this.xMove > pieceX){
                            if (pieceX - k == this.xPos && pieceY - k == this.yPos){ // If it's going right on the board
                            return false;
                            }
                    }
                        if (this.xPos > this.xMove && this.xMove < pieceX){
                            if (pieceX + k == this.xPos && pieceY - k == this.yPos){ // If it's going left on the board
                                return false;
                            }
                        }
                    }
                }
                
                    if (tempPiece.getX() == this.xMove && tempPiece.getY() == this.yMove){
                        captureEnemy(tempPiece, j);
                        return true;
                }
            }
        }
        return true;
    }
}
