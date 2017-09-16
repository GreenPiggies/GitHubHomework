


public class SavingsAccount extends BankAccount
{
	private double minimumBalance;
	private double interestRate;
	
	/**
	 * Creates a new SavingsAccount.
	 */
	public SavingsAccount()
	{
		super(0, 0, "None");
		minimumBalance = 0;
		interestRate = 0.0006;
	}
	/**
	 * Withdraws money from the SavingsAccount.
	 * @param amount The amount of money withdrawn from the SavingsAccount
	 */
	public void withdraw(double amount)
	{
		double temp = this.getBalance();
		super.withdraw(amount);
		if (minimumBalance > this.getBalance())
		{
			minimumBalance = this.getBalance();
		}
	}
	
	public void monthlyReset()
	{
		this.interest();
		minimumBalance = this.getBalance();
		
	}
	
	public void interest()
	{
		//Just to note that the average savings account has an interest rate of 0.06%, which I will be using.
		//The function for interest(using simple interest because I am counting every month) is A = P(1 + r/t).
		//I will be calculating the gained interest, then adding it into the SavingsAccount as a deposit. 
		this.deposit(minimumBalance * (interestRate / (1 / 12)));	
	}
}
