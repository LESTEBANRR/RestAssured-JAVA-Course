import io.restassured.RestAssured;
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
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPLaceJson())  
		.when().post(resource)
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		     .header("server", "Apache/2.4.18 (Ubuntu)");
		
		// Add place -> update Place with New Address -> Get place to validate if new address is prsent in response
		
		
		
	}

}
