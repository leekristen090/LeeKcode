/*
 * Author: Kristen Lee 
 * CS 333 
 * HW 7 data cleaning program 
 */

import java.io.*;
import java.util.*;

class Cleaning
{
    public static void main (String [] args)
    throws IOException
    {
        String line="";
        String childFName="";
        String childLName="";
        String DOB="";
        String whichYear="";
        String month="", day="";
        String parentFName="";
        String parentLName="";
        String phoneNum="";
        int fourYear=0;
        int missingPhone=0;
        String lastPhonePart="";
        int numDupes=0;
        int newYear;
        String newDate="";
        String areaPhone="", middlePhone="";
        String newPhone="";
        int newLast;
        String[]lineArray=new String[6000];
        boolean duplicate=false;
        int lineArrLeng=0;

        Random rand = new Random();

        Scanner dataFile=new Scanner(new FileReader("CampHW.txt"));
        PrintWriter outfile;
        outfile=new PrintWriter(new FileWriter("New7.txt"));
        
        do
        {
            duplicate=false;
            line=dataFile.nextLine();
            for(int i=0; i<lineArrLeng;i++)
            {            
                if(line.equals(lineArray[i]))
                {
                    duplicate=true; 
                }
            }
            
            if(!duplicate)
            {
                lineArray[lineArrLeng++]=line;
                //outfile.print(line+"\n");
            }
            else
            { 
                numDupes++; 
            }
            //lineArray[i]=line;

            String [] parts=line.split(", ");
            childFName=parts[0];
            childLName=parts[1];
            //System.out.println("Child name: "+childFName+" "+childLName);

            //finding YYYY birth dates
            DOB=parts[2];
            //System.out.println("DOB: "+DOB);
            String [] DOBSplit=DOB.split("/");
            month=DOBSplit[0];
            day=DOBSplit[1];
            whichYear=DOBSplit[2];
            //System.out.println("DOB year: "+whichYear);
            //System.out.println("DOB length: "+DOB.length());
            if(whichYear.length()==4)
            { 
                fourYear++;
                newYear=(Integer.parseInt(whichYear))%100;
                newDate=month+"/"+day+"/"+newYear;
            }

            parentFName=parts[3];
            parentLName=parts[4];
            //System.out.println("Parent name: "+parentFName+" "+parentLName);

            // finding wrong phone numbers 
            phoneNum=parts[5];
            String [] phoneParts=phoneNum.split("-");
            areaPhone=phoneParts[0];
            middlePhone=phoneParts[1];
            lastPhonePart=phoneParts[2];
            int lastMax=9, lastMin=1;
            int randLast=rand.nextInt(lastMax-lastMin+1);
            if(lastPhonePart.length()==3)
            {
                missingPhone++;
                newLast=Integer.valueOf(lastPhonePart)*10+randLast;//+randLast;
                newPhone= areaPhone+"-"+middlePhone+"-"+newLast;
            }
            //System.out.println("Phone: "+phoneNum);

            if(!duplicate)
            {
                // printing things to the outfile
                if(lastPhonePart.length()==3 && whichYear.length()==4)
                {
                    outfile.print(childFName+", "+childLName+", "+newDate+", "+parentFName+", "+parentLName+", "+newPhone+"\n");
                }
                else if(whichYear.length()==4 )
                {
                    outfile.print(childFName+", "+childLName+", "+newDate+", "+parentFName+", "+parentLName+", "+phoneNum+"\n");
                }
                else if(lastPhonePart.length()==3)
                {
                    outfile.print(childFName+", "+childLName+", "+DOB+", "+parentFName+", "+parentLName+", "+newPhone+"\n");
                }
                else
                { 
                    outfile.print(childFName+", "+childLName+", "+DOB+", "+parentFName+", "+parentLName+", "+phoneNum+"\n"); 
                }
            }
        
        }
        while(dataFile.hasNext());
        outfile.close();
        System.out.println("\n** Welcome to the Data Cleaing Program! **");
        System.out.println("\nNumber of YYYY: "+fourYear+"\nNumber of modified Phone#s: "+ missingPhone+"\nNumber of duplicated lines: "+numDupes);
        System.out.println("\nNew output file has been made: 'New7.txt'!\n");
        do
        {
            if(whichYear.length()==4)
            { fourYear++; } 
            else
            { fourYear=0; }
            
            if(lastPhonePart.length()==4)
            { missingPhone=0; }
            else
            { missingPhone++; }
            duplicate=false;
            if(!duplicate)
            {   
                numDupes=0;
            }
            else
            {
                numDupes++;
            }
        }
        while(dataFile.hasNext());
        System.out.println("\nUpdated statistics: ");
        System.out.println("Number of YYYY: "+fourYear+"\nNumber of modified Phone#s: "+ missingPhone+"\nNumber of duplicated lines: "+numDupes+"\n");
    }
}