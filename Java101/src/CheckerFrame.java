import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.Timer;
public class CheckerFrame extends JFrame
{
	private CheckerComponenet scene;
	private int counter;
	private Timer t;
	private DrawPiece piece;
	private int[] coordinates;
	private int xCounter;
	private int yCounter;
	
	class TimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			xCounter = 0;
			yCounter = 0;
			if (counter < 75 && piece != null)
			{
				if (coordinates[0] < piece.getX())
				{
					xCounter = -1;
				} else if (coordinates[0] > piece.getX())
				{
					xCounter = 1;
				}
				if (coordinates[1] < piece.getY())
				{
					yCounter = -1;
				} else if (coordinates[1] > piece.getY())
				{
					yCounter = 1;
				}
				piece.moveIncrement(xCounter, yCounter);
				scene.repaint();
				counter++;
			} else
			{
				//System.out.println("HERRO ITS ME");
				scene.setX(piece, coordinates[0]);
				scene.setY(piece, coordinates[1]);
				//System.out.println("New coordinates: " + piece.getX() + ", " + piece.getY());
				piece.resetIncrement();
				//scene.repaint();
				//System.out.println("hi?");
				//scene.repaint();
				counter = 0;
				t.stop();
			}
		}
	}
	
	class MousePressedListener implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent event) 
		{
			if (!t.isRunning())
			{
				int x = (int) ((event.getX() - 13) / 75);
				int y = (int) ((event.getY() - 35) / 75);
				if (scene.pieceAt(x, y) != null)
				{
					piece = scene.pieceAt(x, y);
					coordinates = piece.tryMove();
					if (coordinates != null)
					{
						t.start();
					} 
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public CheckerFrame()
	{
		CheckerComponenet board = new CheckerComponenet(8);
		scene = board;
		ActionListener listener = new TimerListener();
		t = new Timer(25, listener);
		MouseListener listenerMouse = new MousePressedListener();
		addMouseListener(listenerMouse);
		add(board);
		setSize(625, 648);
	}
	
	public CheckerComponenet getBoard()
	{
		return scene;
	}
}
