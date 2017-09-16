import java.io.Serializable;
public class Bank implements Serializable
{
	private BankAccount[] accounts;
	private int size;
	
	public Bank(int capacity)
	{
		if (capacity <= 0)
		{
			capacity = 3;
		} 
		accounts = new BankAccount[capacity];
		size = 0;
	}
	
	public void add(BankAccount account)
	{
		if (size < accounts.length)
		{
			accounts[size++] = account;
		}
	}
	
	public String toString()
	{
		StringBuffer buff = new StringBuffer(size + " accounts\n");
		for (int index = 0; index < size; index++)
		{
			buff.append(accounts[index].getBalance());
			buff.append("\n");
		}
		return buff.toString();
	}
}
