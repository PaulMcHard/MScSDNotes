--Extraction of information from database follows a repeating pattern,
--SELECT COUNT(*) is used firstly to get number of rows in a table,
--This is used to establish the size of relevant arrays in java.
--Then the entirety of the data from the given table is extracted using SELECT *,
--allowing the program to create full objects for each tuple in a given table.
--Process is repeated for each table we wish to use in the program.
--These are called in the program's update methods
--updating the objects and arrays with each new database interaction
--to avoid discrepancy between data stored in the program and in the database.
SELECT COUNT(*) FROM gym.member;
SELECT * FROM gym.member;

SELECT COUNT(*) FROM gym.course;
SELECT * FROM gym.course;

SELECT COUNT(*) FROM gym.member;
SELECT * FROM gym.member;

SELECT COUNT(*) FROM gym.coursebooking;
SELECT * FROM gym.coursebooking;

--This is the SQL code used for adding a new course booking. 
--SELECT COUNT(*) is used to determine the unique booking number, by design 
--the booking number is an integer, which indexes bookings incrementally from 1.
--INSERT INTO is the only method by which a booking is made. Adding the booking
--directly to the database, then updating the arrays and objects by re-accessing
--the information from the database helps avoid duplication of added data as well
--as bugs pertaining to discrepancy between the program data and database data. 
SELECT COUNT(*) FROM gym.coursebooking;
INSERT INTO gym.coursebooking VALUES("+bookCount+","+bookMember.getID()+","+bookCourse.getId()+");