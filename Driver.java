import java.io.*;
import java.util.Scanner;

public class Driver{
	
	
	public static void main(String[] args)
	{
		GroupData data = load("data.txt");
		Scanner in = new Scanner(System.in);

		boolean exit = false;
		while (!exit)
		{
			switch(commandline(data, in))
			{
				case "quit":		exit = true;		break;
				case "add":		add(data, in);		break;
				case "drop":		drop(data, in);		break;
				case "find":		find(data, in);		break;
				case "size":		size(data, in);		break;
				case "members":		members(data, in);	break;
				case "largest":		largest(data);		break;
				case "smallest":	smallest(data); 	break;
				case "cover":		cover(data);		break;
				case "print":		print(data);		break;
				default:		help();			break;
			}
		}
		in.close();
	}

	public static void add(GroupData data, Scanner in)
	{
		//collect name
		System.out.println("Enter student name: ");
		System.out.print('>');
		String name = " " + in.nextLine();

		//collect ID#
		long id;
		while(true)
		{
			System.out.println("Enter student ID: ");
			System.out.print('>');
			id = in.nextLong();
			in.nextLine();

			if(data.find(id) != null)
			{
				System.out.println("ERROR: Student ID already exists.");
			}
			else if(id >= 0 && id < 1000000000)
			{
				break;
			} else {
				System.out.println("ERROR: Invalid student ID.");
			}
		}

		//collect clubs
		boolean[]clubArr;
		while(true)
		{
			System.out.println("Enter groups student is in: ");
			System.out.print('>');
			String clubs = " " + in.nextLine();
			clubArr = convert(clubs);

			if((clubArr != null) && (data.numOfGroups() == clubArr.length))
			{
				break;
			} else if(clubArr == null){
				System.out.println("ERROR: Club input should only be T and F characters.");
			} else if(data.numOfGroups() != clubArr.length) {
				System.out.println("ERROR: Must list declarations for " + data.numOfGroups() + " groups.");
			}
		}

		//add student
		Student student = new Student(id, name, clubArr);
		data.insert(student);
	}

	public static void drop(GroupData data, Scanner in)
	{
		System.out.println("Enter student ID: ");
		System.out.print('>');
		long id = in.nextLong();
		in.nextLine();

		Student student = data.find(id);
		if(student != null)
		{
			System.out.println("Removing: " + student.toString());
			data.delete(student);
		} else {
			System.out.println("Student ID does not exist");
		}
	}

	public static void find(GroupData data, Scanner in)
	{
		while(true)
		{
			System.out.println("Enter student ID: ");
			System.out.print('>');
			long id = in.nextLong();
			in.nextLine();

			Student student = data.find(id);
			if(student != null)
			{
				System.out.println(student.toString());
				break;
			} else {
				System.out.println("ERROR: Student ID does not exist.");
			}
		}
	}

	public static void size(GroupData data, Scanner in)
	{
		while(true)
		{
			System.out.println("Enter Group #: ");
			System.out.print('>');
			int group = in.nextInt();
			in.nextLine();
			if(group > 0 && group <= data.numOfGroups())
			{
				System.out.println("Size of group " + group + ": " + data.numInGroup(group));
				break;
			} else {
				System.out.println("ERROR: Group does not exist");
			}
		}
	}

	public static void members(GroupData data, Scanner in)
	{
		while(true)
		{
			System.out.println("Enter Group #: ");
			System.out.print('>');
			int groupnum = in.nextInt();
			in.nextLine();

			if(groupnum > 0 && groupnum <= data.numOfGroups())
			{
				System.out.print(data.members(groupnum));
				break;
			} else 
			{
				System.out.println("ERROR: Group does not exist.");
			}
		}
	}

	public static void largest(GroupData data)
	{
		if(data.numOfGroups() != 0)
		{
			System.out.println("" +data.sizeLargest());
		}
		else
		{
			System.out.println("ERROR: Groups do not exist.");
		}
	}

