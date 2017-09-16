package java101;
import java.awt.Rectangle;

public class MeasurerTester 
{
	
	public static void main(String[] args)
	{
		Measurer areaMeas = new Measurer()
		{
			public double measure(Object something)
			{
				Rectangle rect = (Rectangle) something;
				double area = rect.getWidth() * rect.getHeight();
				return area;
			}

		};
		Rectangle rect1 = new Rectangle(5, 10, 20, 30);
		Rectangle rect2 = new Rectangle(10, 20, 30, 40);
		Rectangle rect3 = new Rectangle(20, 30, 5, 15);
		Rectangle[] rects = {rect1, rect2, rect3};

		double averageArea = Data.average(rects, areaMeas);
		System.out.println("Average area: " + averageArea); // Expect 625
	}
}
