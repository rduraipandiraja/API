package com.fb.frontend.pages;


import java.awt.AWTException;

import org.openqa.selenium.WebDriver;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FacebookLogin extends Utils {
	
	/************************************************************** Constructor ***********************************************************************/

	public FacebookLogin(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	
	/********************************************************** Xpath Creation started ***************************************************************/

	String textAreaUserName									= "//input[@id='email']";        
	String textAreaPwd										= "//input[@id='pass']";        
	String buttonLogIn										= "//button[@id='loginbutton']";     
	String facebookUserName									= ConfigurationSetup.FACEBOOKUSERNAME;
	public static final String facebookPwd					= ConfigurationSetup.FACEBOOKPASSWORD;

	/******************************************************** Methods Creation started ****************************************************************/

	/* enterUserName */
	public void enterUserName() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to enter UserName ..! *******");
		Log.info("******* About to enter UserName ..! *******");
		
		isElementLocatedByXpathPresent(driver, textAreaUserName);
		enterTextByXpath(driver, textAreaUserName, facebookUserName);
		
		logger.log(LogStatus.PASS, "Sucessfully entered UserName :  " + facebookUserName);
		Log.info("Sucessfully entered UserName :  " + facebookUserName);

	}

	/* enterPassword */
	public void enterPassword() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to enter Password ..! *******");
		Log.info("******* About to enter Password ..! *******");

		isElementLocatedByXpathPresent(driver, textAreaPwd);
		enterTextByXpath(driver, textAreaPwd, facebookPwd);
		
		logger.log(LogStatus.PASS, "Sucessfully entered Password :  " + facebookPwd);
		Log.info("Sucessfully entered Password :  " + facebookPwd);

	}

	/* clickSubmit */
	public void clickSubmit() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click submit ..! *******");
		Log.info("******* About to click submit ..! *******");

		isElementLocatedByXpathPresent(driver, buttonLogIn);
		clickByXpath(driver, buttonLogIn);
		
		logger.log(LogStatus.PASS, "Sucessfully clicked submit ");
		Log.info("Sucessfully clicked submit ");

	}

}