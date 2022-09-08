package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Opk.constant.projConstants;
import com.qa.opencart.base.BaseTest;


import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 100: Design the login page feature for open cart application")
@Story("US - 101: design loginn page features with login, forgot pwd, title, url")
public class LoginPageTest extends BaseTest {

	
	@Test
	@Description("TC_01: login Page Title Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, projConstants.LoginPageTitle);
	}
	
	@Test
	@Description("TC_02: login Page url Test")
	@Severity(SeverityLevel.NORMAL)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		Assert.assertEquals(actUrl.contains(projConstants.LoginPageUrlFraction), true);
	}
	
	@Test
	@Description("TC_03: verify forgot pwd link exist on the login page")
	@Severity(SeverityLevel.CRITICAL)
	public void forgotPwdLinkExistTest() {
		Assert.assertEquals(loginPage.isForgotPwdLinkExist(), true);
	}
	
	@Test
	@Description("TC_04: verify user is able to login with correct username and password")
	@Severity(SeverityLevel.BLOCKER)
	public void loginTest() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accPage.getAccountsPageTitle(), projConstants.AccountsPageTitle);
	}
	
	
	@Test(enabled = false)
	public void loginPageFooterTest() {
		System.out.println("loginPageFooterTest");
	}

}
