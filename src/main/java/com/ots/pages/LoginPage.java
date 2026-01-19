package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

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
	
	
	public Cartpage  loginToApplicationcart(String user, String pass) 
	{
		driver.findElement(username).sendKeys(user);
		
		driver.findElement(password).sendKeys(pass);
		
		driver.findElement(loginButton).click();
	
		Cartpage cart = PageFactory.initElements(driver, Cartpage.class);

		return cart;
	}


}
