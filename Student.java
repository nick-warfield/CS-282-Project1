public class Student implements DataItem<Student> {

    public long idNumber;
    public String name;
    public boolean[] inGroup;

    public Student(long idNumber, String name, boolean[] inGroup)
    {
        this.idNumber = idNumber;
        this.name = name;
        this.inGroup = inGroup;
    }

    public String toString()
    {
        String string = idNumber + ", " + name + ", ";
        for(int i = 0; i < inGroup.length; i++)
        {
            if(inGroup[i] == true)
            {
                string += "T";
            } else {
                string += "F";
            }
        }

        return string;
    }

    /*
    compareTo(Student s) will return 0 if student ID #s are equal, otherwise it will return the difference
    between them. This is the same implementation as compareTo from the java.lang package except made to work
    with Student ID numbers here
     */
    public int compareTo(Student s)
    {
         int comp = (int)(this.idNumber - s.idNumber);
         return comp;
    }

    public boolean memberOfGroup(int group)
    {
        return inGroup[group];
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