package java101;

public class SwitchCase {

	public static void main(String[] args) {
		String monthShort = "Jan";
		String monthName;
		int month;
		
		if (monthShort.equals("Jan"))
		{
			monthName = "January";
			month = 1;
		} else if (monthShort.equals("Feb"))
		{
			monthName = "February";
			month = 2;
		} else if (monthShort.equals("Mar"))
		{
			monthName = "March";
			month = 3;
		} else
		{
			monthName = "Unknown";
			month = 0;
		}
		System.out.println("Month name is " + monthName);
		System.out.println("Month number is " + month);
		switch(monthShort)
		{
			case "Jan":
				monthName = "January";
				month = 1;
				break;
			case "Feb":
				monthName = "February";
				month = 2;
				break;
			case "Mar":
				monthName = "March";
				month = 3;
			default:
				monthName = "Unknown";
				month = 0;
				break;
		}
		System.out.println("Month name is " + monthName);
		System.out.println("Month number is " + month);
		month = 3;
		switch(month)
		{
			case 1:
				monthName = "January";
				break;
			case 2:
				monthName = "February";
				break;
			case 3:
				monthName = "March";
				break;
			default:
				monthName = "Unknown";
				break;
		}
		System.out.println("Month name is " + monthName);
	}

}
