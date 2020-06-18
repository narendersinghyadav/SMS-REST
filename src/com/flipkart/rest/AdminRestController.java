package com.flipkart.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.URIReferenceException;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDao;
import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.model.Course;
import com.flipkart.model.Professor;
import com.flipkart.model.Student;
import com.flipkart.service.AdminOperation;

//admin rest controller
@Path("/admin")
public class AdminRestController {
	private static Logger logger=Logger.getLogger(AdminRestController.class);
	AdminOperation adminoperation =new AdminOperation();
	//add student to db
	@POST //request type
	@Path("/addstudent") //path
	@Consumes("application/json") //consume json type	
	public Response createStudent(Student student) {
		adminoperation.addStudent(student); 
		logger.info("hit post service");
		String result="Track saved" +student;
		return Response.status(201).entity(result).build();
	}

	//delete student from db
	@DELETE //request type delete
	@Path("/deletestudent/{username}") //path
	public Response deleteStudent(@PathParam("username") String username)
			throws URIReferenceException{
		Student student=new Student(username);
		adminoperation.deleteStudent(student);
		return Response.status(200).entity("Track id"+username+"successfully deleted").build();
	}

	//update student
	@PUT
	@Path("/updatestudent")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Student updateStudent(Student student) {
		adminoperation.updateStudent(student);
		return student;
	}
	//List student
	@GET
	@Path("/liststudent")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentDetails() {

		List<Student> studentlist=adminoperation.listStudent();

		return studentlist;
	}

	//add professor
	@POST
	@Path("/addprofessor")
	@Consumes("application/json")	
	public Response createProfessor(Professor professor) {
		adminoperation.addProfessor(professor);
		logger.info("hit post service");
		String result="Track saved" +professor;
		return Response.status(201).entity(result).build();
	}

	//delete professor
	@DELETE
	@Path("/deleteprofessor/{username}")
	public Response deleteProfessor(@PathParam("username") String username)
			throws URIReferenceException{
		adminoperation.deleteProfessor(new Professor(username));
		return Response.status(200).entity("Track id"+username+"successfully deleted").build();
	}

	//update professor
	@PUT
	@Path("/updateprofessor")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Professor updateProfessor(Professor professor) {
		adminoperation.updateProfessor(professor);
		return professor;
	}

	//view catalog
	@GET
	@Path("/viewcatalog")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> listCourse(){
		CatalogDao catalogdao=new CatalogDaoImpl();
		return catalogdao.getCatalog();
	}

	//add course
	@POST
	@Path("/addcourse")
	@Consumes("application/json")	
	public Response createCourse(Course course) {
		adminoperation.addCourseToDb(course);
		logger.info("hit post service");
		String result="Track saved" +course;
		return Response.status(201).entity(result).build();
	}

	//delete course
	@DELETE
	@Path("/deletecourse/{courseid}")
	public Response deleteProfessor(@PathParam("courseid") int courseid)
			throws URIReferenceException{
		adminoperation.deleteCourseFromDb(new Course(courseid));
		return Response.status(200).entity("Track id"+courseid+"successfully deleted").build();
	}

	//update course
	@PUT
	@Path("/updatecourse")
	@Consumes("application/json")
	@Produces(MediaType.APPLICATION_JSON)
	public Course updateCourse(Course course) {
		adminoperation.updateCourseInDb(course);
		return course;
	}
}
