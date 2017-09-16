import java.util.Scanner;

public class TimesTable {
	
	static double num = 2;

	private static String numSpaces(double input)
	{
		String zeros = "";
		while (input < Math.pow(10, Math.floor(Math.log10(num) + 1) + 1))
		{
			input = input * 10;
			zeros += " ";
		}
		return zeros;
		
	}
	
	private static String numSpaces(double input, boolean subtract)
	{
		String zeros = "";
		while (input < Math.pow(10, Math.floor(Math.log10(num) + 1)))
		{
			input = input * 10;
			zeros += " ";
		}
		return zeros;
		
	}
	
	
	
	public static void main(String[] args) 
	{		
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter the number you wish to create a times table for here: ");
		num = Double.parseDouble(scan.nextLine());
		for (int i = 0; i < num; i++)
		{
			if (i == 0)
			{
				System.out.print(numSpaces(i + 1) + "  " + "|" + numSpaces(i + 1, true) + (i + 1));
			} else
			{
				System.out.print(" " + numSpaces(i + 1) + (i + 1));
			}
		}
		
		System.out.println();
		
		for (int i = 0; i < (num + 1) * (numSpaces(1).length() + 2); i++)
		{ 
			if (i == numSpaces(1).length() + 2)
			{
				System.out.print("+");
			} else
			{
				System.out.print("-");
			}
		}
		
		
		
		
		for (int i = 0; i < num; i++)
		{
			System.out.println();
			System.out.print(numSpaces(i + 1) + (i + 1) + " |");
			for (int j = 0; j < num; j++)
			{
				if (j == 0)
				{
					System.out.print(numSpaces((i + 1) * (j + 1), true) + ((i + 1) * (j + 1)));
				} else
				{
					System.out.print(" " + numSpaces((i + 1) * (j + 1)) + ((i + 1) * (j + 1)));

				}
			}
		}
		
		
		
		
		
	}

}
