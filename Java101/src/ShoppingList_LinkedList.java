import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class ShoppingList_LinkedList 
{
	LinkedList<String> items;
	
	public ShoppingList_LinkedList()
	{
		items = new LinkedList<>();
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		ListIterator<String> iterator = items.listIterator();
		while (iterator.hasNext())
		{
			buff.append(iterator.next() + "\t");
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
			items.addLast(item);
			System.out.println("Please enter another item you wish to add to your shopping cart, followed by an enter. (Enter \"quit\" to exit the program. )");
			System.out.print("> ");
			item = in.nextLine();
		}
		
	}
}
