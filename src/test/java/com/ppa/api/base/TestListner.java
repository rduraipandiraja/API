package com.ppa.api.base;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.Reporter;

public class TestListner extends Base implements ITestListener, IInvokedMethodListener {

	/************************************************ Methods Creation started *******************************************************/
	
	/* onFinish */
	public void onFinish(ITestContext arg0) {
		Reporter.log("Completed executing test " + arg0.getName(), true);
	}

	/* onStart */
	public void onStart(ITestContext arg0) {
		Reporter.log("About to begin executing test " + arg0.getName(), true);
		Reporter.log("About to begin executing test " + System.getProperty("user.dir"), true);
	}

	/* onTestFailedButWithinSuccessPercentage */
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	/* onTestFailure */
	public void onTestFailure(ITestResult arg0) {
		printTestResults(arg0);
	}

	/* printTestResults */
	private void printTestResults(ITestResult result) {
		// Reporter.log("TestName = " + result.getTestName(), true);
		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);
		if (result.getParameters().length != 0) {
			String params = null;
			for (Object parameter : result.getParameters()) {
				params += parameter.toString() + ",";
			}
			Reporter.log("Test Method had the following parameters : " + params, true);
		}
		String status = null;
		switch (result.getStatus()) {
		case ITestResult.SUCCESS:
			status = "Pass";
			break;
		case ITestResult.FAILURE:
			status = "Failed";
			break;
		case ITestResult.SKIP:
			status = "Skipped";
			break;
		default:
			status = "Skipped";
		}
		Reporter.log("Test Status: " + status, true);
	}

	/* onTestSkipped */
	public void onTestSkipped(ITestResult arg0) {
		printTestResults(arg0);
	}

	/* onTestStart */
	public void onTestStart(ITestResult arg0) {
	}

	/* onTestSuccess */
	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);
	}

	/* afterInvocation */
	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "Completed executing " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);

	}

	/* beforeInvocation */
	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
		String textMsg = "About to begin executing " + returnMethodName(arg0.getTestMethod());
		Reporter.log(textMsg, true);
	}

	/* returnMethodName */
	private String returnMethodName(ITestNGMethod method) {
		return method.getRealClass().getSimpleName() + "." + method.getMethodName();
	}

}