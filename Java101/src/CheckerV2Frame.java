import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.Timer;


public class CheckerV2Frame extends JFrame
{
	private CheckerBoardV2 scene;
	private int counter;
	private int max;
	private Timer t;
	private PieceV2 piece;
	private int[] coordinates;
	private int xCounter;
	private int yCounter;
	
	class KeyListener extends KeyAdapter
	{
		public void keyPressed(KeyEvent e)
		{
			//System.out.println("TERENCE WAS HERE");
			if (scene.canEndTurn())
			{
				System.out.println("Yay! The Turn can end!");
				scene.endTurn();
			} else
			{
				System.out.println("RIP");
			}
		}
	}
	
	class TimerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			//System.out.println("Let's draw some pieces!");
			movePiece();
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
			int x = (int) ((event.getX() - 13) / 75);
			int y = (int) ((event.getY() - 35) / 75);
			System.out.println(x + ", " + y);
			System.out.println("Test 1: " + scene.pieceAt(x, y));
			System.out.print("Test 1: ");
			System.out.println((scene.pieceAt(x, y) != null) && scene.canSelect(x, y));
			System.out.println("Scene: " + scene);
			System.out.println("Test 1b: " + scene.pieceAt(x, y));
			select(x, y);
			System.out.println("Test 1c: " + scene.pieceAt(x, y));
		}
			/*
			System.out.println(event.getX() + ", " + event.getY());
			System.out.println(x + ", " + y);
			System.out.println(scene.pieceAt(x, y));
			if (scene.pieceAt(x, y) != null)
			{
				piece = scene.pieceAt(x, y);
				coordinates = piece.tryMove();
				System.out.println("Mouse Coordinates: " + x + ", " + y);
				if (coordinates != null)
				{
					System.out.println("Move coordinates: " + coordinates[0] + ", " + coordinates[1]);
					ActionListener listener = new TimerListener();
					t = new Timer(10, listener);
					t.start();
				} 
			}
			*/
		@Override
		public void mouseReleased(MouseEvent arg0) 
		{
			
		}	
	}

			
		
	
	
	public CheckerV2Frame()
	{
		xCounter = 0;
		yCounter = 0;
		max = 0;
		CheckerBoardV2 board = new CheckerBoardV2(8);
		scene = board;
		MouseListener listenerMouse = new MousePressedListener();
		KeyAdapter listenerKey = new KeyListener();
		addMouseListener(listenerMouse);
		addKeyListener(listenerKey);
		setFocusable(true);
		add(board);
		setSize(625, 648);
	}
	
	public void select(int x, int y)
	{
		//System.out.println("Instance Variables: (" + this.x + ", " + this.y + ")");
		System.out.println("Parameter Variables: (" + x + ", " + y + ")");
		System.out.println("Scene: " + scene);
		System.out.println(scene.pieceAt(x, y));
		System.out.println("Test 1d: " + scene.pieceAt(x, y));
		if ((scene.pieceAt(x, y) != null) && scene.canSelect(x, y))
		{
			piece = scene.pieceAt(x, y);
			scene.setSelect(piece);
			scene.repaint();
		} else if (scene.canSelect(x, y))
		{
			if (piece.validMove(x, y, scene))
			{
				coordinates = new int[]{x, y};
				if (Math.abs(piece.getX() - x) == 2)
				{
					max = 150;
				} else
				{
					max = 75;
				}
				ActionListener listener = new TimerListener();
				t = new Timer(10, listener);
				t.start();
			}
		}
	}
	
	public void movePiece()
	{
		if (counter < max && piece != null)
		{
			xCounter += compare(coordinates[0], piece.getX());
			yCounter += compare(coordinates[1], piece.getY());
			//System.out.println("Moving!");
			//System.out.println("xIncrement in timer: " + xCounter);
			//System.out.println("yIncrement in timer: " + yCounter);
			scene.setIncrement(xCounter, yCounter);
			scene.repaint();
			counter++;
		} else if (counter == max)
		{
			System.out.println("FINISHED!");
			int formerX = piece.getX();
			int formerY = piece.getY();
			piece.setX(coordinates[0], scene);
			piece.setY(coordinates[1], scene);
			System.out.println((coordinates[0] == piece.getX()));
			System.out.println((coordinates[1] == piece.getY()));
			scene.moved();
			scene.resetIncrement();
			if (max == 150)
			{
				int changeX = -(piece.getX() - formerX) / 2;
				int changeY = -(piece.getY() - formerY) / 2; 
				scene.remove(formerX + changeX, formerY + changeY);	
				scene.captured();
			}
			//scene.setSelect(null);
			scene.repaint();
			xCounter = 0;
			yCounter = 0;
			counter = 0;
			t.stop();
		} 
	}
	
	public int compare(int a, int b)
	{
		if (a > b)
		{
			return 1;
		} else if (a < b)
		{
			return -1;
		} else
		{
			return 0;
		}
	}
}
