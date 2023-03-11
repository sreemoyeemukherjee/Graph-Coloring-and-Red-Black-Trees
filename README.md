# Graph-Coloring-and-Red-Black-Trees
An application of graph coloring
Write a program that reads a data file containing a list of student class schedules. The name of the data file will be entered on the command line. The output of your program will be 
1. a two dimensional matrix that represents the graph
2. a final exam schedule.

The format of the input file will always be as follows: 
<student name>  <N>  <course name 1>  <course name 2> … <course nameN> <return> 
<student name>  <K>  <course name 1> <course name 2> … <course nameK> <return> 
:
:
Each students name is in the form <last name>,<first name> with no intervening space characters . The course names are all 6 characters in length and are separated from each other by spaces.  Each course name refers to a single section of the course and for each course, only one section is offered. (It’s a small school.) The number after the student’s name represents the number of courses the student is taking. There is no need to validate the file. We will assume that it has already been validated. There is a maximum of 40 students on the file. No student may take more than 5 courses. There is a maximum of 20 different courses offered each term. Our program must build a graph from the data on the file. Final exams must be scheduled in such a way that no student has a conflict.  For example, if Sue is taking both ENG040 and MAT100 then the final exam for each of these courses must be scheduled at a different time. Sue cannot be in two places at once. The output of your program must be in the following form: 
Final Exam Period 1 <course name > <course name > 
Final Exam Period 2 <course name >
Final Exam Period P <course name > 
