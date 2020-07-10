package files;
import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;

public class JiraTest {

	public static void main (String[] args) {
		RestAssured.baseURI="http://localhost:8080/";
		
		
		
		given().pathParam("key", "10101").header("Content-Type","application/json").body("{\r\n"
											+  "  \"body\": \"This is my first comment\",\r\n"
											+  "  \"visibility\":{\r\n"
											+  "	\"type\": \"role\",\r\n"
											+  "	\"value\": \"Administrators\"\r\n"
											+  "   }\r\n"
											+  "}\r\n")
		.post("/rest/api/2/issue/{key}/comment");
		
	}
	
}
