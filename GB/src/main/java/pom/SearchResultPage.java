package pom;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import common.Utils;

public class SearchResultPage {
	public static final Logger logger = LogManager.getLogger("SearchResultPage");

	private static final By RESULT_FOUND_MESSAGE = By
			.xpath("//*[@class='results-text pull-left']/*[text()[contains(.,'plans found')]]");
	private static final By RESULT_CARD = By.xpath("//*[@class='col-sm-4 card-full']");
	private static final By RESULT_CARD_NAME = By.xpath("//*[@class='col-sm-4 card-full']//*[@class='name']");
	private static final By RESULT_CARD_POLICY_PRICE = By
			.xpath("//*[@class='col-sm-4 card-full']//*[@class='policy-price']/*[@class='value']");

	private static final By FILTER_SIDE_BAR = By.xpath("//*[@class='filter-detail sidebar-item']");
	private static final By FILTER_FPG_INSURANCE = By.xpath("//*[@data-filter-name='FPG Insurance']");
	private static final By FILTER_PACIFIC_CROSS = By.xpath("//*[@data-filter-name='Pacific Cross']");
	private static final By FILTER_PRUDENTIAL_GUARANTEE = By.xpath("//*[@data-filter-name='Prudential Guarantee']");
	private static final By FILTER_STANDARD_INSURANCE = By.xpath("//*[@data-filter-name='Standard Insurance']");

	private static final By SORT_SIDE_BAR = By.xpath("//*[@class='sort-detail sidebar-item']");
	private static final By SORT_PRICE_LOW_TO_HIGH = By
			.xpath("//*[@class='radio radio-primary']//*[contains(text(),'Price: Low to high ')]");
	private static final By SORT_PRICE_HIGH_TO_LOW = By
			.xpath("//*[@class='radio radio-primary']//*[contains(text(),'Price: High to low ')]");

	private static final By DETAIL_SIDE_BAR = By.xpath("//*[@class='edit-detail sidebar-item']");
	private static final By DETAIL_DESTINATION = By.xpath("//*[@class='select-component']/button");
	private static final By DETAIL_DESTINATION_DROPDOWN = By.xpath("//*[@class='dropdown-menu open']");
	private static final By DETAIL_DATE_FROM = By.xpath("//*[@class='date-from']");
	private static final By DETAIL_DATE_TO = By.xpath("//*[@class='date-to']");

	private static final By CALENDAR_MONTH_YEAR = By.xpath("//*[@class='datepicker-switch']");

	
	public static void waitForSearchResultPageDisplay(WebDriver driver) throws Exception {
		logger.info("Wait for Search Result page displays properly");
		Utils.waitForPageLoaded(driver);
		Utils.waitForElementVisible(driver, driver.findElement(RESULT_CARD), 20);
		Utils.waitForElementVisible(driver, driver.findElement(RESULT_FOUND_MESSAGE), 20);
		Utils.waitForElementVisible(driver, driver.findElement(FILTER_SIDE_BAR), 20);
		Utils.waitForElementVisible(driver, driver.findElement(SORT_SIDE_BAR), 20);
		Utils.waitForElementVisible(driver, driver.findElement(DETAIL_SIDE_BAR), 20);
	}

	public static void verifyNumberOfSearchItem(WebDriver driver, String sCompareOperator, int expected)
			throws Exception {
		logger.info("Compare number of search item on Result page");
		if (sCompareOperator.toLowerCase().equals("equal")) {
			assertEquals(driver.findElements(RESULT_CARD).size(), expected);
		} else if (sCompareOperator.toLowerCase().equals("greater")) {
			assertTrue(driver.findElements(RESULT_CARD).size() > expected);
		} else if (sCompareOperator.toLowerCase().equals("less")) {
			assertTrue(driver.findElements(RESULT_CARD).size() < expected);
		}
	}

	public static int getNumberOfSearchItem(WebDriver driver) throws Exception {
		int sResultCard = driver.findElements(RESULT_CARD).size();
		return sResultCard;
	}

	public static void checkInsurersOption(WebDriver driver, String sOption) throws Exception {
		logger.info("Check or uncheck on checkbox on Result page");
		if (sOption.toLowerCase().equals("fpg insurance")) {
			driver.findElement(FILTER_FPG_INSURANCE).click();

		} else if (sOption.toLowerCase().equals("pacific cross")) {
			driver.findElement(FILTER_PACIFIC_CROSS).click();

		} else if (sOption.toLowerCase().equals("prudential guarantee")) {
			driver.findElement(FILTER_PRUDENTIAL_GUARANTEE).click();

		} else if (sOption.toLowerCase().equals("standard insurance")) {
			driver.findElement(FILTER_STANDARD_INSURANCE).click();
		}
	}

	public static void verifyInsurersFilter(WebDriver driver, String sExpected) throws Exception {
		logger.info("Verify name of Insurers after filter are match with expected");
		List<WebElement> listOfElements = driver.findElements(RESULT_CARD_NAME);
		for (int i = 0; i < listOfElements.size(); i++) {
			assertTrue(listOfElements.get(i).getText().toLowerCase().trim().equals(sExpected.toLowerCase().trim()));
		}
	}

	public static void changeSortOption(WebDriver driver, String sOption) throws Exception {
		logger.info("Change sort option on Result page");
		if (sOption.toLowerCase().equals("price low to high")) {
			driver.findElement(SORT_PRICE_LOW_TO_HIGH).click();
		} else if (sOption.toLowerCase().equals("price high to low")) {
			driver.findElement(SORT_PRICE_HIGH_TO_LOW).click();
		}
	}

	public static void verifySorting(WebDriver driver, String sOption) throws Exception {
		logger.info("Verify sorting is functional");
		List<WebElement> listOfElements = driver.findElements(RESULT_CARD_POLICY_PRICE);

		if (sOption.toLowerCase().equals("price low to high")) {
			for (int i = 1; i < listOfElements.size(); i++) {
				String sCurrent = listOfElements.get(i).getText().replaceAll(",", "");
				String sPrevious = listOfElements.get(i - 1).getText().replaceAll(",", "");
				int iCurrent = Integer.parseInt(sCurrent.trim());
				int iPrevious = Integer.parseInt(sPrevious.trim());
				assertTrue(iPrevious <= iCurrent);
			}
		} else if (sOption.toLowerCase().equals("price high to low")) {
			for (int i = 1; i < listOfElements.size(); i++) {
				String sCurrent = listOfElements.get(i).getText().replaceAll(",", "");
				String sPrevious = listOfElements.get(i - 1).getText().replaceAll(",", "");
				int iCurrent = Integer.parseInt(sCurrent.trim());
				int iPrevious = Integer.parseInt(sPrevious.trim());
				assertTrue(iPrevious >= iCurrent);
			}
		}
	}

	public static void changeDestination(WebDriver driver) throws Exception {
		logger.info("Change Destination option on Result page");
		driver.findElement(DETAIL_DESTINATION).click();
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.DOWN).build().perform();
		actions.sendKeys(Keys.ENTER).build().perform();
	}

	public static void selectTravelStartDate(WebDriver driver, String date, String month, String year) throws Exception{
		logger.info("Change Destination option on Result page");
		driver.findElement(DETAIL_DATE_FROM).click();
		WebElement dateWidget = driver.findElement(DETAIL_DATE_FROM);
		List<WebElement> columns=dateWidget.findElements(By.tagName("td"));
		
		for(WebElement cell:columns)
			{
				// Select 13th Date
				if (cell.getText().equals(date)) {
					cell.findElement(By.linkText(date)).click();
					break;
				}
			}
	}
}