package com.formy.genericutility;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterSuite;

public class BaseClass {
	
	public static WebDriver driver;
	/**
	 *  This annotation is used to Open the Database Connection with all 
	 * necessary inputs
	 * @throws Throwable
	 */
	@BeforeSuite

	public void establishDataBaseConnection() throws Throwable {
		System.out.println("Data base Connetion is opened");
	}
	/**
	 * This annotation is used to Launch the browser with all precondition
	 * @param browser
	 * @throws Throwable 
	 */
	//@Parameters("browser")
	@BeforeClass
	//String browser
	public void launchBrowser() throws Throwable {
		
	//public void launchBrowser() throws Throwable {
			
		String browser="chrome";
		 
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}else {
			Reporter.log("Please Specify Browser Properly", true);
		}
	}
	/**
	 * This annotation is used to Login To Application 
	 */
	@BeforeMethod
	public void loginToApplication() {
		String url = "https://formy-project.herokuapp.com/form";
		WebDriverUtility.launchApplicationWithMaximize(driver, url, 10);
	}
	/**
	 * This Annotation is used to Logout from application
	 */
	@AfterMethod
	public void logOutFromApplication() {

	//	System.out.println("Logout from Application");
	}
	/**
	 * This annotation is used to  Close the Browser
	 */
	@AfterClass
	public void closeBrowser() {
		WebDriverUtility.quiteBrowser(driver);

	}
	/**
	 * This annotation is used to Close the DataBase Connection
	 * @throws Throwable
	 */
	@AfterSuite
	public void closeDatabaseConnection() throws Throwable {
		System.out.println("Data connetion closed");
	}
}