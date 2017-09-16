package java101;

public class TypeTester 
{

	public static void main(String[] args)
	{
		Types what = new Types();
		System.out.println(what.identify(0));		
		System.out.println(what.identify(0l));
		System.out.println(what.identify(0.0));
		System.out.println(what.identify(0.0f));
		System.out.println(what.identify("0"));
		System.out.println(what.identify('0'));
		System.out.println(what.identify(true));
		byte b = 0;
		System.out.println(what.identify(b));
		short s = 0;
		System.out.println(what.identify(s));	
	}

}
