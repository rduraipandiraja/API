package com.ppa.api.endpoints.helper;

import java.awt.AWTException;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class HelperAccessToken extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public HelperAccessToken(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	

	/****************************************************** Variable Creation started ****************************************************************/

	// Assigning header Values
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String APPVERSIONKEY;
	public static final String APPVERSIONVALUE;
	public static final String ENDPOINT;
	
	public static final String USERNAME;
	public static final String PASSWORD;
	public static final String APPUSERNAME;
	public static final String APPPASSWORD;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token							= "data.attributes.token";        
	String about							= "errors.links.about";                                                                              
	String status							= "errors.status";                              
	String title							= "errors.title";                                         
	String detail							= "errors.detail";              

	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		ACCEPTKEY		= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE		= Utils.getRestApiTestData(0, "accept_value");
		APPVERSIONKEY	= Utils.getRestApiTestData(0, "app_version_key");
		APPVERSIONVALUE	= Utils.getRestApiTestData(0, "app_Version_value");
		ENDPOINT		= Utils.getRestApiTestData(2, "helper_access_token");
		
		USERNAME		= ConfigurationSetup.DESKTOPUSERNAME;
		PASSWORD		= ConfigurationSetup.DESKTOPPASSWORD;
		APPUSERNAME		= ConfigurationSetup.APPUSERNAME;
		APPPASSWORD		= ConfigurationSetup.DESKTOPPASSWORD;
		
	}
	
	/* getAccessTokenHelperEndPoint */
	public Response getAccessTokenHelperEndPoint(String baseURL, String authType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get Helper Access token authentication ..! *******");
		Log.info("******* About to get Helper Access token authentication ..! *******");
		
		// Assigning values
		Response response	= null;
		String completedURL = baseURL+ENDPOINT;

		// AuthType setup based on conditions
		if (authType.equalsIgnoreCase("noAuth")) {
			
			response = getRequestSpecification(completedURL, ACCEPTKEY, ACCEPTVALUE, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token respone :  " + response.asString());
			Log.info("Helper access token respone :  " + response.asString());

		} else if (authType.equalsIgnoreCase("basicAuth")) {
			
			response = getRequestSpecification(completedURL, ACCEPTKEY, ACCEPTVALUE, USERNAME, PASSWORD, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token respone :  " + response.asString());
			Log.info("Helper access token respone :  " + response.asString());

		} else if (authType.equalsIgnoreCase("appVersion")) {
			
			response = getRequestSpecification(completedURL, ACCEPTKEY, ACCEPTVALUE, APPVERSIONKEY, APPVERSIONVALUE, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token respone :  " + response.asString());
			Log.info("Helper access token respone :  " + response.asString());

		} 

		return response;

	}

	/* getAccessTokenHelperEndPoint */
	public Response getAccessTokenHelperEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get Helper Access token response ..! *******");
		Log.info("******* About to get Helper Access token response ..! *******");
		
		// Assigning values
		Response response	= null;
		String completedURL = baseURL+ENDPOINT;

		response = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "Helper access token value response :  " + response.asString());
		Log.info("Helper access token value response :  " + response.asString());

		return response;

	}

	/* getAccessTokenHelperEndPoint */
	public Response getAccessTokenHelperEndPoint(String baseURL, String authType, String AcceptKey, String AcceptValue, String userName, String password, String appVersionKey, String appVersionValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get Helper Access token response ..! *******");
		Log.info("******* About to get Helper Access token response ..! *******");
		
		// Assigning values
		Response response	= null;
		String completedURL = baseURL+ENDPOINT;

		// AuthType setup based on conditions
		if (authType.equalsIgnoreCase("noAuth")) {
			
			response = getRequestSpecification(completedURL, AcceptKey, AcceptValue, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token value response :  " + response.asString());
			Log.info("Helper access token value response :  " + response.asString());

		} else if (authType.equalsIgnoreCase("basicAuth")) {
			
			response = getRequestSpecification(completedURL, AcceptKey, AcceptValue, userName, password, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token value response :  " + response.asString());
			Log.info("Helper access token value response :  " + response.asString());

		} else if (authType.equalsIgnoreCase("appVersion")) {
			
			response = getRequestSpecification(completedURL, AcceptKey, AcceptValue, appVersionKey, appVersionValue, statusCode);
			
			logger.log(LogStatus.PASS, "Helper access token value response :  " + response.asString());
			Log.info("Helper access token value response :  " + response.asString());

		} 

		return response;

	}

	/* validateHelperAccessToken ErrorResponse HelperEndPoint */
	public void validateHelperAccessTokenErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorAbout, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate HelperAccessToken error Response ******* ..!");
		Log.info("******* About to validate HelperAccessToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(about, errorAbout, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated HelperAccessToken error Response ************");
		Log.info("************Successfully validated HelperAccessToken Sucess error ************");

	}

	/* getAccessToken */
	public String getAccessToken(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get Access token ..! *******");
		Log.info("******* About to get Access token ..! *******");
		
		String accessToken = "Bearer " + response.path(token);
		
		logger.log(LogStatus.PASS, "Access token value is :  " + accessToken);
		Log.info("Access token value is :  " + accessToken);

		return accessToken;

	}

	
}