	public static void smallest(GroupData data)
	{
		if(data.numOfGroups() != 0)
		{
			System.out.println("" +data.sizeSmallest());
		}
		else
		{
			System.out.println("ERROR: Groups do not exist.");
		}
	}

	public static void cover(GroupData data)
	{
		/*
			I'm thinking there are 3 scenarios where group # can be consolidated:
			1. one group only has F's - it can be eliminated
			2. If one group "contains" another... i.e. if one group has all the
				members of another group within it. Sometimes this would mean two groups
				have the same members, sometimes it would mean a larger group "contains"
				the same members as smaller group, therefore the larger group also "covers"
				the smaller group.
		- John
		 */
	}

	public static void print(GroupData data)
	{
		System.out.println(data.toString());
	}

	public static void help()
	{
		System.out.println("\nYour options are:" +
			"\nadd (adds a student)" +
			"\ndrop (drops a student)" +
			"\nfind (outputs a student)" +
			"\nsize (outputs the size of a group)" +
			"\nmembers (outputs the members of a group)" +
			"\nlargest (outputs the largest size of any group)" +
			"\nsmallest (outputs the smallest size of any group)" +
			"\ncover (outputs the minimum number of groups which" +
			"cover all students)" +
			"\nprint (outputs complete list of students)" +
			"\nquit (ends the program)\n");
	}

