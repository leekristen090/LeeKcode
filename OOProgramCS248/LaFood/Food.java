// This is an array thingy for La Food Program 
// Author: Kristen Lee
// CS 248 1pm

import java.util.*;

class Food 
{
    String queuePos;
    String partyName;
    int partyNumb;
    int arrivalTime;

    public Food(String q, int t, int p, String n)
    {
        queuePos=q;
        arrivalTime=t;
        partyName=n;
        partyNumb=p;
    }

    /*public Food(Scanner foodfile)
    {
        queuePos=foodfile.next();
        arrivalTime=foodfile. nextInt();
        partyNumb=foodfile.nextInt();
        partyName=foodfile.next();
    }*/

    public String getName() { return partyName; }
    public int getTime() { return arrivalTime; }
    public int getPeople() { return partyNumb; }
    public String getQueuePos() { return queuePos; }

    public String toString()
    {
        //return "Command: "+queuePos+" Name of party: "+partyName+" Number in party: "+partyNumb+" Time: "+arrivalTime;
        //return "Name of party: "+partyName+" Number in party: "+partyNumb+" Time: "+arrivalTime;
        return partyName;
    }
}