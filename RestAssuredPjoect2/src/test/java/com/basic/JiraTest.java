package com.basic;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main(String[] args) {
// TODO Auto-generated method stub
		RestAssured.baseURI = "http://localhost:8066";
//Login Scenario
		SessionFilter session = new SessionFilter();
		String response = given().relaxedHTTPSValidation().header("Content-Type", "application/json")
				.body("{\r\n" + "    \"username\": \"NagaGanjala\",\r\n" + "    \"password\": \"06v399813\"\r\n" + "}")
				.log().all().filter(session).when().post("/rest/auth/1/session").then().log().all().extract().response()
				.asString();
		//Add a comment to an existing bug
				String expectedMessage = "First comment through Automation : Hi How are you?";
				String addCommentResponse = given().pathParam("bugID", "10004").log().all() //Here bugID is a Path parameter/variable
						.header("Content-Type", "application/json")
						.body("{\r\n" + "    \"body\": \"" + expectedMessage + "\",\r\n" + "    \"visibility\": {\r\n"
								+ "        \"type\": \"role\",\r\n" + "        \"value\": \"Administrators\"\r\n" + "    }\r\n"
								+ "}")
						.filter(session).when().post("/rest/api/2/issue/{bugID}/comment").then().log().all().assertThat() //instead of path parameter directly we can pass hard coded value as well
						.statusCode(201).extract().response().asString();
				
				JsonPath js = new JsonPath(addCommentResponse);
				String commentId = js.getString("id");
		//Add Attachment to an existing bug
				/*given().header("X-Atlassian-Token", "no-check").filter(session).pathParam("bugID", "10004")
						.header("Content-Type", "multipart/form-data").multiPart("file", new File("jira1.txt")).when()
						.post("rest/api/2/issue/{bugID}/attachments").then().log().all().assertThat().statusCode(200);
				*/
				
		//Get Issue/Bug Details for a given id
				String issueDetails = given().filter(session).pathParam("bugID", "10004").queryParam("fields", "comment").log()
						.all().when().get("/rest/api/2/issue/{bugID}").then().log().all().extract().response().asString();
				System.out.println("Bug Details:*******************\n"+issueDetails);
				JsonPath js1 = new JsonPath(issueDetails);
				int commentsCount = js1.getInt("fields.comment.comments.size()");
				for (int i = 0; i < commentsCount; i++) {
					String commentIdIssue = js1.get("fields.comment.comments[" + i + "].id").toString();
					if (commentIdIssue.equalsIgnoreCase(commentId)) {
						String message = js1.get("fields.comment.comments[" + i + "].body").toString();
						System.out.println(message);
						Assert.assertEquals(message, expectedMessage);
					}
				}
	}

}