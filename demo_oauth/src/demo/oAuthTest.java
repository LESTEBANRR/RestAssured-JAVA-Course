package demo;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import pojo.GetCourse;
import pojo.WebAutomation;
import pojo.API;
public class oAuthTest {
	
	public static void main(String[] args) throws InterruptedException {
		String[] courseTitle= {"Selenium WebDriver Java","Cypress","Protactor"};
		/*
		//Selenium Automation, Google don't want automation login.		
		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://www.google.com");
		
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("srinath19830");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("password");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String url=driver.getCurrentUrl();
		String partialCode= url.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		*/
		
		String url="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2FvAHBQUZU6o4WJ719NrGBzSELBFVBI9XbxvOtYpmYpeV47bFVExkaxWaF_XR14PHtTZf7ILSEeamywJKwo_BYs9M&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&session_state=0c32992f0d47e93d273922018ade42d1072b9d1f..a35c&prompt=none#";
		
		String partialCode= url.split("code=")[1];
		String code=partialCode.split("&scope")[0];
		
		// Get the Access Token
		String accessTokenResponse=
		given().urlEncodingEnabled(false)
		.queryParam("code", code)
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
		// Login in the page
		GetCourse gc=
		given()
		.queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("http://rahulshettyacademy.com/getCourse.php")
		
		.as(GetCourse.class)
		;
		
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
		
		List<API> apiCourses=gc.getCourses().getApi();
		for (API api : apiCourses) {			
			if(api.getCourseTitle().equalsIgnoreCase("SoapUI Web Services testing")) {
				System.out.println(api.getPrice());
			}
		}
		// Get the course name of Web Automation
		ArrayList<String> a=new ArrayList<String>();
		List<WebAutomation> wa=gc.getCourses().getWebAutomation();
		for (WebAutomation webAutomation : wa) {
			a.add(webAutomation.getCourseTitle());
		}
		List<String> expectedList=Arrays.asList(courseTitle);
		
		Assert.assertTrue(a.equals(expectedList));
	}

}
