package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Weather API **/
public class TC05GetRequest_ValidateJSONResponse {

	@Test
	public void getWeatherDetails() {	 
		   
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		 
		 // Request Object
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 // Response Object.		 
		 Response response = httpRequest.request(Method.GET, "/Delhi");
		 
		 // Print Response body in console
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is : " + responseBody);
		 
		 //Note: Response and ResponseBody both are not same.
		 
		 Assert.assertEquals(responseBody.contains("Delhi"), true);
		 
	
		 
		
		
	}
}
