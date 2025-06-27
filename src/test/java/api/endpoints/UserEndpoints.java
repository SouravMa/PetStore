package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	
	public static Response createUser(User payload) {
		
		Response response= 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.postUrl);
		return response;		
	}
	
	public static Response getUser(String userName) {
		
		Response response=
				given()
					.pathParam("username", userName)
				.when()
					.get(Routes.getUrl);
		return response;
	}
	
	public static Response updateUser(User payload, String userName) {
		
		Response response=
				given()
					.pathParam("username", userName)
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.put(Routes.updateUrl);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		Response response=
				given()
					.pathParam("username", userName)
				.when()
					.delete(Routes.deleteUrl);
		return response;
	}

}
