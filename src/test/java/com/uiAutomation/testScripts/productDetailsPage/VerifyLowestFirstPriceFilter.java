package com.uiAutomation.testScripts.productDetailsPage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;




//import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.uiAutomation.helper.assertion.AssertionHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiAutomation.helper.javaScript.JavaScriptHelper;
//import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.pageObject.NavigationMenu;
import com.uiAutomation.pageObject.ProductCategoryPage;
import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class VerifyLowestFirstPriceFilter extends TestBase {
	
//	private static final Logger log = LoggerHelper.getLogger(VerifyLowestFirstPriceFilter.class);
	
	@Test(description="Verify Lowest Price products")
	public void VerifyLowestFirstPriceListInProduct_detailsPage() throws InterruptedException{
		getApplicationURL(ObjectReader.reader.getURL());
		
		NavigationMenu navigationMenu = new NavigationMenu(driver);
		
		ProductCategoryPage pcatagoryPage = navigationMenu.clickOnMenu(navigationMenu.womenMenu);

		new JavaScriptHelper(driver).scrollIntoView(pcatagoryPage.sortBy);
		
		// select price filter
		pcatagoryPage.selectSortByFilter("Price: Lowest first");
		
		// wait for some time to make sure price is sorted
		Thread.sleep(8000);
		
		List<WebElement> price = pcatagoryPage.getAllProductsPrice();
		Iterator<WebElement> itr = price.iterator();		
		ArrayList<Integer> array = new ArrayList<Integer>();

		pcatagoryPage.getPriceMasaagedData(itr);
		boolean status = pcatagoryPage.verifyArrayHasAscendingData(array);
		
		AssertionHelper.updateTestStatus(status);
	}
}
