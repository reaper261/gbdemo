package common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Utils {
	public static final Logger logger = LogManager.getLogger("Utils");
	
	public static void launchWebPage(WebDriver driver, String sURL) throws  Exception{
		logger.info("Launch web url: " + sURL);
		driver.get(sURL);
		waitForPageLoaded(driver);
	}
	
	public static WebElement waitForElementVisible(WebDriver driver, WebElement webElement, int waitTime) throws  Exception{
		logger.info("Wait for element visible");
		WebDriverWait webWait = new WebDriverWait(driver, waitTime);
		return webWait.until(ExpectedConditions.visibilityOf(webElement));
	}
	
	public static WebElement waitForElementClickable(WebDriver driver, WebElement webElement, int waitTime) throws  Exception{
		logger.info("Wait for element clickable");
		WebDriverWait webWait = new WebDriverWait(driver, waitTime);
		return webWait.until(ExpectedConditions.elementToBeClickable(webElement));
	}
	
	public static void waitVisibilityOfElementLocated(WebDriver driver, By expectedElement, int timeOut) throws  Exception{
		logger.info(String.format("Wait visibility of element %s within %s seconds.", expectedElement, timeOut));
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(expectedElement));
	}
	
	public static void scrollIntoView(WebDriver driver, WebElement element) throws Exception{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}
	
	public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }
}
