package java101;
import java.util.Random;
public class RandomPerm 
{
	//Write a program that produces a random permutation of the numbers 1 to 10. 
	//To generate a random permutation, you need to fill any array with the numbers one to 10 
	//so that no two entries of the array have the same contents and each number is only used once as an entry in the array. 
	//You can do this by brute force, by generating random values until you have a value that is not yet in the array. 
	//But that is highly inefficient. Develop a better algorithm to use in your program.
	public static void main(String args[])
	{
		//Random perm = new Random();
		//System.out.println(perm.nextInt(6) + 1);
		int number = 0;
		int index;
		if (number < 0)
		{
			System.out.println("This number is neither composite nor prime because it is negative!");
		} else if (number == 0 || number == 1)
		{
			System.out.println("This number is neither composite nor prime because its only factors are itself!");
		}
		for (index = 2; index < Math.abs(number); index++)
		{
			if (number % index == 0)
			{
				System.out.println("Composite! It is divisible by " + index + " and " + number / index + ".");
				break;
			}
		}
		if (index == number)
		{
			System.out.println("Prime!");
		}
		
	} 
	/*
	 * Create a new array, write the number in sequence, use nextInt() and remove the number which is at that index, decrement the index when necessary.
	 */
}
