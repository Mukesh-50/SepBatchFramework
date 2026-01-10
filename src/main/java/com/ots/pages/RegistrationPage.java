package com.ots.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.ots.helper.Utility;

public class RegistrationPage {
	
WebDriver driver;
	
	public RegistrationPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	private By name = By.id("name");
	
	private By email= By.id("email");
	
	private By password= By.id("password");
	
	private By Interestcheckbox= By.xpath("//div[contains(@class,'interests')]/div/label[contains(text(),'TestNG')]");
	
	private By malegender= By.xpath("//input[@id='gender1']");
	
	private By femalegender= By.xpath("//input[@id='gender1']");
	
	private By statedropdown= By.xpath("//select[@id='state']");
	
	private By Hobbies = By.xpath("//select[@id='hobbies']/option[contains(text(),'Playing')]");
	
	private By submitbutton= By.xpath("//button[contains(@class,'submit')]");
	
	private By heading= By.xpath("//h2[text()='Sign Up']");
	
	private By alreadyuserlink= By.xpath("//a[@class='subLink']");
	
	
	public boolean isHeaderPresent() 
	{
		boolean status = driver.findElement(heading).isDisplayed();

		return status;
	}
	
	
	/*
	 * Fills registration form with valid fake data and submits.
	 * @return LoginPage after successful signup redirect
	 */
	public LoginPage fillregistrationformwithvaliddata() {

		//generatng the fake username dynamically
		String fakename = Utility.fakername();
		String timestamp = Utility.getCurrentDateTime();
			
			//passing the fake username, email, password
			driver.findElement(name).sendKeys(fakename);
			driver.findElement(email).sendKeys(fakename + timestamp + "@mail.com");
			driver.findElement(password).sendKeys(fakename + timestamp);

		// selecting the check box of TestNG
		driver.findElement(Interestcheckbox).click();
		
		// selecting the male gender
		driver.findElement(malegender).click();
		
		//scrolling down 
		Utility.scrollToElement(driver,driver.findElement(alreadyuserlink));

		// select state as Goa in static fashion
		Select select = new Select(driver.findElement(statedropdown));
		select.selectByVisibleText("Goa");
		
		// select hobby as Reading
		driver.findElement(Hobbies).click();
		
		// click on submit button
		Utility.waitForElementToBeClickableandClick(driver, submitbutton, 5);
		return  new LoginPage(driver);
	}
	
	/*
	 * Fills registration form using data provider.
	 * @return LoginPage after successful signup redirect
	 */
	public LoginPage fillregistrationformusingdataprovider(String Name, String Email, String Password, String Interests,String Gender,String State,String Hobbies) {

			driver.findElement(name).sendKeys(Name);
			driver.findElement(email).sendKeys(Email);
			driver.findElement(password).sendKeys(Password);

		// selecting the check boxes of interests
		
		
		// pass the intereset and split with coma separated, ans store in list of string
		List<String> interestList =Utility.splictstringbycomma(driver, Interests);
		
		// Trim whitespace from each interest string and click one by one and pass with xpath and click
	    for (String interest : interestList) {
	        String trimmedInterest = interest.trim();
	        driver.findElement(By.xpath("//div[contains(@class,'interests')]/div/label[contains(text(),'" + trimmedInterest + "')]")).click();
	    }
		//driver.findElement(By.xpath("//div[contains(@class,'interests')]/div/label[contains(text(),'"+Interests+"')]")).click();
				
		// selecting the male gender
		if(Gender.equalsIgnoreCase("Male")) 
		{
			driver.findElement(malegender).click();
		}else if(Gender.equalsIgnoreCase("Female")) {
			driver.findElement(femalegender).click();
		}
			
		//scrolling down 
		Utility.scrollToElement(driver,driver.findElement(alreadyuserlink));

		// select state as Goa in static fashion
		Select select = new Select(driver.findElement(statedropdown));
		select.selectByVisibleText(State);
		
		// select hobby as Reading
		driver.findElement(By.xpath("//select[@id='hobbies']/option[contains(text(),'"+Hobbies+"')]")).click();
				
		// click on submit button
		Utility.waitForElementToBeClickableandClick(driver, submitbutton, 5);
		return  new LoginPage(driver);
	}
	
	
	
}