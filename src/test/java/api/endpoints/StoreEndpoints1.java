package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StoreEndpoints1 {
	
	public static Response placeOrder(Store payload) {
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.postUrlStore);
		
		return response;
		
	}
	
	public static Response getStoreInventory() {
		
		Response response= 
				given()
					.accept(ContentType.JSON)
				.when()
					.get(Routes.getUrlStoreInventory);
		return response;
		
	}
	
	public static Response getOrder(int orderId) {
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("orderId", String.valueOf(orderId))
				.when()
					.get(Routes.getUrlStore);
		
		return response;
		
	}
	
	public static Response deleteOrder(int orderId) {
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.pathParam("orderId", String.valueOf(orderId))
				.when()
					.delete(Routes.deleteUrlStore);
		
		return response;
		
	}

}
