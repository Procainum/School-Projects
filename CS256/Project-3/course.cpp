#include <iostream>
#include <cstdlib>
#include <fstream>
#include <sstream>
#include <string>

//Header file
#include "Student.h"
#include "course.h"

using namespace std;

//Overloading the >> operator for input.
istream& operator >> (istream& is, Course& course)
{
    string line;
    while(getline(is, line)) 
    {
        Student* student = new Student();
        istringstream iss(line);
        iss >> *student;
        course.students.enqueue(student);
        course.studentCount++;  
    }

    course.sort_();
    return is;
}

//Overloading the << operator for output.
ostream& operator << (ostream& os,  Course& course)
{
    Student* student = new Student();
    
    while(course.students.dequeue(student)) {
        os << *student << endl;
    }

    return os;
}

//Course constructor
Course::Course()
{
    studentCount = 0;
}

/*
*
* EXTRA CREDIT SORT METHOD
* 
*
* This method sorts by LAST NAME. Lowest alphabetical last name goes first!
*
*/

void Course::sort_()
{
    Student *temp[studentCount];
    for(int i = 0; i < studentCount; i++) {
        temp[i] = new Student();
        students.dequeue(temp[i]);
    }

    for(int i = 0; i < studentCount; i++) {
        int min = i;
        string minName = temp[i]->getLastName() + temp[i]->getFirstName();
        for(int j = i + 1; j < studentCount; j++) {
            string name = temp[j]->getLastName() + temp[j]->getFirstName();
            if(name < minName) {
                minName = name;
                min = j;
            }
        }
        if(i != min) {
            Student* swap = temp[i];
            temp[i] = temp[min];
            temp[min] = swap;
        }
    }

    for(int i = 0; i < studentCount; i++) {
        students.enqueue(temp[i]);
    }
}

//Find method, modified since project 1.
Student* Course::find (int id)  
{
        Student* student;
        while(students.dequeue(student)) {
            if(student->getId() == id)
                return student;
        }
        return NULL;
}
