package api.endpoints;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;	


//UserEndPoints.java
// Created for perform Create,Read,Update,Delete, requests to the user API.



public class UserEndpoints {
	
	public static Response createUser(User payload){
		
		Response response=given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .body(payload)
		
		.when()
		     .post(Routes.post_url);
		
		return response;
		
	}
	
     public static Response readUser(String username){
		
		Response response=given()
		      .pathParam("username", username)
		
		.when()
		     .get(Routes.get_url);
		
		return response;

      }
     
     
	public static Response updateUser(String userName,User payload){
		
		Response response=given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .pathParam("username",userName)
		      .body(payload)
		
		.when()
		     .put(Routes.update_url);
		
		 return response;
		
	}
	
	 public static Response deleteUser(String username){
			
			Response response=given()
			      .pathParam("username", username)
			
			.when()
			     .delete(Routes.delete_url);
			
			return response;

	      }
     
     
     
     

}
