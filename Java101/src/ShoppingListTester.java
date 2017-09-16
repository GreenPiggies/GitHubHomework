
public class ShoppingListTester 
{
	public static void main(String[] args)
	{
		ShoppingList cart = new ShoppingList(10);
		cart.inputItems();
		System.out.println(cart.toString());
		
	}
}
