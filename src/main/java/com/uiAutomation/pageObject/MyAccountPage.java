package com.uiAutomation.pageObject;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiAutomation.helper.assertion.VerificationHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.wait.WaitHelper;
import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class MyAccountPage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(MyAccountPage.class);

	WaitHelper waitHelper;

	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/nav/div[2]/a")
	public WebElement signOut;
	
	@FindBy(xpath = "//*[@id='center_column']/p")
	public WebElement yourAccountPageMsg;
	
	@FindBy(xpath = "//*[@id='columns']/div[1]/a")
	public WebElement returnToHomeIcon;
	
	@FindBy(xpath = "//*[@id='center_column']/ul/li/a/span")
	public WebElement btnHome;
	
	@FindBy(xpath = "//*[@id='center_column']/div/div[1]/ul/li[1]/a")
	public WebElement orderHistory;
	
	@FindBy(xpath = "//*[@id='center_column']/div/div[1]/ul/li[1]/a")
	public WebElement myCreditSlips;
	
	@FindBy(xpath = "//*[@id='center_column']/div/div[1]/ul/li[1]/a")
	public WebElement myAddresses;
	
	@FindBy(xpath = "//*[@id='center_column']/div/div[1]/ul/li[1]/a")
	public WebElement myPersonalInformation;

	@FindBy(xpath = "//*[@id='center_column']/div/div[2]/ul/li/a")
	public WebElement myWishLists;
	
	@FindBy(xpath = "//*[@id='header']/div[2]/div/div/nav/div[1]/a/span")
	public WebElement customerName;
	
	/**
	 * This will make sure that object of this page is loaded and page
	 * WebElements are initialized
	 * 
	 * @param driver
	 */
	public MyAccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(yourAccountPageMsg, ObjectReader.reader.getExplicitWait());
		new TestBase().getNavigationScreen(driver);
		
		log.info("MyAccountPage object created...");
		TestBase.logExtentReport("MyAccountPage object created...");
	}
	
	public boolean verifyYourAccountPageMsg(){
		return new VerificationHelper(driver).isDisplayed(yourAccountPageMsg);
	}
	
}
