package com.flipkart.rest;


import java.util.HashMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.flipkart.model.Student;
import com.flipkart.service.StudentOperation;

//student rest controller
@Path("/student")
public class StudentRestController {
	StudentOperation studentoperation =new StudentOperation();
	
	//get student details
	@GET
	@Path("/details/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudentDetails(@PathParam("username") String username) {
		Student student=studentoperation.getStudentDetails(username, "");
		return student;
	}
	
	//get grades by username
		@GET
		@Path("/grades/{username}")
		@Produces(MediaType.APPLICATION_JSON)
		public HashMap<Integer,Integer> getGrades(@PathParam("username") String username) {
			return studentoperation.viewGrade(new Student(username));
		}
	
	//choose courses
	@POST
	@Path("/addcourse/{courseid1}/{courseid2}/{courseid3}/{courseid4}")
	@Consumes("application/json")	
	public Response createStudent(Student student,@PathParam("courseid1") int courseid1,@PathParam("courseid2") int courseid2,@PathParam("courseid3") int courseid3,@PathParam("courseid4") int courseid4) {
		studentoperation.addCourses(student, courseid1, courseid2, courseid3, courseid4);
		System.out.println("hit post service");
		String result="Track saved" +student;
		return Response.status(201).entity(result).build();
	}

	
	
}
