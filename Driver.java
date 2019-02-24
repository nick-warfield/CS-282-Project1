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
			System.out.print("Please enter a data.txt file to start ");
			String line = in.nextLine();
			String[] file = line.split(" "); // split line
			String fileName = file[0];
			if(fileName.equals("data.txt"))
				{
					load(file[0], d1);
					break;
				}
			else 
				System.out.println("Not valid, please try again");
		}
		while (true)
		{
	   		System.out.print("Please enter a command: add, drop," + 
	   			"find, size, members, largest, smallest, cover, and quit.");
	   		String line = in.nextLine();
	   		String[] t = line.split(" "); // split line
	   		String cmd = t[0];
	   		if (cmd.equals("quit"))
	   		{
	   			break;
	   		}
	   		else if (cmd.equals("add"))
	   		{
	      		
	    	}
	    	else if(cmd.equals("drop"))
	    	{
	    		
	    	}
	    	else if(cmd.equals("find"))
	    	{
	    		
	    	}
	    	else if(cmd.equals("size"))
	    	{
	    		
	    	}
			else if(cmd.equals("members"))
	    	{
	    		
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
		}
    }
	// NEED HELP TO SEPERATE DATA BY LINE
	public static void load(String filename, GroupData d2) 
    {
		Scanner fin = null;
		try
		{
	   		fin = new Scanner(new FileReader(filename));
	   		GroupData d1 = d2;
			String row = fin.nextLine();
	   		String[] given = row.split(","); // split
	   		//String info = given[0];
			//String info = given[1];
			//String info = given[2];
			for(int students=0; students<given.length; students+=3)
			{
				for(int data=0; data<3; data++)
				{
					
				}
			}
		}
		catch (Exception exception1)
		{
	   		System.out.println("Error opening input file");
	   		System.exit(0);
		}
    }
}