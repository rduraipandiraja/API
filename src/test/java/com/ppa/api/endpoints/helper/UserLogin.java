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

public class UserLogin extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public UserLogin(ExtentTest logger) {

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
	public static final String USERNAME;
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token								= "data.attributes.access_token";  
	String refreshToken							= "data.attributes.refresh_token";  
	String type									= "data.type";                                            
	String id									= "data.id";                                              
	String fullName								= "data.attributes.user_fullname";                                          
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";                 
	String parameter							= "errors.source.parameter";                                          
	String message								= "message";                                     
	String links								= "errors.links.about"; 
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINT			= Utils.getRestApiTestData(2, "user_login");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		USERNAME			= ConfigurationSetup.USERNAME;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* postDataWithoutDeviceInfoUserLogin */
	public String postDataWithoutDeviceInfoForUserLogin(String type, String email, String password) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"username\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithDeviceInfoForUserLogin */
	public String postDataWithDeviceInfoForUserLogin(String type, String email, String password, String fcmID, String deviceUniqueID, String imeiNumber) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated deviceUniqueID : " +deviceUniqueID.trim());
		logger.log(LogStatus.INFO, "Generated fcmID : " +fcmID.trim());
		logger.log(LogStatus.INFO, "Generated imeiNumber : " +imeiNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated deviceUniqueID : " +deviceUniqueID.trim());
		Log.info("Generated fcmID : " +fcmID.trim());
		Log.info("Generated imeiNumber : " +imeiNumber.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"username\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"device_info\": {\"fcm_id\": \""+ fcmID.trim() +"\",\"device_unique_id\": \""+ deviceUniqueID.trim() +"\",\"imei_number\": \""+ imeiNumber.trim() +"\"}\r\n" +
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* newUserLoginHelperEndPoint */
	public Response newUserLoginHelperEndPoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userLoginResponse		= null;

		switch (OTPFLAG) {
		case "enable":
			
			userLoginResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			
			break;
			
		case "disable":
			
			userLoginResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			
			break;
			
		}

		return userLoginResponse;

	}

	/* newUserLoginHelperEndPoint */
	public Response newUserLoginHelperEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userLoginResponse		= null;

		userLoginResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
		Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());

		return userLoginResponse;

	}
	
	/* newUserLoginHelperEndPoint */
	public Response newUserLoginHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String email, String password, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userLoginResponse		= null;
		String postData					= null;

		postData = this.postDataWithoutDeviceInfoForUserLogin(dataType, email, password);
		userLoginResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
		Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());

		return userLoginResponse;

	}

	/* newUserLoginHelperEndPoint */
	public Response newUserLoginHelperEndPointUsingUserName(String baseURL, String accessTokenValue, String dataType, String username, String password, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userLoginResponse		= null;
		String postData					= null;
		
		switch (USERNAME) {
		case "enable":

			postData = this.postDataWithoutDeviceInfoForUserLogin(dataType, username, password);
			userLoginResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());
			
			break;
			
		case "disable":

			logger.log(LogStatus.PASS, "************ Not required for this partner ************");
			Log.info("************ Not required for this partner ************");
			
			break;
			
		}

		return userLoginResponse;

	}
	
	/* newUserLoginHelperEndPoint */
	public Response newUserLoginHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String email, String password, String fcmID, String deviceUniqueID, String imeiNumber, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userLoginResponse= null;
		String postData					= null;

		postData = this.postDataWithDeviceInfoForUserLogin(dataType, email, password, fcmID, deviceUniqueID, imeiNumber);
		userLoginResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created userLoginResponse : " + userLoginResponse.asString());
		Log.info("************ Successfully created userLoginResponse : " + userLoginResponse.asString());

		return userLoginResponse;

	}

	/* validateUserLogin Sucess Response HelperEndPoint */
	public void validateUserLoginSucessResponseHelperEndpoint(SoftAssert objSoftAssertion, Response response, String userType, String userFullName) throws InterruptedException, AWTException {
		
		switch (USERNAME) {
		case "enable":

			logger.log(LogStatus.INFO, "******* About to validate UserLogin Sucess Response ******* ..!");
			Log.info("******* About to validate UserLogin Sucess Response ******* ..!");
			
			
			assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
			assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
			
			logger.log(LogStatus.PASS, "************Successfully validated UserLogin Sucess Response ************");
			Log.info("************Successfully validated UserLogin Sucess Response ************");
			
			break;
			
		case "disable":

			logger.log(LogStatus.PASS, "************ Not required for this partner ************");
			Log.info("************ Not required for this partner ************");
			
			break;
			
		}

	}
	
	/* validateUserLogin Sucess Response HelperEndPoint */
	public void validateUserLoginSucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userFullName) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin Sucess Response ******* ..!");
		Log.info("******* About to validate UserLogin Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin Sucess Response ************");
		Log.info("************Successfully validated UserLogin Sucess Response ************");

	}

	/* validateUserLogin Error Response HelperEndPoint */
	public void validateUserLoginErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

	}

	/* validateUserLogin error Response HelperEndPoint */
	public void validateUserLoginInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

	}

	/* validateUserLogin error Response HelperEndPoint */
	public void validateUserLoginErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

	}

	/* validateUserLogin error Response HelperEndPoint */
	public void validateUserLoginErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

	}

	/* validateUserLogin error Response HelperEndPoint */
	public void validateUserLoginErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

	}

	/* validateUserLogin error Response HelperEndPoint */
	public void validateUserLoginErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserLogin error Response ******* ..!");
		Log.info("******* About to validate UserLogin error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserLogin error Response ************");
		Log.info("************Successfully validated UserLogin error Response ************");

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
	
	/* getRefreshToken */
	public String getRefreshToken(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get refresh token ..! *******");
		Log.info("******* About to get refresh token ..! *******");
		
		String refreshtoken =  response.path(refreshToken);
		
		logger.log(LogStatus.PASS, "refresh token value is :  " + refreshtoken);
		Log.info("refresh token value is :  " + refreshtoken);

		return refreshtoken;

	}

}