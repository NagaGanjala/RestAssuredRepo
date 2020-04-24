package com.basic;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.files.ReUsableMethods;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
public class JiraBasic {
	Properties prop=new Properties();
	/**
	 * Pre-reqs:
	 * 1.First install the Jira software in your local machine
	 * 2.Create a Sample Project
	 * 3.While creating sample Project It will ask you to give some key-The same key we have to pass in below code.
	 * ex: Here my Project key is REST
	 * To perform any action,we must pass the session ID
	 */
	@Test
	public void JiraAPICreateIssue()
	{
		//Creating Issue/Defect in Jira 
		
		RestAssured.baseURI= "http://localhost:8066";
		String res=given().header("Content-Type", "application/json").
		header("Cookie","JSESSIONID="+ReUsableMethods.getSessionKEY()). //Here is the session id which we generated.
		body("{"+
    "\"fields\": {"+
       "\"project\":{"+
          "\"key\": \"REST\""+ //Rest is my project Key value --where I have to create the bug.
       "},"+
       "\"summary\": \"CreditCardBug\","+
       "\"description\": \"Creating my first bug via automation code\","+
       "\"issuetype\": {"+
          "\"name\": \"Bug\""+
       "}"+
   "}}").when().
		post("/rest/api/2/issue").then().statusCode(201).extract().response().asString();
		
		   JsonPath js= ReUsableMethods.rawToJson(res);
		   String id=js.get("id");
		   System.out.println(id);	//Here I will get the bug ID
				
		}
}
