package com.fb.frontend.pages;


import java.awt.AWTException;

import org.openqa.selenium.WebDriver;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FacebookHome extends Utils {
	
	/************************************************************** Constructor ***********************************************************************/

	public FacebookHome(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	
	/********************************************************** Xpath Creation started ***************************************************************/

	String linkLogIn						= "//a[contains(@href,'login')]";             

	/******************************************************** Methods Creation started ****************************************************************/

	/* clickLinkLogIn */
	public void clickLinkLogIn() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to click linkLogIn ..! *******");
		Log.info("******* About to click linkLogIn ..! *******");

		isElementLocatedByXpathPresent(driver, linkLogIn);
		clickByXpath(driver, linkLogIn);
		
		logger.log(LogStatus.PASS, "Sucessfully clicked linkLogIn ");
		Log.info("Sucessfully clicked linkLogIn ");

	}

}