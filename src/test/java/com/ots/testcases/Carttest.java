package com.ots.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ots.base.BaseClass;
import com.ots.dataprovider.ConfigUtility;
import com.ots.dataprovider.DataProviders;
import com.ots.pages.Cartpage;
import com.ots.pages.LoginPage;

public class Carttest extends BaseClass {
	
	
	Cartpage cart;
	LoginPage login;
	
	@Test(dataProvider = "logindata",dataProviderClass = DataProviders.class)
	public void valodloginmovetocart(String uname,String pass) throws InterruptedException
	{
		login=new LoginPage(driver);
		
		login.loginToApplicationcart(uname, pass);
		
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));
		
		
	}
	
	
	@Test(dependsOnMethods = "valodloginmovetocart")
	public void clickcartpage()
	{
		  cart = new Cartpage(driver);
		  cart.clickcart();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Integer.parseInt(ConfigUtility.readProperty("pageloadTime"))));

	}
	
	@Test(dependsOnMethods = "clickcartpage",description = "This test will check add to cart functionality",dataProvider = "cartdata",dataProviderClass = DataProviders.class)
	public void addtocart(String Addr,String phno) {

	    cart = new Cartpage(driver);
	    cart.addtocart(Addr,phno);
	    

		
	}

	
//	@Test
//	public void verifyurl()
//	{
//		
//		cart = new Cartpage(driver);
//		
//        String curl=cart.verifycurrenturl();
//		
//		Assert.assertEquals(curl, "https://freelance-learn-automation.vercel.app/cart");
//
//	}
			
			
	
	

}
