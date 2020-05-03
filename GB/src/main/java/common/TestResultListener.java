package common;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestResultListener extends TestListenerAdapter implements ISuiteListener{

	public static final Logger logger = LogManager.getLogger("TestResultListener");
	public static Boolean isFailed = false;
	public static String xmlSuiteName = "";
	public static String sanitySuiteName = "";
	public static String testcaseName = "";
	public static String testcaseID = "";
	
	public static String getTestName(ITestResult result) {
		String testName = result.getName().toString().trim();
		return testName;
	}
	
	@Override
	public void onFinish(ISuite suite) {
		logger.info("***FINISHED SUITE " + suite.getName().toUpperCase());		
	}

	@Override
	public void onStart(ISuite suite) {
		logger.info("***STARTING SUITE " + suite.getName().toUpperCase());
		xmlSuiteName = "ExecutionLogs_" + suite.getXmlSuite().getName();
	}
	
	@Override
	public void onTestStart(ITestResult tr) {
		logger.info("***STARTING " + getTestName(tr).toUpperCase());
		isFailed = false;
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.info("***PASSED " + getTestName(tr).toUpperCase());
	}

	@Override
	public void onTestFailure(ITestResult failingTest) {
		logger.error("***FAILED " + getTestName(failingTest).toUpperCase());
		isFailed = true;
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.info("***SKIPPED " + getTestName(tr).toUpperCase());
	}
	
}
