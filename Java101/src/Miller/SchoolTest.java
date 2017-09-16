package Miller;

public class SchoolTest 
{
	public static void main(String[] pigs)
	{
		Student bob = new Student("Bob");
		Student bill = new Student("Bill");
		Student robert = new Student("Robert");
		Student jeff = new Student("Jeff");
		Student jonathan = new Student("Jonathan");
		Student[] students = {bob, bill, robert, jeff, jonathan};
		
		for (int i = 0; i < students.length; i++)
		{
			students[i].addTestScore(new Test(80, Test.classType.MATH));
			students[i].addTestScore(new Test(90, Test.classType.MATH));
			students[i].addTestScore(new Test(70, Test.classType.MATH));
			students[i].addTestScore(new Test(60, Test.classType.MATH));
			students[i].addTestScore(new Test(80, Test.classType.SCIENCE));
			students[i].addTestScore(new Test(90, Test.classType.SCIENCE));
			students[i].addTestScore(new Test(70, Test.classType.SCIENCE));
			students[i].addTestScore(new Test(50, Test.classType.SCIENCE));
			students[i].addTestScore(new Test(80, Test.classType.LANGUAGE));
			students[i].addTestScore(new Test(80, Test.classType.LANGUAGE));
			students[i].addTestScore(new Test(70, Test.classType.LANGUAGE));
			students[i].addTestScore(new Test(60, Test.classType.LANGUAGE));
			System.out.println(students[i].toString());
		}
		
		
		

		
	}
}
