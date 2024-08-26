// Assignment 1
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include <fstream>
#include <iostream>
#include <string>
#include <iomanip>
#include "Student.h"
#define numStud 30
#define fileName "students.txt"

//-----------------------------------------------------------------------

/*struct Student //phase 1
{
    std:: string studentID_;
    int gradePoint_;
    int creditHours_;
};*/

// -----------------------------------------------------------------------

//comparing students GPA for sorting purposes 
//phase 4
bool compareGPA(Student&a, Student&b)
{
    return a.getGPA()>b.getGPA();
}

// -----------------------------------------------------------------------

// doing selection sort 
//phase 4
void selectionSortGPA(Student students[], int number_Stud)
{
    for(int i=0; i<number_Stud-1; i++)
    {
        int maxpos=i;
        for(int j=i+1; j<number_Stud; j++)
        {
            if(compareGPA(students[j], students[maxpos]))
            { maxpos=j; }
        }
        //swaping things
        Student temporary=students[i];
        students[i]= students[maxpos];
        students[maxpos]=temporary;
    }
}

// -----------------------------------------------------------------------

// displaying student info using a function 
//phase 4
void display(Student students[], int number_Stud)
{
    for(int i=0; i<number_Stud; i++)
    {
        std::cout<< students[i].getStudentID()<< ": " <<std::fixed<< std:: setprecision(2)<<students[i].getGPA()<<" "<< students[i].getLetterGrade()<< " "<< std::endl;
    }
}

// -----------------------------------------------------------------------
// -----------------------------------------------------------------------

int main()
{
    std::cout << " " << std::endl;
    std::cout << "** Welcome to Assignment 1 **" << std::endl;
    std::cout << " " << std::endl;

    // READ and OPEN FILE 
    std:: ifstream fileIn(fileName);

    // CREATE ARRAY 
    Student students[numStud]; 

    // Read from file and create Student object 
    for(int i=0; i<numStud; i++)
    {
        //fileIn >> students[i].studentID_ >> students[i].gradePoint_ >> students[i].creditHours_; // phase 1
        std::string studentID_;
        int gradePoint_, creditHours_;
        fileIn >> studentID_ >> gradePoint_ >> creditHours_;
        students[i]=Student(studentID_,gradePoint_,creditHours_);
    }
    
    // CLOSE FILE 
    fileIn.close();

    // Calculate GPA 
    for(int i=0; i<numStud; i++)
    { students[i].calcGPA(); }

    //PRINT TO THOMAS
    // 
    /* phase 1, 2, 3
    for(int i=0; i<numStud; i++)
    {
        //std:: cout << students[i].getStudentID() << " " << students[i].getGradePoint()<< " " << students[i].getCreditHours() << std::endl; //phase 1 and 2
        std:: cout<< students[i].getStudentID() << ": " << students[i].getLetterGrade() << " " << students[i].getGPA()<< std::endl; //phase 3
    }*/

    // using new function -debugging
    std::cout<< "Unsorted: "<< std::endl;
    display(students, numStud);

    // sorting
    selectionSortGPA(students, numStud);

    //displaying sorted info
    std::cout << " " << std::endl;
    std::cout<< "Sorted: "<< std::endl;
    display(students, numStud);

    // --------------------------------------------------------------------
    std::cout << " " << std::endl;
    std::cout << "** Thanks for using the GPA Calculator! **" << std::endl;
    //delete students;
    return 0;
}