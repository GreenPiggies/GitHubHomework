package java101;

public class Square {
	double width;

	public Square (double width)
	{
		this.width = width;
	}
	public double getArea() 
	{
		return width*width;
	}
	public void changeWidth(double newWidth)
	{
		width = newWidth;
	}
	
}
