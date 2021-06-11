package interview.assessment1.ui.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;

import interview.assessment1.config.ReadConfig;

public class BaseTest {
	
	private ReadConfig configuration;
	protected WebDriver driver;
	
	public BaseTest()
	{
		configuration=new ReadConfig();
	}
	
	public WebDriver getDriver(String browser)
	           throws IOException {
	       if(browser.equals("Chrome"))
	       {
	       System.setProperty("webdriver.chrome.driver",configuration.getChromeDriverPath());	       
	       driver=new ChromeDriver();
	       }
	       
	       return driver;
	       
	   }
	
	
	public void maximizeBrowser() {
        driver.manage().window().maximize();
    }

    public void launchApplication(String url) {
        driver.get(url);
        
    }

    public String getUrlFromLoginProperty()
    {
        return configuration.getApplicationUrl();
    }
    
    @AfterMethod()
    public void tearDown()
    {
    	driver.quit();
    }

}
