// This is my exam statistics program

import java.io.*;
import java.util.*;

class examstat
{

  static double avgFunc(int[]score)
  {
    double total=score[0];
    for(int i=1; i<score.length; i++)
    { total=total+score[i]; }

    double avg=score[0];
    avg=total/score.length;

   return avg;
  }
  public static void main(String[] args)
  throws IOException
  {

    System.out.println("** Welcome to the Exam Statistics Program **");

    String filename;
    Scanner cin=new Scanner(System.in);
    System.out.print("Please enter a filename: ");
    filename=cin.nextLine();

    Scanner examfile=new Scanner(new FileReader(filename));
    int n=examfile.nextInt();

    //System.out.println("These are the scores: ");

    //make array
    int[]score=new int[n];

    //populate
    for(int i=0;i<score.length; i++)
    { score[i]=examfile.nextInt(); }

    //find min
    int min=score[0];
    for(int i=1; i<score.length; i++)
    {
      if(score[i]<min)
      { min=score[i]; }
    }

    //find max
    int max=score[0];
    for(int i=1; i<score.length; i++)
    {
      if(score[i]>max)
      {
        max=score[i];
      }
    }

    //find median
    Arrays.sort(score);
    double median;
    if(score.length%2==0)
    {
      median=((double)score[score.length/2-1]+(double)score[score.length/2])/2;
    }
    else
    {
      median=(double)score[score.length]/2;
    }


    //find grades by letter
    int countA=0;
    int countB=0;
    int countC=0;
    int countD=0;
    int countF=0;

    for(int i=0; i<score.length; i++)
    {
      if(score[i]>=90 && score[i]<=100)
      {countA++;}
      else if(score[i]>=80 && score[i]< 90)
      { countB++; }
      else if(score[i]>=70 && score[i]<80)
      { countC++; }
      else if( score[i]>=60 && score[i]<70)
      { countD++; }
      else{ countF++; }
    }

    System.out.println("MAX score: "+max);
    System.out.println("MIN: score "+min);
    System.out.println("AVERAGE: "+avgFunc(score));
    System.out.println("Number of scores by letter: ");
    System.out.println("A: "+countA);
    System.out.println("B: "+countB);
    System.out.println("C: "+countC);
    System.out.println("D: "+countD);
    System.out.println("F: "+countF);
    System.out.println("There are "+n+" total scores.");

  }
}