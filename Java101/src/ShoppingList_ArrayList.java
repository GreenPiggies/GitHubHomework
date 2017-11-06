import java.util.ArrayList;
import java.util.Scanner;
//Prompt every single time... .-.

//Resubmit
public class ShoppingList_ArrayList 
{
	ArrayList<String> items;
	
	public ShoppingList_ArrayList(int arraySize)
	{
		items = new ArrayList<>(arraySize);
	}
	
	public ShoppingList_ArrayList()
	{
		items = new ArrayList<>();
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		for (int index = items.size() - 1; index > 0; index--)
		{
			buff.append(items.get(index));
			buff.append(", ");
		}
		buff.append(items.get(0));
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
	
	public void inputItemsAlt()
	{
		Scanner in = new Scanner(System.in);
		String item;
		boolean quit;
		do
		{
			System.out.println("Please enter an item you wish to add to your shopping cart, followed by an enter. (Enter \"quit\" to exit the program. )");
			System.out.print("> ");
			item = in.nextLine();
			quit = item.toLowerCase().equals("quit");
			if (!quit)
			{
				items.add(item);
			}
		} while (!quit);
	}

}
