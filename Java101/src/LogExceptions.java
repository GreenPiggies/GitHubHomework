import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogExceptions extends RuntimeException
{
	private int errorNumber;
	private String errorDescription;
	
	public LogExceptions()
	{
		this("");
	}
	
	public LogExceptions(String message)
	{
		this(message, -1);
	}
	
	public LogExceptions(String message, int number)
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
	
}
