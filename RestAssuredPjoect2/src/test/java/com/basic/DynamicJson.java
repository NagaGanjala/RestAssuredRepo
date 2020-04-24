package com.basic;

//Library API --Check for API Contract document
//https://jsoneditoronline.org/ --To understand Json path 
//Data Driven Testing
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.ReUsableMethods;
import com.files.payload;

import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

import io.restassured.path.json.JsonPath;


public class DynamicJson {

@Test(dataProvider="BooksData")
public void addBook(String isbn,String aisle)

{
//Add book
RestAssured.baseURI="http://216.10.245.166";
String resp=given().
header("Content-Type","application/json").
body(payload.Addbook(isbn,aisle)).
when().
post("/Library/Addbook.php").
then().assertThat().statusCode(200).
extract().response().asString();
JsonPath js= ReUsableMethods.rawToJson(resp);
   String id=js.get("ID");
   System.out.println(id);
   
   //deleteBOok
}
@DataProvider(name="BooksData")
public Object[][]  getData()
{
//array=collection of elements
//multidimensional array= collection of arrays
return new Object[][] {{"nag1","6666"},{"nag2","6677"}, {"nag3","6688"} };
}


}




