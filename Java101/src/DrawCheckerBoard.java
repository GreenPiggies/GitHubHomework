import java.awt.Graphics;

import javax.swing.JFrame;

public class DrawCheckerBoard 
{
	private Graphics g;
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		//CheckerComponenet checkerBoard = new CheckerComponenet(null, true, 8);
		frame.setSize(625, 650);
		frame.setTitle("Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.add(checkerBoard);
		frame.setVisible(true);
	}

}
