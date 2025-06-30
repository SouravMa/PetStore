package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.StoreEndpoints1;
import api.payload.Store;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class StoreDataDrivenTests {

	@Test(priority= 1, dataProvider="StoreData", dataProviderClass= DataProviders.class)
	public void testPostStore(String id, String petId, String quantity, String shipDate, String status, String complete) {
		
		Store storePayload= new Store();
		storePayload.setId(Integer.parseInt(id));
		storePayload.setPetId(Integer.parseInt(petId));
		storePayload.setQuantity(Integer.parseInt(quantity));
		storePayload.setShipDate(shipDate);
		storePayload.setStatus(status);
		storePayload.setComplete(Boolean.valueOf(complete));
		
		Response response= StoreEndpoints1.placeOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider="OrderId", dataProviderClass=DataProviders.class)
	public void testGetOrderById(String id) {
		
		Response response= StoreEndpoints1.getOrder(Integer.parseInt(id));
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority=2, dataProvider="OrderId", dataProviderClass=DataProviders.class)
	public void testDeleteOrder(String id) {
		
		Response response= StoreEndpoints1.deleteOrder(Integer.parseInt(id));
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
}
