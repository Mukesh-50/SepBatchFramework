package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.ots.helper.Utility;


public class LoginPage 
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
	}
	
	private By username = By.id("email1");

	private By password = By.name("password1");

	private By loginButton = By.xpath("//button[text()='Sign in']");

	private By newUserLink = By.linkText("New user? Signup");

	private By heading = By.xpath("//h2[text()='Sign In']");
	
	//private By successmessage= By.cssSelector("//div[@role='alert']");
	private By successmessage= By.xpath("//div[@class='Toastify__toast-body']");	

	public String getTitle() {
		return driver.getTitle();
	}


	public boolean isHeaderPresent() 
	{
		boolean status = driver.findElement(heading).isDisplayed();

		return status;
	}

	public DashboardPage loginToApplication(String user, String pass) 
	{
		driver.findElement(username).sendKeys(user);
		
		driver.findElement(password).sendKeys(pass);
		
		driver.findElement(loginButton).click();
	
		DashboardPage dashboard = PageFactory.initElements(driver, DashboardPage.class);

		return dashboard;
	}

	//Navigates to the signup/registration page by clicking the "New User" link.
	public RegistrationPage navigatetoSignuppage()
	{
		driver.findElement(newUserLink).click();
		RegistrationPage registrationpage= new RegistrationPage(driver);
		return  registrationpage;
	}
		
	// verify signup success message alert message on sign in page
	public boolean  VerifySignupConfirmationMessage() 
	{
		System.out.println("come to validation");
		Utility.waitForElementToBeClickable(driver, successmessage, 5);
		boolean signupsuccessmessage=driver.findElement(successmessage).getText().contentEquals("Signup successfully, Please login!");
		System.out.println("validation completed and return the boolean");
		return signupsuccessmessage;
	}
		
}
