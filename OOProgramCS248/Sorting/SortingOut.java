// This is my sorting program thing 
// Sorting Out Sorts Program

// Author: Kristen Lee
// CS 248 1pm

// make a graph using a spreadsheet and graph the running times 

import java.io.*;
import java.util.*;

class SortingOut
{
    public static Integer [] randomlist(int n)
    {
        Integer [] list=new Integer[n]; //make the array 
        // Interger is a class used to store an int
        for(int i=0; i<list.length; i++) // for loop to fill the array 
        { list[i]=new Integer((int)(n*Math.random())); }
        return list;
    }

    public static void main(String[] args) throws IOException
    {
        System.out.println("");
        System.out.println("** Welcome to the sorting program! **");

        // This is for user input 
        Scanner cin=new Scanner(System.in);
        System.out.println("");
        System.out.println("Please enter a number: ");
        int n=cin.nextInt();

        // Fill an array with a random list, this will be the original 
        Integer[]list=randomlist(n);
        // This is the "deep copy" of the original list of random numbers  
        Integer sortedList[]=new Integer[list.length];
        for(int i=0; i<list.length; i++)
        { sortedList[i]=list[i]; }

        int timeStart=0;
        int timeEnd=0;

        /*
        // Printing the original random number list
        System.out.println("");
        System.out.println("Original random number list: ");
        for(int i=0; i<list.length; i++)
        { System.out.print(list[i]+" "); }
        */

        //-----------------------------------
        
        //before and after you call a sort and then you substract the two 

        //----------------------------------------------------------

        // Bubble Sort
        timeStart=(int)System.currentTimeMillis(); 
        Sorts.bubble(sortedList);
        timeEnd=(int)System.currentTimeMillis();
        System.out.println("");
        if(n<=100)
        {
            System.out.println("");
            System.out.println("Original: ");
            for(int i=0; i<list.length; i++)
            { System.out.print(list[i]+" "); }
            System.out.println("");
            System.out.println("Using bubble sort: ");
            for(int i=0; i<sortedList.length; i++)
            { System.out.print(sortedList[i]+" "); }
            System.out.println("");
        }
        System.out.print("Bubble sort running time: ");
        System.out.println(timeEnd-timeStart+" milliseconds.");
        /*  
        For debugging
        System.out.println(timeStart);
        System.out.println(timeEnd); 
        */

        //------------------------------------------------------------

        // Insertion Sort 
        for(int i=0; i<list.length; i++)
        { sortedList[i]=list[i]; }
        timeStart=(int)System.currentTimeMillis();
        Sorts.insertion(sortedList);
        timeEnd=(int)System.currentTimeMillis(); 
        if(n<=100)
        {
            System.out.println("");
            System.out.println("Original: ");
            for(int i=0; i<list.length; i++)
            { System.out.print(list[i]+" "); } 
            System.out.println("");
            System.out.println("Using insertion sort: ");
            for(int i=0; i<sortedList.length; i++)
            { System.out.print(sortedList[i]+" "); }
            System.out.println("");
        }
        System.out.print("Insertion sort running time: ");
        System.out.println(timeEnd-timeStart+" milliseconds.");

        //-------------------------------------------------------------

        // Selection sort
        for(int i=0; i<list.length; i++)
        { sortedList[i]=list[i]; }
        timeStart=(int)System.currentTimeMillis();
        Sorts.selection(sortedList);
        timeEnd=(int)System.currentTimeMillis(); 
        if(n<=100)
        {
            System.out.println("");
            System.out.println("Original: ");
            for(int i=0; i<list.length; i++)
            { System.out.print(list[i]+" "); } 
            System.out.println("");
            System.out.println("Using selection sort: ");
            for(int i=0; i<sortedList.length; i++)
            { System.out.print(sortedList[i]+" "); }
            System.out.println("");
        }
        System.out.print("Selection sort running time: ");
        System.out.println(timeEnd-timeStart+" milliseconds.");
        

        //------------------------------------------------------------

        // Shell Sort
        for(int i=0; i<list.length; i++)
        { sortedList[i]=list[i]; }
        timeStart=(int)System.currentTimeMillis();
        Sorts.shell(sortedList);
        timeEnd=(int)System.currentTimeMillis(); 
        if(n<=100)
        {
            System.out.println("");
            System.out.println("Original: ");
            for(int i=0; i<list.length; i++)
            { System.out.print(list[i]+" "); }  
            System.out.println("");
            System.out.println("Using shell sort: ");
            for(int i=0; i<sortedList.length; i++)
            { System.out.print(sortedList[i]+" "); }
            System.out.println("");
        }
        System.out.print("Shell sort running time: ");
        System.out.println(timeEnd-timeStart+" milliseconds.");

        //-------------------------------------------------------------

        // Quick Sort
        for(int i=0; i<list.length; i++)
        { sortedList[i]=list[i]; }
        timeStart=(int)System.currentTimeMillis();
        Sorts.quick(sortedList);
        timeEnd=(int)System.currentTimeMillis(); 
        if(n<=100)
        {
            System.out.println("");
            System.out.println("Original: ");
            for(int i=0; i<list.length; i++)
            { System.out.print(list[i]+" "); }  
            System.out.println("");
            System.out.println("Using quick sort: ");
            for(int i=0; i<sortedList.length; i++)
            { System.out.print(sortedList[i]+" "); }
            System.out.println("");
        }
        System.out.print("Quick sort running time: ");
        System.out.println(timeEnd-timeStart+" milliseconds.");
    }
}