
public class TestTernary 
{

	public static void main(String[] args) 
	{
		/*variable x = (expression/condition) ? value if true : value if false*/
		int x = 10;
		int y;
		
		y = (x == 10) ? 5 : 10;
		System.out.println(y); //Should be 5.
		
		y = (x == 5) ? 5 : 10;
		System.out.println(y); //Should be 10.
	}

}
