import java.awt.Graphics;

import javax.swing.JFrame;
public class CheckerViewer
{
	public static void main(String[] args) 
	{
		CheckerFrame frame = new CheckerFrame();
		frame.setTitle("Animated Checkers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
