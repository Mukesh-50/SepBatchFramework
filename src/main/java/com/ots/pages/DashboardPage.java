package com.ots.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage 
{	
	WebDriver driver;
	
	public DashboardPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	
	private By welcomeMsg=By.className("welcomeMessage");
	
	private By sideMenu=By.xpath("//img[@alt='menu']");
	
	private By logout=By.xpath("//button[normalize-space()='Sign out']");
	
	
	public String getWelcomeMessage()
	{
		String welcomeText=driver.findElement(welcomeMsg).getText();
		
		return welcomeText;
	}
	
	
	public void signoutFromApplication()
	{
		driver.findElement(sideMenu).click();
		
		driver.findElement(logout).click();
	}
	
}
