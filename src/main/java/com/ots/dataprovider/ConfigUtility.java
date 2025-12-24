package com.ots.dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigUtility 
{
	
	
	public static String readProperty(String keyName)
	{
		
		//System.out.println("LOG:INFO - Reading "+ keyName+ " From Config File");
		
		String value = null;
		try 
		{
			File src=new File(System.getProperty("user.dir")+"/Configuration/config.properties");
			
			FileInputStream fis=new FileInputStream(src);
			
			Properties pro=new Properties();
			
			pro.load(fis);
			
			value=pro.getProperty(keyName);
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("Failed to locator config file "+e.getMessage());
			
		} catch (IOException e) 
		{
			System.out.println("Failed to load/read config file "+e.getMessage());
		}
		
		return value;
	}
	
	public static String readProperty(String keyName,String configName)
	{
		
		String value = null;
		try 
		{
			File src=new File(System.getProperty("user.dir")+"/Configuration/"+configName+".properties");
			
			FileInputStream fis=new FileInputStream(src);
			
			Properties pro=new Properties();
			
			pro.load(fis);
			
			value=pro.getProperty(keyName);
			
		} catch (FileNotFoundException e) 
		{
			System.out.println("Failed to locator config file "+e.getMessage());
			
		} catch (IOException e) 
		{
			System.out.println("Failed to load/read config file "+e.getMessage());
		}
		
		return value;
	}

}
