package automation.fravega.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import automation.fravega.restAssured.Backend;
import io.restassured.path.json.JsonPath;

public class BusquedaCerveceria {

	@Test
	public void buscarCerveceria() {
		Backend b = new Backend();
		JsonPath body = b.getCervecerias("lagunitas");
		List<String> list = b.filtrarPorName(body, "Lagunitas Brewing Co");
		body = b.filtrarState(list, "California");
		//int id = 761
		int id = 12040;
		String name = "Lagunitas Brewing Co";
		String street = "1280 N McDowell Blvd";
		String phone = "7077694495";
		Assert.assertTrue(b.asserts(body, id, name, street, phone));
	}
}
