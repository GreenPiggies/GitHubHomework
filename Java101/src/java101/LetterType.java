package java101;

public class LetterType 
{
	
		public LetterType()
		{
			
		}
		public void determineType(String input)
		{
			if (input != null && input.length() == 1)
			{
				String A = "A";
				String Z = "Z";
				String a = "a";
				String z = "z";
				if((input.compareTo(A) >= 0 && input.compareTo(Z) <= 0) || (input.compareTo(a) >= 0 && input.compareTo(z) <= 0))
				{
					String temp = input.toUpperCase();
					if(temp.equals("A") || temp.equals("E") || temp.equals("I") || temp.equals("O") || temp.equals("U"))
					{
						System.out.println("Vowel");
					}
					else
					{
						System.out.println("Consonant");
					}
				}
			} 	
			System.out.println("This is not a valid input. Please input 1 letter.");
		} 
		
}

	
	

