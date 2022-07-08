package com.uiAutomation.pageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.uiAutomation.helper.actions.ActionsHelper;
import com.uiAutomation.helper.browserConfiguration.config.ObjectReader;
import com.uiAutomation.helper.javaScript.JavaScriptHelper;
import com.uiAutomation.helper.logger.LoggerHelper;
import com.uiAutomation.helper.select.DropDownHelper;
import com.uiAutomation.helper.wait.WaitHelper;
import com.uiAutomation.testBase.TestBase;

/**
 * 
 * @author vasudevp
 *
 */
public class ProductCategoryPage {

	private WebDriver driver;
	private static final Logger log = LoggerHelper.getLogger(ProductCategoryPage.class);
	
	WaitHelper waitHelper;
	
	@FindBy(xpath="//*[contains(text(),'Catalog')]")
	public WebElement catalogTextObj;
	
	@FindBy(xpath = "//*[@id='center_column']/ul/li[")
	public WebElement fXPath;
	
	@FindBy(xpath = "]/div/div[2]/h5/a")
	public WebElement sXPath;
	
	@FindBy(id = "productCount")
	List<WebElement> productCount;

	@FindBy(xpath = "//*[@id='center_column']/ul/li/div/div[2]/div[1]/span")
	List<WebElement> allPriceElements;
	
	@FindBy(xpath="//*[@id='AddToCart']")
	public WebElement addToCart;

	@FindBy(xpath="//*[@id='proceedToCheckout']")
	public WebElement proceedToCheckout;

	@FindBy(xpath="//*[@id='selectProductSort']")	//SortingDropDown
	public WebElement sortBy;
	
	public ProductCategoryPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
		waitHelper = new WaitHelper(driver);
		waitHelper.waitForElement(catalogTextObj, ObjectReader.reader.getExplicitWait());
		
		TestBase.logExtentReport("ProductCategoryPage Object created...");
		new TestBase().getNavigationScreen(driver);
	}
	
	public void mouseHoverOnProduct(int number){
		String fXpath = fXPath.toString();
		String sXpath =  sXPath.toString();
		new ActionsHelper(driver).mouseHover(fXpath, number, sXpath);
	}
	
	public void clickOnAddToCart(){
		log.info("Clicking on Add To Cart button");
		TestBase.logExtentReport("Clicking on Add To Cart button");
		addToCart.click();
	}
	
	
	public void clickOnProceedToCheckout(){
		log.info("Clicking on: "+ proceedToCheckout.getText() +"button");
		TestBase.logExtentReport("Clicking on: "+ proceedToCheckout.getText() +"button");
		proceedToCheckout.click();
	}
	
	
	public void selectColor(String data){
		String xPathValue = "//*[contains(text(),'"+data+"')]/parent::*/preceding-sibling::input[1]";
		new JavaScriptHelper(driver).scrollIntoView(driver.findElement(By.xpath(xPathValue)));	
		driver.findElement(By.xpath(xPathValue)).click();
		try{
			Thread.sleep(10000);
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	} 
	
	public void selectSmallSize(){
		log.info("Selecting small size...");
		TestBase.logExtentReport("Selecting small size...");
		driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_1']")).click();
	}
	
	
	public void selectMediumSize(){
		log.info("Selecting medium size...");
		TestBase.logExtentReport("Selecting medium size...");
		try{
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']")).isSelected();
			if(!selected){
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_2']")).click();
				log.info("Checkbox is checked...");
				TestBase.logExtentReport("Checkbox is checked...");
			}
		}
		catch(Exception e){
			log.info("Checkbox was already checked ...");
			TestBase.logExtentReport("Checkbox was already checked ...");
		}
	}
	
	
	public void selectLargeSize(){
		log.info("Selecting large size...");
		TestBase.logExtentReport("Selecting large size...");
		try{
			boolean selected = driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).isSelected();
			if(!selected){
				driver.findElement(By.xpath("//*[@id='layered_id_attribute_group_3']")).click();
				log.info("Checkbox is checked...");
				TestBase.logExtentReport("Checkbox is checked...");
			}
		}
		catch(Exception e){
			log.info("Checkbox was already checked ...");
			TestBase.logExtentReport("Checkbox was already checked ...");
		}
	}
	
	
	public void selectFirstProduct(){
		log.info("Doing mouse hover on first product of page...");
		TestBase.logExtentReport("Doing mouse hover on first product of page...");
	//	WebElement firstProduct = driver.findElement(By.xpath("//*[@id='center_column']/ul/li[1]"));
		new ActionsHelper(driver).actionsHelper(driver).moveToElement(productCount.get(0)).build().perform();
		log.info("Clicking on Add to Cart...");
		TestBase.logExtentReport("Clicking on Add to Cart...");
		addToCart.click();
	}


	public int getTotalProducts(){
		return productCount.size();
	} 
	
	
	public List<WebElement> getAllProductsPrice(){
		return allPriceElements;
	}
	
	public void selectSortByFilter(String dataToSelect){
		DropDownHelper dropdown = new DropDownHelper();
		dropdown.selectUsingVisibleText(sortBy, dataToSelect);
		log.info("Selected option: " +dataToSelect+ " from dropdown ...");
		TestBase.logExtentReport("Selected option: " +dataToSelect+ " from dropdown ......");
	}
	
	public ArrayList<Integer> getPriceMasaagedData(Iterator<WebElement> itr) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		while(itr.hasNext()){
			String str = itr.next().getText();
			if(str.contains("$")){
				String actualPrice = str.substring(1);
			//	log.info(actualPrice);
				double d1 = Double.parseDouble(actualPrice);
				int productPrice = (int) d1;
				array.add(productPrice);
			}
		}
		System.out.println("Data is massaged in ArrayList...");
		return array;
	}

	
	public boolean verifyArrayHasAscendingData(ArrayList<Integer> array){
		for (int i = 0; i < array.size() - 1; i++) {
			// this condition will check all next price should be smaller than previous price
			//  next price can be greater or equal
			if(array.get(i) <= array.get(i+1)){
				log.info("obj.get(i)..." + array.get(i));
				System.out.println("obj.get(i)..." + array.get(i));
				log.info("obj.get(i+1)..." + array.get(i+1));
				System.out.println("obj.get(i+1)..." + array.get(i+1));
			}
			else {
				log.info("price filter is not working...");
				System.out.println("price filter is not working...");
				return false;
			}
		}
		return true;
	}

}
