package com.ots.testcases;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SampleTestOnSeleniumGrid {

	public static void main(String[] args) throws InterruptedException 
	{
		ChromeOptions opt=new ChromeOptions();
		
		//FirefoxOptions opt=new FirefoxOptions();
		
		String hubUrl="http://54.235.238.143:4444/wd/hub";
		
		WebDriver driver=null;
		try 
		{
			driver=new RemoteWebDriver(new URL(hubUrl), opt);
			
		} catch (MalformedURLException e) 
		{
			System.out.println("Could not connect to selenium grid "+e.getMessage());
		}
		driver.manage().window().maximize();
		
		driver.get("https://freelance-learn-automation.vercel.app/login");
		
		driver.findElement(By.id("email1")).sendKeys("admin@email.com");
		
		driver.findElement(By.id("password1")).sendKeys("admin@123");
		
		driver.findElement(By.className("submit-btn")).click();
		
		Thread.sleep(60000);
		
		driver.quit();

	}

}
