package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Weather API **/
public class TC07GetRequest_Autherization {

	@Test
	public void autherizationTest() {	 
		   
		 //Base URI
		 RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
		 
		 //Basic Authentication --we have many types of Authentications in Rest API's
		 PreemptiveBasicAuthScheme authScheme=new PreemptiveBasicAuthScheme();
		 authScheme.setUserName("ToolsQA");
		 authScheme.setPassword("TestPassword");
		 
		 RestAssured.authentication=authScheme;
		 
		 
		 // Request Object
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 //Response Object
		 Response response = httpRequest.request(Method.GET, "/");
		 
		 // Print Response in Console Window
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is:  " + responseBody);
		 
		 //status code validation
		 int statusCode=response.getStatusCode();
		 System.out.println("Status Code Value Is:"+statusCode);
		 Assert.assertEquals(statusCode, 200);
		 
		
		
	}
}
