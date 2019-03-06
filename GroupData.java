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

/*
	public Student find(Student item)
	{
		int index = students.indexOf(item);
		if (index == -1) { return null; }
		return students.get(index);
	}
*/

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

	public String toString()
	{
		String string = "";
		for (Student s : students)
		{
			string += s.toString() + '\n';
		}
		return string;
	}

	//returns size of club array -- needed for handling call for group that doesn't exist in Driver
	public int numOfGroups()
	{
		if(students.size() > 0)
		{
			return students.get(0).numOfGroups();
		}
		return 0;
	}

	public boolean clubLengthCheck()
	{
		boolean equalClubLength = true;
		for(Student s : students)
		{
			if(numOfGroups() != s.numOfGroups())
			{
				equalClubLength = false;
			}
		}
		return equalClubLength;
	}

	public int size()
	{
		return students.size();
	}
	public int[] totals()
	{
		int[] arrOfMembersPerGroup = new int[(students.get(0).numOfGroups())];
		for (Student s : students)
		{
			for(int i=0; i< (students.get(0).numOfGroups()); i++)
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
