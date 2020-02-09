package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class UsersChangePassword extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public UsersChangePassword(ExtentTest logger) {

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

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                              
	String id									= "data.id";                                          
	String message								= "meta.message";                     
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
		EndPointProfile		= Utils.getRestApiTestData(2, "change_password");
		
	}
	
	/* putDataForUsersChangePassword */
	public String putDataForUsersChangePassword(String type, String currentPassword, String newPassword, String confirmPassword) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated currentPassword : " +currentPassword.trim());
		logger.log(LogStatus.INFO, "Generated newPassword : " +newPassword.trim());
		logger.log(LogStatus.INFO, "Generated confirmPassword : " +confirmPassword.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated currentPassword : " +currentPassword.trim());
		Log.info("Generated newPassword : " +newPassword.trim());
		Log.info("Generated confirmPassword : " +confirmPassword.trim());
		
		logger.log(LogStatus.INFO, "About to put data for POST request");
		Log.info("About to post data for PUT request");
		
		String postData ="{\r\n" + 
						"  \"data\": {\r\n" + 
						"    \"type\": \""+type+"\",\r\n" + 
						"    \"attributes\": {\r\n" + 
						"      \"current_password\": \""+currentPassword+"\",\r\n" + 
						"      \"new_password\": \""+newPassword+"\",\r\n" + 
						"      \"confirm_password\": \""+confirmPassword+"\"\r\n" + 
						"    }\r\n" + 
						"  }\r\n" + 
						"}";

		logger.log(LogStatus.PASS, "Post Data for PUT request : " + postData.trim());
		Log.info("Post Data for PUT request : " + postData.trim());
		
		return postData;
		
	}
	
	/* getUsersChangePasswordResponseUserEndPoint */
	public Response getUsersChangePasswordResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get users change password response ******* ..!");
		Log.info("******* About to get users change password response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;

		// AuthType setup based on conditions
		userProfileResponse = putRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got users change password response :  " + userProfileResponse.asString());
		Log.info("************Successfully got users change password response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUsersChangePasswordResponseUserEndPoint */
	public Response getUsersChangePasswordResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get users change password response ******* ..!");
		Log.info("******* About to get users change password response ******* ..!");
		
		// Assigning values
		String completedURL				= baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;

		// AuthType setup based on conditions
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got users change password response :  " + userProfileResponse.asString());
		Log.info("************Successfully got users change password response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUsersChangePasswordResponseUserEndPoint */
	public Response getUsersChangePasswordResponseUserEndPoint(String baseURL, String signUpUserToken, String AcceptValue, String ContentTypeValue, String currentPassword, String newPassword, String confirmPassword, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get users change password response ******* ..!");
		Log.info("******* About to get users change password response ******* ..!");
		
		// Assigning values
		String completedURL				= baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;
		String putData					= null;

		// AuthType setup based on conditions
		putData = this.putDataForUsersChangePassword(type, currentPassword, newPassword, confirmPassword);
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, putData, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got users change password response :  " + userProfileResponse.asString());
		Log.info("************Successfully got users change password response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}

	/* getUsersChangePasswordResponseUserEndPoint */
	public Response getUsersChangePasswordResponseUserEndPoint(String baseURL, String signUpUserToken, String type, String currentPassword, String newPassword, String confirmPassword, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get users change password response ******* ..!");
		Log.info("******* About to get users change password response ******* ..!");
		
		// Assigning values
		String completedURL				= baseURL+EndPointUser+EndPointProfile;
		Response userProfileResponse	= null;
		String putData					= null;

		// AuthType setup based on conditions
		putData = this.putDataForUsersChangePassword(type, currentPassword, newPassword, confirmPassword);
		userProfileResponse = putRequestSpecification(completedURL, AuthorizationKey, signUpUserToken, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, putData, statusCode);

		logger.log(LogStatus.PASS, "************Successfully got users change password response :  " + userProfileResponse.asString());
		Log.info("************Successfully got users change password response :  " + userProfileResponse.asString());

		return userProfileResponse;

	}
	
	/* validateUsersChangePasswordSuccessResponseUserEndPoint */
	public void validateUsersChangePasswordSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String mesage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate users change password success response ******* ..!");
		Log.info("******* About to users change password success response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated users change password success response************");
		Log.info("************Successfully validated users change password success response************");

	}
	
	/* validateUsersChangePassword Error Response UserEndPoint */
	public void validateUsersChangePasswordErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}

	/* validateUsersChangePassword error Response UserEndPoint */
	public void validateUsersChangePasswordInvalidAcceptErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}
	
	/* validateUsersChangePassword error Response UserEndPoint */
	public void validateUsersChangePasswordErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}

	/* validateUsersChangePassword error Response UserEndPoint */
	public void validateUsersChangePasswordErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}

	/* validateUsersChangePassword error Response UserEndPoint */
	public void validateUsersChangePasswordErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}

	/* validateUsersChangePassword error Response UserEndPoint */
	public void validateUsersChangePasswordErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UsersChangePassword error Response ******* ..!");
		Log.info("******* About to validate UsersChangePassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UsersChangePassword error Response ************");
		Log.info("************Successfully validated UsersChangePassword error Response ************");

	}

}