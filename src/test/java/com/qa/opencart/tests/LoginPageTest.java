package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic 100: Design Opencart App -- Login Page ")
@Story("User 101: Opencart Loginpage Design with multiple features")
@Listeners(TestAllureListener.class)

public class LoginPageTest extends BaseTest {

	
	@Description("This is loginPage Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Page title is :" + actTitle);
	    Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE );
	}
	
	
	@Description("This is loginPage Url Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
	   	
		Assert.assertTrue(loginPage.getLoginPageUrl());
	}
	
	
	@Test(priority = 3)
	public void forgotPwdLinkTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	
	@Description("This is loginPage registerLink Test")
	@Test(priority = 4, enabled = false)
	public void registerLinkTest() {
		Assert.assertTrue(loginPage.isRegisterLinkExist());
	}
	
	
	@Description("This is loginPage Correct credentials Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountsPage.getAccountPageTitle(), Constants.ACCOUNTS_PAGE_TITLE); 
	}
	
	
	
	
	
	
	
}
