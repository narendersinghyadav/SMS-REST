package com.flipkart.client;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.flipkart.model.Course;
import com.flipkart.model.Student;
import com.flipkart.service.ProfessorInterface;
import com.flipkart.service.ProfessorOperation;

//Professor rest controller
@Path("/professor")
public class ProfessorRestClient {
	private static Logger logger=Logger.getLogger(ProfessorRestClient.class);
	ProfessorInterface professoroperation=new ProfessorOperation();
	//view enrolled students by courseid
	@GET
	@Path("/viewenrolledstudents/{courseid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> viewEnrolledStudents(@PathParam("courseid") int courseid){
		logger.info("hit get service");
		List<Student> studentlist=professoroperation.viewEnrolledStudents(courseid);
		return studentlist;
	}

	//view selected courses
	@GET
	@Path("/viewselectedcourse/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Course> viewSelectedCourse(@PathParam("username") String username){
		logger.info("hit get service");
		List<Course> course=professoroperation.viewSelectedCourse(username);
		return course;
	}

	//choose course
	@POST
	@Path("/choosecourse/{username}/{courseid}")
	public Response createStudent(@PathParam("username") String username,@PathParam("courseid") int courseid) {
		professoroperation.chooseCourse(username, courseid);
		logger.info("hit post service");
		String result="Track saved";
		return Response.status(201).entity(result).build();
	}
}
