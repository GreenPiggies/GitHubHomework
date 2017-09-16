import java.io.BufferedWriter;
import java.io.Console;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class BankAccountExceptions extends Exception
{
	private int errorNumber;
	private String errorDescription;
	
	public BankAccountExceptions()
	{
		this("");
	}
	
	public BankAccountExceptions(String message)
	{
		this(message, -1);
	}
	
	public BankAccountExceptions(String message, int number)
	{
		super(message);
		errorNumber = number;
		errorDescription = message;
		
		/* Log exception to file */
		try (FileWriter file = new FileWriter("Error.log", true);
			 BufferedWriter writeBuff = new BufferedWriter(file))
		{
			StringBuffer stringBuff = new StringBuffer();
			
			stringBuff.append(new Date());
			stringBuff.append("\t");
			stringBuff.append(errorNumber);
			stringBuff.append("\t");
			stringBuff.append(errorDescription);
			stringBuff.append("\n");
			
			writeBuff.write(stringBuff.toString());
		} catch (IOException exception)
		{
			System.err.println("Error -- " + exception);
		}
	}
	
	/**
	 * Fix problem by prompting user for correct input.
	 * @return THe correct input from the user
	 */
	public String fixProblem()
	{
		System.out.println();
		switch (errorNumber)
		{
			case 1: 
				System.out.print("Enter a positive deposit value: ");
				break;
			case 2: 
				System.out.print("Enter a smaller withdraw value: ");
				break;
			case 3:
				System.out.print("Enter a valid number: ");
				break;
			default:
				System.out.println("Fixing this error is not yet implemented.");
				break;
		}
		
		Console console = System.console();
		String inputLine = console.readLine();
		
		return inputLine;
	}
}
