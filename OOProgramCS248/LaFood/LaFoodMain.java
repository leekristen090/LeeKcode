// The La Food Restaurant Program

// Author: Kristen Lee
// CS 248 1pm

import java.io.*;
import java.util.*;

class LaFoodMain
{
    public static void main(String[]args)
    throws IOException 
    {
        String arrival="A";
        String tableAvailable="T";
        String quit="Q";
        String partyFirstName, partyLastName;
        String partyName="";
        int partyNumb=0; // size of party
        int arrivalTime=0; // time they arrived
        int queueSize=4; // queue size
        int tableTime=0; // time they got seated
        double custTotal=0.0;
        double totalTime=0.0;
        
        System.out.println(" ");
        System.out.println("** Welcome to the La Food Restaurant Simulator! **");
        System.out.println(" ");

        // Reading in the file 
        String filename;
        Scanner cin=new Scanner(System.in);
        System.out.println("Please enter a filename: ");
        // For user input
        filename=cin.next();
        Scanner foodfile=new Scanner(new FileReader(filename));
        String queuePos=foodfile.next();
        System.out.println("");

        //QueueArray object 
        Queue partyQueue=new QueueArray(queueSize);
        //System.out.println(queuePos); // For debugging 
            
        do
        {
            if(queuePos.equals(arrival))
            {
                //System.out.println("Hi there you have arrived");
                arrivalTime=foodfile.nextInt(); // scanning for arrival time 
                //System.out.println(arrivalTime);
                partyNumb=foodfile.nextInt(); //scanning for party size
                partyFirstName=foodfile.next();
                partyLastName=foodfile.nextLine(); 
                partyName=partyFirstName+partyLastName;
                
                Food partyWait=new Food(queuePos, arrivalTime, partyNumb, partyName);
                System.out.println("Please wait at the bar, "+partyWait.getName()+" party of "+partyWait.getPeople()+" people. (Time = "+partyWait.getTime()+")");

                partyQueue.enqueue(partyWait);
                queuePos=foodfile.next();
            }
            else if(queuePos.equals(tableAvailable))
            {
                if(partyQueue.isFull()) // Checking if array is full
                {
                    System.out.print("Queue is full="+partyQueue.isFull());
                    System.out.println("There are no tables avaiable at the moment.");
                }
                tableTime=foodfile.nextInt();
                System.out.println("Table for "+partyQueue.getFront()+"! (Time = "+tableTime+")");
                Food seatedParty=(Food) partyQueue.dequeue();
                totalTime+=tableTime-seatedParty.getTime();
                
                //System.out.println("Total time waiting= "+totalTime); // For debugging
                queuePos=foodfile.next();
                custTotal++; 
            }
            else 
            {
                System.out.println("");
            }
        }
        while(foodfile.hasNextLine());
        //System.out.println(queuePos);
        System.out.println("");
        System.out.println("** Simulation Terminated **");
        System.out.println("The following parties were never sat:\n"+partyQueue.getFront()+" of "+partyNumb+" people."); 
        System.out.println("");
        //System.out.println("Total parties seated = "+custTotal); // For debugging
        System.out.println("Average wait time: "+(totalTime/custTotal));
        System.out.println("");
        System.out.println("Have a nice meal!");
    }
}