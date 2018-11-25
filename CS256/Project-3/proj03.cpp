/*
** Parse a student data file, put it into an array, output it
**
** @source proj03.cpp
** @author Sebastian Vivanco
** @assignment Project 2
** NOTE: It was mentioned in the instructuins that this should be proj03.cpp
** So I do not know what the names should be.
*/

#include <iostream>
#include <cstdlib>
#include <fstream>
#include <sstream>

//Header file
#include "Student.h"
#include "course.h"


using namespace std;

Course course;

void read (string filename, Course &course)
{
    //Open file
    ifstream input_file;
    input_file.open(filename);

    //Parse the file and assign values to student structure
    input_file >> course;
}

int main(int argc, char** argv)
{
    //Initializing array + variables 
    string filename, option;
    int id;

    //Gives usage information after failed attempt
    if(argc < 3) 
    {
        cout << "<input file> find <id>\n<input file> list" << endl;
        return -1;
    }
   
    filename = argv[1];
    option = argv[2];
    read(filename, course);

    //Calls the read function
    if(!option.compare("list")) 
    {
        cout << course;
        return 1;
    }

    //Calls the find function
    if(!option.compare("find")) {
        Student* foundStudent;
        if(argv[3] == NULL) {
            cout << "Error, no ID" << endl;
            return -1;
        }
        id = atoi(argv[3]);
        foundStudent = course.find(id); 
        if(foundStudent == NULL) {
              cout << "Could not find student: " << id << endl;
              return -1;
        }
        cout << *foundStudent;
        return 1;
    }
    
    //Outputs an error message
  
    cout << "Please enter correct parameters" << endl;
    return -1;
}
