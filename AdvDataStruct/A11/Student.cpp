// Assignment 1
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include "Student.h"

Student::Student(): studentID_(""), gradePoint_(0), creditHours_(0), gpa_(0.0), letterGrade_(' ') { calcGPA(); }
Student::Student(std::string& studentID_, int gradePoint_, int creditHours_): studentID_(studentID_), gradePoint_(gradePoint_), creditHours_(creditHours_), gpa_(0.0), letterGrade_(' ') 
{ calcGPA(); }

Student::~Student() {}

std::string Student::getStudentID() { return studentID_; }
int Student::getGradePoint() { return gradePoint_; }
int Student::getCreditHours() { return creditHours_; }
double Student:: getGPA() { return gpa_; }
char Student:: getLetterGrade() { return letterGrade_; }

void Student:: calcGPA()
{
    if(creditHours_!=0)
    {
        gpa_= static_cast<double>(gradePoint_)/creditHours_;
        if(gpa_>=3.67)
        { letterGrade_='A'; }
        else if(gpa_>=2.67)
        { letterGrade_='B'; }
        else if(gpa_>=1.67)
        { letterGrade_='C'; }
        else if(gpa_>=.67)
        { letterGrade_='D'; }
        else
        { letterGrade_='F'; }
    }
}