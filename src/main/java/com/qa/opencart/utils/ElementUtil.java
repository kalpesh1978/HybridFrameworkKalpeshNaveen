package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {
	
	private WebDriver driver;
	private JavaScriptUtil jsUtil;
	
	public ElementUtil(WebDriver  driver) {
		this.driver = driver;
		jsUtil = new JavaScriptUtil(driver);
		
	}
	
	public  By getBy(String locatorType, String locatorValue) {
		By locator = null;
		switch(locatorType.toLowerCase()) {
		case "id":
		  locator = By.id(locatorValue);
			break;
		case "name":
			  locator = By.name(locatorValue);
				break;
		case "classname":
			  locator = By.className(locatorValue);
				break;
		case "xpath":
			  locator = By.xpath(locatorValue);
				break;
		case "cssselector":
			  locator = By.cssSelector(locatorValue);
				break;
		case "linktext":
			  locator = By.linkText(locatorValue);
				break;
				
				default:
				System.out.println("please pass the  right values...");
				break;
		}
		return locator;
		
	}
	
	
	
	public  WebElement getElement(By  locator) {
		WebElement element = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);
		}
		return element;
		
	}
	
	public  WebElement getElement(By  locator, int timeOut) {
		return dopresenceOfElementLocated(locator, timeOut);
		
	}
	 
	public  List<WebElement> getElements(By locator) {
     	return driver.findElements(locator);
     }	

	
	 public  WebElement getElement(String locatorType,String locatorValue) {
		return driver.findElement(getBy(locatorType, locatorValue));
		
	}
	 
	 public void doClear(By locator) {
		 getElement(locator).clear();
	 }
	
	
	public  void doSendKeys(By locator, String value) {
		doClear(locator);
		getElement(locator).sendKeys(value);
		
	}
	
	public void doSendKeys(String locatorType,String locatorValue, String value) {
		getElement(locatorType, locatorValue).sendKeys(value);
		
	}
	
	public void doSendKeys(By locator, String value, int timeOut) {
		dopresenceOfElementLocated(locator, timeOut).sendKeys(value);
	}
	
	public  void doClick(By locator) {
		getElement(locator).click();
	
	}
	
	public void doClick(String locatorType, String locatorValue) {
		getElement(locatorType, locatorValue).click();
		
	}
	
	public  void doClick(By locator, int timeOut) {
		dopresenceOfElementLocated(locator, timeOut).click();
	
	}
	
	public String doGetText(By locator) {
		return getElement(locator).getText();
	}
	
	public String getAttributeValue(By locator,String attrName) {
		String attrValue =  getElement(locator).getAttribute(attrName);
		System.out.println(attrValue);
		return attrValue;
	}
	
	public boolean doIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}
	

	public boolean isElementExist(By locator) {
		int elementCount = getElementsCount(locator);
		System.out.println("total elements found :" + elementCount);
		if (elementCount >=1) {
			System.out.println("element is found..." + locator);
			return true;
		}else {
			System.out.println("element is not found..." + locator);
			return false;	
		}
	}
	
	
	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleText = e.getText();
			if(!eleText.isEmpty()) {
				eleTextList.add(eleText);
			}
		}
		return eleTextList;
	}
	
	public int getElementsCount(By locator) {
		return getElements(locator).size();
	}
	
	public void printElementValues( List<String> eleList) {
		for (String e : eleList) {
			System.out.println(e);
		}
	}
	
	public List<String> getAttributeList(By locator, String attributeName) {
		 List<WebElement> eleList =	getElements(locator);
		 List<String> attrList = new ArrayList<String>();
		 for (WebElement e : eleList) {
			 String attrValue = e.getAttribute(attributeName);
			 attrList.add(attrValue);
		 }
		 return attrList;
	}
	
	
	/*************************Drop Down Utils****************/
	
	public  void doDropDownSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}
	
	public void doDropDownSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}
	
	public void doDropDownSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}
	
	public void doSelectDropDownValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList ) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void selectDropDownValueWithoutSelect(By locator, String value) {
		 List<WebElement> optionsList = getElements(locator);
		   System.out.println(optionsList.size());
		    for (WebElement e : optionsList) {
			   String text = e.getText();
			   if(text.equals(value)) {
				   e.click();
				   break;
			   }
		   }
	}
	
	/******************linksUtils*********************/
	public List<String> getLinksTextList(By locator) {
		List<WebElement> linksList = getElements(locator);
		List<String> linksTextList = new ArrayList<String>();
	    System.out.println(linksList.size());
	    for (WebElement e : linksList) {
			String text = e.getText().trim();
			linksTextList.add(text);
			}
	    return linksTextList;
	}
	
	public void clickOnElementFromSection(By locator, String linkText) {
		 List<WebElement> langList = getElements(locator);
		    System.out.println(langList.size());
		    
			for (WebElement e : langList) {
				String text = e.getText().trim();
				System.out.println(text);
				if (text.equals(linkText)) {
					e.click();
					break;
				}
			}
	}
	//-----------WebTable util-------------//
	public void  printTable(By rowlocator, By colLocator,String beforeXpath, String afterXpath) {
	    int rowCount = getElements(rowlocator).size();
	    int colCount = getElements(colLocator).size();
	    for(int row = 2; row <=rowCount; row++ ) {
	    	for(int col = 1; col<=colCount; col++) {
	    		String xpath = beforeXpath +row+afterXpath+col+"]";
	    		String text = doGetText(By.xpath(xpath));
	    		System.out.println(text+"   |  ");
	    	}
	    	System.out.println();
	    }
	    

	}
	
	
	/*************************** Actions Util ****************/
	
	public void doMoveToElement(By locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(locator)).build().perform();
	}
	
	public void doClickOnChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {
		doMoveToElement(parentMenuLocator);
		Thread.sleep(3000);
		doClick(childMenuLocator);
	}
	
	public void doActionsSendKeys(By locator, String value) {
		Actions act = new Actions(driver);
		act.sendKeys(getElement(locator), value).build().perform();
	}
	
	public void doActionsSendKeysOnActiveElement(By locator, String value) {
		Actions act = new Actions(driver);
		act.click(getElement(locator)).sendKeys(value).build().perform();
	}
	
	public void doActionClick(By locator) {
		Actions act = new Actions(driver);
	    act.click(getElement(locator)).build().perform();
	}
	
	public void doActionMoveToElementClick(By locator) {
		Actions act = new Actions(driver);
	    act.moveToElement(getElement(locator)).click().build().perform();
	}
	
	
	/******************* Wait Utils *********************/
	
	
	
	public WebElement dopresenceOfElementLocated(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	} 
	
	public  WebElement dopresenceOfElementLocated(By locator, int timeOut, long intervalTime ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
	   return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	} 

	public WebElement waitForElementToBeVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	} 
	
	public WebElement waitForElementToBeVisible(By locator, int timeOut, long intervalTime ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
	   return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	} 
	
	public WebElement waitForElementToBeVisibleWithWebElement(By locator, int timeOut, long intervalTime ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
	   return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));

	} 
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
	   return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	} 
	
	
	public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut, long intervalTime ) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut),Duration.ofMillis(intervalTime));
	   return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	} 
	
	public List<String> getElementsTextListWithWait(By locator, int timeOut) {
		List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
		List<String> eleTextList = new ArrayList<String>();
		for(WebElement e : eleList) {
			String text = e.getText();
			eleTextList.add(text);
		}
		return eleTextList;
	}
	
	
	/******************** Wait Utils for Non-WebElements **********************/
	
	public boolean waitForURLToContain(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlFraction));
	}
	
	public boolean waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlToBe(url));
	}
	
	public String doGetTitleWithFraction(String titleFraction, int timeOut) {
		if(waitForTitleContains(titleFraction, timeOut)){
			return driver.getTitle();
		}
		return null;
	}
	
	public String doGetTitle(String title, int timeOut) {
		if(waitForTitleContains(title, timeOut)){
			return driver.getTitle();
		}
		return null;
	}
	
	public boolean waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));

	}
	
	public boolean waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));

	}
	
	 public Alert waitForAlert(int timeOut) {
  	   WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
         return wait.until(ExpectedConditions.alertIsPresent());
     }
     
     public String getAlertText(int timeOut) {
  	   return waitForAlert(timeOut).getText();
     }
     
     public void doAlertDismiss(int timeOut) {
  	 waitForAlert(timeOut).dismiss();
     }
     
     public void doAlertAccept(int timeOut) {
    	 waitForAlert(timeOut).accept();
       }
     
     public void enterAlertText(String text, int timeOut) {
      	 waitForAlert(timeOut).sendKeys(text);
         }
     
     
	
	

}


