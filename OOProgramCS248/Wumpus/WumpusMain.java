// This is the Wumpus Program 

// Author: Kristen Lee 
// Class: CS 248 1pm

//First get the moving working then you an add in the obstacles and the shooting 

import java.io.*;
import java.util.*;
import java.util.Random;

class WumpusMain
{
    public static void main(String[]args)
    throws IOException
    {
        //open the file 
        //String wump;
        Scanner wump=new Scanner(new FileReader("Wump.txt"));
        int w=wump.nextInt();
        int numbSpiders=wump.nextInt();
        int numbPits=wump.nextInt();
        //System.out.println(w); 

        // create array and populate it
        Rooms[]array=new Rooms[w]; 
        for(int i=0; i<array.length; i++)
        { 
            int currentRoom=wump.nextInt();
            int adjLeft=wump.nextInt();
            int adjMiddle=wump.nextInt();
            int adjRight=wump.nextInt();
            String description=wump.nextLine();
            array[i]=new Rooms(currentRoom, adjLeft, adjMiddle, adjRight, description); 
            //System.out.println(array[i]); //This prints all the rooms #s, adjs, and description
        }

        // Making things random!!!
        Random rand=new Random();
        // Randomize bats=jellfish
        int numbBats=2;
        for(int i=0; i<numbBats;i++)
        {
            int random;
            do 
            {
                random=rand.nextInt(array.length);
            }
            while(array[random].hasBats || array[random].hasSpiders || array[random].hasPits);
            array[random].setHasSpiders(true);
        }

        // see which rooms bats/jellyfish are in 
        int numBatRooms=0; 
        for(Rooms room:array) 
        {
            if(room.hasBats) 
            {
                numBatRooms++;
                System.out.println("Jellyfish found in room: "+room.currentRoom);
            }
        } 

        // Randomize spiders=doodlebob
        //int numbSpiders=2;
        for(int i=0; i<numbSpiders;i++)
        {
            int random;
            do 
            {
                random=rand.nextInt(array.length);
            }
            while(array[random].hasSpiders || array[random].hasBats || array[random].hasPits || array[random].currentRoom==1);
            array[random].setHasSpiders(true);
        }
        // see which rooms spiders/doodlebob are in  
        int numSpiderRooms=0; 
        for(Rooms room:array) 
        {
            if(room.hasSpiders) 
            {
                numSpiderRooms++;
                System.out.println("DoodleBob found in room: "+room.currentRoom);
            }
        } 

        // Randomize pits=grease traps
        //int numbPits=2;
        for(int i=0; i<numbPits;i++)
        {
            int random;
            do 
            {
                random=rand.nextInt(array.length);
            }
            while(array[random].hasPits || array[random].hasSpiders || array[random].hasBats || array[random].currentRoom==1);
            array[random].setHasPits(true);
        }
        // see which room pits/grease traps in 
        int numPitRooms=0; 
        for(Rooms room:array) 
        {
            if(room.hasPits) 
            {
                numPitRooms++;
                System.out.println("Grease trap found in room: "+room.currentRoom);
            }
        } 

        // Randomize wumpus=Mr Krabs 
        int numWumpus=1; 
        for(int i=0; i<numWumpus; i++) {
            int random; 
            do {
                random=rand.nextInt(array.length);
            } while(array[random].hasWumpus || array[random].hasPits || array[random].hasSpiders || array[random].hasBats || array[random].currentRoom == 1);
            array[random].setHasWumpus(true); 
        } 
        // see which room Wumpus/mr krabs is in  
        int numWumpRooms=0; 
        for(Rooms room:array) 
        {
            if(room.hasWumpus) 
            {
                numWumpRooms++;
                System.out.println("Mr.Krabs found in room: "+room.currentRoom);
            }
        } 

        //randomize arrow supply room
        int numbArrows=1; 
        for(int i=0; i<numbArrows; i++) 
        {
            int random; 
            do
            {
                random=rand.nextInt(array.length); 
            } 
            while(array[random].hasArrows || array[random].hasWumpus || array[random].hasPits || array[random].hasSpiders || array[random].hasBats || array[random].currentRoom==1);
            array[random].setHasArrows(true);
        }
        // see which room it is in 
        int numbArrowRooms=0; 
        for(Rooms room:array) 
        {
            if(room.hasArrows) 
            {
                numbArrowRooms++; 
                System.out.println("Jellyfish nets found in room: "+room.currentRoom);
            }
        }


        // Game Play 
        System.out.println(" ");
        System.out.println("** Welcome to the Wumpus Program! **");
        System.out.println("Can you steal the Krabby Patty secret formula?");

        int currentRoom=1;
        int arrows=3;
        boolean game=true;
        boolean wumpDead=false;

        while(game)
        {
            int adjLeft=array[currentRoom-1].getAdjLeft();
            int adjMiddle=array[currentRoom-1].getAdjMiddle();
            int adjRight=array[currentRoom-1].getAdjRight();
            String description=array[currentRoom-1].getDescription();

            System.out.println("You are in room "+currentRoom);
            System.out.println("Your adjacent rooms are "+adjLeft+", "+adjMiddle+", and "+adjRight);
            System.out.println(description);
            System.out.println("You have "+arrows+" jellyfish nets left.");

            // print warnings for the rooms 
            if(array[adjLeft-1].hasSpiders || array[adjMiddle-1].hasSpiders || array[adjRight-1].hasSpiders)
            { System.out.println(" You hear me hoy men noy."); }
            if(array[adjLeft-1].hasPits || array[adjMiddle-1].hasPits || array[adjRight-1].hasPits)
            { System.out.println(" You hear a bubbling sound."); }
            if(array[adjLeft-1].hasWumpus || array[adjMiddle-1].hasWumpus || array[adjRight-1].hasWumpus)
            { System.out.println("The cheapskate Mr. Krabs is near."); }

            // choices move or shoot
            Scanner cin=new Scanner(System.in);
            System.out.println("Do you want to (M)ove or (S)hoot?");
            String choice=cin.nextLine(); // for user input
            
            if(choice.equals("M") || choice.equals("m")) //for choice move
            { 
                System.out.println("Which room do you want to move to?"); 
                int roomNumb=cin.nextInt();

                if(roomNumb==adjLeft || roomNumb==adjMiddle || roomNumb==adjRight)
                {
                    currentRoom=roomNumb;
                    if(array[roomNumb-1].hasBats)
                    {
                        System.out.println("A jellyfish has scooped you up and moved you to a different room.");
                        int newRoom=rand.nextInt(array.length)+1;
                        currentRoom=newRoom;

                        // checking room you get dropped in for obstacles
                        if(array[newRoom-1].hasSpiders)
                        {
                            System.out.println("The bat dropped you in a room with DoodleBob. You lose.");
                            game=false;
                        }
                        if(array[newRoom-1].hasPits)
                        {
                            System.out.println("The jellyfish dropped you in a room with a grease trap. You lose.");
                            game=false;
                        }
                        if(array[newRoom-1].hasWumpus)
                        {
                            System.out.println("The bat dropped you in a room with Mr. Krabs. You lose.");
                            game=false;
                        }
                    }
                    else if(array[roomNumb-1].hasSpiders)
                    {
                        System.out.println("The DoodleBob earased you! You lose.");
                        game=false;
                    }
                    else if(array[roomNumb-1].hasPits)
                    {
                        System.out.println("You have fallen into a grease trap! You lose.");
                        game=false;
                    }
                    else if(array[roomNumb-1].hasWumpus)
                    {
                        System.out.println("Mr. Krabs has you now! You lose.");
                        game=false;
                    }
                    else if(array[roomNumb-1].hasArrows)
                    {
                        System.out.println("You have found the jellyfish net supply room.");
                        arrows=3;
                    }
                }
                else
                {
                    System.out.println("Try again.");
                }
            }
            else if(choice.equals("S") || choice.equals("s")) // for choice shoot 
            {
                System.out.println("Which room would you like to shoot into?");
                int roomNumb=cin.nextInt();
                if(roomNumb==adjLeft || roomNumb==adjMiddle || roomNumb==adjRight)
                {
                    if(array[roomNumb-1].hasWumpus)
                    {
                        wumpDead=true;
                        System.out.println("You caught Krabs in your net! The secret formula is now yours! You win!");
                        game=false;
                    }
                    else if(array[roomNumb].hasSpiders)
                    {
                        System.out.println("You caught DoodleBob in your net. Mr. Krabs is still out there.");
                        arrows--;
                        if(arrows==0)
                        {
                            System.out.println("You are out of jellyfish nets. You lose.");
                            game=false;
                        }
                    }
                    else if(array[roomNumb].hasPits)
                    {
                        System.out.println("Your net got stuck in a grease trap. The Wumpus is still alive.");
                        arrows--;
                        if(arrows==0)
                        {
                            System.out.println("You are out of jellyfish nets. You lose.");
                            game=false;
                        }
                    }
                    else
                    {
                        System.out.println("You lost a jellyfish net and caught nothing.");
                        arrows--;
                        if(arrows==0)
                        {
                            System.out.println("You are out of jellyfish nets. You lose.");
                            game=false;
                        }
                    }
                }
                else
                {
                    System.out.println("Try again.");
                }
            }
            else
            {
                System.out.println("Try again.");
            }
        }
    }
}
