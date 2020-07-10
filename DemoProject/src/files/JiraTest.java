package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;

public class JiraTest {

	public static void main (String[] args) {
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session=new SessionFilter();
		
		
		
		String response=
				given().header("Content-Type","application/json").body(
						"{\r\n \"username\":\"l_esteban_rr\",\r\n \"password\":\"1174163RRLE\"\r\n }\r\n").log().all()
				.filter(session)
				.when().post("/rest/auth/1/session").then().log().all()
				.extract().response().asString();
		
		//ADD Comment to an Issue
		given().log().all().pathParam("key", "10101").header("Content-Type","application/json").body("{\r\n"
											+  "  \"body\": \"This is my first comment\",\r\n"
											+  "  \"visibility\":{\r\n"
											+  "	\"type\": \"role\",\r\n"
											+  "	\"value\": \"Administrators\"\r\n"
											+  "   }\r\n"
											+  "}\r\n")
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201);
		
		//Add Attachment
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", "10101")
		.header("Content-Type","multipart/fomr-dat")
		.multiPart("file", new File("C:\\Users\\Azul\\Documents\\L_E\\RestAssuredCourse\\DemoProject\\src\\files\\JiraTextAttach"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all()	.assertThat().statusCode(200);
		
	}
	
}
