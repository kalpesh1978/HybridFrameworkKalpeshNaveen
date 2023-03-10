package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	
	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	
	/**
	 * This method is used to initialise the WebDriver
	 * @param browserName
	 * @return This will return the driver
	 */
	
	
	 public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		 System.out.println("Browser name is :" + browserName);
		 
		 highlight = prop.getProperty("highlight");
		 optionsManager = new OptionsManager(prop);
		
		if(browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}
		
		else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

		}
		else if (browserName.equals("safari")) {
			
			tlDriver.set(new SafariDriver());
		}
		else {
			System.out.println("Please pass the right browser :" + browserName);
			}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
	    openUrl(prop.getProperty("url"));
		
		return getDriver();
		
	}
	 
	 /**
	  * getDriver() it will return a ThreadLocal copy of the WebDriver
	 * @return 
	  */
	 
	 public static synchronized WebDriver getDriver() {
		 return tlDriver.get();
	 }
	 
	 
	 /**
	  * This method is used to  initialise the properties 
	  * @return
	  * This will return properties prop reference
	  */ 
	 
	 public Properties init_prop() {
		 prop = new Properties();
		 FileInputStream ip = null;
		 
		 String envName = System.getProperty("env");//env could be qa/dev/stage/uat
		 
		 if(envName == null) {
			 System.out.println("Running on PROD env :");
			 try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
				
		 }else {
			 System.out.println("Running on : "+ envName);
			 try {
			 switch (envName.toLowerCase()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;

			default:
				System.out.println("Please pass the right Environment......");
				break;
			}
		 }
		 catch(FileNotFoundException e) {
			 e.printStackTrace();
		 }
	 }
		 
		 try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		 return prop;
	 }
	 
	 /**
	  * For Taking ScreenShot
	 * @return 
	  */
	 
	 public String getScreenshot() {
		File src =((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	 }
	
	 /**
	 * Launch Url Method
	 * @param url
	 */
	 
	 public void openUrl(String url) {
		try {
		 if(url==null) {
			 throw new Exception("Url is null");
		 }
		}catch(Exception e) {
			
		}
		
		 getDriver().get(url);
	 }
	 
	 public void openUrl(URL url) {
			try {
			 if(url==null) {
				 throw new Exception("Url is null");
			 }
			}catch(Exception e) {
				
			}
			
			 getDriver().navigate().to(url);
		 }
	 

	 public void openUrl(String baseUrl, String path) {
		try {
		 if(baseUrl==null) {
			 throw new Exception("baseUrl is null");
		 }
		}catch(Exception e) {
			
		}
		//http://amazon.com/accpage/users.html
		 getDriver().get(baseUrl+"/"+path);
	 }
	
	 public void openUrl(String baseUrl, String path, String queryParam) {
			try {
			 if(baseUrl==null) {
				 throw new Exception("baseUrl is null");
			 }
			}catch(Exception e) {
				
			}
			//http://amazon.com/accpage/users.html?route=account/login
			 getDriver().get(baseUrl+"/"+path+"?"+queryParam);
		 }
	
	
}
