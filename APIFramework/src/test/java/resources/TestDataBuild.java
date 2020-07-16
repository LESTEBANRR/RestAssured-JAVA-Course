package resources;

import java.util.ArrayList;
import java.util.List;
import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {

	public AddPlace addPlacePayload(String name, String language, String address) {
		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress(address);
		place.setLanguage(language);
		place.setPhone_number("4491234567");
		place.setWebsite("http://rahulshettyacademy.com");
		place.setName(name);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");
		place.setTypes(types);
		Location location = new Location();
		location.setLat(-38.1932983);
		location.setLng(33.45344);
		place.setLocation(location);
		return place;
	}
}
