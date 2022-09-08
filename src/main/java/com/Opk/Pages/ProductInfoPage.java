package com.Opk.Pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Opk.constant.projConstants;
import com.Opk.utils.ElementUtil;



public class ProductInfoPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. by locators:
	private By productHeader = By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("img.img-thumbnail");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");

	private Map<String, String> productMap;
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public String getProductHeaderValue() {
		// By mainProduct = By.xpath("//h1[text()='" + mainProductName + "']");

		String productHeaderVal = eleUtil.doElementGetText(productHeader);
		System.out.println("prod header: " + productHeaderVal);
		return productHeaderVal;
	}

	public int getProductImagesCount() {
		int imagesCount = eleUtil.waitForElementsToBeVisible(productImages, projConstants.MinimumDefaultTimeOut).size();
		System.out.println("images count: " + imagesCount);
		return imagesCount;
	}

	public Map<String, String> getProductInfo() {
		productMap = new HashMap<String, String>();
		//productMap = new LinkedHashMap<String, String>();
		//productMap = new TreeMap<String, String>();

		// add product name in map:
		productMap.put("productname", getProductHeaderValue());
		getProductMetaData();
		getProductPriceData();
		System.out.println("=============actual product info===========");
		productMap.forEach((k,v) -> System.out.println(k+ ":" +v));
		
		return productMap;
	}

	private void getProductMetaData() {
		// product meta data:
//		Brand: Apple
//		Product Code: Product 18
//		Reward Points: 800
//		Availability: In Stock
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for (WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String key = meta[0].trim();
			String value = meta[1].trim();
			productMap.put(key, value);
		}
	}

	private void getProductPriceData() {
		// price data:
		// $2,000.00 //0
		// Ex Tax: $2,000.00 //1
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String productPrice = metaPriceList.get(0).getText().trim();
		String productExTaxPrice = metaPriceList.get(1).getText().trim();

		productMap.put("productprice", productPrice);
		productMap.put("extaxprice", productExTaxPrice);
	}
}
