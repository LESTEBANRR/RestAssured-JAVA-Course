import org.junit.Test;
import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses() {
		JsonPath js = new JsonPath(payload.DummyRequest());
		
		//Print Number of Courses
				int count= js.getInt("courses.size()");
				System.out.println(count);
				
		//Print Purchase Amount
				int purchase_price= js.getInt("dashboard.purchaseAmount");
				System.out.println(purchase_price);
		
		//Veify if the PriceMAount is correct
		int priceAmount=0;
		for (int i = 0; i < count; i++) {
			int price=js.getInt("courses["+i+"].price");
			int copiesC =js.getInt("courses["+i+"].copies");
			priceAmount+= price*copiesC;
		}
		System.out.println("Purchase Amount : "+priceAmount);
		Assert.assertEquals(priceAmount, purchase_price);
	}
	
}
