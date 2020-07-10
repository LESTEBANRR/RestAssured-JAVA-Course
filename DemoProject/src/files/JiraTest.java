package files;
import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

public class JiraTest {

	public static void main (String[] args) {
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session=new SessionFilter();
		
		String issueId="10101";
		//Authorization
		String response=
				given().relaxedHTTPSValidation().header("Content-Type","application/json").body(
						"{\r\n \"username\":\"l_esteban_rr\",\r\n \"password\":\"1174163RRLE\"\r\n }\r\n").log().all()
				.filter(session)
				.when().post("/rest/auth/1/session").then().log().all()
				.extract().response().asString();
		
		//ADD Comment to an Issue
		String expectedMessage="This is my first comment";
		String addCommentResponse=
		given().log().all().pathParam("key", issueId).header("Content-Type","application/json").body("{\r\n"
											+  "  \"body\": \""+expectedMessage+"\",\r\n"
											+  "  \"visibility\":{\r\n"
											+  "	\"type\": \"role\",\r\n"
											+  "	\"value\": \"Administrators\"\r\n"
											+  "   }\r\n"
											+  "}\r\n")
		.filter(session)
		.when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js1=new JsonPath(addCommentResponse);
		String commentId=js1.get("id").toString();
		
		//Add Attachment to Issue
		given().header("X-Atlassian-Token","no-check").filter(session).pathParam("key", issueId)
		.header("Content-Type","multipart/fomr-dat")
		.multiPart("file", new File("C:\\Users\\Azul\\Documents\\L_E\\RestAssuredCourse\\DemoProject\\src\\files\\JiraTextAttach"))
		.when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all()	.assertThat().statusCode(200);
		
		//Get Issue
		String issueDetails=
		 given().filter(session).pathParam("key", issueId)
		 .queryParam("fields", "comment")
		.when().get("/rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
				
		JsonPath js =new JsonPath(issueDetails);
		int commentsCount=js.getInt("fields.comment.comments.size()");
		for (int i = 0; i < commentsCount; i++) {
			String comId=js.get("fields.comment.comments["+i+"].id").toString();
			if(comId.equalsIgnoreCase(commentId)) {
				String message =js.get("fields.comment.comments["+i+"].body");
				Assert.assertEquals(message, expectedMessage);
				break;
			}			
		}
		
		
	}
	
}
