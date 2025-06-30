package api.test;

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
		
	}
	
}
