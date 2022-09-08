package com.Opk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Opk.constant.projConstants;
import com.Opk.utils.ElementUtil;



public class SearchResultPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. by locators:
	By productCount = By.cssSelector("div.product-thumb");
	
	// 2. const...
	public SearchResultPage(WebDriver driver) 
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	//3. page actions:
	
	public int getSearchProductCount() {
		return eleUtil.waitForElementsToBeVisible(productCount, projConstants.MinimumDefaultTimeOut).size();
	}
	
	public ProductInfoPage selectProduct(String SearchProductName) {
		By product = By.linkText(SearchProductName);
		eleUtil.doClick(product);
		return new ProductInfoPage(driver);
	}
	

}
