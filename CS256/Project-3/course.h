// @file course.h
#ifndef COURSE_H
#define COURSE_H

#include "Queue.h"
#include "Student.h"

class Course {
	/**
	* @param is the input stream
	* @param course the course object reference
	* @return the input stream
	*/
	friend istream& operator >> (istream& is, Course& course);
	/**
	* @param os the output stream
	* @param course the course object reference
	* @return the output stream
	*/

	friend ostream& operator << (ostream& os,  Course& course);
	public:
		Course();
		/**
		* @param id the student id to search for
		*@return pointer to the matched student object. NULL if not found.*/
		Student* find (int id);
	private: /* extra credit */
		void sort_();
	private:
		Queue<Student*> students;
		int studentCount;
};

#endif /* COURSE_H */
