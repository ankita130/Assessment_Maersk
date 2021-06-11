package interview.assessment1.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TransactionDetailsPage {
	WebDriver driver;
	
	public @FindBy(xpath="//h2[contains(text(),'Your flight from')]") WebElement headerFlightsPage;
	public @FindBy(xpath="//p[contains(text(),'Flight Number')]") WebElement flightNumber;
	public @FindBy(xpath="//p[contains(text(),'Price')]") WebElement price;
	public @FindBy(xpath="//input[@value='Purchase Flight']") WebElement purchaseFlightButton;
	
	
	public TransactionDetailsPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public BookingConfirmationPage clickPurchaseFlight()
	{
		purchaseFlightButton.click();
		return new BookingConfirmationPage(driver);
	}
	
	public void waitForPageToLoad()
	{
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));		 
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h2[contains(text(),'Your flight from')]")));       
	}

}
