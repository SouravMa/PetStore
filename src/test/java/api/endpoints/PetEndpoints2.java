package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.Pets;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints2 {
	
	public static ResourceBundle getUrl() {
		
		ResourceBundle rb= ResourceBundle.getBundle("routes");
		return rb;
	
	}
	
	public static Response postPet(Pets payload) {
		
		String postPetUrl= getUrl().getString("postPetUrl");
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(postPetUrl);
					
		return response;
	}
	
	public static Response getPetById(int petId) {
		
		String getPetUrl= getUrl().getString("getPetUrl");
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("petId", String.valueOf(petId))
				.when()
					.get(getPetUrl);
		
		return response;		
	}
	
	public static Response deletePetById(int petId) {
		
		String deletePetUrl= getUrl().getString("deletePetUrl");
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("petId", String.valueOf(petId))
				.when()
					.delete(deletePetUrl);
		
		return response;		
	}

}
