import java.util.Random;

public class Dice 
{
	private Random generator;
	
	public Dice()
	{
		generator = new Random();
	}
	
	public int rollDie()
	{
		return generator.nextInt(6) + 1;
	}
	
	public int rollDice()
	{
		return this.rollDie() + this.rollDie();
	}
}
