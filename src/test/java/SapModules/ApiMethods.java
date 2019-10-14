package SapModules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class ApiMethods {
	
	@Test
	
	public static void sendResults(String F,String T,String DD,List<SapBusDetails> BusDetails) throws InterruptedException, IOException
	{
	
		
		/*if(BrowserContants.ENV.equals("PRD"))
		{
			RestAssured.baseURI =BrowserContants.PRD_API_URL;
		}
		else if(BrowserContants.ENV.equals("STG"))
		{
			RestAssured.baseURI =BrowserContants.STG_API_URL;
		}*/
		
		RestAssured.baseURI =BrowserContants.STG_API_URL;
		
		RequestSpecification request = RestAssured.given();
		request.header("Content-Type", "text/json");
		SapBusResponse result = new SapBusResponse();
		result.From =F;
		result.To = T;
		result.Currency = "SAR";
		result.DepartureDate = DD;
		
		
		result.BusDetails = BusDetails;
		Gson gson = new Gson();
		
		
		request.body(gson.toJson(result));
		System.out.println(gson.toJson(result));
		Response response = request.post("/SaveF3SrpToCache");
		System.out.println("Response body: " + response.body().asString());
		String s=response.body().asString();
		System.out.println(s);
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
		
		
	}

}
