package com.ots.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.ots.dataprovider.ConfigUtility;

public class Cartpage {
	
WebDriver driver;
	
	public Cartpage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	private By cartbtn=By.xpath("//button[text()='Cart']");
	
	private By shopnowclick=By.xpath("//button[normalize-space()='Shop Now']");
	
	private By course1=By.xpath("(//button[contains(text(),'Add to Cart')])[1]");
	
	private By course2=By.xpath("(//button[contains(text(),'Add to Cart')])[2]");
	
	private By enrollbtn=By.xpath("//div[@class='top-container']//button[normalize-space()='Enroll Now']");
	
	private By address=By.xpath("//textarea[@name='address' and @id='address']");
	
	private By phno=By.xpath("//input[@name='phone' and @id='phone']");
	
	private By finalenrlbtn=By.xpath("//button[@class='action-btn' and text()='Enroll Now']");
	
	
	
	
	
	
	
	
	
	
//    public String verifycurrenturl()
//    {
//    	String carturl=driver.getCurrentUrl();
//    	
//    	return carturl;
//    	
//    	
//    }
	
	
	public void clickcart()
	{
		driver.findElement(cartbtn).click();
		
    	driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));
    	
    	driver.findElement(shopnowclick).click();

	}
    
    
    
    public void addtocart(String addr,String phnum)
    {
    	

    	
    	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("implicitWait"))));

     	
     	driver.findElement(course1).click();
     	
     	driver.findElement(course2).click();
     	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("implicitWait"))));
     	
		driver.findElement(cartbtn).click();

     	
     	try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     	driver.findElement(enrollbtn).click();
     	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("implicitWait"))));

//      	try {
//    			Thread.sleep(8000);
//    		} catch (InterruptedException e) {
//    			// TODO Auto-generated catch block
//    			e.printStackTrace();
//    		}
      	
      	
     	
     	driver.findElement(address).sendKeys(addr);
     
      	try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      	
    	driver.findElement(phno).click();
    	
  	
     	driver.findElement(phno).sendKeys(phnum);
     	
     	
     	
     	
    }

}
