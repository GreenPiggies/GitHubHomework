/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		//65, 21, 42
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		try {
			a = list1.remove(55);
			fail("Index out of bounds");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		
		try {
			a = list1.remove(-1);
			fail("Index out of bounds");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		boolean a = list1.add(0);
		assertEquals("Add: check a is correct ", true, a);
		assertEquals("Add: check element 3 is correct ", (Integer)0, list1.get(3));
		assertEquals("Remove: check size is correct ", 4, list1.size());
		try
		{
			list1.add(null);
			fail("Null pointer.");
		} catch (NullPointerException exception)
		{
			
		}
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("Size: check if size is correct", shortList.size(), 2);
		assertEquals("Size: check if size is correct", list1.size(), 3);
		assertEquals("Size: check if size is correct", longerList.size(), LONG_LIST_LENGTH);
		assertEquals("Size: check if size is correct", emptyList.size(), 0);
		// TODO: implement this test
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		list1.add(1, 0);
		assertEquals("AddIndex: Check index 1 is 0", list1.get(1), (Integer)0);
		assertEquals("AddIndex: Check size is 4", list1.size(), 4);
		try
		{
			list1.add(-1, 0);
			fail("Index out of bounds.");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		
		try
		{
			list1.add(list1.size() + 5, 0);
			fail("Index out of bounds.");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		
		try
		{
			list1.add(0, null);
			fail("Null pointer.");
		} catch (NullPointerException exception)
		{
			
		}
		for (int i = 0; i < list1.size(); i++)
		{
			System.out.print(list1.get(i) + "\t");
		}
		assertEquals("AddIndex: Check index 2 is 21", list1.get(2), (Integer)21);

        // TODO: implement this test
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		list1.set(1, 0);
		assertEquals("AddIndex: Check index 1 is 0", list1.get(1), (Integer)0);
		try 
		{
			list1.set(-1, 0);
			fail("Index out of bounds.");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		
		try 
		{
			list1.set(555555555, 0);
			fail("Index out of bounds.");
		} catch (IndexOutOfBoundsException exception)
		{
			
		}
		
		try
		{
			list1.set(0, null);
			fail("Null pointer.");
		} catch (NullPointerException exception)
		{
			
		}
	    // TODO: implement this test
	    
	}
	
	
	// TODO: Optionally add more test methods.
	
}
