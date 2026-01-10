package com.ots.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ots.base.BaseClass;
import com.ots.dataprovider.DataProviders;
import com.ots.pages.LoginPage;
import com.ots.pages.RegistrationPage;

public class RegistrationTest extends BaseClass{
	
	LoginPage login;
	RegistrationPage registrationpage;
	
	@Test(description = "This test will check  Registration page Loading ")
	public void verifyRegistrationpagenavigation()
	{
		login=new LoginPage(driver);
		registrationpage=login.navigatetoSignuppage();
		Assert.assertTrue(registrationpage.isHeaderPresent(),"Sign Up Page not loading by click on the - New user? Signup Link");
	}
	
	@Test(description = "This test will check valid Registration functionality with single user")
	public void validRegistrationwithstaticdata()
	{
		login=new LoginPage(driver);
		registrationpage=login.navigatetoSignuppage();
		login=registrationpage.fillregistrationformwithvaliddata();
		Assert.assertTrue(login.VerifySignupConfirmationMessage(),"Signup successfull alert message is not displaying after signing up");
	}
	
	@Test(description = "This test will check valid multiple Registrations by using data provider",dataProvider = "getRegistrationData",dataProviderClass = DataProviders.class)
	public void validRegistration(String Name, String Email, String Password, String Interests,String Gender,String State,String Hobbies) 
	{
		login=new LoginPage(driver);
		registrationpage=login.navigatetoSignuppage();
		login=registrationpage.fillregistrationformusingdataprovider(Name,Email,Password,Interests,Gender,State,Hobbies);
		Assert.assertTrue(login.VerifySignupConfirmationMessage(),"Signup successfull alert message is not displaying after signing up");
	}

}