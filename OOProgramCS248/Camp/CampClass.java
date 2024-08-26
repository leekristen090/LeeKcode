// This is the class for the camp program 

import java.util.*;

class CampClass implements Comparable 
{
    String command; 
    String camperName;
    int camperAge;
    String camperDiet;

    public CampClass(String c, String n, int a, String d)
    {
        command=c;
        camperName=n;
        camperAge=a;
        camperDiet=d;
    }

    public int getAge() { return camperAge; }
    public String getName() { return camperName; }
    public String getCommand() { return command; }
    public String getDiet() { return camperDiet; }

    // need a compare function
    public int compareTo(Object y)
    {
        if(y instanceof CampClass)
        {
          CampClass f=(CampClass)y; 
          return camperName.compareTo(f.getName());
        }
        else
          return 0; 
    }
}

