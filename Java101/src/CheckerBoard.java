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
	
	private static final int BOARDSIZE = 8;
	private Piece[][] board;
	private boolean turn = true; //true is dark and false is light. Dark is bottom and top is light. 
	private Piece select = null;
	
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
		StdDrawPlus.setXscale(0, BOARDSIZE);
		StdDrawPlus.setYscale(0, BOARDSIZE);
		for(int row = 0; row < BOARDSIZE; row++)
		{
			for(int column = 0; column < BOARDSIZE; column++)
			{
				if ((row + column) % 2 != 0)
		        {
			        this.drawSquare(row, column, false);
					board[row][column] = null;
		        } 
		        else
		        {
		        	this.drawSquare(row, column, false);
			        if (column >= 0 && column <= 2 && !test)
		    		{
			        	Piece newPiece = new Piece(row, column, true, false, this);
			        	this.place(newPiece, row, column);
		    		}
		    		else if (column >= BOARDSIZE - 3 && column <= BOARDSIZE - 1 && !test)
		    		{
		    			Piece newPiece = new Piece(row, column, false, false, this);
			        	this.place(newPiece, row, column);
		    		}
		    		else
		    		{
						board[row][column] = null;
		    		}
		            
				}			
			}	
		}
	}
	
	public boolean validMove(int x, int y)
	{
		boolean valid = false;
		if(x >= 0 && x <= BOARDSIZE - 1 && y >= 0 && y <= BOARDSIZE - 1)
		{
			if ((x - 1 == select.getX()) || (x + 1 == select.getX()))
			{
				if (select.isKing() && (y - 1 == select.getY() || y + 1 == select.getY()))
				{
					
				}
			}
			/*
			if (select.isKing())
			{
				if ((x - 1 == select.getX() || x + 1 == select.getX()) && (y - 1 == select.getY() || y + 1 == select.getY()))
				{
					valid = true;
				} else if ((x - 2 == select.getX() || x + 2 == select.getX()) && (y - 2 == select.getY() || y + 2 == select.getY()))
				{
					int captureX = -(select.getX() - x) / 2; //(x - this.x) / 2
					int captureY = -(select.getY() - y) / 2; //(y - this.y) / 2
					if (pieceAt(select.getX() + captureX, select.getY() + captureY) != null)
					{
						valid = true;
					}
				}
			}	
			else if (select.isDark())
			{
				if ((x - 1 == select.getX() || x + 1 == select.getX()) && y - 1 == select.getY())
				{
					valid = true;
				}
				else if ((x - 2 == select.getX() || x + 2 == select.getX()) && (y - 2 == select.getY()))
				{
					int captureX = -(select.getX() - x) / 2;
					int captureY = -(select.getY() - y) / 2;
					if (pieceAt(select.getX() + captureX, select.getY() + captureY) != null)
					{
						valid = true;
					}	
				}
			}
			else
			{
				if ((x - 1 == select.getX() || x + 1 == select.getX()) && y + 1 == select.getY())
				{
					valid = true;
				}	
				else if((x - 2 == select.getX() || x + 2 == select.getX()) && (y + 2 == select.getY()))
				{
					int captureX = -(select.getX() - x) / 2;
					int captureY = -(select.getY() - y) / 2;
					if (pieceAt(select.getX() + captureX, select.getY() + captureY) != null)
					{
						valid = true;
					}
				}
			}
			*/
		}
		return valid;	
	}
	
	
	/**
	 * Places a piece on the CheckerBoard
	 * @param p The piece to be placed on the checkerBoard
	 * @param x The first index of the 2 dimensional array in which the piece will be placed.
	 * @param y The second index of the 2 dimensional array in which the piece will be placed.
	 */
	public void place(Piece p, int x, int y)
	{
		if(p != null)
		{
			board[x][y] = p;
			this.drawPiece(p, x, y);	
		}
	}
	public Piece[][] getBoard() {
		return board;
	}
	public void setBoard(Piece[][] board) {
		this.board = board;
	}
	public static int getBoardsize() {
		return BOARDSIZE;
	}
	public Piece getSelect() {
		return select;
	}
	public void setTurn(boolean turn) {
		this.turn = turn;
	}
	/**
	 * Draws a piece.
	 * @param p The piece to be drawn
	 * @param x The first index of the 2 dimensional array in which the piece will be drawn.
	 * @param y The second index of the 2 dimensional array in which the piece will be drawn. 
	 */
	public void drawPiece(Piece p, int x, int y)
	{
		if (p != null)
		{
			if (p.isDark() && p.isKing())
			{
				StdDrawPlus.picture(x + .5, y + .5, "pawn-dark-crowned.png", 1, 1);
			}
			else if (p.isDark() && !p.isKing())
			{
				StdDrawPlus.picture(x + .5, y + .5, "pawn-dark.png", 1, 1);
			}
			else if (!p.isDark() && p.isKing())
			{
				StdDrawPlus.picture(x + .5, y + .5, "pawn-light-crowned.png", 1, 1);
			}
			else
			{
				StdDrawPlus.picture(x + .5, y + .5, "pawn-light.png", 1, 1);
			}
		}
		
	}
	/**
	 * Removes a piece from the CheckerBoard
	 * @param x The first index of the 2 dimensional array in which will be removed.
	 * @param y The second index of the 2 dimensional array in which will be removed.
	 * @return The piece that was removed.
	 */
	public Piece remove(int x, int y)
	{
		Piece temp = null;
		if (board[x][y] == null)
		{
			System.out.println("No piece to remove.");
		}
		temp = board[x][y];
		board[x][y] = null;
		this.drawSquare(x, y);
		return temp;
	}

	/**
	 * Draws a square on the checkerboard.
	 * @param x The first index of the 2 dimensional array in which the square will be drawn.
	 * @param y The second index of the 2 dimensional array in which the square will be drawn.
	 */
	public void drawSquare(double x, double y)
	{
		this.drawSquare(x, y, false);	
	}
	
	/**
	 * Draws a square on the CheckerBoard
	 * @param x The first index of the 2 dimensional array in which the square will be drawn.
	 * @param y The second index of the 2 dimensional array in which the square will be drawn.
	 * @param select If select is true, the square is white, if not, the square will be drawn according to the board. 
	 */
	public void drawSquare(double x, double y, boolean select)
	{
		if (select)
		{
			//g2.setColor(Color.WHITE);
			StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
		} else if ((x + y) % 2 == 0)
		{
			//g2.setColor(Color.RED);
			StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
		} else
		{
			//g2.setColor(Color.GRAY);
			StdDrawPlus.setPenColor(StdDrawPlus.RED);
		}
		StdDrawPlus.filledSquare(x + .5, y + .5, .5);
	}
	/**
	 * Returns the piece at the given location.
	 * @param x The first index of the 2 dimensional array in which the piece there is checked.
	 * @param y The second index of the 2 dimensional array in which the piece there is checked. 
	 * @return The piece at the given location. 
	 */
	public Piece pieceAt(int x, int y)
	{
		return board[x][y];
	}
	/**
	 * Checks if the player can end their current turn.
	 * @return True if the player can end, false if not. 
	 */
	public boolean canEndTurn()
	{
		if (select != null)
		{
			return select.hasCaptured() || select.hasMoved();
		}
		return false;
	}
	/**
	 * Ends the turn, assuming canEndTurn() returned true.
	 */
	public void endTurn()
	{
		this.drawSquare(select.getX(), select.getY());
		this.drawPiece(select, select.getX(), select.getY());
		select.setMoved(false);
		select.setCaptured(false);
		select = null;
		turn = !turn;
	}
	/**
	 * Gets whose turn it is. 
	 * @return True is dark's turn, false is light's turn.
	 */
	public boolean getTurn()
	{
		return turn;
	}
	//Tester
	
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
	 * Assigns the select variable a value.
	 * @param select The value the select variable is assigned to.
	 */
	public void setSelect(Piece select)
	{
		this.select = select;
	}
	
	
	/*
	 *  CanSelect
	 * use variables to see if the piece has moved or not, piece has captured or not, and store the piece so canSelect can access it(:D)
	 * Make sure to check all nooks and crannies for errors
	 * know if ur selecting a square or a piece
	 * if you moved already(not captured), nothing is valid to be selected. 
	 * 
	 * - Returns true if the square at (x, y) can be selected.
		- A square with a piece may be selected if it is the corresponding player's turn and one of the following is true:
			- The player has not selected a piece yet.
			- The player has selected a piece, but did not move it.
		- An empty square may be selected if one of the following is true:
			- During this turn, the player has selected a Piece which hasn't moved yet and is selecting an empty spot which is a valid move for the previously selected Piece.
			- During this turn, the player has selected a Piece, captured, and has selected another valid capture destination. When performing multi-captures, you should only select the active piece once; all other selections should be valid destination points.

	 */
	/**
	 * Determines if the position clicked can be selected.
	 * @param x The first index of the 2 dimensional array in which is to be checked for valid selection.
	 * @param y The second index of the 2 dimensional array in which is to be checked for valid selection.
	 * @return True if the position can be selected, false if not. 
	 */
	public boolean canSelect(int x, int y)
	{	
		if (board[x][y] != null)//you are selecting a piece
		{
			if (board[x][y].isDark() == turn && (select == null || (!select.hasMoved() && !select.hasCaptured())))
			{
				System.out.println("Piece canSelect = true");
				return true;
			}
			else
			{
				System.out.println("Piece canSelect = false");
				return false;
			}
		} else //selects space
		{
			if (select != null && ((!select.hasMoved() && select.validMove(x, y)) || (select.hasCaptured() && select.validMove(x, y) && (x == select.getX() - 2 || x == select.getX() + 2))))
			{
				System.out.println("Space canSelect = true");
				return true;
			}
			else
			{
				System.out.println("Space canSelect = false");
				return false; 
			}	
		}
	}

	/**
	 * Selects the given location.
	 * @param x The first index of the 2 dimensional array in which is selected.
	 * @param y The second index of the 2 dimensional array in which is selected.
	 */
	public void select(int x, int y)
	{
		if (board[x][y] != null)
		{
			if (select != null)
			{
				this.drawSquare(select.getX(), select.getY(), false);
				this.drawPiece(select, select.getX(), select.getY());
			}
			this.drawSquare(x, y, true);
            select = board[x][y];
            this.place(select, x, y);
		} else//selecting empty space
		{
			select.move(x, y);
		}
	}
	
	public int getBoardSize()
	{
		return BOARDSIZE;
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
