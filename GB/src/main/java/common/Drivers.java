package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
public class Drivers {
	public static final Logger logger = LogManager.getLogger("Drivers");
	
	static WebDriver webdriver = null;
	
	public static WebDriver getFirefoxWebDriver() throws Exception {
		try {
			logger.info("Start Firefox browser");
			System.setProperty("webdriver.gecko.driver","libraries\\drivers\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			webdriver = new FirefoxDriver(capabilities);
			logger.info("Maximine browser");
			webdriver.manage().window().maximize();
			
		} catch (Exception e) {
			logger.error("Failed to get Firefox driver due to: " + e);
		}
		return webdriver;
	}
	
	public static WebDriver getChromeWebDriver() throws Exception {
		try {
			logger.info("Start Chrome browser");
			System.setProperty("webdriver.chrome.driver","libraries\\drivers\\chromedriver.exe");
			webdriver = new ChromeDriver();
			logger.info("Maximine browser");
			webdriver.manage().window().maximize();
			
		} catch (Exception e) {
			logger.error("Failed to get Chrome driver due to: " + e);
		}
		return webdriver;
	}
	
	public static WebDriver getBrowserDriver(String expectBrowser) throws Exception {
		if(expectBrowser.equalsIgnoreCase("firefox"))
			return getFirefoxWebDriver();
		else if(expectBrowser.equalsIgnoreCase("chrome"))
			return getChromeWebDriver();
		else 
			return getFirefoxWebDriver();
	}
	
}
