package interview.assessment1.api;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import interview.assessment1.util.APIUtil;
import io.restassured.response.Response;

public class TestApi {
	@Test(description="validation of response of the getAPI")
	public void validateLatestLaunchResult()
	{
		String url="https://api.spacexdata.com/v4/launches/latest";
		String url1="https://api.spacexdata.com/v4/launches";
		int latestTime=0;
		Response response=APIUtil.get(url);
		Assert.assertEquals(response.getStatusCode(),200);
		JSONObject resultJson=new JSONObject(response.body().asString());	
		
		Response response1=APIUtil.get(url1);	
		Assert.assertEquals(response1.getStatusCode(),200);	
		JSONArray JSONResponseBody = new   JSONArray(response1.body().asString());
		Map<Object,Integer> latest=new HashMap<Object,Integer>();
		
		for(int i=0;i<JSONResponseBody.length();i++)
		{
			if(!(Objects.equals((JSONResponseBody.getJSONObject(i).get("static_fire_date_unix")),null)))
			{
				if(JSONResponseBody.getJSONObject(i).getInt("static_fire_date_unix")>latestTime)
				{
					latestTime= JSONResponseBody.getJSONObject(i).getInt("static_fire_date_unix");
					latest.put("index",i);
					
				}
			
			}
			
		}		
		JSONObject ExpectedJson=JSONResponseBody.getJSONObject(latest.get("index"));
		System.out.println("Response Expected :"+ExpectedJson);
		System.out.println("Response Actual :"+resultJson);
		
		Assert.assertEquals(ExpectedJson.get("id"),resultJson.get("id"));
		Assert.assertEquals(ExpectedJson.get("launchpad"),resultJson.get("launchpad"));
		Assert.assertEquals(ExpectedJson.get("rocket"),resultJson.get("rocket"));
		Assert.assertEquals(ExpectedJson.get("name"),resultJson.get("name"));
		Assert.assertEquals(ExpectedJson.get("flight_number"),resultJson.get("flight_number"));
		
		
		 
	}

}
