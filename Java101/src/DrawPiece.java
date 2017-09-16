import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
public class DrawPiece 
{
	private int x;
	private int y;
	private boolean isKing;
	private boolean isDark;
	private int xIncrement;
	private int yIncrement;
	private CheckerComponenet scene;
	
	public void resetIncrement()
	{
		xIncrement = 0;
		yIncrement = 0;
	}
	public void setX(int increment)
	{
		x = increment;
	}
	
	public void setY(int increment)
	{
		y = increment;
	}
	/**
	 * Draws a piece.
	 * @param g The Graphics variable which draws the piece.
	 * @param x The x - coordinate of the piece.
	 * @param y The y - coordinate of the piece.
	 * @param isKing If true the piece is a king, if not the piece is not a king.
	 * @param isDark If true the piece is blue, if not the piece is red.
	 */
	public DrawPiece(int x, int y, boolean isKing, boolean isDark, CheckerComponenet scene)
	{
		this.x = x;
		this.y = y;
		this.isKing = isKing;
		this.isDark = isDark;
		this.scene = scene;
		xIncrement = 0;
		yIncrement = 0;
	}
	/**
	 * Returns the value of x.
	 * @return The value of x.
	 */
	public int getX() 
	{
		return this.x;
	}

	/**
	 * Returns the value of y.
	 * @return The value of y.
	 */
	public int getY() 
	{
		return this.y;
	}

	/**
	 * Returns the value of isKing.
	 * @return The value of isKing.
	 */
	public boolean isKing() 
	{
		return isKing;
	}

	/**
	 * Returns the value of isDark.
	 * @return The value of isDark.
	 */
	public boolean isDark() 
	{
		return isDark;
	}

	/**
	 * Draws the piece.
	 * @param g The Graphics component that draws the piece. 
	 */
	//Get increments from a parameter variable
	public void paint(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);
		Ellipse2D.Double piece1 = new Ellipse2D.Double(75 * x + 15 + xIncrement, 75 * y + 15 + yIncrement, 55, 55);
		g2.fill(piece1);
		Ellipse2D.Double piece2 = new Ellipse2D.Double(75 * x + 20 + xIncrement, 75 * y + 20 + yIncrement, 45, 45);
		if (isDark)
		{
			g2.setColor(Color.BLUE);
		} else
		{
			g2.setColor(Color.MAGENTA);
		}
		g2.fill(piece2);
		if (isKing)
		{
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Serif",Font.BOLD, 20));
			g2.drawString("K", x * 75 + 35 + xIncrement, y * 75 + 50 + yIncrement);
		}
	}
	
	//Get scene from a parameter variable.
	public boolean validMove(int x, int y)
	{
		boolean valid = false;
		if(x >= 0 && x <= scene.getBoardSize() - 1 && y >= 0 && y <= scene.getBoardSize() - 1)
		{
			if (isKing)
			{
				if ((x - 1 == this.x || x + 1 == this.x) && (y - 1 == this.y || y + 1 == this.y) && scene.pieceAt(x, y) == null)
				{
					valid = true;
				}
				else if ((x - 2 == this.x || x + 2 == this.x) && (y - 2 == this.y || y + 2 == this.y) && scene.pieceAt(x, y) == null)
				{
					int captureX = -(this.x - x) / 2; //(x - this.x) / 2
					int captureY = -(this.y - y) / 2; //(y - this.y) / 2
					if (scene.pieceAt(this.x + captureX, this.y + captureY) != null && scene.pieceAt(x, y) == null)
					{
						valid = true;
					}
				}
			}	
			else if (isDark)
			{
				if ((x - 1 == this.x || x + 1 == this.x) && y - 1 == this.y && scene.pieceAt(x, y) == null)
				{
					valid = true;
				}
				else if ((x - 2 == this.x || x + 2 == this.x) && (y - 2 == this.y) && scene.pieceAt(x, y) == null)
				{
					int captureX = -(this.x - x) / 2;
					int captureY = -(this.y - y) / 2;
					if (scene.pieceAt(this.x + captureX, this.y + captureY) != null && scene.pieceAt(x, y) == null)
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
	
	public int[] tryMove()
	{
		int[] coordinates = null;
		//if (scene != null)
		//{
			if (isDark)
			{
				if (validMove(this.x + 1, this.y + 1))
				{
					coordinates = new int[]{this.x + 1, this.y + 1};
				} else if (validMove(this.x - 1, this.y + 1))
				{
					coordinates = new int[]{this.x - 1, this.y + 1};
				}
			} else
			{
				if (validMove(this.x + 1, this.y - 1))
				{
					coordinates = new int[]{this.x + 1, this.y - 1};
				} else if (validMove(this.x - 1, this.y - 1))
				{
					coordinates = new int[]{this.x - 1, this.y - 1};
				}
			}
		//} 
		return coordinates;
	}
	
	public void moveIncrement(int x, int y)
	{
		xIncrement += x;
		yIncrement += y;
	}
	
}

