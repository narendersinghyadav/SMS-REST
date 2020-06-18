package com.flipkart.model;

//Grade model class
public class Grades {
	//Grade of four courses
	private int course1grade;
	private int course2grade;
	private int course3grade;
	private int course4grade;
	public Grades(int course1grade, int course2grade, int course3grade, int course4grade) {
		this.course1grade = course1grade;
		this.course2grade = course2grade;
		this.course3grade = course3grade;
		this.course4grade = course4grade;
	}

	public int getCourse1grade() {
		return course1grade;
	}
	public void setCourse1grade(int course1grade) {
		this.course1grade = course1grade;
	}
	public int getCourse2grade() {
		return course2grade;
	}
	public void setCourse2grade(int course2grade) {
		this.course2grade = course2grade;
	}
	public int getCourse3grade() {
		return course3grade;
	}
	public void setCourse3grade(int course3grade) {
		this.course3grade = course3grade;
	}
	public int getCourse4grade() {
		return course4grade;
	}
	public void setCourse4grade(int course4grade) {
		this.course4grade = course4grade;
	}

}
