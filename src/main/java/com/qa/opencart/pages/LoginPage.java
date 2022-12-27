package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	//1. Declare private driver
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	//2. create page class constructor
	public LoginPage(WebDriver driver) {
	     this.driver = driver;
	     eleUtil = new ElementUtil(driver);
    }
	
	//3. create By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By registerLink = By.linkText("Register");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginErrorMesg = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	
	//4. create Page Actions
	@Step("Getting Login Page Title.....")
	public String getLoginPageTitle() {
		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
		
	}
	@Step("Getting Login Page Url.....")
	public boolean getLoginPageUrl() {
		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	@Step("Getting Login Page Forgot Pwd Link.....")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.doIsDisplayed(forgotPwdLink);
	}
	
	@Step("Getting Login Page Register Pwd Link.....")
	public boolean isRegisterLinkExist() {
		return eleUtil.doIsDisplayed(registerLink);
	}
	@Step("Do Login with username: {0} and password: {1}")
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("Login with : " + un +" : " + pwd );
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
		return new AccountsPage(driver);
	}
	
	@Step("Do Login with Wrong username: {0} and password: {1}")
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Try to Login with wrong Credentials : " + un +" : " + pwd );
		eleUtil.doSendKeys(emailId, un);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		
	   String errorMesg = eleUtil.doGetText(loginErrorMesg);
	   System.out.println(errorMesg);
      
	   if(errorMesg.contains(Constants.LOGIN_ERROR_MESSAGE)) {
		   System.out.println("Login Unsuccesful");
		   return false;
	   }
	   return true;
		   
	   
	}
	
	@Step("Navigating to the registration page....")
	public RegistrationPage goToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegistrationPage(driver);
		
	}
	
	
	
	
	
	
	
}
