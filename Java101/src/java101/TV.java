package java101;

public class TV { // this is the class name
	//instance variables 
	private int channel;

	private int volume;

	private boolean state;
	
	static final int MIN_VOLUME = 1;
	
	static final int MAX_VOLUME = 40;
	
	static final int MIN_CHANNEL = 1;
	
	private static final int MAX_CHANNEL = 189; //currently this is the average amount of channels people have on a TV

	//the 3 constructors for overloading
	public TV(int initialChannel, int initialVolume, boolean initialState) 
	{
		if (initialChannel > MAX_CHANNEL)
		{
			channel = MAX_CHANNEL;
		} else if (initialChannel < MIN_CHANNEL)
		{
			channel = MIN_CHANNEL;
		} else
		{
			channel = initialChannel;
		}
		
		if (initialVolume > MAX_VOLUME)
		{
			channel = MAX_VOLUME;
		} else if (initialVolume < MIN_VOLUME)
		{
			channel = MIN_CHANNEL;
		} else
		{
			channel = initialChannel;
		}
		state = initialState;
	}

	public TV(int initialChannel) 
	{
		this(initialChannel, 0, true);
	}

	public TV(int initialChannel, int initialVolume) 
	{
		this(initialChannel, initialVolume, true);

	}
	
	public TV()
	{
		this(0, 0, true);
	}
	
	public void changeChannel(int newChannel) 
	{
		if ((newChannel <= MAX_CHANNEL) && (newChannel >= MIN_CHANNEL))
		{
			channel = newChannel;
		} 
	}

	public int getChannel() 
	{
		return channel;
	}
	
	public void volumeUp() 
	{
		if (volume < MAX_VOLUME)
		{
			volume++;
		}
	}
	
	public void volumeDown() 
	{
		if (volume > MIN_VOLUME)
		{
			volume--;
		}
	}
	
	public void channelUp() 
	{
		if (channel < MAX_CHANNEL)
		{
			channel++;
		} else
		{
			channel = MIN_CHANNEL;
		}
	}
	
	public void channelDown() 
	{
		if (channel > MIN_CHANNEL)
		{
			channel++;
		} else
		{
			channel = MAX_CHANNEL;
		}
	}

	public void changeVolume(int newVolume) 
	{
		if ((newVolume <= MAX_VOLUME) && (newVolume >= MIN_VOLUME))
		{
			volume = newVolume;
		} 
	}

	public int getVolume() 
	{
		return volume;
	}

	public void turnOff() 
	{
		state = false;
	}

	public boolean getState() 
	{
		return state;
	}

	public void changeState() 
	{
		state = !state;
	}
}
