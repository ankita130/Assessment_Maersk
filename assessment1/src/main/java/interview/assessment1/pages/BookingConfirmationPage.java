package interview.assessment1.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingConfirmationPage {
WebDriver driver;
	
public @FindBy(xpath="//h1[contains(text(),'Thank you for your purchase today')]") WebElement headerThankYouForYourPurchase;
public @FindBy(xpath="//td[text()='Id']//following-sibling::td") WebElement idValue;
public @FindBy(xpath="//pre") WebElement resultJson;
	
	
	public BookingConfirmationPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void waitForPageToLoad()
	{
	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));		 
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(text(),'Thank you for your purchase today')]")));       
	}

}
