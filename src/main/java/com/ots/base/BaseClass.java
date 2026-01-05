package com.ots.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ots.dataprovider.ConfigUtility;
import com.ots.factory.BrowserFactory;

public class BaseClass 
{
	public String url;
	public String accesskey;
	public WebDriver driver;
	public String env;
	public String accesskeynew;
	
	@BeforeMethod
	public void setup()
	{
		System.out.println("LOG:INFO-Running Before Class");
		
		String browserName=ConfigUtility.readProperty("browser");
		
		String appURL=ConfigUtility.readProperty("qaenv");
		
		driver=BrowserFactory.startBrowser(browserName, appURL+"/login");
		
		System.out.println("LOG:INFO-Browser is up and running");
	}
	
	@AfterMethod
	public void tearDown()
	{
		System.out.println("LOG:INFO-Running After Class");
		
		driver.quit();
		
		System.out.println("LOG:INFO-Session Closed");
	}

}
