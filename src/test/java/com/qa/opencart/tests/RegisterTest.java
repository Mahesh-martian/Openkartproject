package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Opk.constant.projConstants;
import com.Opk.utils.ExcelUtil;
import com.qa.opencart.base.BaseTest;


public class RegisterTest extends BaseTest {

	@BeforeClass
	public void regSetup() {
		regPage = loginPage.goToRegisterPage();
	}


	public String randomEmail() {
		Random random = new Random();
		String email = "automation" + random.nextInt(1000) + "@gmail.com";
		return email;
	}

	@DataProvider
	public Object[][] getRegExcelData() {
		Object regData[][] = ExcelUtil.getTestData(projConstants.RegisterExcelSheetName);
		return regData;
	}

	@Test(dataProvider = "getRegExcelData")
	public void userRegTest(String firstName, String lastName, String phone, String password, String subscribe) {

		boolean successFlag = regPage.userRegistration(firstName, lastName, randomEmail(), phone, password, subscribe);
		regPage.goToRegisterPage();
		Assert.assertEquals(successFlag, true);

	}

}
