package stepDefinitions;

import static org.junit.Assert.*;
import java.io.IOException;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	static String place_id;
	JsonPath js;
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("^AddPlace Payload with (.+) (.+) and (.+)$")
	public void addplace_payload_with_and(String name, String language, String address) throws IOException {
		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));
		System.out.println("AddPlace Json Body Correctly Created");
	}

	@Given("^DeletePLace payload$")
	public void deleteplace_payload() throws Throwable {
		res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_id));
		System.out.println("deletePlace Json Body Correctly Created");
	}

	@When("^User calls \"([^\"]*)\" API with \\\"([^\\\"]*)\\\" HTTP request$")
	public void user_calls_some_api_with_some_method_http_request(String resource, String method) throws IOException {

		APIResources resourcdeAPI = APIResources.valueOf(resource);
		System.out.println(resourcdeAPI.getResource());
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		System.out.println("\tRequesting to "+resourcdeAPI+" API ...");
		if (method.equalsIgnoreCase("POST")) {
			response = res.when().post(resourcdeAPI.getResource());
		} else if (method.equalsIgnoreCase("GET")) {
			response = res.when().get(resourcdeAPI.getResource());
		}
	}

	@Then("^The API call is success with status code \\\"([^\\\"]*)\\\"$")
	public void the_api_call_is_success_with_status_code_200(int code) throws Throwable {
		assertEquals(response.getStatusCode(), code);
		System.out.println("\t\tRequest Succesfully Done");
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String key, String value) throws Throwable {
		assertEquals(getJsonPath(response, key), value);
		System.out.println("\t\t\tThe value in the key "+key+" is equals to "+value);
	}

	@And("^Verify place_Id created maps to \"([^\"]*)\" = (.+) using \"([^\"]*)\" API$")
	public void verify_placeid_created_maps_to_some_key_using_some_api(String key, String value, String resource)
			throws IOException {
		place_id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_some_api_with_some_method_http_request(resource, "GET");
		assertEquals(value, getJsonPath(response, key));
		System.out.println("Place ID correctly verifired");
	}
}
