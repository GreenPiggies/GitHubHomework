package java101;

import java.awt.Rectangle;

public class AreaMeasurer implements Measurer 
{
	public double measure(Object something)
	{
		Rectangle rect = (Rectangle) something;
		double area = rect.getWidth() * rect.getHeight();
		return area;
	}

}
