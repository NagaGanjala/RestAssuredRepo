package com.students.tests;

import static com.jayway.restassured.RestAssured.given;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.student.base.TestBase;
import com.student.model.Student;
public class StudentPatchTest  extends TestBase{

	
	/**
	 * Test for Updating student information
	 */
	
	@Test
	public void updateStudent(){
		//Put the student ID here
		String studentId="50";
		
		ArrayList<String> courses = new ArrayList<>();
		courses.add("Java");
		courses.add("C++");
		courses.add("C#");
		
		Student student = new Student();
		student.setFirstName("Mark");
		student.setLastName("Taylor");
		student.setEmail("mnx@gmail.com");
		student.setProgramme("Computer Science");
		student.setCourses(courses);
		
		given()
		.contentType(ContentType.JSON)
		.when()
		.body(student)
		.patch("/"+studentId)
		.then()
		.statusCode(200);
		
	
	}

}
