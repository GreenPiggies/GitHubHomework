import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FindB 
{
/* I will use Java102dLesson14.txt and Java102dLesson15.txt as examples. */
	
	/* 
	 * Usage message printed when optional unknown parameters are used or the required parameters are not present.
	 * cut down on the if statements 
	 * fix /c because you are forgetting 1 line
	 * do-while for finding parameters
	 * make sure required args are there
	 * no need to use StringBuffer cause we are just printing it to the screen
	 *  */
	
	/*
	 * Check if # of remaining arguments after options is >1 for error msg*/
	
	/*
	 * */
	
	public static boolean containsKeyWord(boolean ignoreCase, String line, String keyword)
	{
		return ((ignoreCase && line.toLowerCase().contains(keyword.toLowerCase())) || (!ignoreCase && line.contains(keyword)));
	}
	
	public static boolean shouldPrint(boolean containsKeyWord, boolean displayWhenAbsent)
	{
		return ((containsKeyWord && !displayWhenAbsent) || (!containsKeyWord && displayWhenAbsent));
	}
	
	public static void main(String[] args)
	{
		boolean ignoreCase = false;
		boolean tally = false;
		boolean invert = false;
		boolean numbered = false;
		int keywordIndex = 0;
		String keyword = "";
		//Cycle through all the arguments
		while (args[keywordIndex].charAt(0) == '/' && args.length > keywordIndex && args[keywordIndex].length() == 2) //check if args[keywordIndex] length is 2
		{
			switch (args[keywordIndex].charAt(1))
			{
				case 'i': 
					ignoreCase = true;
					break;
				case 'c':
					tally = true;
					break;
				case 'v':
					invert = true;
					break;
				case 'n':
					numbered = true;
					break;
				default:
					//System.err to show error plox
					System.err.println("Invalid option.");
					keywordIndex = args.length;
					return;
			}
			keywordIndex++;
		} 
		if (keywordIndex < args.length)
		{
			keyword = args[keywordIndex];
		}
		if (args.length - keywordIndex > 1) 
		{
			for (int index = keywordIndex + 1; index < args.length; index++)
			{
				try (FileReader file = new FileReader(args[index]);
					 BufferedReader buff = new BufferedReader(file))
				{
					String line;
					StringBuffer stringBuff = new StringBuffer();
					int occurrenceCounter = 0;
					int lineCounter = 0;
					String number = "";
					while ((line = buff.readLine())!= null)
					{
						lineCounter++;
						if (numbered)
						{
							number = "[" + lineCounter + "]";
						}
						if (shouldPrint(containsKeyWord(ignoreCase, line, keyword), invert))
						{
							occurrenceCounter++;
							stringBuff.append(args[index] + ":" + number + " " + line + "\n");
							stringBuff.append(": ");
							stringBuff.append(number);
							stringBuff.append(" ");
							stringBuff.append(line);
							stringBuff.append("\n");
						}
					}
					if (tally)
					{
						System.out.println(args[index] + ": " + occurrenceCounter);
					} else
					{
						System.out.println(stringBuff);
					}
				} catch (FileNotFoundException exception)
				{
					System.out.println(exception.getMessage());
				} catch (IOException exception)
				{
					System.out.println("IO Exception Error -- " + exception);
				}
			}	
		} else
		{
			System.out.println("Usage: Find stringToFind fileName ...");
			System.out.println("  /i     Ignores the case of characters when searching for the word.");
			System.out.println("  /c     Displays only the count of lines containing the word.");
			System.out.println("  /v     Displays all lines NOT containing the specified word.");
			System.out.println("  /n     Displays line numbers with the displayed lines.");
		}
	}

}
