package interview.assessment1.ui.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import interview.assessment1.pages.BookingConfirmationPage;
import interview.assessment1.pages.DisplayFlightsPage;
import interview.assessment1.pages.IndexPage;
import interview.assessment1.pages.TransactionDetailsPage;

public class TestBlaze extends BaseTest {

	@DataProvider(name = "validateSearchResultSourceDestination")
  	public Object[][] dataValidateSearchResultSourceDestination(){
        	return new Object[][]{
              	{"Boston","Rome"},{"Portland","New York"}
        	};
  	}
	@Test(description="Validate the source and destination in the searchFlight result",dataProvider = "validateSearchResultSourceDestination")
	void validateSearchResultSourceDestination(String source,String destination) throws IOException
	{
		String message=String.format("Flights from %s to %s:", source,destination);
		driver=getDriver("Chrome");		
		launchApplication(getUrlFromLoginProperty());
		maximizeBrowser();
		
		IndexPage indexPage=new IndexPage(driver);
		indexPage.waitForPageToLoad();
		indexPage.selectDepartureCity(source);
		indexPage.selectDestinationCity(destination);		
		
		DisplayFlightsPage flightsPage=indexPage.clickFindFlight();
		
		Assert.assertEquals(flightsPage.headerFlightsPage.getText(), message);
	}
	
	@DataProvider(name = "validateSearchResultFlightDetails")
  	public Object[][] dataValidateSearchResultFlightDetails(){
		List<List<String>> finalResult=new ArrayList<List<String>>();
		finalResult.add(Arrays.asList("43","Virgin America","1:43 AM","9:45 PM","$472.56"));
		finalResult.add(Arrays.asList("234","United Airlines","7:43 AM","10:45 PM","$432.98"));
		finalResult.add(Arrays.asList("9696","Aer Lingus","5:27 AM","8:22 PM","$200.98"));
		finalResult.add(Arrays.asList("12","Virgin America","11:23 AM","1:45 PM","$765.32"));
		finalResult.add(Arrays.asList("4346","Lufthansa","1:45 AM","8:34 PM","$233.98"));
        	return new Object[][]{
        		{"Boston","Rome",finalResult}
        	};
  	}
	
	@Test(description="Validate the flightdetails in the searchFlight result",dataProvider = "validateSearchResultFlightDetails")
	void validateSearchResultFlightDetails(String source,String destination,List<List<String>> finalResult) throws IOException
	{
		driver=getDriver("Chrome");		
		launchApplication(getUrlFromLoginProperty());
		maximizeBrowser();
		
		IndexPage indexPage=new IndexPage(driver);
		indexPage.waitForPageToLoad();
		indexPage.selectDepartureCity(source);
		indexPage.selectDestinationCity(destination);		
		
		DisplayFlightsPage flightsPage=indexPage.clickFindFlight();
		List<List<String>> flightSearchResults=flightsPage.getFlightDetailsList();
		
		Assert.assertEquals(flightSearchResults.size(), finalResult.size(),"failing");
		Assert.assertEquals(flightSearchResults, finalResult);
	}
	
	@DataProvider(name = "validateFlightBooking")
  	public Object[][] dataValidateFlightBooking(){
        	return new Object[][]{
              	{"Boston","Rome","234"}
        	};
  	}
	@Test(description="Validate the source and destination in the searchFlight result",dataProvider = "validateFlightBooking")
	void validateFlightBooking(String source,String destination,String flightNumber) throws IOException
	{
		driver=getDriver("Chrome");		
		launchApplication(getUrlFromLoginProperty());
		maximizeBrowser();
		
		IndexPage indexPage=new IndexPage(driver);
		indexPage.waitForPageToLoad();
		indexPage.selectDepartureCity(source);
		indexPage.selectDestinationCity(destination);		
		
		DisplayFlightsPage flightsPage=indexPage.clickFindFlight();
		flightsPage.waitForPageToLoad();
		
		TransactionDetailsPage transactionPage=flightsPage.clickChooseFlight(flightNumber);		
		transactionPage.waitForPageToLoad();
		
		BookingConfirmationPage confirmationPage=transactionPage.clickPurchaseFlight();
		confirmationPage.waitForPageToLoad();
		
		Assert.assertNotNull(confirmationPage.idValue.getText());
		
		String resultJsonString=confirmationPage.resultJson.getText();
		JSONObject resultJson = new JSONObject(resultJsonString);  
		Assert.assertEquals(confirmationPage.idValue.getText(),resultJson.getString("id"));
		
	}
	
}
