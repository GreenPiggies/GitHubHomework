package java101;

public class Sphere {
	
	double radius;
	
	public Sphere(double radius)
	{
		this.radius = radius;
	}
	public double getVolume()
	{
		return 4/3.0 * radius * radius * radius * Math.PI; 
	}
	public void setRadius(double newRadius)
	{
		radius = newRadius;
	}
	public double getRadius()
	{
		return radius;
	}
	
}
