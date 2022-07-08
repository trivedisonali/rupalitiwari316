package com.formy.genericutility;

import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.Properties;

import org.testng.annotations.Test;

/**
 * This Class Contains All Generic or Common Methods of Properties
 * @author shank
 *
 */
public class FileUtility {
	/**
	 * This Method is use to Load and Initialize the PropertiesFile
	 * @throws IOException 
	 */
	public static Properties properties;

	public static void readDataFromProperties(String path) throws Throwable{	
		FileInputStream fis=new FileInputStream(path);
		properties = new Properties();
		properties.load(fis);
	}
	public static String getProperties(String key) {
		String value =properties.getProperty(key);
		return value; 
	}
}
