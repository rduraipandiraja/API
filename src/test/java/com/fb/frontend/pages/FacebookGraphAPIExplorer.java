package com.fb.frontend.pages;

import java.awt.AWTException;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FacebookGraphAPIExplorer extends Utils {
	
	/************************************************************** Constructor ***********************************************************************/

	public FacebookGraphAPIExplorer(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	
	/********************************************************** Xpath Creation started ***************************************************************/

	String buttonSubmit						= "//div[text()='Submit']";        
	String textID							= "(//span[contains(.,'}')])[2]/div/span[1]/a";                                                                              
	String buttonGetAccessToken				= "//div[text()='Get Access Token']";                              
	String copyTokenButton					= "//div[text()='Get Access Token']/parent::div/parent::button/parent::div/div/div[2]";       

	/******************************************************** Methods Creation started ****************************************************************/

	/* getfacebookAppURLForSpecifcApp */
	public String getfacebookAppURLForSpecifcApp() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to form facebook app url ..! *******");
		Log.info("******* About to form facebook app url  ..! *******");
		
		String facebookAppURL = Utils.getConfigurationSetupTestData(0, "facebook_url") + Utils.getConfigurationSetupTestData(0, "facebook_tools_url") + Utils.getConfigurationSetupTestData(0, "facebook_id");
		
		logger.log(LogStatus.PASS, "Formed facebook URL is :  " + facebookAppURL);
		Log.info("Formed facebook URL is :  " + facebookAppURL);

		return facebookAppURL;

	}
	
	/* getFacebookAccessToken */
	public String getFacebookAccessToken() throws InterruptedException, AWTException, UnsupportedFlavorException, IOException {
		
		logger.log(LogStatus.INFO, "******* About to get FB Access token ..! *******");
		Log.info("******* About to get FB Access token ..! *******");

		isElementLocatedByXpathPresent(driver, buttonGetAccessToken);
		clickByXpath(driver, buttonGetAccessToken);

		isElementLocatedByXpathPresent(driver, copyTokenButton);
		clickByXpath(driver, copyTokenButton);
		
		String facebookAccessToken = getClipBoardText(driver); 
		
		logger.log(LogStatus.PASS, "FB access token value is :  " + facebookAccessToken);
		Log.info("FB access token value is :  " + facebookAccessToken);

		return facebookAccessToken;

	}

	/* getFacebookID */
	public String getFacebookID() throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get FB id ..! *******");
		Log.info("******* About to get FB id ..! *******");

		isElementLocatedByXpathPresent(driver, buttonSubmit);
		clickByXpath(driver, buttonSubmit);

		isElementLocatedByXpathPresent(driver, textID);
		String facebookID = getTextByXpath(driver, textID);
		
		logger.log(LogStatus.PASS, "FB id is :  " + facebookID);
		Log.info("FB id value is :  " + facebookID);

		return facebookID;

	}

}