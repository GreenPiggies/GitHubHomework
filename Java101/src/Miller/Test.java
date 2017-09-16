package Miller;

public class Test 
{
	
	public enum classType
	{
		MATH, SCIENCE, LANGUAGE
	}
	
	private double score;
	
	private classType thisClass;
	
	public Test(int score, classType thisClass)
	{
		this.score = score;
		this.thisClass = thisClass;
	}
	
	public double getScore()
	{
		return score;
	}
	
	public void setScore(double newScore)
	{
		score = newScore;
	}
	
	public classType type()
	{
		return thisClass;
	}
}
