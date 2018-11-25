/*
** Parse a student data file, put it into an array, output it
**
** @source student.cpp
** @author Sebastian Vivanco
** @assignment Project 2
*/

#include <iostream>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <string>

//Header file
#include "Student.h"

using namespace std;

//Overloading the >> operator for input.
istream& operator >> (istream& is, Student& student)
{
    is >> student.firstName >> student.lastName >> student.id >> student.grade;
    
    return is;
}

//Overloading the << operator for output.
ostream& operator << (ostream& os, const Student& student)
{
    os << "First name: " << student.firstName << endl;
    os << "Last name: " << student.lastName << endl;
    os << "ID: " << student.id << endl;
    os << "Grade: " << student.grade << endl;
    return os;
}

//No arg constructor
Student::Student() {}

//Regular constructor
Student::Student(string firstName, string lastName, int id, char grade)
{
    this->firstName = firstName;
    this->lastName = lastName;
    this->id = id;
    this->grade = grade;
}

//Getters and setters
void Student::setFirstName(string firstName)
{
    this->firstName = firstName;
}
string Student::getFirstName() const
{
    return firstName;
}
void Student::setLastName(string lastName)
{
    this->lastName = lastName;
}
string Student::getLastName() const
{
    return lastName;
}
void Student::setId(int id)
{
    this->id = id;
}
int Student::getId() const
{
    return id;
}
void Student::setGrade(char grade)
{
    this->grade = grade;
}
char Student::getGrade() const
{
    return grade;
}