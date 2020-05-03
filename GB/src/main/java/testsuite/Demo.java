package testsuite;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import common.Constants;
import common.Drivers;
import common.TestResultListener;
import common.Utils;
import pom.SearchPage;
import pom.SearchResultPage;

@Listeners(common.TestResultListener.class)				

public class Demo {
	static WebDriver driver;
	public static final Logger logger = LogManager.getLogger("DemoSuite");
	
	@AfterMethod
	public void tearDownMethod() throws Exception {
		if (TestResultListener.isFailed) {
			logger.info("Tearing down for test case");
			driver.quit();
			driver = Drivers.getBrowserDriver(Constants.BROWSER);
			Utils.launchWebPage(driver, Constants.MAIN_URL);
			logger.info("Tear down for test case completed.\n");
		}
	}
	
	@AfterClass
    public void tearDownSuite() throws Exception {
		logger.info("***Tearing down for test suite");		
		driver.close();
		logger.info("***Tear down test suite completed.\n");
    }
	
	@Test
	public void testcase1() throws Exception {
		logger.info("***Starting testcase1");
		driver = Drivers.getBrowserDriver(Constants.BROWSER);
		
		logger.info("Step 1: Launch web address");
		Utils.launchWebPage(driver, Constants.MAIN_URL);
		
		logger.info("Step 2: Select Insurance tab ");
		SearchPage.selectTab(driver, "insurance");
		
		logger.info("Step 3: Select Travel tab");
		SearchPage.selectTab(driver, "travel");		
		
		logger.info("Step 4: Click on Show My Result button");
		SearchPage.clickSearch(driver);
		
		
		logger.info("Step 5: Wait for Search Result Page to load");
		Utils.waitForPageLoaded(driver);
		SearchResultPage.waitForSearchResultPageDisplay(driver);
		
		logger.info("Step 6: Verify more than 3 cards are displayed");
		SearchResultPage.verifyNumberOfSearchItem(driver, "greater", 3);
		SearchResultPage.getNumberOfSearchItem(driver);
		
		logger.info("Step 7: Check Standard Insurance checkbox");
		SearchResultPage.checkInsurersOption(driver, "Standard Insurance");
		
		logger.info("Step 8: Verify filter result");
		Utils.waitForPageLoaded(driver);
		SearchResultPage.verifyInsurersFilter(driver, "Standard Insurance");
		
		logger.info("Step 9: Sorting Price Low to High");
		SearchResultPage.changeSortOption(driver, "price low to high");
		
		logger.info("Step 10: Verify sorting Price Low to High");
		Utils.waitForPageLoaded(driver);
		SearchResultPage.verifySorting(driver, "price low to high");
		
		logger.info("Step 11: Change Destination to Philippines");
		SearchResultPage.changeDestination(driver);
		
		logger.info("Step 12: Verify more than 3 cards are displayed");
		SearchResultPage.verifyNumberOfSearchItem(driver, "greater", 3);
		
		logger.info("Step 13: Change Travel Start Date");
		SearchResultPage.selectTravelStartDate(driver, "10", "May" , "2020");
		
	}

}
