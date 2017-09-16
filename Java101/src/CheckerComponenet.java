
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.Color;
import java.awt.Font;
public class CheckerComponenet extends JComponent
{	
	private boolean test;
	private int boardSize;
	private DrawPiece[][] board;
	public DrawPiece setX(DrawPiece piece, int increment)
	{
		remove(piece.getX(), piece.getY());
		piece.setX(increment);
		place(piece, piece.getX(), piece.getY());
		return piece;
	}
	
	public void setY(DrawPiece piece, int increment)
	{
		remove(piece.getX(), piece.getY());
		piece.setY(increment);
		place(piece, piece.getX(), piece.getY());
	}
	public DrawPiece remove(int x, int y)
	{
		DrawPiece temp = board[x][y];
		board[x][y] = null;
		return temp;
	}
	public void place(DrawPiece piece, int x, int y)
	{
		if (board[x][y] == null)
		{
			board[x][y] = piece;
		}
	}
	
	/**
	 * Checks if there is a piece at the given location.
	 * @param x The x-coordinate of the square being searched.
	 * @param y The y-coordinate of the square being searched.
	 * @return If there is a piece there, return true. Else, return false.
	 */
	public DrawPiece pieceAt(int x, int y)
	{
		if (x >= 0 && x <= boardSize - 1 && y >= 0 && y <= boardSize - 1)
		{
			return board[x][y];
		}
		return null;
	}
	/**
	 * Makes a CheckerComponent.
	 * @param t The value of the variable test.
	 * @param num The size of the board.
	 */
	public CheckerComponenet(int num)
	{
		board = new DrawPiece[num][num];
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
					if (column >= 0 && column <= 2)
			    	{
			       		DrawPiece piece = new DrawPiece(row, column, false, true, this);
			       		board[row][column] = piece;
			    	}
			    	else if (column >= boardSize - 3 && column <= boardSize - 1)
			    	{
			    		DrawPiece piece = new DrawPiece(row, column, false, false, this);
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
	 * Draws a CheckerBoard.
	 */
	public void paintComponent(Graphics g)
	{
		for(int row = 0; row < boardSize; row++)
		{
			for(int column = 0; column < boardSize; column++)
			{
				if ((row + column) % 2 == 0)
				{
			       	this.drawSquare(g, row, column, false);
				}
				else
				{
			       	this.drawSquare(g, row, column, false);
				}
			}			
		}	
		for(int row = 0; row < boardSize; row++)
		{
			for(int column = 0; column < boardSize; column++)
			{
				if (board[row][column] != null)
				{
					board[row][column].paint(g);
				}
			}
		}
	}
	public boolean isTest() 
	{
		return test;
	}
	public void setTest(boolean test) 
	{
		this.test = test;
	}
	public int getBoardSize() 
	{
		return boardSize;
	}
	public void setBoardSize(int boardSize) 
	{
		this.boardSize = boardSize;
	}
	public DrawPiece[][] getBoard() 
	{
		return board;
	}
	public void setBoard(DrawPiece[][] board) 
	{
		this.board = board;
	}
	
	

	//Add margins
	//Concentric circles for pieces
	// "K" represents king
	//Add margins
	/**
	 * Draws the squares of the CheckerBoard
	 * @param g The Graphics variable in which the square is drawn with. 
	 * @param x The x - coordinate of the square.
	 * @param y The y - coordinate of the square.
	 * @param select If true, the square is selected. If false, the square is not selected.
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
	
	
}
