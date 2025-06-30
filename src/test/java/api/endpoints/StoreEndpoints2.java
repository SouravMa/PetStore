package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints2 {
	
	// to fetch url's from routes.properties
	public static ResourceBundle getUrl() {
		ResourceBundle rb= ResourceBundle.getBundle("routes");
		return rb;
	}
	
	public static Response placeOrder(Store payload) {
		
		String postUrlStore= getUrl().getString("postUrlStore");
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(postUrlStore);
		
		return response;
		
	}
	
	public static Response getOrder(int orderId) {
		
		String getUrlStore= getUrl().getString("getUrlStore");
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("orderId", String.valueOf(orderId))
				.when()
					.get(getUrlStore);
		
		return response;
		
	}
	
	public static Response deleteOrder(int orderId) {
		
		String deleteUrlStore= getUrl().getString("deleteUrlStore");
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("orderId", String.valueOf(orderId))
				.when()
					.delete(deleteUrlStore);
		
		return response;
		
	}

}
