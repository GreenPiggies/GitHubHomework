import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingList_Arrays 
{

	String[] items;
	int size = 0;
	public static final int DEFAULT_SIZE = 10;
	
	public ShoppingList_Arrays(int arraySize)
	{
		items = new String[arraySize];
	}
	
	public ShoppingList_Arrays()
	{
		items = new String[DEFAULT_SIZE];
	}
	
	public void ensureCapacity(int minCapacity)
	{
		if (items.length < minCapacity)
		{
			String[] newArray = new String[minCapacity * 2];
			for (int index = 0; index < size; index++)
			{
				newArray[index] = items[index];
			}
			items = newArray;
		}
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		for (int index = size - 1; index > 0; index--)
		{
			buff.append(items[index]);
			buff.append(", ");
		}
		buff.append(items[0]);
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
			ensureCapacity(size + 1);
			items[size] = item;
			size++;
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
				ensureCapacity(size + 1);
				items[size] = item;
				size++;
			}
		} while (!quit);
	}

}
