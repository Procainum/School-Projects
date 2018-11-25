// @file student.h
#ifndef STUDENT_H
#define STUDENT_H
#include <string>
using namespace std;

class Student {
	/**
	* @param is the input stream
	* @param course the student object reference
	* @return the input stream
	*/
    friend istream& operator >> (istream& is, Student& student);

	/**
	* @param os the output stream
	* @param course the student object reference
	* @return the output stream
	*/
	friend ostream& operator << (ostream& os, const Student& student);

	public:
		Student();
		Student(string firstName, string lastName, int id, char grade);
		void setFirstName(string firstName);
		string getFirstName() const;
		void setLastName(string lastName);
		string getLastName() const;
		void setId(int id);
		int getId() const;
		void setGrade(char grade) ;
		char getGrade() const;

	/** added all the setter and getter methods **/
		
	private:
		string firstName;
		string lastName;
		int id;
		char grade;
	};

#endif /* STUDENT_H */