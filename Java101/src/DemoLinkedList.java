import java.util.LinkedList;

import java.util.ListIterator;

public class DemoLinkedList 
{
	public static void main(String[] args)
	{
		LinkedList<String> names = new LinkedList<>();
		names.addLast("Harry");						//Adds an element to the end of the list. (Same as add)
		names.addFirst("Sally");					//Adds an element to the beginning of the list. (names now contains [Sally, Harry])
		System.out.println(names.getFirst());							//Gets the element stored at the beginning of the list. ("Sally")
		System.out.println(names.getLast());							//Gets the element stored at the end of the list. ("Harry")
		String firstItem = names.removeFirst();		//Removes the first element of the list and returns it. (firstItem is "Sally", and names is ["Harry"])
		String lastItem = names.removeLast();		//Removes the last element of the list and returns it. (lastItem is "Harry", and names is an empty list)
		
		System.out.println(firstItem);
		System.out.println(lastItem);
		
		names.addFirst("Paul");
		names.addFirst("John");
		names.addFirst("Ringo");
		
		ListIterator<String> iterator = names.listIterator();
		String whoami;

		while (iterator.hasNext())
		{
			String name = iterator.next();
			System.out.println(name);
		}
		
		iterator = names.listIterator();
		whoami = iterator.next();
		System.out.println("Remove: " + whoami);
		iterator.remove();
		
		whoami = iterator.next();
		System.out.println("Set: " + whoami);
		iterator.set("Diane");
		iterator.set("Steve");
		for (String name : names)
		{
			System.out.println(name);
		}

	}
}
