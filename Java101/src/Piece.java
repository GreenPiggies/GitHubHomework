/**
 * Constructs a Piece object. The Piece object contains a position in Cartesian coordinates, a color, a royalty, and two states: moved and captured.
 * The Cartesian coordinates represent the piece's location on a corresponding CheckerBoard's board.
 * The color is represented as a boolean, with true indicating its allegiance to the player controlling the dark pieces, and false indicating its allegiance to the player controlling the light pieces.
 * The royalty is represented as a boolean, with true indicating that the piece is crowned, and false indicating that the piece is not crowned.
 * The boolean moved indicates whether or not the piece has moved; it is true if the piece has moved and false if it has not.
 * The boolean captured indicates whether or not the piece has captured; it is true if the piece has captured and false if it has not.
 * 
 * 
 * 
 * @author hungw
 *
 */
public class Piece 
{
	private int positionX;
	private int positionY;
	private boolean isDark;
	private boolean isKing;
	private boolean moved;
	private boolean captured;
	
	/**
	 * Creates a piece with a given x position, y position, color, and royalty.
	 * @param x The x position of the given location.
	 * @param y The y position of the given location.
	 * @param dark The given side of the piece; this will be true if the piece is dark, and false if the piece is light.
	 * @param king The given royalty of the piece; this will be true if the piece is crowned, and false if the piece is not.
	 */
	public Piece(int x, int y, boolean dark, boolean king)
	{
		this.positionX = x;
		this.positionY = y;
		this.isDark = dark;
		this.isKing = king;
		this.moved = false;
		this.captured = false;
	}
	
	/**
	 * Creates a piece with a given x, y, and color. The royalty is defaulted to uncrowned.
	 * @param xPosition The x position of the given location.
	 * @param yPosition The x position of the given location.
	 * @param dark The given side of the piece; this will be true if the piece is dark, and false if the piece is light.
	 */
	public Piece(int xPosition, int yPosition, boolean dark)
	{
		this.positionX = xPosition;
		this.positionY = yPosition;
		this.isDark = dark;
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
	 * Denotes capture movement: changes the coordinates of the piece and marks the piece as having moved and captured.
	 * Note that this method is only a part of the true capture() method in CheckerBoard.
	 * @param destinationX The x coordinate of the piece's given new position after capturing.
	 * @param destinationY The y coordinate of the piece's given new position after capturing.
	 */
	public void pieceCapture(int destinationX, int destinationY)
	{
		pieceMove(destinationX, destinationY);
		captured = true;
	}
	/**
	 * Denotes regular movement: changes the coordinates of the piece and marks the piece as having moved and captured.
	 * @param destinationX The x coordinate of the piece's given new position after moving.
	 * @param destinationY The y coordinate of the piece's given new position after moving.
	 */
	public void pieceMove(int destinationX, int destinationY)
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
