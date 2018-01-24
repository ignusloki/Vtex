package com.felipelima;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Util {	

	public static WebDriver startBrowser(WebDriver driver, int type) { 
		
		switch (type) {	
		case 0: 			
			System.setProperty("webdriver.gecko.driver", "E:\\DEV\\External\\Selenium\\Driver\\geckodriver.exe");
			FirefoxProfile firefoxProfile = new FirefoxProfile(); 
			driver = new FirefoxDriver();	
			return driver; 

		case 1: 
			System.setProperty("webdriver.ie.driver", "E:\\DEV\\External\\Selenium\\Driver\\IEDriverServer.exe"); 
			DesiredCapabilities capab = DesiredCapabilities.internetExplorer(); 
			capab.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true); 
			driver = new InternetExplorerDriver(capab); 
			return driver; 

		case 2: 
			File chromeFile = new File("E:\\DEV\\External\\Selenium\\Driver\\chromedriver.exe"); 
			System.setProperty("webdriver.chrome.driver", chromeFile.getAbsolutePath()); 
			driver = new ChromeDriver(); 
			return driver; 

		default:
			return driver = null; 
		}	
	}
}
