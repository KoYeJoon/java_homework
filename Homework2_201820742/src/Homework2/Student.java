package Homework2;

import java.util.ArrayList;

public class Student {
	private int studentID;
	private String name;
	private int takenCourse;
	static ArrayList<Student> students = new ArrayList<>();

	Student(){
		
	}
	Student(String s){
		this.name=s;
	}
	Student(Student s){
		this.studentID=s.getStudentID();
		this.name=s.getName();
		this.takenCourse=s.getTakenCourse();
	}
	public Student(String [] std) {
		this.studentID = Integer.parseInt(std[0]);
		this.name=std[1];
		this.takenCourse=Integer.parseInt(std[2]);
	}
	
	public String toString() {
		return String.format("[학생 정보] \n 학번 : %d\n 학생 이름 : %s\n",this.getStudentID(), this.getName() );
		
	}
	
	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTakenCourse() {
		return takenCourse;
	}

	public void setTakenCourse(int takenCourse) {
		this.takenCourse = takenCourse;
	}

	
	
}
