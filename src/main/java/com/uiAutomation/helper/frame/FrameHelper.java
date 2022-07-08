package com.uiAutomation.helper.frame;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class FrameHelper {
	
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(FrameHelper.class);
//	private final static Logger logger = Logger.getLogger(LoggerHelper.class);
	/**
	 * This is parameterized constructor to access and initialize 
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public FrameHelper(WebDriver driver){
		this.driver = driver;
		log.info("FrameHelper object is created...");
		TestBase.logExtentReport("FrameHelper object is created...");
	}

	/**
	 * This method will switchToFrame based on frameId
	 * @param frameId
	 */
	public void switchToFrame(int frameId){
		driver.switchTo().frame(frameId);
		log.info("Switched to: "+ frameId + " frame.");
	}
	
	/**
	 * This method will switchToFrame based on frameName
	 * @param frameName
	 */
	public void switchToFrame(String frameName){
		driver.switchTo().frame(frameName);
		log.info("Switched to: "+ frameName + " frame.");
	}
	
	/**
	 * This method will switchToFrame based on WebElement
	 * @param element
	 */
	public void switchToFrame(WebElement element){
		driver.switchTo().frame(element);
		log.info("Switch to frame having element: "+ element.toString());
	}
	/**
	 * This method will switchToParentFrame
	 */
	public void switchToParentFrame(){
		log.info("Switching to Parent frame...");
		driver.switchTo().parentFrame();
		log.info("Switched to Parent Frame.");
	}
}
