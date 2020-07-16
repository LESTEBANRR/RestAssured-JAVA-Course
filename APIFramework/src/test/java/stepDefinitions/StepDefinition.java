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
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild data = new TestDataBuild();

	@Given("^AddPlace Payload with (.+) (.+) and (.+)$")
	public void addplace_payload_with_and(String name, String language, String address) throws IOException {

		res = given().spec(requestSpecification()).body(data.addPlacePayload(name, language, address));

	}

	@When("^User calls \"([^\"]*)\" API with POST HTTP request$")
	public void user_calls_something_api_with_post_http_request(String strArg1) throws Throwable {

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		response = res.when().post("/maps/api/place/add/json").then().spec(resspec).extract().response();

	}

	@Then("^The API call is success with status code 200$")
	public void the_api_call_is_success_with_status_code_200() throws Throwable {

		assertEquals(response.getStatusCode(), 200);

	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String key, String value) throws Throwable {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.getString(key), value);

	}
}
