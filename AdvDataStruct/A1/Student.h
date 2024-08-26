// Assignment 1
// CS 341

// Honor Pledge: 
//
// I pledge that I have neither given nor
// receieved help on this assignment. 
//
// kmlee1

#include <string>

class Student
{
    public:
        Student();
        Student(std::string& studentID_,int gradPoint_, int creditHours_);
        ~Student();
        std::string getStudentID();
        int getGradePoint();
        int getCreditHours();
        double getGPA();
        char getLetterGrade();
        void calcGPA();
    private:
        std::string studentID_;
        int gradePoint_;
        int creditHours_;
        double gpa_;
        char letterGrade_;
};