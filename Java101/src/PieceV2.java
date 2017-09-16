
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
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
public class PieceV2 
{
	private int x;
	private int y;
	private boolean side;
	private boolean king;
	private boolean moved;
	private boolean captured;
	/**
	 * Draws a piece.
	 * @param p The piece to be drawn
	 * @param x The first index of the 2 dimensional array in which the piece will be drawn.
	 * @param y The second index of the 2 dimensional array in which the piece will be drawn. 
	 */
	//Take xIncrement and yIncrement as parameter variables
	public void drawPiece(Graphics g, int xIncrement, int yIncrement)
	{
		Graphics2D g2 = (Graphics2D) g; 
		g2.setColor(Color.LIGHT_GRAY);
		Ellipse2D.Double piece1 = new Ellipse2D.Double(75 * x + 15 + xIncrement, 75 * y + 15 + yIncrement, 55, 55);
		g2.fill(piece1);
		if (side)
		{
			g2.setColor(Color.BLUE);
		} else
		{
			g2.setColor(Color.MAGENTA);
		}
		Ellipse2D.Double piece2 = new Ellipse2D.Double(75 * x + 20 + xIncrement, 75 * y + 20 + yIncrement, 45, 45);
		g2.fill(piece2);
		if (king)
		{
			g2.setFont(new Font("Serif",Font.BOLD, 20));
			g2.drawString("K", x * 75 + 35 + xIncrement, y * 75 + 50 + yIncrement);
		}
		
		
	}	
	public void drawPiece(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g; 
		g2.setColor(Color.LIGHT_GRAY);
		Ellipse2D.Double piece1 = new Ellipse2D.Double(75 * x + 15, 75 * y + 15, 55, 55);
		g2.fill(piece1);
		if (side)
		{
			g2.setColor(Color.BLUE);
		} else
		{
			g2.setColor(Color.MAGENTA);
		}
		Ellipse2D.Double piece2 = new Ellipse2D.Double(75 * x + 20, 75 * y + 20, 45, 45);
		g2.fill(piece2);
		if (king)
		{
			g2.setFont(new Font("Serif",Font.BOLD, 20));
			g2.drawString("K", x * 75 + 35, y * 75 + 50);
		}
		
		
	}	
	
	public void setX(int increment, CheckerBoardV2 scene)
	{
		scene.place(scene.remove(this.x, this.y), increment, this.y);
		this.x = increment;
	}
	
