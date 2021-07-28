package automation.fravega.restAssured;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Backend {

	public JsonPath getCervecerias(String name) {
		JsonPath body = RestAssured
			.given()
				.baseUri("https://api.openbrewerydb.org/breweries/autocomplete")
				.and()
				.queryParam("query", "lagunitas")
			.when()
				.get("/")
			.then()
				.extract().jsonPath();
		return body;
	}
	
	public List<String> filtrarPorName(JsonPath body, String name) {
		List<String> lid = body.getList("id");
		List<String> lname = body.getList("name");
		List<String> list = new ArrayList<String>();
		for(int i=0; i<lid.size(); i++) {
			if(lname.get(i).equals(name)) list.add(lid.get(i));
		}
		return list;
	}
	
	public JsonPath filtrarState(List<String> list, String state) {
		JsonPath jp = null;
		for(int i=0; i<list.size(); i++) {
			JsonPath body = RestAssured
				.given()
					.baseUri("https://api.openbrewerydb.org/breweries/" + list.get(i))
				.when()
					.get("/")
				.then()
					.extract().jsonPath();
			if(body.getString("state").equals(state)) {
				return body;
			}
		}
		return jp;
	}
	
	public boolean asserts(JsonPath body, int id, String name, String street, String phone) {
		if(body.getInt("id")==id
				&& body.getString("name").equals(name)
				&& body.getString("street").equals(street)
				&& body.getString("phone").equals(phone)) return true;
		else return false;
	}
}
