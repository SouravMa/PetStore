package api.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.PetEndpoints2;
import api.payload.Pets;
import api.payload.Pets.Category;
import api.payload.Pets.Tag;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PetsTests2 {
	
	Pets petsPayload;
	Category categoryPayload;
	Tag tagPayload;
	Faker faker;
	public static Logger logger;
	
	@BeforeClass
	public void setup() {
		
		categoryPayload= new Category();
		tagPayload= new Tag();
		petsPayload= new Pets();
		
		faker= new Faker();
		
		categoryPayload.setId(0);
		categoryPayload.setName(faker.dog().breed());
		
		tagPayload.setId(0);
		tagPayload.setName("Good boy/girl");
		
		petsPayload.setId(faker.idNumber().hashCode());
		petsPayload.setCategory(categoryPayload);
		petsPayload.setName(faker.dog().name());
		
		List<String> pics= new ArrayList<>();
		pics.add("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFUAfyVe3Easiycyh3isP9wDQTYuSmGPsPQvLIJdEYvQ_DsFq5Ez2Nh_QjiS3oZ3B8ZPfK9cZQyIStmQMV1lDPLw");
		pics.add("https://hips.hearstapps.com/hmg-prod/images/dog-puppy-on-garden-royalty-free-image-1586966191.jpg?crop=1xw:0.74975xh;0,0.190xh&resize=1200:*");
		
		petsPayload.setPhotoUrls(pics);
		petsPayload.setTags(Arrays.asList(tagPayload));
		
		// logging
		logger= LogManager.getLogger(this.getClass());
	}
	
	@Test(priority= 1)
	public void testPostPet() {
		
		logger.info("***** Adding pet details *****");
		Response response= PetEndpoints2.postPet(petsPayload);
		response.then().log().all();
		
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("tags[0].name"), "Good boy/girl");
		logger.info("***** Pet details added *****");
	}
	
//	@Test(priority= 2)
	public void getPetById() {
		
		logger.info("***** Fetching pet details by id *****");
		Response response= PetEndpoints2.getPetById(petsPayload.getId());
		System.out.println(petsPayload.getId());
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("tags[0].name"), "Good boy/girl");
		logger.info("***** Pet details fetched *****");
		
	}
	
	@Test(priority= 3)
	public void deletePetById() {
		
		logger.info("***** Deleting pet details by id *****");
		Response response= PetEndpoints2.deletePetById(petsPayload.getId());
		System.out.println(petsPayload.getId());
		response.then().log().all();
		
		response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetDeleteJSONSchema.json"));
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("***** Pet details deleted *****");
		
	}

}