	//menu to call all operations from command line
	public static String commandline(GroupData data, Scanner in)
	{
		System.out.println("\nPlease enter a command: add, drop, " +
			"find, size, members, largest, smallest, \ncover, help, print and quit.");
		System.out.print('>');
		String line = in.nextLine();
		String[] t = line.split(" "); // split line
		String cmd = t[0];
		return cmd;
	}

/*	{
		Scanner in = new Scanner(System.in);
		while (true)
		{
			System.out.println("\nPlease enter a command: add, drop, " +
				"find, size, members, largest, smallest, \ncover, help, print and quit.");
			System.out.print('>');
			String line = in.nextLine();
			String[] t = line.split(" "); // split line
			String cmd = t[0];
			if (cmd.equals("quit"))
			{
				break;
			}
			else if (cmd.equals("add"))
			{
				//collect name
				System.out.println("Enter student name: ");
				System.out.print('>');
				String name = " " + in.nextLine();

				//collect ID#
				long id;
				while(true)
				{
					System.out.println("Enter student ID: ");
					System.out.print('>');
					id = in.nextLong();
					in.nextLine();

					if(data.find(id) != null)
					{
						System.out.println("ERROR: Student ID already exists.");
					}
					else if(id >= 0 && id < 1000000000)
					{
						break;
					} else {
						System.out.println("ERROR: Invalid student ID.");
					}
				}

				//collect clubs
				boolean[]clubArr;
				while(true)
				{
					System.out.println("Enter groups student is in: ");
					System.out.print('>');
					String clubs = " " + in.nextLine();
					clubArr = convert(clubs);

					//I'm sure this if statement can be cleaned up, I'll look at it again later - John
					if((clubArr != null) &&
							(data.numOfGroups() == clubArr.length) ||
							(data.size() == 0))
					{
						break;
					} else if(clubArr == null){
						System.out.println("ERROR: Club input should only be T and F characters.");
					} else if(data.numOfGroups() != clubArr.length && data.size() != 0) {
						System.out.println("ERROR: Must list declarations for " + data.numOfGroups() + " groups.");
					}
				}

				//add student
				Student student = new Student(id, name, clubArr);
				data.insert(student);
			}
			else if(cmd.equals("drop"))
			{
				System.out.println("Enter student ID: ");
				System.out.print('>');
				long id = in.nextLong();
				in.nextLine();

				Student student = data.find(id);
				if(student != null)
				{
					System.out.println("Removing: " + student.toString());
					data.delete(student);
				} else {
					System.out.println("Student ID does not exist");
				}
			}
			else if(cmd.equals("find"))
			{
				while(true)
				{
					System.out.println("Enter student ID: ");
					System.out.print('>');
					long id = in.nextLong();
					in.nextLine();

					Student student = data.find(id);
					if(student != null)
					{
						System.out.println(student.toString());
						break;
					} else {
						System.out.println("ERROR: Student ID does not exist.");
					}
				}
			}
			else if(cmd.equals("size"))
			{
				while(true)
				{
					System.out.println("Enter Group #: ");
					System.out.print('>');
					int group = in.nextInt();
					in.nextLine();
					if(group > 0 && group <= data.numOfGroups())
					{
						System.out.println("Size of group " + group + ": " + data.numInGroup(group));
						break;
					} else {
						System.out.println("ERROR: Group does not exist");
					}
				}
			}
			else if(cmd.equals("members"))
			{
				while(true)
				{
					System.out.println("Enter Group #: ");
					System.out.print('>');
					int groupnum = in.nextInt();
					in.nextLine();

					if(groupnum > 0 && groupnum <= data.numOfGroups())
					{
						System.out.print(data.members(groupnum));
						break;
					} else 
					{
						System.out.println("ERROR: Group does not exist.");
					}
				}
			}
			else if(cmd.equals("largest"))
			{
				if(data.numOfGroups() != 0)
				{
					System.out.println("" +data.sizeLargest());
				}
				else
				{
					System.out.println("ERROR: Groups do not exist.");
				}
			}
			else if(cmd.equals("smallest"))
			{
				if(data.numOfGroups() != 0)
				{
					System.out.println("" +data.sizeSmallest());
				}
				else
				{
					System.out.println("ERROR: Groups do not exist.");
				}
			}
			else if(cmd.equals("cover"))
			{
				/*
				I'm thinking there are 3 scenarios where group # can be consolidated:
					1. one group only has F's - it can be eliminated
					2. If one group "contains" another... i.e. if one group has all the
						members of another group within it. Sometimes this would mean two groups
						have the same members, sometimes it would mean a larger group "contains"
						the same members as smaller group, therefore the larger group also "covers"
						the smaller group.
				- John
				 *//*
			}
			else if(cmd.equals("print"))  // I added this to help with checking for errors - John
			{
				System.out.println(data.toString());
			}
			else if(cmd.equals("help"))
			{
				System.out.println("\nYour options are:" +
					"\nadd (adds a student)" +
					"\ndrop (drops a student)" +
					"\nfind (outputs a student)" +
					"\nsize (outputs the size of a group)" +
					"\nmembers (outputs the members of a group)" +
					"\nlargest (outputs the largest size of any group)" +
					"\nsmallest (outputs the smallest size of any group)" +
					"\ncover (outputs the minimum number of groups which" +
					"cover all students)" +
					"\nprint (outputs complete list of students)" +
					"\nquit (ends the program)\n");
			}
		}
	}
*/
	public static GroupData load(String fileName)
	{
		try
		{
			GroupData data = new GroupData();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line;
			int i = 0;
			if(fileName.equals("data.txt"))
			{
				while((line = br.readLine()) != null)
				{
					String[] items = line.split(",");
					long id = Integer.parseInt(items[0]);
					String name = items[1];
					String clubs = items[2];
					boolean[] arrB = convert(clubs);
					if(arrB == null)
					{
						System.out.println("ERROR: Club input should only be T and F characters.");
						System.exit(0);
					}
					Student stud = new Student(id, name, arrB);
					data.insert(stud);
					i++;
				}
				if(false)
				{
					System.out.println("ERROR: Number of clubs listed for each student must be equal.");
					System.exit(0);
				}
				System.out.println(data.toString());
			}
			else
				System.out.println("\nERROR: Please save data.txt and run app again");
			br.close();
			fr.close();
			return data;
		}
		catch (Exception exception1)
		{
			System.out.println("Error opening input file");
			System.exit(0);
			return null;
		}
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
}
