package interview.assessment1.util;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class APIUtil {
	
	/**
	* get API call method
	* Returns Response of the get API 
	* @author  Ankita Dash
	* @param  url : url  of the get API
	* 
	*/
public static Response get(String url)
	{		 
	
	Response response = RestAssured.given()
		    .when()
		    .get(url)
		    .then()
		    .contentType(ContentType.JSON)
		    .extract().response();
		  
    return response;
	}

}
