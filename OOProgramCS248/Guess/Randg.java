// this is my guessing game program in java
// Kristen Lee

import java.io.*;
import java.util.*;

class randg
{

  static boolean play(int answer)
  {
    if(answer>1) {return false;}
    return true;
  }

  public static void main(String[] args)
  {
    int answer;
    do
    {
      //generate random number
      int number;
      number=(int)(1+100*Math.random());

      //so the use can type in their guess
      Scanner cin;
      cin=new Scanner(System.in); //Keyboard

      System.out.println("** Welcome to the GUESSING GAME!! **");
      System.out.println("I have a number between 1 and 100. Can you guess it?");

      System.out.println("Enter your guess: ");
      int guess;
      guess=cin.nextInt();

          if(guess!=number)
          {
            do
            {
              if(guess>number)
              {
                System.out.println("Too high! Guess again: ");
                guess=cin.nextInt();
              }
              else
              {
                System.out.println("Too low! Guess again: ");
                guess=cin.nextInt();
              }
            }
            while(guess>number||guess<number);
          }
          if(guess==number)
          {
            System.out.println("You win!");
          }
      System.out.println("Do you want to play again? (type '1' for yes or '2' for no): ");
      answer=cin.nextInt();
    }
    while(answer==1);
  }

}