package com.ots.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Utility 
{
	public static WebElement highLightElement(WebDriver driver, WebElement element)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver; 
	
		js.executeScript("arguments[0].setAttribute('style', 'background: pink; border: 2px solid red;');", element);
	
		try 
		{
		Thread.sleep(200);
		} 
		catch (InterruptedException e) {
	
		System.out.println(e.getMessage());
		} 
	
		js.executeScript("arguments[0].setAttribute('style','border: solid 2px black');", element); 
		
		return element;

	}
	
	/*
	 *  Screenshot types
	 *  	1.File
	 *  	2.Byte[]
	 *  	3.Base64 - String (Encoded)
	 * 
	 */
	
	public static void captureScreenshot(WebDriver driver)
	{
		
		try 
		{
			FileHandler.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE), new File(System.getProperty("user.dir")+"/Screenshots/Screenshot_"+Utility.getCurrentDateTime()+".png"));
			
			System.out.println("Captured Screenshot "+System.getProperty("user.dir")+"/Screenshots/Screenshot_"+Utility.getCurrentDateTime()+".png");
			
			
		} catch (IOException e) 
		{
			System.out.println("Failed To Capture Screenshot "+e.getMessage());
		}
		
	}
	
	public static String captureScreenshot(WebDriver driver,String type)
	{
		String screenshot=null;
		
		if(type.equalsIgnoreCase("base64"))
		{
			
			TakesScreenshot ts=(TakesScreenshot)driver;
			
			screenshot=ts.getScreenshotAs(OutputType.BASE64);
			
			
		}
		
		return screenshot;
		
	}

	public static String getCurrentDateTime()
	{
		Date currentDate=new Date();
		
		SimpleDateFormat currentDateFormat=new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss_SS");
		
		String date=currentDateFormat.format(currentDate);
		
		return date;
		
	}
	

	
	public static void selectValuesFromList(WebDriver driver,String xpathExp,String valuesToSelect)
	{
		
	 List<WebElement> allElements=driver.findElements(By.xpath(xpathExp));
		
		for(WebElement ele:allElements)
		{
			String elementText=ele.getText();
			
			System.out.println("Current Values Are "+elementText);
			
			if(elementText.equalsIgnoreCase(valuesToSelect))
			{
				ele.click();
				
				break;
			}
		}
		
		
	}
	
	public static void selectValuesFromList(WebDriver driver,By element,String valuesToSelect)
	{
		
	 List<WebElement> allElements=driver.findElements(element);
		
		for(WebElement ele:allElements)
		{
			String elementText=ele.getText();
			
			System.out.println("Current Values Are "+elementText);
			
			if(elementText.equalsIgnoreCase(valuesToSelect))
			{
				ele.click();
				
				break;
			}
		}
		
		
	}
	
	public static void uploadFiles(WebDriver driver,String fileName)
	{
		// complete this if I pass filename it should take from testdata folder and upload
	}

}
