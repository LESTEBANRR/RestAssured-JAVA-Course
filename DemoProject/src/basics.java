import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Validate if Add Place API is working as expected
		
		//given -all input details
		//when - Submit the API - resources and http method
		//then - validate the response
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String resource="maps/api/place/add/json";
		String resource2="maps/api/place/update/json";
		
		String response=
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPLaceJson()).when().post(resource)
		.then()./*log().all().*/assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//System.out.println(response);
		JsonPath js= new JsonPath(response);
		String placeId= js.getString("place_id");
		
		System.out.println("placeId:"+placeId);		
		
		// Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.UpdateAddess(placeId)).when().put(resource2) 
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		
		
	}

}
