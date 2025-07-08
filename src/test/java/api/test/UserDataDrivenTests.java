package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class UserDataDrivenTests {	

	@Test(priority=1, dataProvider= "Data", dataProviderClass= DataProviders.class)
	public void testPostUser(String userId, String userName, String firstName, String lastName, String emaiId, String password, String phone) {
		
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstname(firstName);
		userPayload.setLastname(lastName);
		userPayload.setEmail(emaiId);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response= UserEndpoints.createUser(userPayload);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}
	
	@Test(priority=2, dataProvider= "UserNames", dataProviderClass= DataProviders.class)
	public void testDeleteUser(String userName) {

		Response response= UserEndpoints.deleteUser(userName);
		response.then().log().all();
		
		AssertJUnit.assertEquals(response.statusCode(), 200);

	}
	
}
