import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/* Write a program Number that reads a file containing text. Read each line and send it to the screen or to an output file with each line preceded by line numbers. If the input file contained:

Mary had a little lamb
Whose fleece was white as snow.
And everywhere that Mary went,
The lamb was sure to go!
then the program will produce the following output:

/* 1 * / Mary had a little lamb
/* 2 * / Whose fleece was white as snow.
/* 3 * / And everywhere that Mary went,
/* 4 * / The lamb was sure to go!
The line numbers are enclosed in /* * / delimiters so that the program can be used for numbering Java source files.

The user can specify the input file name and output file name as two command-line inputs. The user can also just specify the input file name as a single command-line input to have the output printed to the screen. If the user doesn't specify any command-line inputs, then prompt the user for an input file name and print the output to the screen. */

public class Number 
{
	/* Maybe use StringBuffer? */
	/* first else statement should prompt for an input file, then print out the output onto the console. */
	/* BufferedReader may not actually close (FileWriter may not work). */
	/* You can construct out with PrintStream (new PrintWriter(System.out))*/
	
	
	public static void main(String[] args)
	{
		String[] input = new String[1];
		if (args.length <= 0)
		{
			System.out.println("Please enter an input file. ");
			Scanner in = new Scanner(System.in);
			input[0] = in.next();
		} else 
		{
			input = args;
		}
		PrintWriter out = new PrintWriter(System.out);
		try (FileReader fileRead = new FileReader(input[0]);
			 BufferedReader buffRead = new BufferedReader(fileRead)) 
		{
			if (input.length >= 2)
			{
				out = new PrintWriter(new BufferedWriter(new FileWriter(input[1])));
			} 
			String line;
			int counter = 1;
			while ((line = buffRead.readLine()) != null)
			{
				out.println("/* " + counter + " */ " + line);
				counter++;
			}
		} catch (FileNotFoundException exception) 
		{
			System.err.println("File Not Found Exception Error -- " + exception);
		} catch (IOException exception)
		{
			System.err.println("IO Exception Error -- " + exception);
		} finally
		{
			out.close();
		}
		
	} 
		
}

