import java.io.*;
import java.util.Scanner;

public class Driver{
	
	
	public static void main(String[] args)
    {
    	
    	GroupData d2= new GroupData();
		commandline(d2);
		
	}
	//menu to call all operations from command line
	public static void commandline(GroupData d2)
    {
		Scanner in = new Scanner(System.in);
		GroupData d1 = d2;
		while(true)
		{
			System.out.print("\nPlease enter a data.txt file to start ");
			String line = in.nextLine();
			String[] file = line.split(" "); // split line
			String fileName = file[0];
			if(fileName.equals("data.txt"))
				{
					load(file[0], d1);
					break;
				}
			else if(fileName.equals("none"))
	    	{
	    		break;
	    	}
			else 
				System.out.println("Not valid, please try again");
		}
		while (true)
		{
	   		System.out.println("\nPlease enter a command: add, drop, " + 
	   			"find, size, members, largest, smallest, \ncover, help and quit.");
	   		String line = in.nextLine();
	   		String[] t = line.split(" "); // split line
	   		String cmd = t[0];
	   		if (cmd.equals("quit"))
	   		{
	   			break;
	   		}
	   		else if (cmd.equals("add"))
	   		{
	      		System.out.println("Enter student name: ");
				
				System.out.println("Enter student ID: ");
				
				System.out.println("Enter groups student is in: ");
				
	    	}
	    	else if(cmd.equals("drop"))
	    	{
	    		System.out.println("Enter student ID: ");
				
				
	    	}
	    	else if(cmd.equals("find"))
	    	{
	    		System.out.println("Enter student ID: ");
				
				//System.out.println();
	    	}
	    	else if(cmd.equals("size"))
	    	{
	    		System.out.println("Enter Group #: ");
	    	}
			else if(cmd.equals("members"))
	    	{
	    		System.out.println("Enter Group #: ");
	    	}
			else if(cmd.equals("largest"))
	    	{
	    		
	    	}
			else if(cmd.equals("smallest"))
	    	{
	    		
	    	}
			else if(cmd.equals("cover"))
	    	{
	    		
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
					"\nquit (ends the program)\n");
	    	}
		}
    }
	public static void load(String filename, GroupData d2) 
    {

		try
		{
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while((line = br.readLine()) != null)
			{
				String[] items = line.split(",");
				long id = Integer.parseInt(items[0]);
				String name = items[1];
				String clubs = items[2];
				System.out.println("\n "+ id + "\n" + name +"\n" + clubs);
			}
			br.close();
			fr.close();
		}
		catch (Exception exception1)
		{
	   		System.out.println("Error opening input file");
	   		System.exit(0);
		}
    }
}