package com.ots.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest 
{

	@Test
	public void test1()
	{
		System.out.println("Test 1 Step 1");
		System.out.println("Test 1 Step 2");
		Assert.assertTrue(false);
		System.out.println("Test 1 Step 3");
		System.out.println("Test 1 Step 4");
	}
	
	
}
