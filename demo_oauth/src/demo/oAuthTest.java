package demo;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;

public class oAuthTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		
		String accessTokenResponse=
		given()
		.queryParam("code", "")
		.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token"/*Access Token URL*/)
		.asString()
		;
		JsonPath js=new JsonPath(accessTokenResponse);
		String accessToken=js.getString("access_token");
		
		String response=
		given()
		.queryParam("access_token", accessToken)
		.when().log().all()
		.get("http://rahulshettyacademy.com/getCourse.php")
		.asString()
		;

		System.out.println(response);		
		
	}

}
