package com.uiAutomation.helper.browserConfiguration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.ie.ElementScrollBehavior;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.uiAutomation.helper.resource.ResourceHelper;

/**
 * 
 * @author vasudevp
 *
 */
public class IExplorerBrowser {

	public InternetExplorerOptions getIExplorerCapabilities() {

		DesiredCapabilities iExplorer = DesiredCapabilities.internetExplorer();
		iExplorer.setCapability(InternetExplorerDriver.ELEMENT_SCROLL_BEHAVIOR,
				ElementScrollBehavior.BOTTOM);
		iExplorer.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,
				true);
		iExplorer
				.setCapability(
						InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
						true);
		iExplorer.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,
				true);
		iExplorer.setJavascriptEnabled(true);

		InternetExplorerOptions option = new InternetExplorerOptions(iExplorer);
		return option;
	}

	public WebDriver getInternetExplorerDriver(InternetExplorerOptions cap) {
		if (System.getProperty("os.name").contains("Mac")) {
			System.setProperty(
					"webdriver.ie.driver",
					ResourceHelper
							.getResourcePath("/src/main/resources/drivers/IEDriverServer"));
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("Window")) {
			System.setProperty(
					"webdriver.ie.driver",
					ResourceHelper
							.getResourcePath("/src/main/resources/drivers/IEDriverServer.exe"));
			return new InternetExplorerDriver(cap);
		} else if (System.getProperty("os.name").contains("Linux")) {
			System.setProperty("webdriver.ie.driver",
					ResourceHelper.getResourcePath("usr/bin/IEDriverServer"));
			return new InternetExplorerDriver(cap);
		} else
			return null;
	}

}
