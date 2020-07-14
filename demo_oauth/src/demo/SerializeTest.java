package demo;

import io.restassured.RestAssured;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	public static void main(String[] args) {
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
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
		
		String response=
		 given().queryParam("key", "qaclick123")
		.body(place)
		.when().post("/maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString()
		;
		
		//System.out.println(response);
		
	}
}
