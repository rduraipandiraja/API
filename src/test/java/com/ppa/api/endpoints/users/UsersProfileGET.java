package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class UsersProfileGET extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public UsersProfileGET(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AuthorizationKey;
	public static final String AcceptKey;
	public static final String AcceptValue;
	public static final String ContentTypeKey;
	public static final String ContentTypeValue;
	public static final String EndPointUser;
	public static final String EndPointProfile;
	public static final String OTPFLAG;

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                              
	String id									= "data.id";                               
	String fullName								= "data.attributes.fullname";                
	String email								= "data.attributes.email";             
	String mobileNumber							= "data.attributes.mobile_number";             
	String enabledNewletter						= "data.attributes.enabled_newsletter";                  
	String enabledReferralEarningsNotification	= "data.attributes.enabled_referral_earnings_notification";  
	String includedType							= "included.type"; 
	String includedID							= "included.id";         
	String includedConsumerEmailID				= "included.attributes.customer_info_email_id";                                      
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String parameter							= "errors.source.parameter";  
	String mesage								= "message";                             
	String links								= "errors.links.about";     
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AuthorizationKey	= Utils.getRestApiTestData(0, "auth_Key");
		AcceptKey			= Utils.getRestApiTestData(0, "accept_key");
		AcceptValue			= Utils.getRestApiTestData(0, "accept_value");
		ContentTypeKey		= Utils.getRestApiTestData(0, "content_type_key");
		ContentTypeValue	= Utils.getRestApiTestData(0, "content_type_value");
		EndPointUser		= Utils.getRestApiTestData(2, "users");
		EndPointProfile		= Utils.getRestApiTestData(2, "profile");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		
	}

	/* getUserProfileGETResponseUserEndPoint */
	public Response getUserProfileGETResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - GET response ******* ..!");
		Log.info("******* About to get user profile - GET response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;

		// AuthType setup based on conditions
		Response userProfileResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got user profile - GET response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - GET response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUserProfileGETResponseUserEndPoint */
	public Response getUserProfileGetResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - GET response ******* ..!");
		Log.info("******* About to get user profile - GET response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;

		// AuthType setup based on conditions
		Response userProfileResponse = getRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got user profile - GET response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - GET response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUserProfileGETResponseUserEndPoint */
	public Response getUserProfileGETResponseUserEndPoint(String baseURL, String signUpUserToken, String partnerInfo, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - GET response ******* ..!");
		Log.info("******* About to get user profile - GET response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile+partnerInfo;

		// AuthType setup based on conditions
		Response userProfileResponse = getRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got user profile - GET response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - GET response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}
	
	/* validateUserProfileGETSuccessResponseUserEndPoint */
	public void validateUserProfileGETSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String userFullName, String userEmail, String userMobileNumber, String newsLetter, String referralEarningsNotification) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate user profile - GET success response ******* ..!");
		Log.info("******* About to user profile - GET success response ******* ..!");
		
		switch (OTPFLAG) {
		case "enable":
			
			assertionContainsUsingSubstring(type, userType, objSoftAssertion, response);
			assertionContainsUsingSubstring(id, userID, objSoftAssertion, response);
			assertionContainsUsingSubstring(fullName, userFullName, objSoftAssertion, response);
			assertionContainsUsingSubstring(email, userEmail, objSoftAssertion, response);
			assertionForNullValues(mobileNumber, userMobileNumber, objSoftAssertion, response);
			assertionContainsUsingSubstring(enabledNewletter, newsLetter, objSoftAssertion, response);
			assertionContainsUsingSubstring(enabledReferralEarningsNotification, referralEarningsNotification, objSoftAssertion, response);
			
			break;
			
		case "disable":
			
			assertionContainsUsingSubstring(type, userType, objSoftAssertion, response);
			assertionContainsUsingSubstring(id, userID, objSoftAssertion, response);
			assertionContainsUsingSubstring(fullName, userFullName, objSoftAssertion, response);
			assertionContainsUsingSubstring(email, userEmail, objSoftAssertion, response);
			assertionForNullValues(mobileNumber, "null", objSoftAssertion, response);
			assertionContainsUsingSubstring(enabledNewletter, newsLetter, objSoftAssertion, response);
			assertionContainsUsingSubstring(enabledReferralEarningsNotification, referralEarningsNotification, objSoftAssertion, response);
			
			break;
			
		}
		
		logger.log(LogStatus.PASS, "************Successfully validated user profile - GET success response************");
		Log.info("************Successfully validated user profile - GET success response************");

	}
	
	/* validateUserProfileGETSuccessResponseUserEndPoint */
	public void validateUserProfileGETSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String custInfoEmailID) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate user profile - GET success response ******* ..!");
		Log.info("******* About to user profile - GET success response ******* ..!");
		
		assertionContainsUsingSubstring(includedType, userType, objSoftAssertion, response);
		assertionContainsUsingSubstring(includedID, userID, objSoftAssertion, response);
		assertionContainsUsingSubstring(includedConsumerEmailID, custInfoEmailID, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated user profile - GET success response************");
		Log.info("************Successfully validated user profile - GET success response************");

	}

	/*validateUserProfileGETErrorResponseUserEndPoint */
	public void validateUserProfileGETErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfileGET error Response ******* ..!");
		Log.info("******* About to validate UserProfileGET error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfileGET error Response ************");
		Log.info("************Successfully validated UserProfileGET error Response ************");

	}

	/*validateUserProfileGETErrorResponseUserEndPoint */
	public void validateUserProfileGETErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfileGET error Response ******* ..!");
		Log.info("******* About to validate UserProfileGET error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfileGET error Response ************");
		Log.info("************Successfully validated UserProfileGET error Response ************");

	}

	/*validateUserProfileGETErrorResponseUserEndPoint */
	public void validateUserProfileGETErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfileGET error Response ******* ..!");
		Log.info("******* About to validate UserProfileGET error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfileGET error Response ************");
		Log.info("************Successfully validated UserProfileGET error Response ************");

	}

}