package java101; 
//TODO: LINE SPACING
import java.util.Random;
/**
 * This class constructs a partially-filled int array. 
 * Methods here are used to get and set the properties of the partially-filled 
 * int array contained inside the IntArrays object.
 * The methods implemented in this class help the user add, remove, set, and 
 * perform multiple tasks with minimal effort.
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
	 * If the user-specified capacity is invalid (less than 1), the 
	 * capacity is set to a default of 10.
	 * @param initialCapacity The user-specified capacity.
	 */
	public IntArrays(int initialCapacity)
	{
		arraySize = 0;
		if (initialCapacity > 0)
		{
			array = new int[initialCapacity];
		} else
		{
			array = new int[10];
		}		
	}
	
	/**
	 * Returns the value at the specified index in the IntArrays object. 
	 * It will print out an error message and return -1 if the IntArrays 
	 * object is empty or the index is out of bounds.
	 * @param index The index of the value to be printed.
	 * @return The value at the specified index in the IntArrays object.
	 */
	public int get(int index)
	{
		if (index > -1 && index < arraySize)
		{
			return array[index];
		} else 
		{
			System.out.println("Syntax error.");
		}		
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
	 * Returns true if the IntArrays object does not contain any values.
	 * @return Returns true when the IntArrays object is empty, otherwise false.
	 */
	public boolean isEmpty()
	{
		return arraySize == 0;
	}
	
	/**
	 * Returns the smallest value in an IntArrays object. 
	 * If the array is empty, it returns -1;
	 * @return The smallest value in an IntArrays object as an integer. 
	 * If the array is empty, it returns -1;
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
	 * If the array is empty, it returns -1;
	 * @return The biggest value in an IntArrays object as an integer. 
	 * If the array is empty, it returns -1;
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
	 * Returns the average of the values in the IntArrays object. It will print 
	 * an error and return -1 if the IntArrays object is empty.
	 * @return The average of the values in the IntArrays object as an double. 
	 * If the array is empty, it returns -1;
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
	 * Appends a value to the end in the IntArrays object; increases length
	 * if necessary.
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
	
	/**
	 * Adds a value to the IntArrays object at a user-specified index. 
	 * The values present in or after the index are shifted down to create space.
	 * @param inputIndex The index at which the value is inserted into.
	 * @param number The value that is to be inserted at the index.
	 * @return True if successful.
	 */
	public boolean add(int inputIndex, int number) 
	{
		if (inputIndex < arraySize)
		{
			if (arraySize >= array.length)
			{
				this.ensureCapacity(array.length * 2 + 1);
			}
			for (int index = arraySize; index > inputIndex; index--)
			{
				array[index] = array[index - 1];
			}
			array[inputIndex] = number;
			arraySize++;
			return true;
		}
		System.out.println("Syntax error, array is empty.");
		return false;
			
	}
	
	/**
	 * Removes and returns a value at the specified index. Any values 
	 * positioned after the removed value are shifted up 1 position. 
	 * Returns -1 if index is out of bounds.
	 * @param removeIndex The index of the value that is to be removed. 
	 * Returns -1 if index is out of bounds.
	 * @return The removed value.
	 */
	public int removeByIndex(int removeIndex) 
	{
		if (removeIndex < arraySize && removeIndex > -1)
		{
			int element = array[removeIndex];
			for (int index = removeIndex + 1; index < arraySize; index++)
			{
				array[index - 1] = array[index];
			}
			arraySize--;
			return element;
		}	
		System.out.println("Syntax error, index specified is greater than or equal to array size.");
		return -1;
	
	}
	
	/**
	 * Removes the first occurrence of a value from the IntArrays object. Any values positioned
	 * after the removed value are shifted up 1 position. Returns false if the value was not found.
	 * @param number The value to be removed.
	 * @return True if the removal was successful, false if the value was not found.
	 */
	public boolean removeByNumber(int number)
	{
		return removeByIndex(indexOf(number)) != -1;
	}
	
	/**
	 * Checks and returns if the IntArrays object has the given value.
	 * @param number The value that is checked in the IntArrays object.
	 * @return True if the value is in the array, false if the value is not in the array.
	 */
	public boolean contains(int number)
	{
		return indexOf(number) != -1;
	}
	
	/**
	 * Returns the index of the first occurrence of an user-specified value.
	 * Returns -1 when the user-specified value is not found.
	 * @param number The value that is searched for in the IntArrays object.
	 * @return The index of the first occurrence of the value, or -1 if the value 
	 * is not present.
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
	 * Returns the index of the last occurrence of an user-specified value. 
	 * Returns -1 when the user-specified value is not found.
	 * @param number The value that is searched for in the IntArrays object.
	 * @return The index of the last occurrence of the value, or -1 if the number 
	 * is not in the IntArrays object.
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
	 * Replaces the value at the specified index in the IntArrays object with an 
	 * user-specified value. Returns false if the index is out of bounds.
	 * @param index The index locations whose element will be set to the user-specified 
	 * value.
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
	 * Removes all of the values from the IntArrays object.
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
	 * Returns a new array containing only a portion of this IntArrays object 
	 * starting with fromIndex (inclusive) to toIndex (exclusive).
	 * @param fromIndex Where the portion of the new array starts (inclusive).
	 * @param toIndex Where the portion of the new array ends (exclusive).
	 * @return The new array.
	 */
	public int[] subList(int fromIndex, int toIndex)
	{	
		if (fromIndex > -1 && toIndex < arraySize && fromIndex < toIndex)
		{
			int[] newArray = new int[toIndex - fromIndex];
			for (int index = fromIndex; index < toIndex; index++)
			{
				newArray[index - fromIndex] = array[index];
			}
			return newArray;	
		}
		
		return null;
		
	}
	
	/**
	 * Increase the capacity of the IntArrays object, if necessary, to ensure 
	 * that it can hold at least the number of values specified by the minimum 
	 * capacity argument.
	 * @param minCapacity The minimum capacity that is used to see if the capacity 
	 * of the IntArrays object must be increased.
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
	 * Swaps 2 values in the IntArrays object. 
	 * Returns false if either one of the user-specified indexes are out of bounds.
	 * @param index1 The index of the values that is going to be swapped.
	 * @param index2 The index of the values that will be swapped with index1.
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
	 * Prints all the values of an array, with commas separating the values. 
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
	 * Creates and returns a new array that is a numerically sorted interpretation 
	 * of the IntArrays object. 
	 * I accomplish this by comparing an value of an array to the ones after it: 
	 * If it is in the right order, continue. If not, I swap them. 
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
	 * Sorts an IntArrays object in a numerically increasing order. 
	 * I accomplish this by comparing an value of an array to the ones after: 
	 * if it is in the right order, continue. If not, I swap them. 
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
	 * Finds and returns if a value is present in a sorted IntArrays object. 
	 * It uses a binary search method that divides the IntArrays object in 
	 * half again and again to search for the given value.
	 * @param number The value searched for in the IntArrays object.
	 * @return True if the value is in the IntArrays object,
	 * false if it is not in the IntArrays object.
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
	 * Checks if the IntArrays object is a random permutation. 
	 * Assumes permutation is from 1 to arraySize.
	 * A permutation is an arrangement of integers between a lower and upper boundary, 
	 * inclusive. Only one of each number is present.
	 * @return True if the IntArrays object is a random permutation, 
	 * false if it is not a random permutation. 
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
	 * A permutation is an arrangement of integers between a lower and upper boundary, 
	 * inclusive. Only one of each number is present.
	 * @param inputArray The array to be checked.
	 * @return True if inputArray is a permutation, false if not.
	 */
	public static boolean isPermutation(int[] inputArray)
	{
		//boolean isPerm = true;
		if (inputArray != null && inputArray.length > 0) 
		{
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
			if (max - min == inputArray.length - 1)
			{
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
		}
		return false;
	}
	
	/**
	 * Returns a random permutation from 1 to 10.
	 * A permutation is an arrangement of numbers such that there is only one of
	 * each number from the smallest to the greatest number. 
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
			int randomIndex = random.nextInt(10);
			int holder = permutation[randomIndex];
			permutation[randomIndex] = permutation[index];
			permutation[index] = holder;
		}
		return permutation;
	}
	
	/**
	 * Makes a random permutation from the lower boundary to the upper boundary.
	 * Returns null if the lower and upper bounds are valid.
	 * A permutation is an arrangement of numbers such that there is only one 
	 * of each number from the smallest to the greatest number. 
	 * @param lower The lower boundary of the permutation.
	 * @param upper The upper boundary of the permutation.
	 * @return The random permutation in the form of an int[] array.
	 */
	public static int[] permutation(int lower, int upper)
	{
		int[] permutation = null;
		if (upper - lower > 0)
		{
			permutation = new int[upper - lower];
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
		}
		return permutation;
	}

	/**
	 * Merges a sorted array with our IntArrays object to create a new merged array.
	 * Returns null if the user-specified array is null.
	 * @param otherArray The array to be merged with the IntArrays object.
	 * @return The merged array as an array of integers. 
	 */
	public int[] merge(int[] otherArray)
	{
		int[] newArray = null;
		if (otherArray != null)
		{
			newArray = new int[array.length + otherArray.length];
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
		}
	    return newArray;
	}
	
	public static void main(String[] args)
	{
		int[] array = {5, 7, 8, 9, 6, 10};
		IntArrays intArray = new IntArrays();
		intArray.add(5);
		intArray.add(7);
		intArray.add(8);
		intArray.add(9);
		intArray.add(6);
		intArray.add(10);
		for (int index = 0; index < intArray.size(); index++)
		{
			System.out.print(intArray.get(index) + "\t");
		}
		intArray.add(2, 11);
		System.out.println();
		for (int index = 0; index < intArray.size(); index++)
		{
			System.out.print(intArray.get(index) + "\t");
		}
		intArray.removeByIndex(3);
		System.out.println();
		for (int index = 0; index < intArray.size(); index++)
		{
			System.out.print(intArray.get(index) + "\t");
		}
	}
		
}
	


	


