import javax.swing.JComponent;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;

public class CheckerBoardV2 extends JComponent
{
	
	// TODO(wesley): Implement  all of the graphics. 
	// TODO(wesley): Fix this and make it work!
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
	private int xIncrement;
	private int yIncrement;
	private boolean isEndTurn;
	private static final int BOARDSIZE = 8;
	private PieceV2[][] board;
	private boolean darkTurn; //true is dark and false is light. Dark is bottom and top is light. 
	private PieceV2 select = null;
	private int boardSize;
	
	public CheckerBoardV2(int num)
	{
		darkTurn = true;
		xIncrement = 0;
		yIncrement = 0;
		isEndTurn = false;
		board = new PieceV2[num][num];
		boardSize = num;
		for(int row = 0; row < boardSize; row++)
		{
			for(int column = 0; column < boardSize; column++)
			{
				if ((row + column) % 2 == 0)
				{
					board[row][column] = null;
				} else
				{
					//boolean
					if (column >= 0 && column <= 2)
			    	{
			       		PieceV2 piece = new PieceV2(row, column, true, false);
			       		board[row][column] = piece;
			    	}
			    	else if (column >= boardSize - 3 && column <= boardSize - 1)
			    	{
			    		PieceV2 piece = new PieceV2(row, column, false, false);
			    		board[row][column] = piece;
			    	} else
			    	{
			    		board[row][column] = null;
			    	}
				}
			}			
		}
	}
	/**
	 * Sets the piece selected as moved.
	 */
	public void moved()
	{
		select.setMoved(true);
	}
	/**
	 * Sets the piece selected as having captured another piece. 
	 */
	public void captured()
	{
		select.setCaptured(true);
	}
	/**
	 * Changes the position of the piece moving so that it moves.
	 * @param x The number of pixels moved to the left or right/
	 * @param y The number of pixels moved up or down.
	 */
	public void setIncrement(int x, int y)
	{
		xIncrement = x;
		yIncrement = y;
	}
	/**
	 * Resets the increments when the piece has stopped moving. 
	 */
	public void resetIncrement()
	{
		xIncrement = 0;
		yIncrement = 0;
	}
	/**
	 * Draws the board.
	 */
	public void paintComponent(Graphics g)
	{
		for(int row = 0; row < 8; row++)
		{
			for(int column = 0; column < 8; column++)
			{
				//TODO: Reduce
				if ((row + column) % 2 == 0)
				{
			       	this.drawSquare(g, row, column, false);
				}
				else
				{
			       	this.drawSquare(g, row, column, false);
			       	if (board[row][column] != null)
			       	{
			       		board[row][column].drawPiece(g);
			       	}
				}
			}			
		}	
		if (isEndTurn)
		{
			this.drawSquare(g, select.getX(), select.getY(), false);
			select.drawPiece(g);
			select.setMoved(false);
			select.setCaptured(false);
			select = null;
			darkTurn = !darkTurn;
			
		}
		if (select != null)
		{
			System.out.println("Piece selected.");
			this.drawSquare(g, select.getX(), select.getY(), true);
			select.drawPiece(g, xIncrement, yIncrement);
			
		} 
		/*else//selecting empty space
		{
			select.move(xMove, yMove);
		}
		*/
	}
	
		
		

		//Construct a rectangle and draw it

		
		
//Move rectangle 15 units to the right and 25 units down
		//circle.translate(15, 25);

//Draw moved rectangle

		//g2.draw(cicle);	

	
	

	//Add margins
	//Concentric circles for pieces
	// "K" represents king
	
	
	/**
	 * Places a piece on the CheckerBoard
	 * @param p The piece to be placed on the checkerBoard
	 * @param x The first index of the 2 dimensional array in which the piece will be placed.
	 * @param y The second index of the 2 dimensional array in which the piece will be placed.
	 */
	public void place(PieceV2 p, int x, int y)
	{
		if(p != null)
		{
			board[x][y] = p;
			//repaint();
		}
	}
	/**
	 * Removes a piece from the CheckerBoard
	 * @param x The first index of the 2 dimensional array in which will be removed.
	 * @param y The second index of the 2 dimensional array in which will be removed.
	 * @return The piece that was removed.
	 */
	public PieceV2 remove(int x, int y)
	{
		PieceV2 temp = null;
		if(board[x][y] == null)
		{
			System.out.println("No piece to remove.");
		}
		temp = board[x][y];
		board[x][y] = null;
		//repaint();
		return temp;
	}

