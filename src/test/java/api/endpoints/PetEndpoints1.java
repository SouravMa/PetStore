package api.endpoints;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.Pets;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetEndpoints1 {
	
	public static Response postPet(Pets payload) {
		
		Response response=
				given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				.when()
					.post(Routes.postPetUrl);
					
		return response;
	}
	
	public static Response getPetById(int petId) {
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("petId", String.valueOf(petId))
				.when()
					.get(Routes.getPetUrl);
		
		return response;		
	}
	
	public static Response deletePetById(int petId) {
		
		Response response=
				given()
					.accept(ContentType.JSON)
					.pathParam("petId", String.valueOf(petId))
				.when()
					.delete(Routes.deletePetUrl);
		
		return response;		
	}

}
