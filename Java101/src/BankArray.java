

import java.util.Arrays;

public class BankArray {

	public static void main(String[] args) {
		
		BankAccount[] ba = new BankAccount[3];
		ba[0] = new BankAccount(17);
		ba[1] = new BankAccount(14);
		ba[2] = new BankAccount(21);
		
		//double totalBalance = 0;
		for(BankAccount eachAccount : ba)
		{
		
			//totalBalance += eachAccount.getBalance();
			System.out.println(eachAccount.getBalance());
			
		}
		//System.out.println(totalBalance);
		
		Arrays.sort(ba);
		for(BankAccount eachAccount : ba)
		{
		
			//totalBalance += eachAccount.getBalance();
			System.out.println(eachAccount.getBalance());
			
		}
		Object clonedObject = ba[0].clone();
		BankAccount clonedAccount = (BankAccount) clonedObject;
		System.out.println("Cloned account balance: " + clonedAccount.getBalance());
		System.out.println("ba[0] account balance: " + ba[0].getBalance());
		ba[0].deposit(100);
		System.out.println("Cloned account balance: " + clonedAccount.getBalance());
		System.out.println("ba[0] account balance: " + ba[0].getBalance());
	}	
	

	

}
