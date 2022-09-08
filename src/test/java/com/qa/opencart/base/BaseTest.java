package com.qa.opencart.base;


import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.Opk.Pages.AccountsPage;
import com.Opk.Pages.LoginPage;
import com.Opk.Pages.ProductInfoPage;
import com.Opk.Pages.RegisterPage;
import com.Opk.Pages.SearchResultPage;
import com.Opk.fact.DriverFactory;

public class BaseTest {
	public WebDriver driver;
	public Properties prop;

	public DriverFactory df;

	public LoginPage loginPage;
	public AccountsPage accPage;
	public SearchResultPage searchResPage;
	public ProductInfoPage productInfoPage;
	public RegisterPage regPage;
	public SoftAssert softAssert;
	public String methodName;
	

	@Parameters({ "browser" , "browserversion"})
	@BeforeTest
	public void setup(String browserName, String browserVersion, ITestContext testContext) {
		df = new DriverFactory();

		prop = df.initializePropfile();

		if (browserName != null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testContext.getName());
		}

		driver = df.initializeDriver(prop);
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
