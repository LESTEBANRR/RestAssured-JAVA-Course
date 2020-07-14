package demo;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class SerializeTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		String response=
		 given().queryParam("key", "qaclick123")
		.body("")
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response().asString()
		;
		
		System.out.println(response);
		
	}
}
