package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints2 {
	
	// to fetch routes from routes.properties file
	static ResourceBundle getUrl(){
		ResourceBundle routes= ResourceBundle.getBundle("routes");
		return routes;
	}
	
	public static Response createUser(User payload) {
		
		String postUrl= getUrl().getString("postUrl");
		
		Response response= 
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(postUrl);
		return response;		
	}
	
	public static Response getUser(String userName) {
		
		String getUrl= getUrl().getString("getUrl");
		
		Response response=
				given()
					.pathParam("username", userName)
				.when()
					.get(getUrl);
		return response;
	}
	
	public static Response updateUser(User payload, String userName) {
		
		String updateUrl= getUrl().getString("updateUrl");
		
		Response response=
				given()
					.pathParam("username", userName)
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.put(updateUrl);
		return response;
	}
	
	public static Response deleteUser(String userName) {
		
		String deleteUrl= getUrl().getString("deleteUrl");
		
		Response response=
				given()
					.pathParam("username", userName)
				.when()
					.delete(deleteUrl);
		return response;
	}

}
