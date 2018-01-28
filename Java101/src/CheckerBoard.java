import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
public class CheckerBoard
{
	
	//Have not implemented all of the graphics yet. 
	/*
	 * CanSelect
	 * use variables to see if the piece has moved or not, piece has captured or not, and store the piece so canSelect can access it(:D)
	 * Make sure to check all nooks and crannies for errors
	 * know if ur selecting a square or a piece
	 * if you moved already(not captured), nothing is valid to be selected. 
	 * 
	 * Select
	 * know which piece you are selecting
	 * highlight the square that you have selected WHITE for greater visibility. 
	 * if u select an empty square, place the piece that you have selected before to the empty square
	 * 		u should change the x and y variables of that piece as well. 
	 * 
	 * CanEndTurn
	 * check the variables you made for canSelect to see if you can end the turn. 
	 * 
	 * EndTurn
	 * - Make sure to clear variables such as has the piece move, has the piece captured, etc. 
	 * - use a boolean variable to see whose turn it is. 
	 * 
	 * Move
	 * - u should change the x and y variables of that piece as well.
	 * - if i captured a piece, need to delete the piece that i captured.
	 * - remove first, and use place to then place it in its moved position
	 * - remove the piece captured(if there was anything captured) 
	 * - if i reach the bottom or top edge, becomes king.
	 * - update if I have captured or moved a piece. 
	 */
	//TODO: ValidMove comes here!
	
	private Piece[][] board;
	public static final int BOARDSIZE = 8;
	private boolean darkTurn; //true is dark and false is light. Dark is bottom and top is light. 
	private Piece selectedPiece;
	
	/**
	 * Creates a CheckerBoard
	 */
	public CheckerBoard()
	{
		this(false);
	}
	/**
	 * Creates a CheckerBoard. If test is false, it creates a normal checkerboard. If test is true, it creates a checkerboard with no pieces on it. 
	 * @param test Determines if the Checkerboard will have pieces or not.
	 */
	public CheckerBoard(boolean test)
	{
		board = new Piece[BOARDSIZE][BOARDSIZE];
		darkTurn = true;
		selectedPiece = null;
		StdDrawPlus.setXscale(0, BOARDSIZE);
		StdDrawPlus.setYscale(0, BOARDSIZE);
		for(int row = 0; row < BOARDSIZE; row++)
		{
			for(int column = 0; column < BOARDSIZE; column++)
			{
				if (test)
				{
					board[row][column] = null;
				} else
				{
					if ((row + column) % 2 != 0)
			        {
				        this.drawSquare(row, column, false);
						board[row][column] = null;
			        } else
			        {
			        	this.drawSquare(row, column, false);
				        if (column >= 0 && column <= 2 && !test)
			    		{
				        	Piece newPiece = new Piece(row, column, true);
				        	this.place(newPiece, row, column);
			    		} else if (column >= BOARDSIZE - 3 && column <= BOARDSIZE - 1 && !test)
			    		{
			    			Piece newPiece = new Piece(row, column, false);
				        	this.place(newPiece, row, column);
			    		} else
			    		{
							board[row][column] = null;
			    		}
			            
					}			
				}	
			}
		}
	}
	
	/**
	 * Checks if a specified location is a valid destination for the selectedPiece to be moved to. Accounts for color, royalty, and enemy pieces/empty squares.
	 * @param destinationX The x position of the specified location.
	 * @param destinationY The y position of the specified location.
	 * @return
	 */
	public boolean validMove(int destinationX, int destinationY)
	{
		if (destinationX > -1 && destinationX < BOARDSIZE && 
			destinationY > -1 && destinationY < BOARDSIZE)
		{
			
		}
	}
	
	
	/**
	 * Places a piece on the CheckerBoard. It also updates the GUI, placing a piece at the specified location.
	 * @param p The piece to be placed on the checkerBoard
	 * @param destinationX The x position of the specified location.
	 * @param destinationY The y position of the specified location.
	 */
	public void place(Piece p, int destinationX, int destinationY)
	{
		board[destinationX][destinationY] = p;
		this.drawPiece(p, destinationX, destinationY);	
	}

	/**
	 * Draws a piece on the checker board.
	 * @param p The piece to be drawn.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location. 
	 */
	public void drawPiece(Piece p, int selectedX, int selectedY)
	{
		if (p != null)
		{
			if (p.isDark() && p.isKing())
			{
				StdDrawPlus.picture(selectedX + .5, selectedY + .5, "pawn-dark-crowned.png", 1, 1);
			} else if (p.isDark() && !p.isKing())
			{
				StdDrawPlus.picture(selectedX + .5, selectedY + .5, "pawn-dark.png", 1, 1);
			} else if (!p.isDark() && p.isKing())
			{
				StdDrawPlus.picture(selectedX + .5, selectedY + .5, "pawn-light-crowned.png", 1, 1);
			} else
			{
				StdDrawPlus.picture(selectedX + .5, selectedY + .5, "pawn-light.png", 1, 1);
			}
		}
		
	}
	/**
	 * Removes a piece from the CheckerBoard. It also updates the GUI, removing a piece at the specified location.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
	 * @return The piece that was removed, or null if there was no piece to remove/location is invalid.
	 */
	public Piece remove(int selectedX, int selectedY)
	{
		Piece temp = null;
		if (board[selectedX][selectedY] == null)
		{
			System.out.println("No piece to remove.");
		} else if (selectedX < 0 || selectedX > BOARDSIZE - 1 ||
				   selectedY < 0 || selectedY > BOARDSIZE - 1)
		{
			System.out.println("Location specified is out of bounds.");
		} else
		{
			temp = board[selectedX][selectedY];
			board[selectedX][selectedY] = null;
			this.drawSquare(selectedX, selectedY);
		}
		return temp;
	}

