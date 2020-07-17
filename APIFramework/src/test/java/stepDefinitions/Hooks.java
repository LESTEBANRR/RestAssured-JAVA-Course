package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("DeletePlace")
	public void beforeScenario() throws Throwable {
		StepDefinition steps=new StepDefinition();
		steps.addplace_payload_with_and("Shatty", "French", "Asia");
		steps.user_calls_some_api_with_some_method_http_request("AddPlace", "POST");
		steps.the_api_call_is_success_with_status_code_200(200);
		steps.verify_placeid_created_maps_to_some_key_using_some_api("name", "Shatty", "getPlace");
	}
}
