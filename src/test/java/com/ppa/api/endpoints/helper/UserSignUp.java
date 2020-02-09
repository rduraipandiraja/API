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

public class UserSignUp extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public UserSignUp(ExtentTest logger) {

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
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token								= "data.attributes.access_token";  
	String refreshToken							= "data.attributes.refresh_token";  
	String type									= "data.type";                                            
	String id									= "data.id";                                              
	String fullName								= "data.attributes.user_fullname";    
	String userName								= "data.attributes.username";                                             
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
		ENDPOINT			= Utils.getRestApiTestData(2, "user_signup");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* postDataWithoutDeviceInfoSignUpV1WithOTP */
	public String postDataWithoutDeviceInfoForUserSignup(String type, String userName, String fullName, String email, String password, String referralID, String ipAddress) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated userName : " +userName.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated referralID : " +referralID.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated userName : " +userName.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated referralID : " +referralID.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"username\":\""+ userName.trim() +"\",\r\n" + 
				"      \"fullname\":\""+ fullName.trim() +"\",\r\n" + 
				"      \"email\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"referral_user\":"+referralID.trim()+",\r\n" + 
				"      \"ip_address\":\""+ ipAddress.trim() +"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithoutDeviceInfoSignUpV1WithOTP */
	public String postDataWithoutDeviceInfoForUserSignUp(String type, String fullName, String email, String password, String referralID, String ipAddress) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated referralID : " +referralID.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated referralID : " +referralID.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"fullname\":\""+ fullName.trim() +"\",\r\n" + 
				"      \"email\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"referral_user\":"+referralID.trim()+",\r\n" + 
				"      \"ip_address\":\""+ ipAddress.trim() +"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithoutDeviceInfoSignUpV1WithOTP */
	public String postDataWithoutDeviceInfoForUserSignUp(String type, String fullName, String email, String password,  String mobileNumber, String referralID, String ipAddress) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated mobileNumber : " +mobileNumber.trim());
		logger.log(LogStatus.INFO, "Generated referralID : " +referralID.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated mobileNumber : " +mobileNumber.trim());
		Log.info("Generated referralID : " +referralID.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"fullname\":\""+ fullName.trim() +"\",\r\n" + 
				"      \"email\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"mobile_number\":"+ mobileNumber.trim()+",\r\n" + 
				"      \"referral_user\":"+referralID.trim()+",\r\n" + 
				"      \"ip_address\":\""+ ipAddress.trim() +"\"\r\n" + 
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithDeviceInfoForSignUpV1WithOTP */
	public String postDataWithDeviceInfoForUserSignUp(String type, String fullName, String email, String password, String referralID, String ipAddress, String fcmID, String deviceUniqueID, String imeiNumber) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated referralID : " +referralID.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());
		logger.log(LogStatus.INFO, "Generated deviceUniqueID : " +deviceUniqueID.trim());
		logger.log(LogStatus.INFO, "Generated fcmID : " +fcmID.trim());
		logger.log(LogStatus.INFO, "Generated imeiNumber : " +imeiNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated referralID : " +referralID.trim());
		Log.info("Generated deviceUniqueID : " +deviceUniqueID.trim());
		Log.info("Generated fcmID : " +fcmID.trim());
		Log.info("Generated imeiNumber : " +imeiNumber.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"fullname\":\""+ fullName.trim() +"\",\r\n" + 
				"      \"email\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"referral_user\":"+referralID.trim()+",\r\n" + 
				"      \"ip_address\":\""+ ipAddress.trim() +"\",\r\n" + 
				"      \"device_info\": {\"fcm_id\": \""+ fcmID.trim() +"\",\"device_unique_id\": \""+ deviceUniqueID.trim() +"\",\"imei_number\": \""+ imeiNumber.trim() +"\"}\r\n" +
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataWithDeviceInfoForSignUpV1WithOTP */
	public String postDataWithDeviceInfoForUserSignUp(String type, String fullName, String email, String password,  String mobileNumber, String referralID, String ipAddress, String fcmID, String deviceUniqueID, String imeiNumber) {
		
		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fullName : " +fullName.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());
		logger.log(LogStatus.INFO, "Generated password : " +password.trim());
		logger.log(LogStatus.INFO, "Generated mobileNumber : " +mobileNumber.trim());
		logger.log(LogStatus.INFO, "Generated referralID : " +referralID.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());
		logger.log(LogStatus.INFO, "Generated deviceUniqueID : " +deviceUniqueID.trim());
		logger.log(LogStatus.INFO, "Generated fcmID : " +fcmID.trim());
		logger.log(LogStatus.INFO, "Generated imeiNumber : " +imeiNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fullName : " +fullName.trim());
		Log.info("Generated email : " +email.trim());
		Log.info("Generated password : " +password.trim());
		Log.info("Generated mobileNumber : " +mobileNumber.trim());
		Log.info("Generated referralID : " +referralID.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		Log.info("Generated deviceUniqueID : " +deviceUniqueID.trim());
		Log.info("Generated fcmID : " +fcmID.trim());
		Log.info("Generated imeiNumber : " +imeiNumber.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
				"  \"data\": {\r\n" + 
				"    \"type\": \""+type+"\",\r\n" + 
				"    \"attributes\": {\r\n" + 
				"      \"fullname\":\""+ fullName.trim() +"\",\r\n" + 
				"      \"email\":\""+ email.trim() +"\",\r\n" + 
				"      \"password\":\""+ password.trim() +"\",\r\n" + 
				"      \"mobile_number\":"+ mobileNumber.trim()+",\r\n" + 
				"      \"referral_user\":"+referralID.trim()+",\r\n" + 
				"      \"ip_address\":\""+ ipAddress.trim() +"\",\r\n" + 
				"      \"device_info\": {\"fcm_id\": \""+ fcmID.trim() +"\",\"device_unique_id\": \""+ deviceUniqueID.trim() +"\",\"imei_number\": \""+ imeiNumber.trim() +"\"}\r\n" +
				"    }\r\n" + 
				"  }\r\n" + 
				"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}
	
	/* newUserSignUpHelperEndPoint */
	public Response newUserSignUpHelperEndPoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using user signup EndPoint ..! *******");
		Log.info("******* About to sign up as new user using user signup EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userSignUpResponse		= null;

		switch (OTPFLAG) {
		case "enable":
			
			userSignUpResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		case "disable":
			
			userSignUpResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		}

		return userSignUpResponse;

	}

	/* newUserSignUpHelperEndPoint */
	public Response newUserSignUpHelperEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using user signup EndPoint ..! *******");
		Log.info("******* About to sign up as new user using user signup EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userSignUpResponse		= null;

		switch (OTPFLAG) {
		case "enable":
			
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		case "disable":
			
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		}

		return userSignUpResponse;

	}

	/* newUserSignUpHelperEndPoint */
	public Response newUserSignUpHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String fullName, String email, String password, String mobileNumber, String referralID, String deviceType, String ipAddress, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using user signup EndPoint ..! *******");
		Log.info("******* About to sign up as new user using user signup EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userSignUpResponse		= null;
		String postData					= null;

		switch (OTPFLAG) {
		case "enable":
			
			postData = this.postDataWithoutDeviceInfoForUserSignUp(dataType, fullName, email, password, mobileNumber, referralID, ipAddress);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		case "disable":
			
			postData = this.postDataWithoutDeviceInfoForUserSignUp(dataType, fullName, email, password, referralID, ipAddress);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		}

		return userSignUpResponse;

	}

	/* newUserSignUpHelperEndPoint */
	public Response newUserSignUpHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String userName, String fullName, String email, String password, String mobileNumber, String referralID, String deviceType, String ipAddress, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using user signup EndPoint ..! *******");
		Log.info("******* About to sign up as new user using user signup EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userSignUpResponse		= null;
		String postData					= null;

		switch (OTPFLAG) {
		case "enable":
			
			postData = this.postDataWithoutDeviceInfoForUserSignUp(dataType, fullName, email, password, mobileNumber, referralID, ipAddress);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		case "disable":
			
			postData = this.postDataWithoutDeviceInfoForUserSignup(dataType, userName, fullName, email, password, referralID, ipAddress);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		}

		return userSignUpResponse;

	}

	/* newUserSignUpHelperEndPoint */
	public Response newUserSignUpHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String fullName, String email, String password, String mobileNumber, String referralID, String deviceType, String ipAddress, String fcmID, String deviceUniqueID, String imeiNumber, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using user signup EndPoint ..! *******");
		Log.info("******* About to sign up as new user using user signup EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response userSignUpResponse= null;
		String postData					= null;

		switch (OTPFLAG) {
		case "enable":
			
			postData = this.postDataWithDeviceInfoForUserSignUp(dataType, fullName, email, password, mobileNumber, referralID, ipAddress, fcmID, deviceUniqueID, imeiNumber);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		case "disable":
			
			postData = this.postDataWithDeviceInfoForUserSignUp(dataType, fullName, email, password, referralID, ipAddress, fcmID, deviceUniqueID, imeiNumber);
			userSignUpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			Log.info("************ Successfully created userSignUpResponse : " + userSignUpResponse.asString());
			
			break;
			
		}

		return userSignUpResponse;

	}

	/* validateUserSignUp Sucess Response HelperEndPoint */
	public void validateUserSignUpSucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userFullName) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp Sucess Response ******* ..!");
		Log.info("******* About to validate UserSignUp Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(fullName, userFullName, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp Sucess Response ************");
		Log.info("************Successfully validated UserSignUp Sucess Response ************");

	}

	/* validateUserSignUp Error Response HelperEndPoint */
	public void validateUserSignUpErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

	}

	/* validateUserSignUp error Response HelperEndPoint */
	public void validateUserSignUpInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

	}
	
	/* validateUserSignUp error Response HelperEndPoint */
	public void validateUserSignUpErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

	}

	/* validateUserSignUp error Response HelperEndPoint */
	public void validateUserSignUpErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

	}

	/* validateUserSignUp error Response HelperEndPoint */
	public void validateUserSignUpErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

	}

	/* validateUserSignUp error Response HelperEndPoint */
	public void validateUserSignUpErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp error Response ******* ..!");
		Log.info("******* About to validate UserSignUp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp error Response ************");
		Log.info("************Successfully validated UserSignUp error Response ************");

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

	/* getUserID */
	public int getUserID(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get UserID ..! *******");
		Log.info("******* About to get UserID ..! *******");
		
		int userid = response.path(id);
		
		logger.log(LogStatus.PASS, "UserID is :  " + userid);
		Log.info("UserID value is :  " + userid);

		return userid;

	}

	/* getUserName */
	public String getUserName(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get userName ..! *******");
		Log.info("******* About to get userName ..! *******");
		
		String username = response.path(userName);
		
		logger.log(LogStatus.PASS, "UserName is :  " + username);
		Log.info("UserName value is :  " + username);

		return username;

	}

	/* getUserFullName */
	public String getUserFullName(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get userFullName ..! *******");
		Log.info("******* About to get userFullName ..! *******");
		
		String userFullname = response.path(fullName);
		
		logger.log(LogStatus.PASS, "UserFullName is :  " + userFullname);
		Log.info("UserFullName value is :  " + userFullname);

		return userFullname;

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