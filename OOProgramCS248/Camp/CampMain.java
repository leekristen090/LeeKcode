// Camp Posanivee

// Author: Kristen lee
// CS 248 1pm

/*
 * List of commands 
 * H - help: print a list of commands
 * E - name, age, deit; enroll a new camper (insert)
 * W - name; withdraw a camper (delete)
 * D - name; display the age and diet of a camper 
 * A - print the average age of the campers 
 * L - list all campers names in alphabetical order 
 * V - print the number of vegan campers 
 * P - list all the campers in preorder
 * Q - quit
*/

import java.io.*;
import java.util.*;

class CampMain
{
    public static void main(String[]args)
    throws IOException
    {
        boolean camp=true;
        int numbCampers=0;
        String camperName="";
        int camperAge=0;
        String camperDiet="";
        System.out.println("");
        System.out.println("** Welcome to Camp Posanivee! **\n");

        // Reading from the file------------------------------------------------------------------
        String filename;
        Scanner cin=new Scanner(System.in);
        System.out.println("Please enter a filename: ");
        // For user input
        filename=cin.next();
        Scanner campfile=new Scanner(new FileReader(filename));
        String command;//=campfile.next();

        BST camperTree=new BST();

        //-----------------------------------------------------------------------
        //-----------------------------------------------------------------------

        while(camp==true)
        {
            command=campfile.next();
            // command h -help; list all the commands ----------------------------------------------
            if(command.equals("H") || command.equals("h"))
            {
                System.out.println("");
                System.out.println("Command: "+command);
                System.out.println("Here is a list of the commands: \n");
                System.out.println("H - help: print a list of commands");
                System.out.println("E - enroll a new camper");
                System.out.println("D - display the age and diet of a camper");
                System.out.println("A - print the average age of the campers");
                System.out.println("L - list all campers names in alphabetical order");
                System.out.println("V - print the number of vegan campers ");
                System.out.println("P - list all the campers in preorder");
                System.out.println("Q - quit");
            }

            // command e - enroll a new camper--------------------------------------------------------
            if(command.equals("E") || command.equals("e"))
            {
                System.out.println("");
                camperName=campfile.next();
                System.out.println("Command: "+command+ ", " +camperName);
                camperAge=campfile.nextInt();
                camperDiet=campfile.next();
                System.out.println("New camper added: " +camperName+ ", " +camperAge+ ", " +camperDiet);

                CampClass b=new CampClass(command,camperName,camperAge,camperDiet);
                camperTree.insert(b);
            }

            //command w - witdraw a camper------------------------------------------------------------
            if(command.equals("W") || command.equals("w"))
            {
                camperName=campfile.next();
                System.out.println("");
                System.out.println("Command: "+command+ ", " +camperName);
                CampClass f=new CampClass(command,camperName,0,"");
                CampClass found=(CampClass)camperTree.lookup(f);
                camperTree.delete(found);
                System.out.println("Camper withdrawn.");
            }

            //command d - display the age and diet of a camper----------------------------------------
            if(command.equals("D") || command.equals("d"))
            { 
                camperName=campfile.next();
                System.out.println("");
                System.out.println("Command: "+command+ ", " +camperName); 
                CampClass f=new CampClass(command,camperName,0,"");
                CampClass found=(CampClass)camperTree.lookup(f);
                System.out.println("Name: "+found.getName());
                System.out.println("Age: "+found.getAge());
                System.out.println("Diet: "+found.getDiet());
            }

            //command a - print the average age of the campers----------------------------------------
            if(command.equals("A") || command.equals("a")) 
            { 
                double total=0.0;
                int count=0;
                System.out.println("");
                System.out.println("Command: "+command); 
                camperTree.reset(BST.PREORDER);

                while(camperTree.hasNext())
                {
                    CampClass listA=(CampClass)camperTree.getNext();
                    total=listA.getAge()+total;
                    count++;
                }
                if(camperTree.isEmpty()) 
                {
                    System.out.println("There are no campers at this time."); 
                }
                else
                {
                    if(total!=0)
                    {
                        total=total/count;
                        System.out.println("The average camper age: "+total);
                    }
                }
            }

            //command L - list the campers names in alphabetical order----------------------------------
            if(command.equals("L") || command.equals("l"))
            { 
                System.out.println("");
                System.out.println("Command: "+command); 

                BST alpha=new BST();
                camperTree.reset(BST.INORDER);
                System.out.println("Campers in alphabetical order: ");

                while(camperTree.hasNext())
                {
                    CampClass list=(CampClass)camperTree.getNext();
                    System.out.println(list.getName());
                }
            }
            
            //command v - print the number of vegans ---------------------------------------------------
            if(command.equals("V") || command.equals("v"))
            { 
                System.out.println("");
                System.out.println("Command: "+command); 
                camperTree.reset(BST.PREORDER);
                int count=0;

                while(camperTree.hasNext())
                {
                    CampClass list=(CampClass)camperTree.getNext();
                    if(list.getDiet().equals("V"))
                    { count++; }
                }
                System.out.println("Number of vegan campers: "+count);
            }
            
            //command P - print the campers in preorder ------------------------------------------------
            if(command.equals("P") || command.equals("p"))
            { 
                System.out.println("");
                System.out.println("Command: "+command); 
                camperTree.reset(BST.PREORDER);
                System.out.println("Preorder transversal: ");

                while(camperTree.hasNext())
                {
                    CampClass list=(CampClass)camperTree.getNext();
                    System.out.println(list.getName());
                }
            }
            
            //command Q - quit the program ------------------------------------------------------------
            if(command.equals("Q") || command.equals("q"))
            {
                System.out.println("");
                System.out.println("Command: "+command);
                System.out.println("Camp Posanivee Program is over."); 
                camp=false;
            }
        }
    }
}