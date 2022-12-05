
public abstract class Piece {

    /**
     * moveValid - An abstact boolean used as a template for subclasses
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    abstract boolean moveValid(int xPos, int yPos, int xMove, int yMove);

    /**
     * horizontalVertical - A static
     * @param xPos - The x-coordinate of piece selected  
     * @param yPos - The y-coordinate of piece selected  
     * @param xMove - The x-coordinate of where the selected piece is being moved
     * @param yMove - The y-coordinate of where the selected piece is being moved
     * @return
     */
    public static boolean horizontalVertical(int xPos, int yPos, int xMove, int yMove){
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
    public static boolean diagonal(int xPos, int yPos, int xMove, int yMove){
        return (Math.abs(xMove - xPos) == Math.abs(yMove - yPos));
    }
}

