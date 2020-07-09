import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath js=new JsonPath(payload.DummyRequest());
		//Print Number of Courses
		int count= js.getInt("courses.size()");
		System.out.println(count);
		//Print Purchase Amount
		int purchase_price= js.getInt("dashboard.purchaseAmount");
		System.out.println(purchase_price);
		//Print Title of the first course
		String firstTitle= js.getString("courses[0].title");
		System.out.println(firstTitle);
		//Print all courses titles and their respective prices
		System.out.println("Titles and Prices");
		for (int i = 0; i < count; i++) {
			String title=js.getString("courses["+i+"].title");
			String price=js.getString("courses["+i+"].price");
			System.out.println("title: "+title+" , price : "+price);
		}
	}

}
