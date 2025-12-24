package com.ots.listeners;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.ots.dataprovider.ConfigUtility;
import com.ots.factory.BrowserFactory;
import com.ots.helper.Utility;

public class MyTestNGListeners implements ITestListener 
{
	public void onTestSuccess(ITestResult result) 
	{
		ChainTestListener.log("LOG:INFO -Test Passed "+result.getMethod().getMethodName());
		
		if(ConfigUtility.readProperty("screenshotOnSuccess").equalsIgnoreCase("true"))
		{
			String base64=Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
			
			ChainTestListener.embed(base64, "image/png");
		}
	}

	public void onTestFailure(ITestResult result)
	{
		ChainTestListener.log("LOG:INFO -Test Failed "+result.getMethod().getMethodName());
		
		ChainTestListener.log("LOG:INFO -Exceptions "+result.getThrowable().getMessage());
		
		if(ConfigUtility.readProperty("screenshotOnFailure").equalsIgnoreCase("true"))
		{
			String base64=Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
			
			ChainTestListener.embed(base64, "image/png");
		}
	
	}

	public void onTestSkipped(ITestResult result) 
	{
		ChainTestListener.log("LOG:INFO -Test Skipped "+result.getMethod().getMethodName());
		
		ChainTestListener.log("LOG:INFO - Exceptions "+result.getThrowable().getMessage());
		
		if(ConfigUtility.readProperty("screenshotOnSkip").equalsIgnoreCase("true"))
		{
			String base64=Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
			
			ChainTestListener.embed(base64, "image/png");
		}
	}

}
