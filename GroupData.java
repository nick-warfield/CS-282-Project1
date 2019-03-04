import java.util.Vector;

public class GroupData implements DataStructOfItemsInGroups<Student>
{
	private Vector<Student> students;

	public GroupData() { students = new Vector<Student>(); }

	public void insert(Student item)
	{
		students.addElement(item);
	}

	public void delete(Student item)
	{
		students.remove(item);
	}

	public Student find(Student item)
	{
		int index = students.indexOf(item);
		if (index == -1) { return null; }
		return students.get(index);
	}

	public int numInGroup(int num)
	{
		int count = 0;
		for (Student s : students)
		{
			if (s.memberOfGroup(num)) { count++; }
		}
		return count;
	}

	public int sizeLargest() { return 0; }
	public int sizeSmallest() { return 0; }
	public String members(int num)
    {
        String members = "";
	    for(Student s : students)
        {
            if(s.memberOfGroup(num))
            {
                members += s.toString() + '\n';
            }
        }
        return members;
    }
	public int numToReachAll() { return 0; }

	public String toString()
	{
		String string = "";
		for (Student s : students)
		{
			string += s.toString() + '\n';
		}
		return string;
	}

	//counts amount of separate groups. will return number of groups that the student with most groups is in.
	public int numOfGroups()
    {
        int totalGroups = 0;
        for(Student s : students)
        {
            if(s.numOfGroups() > totalGroups)
            {
                totalGroups = s.numOfGroups();
            }
        }
        return totalGroups;
    }
}
