package com.flipkart.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.flipkart.dao.CatalogDaoImpl;
import com.flipkart.dao.UserDaoImpl;
import com.flipkart.model.Course;

//Course catalog class
public class CourseCatalog {
	
	CatalogDaoImpl coursedao=new CatalogDaoImpl();
	Logger logger=Logger.getLogger(CourseCatalog.class);
	
	//Show list of courses present in catalog
	public void SearchValidCourse(){
		List<Course> courselist=coursedao.getCatalog();
		logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$10s %6$10s","Course Id","Course Name","Course Schedule","Number of students","Fees","Catalog id"));
		courselist.forEach(list->logger.info(String.format("%1$10s %2$10s %3$10s %4$10s %5$20s %6$10s",list.getCourseId(),list.getCourseName(),list.getCourseSchedule(),list.getNumberOfStudents(),list.getFees(),list.getCatalogid())));
	}
}
