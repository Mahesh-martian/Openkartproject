package com.Opk.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Opk.constant.projConstants;
import com.Opk.utils.ElementUtil;



public class AccountsPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. by locators:

	private By logoutLink = By.linkText("Logout");
	private By searchField = By.name("search");
	private By searchIcon = By.cssSelector("div#search button");
	private By accPageHeaders = By.cssSelector("div#content h2");

	// 2. const...
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions:
	public String getAccountsPageTitle() {
		String title = eleUtil.waitForTitleToBe(projConstants.AccountsPageTitle, projConstants.MaximumDefaultTimeOut);
		System.out.println("Acc page title : " + title);
		return title;
	}

	public String getAccountsPageUrl() {
		String url = eleUtil.waitForUrl(projConstants.MinimumDefaultTimeOut, projConstants.AccountsPageUrlFraction);
		System.out.println("Acc page url : " + url);
		return url;
	}

	public boolean isLogoutLinkExist() {
		return eleUtil.waitForElementVisible(logoutLink, projConstants.MediumDefaultTimeOut).isDisplayed();
	}

	public boolean isSearchFieldExist() {
		return eleUtil.waitForElementVisible(searchField, projConstants.MediumDefaultTimeOut).isDisplayed();
	}

	public List<String> getAccountSectionsHeaderList() {
		return eleUtil.getAllElementsTextList(accPageHeaders, projConstants.MinimumDefaultTimeOut);
	}
	
	
	//common page actions:
	public SearchResultPage doSearch(String productName) {
		System.out.println("searching for : " + productName);
		eleUtil.doSendKeysWithWait(searchField, 10, productName);
		eleUtil.doClick(searchIcon);
		return new SearchResultPage(driver);
	}
	
}
