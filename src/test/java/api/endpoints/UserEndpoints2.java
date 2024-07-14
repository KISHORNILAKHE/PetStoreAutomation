package api.endpoints;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.matcher.RestAssuredMatchers.*;	


//UserEndPoints.java
// Created for perform Create,Read,Update,Delete, requests to the user API.



public class UserEndpoints2 {
	
	// method created for getting urls  from prop file 
	static ResourceBundle getURL(){
		
		ResourceBundle routes = ResourceBundle.getBundle("routes");// Load the prop file
		return routes;
	}
	
	public static Response createUser(User payload){
		
		String post_url=getURL().getString("post_url");
		
		Response response=given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .body(payload)
		
		.when()
		     .post(post_url);
		
		return response;
		
	}
	
     public static Response readUser(String username){
    	 
    	 String get_url=getURL().getString("get_url");
		
		Response response=given()
		      .pathParam("username", username)
		
		.when()
		     .get(get_url);
		
		return response;

      }
     
     
	public static Response updateUser(String userName,User payload){
		
		String update_url=getURL().getString("update_url");
		
		Response response=given()
		      .contentType(ContentType.JSON)
		      .accept(ContentType.JSON)
		      .pathParam("username",userName)
		      .body(payload)
		
		.when()
		     .put(update_url);
		
		 return response;
		
	}
	
	 public static Response deleteUser(String username){
		 
		 String delete_url=getURL().getString("delete_url");
			
			Response response=given()
			      .pathParam("username", username)
			
			.when()
			     .delete(Routes.delete_url);
			
			return response;

	      }
     
     
     
     

}
