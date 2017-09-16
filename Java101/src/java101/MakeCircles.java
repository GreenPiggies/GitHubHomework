package java101;

public class MakeCircles {
	public static void main(String[] args) {
		Circle big = new Circle(10.00);
		Circle medium = new Circle(7.50);
		Circle small = new Circle(5.00);
		System.out.println(big.getArea());
		System.out.println(big.getCircumference());
		System.out.println(medium.getArea());
		System.out.println(medium.getCircumference());
		System.out.println(small.getArea());
		System.out.println(small.getCircumference());

	}
}
