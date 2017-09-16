


//TODO: Comments?
public class Appointment 
{
	// Maybe I should store startHour and startMin instead, it is the lowest common demoniator.
	private int startHour;
	private int startMin;
	private int endHour;
	private int endMin;
	private String name;
	public Appointment(int startHour, int startMin, int endHour, int endMin, String name)
	{
		this.name = name;
		/* Check ranges (if error, print error message and set to default value)*/
		if ((startHour >= 0 && startHour <= 23) 
				&& (startMin >= 0 && startMin <= 59)
				&& (endHour >= 0 && endHour <= 23)
				&& (endMin >= 0 && endMin <= 59) //Checking if appointment doesn't go backwards in time
				&& (startHour * 60 + startMin < endHour * 60 + endMin))
		{
			this.startHour = startHour;
			this.startMin = startMin;
			this.endHour = endHour;
			this.endMin = endMin;
		} else
		{
			System.out.println("Times invalid.");
			this.startHour = 0;
			this.startMin = 0;
			this.endHour = 0;
			this.endMin = 0;
		}
		
	}
	
	private int getStart()
	{
		return startHour * 60 + startMin;
	}
	
	private int getEnd()
	{
		return endHour * 60 + endMin;
	}
	
	public boolean overlap(Appointment other)
	{
		return (getEnd() > other.getStart()) && (other.getEnd() > getStart());
	}
}

