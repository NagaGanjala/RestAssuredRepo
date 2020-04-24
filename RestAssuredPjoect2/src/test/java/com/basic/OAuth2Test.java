/*
 * *******************UPDATED CODE AS PER GOOGLE UPDATE *****************************
 */
package com.basic;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
//import pojo.Api;
//import pojo.GetCourse;


public class OAuth2Test {

public static void main(String[] args) throws InterruptedException {
//step1: Authorization code grant type
/*
 * Here we have to use the given Contract API and Login to resource server(gmail) by using selenium but Google not supporting gmail login through automation since 2020.
 * So we manually hit the endpoint manually and login into gmmail and get that url and hardcoded in below.
 */
	System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
	WebDriver driver= new ChromeDriver();
	driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
	driver.findElement(By.cssSelector("input[type='email']")).sendKeys("fdfd");
	driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
	Thread.sleep(3000);
	driver.findElement(By.cssSelector("input[type='password']")).sendKeys("fxfe");
	driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
	Thread.sleep(4000);
	//String url=driver.getCurrentUrl();
	
String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2FzAEkOYoupxpck_2c9MR90XwWQdTSExBOdkBIy9jyvQV1hDYHKd6kx3vADTA6A6hl5GfIXXDKWLMYQN3H2egZ9NQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent#";
//step2 get the code from step1
String partialcode=url.split("code=")[1];
String code=partialcode.split("&scope")[0];
System.out.println(code);
String response =
                given() 
                .urlEncodingEnabled(false)
                       .queryParams("code",code)
               
                   .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParams("grant_type", "authorization_code")
                        .queryParams("state", "verifyfjdss")
                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                     // .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                       
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();
// System.out.println(response);
JsonPath jsonPath = new JsonPath(response);
    String accessToken = jsonPath.getString("access_token");
    System.out.println(accessToken);
//step3: Get the accessToken from step2 and use it in your code    
String r2=    given().contentType("application/json").
queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
.when()
           .get("https://rahulshettyacademy.com/getCourse.php")
.asString();
System.out.println(r2);

}

}