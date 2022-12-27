package com.qa.opencart.pages;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private ElementUtil  eleUtil;
	
	private By ProductHeader = By.xpath("//div[@id = 'content']//h1");
	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By ProductMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By ProductPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By Qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	private Map<String, String> productInfoMap;
	
	
    public ProductInfoPage(WebDriver driver) {
		
    	eleUtil =  new ElementUtil(driver);
	}
    
    public String getProductHeader() {
    	String ProductHeaderText = eleUtil.doGetText(ProductHeader);
    	System.out.println("Product Header is : "+ ProductHeaderText );
    	return ProductHeaderText;
    }
    
    public int getProductImagesCount() {
    	return eleUtil.waitForElementsToBeVisible(ProductImages, 10).size();
    }
    
    public Map<String, String> getProductInfo() {
    	
    	productInfoMap = new LinkedHashMap<String, String>();
    	productInfoMap.put("Name", getProductHeader());
    	getProductMetaData();
    	getProductPriceData();
    	return productInfoMap;
     }
    
    private void getProductMetaData() {
    	 List<WebElement> metaDataList = eleUtil.getElements(ProductMetaData);
    	 
    	 for(WebElement e : metaDataList) {
    		String text = e.getText();
    		String meta[] = text.split(":");
    		String metaKey = meta[0].trim();
    		String metaValue = meta[1].trim();
    		productInfoMap.put(metaKey, metaValue);
    	 }
    }
    
    private void getProductPriceData() {
   	 List<WebElement> metaPriceList = eleUtil.getElements(ProductPriceData);
   	 
     	String price = metaPriceList.get(0).getText().trim();
   	    String exPrice = metaPriceList.get(1).getText().trim();
   	    productInfoMap.put("price", price);
   	    productInfoMap.put("exTaxPrice", exPrice);



    }
    
    
    
    
    
    
    
    
	    
}
