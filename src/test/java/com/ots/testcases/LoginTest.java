package com.ots.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.ots.base.BaseClass;
import com.ots.dataprovider.DataProviders;
import com.ots.pages.DashboardPage;
import com.ots.pages.LoginPage;


public class LoginTest extends BaseClass
{

	@Test(description = "This test will check valid user login functionality",dataProvider = "logindata",dataProviderClass = DataProviders.class)
	public void validLogin(String uname,String pass)
	{
		
		LoginPage login=new LoginPage(driver);
		
		DashboardPage dashboard=login.loginToApplication(uname, pass);
	
		String welcomeText=dashboard.getWelcomeMessage();
		
		Assert.assertTrue(welcomeText.contains("Welcome"),"Login Messages Did Not Appear");
		
		dashboard.signoutFromApplication();
		
		Assert.assertTrue(login.isHeaderPresent(),"Logout Failed - Sign In Is Not Present");
		
	}


}
