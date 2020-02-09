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

public class UsersProfilePUT extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public UsersProfilePUT(ExtentTest logger) {

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
	String message								= "meta.message"; 
	String includedType							= "included.type"; 
	String includedID							= "included.id";         
	String includedConsumerEmailID				= "included.attributes.customer_info_email_id";                                      
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String parameter							= "errors.source.parameter";                 
	String pointer								= "errors.source.pointer";                                          
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
	
	/* putDataForUserProfilePUT */
	public String putDataForUserProfilePUTForDeviceTypeDesktop(String type, String fullName, String password, String newEmail, String confirmNewEmail, String enableNewsLetter, String enableReferralEarningsNotification) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated newEmail : " +newEmail.trim());
		logger.log(LogStatus.INFO, "Generated confirmNewEmail : " +confirmNewEmail.trim());
		logger.log(LogStatus.INFO, "Generated enableNewsLetter : " +enableNewsLetter.trim());
		logger.log(LogStatus.INFO, "Generated enableReferralEarningsNotification : " +enableReferralEarningsNotification.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		
		logger.log(LogStatus.INFO, "About to put data for POST request");
		Log.info("About to post data for PUT request");
		
		String postData ="{\r\n" + 
						"  \"data\": {\r\n" + 
						"    \"type\": \""+type+"\",\r\n" + 
						"    \"attributes\": {\r\n" + 
						"      \"fullname\":\""+fullName+"\",\r\n" + 
						"      \"new_email1\":\""+newEmail+"\",\r\n" + 
						"      \"confirm_new_email1\":\""+confirmNewEmail+"\",\r\n" + 
						"      \"enable_newsletter\":\""+enableNewsLetter+"\",\r\n" + 
						"      \"enable_referral_earnings_notification\":\""+enableReferralEarningsNotification+"\"\r\n" + 
						"    }\r\n" + 
						"  }\r\n" + 
						"}\r\n" + 
						"";

		logger.log(LogStatus.PASS, "Post Data for PUT request : " + postData.trim());
		Log.info("Post Data for PUT request : " + postData.trim());
		
		return postData;
		
	}

	/* putDataForUserProfilePUT */
	public String putDataForUserProfilePUTForDeviceTypeApp(String type, String fullName, String password, String newEmail, String confirmNewEmail, String enableNewsLetter, String enableReferralEarningsNotification) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated newEmail : " +newEmail.trim());
		logger.log(LogStatus.INFO, "Generated confirmNewEmail : " +confirmNewEmail.trim());
		logger.log(LogStatus.INFO, "Generated enableNewsLetter : " +enableNewsLetter.trim());
		logger.log(LogStatus.INFO, "Generated enableReferralEarningsNotification : " +enableReferralEarningsNotification.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		
		logger.log(LogStatus.INFO, "About to put data for POST request");
		Log.info("About to post data for PUT request");
		
		String postData ="{\r\n" + 
						"  \"data\": {\r\n" + 
						"    \"type\": \""+type+"\",\r\n" + 
						"    \"attributes\": {\r\n" + 
						"      \"fullname\":\""+fullName+"\",\r\n" + 
						"      \"new_email\":\""+newEmail+"\",\r\n" + 
						"      \"confirm_new_email\":\""+confirmNewEmail+"\",\r\n" + 
						"      \"enable_newsletter\":\""+enableNewsLetter+"\",\r\n" + 
						"      \"enable_referral_earnings_notification\":\""+enableReferralEarningsNotification+"\"\r\n" + 
						"    }\r\n" + 
						"  }\r\n" + 
						"}\r\n" + 
						"";

		logger.log(LogStatus.PASS, "Post Data for PUT request : " + postData.trim());
		Log.info("Post Data for PUT request : " + postData.trim());
		
		return postData;
		
	}
	
