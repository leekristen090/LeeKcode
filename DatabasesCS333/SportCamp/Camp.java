import java.util.*;

class Camp
{
    String fname; //first name 
    String lname; //last name
    String pfname; //parent first name 
    String plname; //parent last name
    int bmonth; //birth month 
    int bday; //birth date
    int byear; //birth year
    int pphone; //parent phone 

    public Camp(String f, String l, int m, int d, int y, String f1, String f2, int p)
    {
        fname=f;
        lname=l;
        bmonth=m;
        bday=d;
        byear=y;
        pfname=f1;
        plname=f2;
        pphone=p;
    }

    public String getfname() { return fname; }
    public String getlname() { return lname; }
    public int getbmonth() { return bmonth; }
    public int getbday() { return bday; }
    public int getbyear() { return byear; }
    public int getPhone() { return pphone; }

    public String toString()
    {
        return fname+", "+lname+", "+bmonth+"/"+bday+"/"+byear+", "+pfname+", "+lname+", "+pphone; 
        //Parent and child last name are the same 
    }

}