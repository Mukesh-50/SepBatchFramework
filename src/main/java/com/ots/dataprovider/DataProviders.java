package com.ots.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
	
	@DataProvider(name="logindata")
	public static Object[][] getLoginData()
	{
		System.out.println("LOG:INFO-Loading Data From Excel");
		
		Object[][]arr=ExcelUtility.getData("LoginDetails");
		
		System.out.println("LOG:INFO-Test Data Is Ready");
		
		return arr;
	}
	
	@DataProvider(name="getRegistrationData")
	public static Object[][] getRegistrationData()
	{
		System.out.println("LOG:INFO-Loading Registration Data From Excel");
		
		Object[][]arr=ExcelUtility.getData("RegisterationDetails","TestData");
		
		System.out.println("LOG:INFO-Registration Test Data Is Ready");
		
		return arr;
	}
	
	
	@DataProvider(name="getCourseData")
	public static Object[][] getCourseData()
	{
		System.out.println("LOG:INFO-Loading Data From Excel");
		
		Object[][]arr=ExcelUtility.getData("CourseDetails");
		
		System.out.println("LOG:INFO-Test Data Is Ready");
		
		return arr;
	}
	
	
	@DataProvider(name="getCategoryData")
	public static Object[][] getCategoryData()
	{
		System.out.println("LOG:INFO-Loading Data From Excel");
		
		Object[][]arr=ExcelUtility.getData("CategoryDetails");
		
		System.out.println("LOG:INFO-Test Data Is Ready");
		
		return arr;
	}

}
