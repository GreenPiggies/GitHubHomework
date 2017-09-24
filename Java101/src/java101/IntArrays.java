package java101;

import java.util.Random;

//TODO: Add comment: What is a permutation?
//TODO: Partially filled array with methods and stuff

/**
 * This class constructs a partially-filled int array. 
 * Methods here are used to get and set the properties of the partially-filled int array contained inside the IntArrays object.
 * The methods implemented in this class help the user add, remove, set, and perform multiple tasks with minimal effort.
 * 
 * @author Wesley
 */

public class IntArrays 
{
	private int arraySize;
	private int[] array;
	
	/**
	 * Constructs a new IntArrays object with a default capacity of 10. 
	 */
	public IntArrays()
	{
		this(10);
	}
	
	/**
	 * Constructs a new IntArrays object with a user-specified capacity.	
	 * @param initialCapacity The user-specified capacity.
	 */
	public IntArrays(int initialCapacity)
	{
		arraySize = 0;
		if (initialCapacity < 1)
		{
			array = new int[initialCapacity];
		} else
		{
			array = new int[10];
		}		
	}
	
	/**
	 * Returns the value at the specified value in the array. It will print out an error message and returning -1 if the IntArrays object is empty or the index is out of bounds.
	 * @param index The index of the value to be printed.
	 * @return The value at the specified index in the array.
	 */
	public int get(int index)
	{
		if (arraySize > 0)
		{
			if (index > -1 && index < arraySize)
			{
				return array[index];
			} else 
			{
				System.out.println("Syntax error, index is out of bounds.");
			}
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
	}
	
	/**
	 * Returns the number of values in the IntArrays object.
	 * @return The number of values in the IntArrays object as an integer.
	 */
	public int size()
	{
		return arraySize;
	}
	
	/**
	 * Returns true if the IntArrays object does not contain any elements.
	 * @return Returns true when the IntArrays object is empty, otherwise false.
	 */
	public boolean isEmpty()
	{
		return arraySize == 0;
	}
	
	/**
	 * Returns the smallest value in an IntArrays object.
	 * @return The smallest value in an IntArrays object as an integer.
	 */
	public int min()
	{
		if (arraySize > 0)
		{
			int minNumber = array[0];
			for (int index = 0; index < arraySize; index++) 
			{
				if (array[index] < minNumber)
				{
					minNumber = array[index];
				}
			}			
			return minNumber;
		}
		System.out.println("Syntax error, array is empty.");
		return -1;

	}
	/**
	 * Returns the biggest value in an IntArrays object.
	 * @return The biggest value in an IntArrays object as an integer.
	 */
	public int max()
	{
		if (arraySize > 0)
		{
			int maxNumber = array[0];
			for (int index = 0; index < arraySize; index++) 
			{
				if (array[index] > maxNumber)
				{
					maxNumber = array[index];
				}
			}			
			return maxNumber;
		}
		return -1;
		
	}
	/**
	 * Returns the sum of the values in the IntArrays object. 
	 * @return The sum of the values in the IntArrays object as an integer.
	 */
	public int sum()
	{
		int sum = 0;
		for (int index = 0; index < arraySize; index++)
		{
			sum += array[index];
		}
		return sum;
	}
	/**
	 * Returns the average of the values in the IntArrays object. It will print an error if the array is empty.
	 * @return The average of the values in the IntArrays object as an double.
	 */
	public double average()
	{
		if (arraySize > 0)
		{
			double sum = this.sum();
			return sum / arraySize;
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
	}	
	
	/**
	 * Adds a value in the IntArrays object; increases length if necessary.
	 * @param number The user-specified value added in. 
	 * @return True when successful.
	 */
	public boolean add(int number)
	{
		if (arraySize >= array.length)
		{
			this.ensureCapacity(array.length * 2 + 1);
		} 
		array[arraySize] = number;			
		arraySize++;
		return true;
	}
	//Moving 1 down :)
	/**
	 * Adds a value to the IntArrays object at a user-specified index. If the specified index is occupied, the elements present in or after the index are moved down to create space.
	 * @param inputIndex The index at which the value is inserted into.
	 * @param number The value that is to be inserted at the index.
	 * @return True if successful.
	 */
	public boolean add(int inputIndex, int number)
	{
		if (inputIndex < arraySize)
		{
			arraySize++;
			if (arraySize >= array.length)
			{
				this.ensureCapacity(array.length * 2 + 1);
			}
			for (int index = arraySize - 1; index > index; index--)
			{
				array[index] = array[index - 1];
			}
			array[inputIndex] = number;
			return true;
		}
		System.out.println("Syntax error, array is empty.");
		return false;
			
	}
	/**
	 * Removes and returns a value at the specified index. Any elements positioned after the removed element are shifted up 1 position.
	 * @param removeIndex The index of the value that is to be removed.
	 * @return The removed value.
	 */
	public int removeByIndex(int removeIndex)
	{
		if (removeIndex < arraySize)
		{
			int element = array[removeIndex];
			for (int index = removeIndex + 1; index < arraySize; index++)
			{
				array[index - 1] = array[index];
			}
			arraySize--;
			return element;
		}	
		System.out.println("Syntax error, array is empty.");
		return -1;
	
	}
	/**
	 * Removes the first occurrence of a value from the array. Any elements positioned after the removed element are shifted up 1 position.
	 * @param number The element which is to be removed
	 * @return True if the removal was successful, false if the element was not in the array.
	 */
	public boolean removeByNumber(int number)
	{
		if (this.indexOf(number) != -1)
		{
			if (this.indexOf(number) != arraySize - 1)
			{
				for (int index = this.indexOf(number); index < arraySize - 1; index++)
				{
					array[index] = array[index + 1];
				}
			}
			arraySize--;
			return true;
		
		}	
		return false;
	}
	/**
	 * Checks and returns if the IntArrays object has the given element.
	 * @param number The element that is checked in the IntArrays object.
	 * @return True if the element is in the array, false if the element is not in the array.
	 */
	public boolean contains(int number)
	{
		return indexOf(number) != -1 ? true : false;
		
	}
	/**
	 * Returns the index of the first occurrence of a user-specified value.
	 * @param number The value that is searched for in the IntArrays object.
	 * @return The index of the first occurrence of the value, or -1 if the value is not present.
	 */
	public int indexOf(int number)
	{
		for (int index = 0; index < arraySize; index++) 
		{
			if (array[index] == number)
			{
				return index;
			}
		}
		return -1;
	}
	/**
	 * Returns the index of the last occurrence of the a user-specified value.
	 * @param number The value that is searched for in the IntArrays object.
	 * @return The index of the last occurrence of the value, or -1 if the number is not in the IntArrays object.
	 */
	public int lastIndexOf(int number)
	{
		for (int index = arraySize - 1; index >= 0; index--)
		{
			if (array[index] == number)
			{
				return index;
			}
		}
		return -1;
	}
	/**
	 * Replaces the value at the specified index in the IntArrays object with the specified value.
	 * @param index The index locations whose element will be set to the user-specified value.
	 * @param integer The value to replace the current value in the index.
	 * @return True if successful, false if not.
	 */
	public boolean set(int index, int integer)
	{
		if (index >= 0 && index < arraySize)
		{
			array[index] = integer;
			return true;
		}
		return false;
			
	}
	/**
	 * Removes all of the elements from the IntArrays object.
	 */
	public void clear()
	{
		arraySize = 0;
	}
	/**
	 * Returns a copy of the IntArrays object as an integer array.
	 */
	public int[] clone()
	{	
		int[] newArray = new int[array.length];
		for (int index = 0; index < arraySize; index++)
		{
			newArray[index] = array[index];
		}
		return newArray;
	}
	
	/**
	 * Returns a new array containing only a portion of this IntArrays object starting with fromIndex (inclusive) to toIndex (exclusive).
	 * @param fromIndex Where the portion of the new array starts (inclusive).
	 * @param toIndex Where the portion of the new array ends (exclusive).
	 * @return The new array.
	 */
	public int[] subList(int fromIndex, int toIndex)
	{	
		if (arraySize != 0)
		{
			int[] newArray = new int[toIndex - fromIndex];
			for (int index = fromIndex; index < toIndex; index++)
			{
				newArray[index] = array[index];
			}
			return newArray;	
		}
		
		return array;
		
	}
	/**
	 * Increase the capacity of the IntArrays object, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
	 * @param minCapacity The minimum capacity that is used to see if the capacity of the IntArrays object must be increased.
	 */
	public void ensureCapacity(int minCapacity)
	{
		if (array.length < minCapacity)
		{
			int[] newArray = new int[minCapacity];
			for (int index = 0; index < arraySize; index++)
			{
				newArray[index] = array[index];
			}
			array = newArray;
		}
			
	}
	/**
	 * Swaps 2 elements in the IntArrays object.
	 * @param index1 The index of the element that is going to be swapped.
	 * @param index2 The index of the element that will be swapped with index1.
	 * @return True if the swap is successful, and false if the swap is unsuccessful.
	 */
	public boolean swap(int index1, int index2)
	{
		if (index1 >= 0 && index2 >= 0 && index1 < arraySize && index2 < arraySize)
		{ 
			int holder = array[index1];
			array[index1] = array[index2];
			array[index2] = holder;
			return true;
		}	
		return false;
	}
	/**
	 * Prints all the elements of an array, with commas separating the elements. 
	 */
	public void print()
	{
		StringBuffer print = new StringBuffer();
		if (arraySize != 0)
		{
			for (int index = 0; index < arraySize - 1; index++) 
			{ 
				print.append(array[index] + ", ");
			}
			print.append(array[arraySize - 1]);
			System.out.println(print.toString());
		}
	}
	/**
	 * Creates and returns a new array that is a numerically sorted interpretation of the IntArrays object. I accomplish this by comparing an element of an array to the ones after it: if it is in the right order, continue. If not, I swap them. 
	 */
	public int[] oldSorted()
	{
		int[] newArray = array.clone();
		for (int index = 0; index < arraySize; index++) 
		{
			for (int secondIndex = index + 1; secondIndex < arraySize; secondIndex++) 
			{
				if (newArray[index] > newArray[secondIndex]) 
				{
					int temp = newArray[index];
					newArray[index] = newArray[secondIndex];
					newArray[secondIndex] = temp;
				}
			}
		}
		return newArray;
		
	}
	/**
	 * Numerically sorts an IntArrays object. I accomplish this by comparing an element of an array to the ones after: if it is in the right order, continue. If not, I swap them. 
	 */
	public void oldSort()
	{
		for (int index = 0; index < arraySize; index++) {
			for (int secondIndex = index + 1; secondIndex < arraySize; secondIndex++) {
				int temp = 0;
				if (array[index] > array[secondIndex]) {
					temp = array[index];
					array[index] = array[secondIndex];
					array[secondIndex] = temp;
				}
			}
		}
		
	}
	/**
	 * Finds and returns if a number is present in a sorted array. It uses a binary search method that divides the array in half again and again to search for the given number.
	 * @param number The value searched for in the array.
	 * @return True if the value is in the array, false if it is not in the array.
	 */
	public boolean search(int number)
	{
		int left = 0;
		int right = arraySize - 1;
		while (right >= left)
		{
			int middle = (left + right) / 2;
			if (array[middle] < number)
			{
				left = middle + 1;
			} else if (array[middle] > number)
			{
				right = middle - 1;
			} else
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * Checks and returns if the IntArrays object is sorted.
	 * @return True if the IntArrays object is sorted, false if it is not sorted. 
	 */
	public boolean isSorted()
	{	
		for (int index = 0; index < arraySize - 1; index++)
		{
			if (array[index] > array[index + 1])
			{
				return false;
			}
		}
		return true;
		
	}
	/**
	 * Checks if the IntArrays object is a random permutation. Assumes permutation is from 1 to arraySize.
	 * @return True if the IntArrays object is a random permutation, false if it is not a random permutation. 
	 */
	public boolean isRandomPerm()
	{
		boolean[] bins = new boolean[arraySize];
		for (int index = 0; index < arraySize; index++)
		{
			bins[index] = false;
		}
		for (int index = 0; index < arraySize; index++)
		{
			if (bins[array[index] - 1])
			{
				return false;
			} 
			bins[array[index] - 1] = true;
		}
		return true;
	}
	
	/**
	 * Checks if an integer array is a permutation.
	 * @param inputArray The array to be checked.
	 * @return True if inputArray is a permutation, false if not.
	 */
	public static boolean isPermutation(int[] inputArray)
	{
		if (inputArray == null || inputArray.length == 0)
		{
			return false;
		}
		int min = inputArray[0];
		int max = inputArray[0]; 
		boolean[] bins = new boolean[inputArray.length];
		for (int index = 0; index < inputArray.length; index++)
		{
			bins[index] = false;
			if (inputArray[index] < min)
			{
				min = inputArray[index];
			}
			if (inputArray[index] > max)
			{
				max = inputArray[index];
			}
		}
		
		if (max - min != inputArray.length - 1) //Means the number of #s between min and max do not correspond to the inputArray size; it could never be a permutation.
		{
			return false;
		} 
		
		for (int index = 0; index < inputArray.length; index++)
		{
			int compareIndex = inputArray[index] - min;
			if (bins[compareIndex])
			{
				return false;
			} 
			bins[compareIndex] = true;
		}
		return true;
	}
	
	/**
	 * Returns a random permutation from 1 to 10.
	 * @return The random permutation in the form of an int[] array.
	 */
	public static int[] permutation()
	{
		int[] permutation = new int[10];
		Random random = new Random();
		for (int index = 0; index < permutation.length; index++)
		{
			permutation[index] = index + 1;
		}
		for (int index = 0; index < permutation.length; index++)
		{
			int randomIndex = random.nextInt(9);
			int holder = permutation[randomIndex];
			permutation[randomIndex] = permutation[index];
			permutation[index] = holder;
		}
		return permutation;
	}
	
	/**
	 * Makes a random permutation from the lower boundary to the upper boundary.
	 * @param lower The lower boundary of the permutation.
	 * @param upper The upper boundary of the permutation.
	 * @return The random permutation in the form of an int[] array.
	 */
	public static int[] permutation(int lower, int upper)
	{
		int[] permutation = new int[upper - lower];
		Random random = new Random();
		for (int index = 0; index < permutation.length; index++)
		{
			permutation[index] = index + (lower);
		}
		for (int index = 0; index < permutation.length; index++)
		{
			int randomIndex = random.nextInt(upper - lower);
			int holder = permutation[randomIndex];
			permutation[randomIndex] = permutation[index];
			permutation[index] = holder;
		}
		return permutation;
	}

	/**
	 * Merges two sorted arrays into a new sorted integer array. 
	 * @param otherArray The array to be merged with the array interpretation of the object IntArrays.
	 * @return The new array. 
	 */
	public int[] merge(int[] otherArray)
	{
		int[] newArray = new int[array.length + otherArray.length];
	    int arrayIndex = 0, otherIndex = 0, thirdIndex = 0;
	    while (arrayIndex < array.length && otherIndex < otherArray.length)
	    {
	        if (array[arrayIndex] < otherArray[otherIndex])
	        {
	            newArray[thirdIndex] = array[arrayIndex];
	            arrayIndex++;
	        } else
	        {
	            newArray[thirdIndex] = otherArray[otherIndex];
	            otherIndex++;
	        }
	        thirdIndex++;
	    }

	    while (arrayIndex < array.length)
	    {
	        newArray[thirdIndex] = array[arrayIndex];
	        arrayIndex++;
	        thirdIndex++;
	    }

	    while (otherIndex < otherArray.length)
	    {
	        newArray[thirdIndex] = otherArray[otherIndex];
	        otherIndex++;
	        thirdIndex++;
	    }

	    return newArray;
	}
		
}
	


	


