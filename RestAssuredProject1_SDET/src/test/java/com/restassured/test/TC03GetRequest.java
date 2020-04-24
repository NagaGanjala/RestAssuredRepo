package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Google Maps - Places API **/
public class TC03GetRequest {

	@Test
	public void googleMapTest() {	 
		   
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = "https://maps.googleapis.com";
		 
		 // Request Object
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 // Response Object.
		 //How to generate Google maps API Key
		 //https://www.youtube.com/watch?v=_UTQOf19_xg
		 
		 //We can pass either xml/json request
		 //Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=17.4950178,78.3212881&radius=3000&type=supermarket&key=AIzaSyCS2k5v6c1pChnkKYBW4byr562L0BnG2Ko");
		 Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/json?location=17.4950178,78.3212881&radius=3000&type=supermarket&key=AIzaSyCS2k5v6c1pChnkKYBW4byr562L0BnG2Ko");
		 
		 //To get your latitude and longitude
		 //https://www.maps.ie/coordinates.html
		 
		 // Print Response body in console
		 String responseBody = response.getBody().asString();
		 System.out.println("Response Body is : " + responseBody);
		 
		 //Capture the details of headers from response and validating the same
		 String contentType=response.header("Content-Type"); //capture details of Content-Type Header
		 System.out.println("Content-Type is:"+contentType);
		 //Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
		 Assert.assertEquals(contentType, "application/json; charset=UTF-8");
		 //similarly we have to validate many other headers
		 
		 String contentEncode=response.header("Content-Encoding"); //capture details of Content-Type Header
		 System.out.println("contentEncode is:"+contentEncode);
		 Assert.assertEquals(contentEncode, "gzip");
		 
		
		
	}
}
