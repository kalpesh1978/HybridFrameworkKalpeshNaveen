package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Errors;

public class AccountsPageTest extends BaseTest {
	
	

	
	@BeforeClass
	public void accPageSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void accPageTitleTest() {
		String actTitle = accountsPage.getAccountPageTitle();
		System.out.println("account page title is :"+ actTitle);
		Assert.assertEquals(actTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test(priority=2, enabled = false)
	public void accPageHeaderTest() {
		String header = accountsPage.getAccountPageHeader();
		System.out.println("Acc page Header is : "+ header);
		Assert.assertEquals(header, "naveen animation",Errors.ACC_PAGE_HEADER_NOT_FOUND_ERROR_MESSG);
	}
	
	@Test(priority=2)
	public void isLogOutExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test(priority=3)
	public void accPageSectionsTest() {
		List<String> actAccSecList = accountsPage.getAccountSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
	}
	
	@DataProvider
	public Object[][] productData() {
		return new Object[][] {
			{"MacBook"},
			{"Apple"},
			{"Samsung"}
		};
	}
	
	
	@Test(priority=4, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accountsPage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductsListCount()>0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
		};
	}
	
	@Test(priority=6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainproductName) {
		searchResultsPage = accountsPage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainproductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainproductName);
		
     }
	
	
	
	
}
