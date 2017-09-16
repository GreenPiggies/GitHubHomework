import java.io.Console;
import java.io.IOException;

/*
 * 1. FIX FORMATTING - LATER
 * 2. COMMENTS PRS PRS PRS PRS PRS
 */
public class BankConsole 
{
	/**
	 * Checks if an entered password is correct.
	 * @param console The console to which the entered password is typed into. 
	 * @return True if the password is correct, false if not.
	 */
	public static boolean checkPermission(Console console)
	{
		int count = 0;
		while (count < 3) //3 tries for password.
		{
			char[] pass = console.readPassword("\n%s", "Please enter your password: "); //Reads password
			if (String.valueOf(pass).equals("1066"))
			{
				return true;
			} else
			{
				System.out.println("Invalid password: Please try again."); //After every failed try
				count++;
			}
			for (int index = 0; index < pass.length; index++) //Clears password.
			{
				pass[index] = 0;
			}
		}
		System.out.println("Invalid password 3 times in a row: Canceled."); //After 3 failed tries
		return false;
	}
	
	
	public static void main(String[] args) 
	{
		Console console = System.console();
		try
		{
			if (console != null)
			{
				System.out.println();
				System.out.println("Welcome to Wells Fargo!");
				
				String line;
				BankAccount bank = new BankAccount(1000, 1066, "Bob");
				do
				{
					System.out.println();
					System.out.println("Select from the following choices:");
					System.out.println();
					System.out.println("(1): Check your balance.");
					System.out.println("(2): Deposit money into your bank account.");
					System.out.println("(3): Withdraw money from into your bank account.");
					System.out.println("(4): Terminate the program.");
					System.out.println();	
					line = console.readLine("%s", "> ");
					switch (line)
					{
						case "1": //Check Balance
							System.out.println();
							System.out.println(bank.getBalance());
							break;
						case "2": //Deposit
							if (BankConsole.checkPermission(console)) //Check password
							{
								line = console.readLine("%s", "Please enter the amount of money you wish to place into your bank account: ");
								bank.deposit(Double.parseDouble(line));
							}
							break;
						case "3":  //Withdraw
							if (BankConsole.checkPermission(console)) //Check password
							{
								line = console.readLine("%s", "Please enter the amount of money you wish to take from your bank account: ");
								bank.withdraw(Double.parseDouble(line));	
							}
							break;
						case "4": //Quit
							System.out.println("Closing console...");
							break;
						default:
							System.out.println("Invalid choice: Please try again.");
							break;
					}
				} while(!line.equals("4")); //While loop stops when user enters quit.
			} else
			{
				System.out.println("You have no console! *Gasps*");
			}
		} catch (NumberFormatException exception)
		{
			System.out.println("Error -- " + exception);
		}
	}
}
