import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
/*
 * The purpose of the CheckerBoard object is to represent a checker board and allow the user to play a game of checkers with it.
 * 
 * A CheckerBoard object contains a two-dimensional array of Piece objects, a boolean indicating the player turn, and a selected Piece object. There is also a constant BOARD_LENGTH that is used to determine the size of the board.
 * The two-dimensional array of Piece objects represents the checker board and stores the values of the pieces of the checker board in their respective locations.
 * The boolean indicating player turn is true if the player controlling the dark pieces is currently having their turn.
 * The selected Piece object stores the piece at the location currently selected by the player. If the player has not yet selected a piece, the variable holds null.
 * 
 * To play the game, run the main method of this class. 
 * This main method repeatedly checks for mouse clicks (selections) and space bar presses (turn switching) and handles these inputs accordingly if they are valid or not (selects a location if possible, ends turn if possible).
 * Also, it repeatedly checks the board to determine if there is a winner.
 * 
 */
public class CheckerBoard
{
	private Piece[][] board;
	public static final int BOARD_LENGTH = 8;
	private boolean darkTurn; 
	private Piece selectedPiece;
	
	/**
	 * Creates a CheckerBoard object.
	 * This initializes the two dimensional array that holds the information of the checker board. It creates a normal checker board with pieces on the gray squares for the top three and bottom three rows. 
	 * Also, it sets the current turn (in this case, it defaults to dark starting first) and initializes the selected piece to null. 
	 */
	public CheckerBoard()
	{
		this(false);
	}
	/**
	 * Creates a CheckerBoard object. 
	 * This initializes the two dimensional array that holds the information of the checker board. If test is false, it creates a normal checker board with pieces on the gray squares for the top three and bottom three rows. If test is true, it creates a checker board with no pieces on it. 
	 * Also, it sets the current turn (in this case, it defaults to dark starting first) and initializes the selected piece to null.
	 * @param test If this is false, the checker board will be set with pieces on the black tiles for the top three and bottom three rows (classic checker board setup). If this is true, the checker board starts off empty.
	 */
	public CheckerBoard(boolean test)
	{	
		board = new Piece[BOARD_LENGTH][BOARD_LENGTH];
		setScale();
		darkTurn = true;
		selectedPiece = null;
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				board[row][column] = null;
				drawSquare(row, column);
			}
		}
		if (!test)
		{
			setUpBoard();
		}
	}
		
	/**
	 * Sets the scale of the graphical checker board. 
	 */
	public void setScale() 
	{
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
	}
	
	/**
	 * Positions the pieces for the start of the game. 
	 * This includes placing dark pieces on the gray squares in the top three rows, as well as placing light pieces on the gray squares in the bottom three rows.
	 * The pieces are also drawn on the graphical checker board.
	 */
	public void setUpBoard()
	{
		//Let's do the top ones first!
		for (int row = 0; row < 3; row++)
		{
			for (int column = row % 2; column < board.length; column += 2)
			{
				place(new Piece(row, column, true), row, column);
			}
		}
		
		//Let's do the bottom ones next!
		for (int row = board.length - 3; row < board.length; row++)
		{
			for (int column = row % 2; column < board.length; column += 2)
			{
				place(new Piece(row, column, false), row, column);
			}
		}
		
		
	}
	
	
	//Changed the comments to explain piece.pieceMove and piece.reset
	/**
	 * Places and draws a given piece at a given position on the checker board. 
	 * This method also changes the piece's internal location, and resetting the piece's moved and captured variables.
	 * @param piece The piece to be placed on the checkerBoard
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
	 */
	public void place(Piece piece, int destinationX, int destinationY)
	{
		board[destinationX][destinationY] = piece;
		piece.pieceMove(destinationX, destinationY);
		piece.reset();
		this.drawPiece(piece);	
	}

	
	/**
	 * Draws a given piece at its internal location. It determines the piece's shape by its color and its royalty.
	 * @param piece The given piece to be drawn.
	 */
	public void drawPiece(Piece piece)
	{
		final String FILE_TYPE = ".png";
		final String CROWN = "-crowned";
		final String DARK = "dark";
		final String LIGHT = "light";
		final double OFFSET = 0.5;
		final String PAWN = "pawn";
		if (piece != null)
		{
			String color = "-" + (piece.isDark() ? DARK : LIGHT);
			String royalty = piece.isKing() ? CROWN : "";
			
			StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, PAWN + color + royalty + FILE_TYPE, 1, 1);
		}
		
	}
	/**
	 * Removes and returns the piece at the given location on the checker board. This also removes the piece's drawing from the graphical checker board.
	 * @param selectedX The x position of the given location.
	 * @param selectedY The y position of the given location.
	 * @return The piece that was removed, or null if there was no piece to remove/location is invalid.
	 */
	public Piece remove(int selectedX, int selectedY)
	{
		
		Piece removedPiece = board[selectedX][selectedY];
		board[selectedX][selectedY] = null;
		this.drawSquare(selectedX, selectedY);
		return removedPiece;
	}
	
	//TODO: update comments
	/**
	 * Draws a square on the checker board at the given location. 
	 * If the given location is also the location of the selected piece, the square is white. If not, the square color is determined by its location.
	 * If the (sum of the x and y position of the given location) % 2 == 0, then the square is gray. If not, the square is red.
	 * @param selectedX The x position of the given location.
	 * @param selectedY The y position of the given location.
	 */
	public void drawSquare(int selectedX, int selectedY)
	{
		final double OFFSET = .5;
		if (board[selectedX][selectedY] == (selectedPiece)) // 
		{
			//g2.setColor(Color.WHITE);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if ((selectedX + selectedY) % 2 == 0)
		{
			//g2.setColor(Color.RED);
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else
		{
			//g2.setColor(Color.GRAY);
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(selectedX + OFFSET, selectedY + OFFSET, OFFSET);
	}
	
	/**
	 * Checks and returns a boolean signifying if the player can end their current turn.
	 * A player can end their turn when they have moved a piece.
	 * @return True if the player can end, false if not. 
	 */
	public boolean canEndTurn()
	{
		return selectedPiece != null && selectedPiece.hasMoved();
	}
	/**
	 * Ends the player's turn.
	 * This method de-selects the currently selected piece by updating the graphical checker board (removing white highlight around selected piece) and the selected Piece variable, setting the piece's moved and captured variables to null, and finally changing the turn.
	 */
	public void endTurn()
	{
		this.drawSquare(selectedPiece.getX(), selectedPiece.getY());
		this.drawPiece(selectedPiece);
		selectedPiece.reset();
		selectedPiece = null;
		darkTurn = !darkTurn;
	}

	/**
	 * Determines if the game has ended, and if it has, who won. 
	 * This method searches the board for light and dark pieces. If there are no light pieces, the dark player has won. If there are no dark pieces, the light player has won. 
	 * @return If the game has ended, it returns the winner. If the game has not ended, it returns null.
	 */
	public String winner()
	{
		int light = 0;
		int dark = 0;
		for (int row = 0; row < BOARD_LENGTH; row++)
		{
			for (int column = 0; column < BOARD_LENGTH; column++)
			{
				if (board[row][column] != null)
				{
					if (board[row][column].isDark())
					{
						dark++;
					} else
					{
						light++;
					}
				}
			}
		}
		if (light == 0)
		{
			return "Dark has won!";
		} else if (dark == 0)
		{
			return "Light has won!";
		}
		return null;
	}
	
	/**
	 * Moves the selected piece to a given location. 
	 * This is accomplished by first removing the selected piece, then placing it at a different location. The internal location of the selected piece and its moved status are also changed.
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
	 */
	public void move(int destinationX, int destinationY)
	{
		Piece movedPiece = remove(selectedPiece.getX(), selectedPiece.getY());
		place(movedPiece, destinationX, destinationY);
	}
	
	/**
	 * Moves the selected piece to a given location. However, since this is a capture, the piece in between the origin and destination of the selectedPiece is removed. 
	 * The movement of the selected piece is accomplished by first removing it, then placing it at a given location. The internal location of the selected piece and its moved and captured status are also changed.
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
	 */
	public void capture(int destinationX, int destinationY)
	{
		/*Let's do some math! In order to figure out the piece captured, 
		 * we can use the midpoint formula to figure out the location halfway between two points 
		 * (e.g. the piece halfway between selectedPiece's original position and its new position is the captured piece!) */
		remove((selectedPiece.getX() + destinationX) / 2, (selectedPiece.getY() + destinationY) / 2);
		Piece movedPiece = remove(selectedPiece.getX(), selectedPiece.getY());
		movedPiece.pieceCapture(destinationX, destinationY);
		place(movedPiece, movedPiece.getX(), movedPiece.getY());
	}
	
	/**
	 * Calculates and returns whether or not the distance of selectedPiece's given movement qualifies as a capture movement (used in canSelect). 
	 * @param destinationX The x position of the given movement.
	 * @param destinationY The y position of the given movement.
	 * @return True if the movement distance qualifies as a capture, false if not.
	 */
	public boolean isCaptureDistance(int destinationX, int destinationY)
	{
		return (Math.abs(selectedPiece.getX() - destinationX) == 2 && Math.abs(selectedPiece.getY() - destinationY) == 2);	
	}
	
	//TODO: CHANGE THE COMMENTS FOR THIS ONE
	/**
	 * Returns whether or not the location clicked can be selected.
	 * This will be true in one of two circumstances:
	 * - the location selected is a piece, the piece is the player's piece, and no piece has been selected or the selected piece has not moved.
	 * - the location selected is a square, and the selected piece has not moved or is planning to make another capture move.
	 * @param selectedX The first index of the 2 dimensional array in which is to be checked for valid selection.
	 * @param selectedY The second index of the 2 dimensional array in which is to be checked for valid selection.
	 * @return True if the position can be selected, false if not. 
	 */
	public boolean canSelect(int selectedX, int selectedY)
	{	
		if (selectedX > -1 && selectedX < board.length && 
			selectedY > -1 && selectedY < board.length)
		{
			if (board[selectedX][selectedY] != null)
			{
				if (darkTurn == board[selectedX][selectedY].isDark() && (selectedPiece == null || !selectedPiece.hasMoved()))
				{
					return true;
				}
			} else
			{
				if (selectedPiece != null && (!selectedPiece.hasMoved() || (selectedPiece.hasCaptured() && isCaptureDistance(selectedX, selectedY))))
				{
					return validMove(selectedX, selectedY);
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks if a given location is a valid destination for the selectedPiece to be moved to,
	 * given that:
	 * The selectedPiece has not yet moved or it has captured and is making another capture.
	 * The destination is an empty square.
	 * 
	 * This method first finds the distance from the selected piece to its destination. 
	 * If the selected piece is crowned or the piece's destination is in the correct direction (dark goes down, light goes up), the distances are then checked.
	 * 		If the absolute value of both x and y distances are 1, then true is returned.
	 * 		If the absolute value of both x and y distances are 2, the location between the selected piece's location and its destination is checked.
	 *			If this location contains a piece of the opposite color to the selected piece, then true is returned.
	 * In any other situation, the method returns false; 
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
	 * @return
	 */
	public boolean validMove(int destinationX, int destinationY)
	{
		int incrementX = destinationX - selectedPiece.getX();
		int incrementY = destinationY - selectedPiece.getY();
		if (selectedPiece.isKing() || (selectedPiece.isDark() && incrementY > 0 || (!selectedPiece.isDark() && incrementY < 0)))
		{
			if (Math.abs(incrementY) == 1 && Math.abs(incrementX) == 1)
			{
				return true;
			} else if (Math.abs(incrementY) == 2 && Math.abs(incrementX) == 2)
			{
				//Captured piece does not share the same color as selected piece.
				int capturedX = (selectedPiece.getX() + destinationX) / 2;
				int capturedY = (selectedPiece.getY() + destinationY) / 2;
				return board[capturedX][capturedY] != null && (board[capturedX][capturedY].isDark() == !selectedPiece.isDark());
			} else
			{
				return false;
			}
		} 
		return false;
	}
	

	/**
	 * Selects the given location. 
	 * First selectedPiece is checked to see if it is a piece or an empty square.
	 * If the given location is not null (a piece), then selectedPiece is assigned to the piece at the given location and the GUI is updated.
	 * If the given location is an empty square, then a move or capture is executed with selectedPiece and all necessary variables and GUIs.
	 * @param x The first index of the 2 dimensional array in which is selected.
	 * @param y The second index of the 2 dimensional array in which is selected.
	 */
	public void select(int x, int y)
	{
		if (board[x][y] != null)
		{
			if (selectedPiece != null)
			{
				this.drawSquare(selectedPiece.getX(), selectedPiece.getY());
				this.drawPiece(selectedPiece);
			}
			this.drawSquare(x, y);
            selectedPiece = board[x][y];
            this.place(selectedPiece, x, y);
		} else
		{
			selectedPiece.pieceMove(x, y);
		}
	}
	
	
	public static void main(String[] hi)
	{

		CheckerBoard test = new CheckerBoard();
		int x, y;

        while (test.winner() ==  null)
        {
            
             if (StdDrawPlus.mousePressed() && StdDrawPlus.mouseX() > -1 && StdDrawPlus.mouseX() < BOARD_LENGTH && StdDrawPlus.mouseY() > -1 && StdDrawPlus.mouseY() < BOARD_LENGTH)
             {
            	 x = (int) StdDrawPlus.mouseX();
                 y = (int) StdDrawPlus.mouseY();
                 if (test.canSelect(x, y))
                 {
                	 test.select(x, y);
                 }
             }

            // Monitors for space-bar presses. Switches turn whenever the space-bar is pressed. */
             if (StdDrawPlus.isSpacePressed())
             {
                 if (test.canEndTurn())
                 {
                	 test.endTurn();
                 }
             }
 
             StdDrawPlus.show(100);   
        }
        System.out.println(test.winner());
	}
	
}
