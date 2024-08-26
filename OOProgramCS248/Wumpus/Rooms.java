// This is the room file thingy for wumpus

// Author: Kristen Lee
// CS 248 1pm

//import java.io.*;
import java.util.*;

public class Rooms
{
    int currentRoom;
    int adjLeft;
    int adjMiddle;
    int adjRight;
    String description;
    String warning;
    boolean hasBats; //jellyfish
    boolean hasSpiders; //doodlebob
    boolean hasPits; //grease trap
    boolean hasWumpus; //Mr.Krabs
    boolean hasArrows; //jellyfish nets

    public Rooms(int c, int l, int m, int r, String d)
    {
        currentRoom=c;
        adjLeft=l;
        adjMiddle=m;
        adjRight=r;
        description=d;
        hasBats=false;
        hasSpiders=false;
        hasPits=false;
        hasWumpus=false;
    }

    public int getCurrentRoom() { return currentRoom;}
    public int getAdjLeft() { return adjLeft; }
    public int getAdjMiddle() { return adjMiddle; }
    public int getAdjRight() { return adjRight; }
    public String getDescription() { return description; }

    public void setHasBats(boolean value) { hasBats=value; }
    public void setHasSpiders(boolean value) { hasSpiders=value; }
    public void setHasPits(boolean value) { hasPits=value; }
    public void setHasArrows(boolean value) { hasArrows=value; }
    public void setHasWumpus(boolean value) { hasWumpus=value; }

    public String toString()
    {
        return "You are in room "+currentRoom+" and your adjacent rooms are "+adjLeft+", "+adjMiddle+", and "+adjRight+". Room Description:"+description;
    }
}