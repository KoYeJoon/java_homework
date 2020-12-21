package Homework2;

import java.util.ArrayList;

interface Joinable {
	public ArrayList joining(ArrayList arr, ArrayList arr2);
}
interface Filterable {
	public boolean filtering(ArrayList arr, String s);
}
public class ArrayListHandler {

	public ArrayListHandler() {

	}

	public interface Callback {
		void printout(ArrayList s);
	}

	private Callback callback;

	class filter implements Filterable {
		private ArrayList<Student> studentInfo = new ArrayList<>();
		private ArrayList<CourseGrade> courseInfo = new ArrayList<>();
		private String n;

		public filter(ArrayList arr, String name) {
			if (arr.get(0) instanceof Student) {
				studentInfo = (ArrayList<Student>) arr;
				this.n = name;
			} else {
				courseInfo = (ArrayList<CourseGrade>) arr;
				this.n = name;
			}
		}

		@Override
		public boolean filtering(ArrayList arr, String s) {
			if ((arr.get(0) instanceof Student)) {
				ArrayList<Student> studentInfo = (ArrayList<Student>) arr;
				for (Student a : studentInfo) {
					if (a.getName().equalsIgnoreCase(s)) {
						return true;
					}
				}
			}
			if (arr.get(0) instanceof CourseGrade) {
				ArrayList<CourseGrade> courseInfo = (ArrayList<CourseGrade>) arr;
				for (CourseGrade a : courseInfo) {
					if (a.getCourseName().equalsIgnoreCase(s)) {
						return true;
					}
				}
			}
			return false;
		}

		public String getN() {
			return n;
		}

		public void setN(String n) {
			this.n = n;
		}

	}

	static class makeJoinedClass implements Joinable {
		private Student data3;
		private CourseGrade data4;

		public makeJoinedClass(Student data, CourseGrade data2) {
			this.data3 = data;
			this.data4 = data2;
		}

		makeJoinedClass() {

		}

		public makeJoinedClass(ArrayList<Student> studentResult, ArrayList<CourseGrade> courses) {

		}

		int studentID = 0;

		
		public Student getData3() {
			return data3;
		}

		public CourseGrade getData4() {
			return data4;
		}


		@Override
		public ArrayList joining(ArrayList arr, ArrayList arr2) {
			if (arr.get(0) instanceof Student) {
				ArrayList<makeJoinedClass> join = new ArrayList<>();
				ArrayList<Student> stu = new ArrayList<>();
				ArrayList<CourseGrade> cou = new ArrayList<>();
				ArrayList<CourseGrade> result = new ArrayList<>();
				stu = (ArrayList<Student>) arr;
				cou = (ArrayList<CourseGrade>) arr2;
				studentID = stu.get(0).getStudentID();
				for (CourseGrade b : cou) {
					if (studentID == b.getStudentID()) {
						result.add(b);
					}
				}
				for (int i = 0; i < result.size(); i++) {
					join.add(new makeJoinedClass(stu.get(i), result.get(i)));
				}
				return join;
			} else {
				ArrayList<makeJoinedClass> join = new ArrayList<>();
				ArrayList<Student> stu = new ArrayList<>();
				ArrayList<CourseGrade> cou = (ArrayList<CourseGrade>) arr;
				ArrayList<CourseGrade> cou1 = (ArrayList<CourseGrade>) arr2;
				int takenCourse = cou.get(0).getCourseID();
				for (int i = 0; i < cou.size(); i++) {
					studentID = cou.get(0).getStudentID();
					for (Student s : Student.students) {
						if (studentID == s.getStudentID() && takenCourse == s.getTakenCourse()) {
							stu.add(s);
						}
					}
				}

				for (int i = 0; i < cou.size(); i++) {
					join.add(new makeJoinedClass(stu.get(i), cou.get(i)));
				}
				return join;

			}
		}

	}

	
	public ArrayList join(ArrayList l1, ArrayList l2, Joinable join, Callback cb) {
		ArrayListHandler a = new ArrayListHandler();
		ArrayList<CourseGrade> studentInfo = new ArrayList<>();
		ArrayList<makeJoinedClass> joined = new ArrayList<>();
		if (l1.get(0) instanceof CourseGrade) {
			ArrayList<ArrayListHandler.makeJoinedClass> result = new ArrayList<>();
			cb.printout(join.joining(l1, l2));
		} else {
			Joinable joining2 = join;
			studentInfo = joining2.joining(l1,l2);
			cb.printout(studentInfo);
		}

		return joined;

	}

	public ArrayList filter(ArrayList l1, Filterable filter, Callback cb) throws Exception {
		ArrayList<Student> studentInfo = new ArrayList<>();
		ArrayList<CourseGrade> courseInfo = new ArrayList<>();
		int flag = 0;
		ArrayListHandler.filter filtering = (Homework2.ArrayListHandler.filter) filter;
		try {
			boolean result = filtering.filtering(l1, filtering.getN());
			if (result == false) {
				throw new Exception("조건에 맞는 정보가 없습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (l1.get(0) instanceof Student) {
			Student nStudent = new Student((Student) l1.get(0));
			String name = filtering.getN();
			for (Student b : (ArrayList<Student>) l1) {
				if (b.getName().equalsIgnoreCase(filtering.getN())) {
					Student newStudent = new Student();
					flag = 1;
					newStudent.setName(filtering.getN());
					newStudent.setStudentID(b.getStudentID());
					newStudent.setTakenCourse(b.getTakenCourse());
					studentInfo.add(newStudent);
				}
			}
			return studentInfo;

		} else{
			Filterable courseFilt = filter;
			CourseGrade nCourseGrade = new CourseGrade((CourseGrade) l1.get(0));
			String name = filtering.getN();

			for (CourseGrade a : (ArrayList<CourseGrade>) l1) {
				if (name.equalsIgnoreCase(a.getCourseName())) {
					CourseGrade newCourseGrade = new CourseGrade();
					flag = 1;
					newCourseGrade.setStudentID(a.getStudentID());
					newCourseGrade.setCourseGrade(a.getCourseGrade());
					newCourseGrade.setCourseID(a.getCourseID());
					newCourseGrade.setCourseName(a.getCourseName());
					courseInfo.add(newCourseGrade);
				}
			}
			
		}
		return courseInfo;
	
	}

}
