package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {
	
	Faker faker;
	User userPayload;
	
	   @BeforeClass
	   public void setupData() {
		   
		   faker=new Faker();
		   userPayload=new User();
		   
		   userPayload.setId(faker.idNumber().hashCode());
		   userPayload.setUsername(faker.name().username());
		   userPayload.setFirstname(faker.name().firstName());
		   userPayload.setLastName(faker.name().lastName());
		   userPayload.setEmail(faker.internet().safeEmailAddress());
		   userPayload.setPassword(faker.internet().password(5, 10));
		   userPayload.setPhone(faker.phoneNumber().cellPhone());
		   
		   
	   }
	   
	  @Test(priority=1) 
	  public void  testPostUser() {
		  
		  Response response=UserEndpoints.createUser(userPayload);
		  response.then().log().all();
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
		 
	}
	  @Test(priority=2)
	  public void testGetUserByName() {
		  
		 Response response=UserEndpoints.readUser(this.userPayload.getUsername());
		 
		 response.then().log().all();
		 Assert.assertEquals(response.getStatusCode(),200);
		 
		 }
	  @Test(priority=3)
	  public void testUpdateUserByName() {
		  
		  // Update data using payload (username based as we are not updating username)
		  
		   userPayload.setFirstname(faker.name().firstName());
		   userPayload.setLastName(faker.name().lastName());
		   userPayload.setEmail(faker.internet().safeEmailAddress());
		  
		  Response response=UserEndpoints.updateUser(this.userPayload.getUsername(),userPayload);
		  //response.then().log().body().statusCode(200);// chai assertion
		  
		   response.then().log().body();
		  
		  Assert.assertEquals(response.getStatusCode(), 200);// testng assertion
		  
		  
		//  checking data after update
		  
		    Response responseAfterUpdate=UserEndpoints.readUser(this.userPayload.getUsername());
			 
			 response.then().log().all();
			 Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
		  
	  }
	  
	  @Test(priority=4)
	  public void testDeleteUserByName() {
		  
		  Response response=UserEndpoints.deleteUser(this.userPayload.getUsername());
		  
		  Assert.assertEquals(response.getStatusCode(), 200);
	  }
	  
	  
	  
}
