package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class ResendOTP extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public ResendOTP(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSER;
	public static final String ENDPOINTRESENDOTP;

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                              
	String id									= "data.id";     
	String message								= "meta.message";                          
	String code									= "errors.code";                                                    
	String title								= "errors.title";   
	String mesage								= "message";                                       
	String status								= "errors.status";                              
	String detail								= "errors.detail";                            
	String links								= "errors.links.about";           
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSER		= Utils.getRestApiTestData(2, "users");
		ENDPOINTRESENDOTP	= Utils.getRestApiTestData(2, "resendotp");
		
	}
	
	/* ResendOtpResponseUserEndPoint */
	public Response ResendOtpResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ResendOtp response ******* ..!");
		Log.info("******* About to get ResendOtp response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTRESENDOTP;

		// AuthType setup based on conditions
		Response resendOTPResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ResendOtp response :  " + resendOTPResponse.asString());
		Log.info("************Successfully got ResendOtp response :  " + resendOTPResponse.asString());

		return resendOTPResponse;

	}
	
	/* ResendOtpResponseUserEndPoint */
	public Response ResendOtpResponseUserEndPoint(String baseURL, String signUpUserToken, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ResendOtp response ******* ..!");
		Log.info("******* About to get ResendOtp response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTRESENDOTP;

		// AuthType setup based on conditions
		Response ResendOTPEndPoint = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ResendOtp response :  " + ResendOTPEndPoint.asString());
		Log.info("************Successfully got ResendOtp response :  " + ResendOTPEndPoint.asString());

		return ResendOTPEndPoint;

	}

	/* ResendOtpResponseUserEndPoint */
	public Response ResendOtpResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get ResendOtp response ******* ..!");
		Log.info("******* About to get ResendOtp response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTRESENDOTP;

		// AuthType setup based on conditions
		Response ResendOTPEndPoint = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ResendOtp response :  " + ResendOTPEndPoint.asString());
		Log.info("************Successfully got ResendOtp response :  " + ResendOTPEndPoint.asString());

		return ResendOTPEndPoint;

	}

	/* validateResendOtpSuccessResponseUserEndPoint */
	public void validateResendOtpSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String mesage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ResendOtp success response ******* ..!");
		Log.info("******* About to get ResendOtp success response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ResendOtp success response************");
		Log.info("************Successfully validated ResendOtp success response************");

	}
	
	/* validateResendOtp error Response UserEndPoint */
	public void validateResendOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ResendOtp error Response ******* ..!");
		Log.info("******* About to validate ResendOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ResendOtp error Response ************");
		Log.info("************Successfully validated ResendOtp error Response ************");

	}

	/* validateResendOtp error Response UserEndPoint */
	public void validateResendOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ResendOtp error Response ******* ..!");
		Log.info("******* About to validate ResendOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ResendOtp error Response ************");
		Log.info("************Successfully validated ResendOtp error Response ************");

	}

	/* validateResendOtp error Response UserEndPoint */
	public void validateResendOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ResendOtp error Response ******* ..!");
		Log.info("******* About to validate ResendOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ResendOtp error Response ************");
		Log.info("************Successfully validated ResendOtp error Response ************");

	}

}