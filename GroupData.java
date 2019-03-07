/*
COMP282 Section 16304 Project 1
Group members:
Nicholas Warfield
Javier Aguayo
John Wiesenfeld
 */

import java.util.Vector;
import java.util.Arrays;

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
		for(Student s : students)
		{
			if(s.compareTo(item) == 0)
			{
				return s;
			}
		}
		return null;

		/*int index = students.indexOf(item);
		if (index == -1) { return null; }
		return students.get(index);
		*/
	}

	// deprecieated
/*	public Student find(long id)
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
*/
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
		/*while(smallest==0 && i<groupTot.length)
		{
			smallest = groupTot[i];
			i++;
		}*/
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
	public int numToReachAll() {

		int canBeReducedBy = 0;

		//change club affliliations into 2d array for ease of access
		boolean[][] arr1 = new boolean[groupCount][students.size()];

		for(int groups = 0; groups < groupCount; groups++)
		{
			for(int studs = 0; studs < students.size(); studs++)
			{
				arr1[groups][studs] = students.get(studs).memberOfGroup(groups);
			}
		}

		//printing the array just for testing
		for(int i = 0; i < arr1.length; i++)
		{
			for(int j = 0; j < arr1[i].length; j++)
			{
				System.out.print(arr1[i][j] + " ");
			}
			System.out.print('\n');
		}

		//using a boolean array, each index is a group to show whether that group is necessary.
		//if it is empty or can be absorbed by another group, it will change to false.
		boolean[] goodGroups = new boolean[groupCount];
		Arrays.fill(goodGroups, true);

		//cancel empty groups
		for(int i = 0; i < groupCount; i++)
		{
			boolean isEmpty = false;
			for(int j = 0; j < students.size(); j++)
			{
				if(arr1[i][j] == true)
				{
					isEmpty = false;
				}
			}
			if(isEmpty)
			{
				goodGroups[i] = false;
			}
		}

		//cancel identical groups
		for(int i = 0; i < groupCount; i++)
		{
			if(goodGroups[i])
			{
				for(int j = 0; j < groupCount; j++)
				{
					if((i != j) &&
							goodGroups[j] &&
							Arrays.equals(arr1[i], arr1[j]))
					{
						goodGroups[i] = false;
					}
				}
			}
		}

		/*
		Need to compare remainders (absorb j into i if there are no elements that are true in j that are false in i)
		also need to account for case where the above will work, but j can't be absorbed because it has elements
		that are present in other groups where it can't be absorbed
		*/

		//count good groups
		int good = 0;
		for(int i = 0; i < goodGroups.length; i++)
		{
			if(goodGroups[i]) {good++;}
		}
		return good;

	}

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
