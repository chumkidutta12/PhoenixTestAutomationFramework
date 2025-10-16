package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigManager {
	
	//WAP to read the properties file from src/test/resources/config/config.properties
	private static Properties prop= new Properties();	//Create the object of Properites class\
	private static String path="config/config.properties";
	private static String env; 
	private ConfigManager() 
	{
		//Private Constructor!!!
	}
	
	static {
		env= System.getProperty("env", "qa");
		env=env.toLowerCase().trim();
		
		/*switch(env)
		{
		case "dev":
		{
			path="config/config.dev.properties";
			break;
		}
		
		case "qa":
		{
			path="config/config.qa.properties";
			break;
		}
		
		case "uat":
		{
			path="config/config.uat.properties";
			break;
		}
		default:
			path="config/config.qa.properties";
		}
		*/
		switch(env) {
		case "dev"  -> path="config/config.dev.properties"; 
		case "qa"  -> path="config/config.qa.properties"; 
		case "uat"  -> path="config/config.uat.properties"; 
		default  -> path="config/config.qa.properties"; 
		
		}
		
		InputStream input= Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		
		if(input==null)
		{
			throw new RuntimeException("Cannot find the file at the path"+path);
		}
		
		
		
		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) throws IOException
	{
		//load the Properties file using the load()
		
		
		return prop.getProperty(key);
	}	
	
}
