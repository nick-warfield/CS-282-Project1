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
			throw new IllegalArgumentException("ERROR: Student does not have the right number of groups");
		} else if (find(item) != null)
		{
			throw new IllegalArgumentException("ERROR: Student ID already exists.");
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
			//find index of student in the least number of groups
			int leastGroupsStudent = findLeastGroupsStudent(arr, studentsBeingConsidered);

			// find index of group that student is in with most members
			int mostMembers = findMostMembers(arr, studentsBeingConsidered, leastGroupsStudent);

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

	private boolean[][] makeArray()
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

	private int findMostMembers(boolean[][] arr, boolean[] studentsBeingConsidered, int leastGroupsStudent)
	{
		int mostMembers = 0;
		int mostTrues = 0;
		for(int i = 0; i < groupCount; i++)
		{
			if(arr[i][leastGroupsStudent])
			{
				int currentTrues = 0;
				for(int j = 0; j < students.size(); j++)
				{
					if(studentsBeingConsidered[j] && arr[i][j]){currentTrues++;}
				}
				if(currentTrues > mostTrues)
				{
					mostMembers = i;
					mostTrues = currentTrues;
				}
			}
		}
		return mostMembers;
	}

	private int findLeastGroupsStudent(boolean[][] arr, boolean[] studentsBeingConsidered)
	{
		int leastGroups = 0;
		int mostFalses = 0;
		for(int j = 0; j < students.size(); j++)
		{
			int currentFalses = 0;
			for(int i = 0; i < groupCount; i++)
			{
				if(studentsBeingConsidered[j] && !arr[i][j]) {currentFalses++;}
			}
			if(currentFalses > mostFalses)
			{
				leastGroups = j;
				mostFalses = currentFalses;
			}
		}
		return leastGroups;
	}

	public static boolean[] convert(String s)
	{
		boolean[] arr = new boolean[s.length()-1];

		boolean goodConvert = true;
		for(int i = 1; i < s.length(); i++)
		{
			if(s.charAt(i) == 'T')
			{
				arr[i-1] = true;
			} else if(s.charAt(i) == 'F'){
				arr[i-1] = false;
			} else {
				goodConvert = false;
			}
		}
		if(goodConvert)
		{
			return arr;
		} else {
			return null;
		}
	}

	// depreciated
	private int[] totals()
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
