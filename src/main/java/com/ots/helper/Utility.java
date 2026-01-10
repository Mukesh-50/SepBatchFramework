package com.ots.helper;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.github.javafaker.Faker;

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

	//generating the fake firstname 
	public static String fakername() {
		//generating fake user data
		Faker fake = new Faker();
		String fakename=fake.name().firstName();
		
		return fakename;
	}
	
	//Wait element to be clickable  and click 
	public static void waitForElementToBeClickableandClick (WebDriver driver, By element, int timeoutInSeconds) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	}
	
	//Wait element to be clickable  
	public static void waitForElementToBeClickable(WebDriver driver, By element, int timeoutInSeconds) 
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	//wait untill the element is present 
	public static void waitForElementToBePresent(WebDriver driver, By element, int timeoutInSeconds) 
	{
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	    wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}
		
	//Scrolldown method by using javascript executor
	public static void scrolldown(WebDriver driver, By element) {
		// Cast the driver to JavascriptExecutor
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Locate the target element
		WebElement scrollelement = driver.findElement(element);

		// Scroll to the element
		js.executeScript("arguments[0].scrollIntoView(true);", scrollelement);
	}
	
	//Scrolldown method by using actions class
	public static void scrollToElement(WebDriver driver, WebElement element) 
	{
	    Actions actions = new Actions(driver);
	    actions.moveToElement(element).perform();
	}
	
	// Split comma-separated string into array and convert to List 
	public static  List<String> splictstringbycomma(WebDriver driver, String interests)
	{
	    String[] interestArray = interests.split(",");
	    List<String> interestList = Arrays.asList(interestArray);
	    return interestList;
	}
	
}
