package pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.Utils;

public class SearchPage {
	public static final Logger logger = LogManager.getLogger("SearchPage");
	private static final int timeOut = 15;

	private static final By INSURANCE_TAB = By.xpath("//*[@role='tablist']/*[@data-gb-name='Insurance']");
	private static final By CREDITCARDS_TAB = By.xpath("//*[@role='tablist']/*[@data-gb-name='CreditCards']");
	private static final By LOANS_TAB = By.xpath("//*[@role='tablist']/*[@data-gb-name='Loans']");
	private static final By CAR_TAB = By.xpath("//*[@role='tablist']//*[@data-gb-name='Car']");
	private static final By TRAVEL_TAB = By.xpath("//*[@role='tablist']//*[@data-gb-name='Travel']");
	private static final By SEARCH_BTN = By.xpath("//button[@name='product-form-submit']");


	public static void selectTab(WebDriver driver, String tabName) throws Exception {
		String strTabName = tabName.toLowerCase();
		if (strTabName.equals("insurance")) {

			Utils.waitForElementClickable(driver, driver.findElement(INSURANCE_TAB), timeOut);
			logger.info("Click Insurance tab");
			driver.findElement(INSURANCE_TAB).click();

		} else if (strTabName.equals("creditcards")) {

			Utils.waitForElementClickable(driver, driver.findElement(CREDITCARDS_TAB), timeOut);
			logger.info("Click Credit Cards button");
			driver.findElement(CREDITCARDS_TAB).click();

		} else if (strTabName.equals("loans")) {

			Utils.waitForElementClickable(driver, driver.findElement(LOANS_TAB), timeOut);
			logger.info("Click Loans tab");
			driver.findElement(LOANS_TAB).click();

		} else if (strTabName.equals("car")) {

			Utils.waitForElementClickable(driver, driver.findElement(CAR_TAB), timeOut);
			logger.info("Click Car tab");
			driver.findElement(CAR_TAB).click();

		} else if (strTabName.equals("travel")) {

			Utils.waitForElementClickable(driver, driver.findElement(TRAVEL_TAB), timeOut);
			logger.info("Click Travel tab");
			driver.findElement(TRAVEL_TAB).click();
		}
	}

	public static void clickSearch(WebDriver driver) throws Exception {
		logger.info("Click Search button");
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated(SEARCH_BTN));
		driver.findElement(SEARCH_BTN).click();
	}

}