	/* getUserProfilePUTResponseUserEndPoint */
	public Response getUserProfilePUTResponseUserEndPoint(String baseURL, String authorizationValue, String AcceptValue, String ContentTypeValue, String type, String fullname, String password, String newEmail, String confirmNewEmail, String enableNewsLetter, String enableReferralEarningsNotification, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - PUT response ******* ..!");
		Log.info("******* About to get user profile - PUT response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;
		String putData					= null;

		// AuthType setup based on conditions
		putData = this.putDataForUserProfilePUTForDeviceTypeDesktop(type, fullname, password, newEmail, confirmNewEmail, enableNewsLetter, enableReferralEarningsNotification);
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, authorizationValue, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, putData, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got user profile - PUT response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - PUT response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}
	
	/* getUserProfilePUTResponseUserEndPoint */
	public Response getUserProfilePUTResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - PUT response ******* ..!");
		Log.info("******* About to get user profile - PUT response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;

		// AuthType setup based on conditions
		userProfileResponse = putRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got user profile - PUT response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - PUT response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUserProfilePUTResponseUserEndPoint */
	public Response getUserProfilePUTResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - PUT response ******* ..!");
		Log.info("******* About to get user profile - PUT response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;

		// AuthType setup based on conditions
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got user profile - PUT response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - PUT response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}
	
	/* getUserProfilePUTResponseUserEndPoint */
	public Response getUserProfilePUTResponseUserEndPoint(String baseURL, String signUpUserToken, String type, String fullname, String password, String newEmail, String confirmNewEmail, String enableNewsLetter, String enableReferralEarningsNotification, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - PUT response ******* ..!");
		Log.info("******* About to get user profile - PUT response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;
		String putData					= null;

		// AuthType setup based on conditions
		putData = this.putDataForUserProfilePUTForDeviceTypeDesktop(type, fullname, password, newEmail, confirmNewEmail, enableNewsLetter, enableReferralEarningsNotification);
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, putData, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got user profile - PUT response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - PUT response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUserProfilePUTResponseUserEndPoint */
	public Response getUserProfilePUTResponseUserEndPointForDeviceTypeApp(String baseURL, String signUpUserToken, String type, String fullname, String password, String newEmail, String confirmNewEmail, String enableNewsLetter, String enableReferralEarningsNotification, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get user profile - PUT response ******* ..!");
		Log.info("******* About to get user profile - PUT response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;
		String putData					= null;

		// AuthType setup based on conditions
		putData = this.putDataForUserProfilePUTForDeviceTypeApp(type, fullname, password, newEmail, confirmNewEmail, enableNewsLetter, enableReferralEarningsNotification);
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, putData, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got user profile - PUT response :  " + userProfileResponse.asString());
		Log.info("************Successfully got user profile - PUT response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* validateUserProfilePUTSuccessResponseUserEndPoint */
	public void validateUserProfilePUTSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String userFullName, String userEmail, String userMobileNumber, String newsLetter, String referralEarningsNotification, String mesage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate user profile - GET success response ******* ..!");
		Log.info("******* About to user profile - GET success response ******* ..!");
		
		switch (OTPFLAG) {
		case "enable":
			
			assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(email, userEmail, objSoftAssertion, response);
			assertionForNullValues(mobileNumber, userMobileNumber, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(enabledNewletter, newsLetter, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(enabledReferralEarningsNotification, referralEarningsNotification, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
			
			break;
			
		case "disable":
			
			assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(email, userEmail, objSoftAssertion, response);
			assertionForNullValues(mobileNumber, "null", objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(enabledNewletter, newsLetter, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(enabledReferralEarningsNotification, referralEarningsNotification, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
			
			break;
			
		}
		
		logger.log(LogStatus.PASS, "************Successfully validated user profile - GET success response************");
		Log.info("************Successfully validated user profile - GET success response************");

	}
	
	/* validateUserProfilePUT Error Response UserEndPoint */
	public void validateUserProfilePUTErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

	/* validateUserProfilePUT error Response UserEndPoint */
	public void validateUserProfilePUTInvalidHeaderErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

	/* validateUserProfilePUT error Response UserEndPoint */
	public void validateUserProfilePUTErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

	/* validateUserProfilePUT error Response UserEndPoint */
	public void validateUserProfilePUTErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

	/* validateUserProfilePUT error Response UserEndPoint */
	public void validateUserProfilePUTErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

	/* validateUserProfilePUT error Response UserEndPoint */
	public void validateUserProfilePUTErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserProfilePUT error Response ******* ..!");
		Log.info("******* About to validate UserProfilePUT error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserProfilePUT error Response ************");
		Log.info("************Successfully validated UserProfilePUT error Response ************");

	}

}