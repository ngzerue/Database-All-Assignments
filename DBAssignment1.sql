create database school;
create table student(
  studentId int  auto_increment,
  first_name varchar(50) not null,
  last_name  varchar(50) not null,
  address varchar(255),
  primary key (studentId)
  );
  
  create table Course (
   courseId int not null,
   course_name varchar(50) not null,
   course_credit int  not null,
   primary key (courseId)
   );
  
insert into student (first_name, last_name, address)
             values('Tesfay','Hailu','Mekelle'),
                   ('Girmay ','Desta','Maichew'),
                   ('Teklay','Abreha','Adigrat'),
                   ('Genet','Belay','Axum'),
                   ('Seada ','Muzey','Shire');
 
insert into Course(courseId, course_name, course_credit) 
            values(1, 'Introduction to Computer', 3),
                  (2, 'Introduction to Java', 3),
                  (3, 'Logic', 2),
                  (4, 'Databse', 2),
                  (5, 'Mulesoft', 4);
 select * from student;
 select *from Course;
 select *from student where studentId = 3;
 select * from Course where courseId = 3;
 update student set address= 'Humera' where studentId= 4; 
 update Course set  course_name ='Basic Mathematics' where courseId = 3;
 delete from student where studentId = 3;
 delete from Course Where courseId =5;
 