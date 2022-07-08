package com.uiAutomation.pageObject;

import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiAutomation.helper.actions.ActionsHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.wait.WaitHelper;
import com.uiAutomation.testBase.TestBase;

/**
 * This NavigationMenu class helps us to navigate within menu
 * based on input which we supplies thru methods.
 * @author vasudevp
 *
 */
public class NavigationMenu {
	
	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(NavigationMenu.class);
		
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtMenu;
	
	/**
	 * 
	 * @param driver
	 */
	public NavigationMenu(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(womenMenu, ObjectReader.reader.getExplicitWait());
		
		log.info("NavigationMenu Object created...");
		TestBase.logExtentReport("NavigationMenu Object created...");
		new TestBase().getNavigationScreen(driver);
	}
	
	/**
	 * 
	 * @param toElement
	 */
	public void mouseHover(String toElement){
		new ActionsHelper(driver).mouseHover(toElement);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public ProductCategoryPage clickOnMenu(String element){
		new ActionsHelper(driver).mouseClick(element);
		return new ProductCategoryPage(driver);
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public ProductCategoryPage clickOnMenu(WebElement element){
	//	new ActionsHelper().mouseClick(element);
		new ActionsHelper(driver).mouseHoverAndClick(element, element);
		return new ProductCategoryPage(driver);
	}
	
	
	
}
