package com.ots.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.ots.dataprovider.ConfigUtility;
import com.ots.factory.BrowserFactory;

public class BaseClass 
{
	public WebDriver driver;
	
	@BeforeClass
	public void setup()
	{
		System.out.println("LOG:INFO-Running Before Class");
		
		String browserName=ConfigUtility.readProperty("browser");
		
		String appURL=ConfigUtility.readProperty("qaenv");
		
		driver=BrowserFactory.startBrowser(browserName, appURL+"/login");
		
		System.out.println("LOG:INFO-Browser is up and running");
	}
	
	@AfterClass
	public void tearDown()
	{
		System.out.println("LOG:INFO-Running After Class");
		
		driver.quit();
		
		System.out.println("LOG:INFO-Session Closed");
	}

}
