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
import org.openqa.selenium.support.ui.WebDriverWait;

public class DisplayFlightsPage {
private WebDriver driver;
	
public @FindBy(xpath="//h3[contains(text(),'Flights from')]") WebElement headerFlightsPage;
public @FindBy(xpath="//tr[./th[text()='Choose']]") WebElement flightsTableColumnsHeader;
public @FindBy(xpath="//thead[./tr[./th[text()='Choose']]]//following-sibling::tbody") WebElement flightsTableColumnsValue;
	
	
	public DisplayFlightsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public TransactionDetailsPage clickChooseFlight(String flightNumber)
	{
		String xpath=String.format("//td[text()='%s']//preceding-sibling::td/input", flightNumber);
		WebElement chooseFlightButton=this.driver.findElement(By.xpath(xpath));
		chooseFlightButton.click();
		return new TransactionDetailsPage(driver);
	}
	
	public List<List<String>> getFlightDetailsList()
	{
		List<List<String>> flightsDetails=new ArrayList<List<String>>();
		List<WebElement> eachRowOfFlight=new ArrayList<WebElement>();
		//eachRowOfFlight=flightsTableColumnsValue.findElements(By.xpath("/tr"));
		eachRowOfFlight=this.driver.findElements(By.xpath("//thead[./tr[./th[text()='Choose']]]//following-sibling::tbody/tr"));
		for(WebElement eachRow:eachRowOfFlight)
		{
			List<String> eachRowData=new ArrayList<String>();
			List<WebElement> columnElements=new ArrayList<WebElement>();			
			columnElements=eachRow.findElements(By.tagName("td"));
			for(WebElement eachColumn:columnElements)
			{
				eachRowData.add(eachColumn.getText());
			}
			eachRowData.remove(0);
			
			flightsDetails.add(eachRowData);
		}
		
		return flightsDetails;
		
	}
	
	public void waitForPageToLoad()
	{
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));		 
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Flights from')]")));       
	}

}
