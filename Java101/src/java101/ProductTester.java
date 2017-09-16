package java101;

public class ProductTester {
	public static void main(String[] args)
	{
		Product toaster = new Product("Toaster", 16, 11);
		System.out.println(toaster.getPrice());
		Product shoes = new Product("Shoes", 21, 5);
		System.out.println(toaster.totalCost());
		System.out.println(shoes.totalCost());

	}

}