	public void setY(int increment, CheckerBoardV2 scene)
	{
		scene.place(scene.remove(this.x, this.y), this.x, increment);
		this.y = increment;
	}
	/**
	 * The testing version for creating a Piece.
	 * @param x The first index of the coordinates of the piece created.
	 * @param y The second index of the coordinates of the piece created.
	 * @param side The side on which the piece will be on.
	 * @param king True if the piece is a king, false if not.
	 * @param b The checkerboard that the piece is on.
	 */
	public PieceV2(int x, int y, boolean side, boolean king) //just for testing purposes
	{
		this.x = x;
		this.y = y;
		this.side = side;
		this.king = king;
		this.moved = false;
		this.captured = false;
	}
	/**
	 * The constructor for creating a normal piece. 
	 * @param x The first index of the coordinates of the piece created. 
	 * @param y The second index of the coordinates of the piece created.
	 * @param side The side on which the piece will be on.
	 * @param b The CheckerBoard that the piece is located on. 
	 */
	public PieceV2(int x, int y, boolean side)
	{
		this.x = x;
		this.y = y;
		this.side = side;
		this.king = false;
		this.moved = false;
		this.captured = false;
	}
	/**
	 * Checks if a piece can move to a certain location.
	 * @param x The first index of the coordinates of the location.
	 * @param y The second index of the coordinates of the location.
	 * @return True if the piece can move there, false if not. 
	 */
	//Take scene as a parameter variable. 
	public boolean validMove(int x, int y, CheckerBoardV2 scene)
	{
		boolean valid = false;
		if(x >= 0 && x <= scene.getBoardSize() - 1 && y >= 0 && y <= scene.getBoardSize() - 1)
		{
			if (king)
			{
				if ((x - 1 == this.x || x + 1 == this.x) && (y - 1 == this.y || y + 1 == this.y))
				{
					valid = true;
				}
				else if ((x - 2 == this.x || x + 2 == this.x) && (y - 2 == this.y || y + 2 == this.y))
				{
					int captureX = -(this.x - x) / 2; //(x - this.x) / 2
					int captureY = -(this.y - y) / 2; //(y - this.y) / 2
					if (scene.pieceAt(this.x + captureX, this.y + captureY) != null)
					{
						valid = true;
					}
				}
			}	
			else if (side)
			{
				if ((x - 1 == this.x || x + 1 == this.x) && y - 1 == this.y)
				{
					valid = true;
				}
				else if ((x - 2 == this.x || x + 2 == this.x) && (y - 2 == this.y))
				{
					int captureX = -(this.x - x) / 2;
					int captureY = -(this.y - y) / 2;
					if (scene.pieceAt(this.x + captureX, this.y + captureY) != null)
					{
						valid = true;
					}	
				}
			}
			else
			{
				if ((x - 1 == this.x || x + 1 == this.x) && y + 1 == this.y)
				{
					valid = true;
				}	
				else if((x - 2 == this.x || x + 2 == this.x) && (y + 2 == this.y))
				{
					int captureX = -(this.x - x) / 2;
					int captureY = -(this.y - y) / 2;
					if (scene.pieceAt(this.x + captureX, this.y + captureY) != null)
					{
						valid = true;
					}
				}
			}
		}
		return valid;	
	}

	
	/**
	 * Returns the value of king. 
	 * @return The value of the king variable.
	 */
	public boolean isKing() 
	{
		return this.king;
	}
	/**
	 * Returns the value of side.
	 * @return The value of the side variable.
	 */
	public boolean isDark()
	{
		return this.side;
	}
	/**
	 * Returns the value of captured.
	 * @return The value of the captured variable.
	 */
	public boolean hasCaptured()
	{
		return captured;
	}
	/**
	 * Returns the value of x.
	 * @return The value of the x variable. 
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * Returns the value of y.
	 * @return The value of the y variable.
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * Returns the value of moved.
	 * @return The value of the moved variable.
	 */
	public boolean hasMoved()
	{
		return moved;
	}
	/**
	 * Changes the value of moved.
	 * @param newMoved The new value moved is assigned to. 
	 */
	public void setMoved(boolean newMoved)
	{
		moved = newMoved;
	}
	/**
	 * Changes the value of captured.
	 * @param newCaptured The new value captured is assigned to. 
	 */
	public void setCaptured(boolean newCaptured)
	{
		captured = newCaptured;
	}
	/*
	 * Move
	 * - u should change the x and y variables of that piece as well.
	 * - if i captured a piece, need to delete the piece that i captured.
	 * - remove first, and use place to then place it in its moved position.
	 * - remove the piece captured(if there was anything captured) 
	 * - if i reach the bottom or top edge, becomes king.
	 * - update if I have captured or moved a piece. 
	 */	
	/**
	 * Captures a piece, assuming validMove == true
	 * @param x The first index of the coordinates of the piece capturing. 
	 * @param y The second index of the coordinates of the piece capturing
	 * @param changeX The first index of the coordinates of the new position of the piece.
	 * @param changeY The second index of the coordinates of the new position of the piece. 
	 */
	public void captured(int x, int y, int changeX, int changeY, CheckerBoardV2 scene)
	{
		scene.place(scene.remove(this.x, this.y), x, y);
		scene.remove(this.x + changeX, this.y + changeY);
		//scene.drawSquare(g, x, y, false);
		//scene.place(g, scene.remove(g, this.x, this.y), x, y);
		//scene.remove(g, this.x + changeX, this.y + changeY);
		this.x = x;
		this.y = y;
		captured = true;
		moved = true;
		scene.repaint();
	}
	/**
	 * Moves a piece, assuming validMove returned true;
	 * @param x The first index of the coordinates in which the piece is to be moved to.
	 * @param y The second index of the coordinates in which the piece is to be moved to. 
	 */
	public void move(int x, int y, CheckerBoardV2 scene)
	{
		if (this.x - x == 1 || this.x - x == -1)
		{
			PieceV2 temp = scene.remove(this.x, this.y);
			this.x = x;
			this.y = y;
			//scene.drawSquare(g, x, y, true);
			scene.place(temp, this.x, this.y);
			System.out.println(scene.pieceAt(this.x, this.y));
			System.out.println(scene.pieceAt(x, y));
			System.out.println(temp);
			System.out.println("Coordinates: (" + x + ", " + y + ").");
			moved = true;
			scene.repaint();
		} else if((this.x - x == 2 || this.x - x == -2) && (this.y - y == 2 || this.y - y == -2))
		{
			this.captured(x, y, -(this.x - x) / 2, -(this.y - y) / 2, scene);
		}
		if((scene.getTurn() && this.y == scene.getBoardSize() - 1) || (!scene.getTurn() && this.y == 0))
		{
			king = true;
			scene.repaint();
		}
		
	}
	
	
	
}
