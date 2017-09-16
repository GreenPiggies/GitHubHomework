package Miller;

public class Student {

	private String name;
	
	private Test[] mathTests;
	
	private Test[] scienceTests;

	private Test[] languageTests;
	
	private int mathSize;
	
	private int scienceSize;
	
	private int languageSize;
	
	private double mathGrade;
	
	private double scienceGrade;
	
	private double languageGrade;
	
	
	public Student(String name, Test[] tests)
	{
		this.name = name;
		mathSize = 0;
		scienceSize = 0;
		languageSize = 0;
		
		mathTests = new Test[1];
		scienceTests = new Test[1];
		languageTests = new Test[1];

		if (tests.length != 0)
		{
			for (int i = 0; i < tests.length; i++)
			{
				if (tests[i].type().equals("Mathematics"))
				{
					ensureCapacity(Test.classType.MATH, 1);
					mathTests[mathSize] = tests[i];
					mathGrade += tests[i].getScore();
					mathSize++;
				} else if (tests[i].type().equals("Science"))
				{
					ensureCapacity(Test.classType.SCIENCE, 1);
					scienceTests[scienceSize] = tests[i];
					scienceGrade += tests[i].getScore();
					scienceSize++;
				} else if (tests[i].type().equals("Language Arts"))
				{
					ensureCapacity(Test.classType.LANGUAGE, 1);
					languageGrade += tests[i].getScore();
					languageTests[languageSize] = tests[i];
					languageSize++;
				}
			}
			mathGrade = mathGrade / mathSize;
			scienceGrade = scienceGrade / scienceSize;
			languageGrade = languageGrade / languageSize;
			
		} 
		
	}
	
	public Student(String name)
	{
		this(name, new Test[0]);
	}
	
	public void ensureCapacity(Test.classType thisClass, int addSize)
	{
		if (thisClass == Test.classType.MATH)
		{
			while (mathTests.length < mathSize + addSize)
			{
				//Double the size of the array, copy it over.
				Test[] newArray = new Test[mathTests.length * 2];
				for(int index = 0; index < mathSize; index++)
				{
					newArray[index] = mathTests[index];
				}
				mathTests = newArray;
			}
		} 
		if (thisClass == Test.classType.SCIENCE)
		{
			while (scienceTests.length < scienceSize + addSize)
			{
				//Double the size of the array, copy it over.
				Test[] newArray = new Test[scienceTests.length * 2];
				for(int index = 0; index < scienceSize; index++)
				{
					newArray[index] = scienceTests[index];
				}
				scienceTests = newArray;
			}
		} 
		if (thisClass == Test.classType.LANGUAGE)
		{
			while (languageTests.length < languageSize + addSize)
			{
				//Double the size of the array, copy it over.
				Test[] newArray = new Test[languageTests.length * 2];
				for(int index = 0; index < languageSize; index++)
				{
					newArray[index] = languageTests[index];
				}
				languageTests = newArray;
			}
		}
		
	
	}
	
	public void addTestScore(Test test)
	{
		if (test != null)
		{
			ensureCapacity(test.type(), 1);
			if (test.type() == Test.classType.MATH)
			{
				mathTests[mathSize] = test;
				mathGrade = ((mathGrade * mathSize) + test.getScore()) / (mathSize + 1);
				mathSize++;
			} if (test.type() == Test.classType.SCIENCE)
			{
				scienceTests[scienceSize] = test;
				scienceGrade = ((scienceGrade * scienceSize) + test.getScore()) / (scienceSize + 1);
				scienceSize++;
			} if (test.type() == Test.classType.LANGUAGE)
			{
				languageTests[languageSize] = test;
				languageGrade = ((languageGrade * languageSize) + test.getScore()) / (languageSize + 1);
				languageSize++;
			}
		}
		
	}
	
	public String toString()
	{
		return name + "'s grade in Mathematics: " + mathGrade
				+ "\n" + name + "'s grade in Science: " + scienceGrade
				+ "\n" + name + "'s grade in Language Arts: " + languageGrade;
	}
}
