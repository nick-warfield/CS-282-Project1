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

		int numToReachAll = 0;

		//create array to keep track of which students are still not in groups
		boolean[] studentsBeingConsidered = new boolean[students.size()];
		Arrays.fill(studentsBeingConsidered, true);
		//counter of students that have not been grouped yet
		int studentsLeft = students.size();


		//change club affliliations into 2d array for ease of access
		boolean[][] arr = makeArray();

		while(studentsLeft > 0)
		{
			// find index of group with most members
			int mostMembers = findMostMembers(arr, studentsBeingConsidered);

			//Find which students are in it and remove from studentsBeingConsidered, subtract from studentsLeft
			for(int j = 0; j < students.size(); j++)
			{
				if(arr[mostMembers][j] && studentsBeingConsidered[j])
				{
					studentsBeingConsidered[j] = false;
					studentsLeft--;
				}
			}
			//increase numToReachAll everytime a group is formed until all students are covered (studentsLeft = 0)
			numToReachAll++;
		}
		return numToReachAll;
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

	public boolean[][] makeArray()
	{
		boolean[][] arr = new boolean[groupCount][students.size()];

		for(int groups = 0; groups < groupCount; groups++)
		{
			for(int studs = 0; studs < students.size(); studs++)
			{
				arr[groups][studs] = students.get(studs).memberOfGroup(groups);
			}
		}
		return arr;
	}

	public int findMostMembers(boolean[][] arr, boolean[] studentsBeingConsidered)
	{
		int mostMembers = 0;
		int mostTrues = 0;
		for(int i = 0; i < groupCount; i++)
		{
			int currentTrues = 0;
			for(int j = 0; j < students.size(); j++)
			{
				if(studentsBeingConsidered[j] && arr[i][j]) {currentTrues++;}
			}
			if (currentTrues > mostTrues)
			{
				mostMembers = i;
				mostTrues = currentTrues;
			}
		}
		return mostMembers;
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
