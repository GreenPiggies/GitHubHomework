package Miller;

public class Class 
{
	String name;
	
	Student[] students;
	
	int size;
	
	public Class(String name, Student[] students)
	{
		this.name = name;
		this.students = students;
		size = students.length;
	}
	
	public Class(String name)
	{
		this.name = name;
		students = new Student[5];
		size = 0;
	}
	
	public void ensureCapacity(int addSize)
	{
		while (students.length < size + addSize)
		{
			//Double the size of the array, copy it over.
			Student[] newArray = new Student[students.length * 2];
			for(int index = 0; index < size; index++)
			{
				newArray[index] = students[index];
			}
			students = newArray;
		}
	}
	
	public void addStudent(Student student)
	{
		if (student != null)
		{
			ensureCapacity(1);
			students[size] = student;
			size++;
		}
	}
}
