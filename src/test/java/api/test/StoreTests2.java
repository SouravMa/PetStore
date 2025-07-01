package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.StoreEndpoints2;
import api.payload.Store;
import io.restassured.response.Response;

public class StoreTests2 {
	
	Store storePayload;
	Faker faker;
	public static Logger logger;

	@BeforeClass
	public void setup() {
		
		faker= new Faker();
		storePayload= new Store();
		
		storePayload.setId(faker.idNumber().hashCode());
		storePayload.setQuantity(0);
		storePayload.setPetId(0);
		storePayload.setShipDate("2025-06-30T06:40:12.349Z");
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
		
		//Logging:
		logger= LogManager.getLogger(this.getClass());
		
	}
	
	@Test(priority= 1)
	public void testPostStore() {
		
		logger.info("***** Creating Order *****");
		Response response= StoreEndpoints2.placeOrder(storePayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.body().jsonPath().get("status"), "placed");
		logger.info("***** Order is placed *****");
	}
	
	@Test(priority= 2)
	public void testGetOrderByOrderId() {
		
		logger.info("***** Fetching Order *****");
		Response response= StoreEndpoints2.getOrder(this.storePayload.getId());
		System.out.println(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.body().jsonPath().get("complete"), true);
		logger.info("***** Order fetched *****");
	}
	
	@Test(priority= 3)
	public void getStoreInventory() {
		
		logger.info("***** Fetching store inventory *****");
		Response response= StoreEndpoints2.getStoreInventory();
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** Pet inventory fetched *****");
		
	}
	
	@Test(priority= 4)
	public void testDeleteOrderByOrderId() {
		
		logger.info("***** Deleting Order *****");
		Response response= StoreEndpoints2.deleteOrder(this.storePayload.getId());
		System.out.println(this.storePayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.body().jsonPath().get("message"), String.valueOf(this.storePayload.getId()));
		logger.info("***** Order deleted *****");
	}
	
}