	/**
	 * Draws a square on the CheckerBoard
	 * @param x The first index of the 2 dimensional array in which the square will be drawn.
	 * @param y The second index of the 2 dimensional array in which the square will be drawn.
	 * @param select If select is true, the square is white, if not, the square will be drawn according to the board. 
	 */
	public void drawSquare(Graphics g, int x, int y, boolean select)
	{
		Graphics2D g2 = (Graphics2D) g;
		if (select)
		{
			g2.setColor(Color.WHITE);
		}
		else if ((x + y) % 2 == 0)
		{
			g2.setColor(Color.RED);
		}
		else
		{
			g2.setColor(Color.GRAY);
		}
		Rectangle checkerSquare = new Rectangle((int) (75 * x + 5), (int) (75* y + 5), 75, 75);
		g2.fill(checkerSquare);
	}
	/**
	 * Returns the piece at the given location.
	 * @param x The first index of the 2 dimensional array in which the piece there is checked.
	 * @param y The second index of the 2 dimensional array in which the piece there is checked. 
	 * @return The piece at the given location. 
	 */
	public PieceV2 pieceAt(int x, int y)
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
		System.out.println("turn ending");
		isEndTurn = true;
		//select = null;
		repaint();
	}
	/**
	 * Gets whose turn it is. 
	 * @return True is dark's turn, false is light's turn.
	 */
	public boolean getTurn()
	{
		return darkTurn;
	}
	//Tester
	/**
	 * Changes the turn to the variable turn.
	 * @param turn The value turn is changed to.
	 */
	public void changeTurn(boolean turn)
	{
		this.darkTurn = turn;
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
					}
					else
					{
						light++;
					}
				}
			}
		}
		if (light == 0)
		{
			return "Blue has won!";
		}
		else if (dark == 0)
		{
			return "Magenta has won!";
		}
		return null;
	}
	/**
	 * Assigns the select variable a value.
	 * @param select The value the select variable is assigned to.
	 */
	public void setSelect(PieceV2 select)
	{
		System.out.println("Selected");
		this.select = select;
		System.out.println(this.select);
		
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
			System.out.println("The turn is " + darkTurn);
			System.out.println("The piece is " + board[x][y].isDark());
			if (board[x][y].isDark() == darkTurn && (select == null || (!select.hasMoved() && !select.hasCaptured())))
			{
				//System.out.println("Piece canSelect = true");
				return true;
			}
			else
			{
				//System.out.println("Piece canSelect = false");
				return false;
			}
		}
		else //selects space
		{
			if (select != null && (select.validMove(x, y, this) && (!select.hasMoved() || (!select.hasCaptured() && Math.abs(select.getX() - x) == 2))))
			{
				//System.out.println("Space canSelect = true");
				return true;
			}
			else
			{
				//System.out.println("Space canSelect = false");
				return false; 
			}	
		}
	}

	/**
	 * Selects the given location.
	 * @param x The first index of the 2 dimensional array in which is selected.
	 * @param y The second index of the 2 dimensional array in which is selected.
	 */
	
	public int getBoardSize()
	{
		return BOARDSIZE;
	}
	/*public static void main(String[] args)
	{
		int x, y;
		JFrame frame = new JFrame();
		CheckerBoardV2 test = new CheckerBoardV2();
		frame.setSize(700, 700);
		frame.setTitle("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		class MousePressedListener extends MouseAdapter
		{
			public void mousePressed(MouseEvent event)
			{
				int x = event.getX();
				int y = event.getY();
				if ((x >= 5 && x <= 75 * BOARDSIZE + 5) && (y >= 5 && y <= 75 * BOARDSIZE + 5))
				{
					x = ((int) x - 5) % 75;
					y = ((int) y - 5) % 75;
					if (test.canSelect(x, y))
					{
						test.select(g, x, y);
					}
				}
			}

		}
		
		class KeyPressedListener extends KeyAdapter
		{

			public void keyPressed(KeyEvent arg0) 
			{
				if (test.canEndTurn())
				{
					test.endTurn(g);
				}
			}

		}
		frame.add(test);
		frame.setVisible(true);

        while (test.winner() ==  null)
        {
        	MouseListener listener1 = new MousePressedListener();
        	KeyListener listener2 = new KeyPressedListener();
    		test.addMouseListener(listener1);
    		test.addKeyListener(listener2);
    		test.setFocusable(true);
        }
        
	}*/
	
}
