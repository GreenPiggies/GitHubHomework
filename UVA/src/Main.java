import java.io.*;
import java.util.*;

class Main
{
	static String message;
	
    public static void main (String args[])  // entry point from OS
    {
    	Scanner in = new Scanner(System.in);
    	
    	init(in);
    	
    	replaceQuotes();
    	
    	System.out.println(message);
    	
        //Main myWork = new Main();  // create a dinamic instance
        //myWork.Begin();            // the true entry point
    }
    
    static void replaceQuotes()
    {
    	int index = -1;
    	int counter = 0;
    	while ((index = message.indexOf("\"")) != -1)
    	{
    		String replacement = "";
    		if (counter % 2 == 0)
    		{
    			replacement = "``";
    		} else
    		{
    			replacement = "''";
    		}
    		message = message.substring(0, index) + replacement + message.substring(index + 1);
    		counter++;
    	}
    }



	static void init(Scanner in) 
	{
		message = "";
		message += in.nextLine();
		while (in.hasNextLine())
		{
			message += "\n" + in.nextLine();
		}
	}
}