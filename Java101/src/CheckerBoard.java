import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
/*
 * Creates a CheckerBoard object. The purpose of the CheckerBoard object is to represent a checker board and allow the user to play a game of checkers with it.
 * 
 * A CheckerBoard object contains a two-dimensional array of Piece objects, a boolean indicating the player turn, and a selected Piece object. There is also a constant boardsize that is used to determine the size of the board.
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
	public static final int BOARDSIZE = 8;
	private boolean darkTurn; 
	private Piece selectedPiece;
	
	/**
	 * Creates a default checker board with 
	 */
	public CheckerBoard()
	{
		this(false);
	}
	/**
	 * Creates a CheckerBoard object. 
	 * This initializes the two dimensional array that holds the information of the checker board. If test is false, it creates a normal checker board with pieces on the black tiles for the top three and bottom three rows. If test is true, it creates a checkerboard with no pieces on it. 
	 * Also, it sets the current turn (in this case, it defaults to dark starting first) and initializes the selected piece to null.
	 * @param test If this is false, the checker board will be set with pieces on the black tiles for the top three and bottom three rows (classic checker board setup). If this is true, the checker board starts off empty.
	 */
	public CheckerBoard(boolean test)
	{
		//TODO: Move all the StdDrawPlus and drawing stuff to a different method
		//TODO: Constructor sets everything to null
<<<<<<< HEAD
		//TODO: IF test is true, call setUpBoard that will set pieces
		board = new Piece[BOARDSIZE][BOARDSIZE];
		darkTurn = true;
		selectedPiece = null;
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				/*if (test)
				{
					board[row][column] = null;
				} else
				{
		        	this.drawSquare(row, column, false);
					if ((row + column) % 2 != 0)
			        {
						board[row][column] = null;
			        } else
			        {
				        if (column >= 0 && column <= 2)
			    		{
				        	Piece newPiece = new Piece(row, column, true);
				        	this.place(newPiece, row, column);
			    		} else if (column >= BOARDSIZE - 3 && column <= BOARDSIZE - 1)
			    		{
			    			Piece newPiece = new Piece(row, column, false);
				        	this.place(newPiece, row, column);
			    		} else
			    		{
							board[row][column] = null;
			    		}
			            
					}			
				}*/
				this.drawSquare(row, column, false);
				board[row][column] = null;
				if (!test)
				{
					//TODO: call another method that popultes the board
=======
		//TODO: If test is true, call setUpBoard that will set pieces.
		board = new Piece[BOARDSIZE][BOARDSIZE];
		darkTurn = true;
		selectedPiece = null;
		
		for (int row = 0; row < BOARDSIZE; row++)
		{
			for (int column = 0; column < BOARDSIZE; column++)
			{
				this.drawSquare(row, column);
				board[row][column] = null;
				if (!test)
				{
					setUpBoard();
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
				}
			}
		}
		drawBoard();
	}
	
	/**
	 * Draws the board as a graphical interface. 
	 */
	public void drawBoard() 
	{
		StdDrawPlus.setXscale(0, board.length);
		StdDrawPlus.setYscale(0, board.length);
		for (int row = 0; row < board.length; row++)
		{
			for (int column = 0; column < board.length; column++)
			{
				drawSquare(row, column);
				if (board[row][column] != null)
				{
					drawPiece(board[row][column]);
				}
			}
		}
	}
	/**
	 * Positions the pieces for the start of the game. 
	 * This includes placing dark pieces on the gray squares in the top three rows, as well as placing light pieces on the gray squares in the bottom three rows.
	 */
	public void setUpBoard()
	{
		//Let's do the top ones first!
		for (int row = 0; row < 3; row++)
		{
			for (int column = row % 2; column < board.length; column += 2)
			{
				board[row][column] = new Piece(row, column, true); //This creates a dark Piece with a given location of (row, column) 
			}
		}
		
		for (int row = board.length - 3; row < board.length; row++)
		{
			for (int column = row % 2; column < board.length; column += 2)
			{
				board[row][column] = new Piece(row, column, false); //This creates a light Piece with a given location of (row, column) 
			}
		}
		
		
	}
	
	
	
	/**
<<<<<<< HEAD
	 * Places a piece on the board. It also updates the GUI, drawing a piece at the specified location.
	 * @param piece The piece to be placed and drawn on the board.
	 * @param destinationX The x position of the specified location.
	 * @param destinationY The y position of the specified location.
=======
	 * Places and draws a given piece at a given position on the checker board. 
	 * @param p The piece to be placed on the checkerBoard
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
	 */
	public void place(Piece piece, int destinationX, int destinationY)
	{
<<<<<<< HEAD
		board[destinationX][destinationY] = piece;
		//Set internal position of the piece: don't use movement Piece.movement!
		this.drawPiece(piece);	
	}

	/**
	 * Draws a piece on the GUI at its internal position. 
	 * @param piece The piece to be drawn.
	 */
	public void drawPiece(Piece piece)
	{
		final double OFFSET = 0.5;
=======
		board[destinationX][destinationY] = p;
		this.drawPiece(p);	
	}

	/**
	 * Draws a given piece at its internal location. It determines the piece's shape by its color and its royalty.
	 * @param piece The given piece to be drawn.
	 */
	public void drawPiece(Piece piece)
	{
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
		if (piece != null)
		{
			if (piece.isDark() && piece.isKing())
			{
<<<<<<< HEAD
				StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, "pawn-dark-crowned.png", 1, 1);
			} else if (piece.isDark() && !piece.isKing())
			{
				StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, "pawn-dark.png", 1, 1);
			} else if (!piece.isDark() && piece.isKing())
			{
				StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, "pawn-light-crowned.png", 1, 1);
			} else
			{
				StdDrawPlus.picture(piece.getX() + OFFSET, piece.getY() + OFFSET, "pawn-light.png", 1, 1);
=======
				StdDrawPlus.picture(piece.getX() + .5, piece.getY() + .5, "pawn-dark-crowned.png", 1, 1);
			} else if (piece.isDark() && !piece.isKing())
			{
				StdDrawPlus.picture(piece.getX() + .5, piece.getY() + .5, "pawn-dark.png", 1, 1);
			} else if (!piece.isDark() && piece.isKing())
			{
				StdDrawPlus.picture(piece.getX() + .5, piece.getY() + .5, "pawn-light-crowned.png", 1, 1);
			} else
			{
				StdDrawPlus.picture(piece.getX() + .5, piece.getY() + .5, "pawn-light.png", 1, 1);
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
			}
		}
		
	}
	/**
<<<<<<< HEAD
	 * Removes and returns a piece from the CheckerBoard. It also updates the GUI, removing a piece at the specified location.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
	 * @return The piece that was removed, or null if there was no piece to remove/location is out of bounds.
=======
	 * Removes and returns the piece at the given location on the checker board. This also removes the piece's drawing from the graphical checker board.
	 * @param selectedX The x position of the given location.
	 * @param selectedY The y position of the given location.
	 * @return The piece that was removed, or null if there was no piece to remove/location is invalid.
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
	 */
	public Piece remove(int selectedX, int selectedY)
	{
		//REMOVE THESE
		//HAVE SELECT REMOVE THESE
		Piece temp = null;
		if (selectedX < 0 || selectedX > BOARDSIZE - 1 ||
<<<<<<< HEAD
			selectedY < 0 || selectedY > BOARDSIZE - 1)
		{
			System.out.println("Location specified is out of bounds.");
		} else if (board[selectedX][selectedY] == null)
		{
=======
		    selectedY < 0 || selectedY > BOARDSIZE - 1)
		{
			System.out.println("Location given is out of bounds.");
		} else if (board[selectedX][selectedY] == null)
		{
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
			System.out.println("No piece to remove.");
		} else
		{
			temp = board[selectedX][selectedY];
			board[selectedX][selectedY] = null;
			this.drawSquare(selectedX, selectedY);
		}
		return temp;
	}

	/**
<<<<<<< HEAD
	 * Draws a square on the board. Square color is determined by the square's location on the board.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
=======
	 * Draws a square on the checker board at the given location. The color is determined by the given location.
	 * If the (sum of the x and y position of the given location) % 2 == 0, then the square is gray. If not, the square is red. 
	 * @param selectedX The x position of the given location.
	 * @param selectedY The y position of the given location.
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
	 */
	public void drawSquare(double selectedX, double selectedY)
	{
		this.drawSquare(selectedX, selectedY, false);	
	}
	
	/**
<<<<<<< HEAD
	 * Draws a square on the checkerboard. However, if select is true, the square will be white. If not, the square color is determined by the square's location on the board.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
=======
	 * Draws a square on the checker board at the given location. However, if select is true, the square will be white. If not, the square color is determined by the given location.
	 * @param selectedX The x position of the given location.
	 * @param selectedY The y position of the given location.
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
	 * @param select If select is true, the square is white, if not, the square will be drawn according to the board. 
	 * If the (sum of the x and y position of the given location) % 2 == 0, then the square is gray. If not, the square is red.
	 */
	public void drawSquare(double selectedX, double selectedY, boolean select)
	{
		if (select)
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
		StdDrawPlus.filledSquare(selectedX + .5, selectedY + .5, .5);
	}
	
	/**
	 * Checks and returns a boolean signifying if the player can end their current turn.
	 * @return True if the player can end, false if not. 
	 */
	public boolean canEndTurn()
	{
<<<<<<< HEAD
		//Conditional operator makes this a one-liner :)
=======
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76
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
		selectedPiece.setMoved(false);
		selectedPiece.setCaptured(false);
		selectedPiece = null;
		darkTurn = !darkTurn;
	}
