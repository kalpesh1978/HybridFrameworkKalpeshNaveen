package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			
			{"test@gmail.com", "test123"},
			{"test@gmail.com", "Selenium@12345"},
			{"  ", "Selenium@12345"},
			{"@#@#@gmail.com", "test123"},
			{"",""}
			
		};
	}
	@Test(dataProvider= "loginWrongTestData")
	public void loginNegativeTest(String username, String password) {
		
       Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));     
	}
	
	
	
	
}
