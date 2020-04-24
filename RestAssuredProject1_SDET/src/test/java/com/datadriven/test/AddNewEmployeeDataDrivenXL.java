package com.datadriven.test;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class AddNewEmployeeDataDrivenXL {
	
	
	//Here we can read the data from Excel File by using POI API..Please refer my 7 live projects code
	@DataProvider(name="empdataprovider")
	String[][] getEmpData(){
		
		//String[][] empdata= {{"NagaGanjala2","50000","31"},{"NagaGanjala3","50000","32"},{"NagaGanjala4","50001","33"}};
		
		return null;
		
	}
	
	
	@Test(dataProvider="empdataprovider")
	public void postReqAddNewEmployee(String eName,String eSal,String eAge) {
		 RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		 RequestSpecification httpRequest = RestAssured.given();
		 //Here we created data which we can send along with post request.
		 JSONObject requestParams=new JSONObject();
		 //Every run we have to change the data
		 requestParams.put("name", eName);
		 requestParams.put("salary", eSal);
		 requestParams.put("age", eAge);
		 //Add a header stating the Request body is a JSON
		 httpRequest.header("Content-Type","application/json");
		 //Add the json to the body of the request
		 httpRequest.body(requestParams.toJSONString());
		 //Post Request
		 Response response=httpRequest.request(Method.POST,"/create"); 
		 //Capture Response Body to perform validations
		 String responseBody=response.getBody().asString();
		 System.out.println("eResponseBody is:"+responseBody);
		 //Validating Response Body
		 Assert.assertEquals(responseBody.contains(eName), true);
		 Assert.assertEquals(responseBody.contains(eSal), true);
		 Assert.assertEquals(responseBody.contains(eAge), true);
		 int statusCode=response.getStatusCode();
		 Assert.assertEquals(statusCode, 200);
		 
	}

}
