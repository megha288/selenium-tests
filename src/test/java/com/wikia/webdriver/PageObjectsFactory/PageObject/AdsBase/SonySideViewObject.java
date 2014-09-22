package com.wikia.webdriver.PageObjectsFactory.PageObject.AdsBase;

import com.wikia.webdriver.PageObjectsFactory.PageObject.BasePageObject;
import org.openqa.selenium.*;

/**
 * @author Piotr 'Rychu' Gabryjeluk
 * @ownership AdEngineering
 */
public class SonySideViewObject extends BasePageObject {
	final String sonySideViewUrl = "http://wikiamonetization.wikia.com/info.tvsideview.sony.net/sony-sideview.html";

	public SonySideViewObject(WebDriver driver) {
		super(driver);
		getUrl(sonySideViewUrl);
	}

	public AdsBaseObject goToDestinationPage(String page) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("location.href = arguments[0]", page);
		return new AdsBaseObject(driver);
	}
}
