package Miller;

public class School 
{
	private Class[] classes;
	
	private int size;
	
	public School(Class[] classes)
	{
		this.classes = classes;
		size = classes.length;
	}
	
	public School()
	{
		this(new Class[5]);
		size = 0;
	}
	
	public void ensureCapacity(int addSize)
	{
		while (classes.length < size + addSize)
		{
			//Double the size of the array, copy it over.
			Class[] newArray = new Class[classes.length * 2];
			for(int index = 0; index < size; index++)
			{
				newArray[index] = classes[index];
			}
			classes = newArray;
		}
	}
	
	public void addClass(Class otherClass)
	{
		if (otherClass != null)
		{
			ensureCapacity(1);
			classes[size] = otherClass;
			size++;
		}
	}
}
