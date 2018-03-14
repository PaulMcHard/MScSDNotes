--schema summary:
--School(name)
--schoollocations(school,location)
--lecturer(staffID,fname, sname, school, dob)
--programme(programmeID,name,progLevel,duration)
--course(courseID,name,courseLevel,credits)
--student(studentID,fname,sname, school, advisor, entry_year,dob, programme)
--studentcourses(student,course)
--lecturercourses(lecturer,course)
--programmecourses(programme,course)

--note that other tables are dependent on school, but school isn't dependent
--on them, thus we need to create school and school locations first
CREATE TABLE School
	(name VARCHAR(60) CONSTRAINT school_pk PRIMARY KEY
	);

CREATE TABLE SchoolLocations
	(
	school VARCHAR(60) CONSTRAINT school_location REFERENCES School(name),
	location VARCHAR(60), 
	--the locations primary key is a composite key, so we must define it at the end
	CONSTRAINT locations_pk PRIMARY KEY (school,location)
	);
	
-- You can change dob to DATE but then you must ensure the format in the data script matches the default format  
-- for your database. You can find the default format for the database by running the sql show datestyle;  You'll
-- probably get ISO DMY or ISO MDY. If the format is ISO MDY then you'll be able to run the scripts using Date otherwise
--you'll need to change the format for the dob entries in the data script. 
CREATE TABLE Lecturer
	(staffID INT CONSTRAINT staff_pk PRIMARY KEY,
	fname VARCHAR(40), sname VARCHAR(40),
	school VARCHAR(60) CONSTRAINT staff_school REFERENCES School(name),
	dob VARCHAR(10),
	salary INT);

CREATE TABLE Programme
	(programmeID INT CONSTRAINT programme_pk PRIMARY KEY,
	name VARCHAR(80),
	progLevel VARCHAR(20),
	duration INT	
	);

CREATE TABLE Course
	(courseID INT CONSTRAINT course_pk PRIMARY KEY,
	name VARCHAR(60),
	courseLevel VARCHAR(20),
	credits INT
	);

--as for lecturer, the dob date stuff applies here also
CREATE TABLE Student
	(studentID INT CONSTRAINT student_pk PRIMARY KEY, 
	fname VARCHAR(40), sname VARCHAR(40),
	--There's an assumption here that a student belongs to only one school
	--in this case combined degrees aren't reflected
	--if you wanted to allow combined degrees, how would you show it?
	school VARCHAR(60) CONSTRAINT student_school REFERENCES School(name), 
	advisor INT CONSTRAINT student_advisor REFERENCES Lecturer(staffID),
	entry_year INT,
	dob VARCHAR(10),
	programme INT CONSTRAINT student_prog REFERENCES Programme(programmeID),
	address VARCHAR(500)
	);
	
CREATE TABLE StudentCourses
	(student INT CONSTRAINT student_on_course REFERENCES Student(studentID), 
	course INT CONSTRAINT course_taken REFERENCES Course(courseID),
	CONSTRAINT student_courses_pk PRIMARY KEY (student,course)
	);

CREATE TABLE LecturerCourses
	(lecturer INT CONSTRAINT delivered_by REFERENCES Lecturer(staffID),
	course INT CONSTRAINT course_delivered REFERENCES Course(courseID),
	CONSTRAINT lecturer_courses_pk PRIMARY KEY (lecturer,course)
	);

CREATE TABLE programmeCourses
	(programme INT CONSTRAINT programme REFERENCES Programme(programmeID),
	course INT CONSTRAINT course_in_programme REFERENCES Course(courseID),
	CONSTRAINT programme_courses_pk  PRIMARY KEY(programme,course)
	);