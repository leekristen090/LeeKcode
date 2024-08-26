// this is the date program
//this is like fishmain

//Author: Kristen Lee
// CS 248 1pm

import java.io.*;

public class Year3000
{
  public static void main(String [] args) throws IOException
  {
    DateInterface d = new MyDate();
    DateInterface d2 = new MyDate();
    d.set(1,27,2019,0); // sets the date to Sunday, January 27th, 2019
    d2.set(1,27,2019,0);
    while(d.getYear()<3000)
    {
      d.tomorrow();
      // un-comment the next line to help with debugging
      //System.out.println(d);
    }

    boolean stop=false;
    while(d2.getYear()>1799)
    {
      d2.yesterday();
      while(d2.getYear()<=1799 && stop!=true)
      {
        d2.tomorrow();
        // at this point, d represents January 1, 1800
        //System.out.println(" "); 
        System.out.println(d2);
        stop=true;
      }
    }

    // at this point, d represents January 1, 3000
    System.out.println(" ");
    System.out.println(d);
    System.out.println(" "); 
  }
}

