package com.datadriven.test;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class AddNewEmployee {
	
	@Test
	public void postReqAddNewEmployee() {
		 RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		 RequestSpecification httpRequest = RestAssured.given();
		 //Here we created data which we can send along with post request.
		 JSONObject requestParams=new JSONObject();
		 //Every run we have to change the data
		 requestParams.put("name", "NagaGanjala1");
		 requestParams.put("salary", "90000");
		 requestParams.put("age", "30");
		 //Add a header stating the Request body is a JSON
		 httpRequest.header("Content-Type","application/json");
		 //Add the json to the body of the request
		 httpRequest.body(requestParams.toJSONString());
		 //Post Request
		 Response response=httpRequest.request(Method.POST,"/create"); 
		 //Capture Response Body to perform validations
		 String responseBody=response.getBody().asString();
		 //Validating Response Body
		 Assert.assertEquals(responseBody.contains("NagaGanjala1"), true);
		 Assert.assertEquals(responseBody.contains("90000"), true);
		 Assert.assertEquals(responseBody.contains("30"), true);
		 int statusCode=response.getStatusCode();
		 Assert.assertEquals(statusCode, 200);
		 
	}

}
