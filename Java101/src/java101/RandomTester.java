package java101;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;



public class RandomTester 
{

	public static void main(String[] args) 
	{
		int[] array = {5, 7, 8, 9, 6, 10};
		IntArrays intArray = new IntArrays(array);
		intArray.add(11, 2);
		for (int index = 0; index < intArray.length; index++)
		{
			System.out.println(intArray[index] + "\t");
		}
		
	
		/*int bob = 9;
		if (bob == 9)
			bob = 8;
		
		int[] hey = {1, 5, 3, 7, 8};
		
		Arrays.sort(hey);
		
		for (int index = 0; index < hey.length; index++)
		{
			System.out.println(hey[index]);
		}
		*/
		
		
		
		
		
		/*
		String test = ".";
		System.out.println(test.toUpperCase());
		
		
		IntArrays array = new IntArrays(30);
		for (int index = 0; index < 30; index++)
		{
			array.add(2 * index + 1);
		}
		for (int index = 0; index < 30; index++)
		{
			System.out.println("Even: " + array.search(2 * index));
			System.out.println("Odd: " + array.search(2 * index + 1));
		}

		*/
		
		
		
		
	}

}
