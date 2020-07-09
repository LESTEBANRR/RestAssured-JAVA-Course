package files;

import org.junit.jupiter.api.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	@Test
	public void addBook() {
		RestAssured.baseURI="http://216.10.245.166";
		String response= given().header("Content-Type","application/json").
		body(payload.Addbook()).
		when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUsableMethods.rawToJson(response);
		String id=js.getString("ID");
		System.out.println("ID: "+id);
	}
}
