
/*
 * 1. Read the spec and understand the overall picture.
2a. Create the Piece class and implement its methods, starting from the
simple and working your way to the more complicated. Don't implement
move() yet. Use the validMove() method as you currently have it and finish
implementing it which you implement the move() method.
2b. Write JUnit tests for the Piece class for all methods except the
move() method.
3. Create Board.java so that when you type "java Board", it displays a
blank 8x8 board. You will be implementing parts of your board as you go
along.
4a. Implement the pieceAt(), place(), and remove() methods for the Board
class.
4b. JUnit test may be written to test these methods unless it is easier to
visually verify the correctness compared to writing a test. On the other
hand, if you need to click around the GUI checkerboard quite a bit in
order to check for correctness, then it is recommended that JUnit tests be
written instead. Don't forget the "test" option for the Board constructor,
which may be particularly handy for testing.
4c. Modify your Board constructor so that it now starts up with the
correct initial configuration of the board. You might want to utilize your
newly written methods.



*/
//TODO: Make validMoves to display POSSIBLE moves
public class Piece 
{
	private int positionX;
	private int positionY;
	private boolean isDark;
	private boolean isKing;
	private boolean moved;
	private boolean captured;
	
	/**
	 * Creates a piece with a specified x, y, color, and royalty. This is generally used for testing, as the royalty of the piece can be specialized.
	 * @param x The first index of the coordinates of the piece created.
	 * @param y The second index of the coordinates of the piece created.
	 * @param dark The side on which the piece will be on.
	 * @param king True if the piece is a king, false if not.
	 */
	public Piece(int x, int y, boolean dark, boolean king)//just for testing purposes
	{
		this.positionX = x;
		this.positionY = y;
		this.isDark = dark;
		this.isKing = king;
		this.moved = false;
		this.captured = false;
	}
	
	/**
	 * Creates a piece with a specified x, y, and color. The royalty is defaulted to uncrowned.
	 * @param x The first index of the coordinates of the piece created. 
	 * @param y The second index of the coordinates of the piece created.
	 * @param side The side on which the piece will be on.
	 * @param b The CheckerBoard that the piece is located on. 
	 */
	public Piece(int x, int y, boolean side)
	{
		this.positionX = x;
		this.positionY = y;
		this.isDark = side;
		this.isKing = false;
		this.moved = false;
		this.captured = false;
	}
	
	
	/**
	 * Returns the royalty of the piece. It will return true if the piece is crowned and false if the piece is not crowned.
	 * @return True if the piece is crowned, false if the piece is not crowned.
	 */
	public boolean isKing() 
	{
		return this.isKing;
	}
	
	/**
	 * Returns the color of the piece. It will return true if the piece is dark and false if the piece is not dark (light).
	 * @return True if the piece is dark, false if the piece is not dark.
	 */
	public boolean isDark()
	{
		return this.isDark;
	}
	
	/**
	 * Returns a boolean indicating whether or not the piece has captured another piece. 
	 * @return True if the piece has captured, false if not.
	 */
	public boolean hasCaptured()
	{
		return captured;
	}
	
	/**
	 * Returns the x position of the piece.
	 * @return The x position of the piece. 
	 */
	public int getX()
	{
		return positionX;
	}
	
	/**
	 * Returns the y position of the piece.
	 * @return The y position of the piece.
	 */
	public int getY()
	{
		return positionY;
	}
	
	/**
	 * Returns a boolean indication whether or not the piece has moved.
	 * @return True if the piece has moved, false if not.
	 */
	public boolean hasMoved()
	{
		return moved;
	}
	
	/**
	 * Denotes capture movement: changes the coordinates of the piece and sets moved and captured to true. 
	 * Note that this method is only a part of the true capture() method in CheckerBoard.
	 * @param destinationX The x coordinate of the piece's specified new position after capturing.
	 * @param destinationY The y coordinate of the piece's specified new position after capturing.
	 */
	public void captureMovement(int destinationX, int destinationY)
	{
		movement(destinationX, destinationY);
		captured = true;
	}
	/**
	 * Denotes regular movement: changes the coordinates of the piece and sets moved to true.
	 * @param destinationX The x coordinate of the piece's specified new position after moving.
	 * @param destinationY The y coordinate of the piece's specified new position after moving.
	 */
	public void movement(int destinationX, int destinationY)
	{
		if (destinationX > -1 && destinationX < CheckerBoard.BOARDSIZE && 
			destinationY > -1 && destinationY < CheckerBoard.BOARDSIZE)
		{
			positionX = destinationX;
			positionY = destinationY;
			moved = true;
		}
		
	}
	
	/**
	 * Sets the boolean denoting if the piece has moved or not.
	 * @param moved The new value denoting if the piece has moved or not.
	 */
	public void setMoved(boolean moved) 
	{
		this.moved = moved;		
	}
	
	/**
	 * Sets the boolean denoting if the piece has moved or not.
	 * @param captured The new value denoting if the piece has moved or not.
	 */
	public void setCaptured(boolean captured) 
	{
		this.captured = captured;		
	}
	
	
	
	
}
