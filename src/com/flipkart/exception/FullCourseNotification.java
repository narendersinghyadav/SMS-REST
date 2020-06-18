package com.flipkart.exception;

import org.apache.log4j.Logger;

//Notification class
public class FullCourseNotification extends Exception{
	private Logger logger =Logger.getLogger(FullCourseNotification.class);

	private int courseid;
	public FullCourseNotification(int courseid) {
		this.courseid = courseid;
	}
	//Display notification message
	public void displayMessage() {
		logger.error(courseid+" course is full");
	}
}
