package java101;

//TODO: Reformat
import java.util.Random;

/* Notes
 * When I return -1, print out that the array is clear.
 * Turn in homework earlier(On Monday Maybe?) 
 * Do array.clone by EnsureCapacity; using it overrides the previous int[].
 * Only add (arraySize != 0) condition if necessary.
 * WRITE CODE THE WAY YOU WOULD DO IT IN YOUR MIND
 * 
 */

/**
 * A int array that can be manipulated using the methods in this class. Its methods make it similar to an ArrayList, except this class is especially for integers.
 * @author Wesley
 */

public class IntArrays 
{
	private int arraySize;
	private int[] array;
	
	/**
	 * Constructs a new integer array with a default length of 10.
	 */
	public IntArrays()
	{
		arraySize = 0;
		array = new int[10];
	}
	
	/**
	 * Constructs a new int array with a length of initialCapacity.	
	 * @param initialCapacity The initial length of the array.
	 */
	
	public IntArrays(int initialCapacity)
	{
		arraySize = 0;
		array = new int[initialCapacity];
	}
	
	/**
	 * Returns the number at the specified value in the array. It will print out an error message if the array is empty or the index is out of bounds.
	 * @param index The index of the number to be printed.
	 * @return The number at the specified index in the array.
	 */
	public int get(int index)
	{
		if (arraySize != 0)
		{
			if (index > -1 && index < arraySize)
			{
				return array[index];
			}
			System.out.println("Syntax error, index is out of bounds.");
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
	}
	
	/**
	 * Returns the length of the array.	
	 * @return The length of the array as an integer.
	 */
	public int size()
	{
		return arraySize;
	}
	
	/**
	 * Used to check if an IntArrays object has a size of zero.
	 * @return Returns true when the array is empty, and false when the array is not empty.
	 */
	public boolean isEmpty()
	{
		return arraySize == 0;
	}
	
	/**
	 * Finds the smallest number in an array.
	 * @return The smallest number in the array.
	 */
	public int min()
	{
		if (arraySize != 0)
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
	 * Finds the biggest number in the array.
	 * @return The biggest number in the array.
	 */
	public int max()
	{
		if (arraySize != 0)
		{
			int maxNumber = array[0];
			for (int index = 0; index < arraySize; index++) {
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
	 * Finds the sum of the elements in the array.
	 * @return The sum of the elements in the array.
	 */
	public int sum()
	{
		if(arraySize != 0)
		{
			int sum = 0;
			for (int index = 0; index < arraySize; index++)
			{
				sum += array[index];
			}
			return sum;
			
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
		
	}
	/**
	 * Finds the average of the numbers in the array.
	 * @return The average of the numbers in the array.
	 */
	public double average()
	{
		if (arraySize != 0)
		{
			double sum = this.sum();
			return sum / arraySize;
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
	}	
	/**
	 * Adds a number at the end of the array; increases length if necessary.
	 * @param number The number you want to add in. 
	 * @return True when successful.
	 */
	
	public boolean add(int number)
	{
		if (arraySize < array.length)
		{
			array[arraySize] = number;			
		} else
		{
			this.ensureCapacity(array.length * 2);
			array[arraySize] = number;
		}
		arraySize++;
		return true;
		

	}
	/**
	 * Adds a number to the array at a certain index.
	 * @param inputIndex The index at which the number is inserted into.
	 * @param number The number that is to be inserted at the index.
	 * @return True if successful.
	 */
	
	public boolean add(int inputIndex, int number)
	{
		if (arraySize != 0)
		{
			if (inputIndex < arraySize)
			{
				if (arraySize < array.length)
				{
					arraySize++;
					for (int index = arraySize - 1; index > index; index--)
					{
						array[index] = array[index - 1];
					}
					array[inputIndex] = number;
					
				}
				else if (arraySize >= array.length)
				{
					arraySize++;
					this.ensureCapacity(array.length * 2);
					for (int index = arraySize - 1; index > inputIndex; index--)
					{
						array[index] = array[index - 1];
					}
								
				}
				return true;
			}
			
		}
		System.out.println("Syntax error, array is empty.");
		return false;
			
	}
	/**
	 * Removes an element at the specified index, and moves in elements on the right to fill the gap.
	 * @param removeIndex The index of the element that is to be removed.
	 * @return The removed element.
	 */
	public int removeByIndex(int removeIndex)
	{
		if (arraySize != 0)
		{
			if (removeIndex < array.length)
			{
				int element = array[removeIndex];
				for(int index = removeIndex + 1; index < arraySize; index++)
				{
					array[index - 1] = array[index];
				}
				arraySize--;
				return element;
			}	
		}
		System.out.println("Syntax error, array is empty.");
		return -1;
	
	}
	/**
	 * Removes the first occurrence of a number from the array.
	 * @param number The number which is to be removed
	 * @return True if the removal was successful, false if the number was not in the array.
	 */
	public boolean removeByNumber(int number)
	{
		if (arraySize != 0)
		{
			if (this.indexOf(number) != -1)
			{
				if (this.indexOf(number) == arraySize - 1)
				{
					arraySize--;
					return true;
				}
				else
				{
					for (int index = this.indexOf(number); index < arraySize - 1; index++)
					{
						array[index] = array[index + 1];
					}
					arraySize--;
					return true;
				}
			
			}	
		}
	return false;
	}
	/**
	 * Checks if the array has the given number as an element.
	 * @param number The number that is being checked for in the array.
	 * @return True if the number is in the array, false if the number is not in the array.
	 */
	public boolean contains(int number)
	{
		if (arraySize != 0)
		{
			for (int index = 0; index < arraySize; index++) 
			{
				if (array[index] == number)
				{
					return true;
				}
			}
		}
		return false;
		
	}
	/**
	 * Finds the index of the first occurrence of the given number.
	 * @param number The number that is searched for in the array.
	 * @return The index of the first occurrence of the number, or -1 if the number is not in the array.
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
	 * Finds the index of the last occurrence of the given number.
	 * @param number The number that is searched for in the array.
	 * @return The index of the last occurrence of the number, or -1 if the number is not in the array.
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
	 * Replaces the integer at the specified position in the array with the specified integer.
	 * @param index The index to set to the integer.
	 * @param integer The number to replace the current number in the index.
	 */
	//Problems
	public void set(int index, int integer)
	{
		if (arraySize != 0)
		{
			if (index >= 0 && index < arraySize)
			{
				array[index] = integer;
			}
		}
			
	}
	/**
	 * Removes all of the elements from the array.
	 */
	public void clear()
	{
		arraySize = 0;
	}
	/**
	 * Returns a new copy of the array.
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
	 * Returns a new array containing only a portion of this array starting with fromIndex (inclusive) to toIndex (exclusive).
	 * @param fromIndex Where the portion of the new array starts(inclusive).
	 * @param toIndex Where the portion of the new array ends(exclusive).
	 * @return The new array.
	 */
	
	public int[] subList(int fromIndex, int toIndex)
	{	
		if(arraySize != 0)
		{
			int[] newArray = new int[toIndex - fromIndex];
			for(int index = fromIndex; index < toIndex; index++)
			{
				newArray[index] = array[index];
			}
			return newArray;	
		}
		
		return array;
		
	}
	/**
	 * Increase the capacity of the array, if necessary, to ensure that it can hold at least the number of elements specified by the minimum capacity argument.
	 * @param minCapacity The minimum capacity that is used to see if the capacity of the array must be increased.
	 */
	public void ensureCapacity(int minCapacity)
	{
		if (arraySize != 0)
		{
			if (array.length < minCapacity)
			{
				int[] newArray = new int[minCapacity];
				for (int index = 0; index < array.length; index++)
				{
					newArray[index] = array[index];
				}
				array = newArray;
			}
		
		}
			
	}
	/**
	 * Swaps 2 elements of the array.
	 * @param index1 The index of the element that is going to be swapped.
	 * @param index2 The index of the element that will be swapped with index1.
	 * @return True if the swap is successful, and false if the swap is unsuccessful.
	 */
	public boolean swap(int index1, int index2)
	{
		if (arraySize != 0)
		{
			if (index1 >= 0 && index2 >= 0 && index1 < arraySize && index2 < arraySize)
			{ 
				int holder = array[index1];
				array[index1] = array[index2];
				array[index2] = holder;
				return true;
			}
		}	
	return false;
}
	/**
	 * Prints all the elements of an array, with commas separating the elements. 
	 */
	public void print()
	{
		//TODO: Fix
		if(arraySize != 0)
		{
			if (arraySize >= 1) 
			{
				System.out.print(array[0]);
			}

			for (int index = 1; index < arraySize; index++) 
			{ 
				System.out.print((", " + array[index]));
			}
			System.out.println(" ");
		}
	}
	/**
	 * Creates and returns a new array that is a numerically sorted version of the array. I accomplish this by comparing an element of an array to the one on its right: if it is in the right order, continue. If not, I swap them. 
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
	 * Numerically sorts an array.  I accomplish this by comparing an element of an array to the one on its right: if it is in the right order, continue. If not, I swap them. 
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
	 * Finds out if a number is present in a sorted array.
	 * @param number The number searched for in the array.
	 * @return True if the number is in the array, false if it is not in the array.
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
	 * Checks if an array is sorted.
	 * @return True if the array is sorted, false if the array is not sorted. 
	 */
	public boolean isSorted()
	{	
		int index = 0;
		while ((index < arraySize - 1))
		{
			if (array[index] < array[index])
			index++;
		}
		//TODO: Use for loop
		return (index == arraySize - 1);
	}
	/**
	 * Checks if the array is a random permutation. Assumes permutation is from 1 to arraySize.
	 * @return True if the array is a random permutation, false if it is not a random permutation. 
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
		//TODO: Check null and length
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
	 * Makes a random permutation from 1 to 10
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
	 * Makes a random permutation from the lower boundary to the upper boundary
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
	 * Merges two sorted array and creates a new array that merges them into a sorted array. 
	 * @param otherArray The array to be merged with the array in the object IntArrays.
	 * @return The new array. 
	 */
	public int[] merge(int[] otherArray)
	{
		int[] newArray = new int[array.length + otherArray.length];
	    int index = 0, secondIndex = 0, thirdIndex = 0;
	    while (index < array.length && secondIndex < otherArray.length)
	    {
	        if (array[index] < otherArray[secondIndex])
	        {
	            newArray[thirdIndex] = array[index];
	            index++;
	        }
	        else
	        {
	            newArray[thirdIndex] = otherArray[secondIndex];
	            secondIndex++;
	        }
	        thirdIndex++;
	    }

	    while (index < array.length)
	    {
	        newArray[thirdIndex] = array[index];
	        index++;
	        thirdIndex++;
	    }

	    while (secondIndex < otherArray.length)
	    {
	        newArray[thirdIndex] = otherArray[secondIndex];
	        secondIndex++;
	        thirdIndex++;
	    }

	    return newArray;
	}
		
}
	


//DO BINARY SORT FOR SEARCH 
//Hold on...do later


//WHY R THERE SO MANY BINARY THINGS ASK NEXT CLASS 1/30/2015
	

	


