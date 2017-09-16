package java101;

public class ProductPrinter {
	public static void main(String[] args){
		Product Computer = new Product("Computer", 500, 300);
		Product Mouse = new Product("Mouse", 10, 200);
		System.out.println("Product Name: " + Computer.getName());
		System.out.println("Price per unit: " + Computer.getPrice());		
		System.out.println("Quantity of unit: " + Computer.getQuantity());
		System.out.println("Product Name: " + Mouse.getName());
		System.out.println("Price per unit: " + Mouse.getPrice());		
		System.out.println("Quantity of unit: " + Mouse.getQuantity());
		Computer.reducePricePercent(0.25);
		Mouse.reducePricePercent(0.25);
		System.out.println("Computer total Cost = " + Computer.totalCost());
		System.out.println("Mouse total Cost = " + Mouse.totalCost());
	}
}