	/**
	 * Draws a square on the checker board. Square color is determined by the board.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
	 */
	public void drawSquare(double selectedX, double selectedY)
	{
		this.drawSquare(selectedX, selectedY, false);	
	}
	
	/**
	 * Draws a square on the checkerboard. However, if select is true, the square will be white. If not, the square color is determined by the board.
	 * @param selectedX The x position of the specified location.
	 * @param selectedY The y position of the specified location.
	 * @param select If select is true, the square is white, if not, the square will be drawn according to the board. 
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
		//Conditional operator makes this a one-liner :)
		return selectedPiece != null ? selectedPiece.hasMoved() : false;
	}
	/**
	 * Ends the turn, assuming canEndTurn() returned true. This deselects the selected piece and allows the other player to select pieces.
	 */
	public void endTurn()
	{
		this.drawSquare(selectedPiece.getX(), selectedPiece.getY());
		this.drawPiece(selectedPiece, selectedPiece.getX(), selectedPiece.getY());
		selectedPiece.setMoved(false);
		selectedPiece.setCaptured(false);
		selectedPiece = null;
		darkTurn = !darkTurn;
	}
	
	/**
	 * Returns the boolean determining whether the turn 
	 * @return True is dark's turn, false is light's turn.
	 */
	public boolean isDarkTurn()
	{
		return darkTurn;
	}

	/**
	 * Determines if the game has ended, and if it has, who won.
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
	 * Moves the selected piece to a specified location. It changes all necessary variables and GUIs.
	 * @param destinationX The x position of the specified location.
	 * @param destinationY The y position of the specified location.
	 */
	public void move(int destinationX, int destinationY)
	{
		Piece movedPiece = board[selectedPiece.getX()][selectedPiece.getY()];
		board[destinationX][destinationY] = movedPiece;
		movedPiece.movement(destinationX, destinationY);
	}
	
	/**
	 * Moves the selected piece to a specified location. However, since this is a capture, the piece in between the origin and destination 
	 * of the selectedPiece is removed. It changes all necessary variables and GUIs.
	 * @param destinationX The x position of the specified location.
	 * @param destinationY The y position of the specified location.
	 */
	public void capture(int destinationX, int destinationY)
	{
		/*Let's do some math! In order to figure out the piece captured, 
		 * we can use the midpoint formula to figure out the piece halfway between two points 
		 * (e.g. the piece halfway between selectedPiece's original position and its new position is the captured piece!) */
		remove((selectedPiece.getX() + destinationX) / 2, (selectedPiece.getY() + destinationY) / 2);
		Piece movedPiece = board[selectedPiece.getX()][selectedPiece.getY()];
		board[destinationX][destinationY] = movedPiece;
		movedPiece.captureMovement(destinationX, destinationY);
		
	}
	
	/**
	 * Calculates and returns whether or not the distance of selectedPiece's specified movement qualifies as a capture movement (used in canSelect). 
	 * @param destinationX The x position of the specified movement.
	 * @param destinationY The y position of the specified movement.
	 * @return True if the movement distance qualifies as a capture, false if not.
	 */
	public boolean isCaptureDistance(int destinationX, int destinationY)
	{
		return (Math.abs(selectedPiece.getX() - destinationX) == 2 && Math.abs(selectedPiece.getY() - destinationY) == 2);
		
	}
	
	
	/**
	 * Determines if the position clicked can be selected.
	 * First, the boundaries of the specified position are checked.
	 * After this, the position specified is evaluated.
	 * If the position specified is a piece, the player turn, color, and moved aspect of the piece are checked. If the turn and color
	 * both match and the player has not yet moved, the piece can be selected.
	 * If the position specified is an empty square, the value of select (select should not be null), the moved, captured, and distance to position
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
				if (isDarkTurn() == board[selectedX][selectedY].isDark() && !board[selectedX][selectedY].hasMoved())
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
	 * Selects the given location. 
	 * First selectedPiece is checked.
	 * If the specified location is not null (a piece), then selectedPiece is assigned to the piece at the specified location and the GUI is updated.
	 * If the specified location is an empty square, then a move or capture is executed with selectedPiece and all necessary variables and GUIs.
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
				this.drawPiece(selectedPiece, selectedPiece.getX(), selectedPiece.getY());
			}
			this.drawSquare(x, y, true);
            selectedPiece = board[x][y];
            this.place(selectedPiece, x, y);
		} else
		{
			selectedPiece.movement(x, y);
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
