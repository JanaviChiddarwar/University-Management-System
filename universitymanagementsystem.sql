create database universitymanagementsystem;

use universitymanagementsystem;

create table login
(
	username varchar(40),
    password varchar(40)
);
select * from login;
insert into login values('Gk & Sons', 'Pass@123');

create table teacher
(
	name varchar(25),
    surname varchar(25),
    empid varchar(15),
    dob varchar(20),
    address varchar(50),
    phone varchar(15),
    email varchar(30),
    class_x varchar(10),
    class_xii varchar(10),
    aadhar varchar(15),
    education varchar(20),
    department varchar(20)
);
select * from teacher;

create table student
(
	name varchar(25),
    surname varchar(25),
    rollno varchar(15),
    dob varchar(20),
    address varchar(50),
    phone varchar(15),
    email varchar(30),
    class_x varchar(10),
    class_xii varchar(10),
    aadhar varchar(15),
    course varchar(20),
    branch varchar(20)
);
select * from student;

create table studentleave
(
	rollno varchar(20),
    date varchar(20),
    Time varchar(20)
);
select * from studentleave;

create table teacherleave
(
	empID varchar(20),
    date varchar(20),
    Time varchar(20)
);
select * from teacherleave;

create table subject
(
	rollno varchar(20),
    semester varchar(20),
    subj1 varchar(50),
    subj2 varchar(50),
    subj3 varchar(50),
    subj4 varchar(50),
    subj5 varchar(50)
);
select * from subject;

create table marks
(
	rollno varchar(20),
    semester varchar(20),
    mrk1 varchar(50),
    mrk2 varchar(50),
    mrk3 varchar(50),
    mrk4 varchar(50),
    mrk5 varchar(50)
);
select * from marks;

create table fee(course varchar(20), semester1 varchar(20), semester2 varchar(20), semester3 varchar(20), semester4 varchar(20), semester5 varchar(20), semester6 varchar(20), semester7 varchar(20), semester8 varchar(20));
insert into fee values("BTech", "49000", "43000","43000","43000","43000","43000","43000","43000");
insert into fee values("Bsc", "44000", "35000","35000","35000","35000","35000","","");
insert into fee values("BCA", "39000", "34000","34000","34000","34000","34000","","");
insert into fee values("MTech", "70000", "60000","60000","60000","","","","");
insert into fee values("MSc", "44000", "45000","45000","45000","","","","");
insert into fee values("MCA", "66000", "42000","42000","49000","","","","");
insert into fee values("Bcom", "32000", "20000","20000","20000","20000","20000","","");
insert into fee values("Mcom", "46000", "30000","30000","30000","","","","");
select * from fee;

create table feecollege(rollno varchar(20), course varchar(20), branch varchar(20), semester varchar(20), total varchar(20));
select * from feecollege;