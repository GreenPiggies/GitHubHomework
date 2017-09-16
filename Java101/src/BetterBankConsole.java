import java.io.Console;
public class BetterBankConsole 
{
	public static String menu()
	{
		StringBuffer buffer = new StringBuffer("\nWelcome to the Java Bank!\n\n");
		buffer.append("Select from the following choices:\n\n");
		buffer.append("1. Check your balance\n");
		buffer.append("2. Deposit money\n");
		buffer.append("3. Withdraw money\n");
		buffer.append("4. Exit\n");
		buffer.append("\n> ");
		return buffer.toString();
	}

	public static void main(String[] args) 
	{
		BankAccount account = new BankAccount();
		Console console = System.console();
		if (console == null)
		{
			System.out.println("You don't have a console!");
		} else
		{
			String choice = "";
			do 
			{
				System.out.print(menu());
				choice = console.readLine();
				System.out.println();
				
				boolean tryAgain = true;
				String value = "";
				switch (choice)
				{
					case "1":
						System.out.println("Balance is: " + account.getBalance());
						break;
					case "2": 
						System.out.println("Enter amount: ");
						value = console.readLine();
						
						while (tryAgain)
						{
							try
							{
								account.deposit(value);
								System.out.println("Depositing: " + value);
								tryAgain = false;
							} catch (BankAccountExceptions exception)
							{
								System.err.println("Error -- " + exception.toString());
								value = exception.fixProblem();
							}
						}						
						break;
					case "3":
						System.out.println("Enter amount: ");
						value = console.readLine();
						
						while (tryAgain)
						{
							try
							{
								account.withdraw(value);
								System.out.println("Withdrawing: " + value);
								tryAgain = false;
							} catch (BankAccountExceptions exception)
							{
								System.err.println("Error -- " + exception.toString());
								value = exception.fixProblem();
							}
						}
						break;
					case "4":
						System.out.println("Exiting program.\n");
						break;
					default:
						System.err.println(choice + " is an invalid choice. Please select again.\n");
				} 
			} while (!choice.equals("4"));
		}
		
			
	}

}
