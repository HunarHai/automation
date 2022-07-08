package com.uiAutomation.testBase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.uiAutomation.helper.browserConfiguration.BrowserType;
import com.uiAutomation.helper.browserConfiguration.ChromeBrowser;
import com.uiAutomation.helper.browserConfiguration.FirefoxBrowser;
import com.uiAutomation.helper.browserConfiguration.IExplorerBrowser;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiAutomation.helper.browserConfiguration.config.PropertyReader;
import com.uiAutomation.helper.javaScript.JavaScriptHelper;
import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.resource.ResourceHelper;
import com.uiAutomation.helper.wait.WaitHelper;
import com.uiAutomation.utils.ExtentManager;

/**
 * This is a Base Class of the project
 * @author vasudevp
 *
 */
public class TestBase {
	
	public WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(TestBase.class);
	
	public static ExtentReports extent;
	public static ExtentTest test;
	public static File reportDirectory;
	
	/**
	 * This beforeSuite() method is to instantiating ExtentManager class for extent report
	 */
	@BeforeSuite
	public void beforeSuite(){
		extent =  ExtentManager.getInstance();
	}
	
	/**
	 * 
	 */
	@BeforeTest
	public void beforeTest(){
		ObjectReader.reader = new PropertyReader();
	//	reportDirectory = new File(ResourceHelper.getResourcePath("../screenshots//"));
		reportDirectory = new File("src/test/resources/screenshots");
		
		System.out.println("ResourceHelper user.dir >>> " + System.getProperty("user.dir") );		
		System.out.println("reportDirectory :       " + reportDirectory );
		try {
			setUpDriver(ObjectReader.reader.getBrowserType());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	@BeforeClass
	public void beforeClass(){
		test = extent.createTest(getClass().getSimpleName());
		test.log(Status.INFO, getClass().getSimpleName());
	}
	
	/**
	 * 
	 * @param method
	 */
	@BeforeMethod
	public void beforeMethod(Method method){
		test.log(Status.INFO, "Test Case "+method.getName()+"() started.");
	}
	
	/**
	 * 
	 * @param result
	 */
	@AfterMethod
	public void afterMethod(ITestResult result){
		if(result.getStatus() == ITestResult.FAILURE){
			test.log(Status.FAIL, result.getThrowable());
			test.log(Status.FAIL, result.getName()+"() is FAIL.");
			try {
				String imagePath = captureScreenshot(result.getName(), driver);
				test.addScreenCaptureFromPath(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SUCCESS){
			test.log(Status.PASS, result.getName()+"() is PASS.");
			test.log(Status.PASS, result.getName()+"() test completed.");
			try {
				String imagePath = captureScreenshot(result.getName(), driver);
				test.addScreenCaptureFromPath(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(result.getStatus() == ITestResult.SKIP){
			test.log(Status.SKIP, result.getThrowable());
			test.log(Status.PASS, result.getName()+"() is SKIP.");
		}
		extent.flush();
	}

	/**
	 * This method is to quit the driver after test, if not null.
	 */
	@AfterTest
	public void afterTest(){
		if(driver!=null){
			driver.quit();
		}
	}
	
	@AfterMethod
	public void afterMethod(Method method){
		test.log(Status.INFO, method.getName()+" test finished.");
	}
	
	@AfterClass
	public void afterClass(){
		System.out.println("	This is AfterClass line.");
	}
	
	@AfterSuite
	public void afterSuite(){
		System.out.println("	This is AfterSuite line.");
	}

	
	/**
	 * 
	 * @param browserType
	 * @return
	 * @throws Exception
	 */
	public WebDriver getBrowserObject(BrowserType browserType) throws Exception{
		try{
			switch(browserType){
			case Chrome:
				// get object of ChromeBrowser class
				ChromeBrowser chrome = ChromeBrowser.class.newInstance();
				ChromeOptions chromeOption = chrome.getChromeOptions();
				return chrome.getChromeDriver(chromeOption);
				
			case Firefox:
				// get object of FirefoxBrowser class
				FirefoxBrowser firefox = FirefoxBrowser.class.newInstance();
				FirefoxOptions ffOptions = firefox.getFirefoxOptions();
				return firefox.getFirefoxDriver(ffOptions);
								
			case Iexplorer:
				// get object of IExplorerBrowser class
				IExplorerBrowser ie = IExplorerBrowser.class.newInstance();
				InternetExplorerOptions ieOptions = ie.getIExplorerCapabilities();
				return ie.getInternetExplorerDriver(ieOptions);

			default: 
				throw new Exception("Driver not found: "+browserType.name());
			}
		}
		catch(Exception e){
			log.info(e.getMessage());
			throw e;
		}
	}
	
	/**
	 * This method is to setup WebDriver per browser type 
	 * and also set required wait time
	 * @param browserType
	 * @throws Exception
	 */
	public void setUpDriver(BrowserType browserType) throws Exception{
		driver = getBrowserObject(browserType);
		log.info("Initialized WebDriver: "+driver.hashCode());
		
		WaitHelper wait = new WaitHelper(driver);
		wait.setImplicitWait(ObjectReader.reader.getImplicitWait(), TimeUnit.SECONDS);
		wait.pageLoadTime(ObjectReader.reader.getPageLoadTime(), TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is to capture the screenshot
	 * @param fileName
	 * @return
	 */
	public String captureScreenshot(String fileName, WebDriver driver){
		if(driver == null){
			log.info("driver is null...");
			return null;
		}
		if(fileName==""){
			fileName="blank";
		}
		
		File destFile = null;
		Calendar calender = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss_SSS");
		
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try{
			String filePath = reportDirectory.toString() + "/" + fileName + "_" +formater.format(calender.getTime()) + ".png";
			destFile = new File(filePath);
			Files.copy(scrFile.toPath(), destFile.toPath());
			Reporter.log("<a href='" +destFile.getAbsolutePath()+ "'> <img src='" +destFile.getAbsolutePath()+ "'height='100' width='100'/></a>");
			test.addScreenCaptureFromPath(filePath, "testTitle"); //  ("<a href='" +destFile.getAbsolutePath()+ "'> <img src='" +destFile.getAbsolutePath()+ "'height='100' width='100'/></a>")
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		
		return destFile.toString();
	}

	/**
	 * 
	 * @param driver
	 */
	public void getNavigationScreen(WebDriver driver){
		log.info("Capturing screenshot for UI navigation...");
		new JavaScriptHelper(driver).zoomInBy40Percentage();
		String screen = captureScreenshot("", driver);
		new JavaScriptHelper(driver).zoomInBy100Percentage();
	/*	try { 
			 test.addScreenCaptureFromPath(screen); 
		}
		catch (IOException e){
			e.printStackTrace();
		} */
	}

	/**
	 * 
	 * @param s1
	 */
	public static void logExtentReport(String s1){
		test.log(Status.INFO, s1);
	}
	
	/**
	 * 
	 * @param url
	 */
	public void getApplicationURL(String url){
		driver.get(url);
		logExtentReport("Getting application thru ..." + url);
	}
	
}