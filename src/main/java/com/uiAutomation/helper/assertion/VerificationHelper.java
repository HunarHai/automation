package com.uiAutomation.helper.assertion;

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
public class VerificationHelper {
	
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(VerificationHelper.class);
	
	/**
	 * This is parameterized constructor to access and initialize 
	 * the private WebDriver 'driver' variable
	 * @param driver
	 */
	public VerificationHelper(WebDriver driver){
		this.driver = driver;
		log.info("VerificationHelper object is created...");
		TestBase.logExtentReport("VerificationHelper object is created...");
	}
	
	/**
	 * This method is to return element is displayed
	 * @param element
	 * @return
	 */
	public boolean isDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info("Element is Displayed..." + element.getText());
			TestBase.logExtentReport("Element is Displayed..." + element.getText());
			return true;
		}catch(Exception e){
		//	log.error("Element is not Displayed..." + e.getMessage());
			log.error("Element is not Displayed..." + e.getCause());
			TestBase.logExtentReport("Element is not Displayed..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is not displayed
	 * @param element
	 * @return
	 */
	public boolean isNotDisplayed(WebElement element){
		try{
			element.isDisplayed();
			log.info("Element is Displayed..." + element.getText());
			TestBase.logExtentReport("Element is Displayed..." + element.getText());
			return false;
		}catch(Exception e){
			log.error("Element is not Displayed...");
			TestBase.logExtentReport("Element is not Displayed...");
			return true;
		}
	}
	
	/**
	 * This method is to return element is enabled
	 * @param element
	 * @return
	 */
	public boolean isEnabled(WebElement element){
		try{
			element.isEnabled();
			log.info("Element is enabled." + element.getText());
			TestBase.logExtentReport("Element is enabled." + element.getText());
			return true;
		}
		catch(Exception e){
			log.error("Element is not enabled..." + e.getCause());
			TestBase.logExtentReport("Element is not enabled..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is not enabled
	 * @param element
	 * @return
	 */
	public boolean isNotEnabled(WebElement element){
		try{
			element.isEnabled();
			log.info("Element is enabled..." + element.getText());
			TestBase.logExtentReport("Element is enabled..." + element.getText());
			return false;
		}catch(Exception e){
			log.error("Element is not enebled...");
			TestBase.logExtentReport("Element is not enabled...");
			return true;
		}
	}
	
	/**
	 * This method is to return element is selected
	 * @param element
	 * @return
	 */
	public boolean isSelected(WebElement element){
		try{
			element.isSelected();
			log.info("Element is selected..." + element.getText());
			TestBase.logExtentReport("Element is selected..." + element.getText());
			return true;
		}catch(Exception e){
			log.error("Element is not selected..." +e.getCause());
			TestBase.logExtentReport("Element is not selected..." + e.getCause());
			return false;
		}
	}
	
	/**
	 * This method is to return element is not selected
	 * @param element
	 * @return
	 */
	public boolean isNotSelected(WebElement element){
		try{
			element.isSelected();
			log.info("Element is selected..." + element.getText());
			TestBase.logExtentReport("Element is selected..." + element.getText());
			return false;
		}catch(Exception e){
			log.error("Element is not selected...");
			TestBase.logExtentReport("Element is not selected...");
			return true;
		}
	}
	
	/**
	 * This method is to read value/text from displayed element
	 * @param element
	 * @return
	 */
	public String getText(WebElement element){
		if(null == element){
			log.info("Element is null...");
			TestBase.logExtentReport("Element is null...");
			return null;
		}
		boolean status = isDisplayed(element);
		if(status){
			log.info("Element text is "+element.getText());
			TestBase.logExtentReport("Element text is " + element.getText());
			return element.getText();
		}
		else{
			return null;
		}
	}
	
	/**
	 * This method will provide page title
	 * @return
	 */
	public String getTitle(){
		log.info("Page Title is "+driver.getTitle());
		TestBase.logExtentReport("Page Title is " + driver.getTitle());
		return driver.getTitle();
	}

}
