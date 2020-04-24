package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Weather API **/
public class TC01GetRequest {

	@Test
	public void getWeatherDetails() {	 
		   
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		 
		 // Get the RequestSpecification of the request that you want to sent
		 // to the server. The server is specified by the BaseURI that we have
		 // specified in the above step.
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 // Make a request to the server by specifying the method Type and the method URL.
		 // This will return the Response from the server. Store the response in a variable.
		 Response response = httpRequest.request(Method.GET, "/Hyderabad");
		 
		 // Now let us print the body of the message to see what response
		 // we have recieved from the server
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is =>  " + responseBody);
		 
		 //status code validation
		 int statusCode=response.getStatusCode();
		 System.out.println("Status Code Value Is:"+statusCode);
		 Assert.assertEquals(statusCode, 200);
		 
		 //Status Line Validation
		 String statusLine=response.getStatusLine();
		 System.out.println("Status line is:"+statusLine);
		 Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
}
