public class Student implements DataItem<E>{

    public long idNumber;
    public String name;
    public boolean[] inGroup;

    public Student(long idNumber, String name, boolean[] inGroup)
    {
        this.idNumber = idNumber;
        this.name = name;
        this.inGroup = inGroup;
    }

    public void print()
    {
        System.out.print(idNumber() + ", ");
        System.out.print(name() + ", ");
        for(int i = 0; i < inGroup.length; i++)
        {
            if(inGroup[i] == true)
            {
                System.out.print("T");
            } else {
                System.out.print("F");
            }
        }
    }

    public long getIdNumber()
    {
        return idNumber;
    }

    public String getName()
    {
        return name;
    }

    public boolean[] getInGroup()
    {
        return inGroup;
    }

}