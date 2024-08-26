// The Dating Program
// Author: Kristen Lee
// CS 248 1pm

import java.io.*;

class MyDate implements DateInterface
{
    int day;
    int month;
    int year;
    int dayofweek;
    String newdow;
    String newmonth;

    public void set(int m, int d, int y, int dow) 
    {
        month=m;
        day=d;
        year=y;
        dayofweek=dow;
    }

    // return the day of the month (1-31)
    public int getDay()
    {
        return day;
    }

    // return the day of the week (0-6)
    public int getDow()
    {
        return dayofweek;
    }

    // return the month of the year (1-12)
    public int getMonth()
    {
        return month;
    }

    // return the year (four digits)
    public int getYear()
    {
        return year;
    }

    // moves the date forward by exactly one day
    public void tomorrow()
    {
        if(dayofweek==6)
        {
            dayofweek=0;
        }
        else
        {
            dayofweek++;
        }

        if(month == 1 && day==31)
        {
            day=1;
            month++;
        }
        else if(month==3 && day==31)
        {
            day=1;
            month++;
        }

        else if(month==4 && day==30)
        {
            day=1;
            month++;
        }
        else if(month==5 && day==31)
        {
            day=1;
            month++;
        }
        else if(month==6 && day==30)
        {
            day=1;
            month++;
        }
        else if(month==7 && day==31)
        {
            day=1;
            month++;
        }
        else if(month==8 && day==31)
        {
            day=1;
            month++;
        }
        else if(month==9 && day==30)
        {
            day=1;
            month++;
        }
        else if(month==10 && day==31)
        {
            day=1;
            month++;
        }
        else if(month==11 && day==30)
        {
            day=1;

            month++;
        }
        else if(month==12 && day==31)
        {
            day=1;
            month=1;
            year=year+1;
        }

        else if(month==2) // LEAP YEARS AHH
        {
            if((year%4==0 && year%100!=0)|| year% 400==0)
            {
                if(day==29)
                {
                    day=1;
                    month++;
                }
                else
                {
                    day++;
                }
            }
            else
            {
                if(day==28)
                {
                    day=1;
                    month++;
                }
                else
                {
                    day++;
                }
            }

        }
        else
        {
            day++;
        }

    }

    // moves the date backwards exactly one day
    public void yesterday()
    {
        if(month==1 && day==1)
        {
            day=31;
            month=12;
            year=year-1;
        }
        else if(month==12 && day==1)
        {
            day=30;
            month--;
        }
        else if(month==11 && day==1)
        {
            day=31;
            month--;
        }
        else if(month==10 && day==1)
        {
            day=30;
            month--;
        }
        else if(month==9 && day==1)
        {
            day=31;
            month--;
        }
        else if(month==8 && day==1)
        {
            day=31;
            month--;
        }        
        else if(month==7 && day==1)
        {
            day=30;
            month--;
        }
        else if(month==6 && day==1)
        {
            day=30;
            month--;
        }
        else if(month==5 && day==1)
        {
            day=31;
            month--;
        }
        else if(month==4 && day==1)
        {
            day=30;
            month--;
        }
        else if(month==3 && day==1)
        {
            // lear years
            if(((year%4==0) && (year%100!=0))|| (year% 400==0))
            {
                day=29;
                month--;
            }
            else
            {
                day=28;
                month--;
            }
        }
        else if(month==2 && day==1)
        {
            day=31;
            month--;
        }
        else
        {
            day--;
        }

        // going back one day of the week 
        if(dayofweek==0)
        {
            dayofweek=6;
        }
        else
        {
            dayofweek--;
        }
    }
    
    // return the date as a string in the format "Monday March 18, 2002"
    public String toString()
    {
        // chnage month from number to words
        String newmonth;
        if(month==1)
        {
            newmonth="January";
        }
        else if(month==2)
        {
            newmonth="February";
        }
        else if(month==3)
        {
            newmonth="March";
        }
        else if(month==4)
        {
            newmonth="April";
        }
        else if(month==5)
        {
            newmonth="May";
        }
        else if(month==6)
        {
            newmonth="June";
        }
        else if(month==7)
        {
            newmonth="July";
        }
        else if(month==8)
        {
            newmonth="August";
        }
        else if(month==9)
        {
            newmonth="September";
        }
        else if(month==10)
        {
            newmonth="October";
        }
        else if(month==11)
        {
            newmonth="November";
        }

        else
        {
            newmonth="December";
        }

        // change day from numbers to words
        String newdow;
        if(dayofweek==0)
        {
            newdow="Sunday";
        }
        else if(dayofweek==1)
        {
            newdow="Monday";
        }
        else if(dayofweek==2)
        {
            newdow="Tuesday";
        }
        else if(dayofweek==3)
        {
            newdow="Wednesday";
        }
        else if(dayofweek==4)
        {
            newdow="Thursday";
        }
        else if(dayofweek==5)
        {
            newdow="Friday";
        }
        else
        {
            newdow="Saturday";
        }

        return "The date is: "+newdow+" "+newmonth+" "+day+", "+year;
    }
    
}