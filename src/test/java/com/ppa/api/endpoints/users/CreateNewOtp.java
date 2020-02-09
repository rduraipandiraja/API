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

public class CreateNewOtp extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public CreateNewOtp(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSERS;
	public static final String ENDPOINTGENERATEOTP;
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
	String mesage								= "meta.message";                       
	String links								= "errors.links.about";     
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTGENERATEOTP	= Utils.getRestApiTestData(2, "generateotp");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		USERNAME			= ConfigurationSetup.USERNAME;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* postDataForCreateNewOtp */
	public String postDataForCreateNewOtp(String type, String mobileNumber) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated mobileNumber : " +mobileNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated mobileNumber : " +mobileNumber.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData ="{\r\n" + 
						"  \"data\": {\r\n" + 
						"    \"type\": \""+type+"\",\r\n" + 
						"    \"attributes\": {\r\n" + 
						"      \"mobile_number\": "+mobileNumber+"\r\n" + 
						"    }\r\n" + 
						"  }\r\n" + 
						"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* newCreateNewOtpUserEndPoint */
	public Response newCreateNewOtpUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTGENERATEOTP;
		Response createNewOtpResponse		= null;

		switch (OTPFLAG) {
		case "enable":
			
			createNewOtpResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
			Log.info("************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
			
			break;
			
		case "disable":
			
			createNewOtpResponse = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
			Log.info("************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
			
			break;
			
		}

		return createNewOtpResponse;

	}

	/* newCreateNewOtpUserEndPoint */
	public Response newCreateNewOTPUserEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTGENERATEOTP;
		Response createNewOtpResponse	= null;

		createNewOtpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
		Log.info("************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());

		return createNewOtpResponse;

	}
	
	/* newCreateNewOtpUserEndPoint */
	public Response newCreateNewOtpUserEndPoint(String baseURL, String accessTokenValue, String dataType, String mobileNumber, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to login using user login EndPoint ..! *******");
		Log.info("******* About to login using user login EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTGENERATEOTP;
		Response createNewOtpResponse		= null;
		String postData					= null;

		postData = this.postDataForCreateNewOtp(dataType, mobileNumber);
		createNewOtpResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());
		Log.info("************ Successfully created createNewOtpResponse : " + createNewOtpResponse.asString());

		return createNewOtpResponse;

	}

	/* validateCreateNewOtp Sucess Response UserEndPoint */
	public void validateCreateNewOtpSucessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String message) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp Sucess Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(mesage, message, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp Sucess Response ************");
		Log.info("************Successfully validated CreateNewOtp Sucess Response ************");

	}

	/* validateCreateNewOtp Error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpInvalidHeaderErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}
	
	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

	/* validateCreateNewOtp error Response UserEndPoint */
	public void validateCreateNewOtpErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateNewOtp error Response ******* ..!");
		Log.info("******* About to validate CreateNewOtp error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateNewOtp error Response ************");
		Log.info("************Successfully validated CreateNewOtp error Response ************");

	}

}