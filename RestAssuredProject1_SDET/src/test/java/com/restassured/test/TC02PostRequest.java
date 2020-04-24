package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Register Customer API **/
public class TC02PostRequest {

	@Test
	public void registrationSuccessfull() {	 
		   
		 // Specify the base URI
		 RestAssured.baseURI = "http://restapi.demoqa.com/customer";
		 
		 //Request Object
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 //Request Body/Request Payload sending along with post request
		 JSONObject requestParams=new JSONObject();
		 //Param Names must be case sensitive
		 requestParams.put("FirstName", "Naga");
		 requestParams.put("LastName", "Ganjala");
		 requestParams.put("UserName", "NagaGanjala");
		 requestParams.put("Password", "Secret143");
		 requestParams.put("Email", "Naga.Ganjala@gmail.com");
		 
		 httpRequest.header("Content-Type","application/json");
		 //attach above data to the request
		 httpRequest.body(requestParams.toJSONString());
		 
		 //Response Object
		 Response response = httpRequest.request(Method.POST, "/register");
		 
		//Print Response in Console window
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is: " + responseBody);
		 
		 //status code validation
		 int statusCode=response.getStatusCode();
		 System.out.println("Status Code Value Is:"+statusCode);
		 Assert.assertEquals(statusCode, 201);
		 
		 //Success code validation
		 String successCode=response.jsonPath().get("SuccessCode");
		 System.out.println("Success Code Value is:"+successCode);
		 Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		 
		 
	}
}
