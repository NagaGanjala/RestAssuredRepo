package com.files;

import io.restassured.path.json.JsonPath;


public class ReUsableMethods {

	
	public static JsonPath rawToJson(String response)
	{
		JsonPath js1 =new JsonPath(response);
		return js1;
	}

	public static String getSessionKEY() {
		// TODO Auto-generated method stub
		return "0105E7FF45B7734C61129B9F334DF522";
	}
	
	
}
