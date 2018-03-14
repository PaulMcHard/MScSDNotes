--I have left the columns
INSERT INTO School(name)
    VALUES ('Computing Science'),('Mathematics'),('Physics')
    ,('Philosophy'),('Psychology');

INSERT INTO SchoolLocations(school,location)
	VALUES ('Computing Science','Boyd Orr'),('Computing Science','Sir Alwyn Williams'),('Computing Science','Lilybank Gardens'),('Mathematics','Maths Building'),('Physics','Kelvin Building'),
	('Philosophy','67 Oakfield Avenue'),('Philosophy','69 Oakfield Avenue'),('Psychology','58 Hillhead Street');
--date format 1/8 MONTH/DAY so 8th January
--names are taken from films/books
INSERT INTO Lecturer(staffID,fname,sname, school, dob,salary)
	VALUES (1,'Martin','Riggs','Computing Science','1/8/1956',30000),
	(2,'Clarise','Starling','Psychology','3/19/1976',20000),
	(3,'Ethan','Edwards','Philosophy','12/20/1945',15000),
	(4,'Leia','Organa','Physics','10/14/1986',17000),
	(5,'Roy','Batty','Mathematics','3/1/1941',19000),
	(6,'Boba','Fett','Computing Science','11/19/1988',24000),
	(7,'Axel','Foley','Psychology','2/21/1986',19000),
	(8,'Emmet','Brown','Physics','6/15/1988',17000),
	(9,'Ellen','Ripley','Mathematics','1/1/1999',18000),
	(10,'Sarah','Connor','Computing Science','12/12/1967',19500),
	(11,'Chihiro','Sen','Philosophy','7/30/2001',17000),
	(12,'Lucy','Pevensie','Mathematics','8/11/1998',16500),
	(13,'James','Bond','Computing Science','5/2/1966',NULL),
	(14,'Marty','McFly','Psychology','11/12/1984',NULL),
	(15,'Jules','Winfield','Physics','7/18/1949',NULL),
	(16,'Lucy','Quinn','Physics','12/18/1986',NULL)
	;

-- M stands for Masters, H stands for honours (an undergraduate programme)
INSERT INTO Programme(programmeid, name, proglevel, duration)
    VALUES (1, 'Information Technology', 'M', 1),
	(2, 'Software Development', 'M', 1),
	(3, 'Child Psychology', 'H', 4),
	(4, 'Aesthetics', 'H', 4),
	(5, 'Pure Mathematics', 'H', 4),
	(6, 'Astrophysics','H',4),
	(7,'Applied Mathematics','M',5)
	;

INSERT INTO course(courseid, name, courselevel, credits)
    VALUES (1, 'Information Systems and Databases', 'M', 10),
	(2, 'Programming', 'M', 20),
	(3, 'Internet Technology', 'M', 20),
	(4, 'Intro to Psychology', 'H', 20),
	(5, 'Further Psychology', 'H', 20),
	(6, 'Intro to Aesthetics', 'H', 20),
	(7, 'Topology', 'M', 20),
	(8, 'Group and Ring Theory', 'H', 20),
	(9, 'Set Theory', 'H', 20),
	(10, 'Complex Analysis', 'H', 20),
	(11, 'Astrophysics', 'H', 20),
	(12, 'Electricity and Magnetism', 'H', 20),
	(13, 'Classical Mechanics', 'H', 20),
	(14, 'Human Centred Security','M',10);

--names generated randomly at http://random-name-generator.info/random
--dob randomly generated at 
--http://sqa.fyicenter.com/Online_Test_Tools/Test_User_Birthday_Date_Generator.php
INSERT INTO student(studentid, fname, sname, school, advisor, entry_year, dob, programme,address)
    VALUES (1, 'Lindsey', 'Scott', 'Psychology', 2, 2014, '5/5/1982', 3,'5 Evergreen Terrace'),
	(2, 'Eddie', 'Vaughn', 'Physics', 4, 2013, '04/06/1982', 6,'9 Bath Street'),
	(3, 'Sally', 'Davidson', 'Computing Science', 1, 2013, '05/30/1964', 1,'12 Hope Street'),
	(4, 'Lila', 'Perkins', 'Mathematics', 5, 2012, '02/07/1972', 5,NULL),
	(5, 'Sammy', 'Wilkerson', 'Psychology', 7, 2011, '03/18/1985', 3,NULL),
	(6, 'Maurice', 'Cooper', 'Philosophy', 3, 2014, '11/14/1987', 4,'15 Fast Lane'),
	(7, 'Delores', 'Chavez', 'Philosophy', 11, 2013, '07/22/1982', 4,'26 Pemberly Hall'),
	(8, 'Thelma', 'Tate', 'Computing Science', 6, 2014, '11/13/1980', 2,'72 Alexander Road'),
	(9, 'Joshua', 'Scott', 'Mathematics', 9, 2014, '5/5/1982', 5,'91 Lilybank Gardens'),
	(10, 'Sherman', 'Goodman', 'Physics', 8, 2012, '03/14/1976', 6,'17 Dundonald Way'),
	(11, 'Kirk', 'Moss', 'Physics', 8, 2013, '02/06/1982', 6,NULL),
	(12, 'Cameron', 'Huff', 'Computing Science', 10, 2014, '5/5/1982', 2,NULL),
	(13, 'Tabitha', 'Rodriquez', 'Computing Science', 1, 2014, '12/23/1984', 1,NULL),
	(14, 'Kirk', 'Tobias', 'Mathematics', NULL, 2014, '11/20/1979', 5,NULL)
	;

INSERT INTO studentcourses(student, course)
    VALUES (1, 4),(1,5),(5,4),(6,6),(2,11),(2,12),(10,11),(10,13),(11,11),(11,12),(4,7),(4,9),(4,10),(9,9),(9,10),
	(3,1),(3,2),(3,3),(8,2),(8,3),(10,1),(10,2),(1,1),(1,2),(14,8),(14,9),(14,10);

INSERT INTO lecturercourses(lecturer, course)
    VALUES (2, 4),(7,5),(14,4),(3,6),(4,11),(8,12),(15,13),(5,7),(5,8),(9,9),(12,10),(1,1),(6,2),(10,3);

INSERT INTO programmecourses(programme, course)
    VALUES (3, 4),(3,5),(4,6),(6,11),(6,12),(6,13),(5,8),(5,9),(5,10),(7,7),(7,9),(1,1),(1,2),(2,1),(2,2),(2,3);

