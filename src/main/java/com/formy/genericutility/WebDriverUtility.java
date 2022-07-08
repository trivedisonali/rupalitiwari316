package com.formy.genericutility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This Class Contains All Generic or Common Methods of WebDriver
 * @author shank
 *
 */
public class WebDriverUtility {
	/**
	 * This Method is used to Wait until the element is visible 
	 * @param driver
	 * @param timeout
	 * @param element
	 */

	public static void waitUntilElementVisible(WebDriver driver,long timeout,WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This Method is  used to wait implicit for specified time
	 * @param driver
	 * @param timeout
	 */
	public static void implicitWait(WebDriver driver,long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}
	/**
	 * This Method is used to wait until element visible with specific polling time 
	 * @param driver
	 * @param timeout
	 * @param element
	 * @param pollingTime
	 */
	public static void waitUntilElementVisibleWithCustomPoll(WebDriver driver, int timeout,WebElement element,long pollingTime) {
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		wait.until(ExpectedConditions.visibilityOf(element));
		wait.pollingEvery(Duration.ofSeconds(pollingTime));
		wait.ignoring(Throwable.class);

	}
	/**
	 * This is Method will wait until element is clickable with customize time and polling period 
	 * @param timeout
	 * @param element
	 * @param pollingTime
	 * @throws InterruptedException
	 */
	public static void customWaitTillElementClickable(long timeout,WebElement element,int pollingTime) throws InterruptedException {
		int count=0;
		while(count<=timeout) {
			try {
				element.click();
				break;
			} catch (NoSuchElementException e) {
				Thread.sleep(pollingTime);
				count++;
			}
		}
	}
	/**
	 * This Method is used to Maximize the Browser window
	 * @param driver
	 * @return 
	 */
	public static void maximizeBrowserWindow(WebDriver driver) {
		driver.manage().window().maximize();	
	}
	/**
	 * This Method will used to open the application with maximize
	 * @param driver
	 * @param url
	 * @param timeout
	 */
	public static void launchApplicationWithMaximize(WebDriver driver, String url,long timeout) {
		maximizeBrowserWindow(driver);
		implicitWait(driver, timeout);
		driver.get(url);
	}
	/**
	 * This Method is used to Close the Current Window of the Application
	 * @param driver
	 */
	public static void closeBrowser(WebDriver driver) {
		driver.close();
	}
	/**
	 * This Method is used to Quite Entire the Browser Session
	 * @param driver
	 * @return 
	 */
	public static void quiteBrowser(WebDriver driver) {
		driver.quit();

	}
	/**
	 * This Method is used to switch to specific window by using partial text of Title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindow(WebDriver driver,String partialTitleText) {
		Set<String> allWindowIDS = driver.getWindowHandles();
		for (String id : allWindowIDS) {
			driver.switchTo().window(id);
			if (driver.getTitle().contains(partialTitleText)) {
				break;
			}
		}
	}
	/**
	 * This Method is used to Switch to Specific Window by using Current URL partial Text
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindow(String partialURLText,WebDriver driver) {
		Set<String> allWindowIDS = driver.getWindowHandles();
		for (String id : allWindowIDS) {
			driver.switchTo().window(id);
			if (driver.getCurrentUrl().contains(partialURLText)) {
				break;
			}
		}
	}
	public static void switchToParentWindow(WebDriver driver) {
		String parentWindow = driver.getWindowHandle();
		driver.switchTo().window(parentWindow);
	}
	/**
	 * This Method is used to move the mouse cursor on Particular WebElement
	 * @param driver
	 * @param element
	 */
	public static void moveToElement(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).perform();
	}
	/**
	 * This Method is used to Double Click on Particular WebElement 
	 * @param driver
	 * @param element
	 */
	public static void doubleClickOnElement(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.doubleClick(element).perform();
	}
	/**
	 * This Method is used to Right Click on Particular WebElement
	 * @param driver
	 * @param element
	 */
	public static void rightClickOnElement(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.contextClick(element).perform();
	}
	/**
	 * This Method is used to Right Click on WebPage 
	 * @param driver
	 */
	public static void doubleClickOnElement(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.contextClick().perform();
	}
	/**
	 * This Method is used to Move the Element from one Place(SourceElement) to Another Place(TargetElement)
	 * @param driver
	 * @param sourceElement
	 * @param targetElement
	 */
	public static void dragAndDrop(WebDriver driver,WebElement sourceElement,WebElement targetElement) {
		Actions actions = new Actions(driver);
		actions.dragAndDrop(sourceElement, targetElement).perform();
	}
	/**
	 * This Method is used to Click WebElement by using Actions Class
	 * @param driver
	 */
	public static void click(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.click().perform();
	}
	/**
	 * This Method is used to Click the Particular WebElement by using Actions Class
	 * @param driver
	 * @param element
	 */
	public static void click(WebDriver driver,WebElement element) {
		Actions actions = new Actions(driver);
		actions.click(element).perform();
	}
	/**
	 * This Method is Used to Select the DropDown By using Index option
	 * @param element
	 * @param index
	 */
	public static void select(WebElement element,int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}
	/**
	 * This Method is Used to Select the DropDown By using value option
	 * @param element
	 * @param value
	 */
	public static void select(WebElement element,String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}
	/**
	 * This Method is Used to Select the DropDown By using VisibleText option
	 * @param visibleText
	 * @param element
	 */
	public static void select(String visibleText,WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}
	/**
	 * This Method is Used to DeSelect the DropDown By using Index option
	 * @param element
	 * @param index
	 */
	public static void deSelect(WebElement element, int index) {
		Select select = new Select(element);
		select.deselectByIndex(index);
	}
	/**
	 * This Method is Used to DeSelect the DropDown By using Value option
	 * @param element
	 * @param value
	 */
	public static void deSelect(WebElement element,String value) {
		Select select = new Select(element);
		select.deselectByValue(value);
	}
	/**
	 * This Method is Used to DeSelect the DropDown By using VisibleText option
	 * @param visibleText
	 * @param element
	 */
	public static void deSelect(String visibleText,WebElement element) {
		Select select = new Select(element);
		select.deselectByVisibleText(visibleText);
	}
	/**
	 * This Method is used to Switch to frame By using Index Number
	 * @param driver
	 * @param index
	 */
	public static void frame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}
	/**
	 * This Method is used to Switch to frame By using WebElement
	 * @param driver
	 * @param element
	 */
	public static void frame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * This Method is used to Switch to frame By using name or Id
	 * @param driver
	 * @param nameOrId
	 */
	public static void frame(WebDriver driver,String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * This Method is used to take the screenShot of Failure Test Script
	 * @param driver
	 * @param screenShotFileName
	 * @throws IOException
	 */
	public static void takeScreenShotOfFailureScript(WebDriver driver,String screenShotFileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File dstFile = new File("./errorsShot/"+screenShotFileName+JavaUtility.getDateAndTime()+".png");
		//Files.copy(srcFile, dstFile);
		FileUtils.copyFile(srcFile, dstFile);	
	}
	/**
	 * This Method is used to ScreentShot and Get the Path 
	 * @param driver
	 * @param screenShotFileName
	 * @return
	 * @throws IOException
	 */
	public static String takeScreenShotAndgetPath(WebDriver driver,String screenShotFileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File srcFile = ts.getScreenshotAs(OutputType.FILE);
		File dstFile = new File("./errorsShot/"+screenShotFileName+JavaUtility.getDateAndTime()+".png");
		FileUtils.copyFile(srcFile, dstFile);
		//Files.copy(srcFile, dstFile);
		String absolutePath = dstFile.getAbsolutePath();
		return absolutePath;		
	}
	/**
	 * This Method is used to scroll the WebPage Until the WebElement is Present by using Java Script
	 * @param driver
	 * @param element
	 */
	public static void clickElementThroughJS(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView();",element);
	}
	/**
	 * This Method is used to click the WebElement by using Java Script
	 * @param driver
	 * @param element
	 */
	public static void scrollTillElementVisible(WebDriver driver, WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();",element);
	}
	/**
	 * This Method is used to Scroll the webPage Up OR Down
	 * @param driver
	 * @param upOrDown
	 */
	public static void pageScrollDownThroughJS(WebDriver driver, String upOrDown) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,"+upOrDown+"document.body.scrollHeight);");
	}
	/**
	 * This Method is used to open the application through javaScript
	 * @param driver
	 * @param url
	 */
	public static void openApplicationThroughJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.location='"+url+"'");
	}
	/**
	 * This Method is used to Send the Text into WebElement
	 * @param driver
	 * @param url
	 * @param element
	 * @param input
	 */
	public static void sendKeysThroughJS(WebDriver driver, String url, WebElement element,String input) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].value='"+input+"'",element);
	}
	/**
	 * This Method is used to Launch the Browser
	 * @param browser
	 * @param driver
	 * @return 
	 */
	public static WebDriver launchBrowser(String browser,WebDriver driver) {
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}else if(browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver(); 	
		}else if(browser.equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver= new InternetExplorerDriver(); 
		}else {
			System.out.println("Please Specify Browser Name Properly");
		}
		return driver;	
	}	
	/**
	 * This Method is used to switch to Alert PopUp
	 * @param driver
	 * @return 
	 */
	public static String getAlertPopUpText(WebDriver driver) {

		Alert alert = driver.switchTo().alert();
		String alertPopUpText = alert.getText();
		alert.accept();
		return alertPopUpText;
	}
	/**
	 * This Method is Used to Accept Alert PopUp
	 * @param driver
	 */
	public static void acceptAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	/**
	 *This Method is Used to Dismiss Alert PopUp
	 * @param driver
	 */
	public static void dismisstAlertPopUp(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
}

