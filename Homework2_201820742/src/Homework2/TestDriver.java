package Homework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Homework2.ArrayListHandler.Callback;
import Homework2.ArrayListHandler.makeJoinedClass;

public class TestDriver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan;

		try {
			File student = new File("src/Student.txt");
			scan = new Scanner(student);
			while (scan.hasNext()) {
				String studentInfo[] = scan.nextLine().split(" ");
				Student s = new Student(studentInfo);
				Student.students.add(s);
			}
			File courseGrade = new File("src/CourseGrade.txt");
			scan = new Scanner(courseGrade);
			while (scan.hasNext()) {
				String courseGradeInfo[] = scan.nextLine().split(" ");
				CourseGrade c = new CourseGrade(courseGradeInfo);
				CourseGrade.courses.add(c);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("잘못된 파일의 입력 ");
		}

		Scanner scan1 = new Scanner(System.in);

		System.out.print("이름 입력 : ");
		String name = scan1.nextLine();
		boolean flag = false;
		ArrayList<Student> studentResult = new ArrayList<>();
		ArrayListHandler connection = new ArrayListHandler();
		ArrayList<ArrayListHandler.makeJoinedClass> result = new ArrayList<>();
		ArrayListHandler.Callback callback = new Callback() {

			@Override
			public void printout(ArrayList s) {
				System.out.println(((makeJoinedClass) s.get(0)).getData3().toString());
				double sum = 0;
				int subjectNum = 0;
				for (makeJoinedClass c : (ArrayList<makeJoinedClass>) s) {
					System.out.println(c.getData4().toString());
					sum = sum + c.getData4().getCourseGrade();
					subjectNum++;
				}
				
				double avg = avg = sum / (double) subjectNum;

				System.out.printf("[평균학점]\n%f\n\n", avg);
			}
		};
		ArrayListHandler.filter studentFilt = connection.new filter(Student.students,name);
		ArrayListHandler.makeJoinedClass joining = new makeJoinedClass();
		studentResult = connection.filter(Student.students, studentFilt, callback);
		if(studentFilt.filtering(Student.students, name)==true) {
			result = connection.join(studentResult, CourseGrade.courses, joining, callback);
		}
		
		
		// 2번째 실행
		System.out.print("과목 이름 : ");
		String name2 = scan1.nextLine();

		ArrayList<CourseGrade> courseResult = new ArrayList<>();
		ArrayListHandler.filter courseFilt = connection.new filter(CourseGrade.courses, name2);
		ArrayList<ArrayListHandler.makeJoinedClass> result2 = new ArrayList<>();
		ArrayListHandler.Callback callback2 = new Callback () {

			@Override
			public void printout(ArrayList s) {

				double max = 0;
				double min = 5;

				for (makeJoinedClass a : (ArrayList<makeJoinedClass>)s) {
					if (((CourseGrade) a.getData4()).getCourseGrade() < min) {
						min = ((CourseGrade) a.getData4()).getCourseGrade();
					}
					if (((CourseGrade) a.getData4()).getCourseGrade() > max) {
						max = ((CourseGrade) a.getData4()).getCourseGrade();
					}

				}
				System.out.println("[최대 학점] ");
				System.out.println(max);
				System.out.println("[최소 학점] ");
				System.out.println(min);

			}
		};
		ArrayListHandler.makeJoinedClass coursejoin = new makeJoinedClass();
		courseResult = connection.filter(CourseGrade.courses, courseFilt, callback2);
		if(courseFilt.filtering(CourseGrade.courses, name2)==true) {
			result2 = connection.join(courseResult, CourseGrade.courses, joining, callback2);
		}
	}

}
