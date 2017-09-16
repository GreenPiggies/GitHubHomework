package java101;

public class Calendar 
{
	public static void daysOfMonth(int monthNumber)
	{
		switch (monthNumber)
		{
			case 2:
				System.out.println("28");
				break;	
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("30");
				break;
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				System.out.println("31");
				break;
			default:
				System.out.println("The number you have given is an invalid month. Please try again.");
				break;
			
		}
	}
}

