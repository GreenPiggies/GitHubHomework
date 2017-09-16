import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BankTester 
{
	
	public static void main(String[] args) 
	{
		BankAccount wesley = new BankAccount(1000, 1066, "Wesley");
		wesley.deposit(20.00);
		System.out.println("Wesley's balance: " + wesley.getBalance());
		System.out.println("Wesley's secret: " + wesley.getSecret());		
		BankAccount jonathan = new BankAccount(50, 1000, "Jonathan");
		jonathan.deposit(20);
		System.out.println("Jonathan's balance: " + jonathan.getBalance());
		System.out.println("Jonathan's balance: " + jonathan.getSecret());

		
		try
		{
			FileOutputStream fileStreamOutput = new FileOutputStream("Bank.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileStreamOutput);
			
			out.writeObject(wesley);
			out.writeObject(jonathan);
			
			out.close();
			
		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		BankAccount newWesley = null;
		BankAccount newJonathan = null;
		try
		{
			FileInputStream fileStreamInput = new FileInputStream("Bank.dat");
			ObjectInputStream in = new ObjectInputStream(fileStreamInput);
			
			newWesley = (BankAccount) in.readObject();
			newJonathan = (BankAccount) in.readObject();
			
			in.close();
		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		} catch (ClassNotFoundException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		
		System.out.println("Wesley's balance restored: " + newWesley.getBalance());		
		System.out.println("Wesley's secret restored: " + newWesley.getSecret());
		System.out.println("Jonathan's balance restored: " + newJonathan.getBalance());		
		System.out.println("Jonathan's secret restored: " + newJonathan.getSecret());

		BankAccount[] accounts = new BankAccount[3];
		accounts[0] = new BankAccount(111);
		accounts[1] = new BankAccount(222);
		accounts[2] = new BankAccount(333);

		try 
		{
			FileOutputStream fileStream = new FileOutputStream("BankAccountsArray.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileStream);

			out.writeObject(accounts);

			out.close();

		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		try 
		{
			FileInputStream fileStream = new FileInputStream("BankAccountsArray.dat");
			ObjectInputStream in = new ObjectInputStream(fileStream);

			BankAccount[] restoreMyAccounts = (BankAccount[]) in.readObject();
			for (BankAccount each : restoreMyAccounts)
			{
				System.out.println("Restored: " + each.getBalance());
			}
			in.close();

		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		} catch (ClassNotFoundException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		Bank myBank = new Bank(4);
		myBank.add(new BankAccount(919));
		myBank.add(new BankAccount(828));
		myBank.add(new BankAccount(737));	
		System.out.println(myBank);
		
		try
		{
			FileOutputStream fileStreamOutput = new FileOutputStream("BigBank.dat");
			ObjectOutputStream out = new ObjectOutputStream(fileStreamOutput);
			
			out.writeObject(myBank);
			
			out.close();
			
		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		try
		{
			FileInputStream fileStreamInput = new FileInputStream("BigBank.dat");
			ObjectInputStream in = new ObjectInputStream(fileStreamInput);
			Bank restoreMyBank = (Bank) in.readObject();
			System.out.println(restoreMyBank.toString());
			in.close();
		} catch (IOException exception)
		{
			System.out.println("Error -- " + exception);
		} catch (ClassNotFoundException exception)
		{
			System.out.println("Error -- " + exception);
		}
		
		System.out.println(wesley.getBalance());
		try
		{		
			wesley.withdraw(2000);
		} catch (IllegalArgumentException exception)
		{
			System.err.println(exception.getMessage());
		}

		try
		{		
			wesley.withdraw(2000);
		} catch (InsufficientFundsException exception)
		{
			System.err.println(exception.getMessage());
			throw new LogExceptions("Insufficient Funds", 1);
		}
		
		


	}

}
