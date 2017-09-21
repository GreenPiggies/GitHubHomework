package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() 
	{
		size = 0;
		head = new LLNode<E>();
		tail = new LLNode<E>();
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null)
		{
			throw new NullPointerException();
		}
		LLNode newNode = new LLNode<E>(element);
		newNode.prev = tail.prev;
		newNode.next = tail;
		tail.prev.next = newNode;
		tail.prev = newNode;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		LLNode<E> node = head.next;
		if(index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index + 1; i++)
		{
			if (i == index)
			{
				return node.data;
			} else
			{
				node = node.next;
			}
		}
		return null;
	}
	
	public LLNode<E> getNode(int index) throws IndexOutOfBoundsException
	{
		LLNode<E> node = head.next;
		if (index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		for (int i = 0; i < index + 1; i++)
		{
			if (i == index)
			{
				return node;
			} else
			{
				node = node.next;
			}
		}
		return null;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		LLNode<E> prevNode = null;
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException();
		}
		if (element == null)
		{
			throw new NullPointerException();
		}
		if (index == 0)
		{
			prevNode = head;
		} else
		{
			prevNode = (getNode(index - 1)); 
		}
		LLNode<E> nextNode = null;
		if (index == size)
		{
			nextNode = tail;
		} else
		{
			nextNode = (getNode(index));
		}
		LLNode<E> newNode = new LLNode<E>(element);
		newNode.prev = prevNode;
		newNode.next = nextNode;
		prevNode.next = newNode;
		nextNode.prev = newNode;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index >= size || index < 0)
		{
			throw new IndexOutOfBoundsException();
		}
		E data = get(index);
		LLNode<E> prevNode = null;
		if (index - 1 < 0)
		{
			prevNode = head;
		} else
		{
			prevNode = (getNode(index - 1));
		}
		LLNode<E> nextNode = null;
		if (index + 1 > size())
		{
			nextNode = tail;
		} else
		{
			nextNode = (getNode(index + 1));
		}
		prevNode.next = nextNode;
		nextNode.prev = prevNode;
		size--;
		return data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
		if (element == null)
		{
			throw new NullPointerException();
		}
		E holder = getNode(index).data;
		getNode(index).data = element;
		return holder;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode()
	{
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	

}
