package files;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class StaticJson {
	@Test
	public void addBook() throws IOException{
		
		RestAssured.baseURI="http://216.10.245.166";
		String path="C:\\Users\\Azul\\Documents\\L_E\\RestAssuredCourse\\Addbookdetails.json";
		
		String response= given().header("Content-Type","application/json").
		body(GenerateStringFromResource(path)).
		when().post("/Library/Addbook.php")
		.then().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=ReUsableMethods.rawToJson(response);
		
		String id=js.getString("ID");
		System.out.println("ID: "+id);
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}
}
