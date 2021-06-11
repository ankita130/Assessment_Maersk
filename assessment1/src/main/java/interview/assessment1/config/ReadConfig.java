package interview.assessment1.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class ReadConfig {
	
	private Properties configProperty;
	
	public ReadConfig()
	{
		configProperty=new Properties();
	}
	
	public Properties getProperties() throws IOException
	{
		try
		{
		String fileName = "resources/application.properties";
		File file = new File(fileName);
		String path = file.getAbsolutePath();
		FileInputStream input=new FileInputStream(path);
	    configProperty.load(input);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		return configProperty;
	
	}
	
	public String getApplicationUrl()
	{
		return this.configProperty.getProperty("application_url");
	}
	
	public String getChromeDriverPath() throws IOException
	{
		String driverPath=this.getProperties().getProperty("chrome.driver.path");
		File driverFile=new File(driverPath);
		String absolutePath=driverFile.getAbsolutePath();
		return absolutePath;
		
	}
}
