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

	/*
	compareTo(Student s) will return 0 if student ID #s are equal, otherwise it will return the difference
	between them. This is the same implementation as compareTo from the java.lang package except made to work
	with Student ID numbers here instead of Strings.
	 */
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

	//returns # of groups the student is a member of
	public int numOfGroups()
	{
		return inGroup.length;
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