<<<<<<< HEAD
=======
	
	/**
	 * Returns the boolean determining whose turn it is.
	 * @return True is dark's turn, false is light's turn.
	 */
	public boolean isDarkTurn()
	{
		return darkTurn;
	}
>>>>>>> ddc9011bc80a9b391ee5f1c80ffa773f3c985d76

	/**
	 * Determines if the game has ended, and if it has, who won. 
	 * This method searches the board for light and dark pieces. If there are no light pieces, the dark player has won. If there are no dark pieces, the light player has won. 
	 * @return If the game has ended, it returns the winner. If the game has not ended, it returns null.
	 */
	public String winner()
	{
		int light = 0;
		int dark = 0;
		for (int row = 0; row < BOARDSIZE; row++)
		{
			for (int column = 0; column < BOARDSIZE; column++)
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
		movedPiece.pieceMove(destinationX, destinationY);
		place(movedPiece, movedPiece.getX(), movedPiece.getY());
	}
	
	/**
	 * Moves the selected piece to a given location. However, since this is a capture, the piece in between the origin and destination 
	 * of the selectedPiece is removed. 
	 * The movement of the selected piece is accomplished by first removing it, then placing it at a given location. The internal location of the selected piece and its moved and captured status are also changed.
	 * @param destinationX The x position of the given location.
	 * @param destinationY The y position of the given location.
	 */
	public void capture(int destinationX, int destinationY)
	{
		/*Let's do some math! In order to figure out the piece captured, 
		 * we can use the midpoint formula to figure out the piece halfway between two points 
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
	
	
	/**
	 * Determines if the position clicked can be selected.
	 * First, the boundaries of the given position are checked.
	 * After this, the position given is evaluated.
	 * If the position given is a piece, the player turn, color, and moved aspect of the piece are checked. If the turn and color
	 * both match and the player has not yet moved, the piece can be selected.
	 * If the position given is an empty square, the value of select (select should not be null), the moved, captured, and distance to position
	 * are all checked. If the piece has not moved or it has captured and is making another capture, then validMove is run. If validMove returns true,
	 * the piece can be selected.
	 * @param selectedX The first index of the 2 dimensional array in which is to be checked for valid selection.
	 * @param selectedY The second index of the 2 dimensional array in which is to be checked for valid selection.
	 * @return True if the position can be selected, false if not. 
	 */
	public boolean canSelect(int selectedX, int selectedY)
	{	
		if (selectedX > -1 && selectedX < BOARDSIZE && 
			selectedY > -1 && selectedY < BOARDSIZE)
		{
			if (board[selectedX][selectedY] != null)
			{
				if (darkTurn == board[selectedX][selectedY].isDark() && !board[selectedX][selectedY].hasMoved())
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
		if (destinationX > -1 && destinationX < board.length && 
			destinationY > -1 && destinationY < board.length)
		{
			int incrementX = destinationX - selectedPiece.getX();
			int incrementY = destinationY - selectedPiece.getX();
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
				this.drawSquare(selectedPiece.getX(), selectedPiece.getY(), false);
				this.drawPiece(selectedPiece);
			}
			this.drawSquare(x, y, true);
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
            
             if (StdDrawPlus.mousePressed() && StdDrawPlus.mouseX() > -1 && StdDrawPlus.mouseX() < BOARDSIZE && StdDrawPlus.mouseY() > -1 && StdDrawPlus.mouseY() < BOARDSIZE)
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
