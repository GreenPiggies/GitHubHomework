import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
//TODO: RESUBMIT
public class RandomMethodTest {
	
	public static void main(String[] args)
	{
		/*System.out.println(findBiggest(2, 3, 5));
		System.out.println(findBiggest(3, 2, 5));
		System.out.println(findBiggest(5, 2, 3));
		System.out.println(findBiggest(2, 5, 3));
		System.out.println(findBiggest(5, 3, 2));
		System.out.println(findBiggest(3, 5, 2));
		*/

		String apple = "apple";
		String bat = "bat";
		String cat = "cat";
		lexicographicOrder(apple, bat, cat);
		lexicographicOrder(apple, cat, bat);
		lexicographicOrder(bat, apple, cat);
		lexicographicOrder(bat, cat, apple);
		lexicographicOrder(cat, apple, bat);
		lexicographicOrder(cat, bat, apple);




		
	    /*(try
	    {
	        Scanner in = new Scanner(new File("sample2.txt"));
	        int value = in.nextInt();
	        System.out.println(value);
	        in.close();
	    } catch (IOException exception)
	    {
	        System.out.println(exception);
	    } */
	}
	
	public static void writeAll(String[] lines, String filename)
	{
	    try (PrintWriter out = new PrintWriter(filename)) 
	    {
	        for (String line : lines)
	        {
	            out.println(line.toUpperCase());
	        }
	    } catch (FileNotFoundException exception)
	    {
	        System.err.println("File Not Found Exception Error -- " + exception);
	    }
	}
	
	public static double findBiggest(double first, double second, double third)
	{
		double biggest = first;
		if (second > biggest)
		{
			biggest = second;
		} 
		if (third > biggest)
		{
			biggest = third;
		}
		return biggest;
		
	}
	
	
	//maybe think about it?
	//void, prints instead of returning
	public static void lexicographicOrder(String str1, String str2, String str3)
	{
		String first = str1;
	    String second = str2;
	    String third = str3;
	    if (first.compareTo(second) > 0) //3, 5, 6
	    {
	    	second = first;
	    	first = str2;
	    } 
	    if (second.compareTo(third) > 0) //2, 4, 5, 6
	    {
	    	third = second;
	    	second = str3;
	    	if (first.compareTo(second) > 0)//4, 6
		    {
		    	second = first;
		    	first = str3;
		    }
	    }
	    /*
	     * 1) a b c * false false false
	     * 2) a c b * false true false
	     * 3) b a c * true false false
	     * 4) b c a * false true true
	     * 5) c a b * true true false
	     * 6) c b a * true true true
	     * 
	     * 1) false false
	     * 2) false true
	     * 3) true false
	     * 4) true true*/
	    System.out.println(first + ", " + second + ", " + third);
	}
	    public int firstSpace(String str)
	    {
	        for(int i = 0; i < str.length(); i++)
	        {
	        	char c = str.charAt(i);
	        	if(c == ' ')
	        	{
	        		return i;
	        	}
	        }
	        return -1;
	    }
	    public int lastSpace(String str)
	    {
	        for(int i = str.length() - 1; i > 0; i--)
	        {
	            char c = str.charAt(i);
	            if(c == ' ')
	    	    {
	    	        return i;
	    	    }
	        }
	    	return -1;
	    }
	    public int findSpaces(String str)
	    {
            int numSpaces = 0;
	        for(int i = 0; i < str.length(); i++)
	        {
	            char c = str.charAt(i);
	            if(c == ' ')
	            {
	    	        numSpaces++;
	    	    }
	        }
	    	if(numSpaces > 0)
	    	{
	    		return numSpaces;
	    	} else
	    	{
	    		return -1;
	    	}
	    }
	    public int findSentences(String str)
	    {
            int numSentence = 0;
	        for(int i = 0; i < str.length(); i++)
	        {
	            char c = str.charAt(i);
	            if(i + 2 < str.length())
	            {
	            	if((c == '.' || c == '?' || c == '!') && (Character.isUpperCase(str.charAt(i + 2)) || str.charAt(i + 2) == ' '))
	            	{
	            		numSentence++;
	            	}
	            } else if(c == '.' || c == '?' || c == '!')
	            {
	            	numSentence++;
	            }
	        }
	    	if(numSentence > 0)
	    	{
	    		return numSentence;
	    	} else
	    	{
	    		return -1;
	    	}
	    }

	    public String secondMax(int[] nums)
	    {
	    	int[] numsCopy = nums;
	    	int max = Integer.MIN_VALUE;
	    	int secondMax = Integer.MIN_VALUE;
	    	int secondMaxIndex = 0;
	    	for (int i = 0; i < numsCopy.length; i++)
	    	{
	    		if (numsCopy[i] > max)
	    		{
	    			secondMax = max;
	    			secondMaxIndex = i;
	    			max = numsCopy[i];
	    		}
	    		else if (numsCopy[i] > secondMax)
	    		{
	    			secondMax = numsCopy[i];
	    			secondMaxIndex = i;
	    		}
	    		else
	    		{
	    			// Do nothing
	    		}
	    	}
	    	return "The second largest number in this array is " + secondMax + " and its index is " + secondMaxIndex + "!";
	    }
}
