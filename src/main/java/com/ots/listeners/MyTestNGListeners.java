package com.ots.listeners;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.ots.dataprovider.ConfigUtility;
import com.ots.factory.BrowserFactory;
import com.ots.helper.Utility;

public class MyTestNGListeners implements ITestListener 
{
  
	  private static final String RUN_TS;
	    private static final String REPORT_DIR;

	    static {
	        RUN_TS = LocalDateTime.now()
	                .format(DateTimeFormatter.ofPattern("hh_mm_ss_dd_MM_yyyy"));

	        REPORT_DIR = "Reports/chaintest_" + RUN_TS;
	        new File(REPORT_DIR).mkdirs();

	        // ✅ set it BEFORE any test methods run
	        System.setProperty("chaintest.generator.simple.output-file", REPORT_DIR + "/Index.html");
	        // If you use email generator too
	        System.setProperty("chaintest.generator.email.output-file", REPORT_DIR + "/Email.html");
	    }
	
    @Override
    public void onTestSuccess(ITestResult result) 
    {
        ChainTestListener.log("LOG:INFO -Test Passed " + result.getMethod().getMethodName());

        if (ConfigUtility.readProperty("screenshotOnSuccess").equalsIgnoreCase("true"))
        {
            String base64 = Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
            ChainTestListener.embed(base64, "image/png");
        }
    }

    @Override
    public void onTestFailure(ITestResult result)
    {
        ChainTestListener.log("LOG:INFO -Test Failed " + result.getMethod().getMethodName());
        ChainTestListener.log("LOG:INFO -Exceptions " + result.getThrowable().getMessage());

        if (ConfigUtility.readProperty("screenshotOnFailure").equalsIgnoreCase("true"))
        {
            String base64 = Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
            ChainTestListener.embed(base64, "image/png");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) 
    {
        ChainTestListener.log("LOG:INFO -Test Skipped " + result.getMethod().getMethodName());
        ChainTestListener.log("LOG:INFO - Exceptions " + result.getThrowable().getMessage());

        if (ConfigUtility.readProperty("screenshotOnSkip").equalsIgnoreCase("true"))
        {
            String base64 = Utility.captureScreenshot(BrowserFactory.getDriver(), "base64");
            ChainTestListener.embed(base64, "image/png");
        }
    }
}
