package com.restassured.test;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
/** Weather API **/
public class TC06GetRequest_ExtractValuesOfEachNodeInJSON {

	@Test
	public void getWeatherDetails() {	 
		   
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		 
		 // Request Object
		 RequestSpecification httpRequest = RestAssured.given();
		 
		 // Response Object.		 
		 Response response = httpRequest.request(Method.GET, "/Delhi");
		 
		JsonPath jsonpath=response.jsonPath();
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity"));
		System.out.println(jsonpath.get("WeatherDescription"));
		System.out.println(jsonpath.get("WindSpeed"));
		System.out.println(jsonpath.get("WindDirectionDegree"));
		 
		Assert.assertEquals(jsonpath.get("City"), "Delhi");
		
	}
}
