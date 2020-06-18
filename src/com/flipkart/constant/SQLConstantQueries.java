package com.flipkart.constant;

//SQL Query constants
public class SQLConstantQueries {
	//UserDao queries
	public static final String LIST_BY_USERNAME="select password,role from user where username=?";

	//CatalogDao queries
	public static final String LIST_CATALOG="select * from courses";
	public static final String INCREMENT_NUMBER_OF_STUDENTS="update courses set numberofStudents=numberofStudents+1 where courseid=? and numberofStudents<10";
	public static final String DECREMENT_NUMBER_OF_STUDENTS = "update courses set numberofStudents=numberofStudents-1 where courseid=?";

	//StudentDao queries
	public static final String ADD_COURSE_BY_STUDENT="insert into studentcourses values(?,?,?,?,?,?)";
	public static final String UPDATE_COURSE_BY_STUDENT = "update studentcourses set course1id=?,course2id=?,course3id=?,course4id=?,timestamp=? where username=?";
	public static final String LIST_GRADE = "select coursegrade,courseid from grade where username=?";
	public static final String LIST_SELECTED_COURSES="select course1id,course2id,course3id,course4id from studentcourses where username=?";

	//AdminDao sql queries
	public static final String ADD_USER = "insert into user values(?,?,?)";
	public static final String ADD_STUDENT="insert into student values(?,?,?,?,?,?)";
	public static final String DELETE_USER = "delete from user where username=?";
	public static final String DELETE_STUDENT = "delete from student where username=?";
	public static final String UPDATE_STUDENT = "update student set name=?,address=?,year=?,mobilenumber=?,gender=? where username=?";
	public static final String ADD_PROFESSOR = "insert into professor values(?,?,?,?)";
	public static final String DELETE_PROFESSOR = "delete from professor where username=?";
	public static final String UPDATE_PROFESSOR = "update professor set name=?,address=?,mobilenumber=? where username=?";
	public static final String ADD_ADMIN = "insert into admin values(?,?,?,?)";
	public static final String DELETE_ADMIN = "delete from admin where username=?";
	public static final String UPDATE_ADMIN = "update admin set name=?,address=?,mobilenumber=? where username=?";
	public static final String ADD_COURSE_TO_DB = "insert into courses values(?,?,?,?,?,?)";
	public static final String DELETE_COURSE_TO_DB = "delete from courses where courseid=?";
	public static final String UPDATE_COURSE_TO_DB = "update courses set coursename=?,courseschedule=?,numberofStudents=?,fees=?,catalogid=? where courseid=?";

	//ProfessorDao queries
	public static final String LIST_ENROLLED_STUDENTS = "select username,name from studentcourses natural join student where studentcourses.course1id=? or studentcourses.course2id=? or studentcourses.course3id=? or studentcourses.course4id=?";
	public static final String ADD_GRADE = "insert into grade values(?,?,?)";
	public static final String CHOOSE_COURSE_FOR_TEACHING = "insert into professorcourse values(?,?)";
	public static final String LIST_COURSES_BY_USERNAME = "select courses.catalogid,courses.courseid,courses.coursename,courses.courseschedule,courses.numberofStudents,courses.fees from courses natural join professorcourse where professorcourse.username=?";
	public static final String GET_STUDENT_DETAILS = "select * from student where username=?";
	public static final String LIST_STUDENT = "select  * from student";

	//payment methods
	public static final String UPDATE_PAYMENT = "insert into payment(paymentmode,paymentdescription) values(?,?)";
	public static final String GET_PAYMENT_ID="select max(paymentid) as paymentid from payment";
	public static final String REGISTRATION_ENTRY = "insert into registration(username,paymentid,timestamp) values(?,?,?)";


}
