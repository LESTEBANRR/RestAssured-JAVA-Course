import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.ReUsableMethods;
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
		
		String newAddress="Summer Walk, Africa";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.UpdateAddess(placeId,newAddress)).when().put(resource2) 
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		// Get Place
		String getPlaceResponse= 
		 given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.queryParam("place_id", placeId).when().get("maps/api/place/get/json")
		.then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = ReUsableMethods.rawToJson(getPlaceResponse);
		String actualAddress=js1.getString("address");
		
		System.out.println(actualAddress);
		Assert.assertEquals(actualAddress, newAddress);
		//Cucumber Junit, Testing
	}

}
