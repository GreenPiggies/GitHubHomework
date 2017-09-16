import java.io.Serializable;

/**
 * A bank account has a balance that can be changed by deposits and withdrawals.
 * @author Wesley
 */
public class BankAccount implements Comparable, Cloneable, Serializable
{
	//private static final long serialVersionUID = 21L;
	
	private double balance;
	
	transient double secret; 
	
	private int accountNumber;
	
	private String accountName;
	
	private static final double INTEREST_RATE = 0.013;
	
	public BankAccount(int initialBalance, int newAccountNumber, String newAccountName )
	{
		balance = initialBalance;
		accountNumber = newAccountNumber;
		accountName = newAccountName;
		secret = 3.1415;
	}
	
	/**
	 * Constructs a bank Account with a zero balance.
	 */
	public BankAccount(){
		this(0, 0, "None");
	}
	/**
	 * Constructs a bank account with an initial balance.
	 * @param initialBalance Starting balance amount
	 */
	public BankAccount(int initialBalance){
		this(initialBalance, 0, "None");
	}
	/**
	 * Deposits money into the bank account.
	 * @param value The amount to deposit as a string.
	 * @throws BankAccountExceptions 
	 */
	public void deposit(String value) throws BankAccountExceptions
	{
		double amount = 0;
		try
		{
			amount = Double.parseDouble(value);
		} catch (NumberFormatException exception)
		{
			throw new BankAccountExceptions("Bad number", 3);
		}
		if (amount < 0)
		{
			throw new BankAccountExceptions("Negative amount", 1);
		}
		balance = balance + amount;
		
	}
	/**
	 * Deposits money into the bank account.
	 * @param amount The amount to deposit.
	 */
	public void deposit(double amount)
	{
		balance = balance + amount;
	}
	/**
	 * Withdraws money from the bank account.
	 * @param amount The amount to withdraw as a string.
	 * @throws BankAccountExceptions
	 */
	public void withdraw(String value) throws BankAccountExceptions
	{
		double amount = 0;
		try
		{
			amount = Double.parseDouble(value);
		} catch (NumberFormatException exception)
		{
			throw new BankAccountExceptions("Bad number", 3);
		}
		if (amount > balance)
		{
			throw new BankAccountExceptions("Amount exceeds balance", 2);
		} else if (amount < 0)
		{
			throw new BankAccountExceptions("Negative amount", 1);
		}
		balance = balance - amount;
	}
	
	/**
	 * Withdraws money from the bank account.
	 * @param amount The amount to withdraw
	 */
	public void withdraw(double amount)
	{
		if (amount > balance)
		{
			throw new InsufficientFundsException("Amount exceeds balance.");
		}
		balance = balance - amount;
	}
	/**
	 * Gets the current balance of the bank account.
	 * @return The current balance.
	 */
	public double getBalance()
	{
		return balance;
	}
	
	public double getSecret()
	{
		return secret;
	}
	
	public int getAccountNumber()
	{
		return accountNumber;
	}
	
	public String getAccountName()
	{
		return accountName;
	}
	
	public double transfer(int transferAmount, BankAccount transferTo)
	{
		if (this != transferTo)
		{
			/* Way One */
			transferTo.deposit(transferAmount);
			withdraw(transferAmount);
			
			/* Way Two */
			transferTo.deposit(transferAmount);
			balance = balance - transferAmount;
			
			/* Way Three */
			transferTo.balance = transferTo.balance + transferAmount;
			balance = balance - transferAmount;
			
			
		}
		return balance;
	}
	
	public double total(BankAccount [] ba)
	{
		double sum = 0;
		
		for (BankAccount each : ba)
		{
			if (this != each)
			{
				sum += each.getBalance();
			}
		}
		return sum;
	}
	
	public double getInterest()
	{
		return balance * INTEREST_RATE;
	}
	
	public boolean transaction(String transaction, int amount)
	{
		if (transaction.equals("deposit"))
		{
			this.deposit(amount);
			return true;
		} else if (transaction.equals("withdraw"))
		{
			this.withdraw(amount);			
			return true;
		} else 
		{
			return false;
		}
	}
	
	public boolean transaction(String transaction)
	{
		if(transaction.equals("getBalance"))
		{
			this.getBalance();
			return true;
		} 
		return false;
	}
	
	public int compareTo(Object otherObject)
	{
		BankAccount other = (BankAccount) otherObject;
		if (balance < other.balance)
		{
			return -1;
		}
		else if (balance > other.balance)
		{
			return 1;
		}
		else
		{
			return 0;
		}
		
	}
	
	public Object clone()
	{
		try
		{	
			Object clonedAccount = super.clone();
			return clonedAccount;
		} 
		catch (CloneNotSupportedException e)
		{
			System.out.println("You just made an error!");
			return null; //Can�t really happen because we implemented Cloneable, but we still must catch it. 
		}
	}

	
}



