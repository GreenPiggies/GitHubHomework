import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* Write a program Find that searches all files specified on the command line and prints out all the lines containing 
 * a specified word. For example, if you call:

java Find main someDirectory noFile Lecture1.txt Lecture4.txt
then the program will print all the lines that contain the word "main":

someDirectory (Access is denied)
noFile (The system cannot find the file specified)
Lecture1.txt: - If we want to run a Java program, we must create a main method with the following signature:
Lecture1.txt:  public static void main(String[] args)
Lecture4.txt: It doesn't contain a main method.
Lecture4.txt: -> Most classes don't contain a main method.
Lecture4.txt: A tester class is a class with a main method that contains statements to run methods of another class.
Lecture4.txt:     - Supply a main method.
Lecture4.txt:     - Inside the main method, construct one or more objects of the class that is being tested.

The specified word will be the first command line argument followed by the list of files.
For each line that contains the specified word, 
the name of the file will be printed first, 
followed by a colon and a space, and then the line itself. 
If there is a problem opening a file (e.g., is not a file but a directory or does not exist), 
then print an appropriate and useful message as shown in the example above.

 */
/* 
  /i     Ignores the case of characters when searching for the word.
  /c     Displays only the count of lines containing the word.
  /v     Displays all lines NOT containing the specified word.
  /n     Displays line numbers with the displayed lines.
*/
public class FindA 
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
	public static void main(String[] args)
	{
		boolean ignoreCase = false;
		boolean occurrenceCount = false;
		boolean notFound = false;
		boolean numbered = false;
		int keywordIndex = 0;
		if (args.length > 1)
		{
			//Cycle through all the arguments
			for (int index = 0; index < args.length; index++)
			{
				//Looking for parameters
				if (args[index].contains("/"))
				{
					if (args[index].contains("i"))
					{
						ignoreCase = true;
					} else if (args[index].contains("c"))
					{
						occurrenceCount = true;
					} else if (args[index].contains("v"))
					{
						notFound = true;
					} else if (args[index].contains("n"))
					{
						numbered = true;
					}
					keywordIndex++; //so we know where the keyword is
				} else if (index > keywordIndex)
				{
					try (FileReader file = new FileReader(args[index]);
						 BufferedReader buff = new BufferedReader(file))
					{
						String line;
						StringBuffer stringBuff = new StringBuffer();
						int lineCounter = 1;
						int occurrenceCounter = 0;
						String number = "";
						while ((line = buff.readLine())!= null)
						{
							if (numbered)
							{
								number = "[" + lineCounter + "]: ";
							}
							if (ignoreCase)
							{
								if (notFound)
								{
									if (!line.toLowerCase().contains(args[keywordIndex].toLowerCase()))
									{
										if (occurrenceCount)
										{
											occurrenceCounter++;
										} else
										{
											stringBuff.append(args[keywordIndex + 1] + ": " + number + line + "\n");
											lineCounter++;	
										}
									}
								} else
								{
									if (line.toLowerCase().contains(args[keywordIndex].toLowerCase()))
									{
										if (occurrenceCount)
										{
											occurrenceCounter++;
										} else
										{
											stringBuff.append(args[keywordIndex + 1] + ": " + number + line + "\n");
											lineCounter++;
										}
									}
								}
							} else
							{
								if (notFound)
								{
									if (!line.contains(args[keywordIndex]))
									{
										if (occurrenceCount)
										{
											occurrenceCounter++;
										} else
										{
											stringBuff.append(args[keywordIndex + 1] + ": " + number + line + "\n");
											lineCounter++;
											
										}
									}
								} else
								{
									if (line.contains(args[keywordIndex]))
									{
										if (occurrenceCount)
										{
											occurrenceCounter++;
										} else
										{
											stringBuff.append(args[keywordIndex + 1] + ": " + number + line + "\n");
											lineCounter++;
											
										}
									}
								}
							}
						}
						if (occurrenceCount)
						{
							stringBuff.append(args[index] + ": " + occurrenceCounter);
						}
						System.out.println(stringBuff);
					} catch (FileNotFoundException exception)
					{
						System.out.println(exception.getMessage());
					} catch (IOException exception)
					{
						System.out.println("IO Exception Error -- " + exception);
					}
				}	
			}
		} else 
		{
			System.out.println("Usage: Find stringToFind fileName ...");
		}
	}
}
