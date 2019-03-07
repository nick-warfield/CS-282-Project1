import java.util.Vector;

public class GroupData implements DataStructOfItemsInGroups<Student>
{
	private Vector<Student> students;
	private int groupCount = 0;

	public GroupData() { students = new Vector<Student>(); }

	public void insert(Student item) throws IllegalArgumentException
	{
		if (item.getInGroup().length != groupCount && !students.isEmpty())
		{
			throw new IllegalArgumentException("Student does not have the right number of groups");
		}
		students.addElement(item);
		groupCount = item.getInGroup().length;
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

	// deprecieated
	public Student find(long id)
	{
		for(Student s : students)
		{
			if(s.getIdNumber() == id)
			{
				return s;
			}
		}
		return null;
	}

	public int numInGroup(int num)
	{
		int count = 0;
		for (Student s : students)
		{
			if (s.memberOfGroup(num - 1)) { count++; }
		}
		return count;
	}

	public int sizeLargest() 
	{ 
		int[] groupTot = totals();
		int  largest = groupTot[0];
		int i = 0;
		while(i<groupTot.length)
		{
			if(groupTot[i]>largest)
			{
				largest = groupTot[i];
			}
			i++;
		}
		return largest; 
	}
	public int sizeSmallest() 
	{ 
		int[] groupTot = totals();
		int  smallest = groupTot[0];
		int i = 1;
		while(smallest==0 && i<groupTot.length)
		{
			smallest = groupTot[i];
			i++;
		}
		//int i = 0;
		while(i<groupTot.length)
		{
			if(groupTot[i]<smallest)
			{
				smallest = groupTot[i];
			}
			i++;
		}
		return smallest; 
	}
	public String members(int num)
	{
		String members = "";
		for(Student s : students)
		{
			if(s.memberOfGroup(num - 1))
			{
				members += s.toString() + '\n';
			}
		}
		return members;
	}
	public int numToReachAll() { return 0; }

	public int numOfGroups() { return groupCount; }

	public String toString()
	{
		String string = "";
		for (Student s : students)
		{
			string += s.toString() + '\n';
		}
		return string;
	}

	// depreciated
	public int[] totals()
	{
		int[] arrOfMembersPerGroup = new int[groupCount];
		for (Student s : students)
		{
			for(int i=0; i< groupCount; i++)
			{
				if(s.memberOfGroup(i))
				{
					arrOfMembersPerGroup[i]++;
				}
			}
		}
		return arrOfMembersPerGroup;
	}
}
