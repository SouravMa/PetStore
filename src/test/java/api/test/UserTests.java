package api.test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class UserTests {
	
	User userPayload;
	Faker faker;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		
		faker= new Faker();
		userPayload= new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastname(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password());
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		
		//logging
		logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority= 1)
	public void testPostUser() {
		
		logger.info("***** Creating User *****");
		Response response= UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.body().jsonPath().get("type"), "unknown");
		logger.info("***** User is created *****");
	}

	@Test(priority= 2)
	public void testGetUser() {
		
		logger.info("***** Reading User Info*****");
		System.out.println(userPayload.getUsername());
		Response response= UserEndpoints.getUser(userPayload.getUsername());
		response.then().log().all();

		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** User info fetched successfully *****");
	}
	
	@Test(priority= 3)
	public void testUpdateUser() {
		
		logger.info("***** Updating User *****");
		faker= new Faker();
		
		userPayload.setUsername(faker.name().username());	
		
		System.out.println(this.userPayload.getUsername());
		Response response= UserEndpoints.updateUser(userPayload, userPayload.getUsername());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** User updated successfully *****");
		
		//checking body after update
		logger.info("***** Verifying user details after update *****");
		System.out.println(userPayload.getUsername());
		Response responseAfterUpdate= UserEndpoints.getUser(userPayload.getUsername());
		responseAfterUpdate.then().log().body();				
		responseAfterUpdate.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("GetUserByUsernameJSONSchema.json"));
		Assert.assertEquals(responseAfterUpdate.statusCode(), 200);
	}
	
	@Test(priority= 4)
	public void testDeleteUser() {
		
		logger.info("***** Deleting User *****");
		System.out.println(this.userPayload.getUsername());
		Response response= UserEndpoints.deleteUser(userPayload.getUsername());
		response.then().log().all();
	
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** User deleted successfully *****");
	}
}
