package java101;

public class Product {
	private double price;
	
	private String name;
	
	private int quantity;

	
	public Product(String initialName, double initialPrice, int initialQuantity)
	{
		price = initialPrice;
		quantity = initialQuantity;
		name = initialName;
		
	}
	public Product(String initialName, double initialPrice)
	{
		price = initialPrice;
		name = initialName;
		quantity = 0;
	}
	public String getPrice()
	{
		return "$"+ price;
	}
	public double getQuantity()
	{
		return quantity;
	}
	public String getName()
	{
		return name;
	}
	public void reducePrice(int amount)
	{
		price = price - amount;
	}
	public void reducePricePercent(double amount)
	{
		price = price * (1 - amount);
	}
	public String totalCost()
	{
		return "$"+price * quantity;
	}
}