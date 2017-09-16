

public class CheckingAccount extends BankAccount 
{
	private int numWithdraw;
	
	public CheckingAccount()
	{
		super(0, 0, "None");
		numWithdraw = 0;
	}
	
	public void withdraw(double amount)
	{
		super.withdraw(amount);
		numWithdraw++;
		if(numWithdraw > 3)
		{
			super.withdraw(1);
		}
	}
	
	public void monthlyReset()
	{
		numWithdraw = 0;
	}
	
}
