import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

public class ShoppingList_Queue 
{
	Queue<String> items;
	
	public ShoppingList_Queue()
	{
		items = new LinkedList<>();
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		while (items.size() > 0)
		{
			buff.append(items.remove());
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
			items.add(item);
			System.out.println("Please enter another item you wish to add to your shopping cart, followed by an enter. (Enter \"quit\" to exit the program. )");
			System.out.print("> ");
			item = in.nextLine();
		}
	}
}
