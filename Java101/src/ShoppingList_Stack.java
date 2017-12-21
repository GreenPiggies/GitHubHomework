import java.util.ListIterator;
import java.util.Scanner;
import java.util.Stack;

public class ShoppingList_Stack 
{
	Stack<String> items;
	
	public ShoppingList_Stack()
	{
		items = new Stack<>();
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		while (items.size() > 0)
		{
			buff.append(items.pop() + " ");
		}
		return buff.toString();
	}
	
	public void inputItems()
	{
		System.out.println("Please enter an item you wish to add to your shopping cart, followed by an enter. (Enter \"quit\" to exit the program. )");
		System.out.print("> ");
		
		Scanner in = new Scanner(System.in);
		String item = in.nextLine();
		while (!item.toLowerCase().equals("quit"))
		{
			items.push(item);
			System.out.println("Please enter another item you wish to add to your shopping cart, followed by an enter. (Enter \"quit\" to exit the program. )");
			System.out.print("> ");
			item = in.nextLine();
		}
	}
}
