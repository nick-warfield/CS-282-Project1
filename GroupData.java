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
		int[] groupTot = new int[groupCount];
		for(int group=1; group<= groupCount; group++)
		{
			groupTot[group-1] = numInGroup(group);
		}
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
		int[] groupTot = new int[groupCount];
		for(int group=1; group<= groupCount; group++)
		{
			groupTot[group-1] = numInGroup(group);
		}
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

		//change club affliliations into 2d array to easily access group affiliations
		boolean[][] groupsAndStudents = makeArray();

		//Create array to keep track of which students are still not in groups
		//Use counter to keep track of remaining #
		boolean[] studentsBeingConsidered = new boolean[students.size()];
		Arrays.fill(studentsBeingConsidered, true);
		int studentsLeft = students.size();

		while(studentsLeft > 0)
		{
			//find index of student in the least number of groups
			int studentInLeastGroups = findLeastGroupsStudent(groupsAndStudents, studentsBeingConsidered);

			//return 0 if there is a student in 0 groups
			if(studentInLeastGroups == -1)
			{
				return 0;
			}

			//Find the group that studentInLeastGroups is a member of with the MOST other students and remove them
			int mostMembersGroup = findMostMembers(groupsAndStudents, studentsBeingConsidered, studentInLeastGroups);
			for(int j = 0; j < students.size(); j++)
			{
				if(groupsAndStudents[mostMembersGroup][j] && studentsBeingConsidered[j])
				{
					studentsBeingConsidered[j] = false;
					studentsLeft--;
				}
			}
			//increment group counter
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

	//Helper method for numToReachAll (cover) that turns the students' group info
	//into a 2D array for easy traversal
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

	//Helper method for numToReachAll (cover) that finds which group
	//that a given student is in has the most members.
	// Ignores students already taken out of consideration.
	private int findMostMembers(boolean[][] groupsAndStudents, boolean[] studentsBeingConsidered, int student)
	{
		int mostMembers = 0;
		int mostTrues = 0;
		for(int i = 0; i < groupCount; i++)
		{
			if(groupsAndStudents[i][student])
			{
				int currentTrues = 0;
				for(int j = 0; j < students.size(); j++)
				{
					if(studentsBeingConsidered[j] && groupsAndStudents[i][j]){currentTrues++;}
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

	//Helper method for numToReachAll (cover) that finds the student in the least number of groups.
	//Ignores students already taken out of consideration.
	private int findLeastGroupsStudent(boolean[][] groupsAndStudents, boolean[] studentsBeingConsidered)
	{
		int leastGroups = 0;
		int mostFalses = 0;
		for(int j = 0; j < students.size(); j++)
		{
			int currentFalses = 0;
			for(int i = 0; i < groupCount; i++)
			{
				if(studentsBeingConsidered[j] && !groupsAndStudents[i][j]) {currentFalses++;}
			}
			if(currentFalses == groupCount){
				return -1;
			} else if(currentFalses > mostFalses)
			{
				leastGroups = j;
				mostFalses = currentFalses;
			}
		}
		return leastGroups;
	}

	//Method to take T/F string from Driver and turn it into boolean array. Returns null
	//in case of illegal characters.
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

}
