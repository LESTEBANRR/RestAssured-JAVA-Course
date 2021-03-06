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
		//Print no. of copies sold by RPA Course
		System.out.println("no. of copies sold by RPA Course");
		int copies=0;
		for (int i = 0; i < count; i++) {
			String title=js.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA")) {
				copies=js.getInt("courses["+i+"].copies");
				break;
			}
		}
		System.out.println(copies);
		//Veify if the PriceMAount is correct
		int priceAmount=0;
		for (int i = 0; i < count; i++) {
			int price=js.getInt("courses["+i+"].price");
			int copiesC =js.getInt("courses["+i+"].copies");
			priceAmount+= price*copiesC;
		}
		System.out.println("Purchase Amount : "+priceAmount);
		if (priceAmount==purchase_price) {
			System.out.println("The Purchase Amount is Correct");
		}else {
			System.out.println("The Purchase Amount is Incorrect");
		}
	}
	
	
	
	
	
	

}
