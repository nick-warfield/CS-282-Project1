/*
COMP282 Section 16304 Project 1
Group members:
Nicholas Warfield
Javier Aguayo
John Wiesenfeld
 */

public class Student implements DataItem<Student> {

	private long idNumber;
	private String name;
	private boolean[] inGroup;

	public Student(long idNumber, String name, boolean[] inGroup)
	{
		this.idNumber = idNumber;
		this.name = name;
		this.inGroup = inGroup;
	}
	public Student(long idNumber)
	{
		this.idNumber = idNumber;
		this.name = "";
		this.inGroup = new boolean[0];
	}

	public String toString()
	{
		String num = String.format("%09d", idNumber);
		String string = num + "," + name + ", ";
		for(int i = 0; i < inGroup.length; i++)
		{
			if(inGroup[i] == true)
			{
				string += "T";
			} else {
				string += "F";
			}
		}
		return string;
	}


	//compareTo(Student s) will return 0 if student ID #s are equal
	public int compareTo(Student s)
	{
		 int comp = (int)(this.idNumber - s.idNumber);
		 return comp;
	}

	public boolean memberOfGroup(int group)
	{
		if(group < inGroup.length)
		{
			return inGroup[group];
		} else {
			return false;
		}
	}

	public long getIdNumber()
	{
		return idNumber;
	}

	public String getName()
	{
		return name;
	}

	public boolean[] getInGroup()
	{
		return inGroup;
	}



}
