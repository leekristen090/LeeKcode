/*
 * Author: Kristen Lee
 * CS 333 
 * Assignment 2
 */

import java.io.*;
import java.util.*;
import java.util.Random;


class HWTwo
{
    public static String f()
    throws IOException
    {
        String fname="";
        String lname="";
        Random rand = new Random();

        Scanner fnamefile=new Scanner(new FileReader("FirstName.txt"));
        String [] fnameArray = new String[100];

        do
        {
            for(int i=0; i<fnameArray.length; i++)
            {                
                fname=fnamefile.nextLine();
                fnameArray[i]=fname;
                //This prints tha whole list of first names from array
                //System.out.println(fnameArray[i]); //debugging
            }
        }
        while(fnamefile.hasNextLine());
        //System.out.println(fnameArray.length); //Printing array length -debugging

        Scanner lnamefile=new Scanner(new FileReader("LastName.txt"));
        String [] lnameArray= new String[100];

        do
        {
            for(int i=0; i<lnameArray.length; i++)
            {
                lname=lnamefile.nextLine();
                lnameArray[i]=lname;
                //This prints the whole list of last names from array
                //System.out.println(lnameArray[i]); //debugging
            }  
        }
        while(lnamefile.hasNextLine());

        //------------------Randomly choose child first and last name-------------------
        
        //first name------------------------
        int fname_max=99, fname_min=0;
        int randfname_num=rand.nextInt(fname_max-fname_min+1)+fname_min;

        //last name (same as parent last name)
        int lname_max=99, lname_min=0;
        int randLname_num=rand.nextInt(lname_max-lname_min+1)+lname_min;

        //parent first name------------------
        int pfname_max=99, pfname_min=0;
        int randPFname_num=rand.nextInt(pfname_max-pfname_min+1)+pfname_min;

        //------------------------generating random numbers-----------------------------
        /*
        System.out.println("Random month between "+bmonth_min+" to "+bmonth_max);
        System.out.println("Random day between "+bday_min+" to "+bday_max);
        System.out.println("Random year between "+byear_min+" to "+byear_max);
        */

        //month-----------------------
        int bmonth_max=12, bmonth_min=1;
        int child_bmonth=rand.nextInt(bmonth_max-bmonth_min+1)+bmonth_min;
        //day -------------------------
        int bday_max=31, bday_min=1;
        if(child_bmonth == 2)
        {
            bday_max = 28;
        } 
        else if(child_bmonth == 4 || child_bmonth == 6 || child_bmonth == 9 || child_bmonth == 11)
        {
            bday_max = 30;
        }
        int child_bday=rand.nextInt(bday_max-bmonth_min+1)+bday_min;
        //year-------------------------
        int byear_max=2018, byear_min=2006;
        int child_byear = rand.nextInt(byear_max-byear_min+1)+byear_min;
        //System.out.println("Child DOB: "+child_bmonth+"/"+child_bday+"/"+child_byear);
        int y=child_byear%100;
        String DOB = child_bmonth+"/"+child_bday+"/"+y;
        

        //parent phone number----------
        int area_max=999,area_min=100;
        //int area = rand.nextInt(1000);
        int area = rand.nextInt(area_max-area_min+1)+area_min;
        int middle = rand.nextInt(1000);
        int modLastNum=rand.nextInt(1000);
        int lastnum=rand.nextInt(10000); 
        String modline= fnameArray[randfname_num]+", "+lnameArray[randLname_num]+", "+child_bmonth+"/"+child_bday+"/"+child_byear+", "+fnameArray[randPFname_num]+", "+lnameArray[randLname_num]+", "+area+"-"+middle+"-"+modLastNum;
        String line= fnameArray[randfname_num]+", "+lnameArray[randLname_num]+", "+DOB+", "+fnameArray[randPFname_num]+", "+lnameArray[randLname_num]+", "+area+"-"+middle+"-"+lastnum; 
        //System.out.println("Phone number: "+area+"-"+middle+"-"+lastnum);
        for(int i=4; i<100; i++)
        {
            if((i-3)%50==0)
            {
                return modline;
            }
            else return line;
        }
        //String line= fnameArray[randfname_num]+", "+lnameArray[randLname_num]+", "+child_bmonth+"/"+child_bday+"/"+child_byear+", "+fnameArray[randPFname_num]+", "+lnameArray[randLname_num]+", "+area+"-"+middle+"-"+lastnum; 
        return line;
    } 
    //main function f 
    public static void main (String[]args)
    throws IOException
    {
        PrintWriter outfile;
        outfile=new PrintWriter(new FileWriter("CampHW.txt"));
        for (int i=0; i<100;i++)
        {
            outfile.print(f());
            outfile.println("");
        }
        outfile.println("");
        outfile.close();

        System.out.println("\nWelcome to Assignment 2 and 7 for CS 333!\nYour output file is complete!\n");

    }
}