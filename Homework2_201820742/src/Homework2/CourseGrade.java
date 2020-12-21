package Homework2;

import java.util.ArrayList;

public class CourseGrade{
	private int courseID;
	private int studentID;
	private double courseGrade;
	private String courseName;

	static ArrayList<CourseGrade> courses = new ArrayList<>();
	public CourseGrade() {
		
	}
	public CourseGrade(int i) {
		this.studentID=i;
	}
	public CourseGrade(CourseGrade c) {
		this.courseID = c.getCourseID();
		this.studentID = c.getStudentID();
		this.courseGrade = c.getCourseGrade();
		this.courseName=c.getCourseName();
	}
	public CourseGrade(String s) {
		this.courseName=s;
	}
	public CourseGrade(String[] cg) {
		this.courseID = Integer.parseInt(cg[0]);
		this.studentID = Integer.parseInt(cg[1]);
		this.courseGrade = Double.parseDouble(cg[2]);
		this.courseName = cg[3];
	}
	
	public String toString() {
		return String.format("[수강 정보] \n 과목번호 : %d\n 과목 명 :  %s\n 과목별 학점 : %f\n\n",this.courseID, this.courseName,this.courseGrade);
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public double getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(double courseGrade) {
		this.courseGrade = courseGrade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	
	
}
