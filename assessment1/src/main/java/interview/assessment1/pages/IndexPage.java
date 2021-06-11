package interview.assessment1.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IndexPage {
	
	private WebDriver driver;
	
	public @FindBy(xpath="//h1[contains(text(),'Welcome to the Simple Travel')]") WebElement headerWelcome;
	public @FindBy(xpath="//select[@name='fromPort']") WebElement departureCityDropDown;
	public @FindBy(xpath="//h2[contains(text(),'departure city')]") WebElement departureCityLabel;
	public @FindBy(xpath="//select[@name='toPort']") WebElement destinationCityDropDown;
	public @FindBy(xpath="//h2[contains(text(),'destination city')]") WebElement destinationCityLabel;
	public @FindBy(xpath="//input[@class='btn btn-primary'][@value='Find Flights']") WebElement findFlightButton;
	public @FindBy(xpath="//p[contains(text(),'Check out')]") WebElement checkoutText;
	public @FindBy(xpath="//p[contains(text(),'Check out')]/a") WebElement checkoutLink;
	
	public IndexPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void selectDepartureCity(String cityName)
	{
		departureCityDropDown.click();
		Select cityListSelect=new Select(departureCityDropDown);
		cityListSelect.selectByVisibleText(cityName);
	}
	
	public List<String> getDepartureCityList()
	{
		List<String> cityList=new ArrayList<String>();
		List<WebElement> cityListWebElement=new ArrayList<WebElement>();
		
		Select cityListSelect=new Select(departureCityDropDown);
		cityListWebElement=cityListSelect.getOptions();
		for(WebElement eachOption:cityListWebElement)
			cityList.add(eachOption.getText());
		return cityList;
	}
	
	public void selectDestinationCity(String cityName)
	{
		destinationCityDropDown.click();
		Select cityListSelect=new Select(destinationCityDropDown);
		cityListSelect.selectByVisibleText(cityName);
	}
	
	public List<String> getDestinationCityList()
	{
		List<String> cityList=new ArrayList<String>();
		List<WebElement> cityListWebElement=new ArrayList<WebElement>();
		
		Select cityListSelect=new Select(destinationCityDropDown);
		cityListWebElement=cityListSelect.getOptions();
		for(WebElement eachOption:cityListWebElement)
			cityList.add(eachOption.getText());
		return cityList;
	}
	
	public DisplayFlightsPage clickFindFlight()
	{
		findFlightButton.click();
		return new DisplayFlightsPage(driver);
	}

	public void clickCheckoutLink()
	{
		checkoutLink.click();
	}
	
	public void waitForPageToLoad()
	{
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));		 
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Welcome to the Simple Travel')]")));       
	}
}
