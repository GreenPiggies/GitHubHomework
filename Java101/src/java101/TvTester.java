package java101;

public class TvTester {

	public static void main(String[] args) {
		TV wesley = new TV(3, 5, true);
		TV wesley2 = new TV(5);
		TV wesley3 = new TV(10, 15);
		System.out.println(wesley3.getChannel());
		wesley.changeState();
		System.out.println(wesley2.getChannel());
		System.out.println(wesley.getState());
		wesley.changeState();
		System.out.println(wesley.getState());
		wesley.turnOff();
		System.out.println(wesley.getState());
		System.out.println(wesley.getChannel());
		wesley.changeChannel(1000);
		System.out.println(wesley.getChannel());
		System.out.println(wesley.getState());
		wesley.changeState();
		System.out.println(wesley.getState());
		wesley.changeState();
		System.out.println(wesley.getState());

		
	}
}
