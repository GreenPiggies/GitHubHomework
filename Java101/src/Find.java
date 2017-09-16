import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Find 
{

/* Test it thoroughly */
	
	public static void main(String[] args)
	{
		boolean ignoreCase = false;
		boolean tally = false;
		boolean invert = false;
		boolean numbered = false;
		int keywordIndex = 0;
		String keyword = "";
		//Cycle through all the arguments
		while (args.length > 0 && args[keywordIndex].charAt(0) == '/' && args.length > keywordIndex && args[keywordIndex].length() == 2) //check if args[keywordIndex] length is 2
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
		if (args.length - keywordIndex > 1) 
		{
			keyword = args[keywordIndex];
			for (int index = keywordIndex + 1; index < args.length; index++)
			{
				try (FileReader file = new FileReader(args[index]);
					 BufferedReader buff = new BufferedReader(file))
				{
					String line;
					StringBuffer stringBuff = new StringBuffer();
					int matched = 0;
					int lines = 1;
					if (ignoreCase)
					{
						keyword = keyword.toLowerCase();
					}
					String testLine;
					while ((line = buff.readLine()) != null)
					{
						testLine = ignoreCase ? line.toLowerCase() : line;
						boolean contains = testLine.contains(keyword);
						if (contains)
						{
							matched++;
						}
						if ((!invert && contains) || (invert && !contains))
						{ 
							if (!tally)
							{
								stringBuff.append(args[index]);
								stringBuff.append(":");
								stringBuff.append(numbered ? "[" + lines + "] " : " ");
								stringBuff.append(line);
								stringBuff.append("\n");
							} 
						} 
						lines++;

					}
					if (tally)
					{
						stringBuff.append(invert ? (lines - 1) - matched : matched);
					}
					System.out.println(stringBuff.toString());
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
