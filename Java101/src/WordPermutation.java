
import java.util.ArrayList;
import java.util.List;

public class WordPermutation 
{
	public static void main(String[] args)
	{
		System.out.println(iterativePermutation("beat"));
	}
	
	private static List<String> iterativePermutation(String str)
	{
		List<String> permutations = new ArrayList<String>();
		if (str == null || str.length() <= 0)
		{
			return null;
		}
		permutations.add(str.substring(0, 1));
		for (int letterIndex = 1; letterIndex < str.length(); letterIndex++)
		{
			List<String> newPermutations = new ArrayList<String>();
			String letter = str.substring(letterIndex, letterIndex + 1);
			for (String perm : permutations)
			{
				for (int substringIndex = 0; substringIndex <= perm.length(); substringIndex++)
				{
					newPermutations.add(perm.substring(0, substringIndex) + letter + perm.substring(substringIndex));
				}
			}
			permutations = newPermutations;
		}
		return permutations;
	}
	
	private static List<String> recursivePermutation(String str) 
	{
		List<String> permutations = new ArrayList<String>();
	    int length = str.length();
	    if (length == 1) 
	    {
	    	permutations.add(str);
	    } else 
	    {
	        for (int index = 0; index < length; index++)
	        {
	        	String letter = str.substring(index, index + 1);
	        	String sub = str.substring(0, index) + str.substring(index + 1, length);
	        	for (String subPerm : recursivePermutation(sub)) //Add the letter in the front
	        	{
	        		permutations.add(letter + subPerm);
	        	}
	        }
	    }
    	return permutations;
	}

}
