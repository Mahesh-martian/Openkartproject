package com.Opk.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Opk.constant.projConstants;
import com.Opk.utils.ElementUtil;


public class RegisterPage 
{
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");


	private By subscribeYes = By.xpath("(//input[@class = 'form-check-input' and @name = 'newsletter'])[1]");
	private By subscribeNo = By.xpath("(//input[@class = 'form-check-input' and @name = 'newsletter'])[2]");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//button[@type='submit' and text()='Continue']");
	

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) 
	{
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
// doubt
	public boolean userRegistration(String firstName, String lastName, String email, String phone, String password,
			String subscribe) 
	{

		eleUtil.doSendKeysWithWait(this.firstName, projConstants.MediumDefaultTimeOut, firstName);
		
		eleUtil.doSendKeys(this.lastName, lastName);
		eleUtil.doSendKeys(this.email, email);
//		eleUtil.doSendKeys(this.telephone, phone);
		eleUtil.doSendKeys(this.password, password);

		if (subscribe.equalsIgnoreCase("yes")) {
			eleUtil.doClick(this.subscribeYes);
		} else {
			eleUtil.doClick(this.subscribeNo);
		}

		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueButton);
		
		
		LoginPage log = new LoginPage(driver) ;
		log.doLogin(email, password);
		System.out.println("user reg success mesggg================= " );
		String res = eleUtil.waitForTitleContains("Account", 5);
		if ( res == "My Account")
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public void goToRegisterPage() {
		eleUtil.doClick(this.logoutLink);
		eleUtil.doClick(this.registerLink);
	}


}
