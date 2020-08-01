package br.com.selenium.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
	
	private static WebDriver driver;
	private DriverFactory() { }
	
	public static WebDriver getDriver() {
	    if(driver == null) {
	    		switch(Properties.BROWSER) {
	    		case FIREFOX: 
	    			driver = new FirefoxDriver(); 
	    			break;
	    		case CHROME: 
	    			driver = new ChromeDriver(); 
	    			break;
	    		}
			driver.manage().window().setSize(new Dimension(1200, 1065));
	    }
	    return driver;  	
	}	

	public static void killDriver() {		
		if(driver != null) {
			driver.quit();
			driver = null;
		}
	}

	
}