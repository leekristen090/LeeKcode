// The Dating Program 
// Author: Kristen Lee
// CS 248 1pm

public interface DateInterface
{
  public int getDay();

  public int getDow();

  public int getMonth();

  public int getYear();

  public void set(int m, int d, int y, int dow);

  public void tomorrow();

  public void yesterday();

  //@return the date as a String in the format "Monday March 18, 2002" 
  public String toString();
}
