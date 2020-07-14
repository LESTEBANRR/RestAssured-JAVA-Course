package demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {

	public static void main(String[] args) {	
		
		AddPlace place=new AddPlace();
		place.setAccuracy(50);
		place.setAddress("Addresss 1234");
		place.setLanguage("French");
		place.setPhone_number("4491234567");
		place.setWebsite("http://rahulshettyacademy.com");
		place.setName("FrontLine");
		List<String> types=new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		place.setTypes(types);
		Location location=new Location();
		location.setLat(-38.1932983);
		location.setLng(33.45344);
		place.setLocation(location);
		
		RequestSpecification req = new RequestSpecBuilder()
		.setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON)
		.build();
		
		ResponseSpecification resspec = new ResponseSpecBuilder()
		.expectStatusCode(200)
		.expectContentType(ContentType.JSON)
		.build()
		;
		
		RequestSpecification res=
		 given().spec(req)
		.body(place);
		
		Response response=res.when().post("/maps/api/place/add/json")
		.then().log().all().spec(resspec).extract().response()
		;
		
		String responseString=response.asString();
		System.out.println(responseString);
		//System.out.println(response);
		
	}
}
