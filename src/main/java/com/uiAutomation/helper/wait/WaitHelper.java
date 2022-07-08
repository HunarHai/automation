package com.uiAutomation.helper.wait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.uiAutomation.helper.logger.LoggerHelper;
//import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class WaitHelper {
	
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(WaitHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize 
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public WaitHelper(WebDriver driver){
		this.driver = driver;
		log.info("WaitHelper object created...");
	//	TestBase.logExtentReport("WaitHelper object is created...");
	}

	/**
	 * This is ImplicitWait method
	 * @param timeout
	 * @param unit
	 */
	@SuppressWarnings("deprecation")
	public void setImplicitWait(long timeout, TimeUnit unit){
		log.info("Implicit Wait has been set to "+timeout+ " seconds.");
		driver.manage().timeouts().implicitlyWait(timeout, unit);
	}
	
	/**
	 * This is help us to get WebDriverWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec){
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.pollingEvery(pollingEveryInMiliSec,TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		wait.ignoring(ElementNotVisibleException.class);
		wait.ignoring(StaleElementReferenceException.class);
		wait.ignoring(NoSuchFrameException.class);
		return wait;
	}
	
	/**
	 * This method will make sure element is Visible with Polling Time
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 */
	public void WaitForElementVisibleWithPollingTime(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec){
		log.info("Waiting for :"+ element.toString() +" for: " + timeOutInSeconds +" seconds.");
		WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);		
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now.");
	}
	
	/**
	 * This method will make sure elementToBeClickable
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForElementToBeClickable(WebElement element, int timeOutInSeconds){
		log.info("Waiting for: "+element.toString()+" for: "+timeOutInSeconds+" seconds.");
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		log.info("Element is clickable now.");
	}
	
	/**
	 * This method will make sure invisibility of element
	 * @param element
	 * @param timeOutInSeconds
	 * @return
	 */
	public boolean WaitForElementNotPresent(WebElement element, long timeOutInSeconds){
		log.info("Waiting for: "+element.toString()+" for: "+timeOutInSeconds+" seconds.");
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	//	wait.until(ExpectedConditions.invisibilityOf(element));
		boolean status = wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(element)));
		log.info("Element is invisible now.");
		return status;
	}
	
	/**
	 * This method will wait for FrameToBeAvailableAndSwitchToIt
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void WaitForFrameToBeAvailableAndSwitchToIt(WebElement element, long timeOutInSeconds){
		log.info("Waiting for: "+element.toString()+" for: "+timeOutInSeconds+" seconds.");
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
	//	wait.until(ExpectedConditions.invisibilityOf(element));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
		log.info("Frame is available and switched.");
	}
	
	/**
	 * This method will give us the fluentWait object
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	private Wait<WebDriver> getFluentWait(int timeOutInSeconds, int pollingEveryInMiliSec){
		@SuppressWarnings("deprecation")
		Wait<WebDriver> flWait = new FluentWait<WebDriver>(driver)
				.withTimeout(timeOutInSeconds,TimeUnit.SECONDS)
				.pollingEvery(pollingEveryInMiliSec,TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
			return flWait;
	}
	
	/**
	 * This method will make sure element is visible with FluentWait object
	 * @param element
	 * @param timeOutInSeconds
	 * @param pollingEveryInMiliSec
	 * @return
	 */
	public WebElement waitForElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec){
		Wait<WebDriver> fWait = getFluentWait(timeOutInSeconds, pollingEveryInMiliSec);
		fWait.until(ExpectedConditions.visibilityOf(element));
		return element;
		// fWait.until(ExpectedConditions.elementToBeSelected(element));
		// fWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method will make sure the page to get load 
	 * @param timeout
	 * @param unit
	 */
	@SuppressWarnings("deprecation")
	public void pageLoadTime(long timeout, TimeUnit unit){
		log.info("Waiting for page to load for: " + timeout + " seconds.");
		driver.manage().timeouts().pageLoadTimeout(timeout, unit);
		log.info("Page is loaded.");
	}
	
	/**
	 * This method will make sure the element to be visible
	 * @param element
	 * @param timeOutInSeconds
	 */
	public void waitForElement(WebElement element, int timeOutInSeconds){
		log.info("Waiting for: " + element.getAttribute("innerText") + " for: " + timeOutInSeconds + " second");
		@SuppressWarnings("deprecation")
		WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		log.info("Element is visible now.");
	}
	
}