package com.ppa.api.endpoints.helper;

import java.awt.AWTException;
import java.util.Arrays;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class SignUpOTP extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public SignUpOTP(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINT;
	public static final String OTPFLAG;

	/********************************************************** Gpath Creation started ***************************************************************/

	String status								= "errors.status";                              
	String about								= "errors.links.about";                               
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";    
	String message								= "message";                                      
	String links								= "errors.links.about";         
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINT			= Utils.getRestApiTestData(2, "signup_otp");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		
	}
	
	/* postDataForSignUpOTP */
	public String postDataForSignUpOTP(String type, String mobileNumber) {

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData ="	{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"mobile_number\":"+ mobileNumber.trim()+"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* getGUIDHelperEndPoint */
	public Response getGUIDHelperEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after not passing header ..! *******");
		Log.info("******* About to get sign up OTP response after not passing header ..! *******");

		// Assigning Values
		String completedURL			= baseURL+ENDPOINT;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OTPFLAG) {
		case "enable":
			
			response = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
			
			break;
			
		case "disable":
						
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}
		
		return response;

	}
	
	/* getGUIDHelperEndPoint */
	public Response getGUIDEntireJsonResponseHelperEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINT;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OTPFLAG) {
		case "enable":
			
			response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
			
			break;
			
		case "disable":
						
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}
		
		return response;

	}

	/* getGUIDHelperEndPoint */
	public Object[] getGUIDHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String mobileNumber, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINT;
		String emptyValue			= Utils.getRestApiTestData(8, "emptyValue");
		Response response			= null;
		String postData				= null;
		Object[] signupOTPResponse	= null;
		
		// OTP enabled based on conditions
		switch (OTPFLAG) {
		case "enable":
			
			postData = postDataForSignUpOTP(dataType, mobileNumber);
			response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			// Assigning Values from response
			String type = response.path("data.type");
			String otpGuid = response.path("data.id");
			String otpExpireSeconds = response.path("data.attributes.otp_expire_sec");
			String successMessage = response.path("meta.message");
			
			signupOTPResponse = new Object[] {type, otpGuid, otpExpireSeconds, successMessage, mobileNumber};

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + Arrays.toString(signupOTPResponse));
			Log.info("************ Successfully created signupOTPResponse :  " + Arrays.toString(signupOTPResponse));
			
			break;
			
		case "disable":
						
			signupOTPResponse = new Object[] {emptyValue, emptyValue, emptyValue, emptyValue, emptyValue};

			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}
		
		return signupOTPResponse;

	}

	/* getGUIDHelperEndPoint */
	public Response getGUIDEntireJSONResponseHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String mobileNumber, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINT;
		String postData				= null;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OTPFLAG) {
		case "enable":

			postData = postDataForSignUpOTP(dataType, mobileNumber);
			response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());

			break;

		case "disable":

			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);

			break;
			
		}
		
		return response;

	}

	/* validateSignUpWithOTP error Response HelperEndPoint */
	public void validateSignUpOTPErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate SignUpWithOTP error Response ******* ..!");
		Log.info("******* About to validate SignUpWithOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated SignUpWithOTP error Response ************");
		Log.info("************Successfully validated SignUpWithOTP error Response ************");

	}

	/* validateSignUpWithOTP error Response HelperEndPoint */
	public void validateSignUpOTPInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate SignUpWithOTP error Response ******* ..!");
		Log.info("******* About to validate SignUpWithOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated SignUpWithOTP error Response ************");
		Log.info("************Successfully validated SignUpWithOTP error Response ************");

	}
	
	/* validateSignUpWithOTP error Response HelperEndPoint */
	public void validateSignUpOTPErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate SignUpWithOTP error Response ******* ..!");
		Log.info("******* About to validate SignUpWithOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated SignUpWithOTP error Response ************");
		Log.info("************Successfully validated SignUpWithOTP error Response ************");

	}

	/* validateSignUpOTP Error Response HelperEndPoint */
	public void validateSignUpOTPErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorAbout, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate HelperAccessToken error Response ******* ..!");
		Log.info("******* About to validate HelperAccessToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(about, errorAbout, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated HelperAccessToken error Response ************");
		Log.info("************Successfully validated HelperAccessToken Sucess error ************");

	}

}