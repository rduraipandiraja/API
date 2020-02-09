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

public class ForgotPassword extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public ForgotPassword(ExtentTest logger) {

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
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token								= "data.attributes.access_token";  
	String type									= "data.type";                                            
	String id									= "data.id";                                        
	String message								= "meta.message";                                                 
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";                 
	String parameter							= "errors.source.parameter";                          
	String links								= "errors.links.about"; 
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINT			= Utils.getRestApiTestData(2, "forgotPassword");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}

	/* postDataForForgotPassword */
	public String postDataForForgotPassword(String type, String email) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated email : " +email.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated email : " +email.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"email\":\""+email+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}
	
	/* newForgotPasswordHelperEndPoint */
	public Response newForgotPasswordHelperEndPoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to request forgot password using forgot password EndPoint ..! *******");
		Log.info("******* About to request forgot password using forgot password EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response forgotPasswordResponse= null;
		
		forgotPasswordResponse = postRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());
		Log.info("************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());

		return forgotPasswordResponse;

	}

	/* newForgotPasswordHelperEndPoint */
	public Response newForgotpasswordHelperEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to Request ForgotPassword using ForgotPassword EndPoint ..! *******");
		Log.info("******* About to Request ForgotPassword using ForgotPassword EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response forgotPasswordResponse	= null;

		forgotPasswordResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());
		Log.info("************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());

		return forgotPasswordResponse;

	}
	
	/* newForgotPasswordHelperEndPoint */
	public Response newForgotPasswordHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String email, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to Request ForgotPassword using ForgotPassword EndPoint ..! *******");
		Log.info("******* About to Request ForgotPassword using ForgotPassword EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT+deviceType;
		Response forgotPasswordResponse	= null;
		String postData					= null;

		postData = this.postDataForForgotPassword(dataType, email);
		forgotPasswordResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());
		Log.info("************ Successfully created forgotPasswordResponse : " + forgotPasswordResponse.asString());

		return forgotPasswordResponse;

	}

	/* validateForgotPassword Sucess Response HelperEndPoint */
	public void validateForgotPasswordSucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String userMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword Sucess Response ******* ..!");
		Log.info("******* About to validate ForgotPassword Sucess Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, userMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword Sucess Response ************");
		Log.info("************Successfully validated ForgotPassword Sucess Response ************");

	}
	
	/* validateforgotPassword Error Response HelperEndPoint */
	public void validateForgotPasswordErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate forgotPassword error Response ******* ..!");
		Log.info("******* About to validate forgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("message", errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated forgotPassword error Response ************");
		Log.info("************Successfully validated forgotPassword error Response ************");

	}

	/* validateForgotPassword error Response HelperEndPoint */
	public void validateForgotPasswordInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword error Response ******* ..!");
		Log.info("******* About to validate ForgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword error Response ************");
		Log.info("************Successfully validated ForgotPassword error Response ************");

	}
	
	/* validateForgotPassword error Response HelperEndPoint */
	public void validateForgotPasswordErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword error Response ******* ..!");
		Log.info("******* About to validate ForgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword error Response ************");
		Log.info("************Successfully validated ForgotPassword error Response ************");

	}

	/* validateForgotPassword error Response HelperEndPoint */
	public void validateForgotPasswordErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword error Response ******* ..!");
		Log.info("******* About to validate ForgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword error Response ************");
		Log.info("************Successfully validated ForgotPassword error Response ************");

	}

	/* validateForgotPassword error Response HelperEndPoint */
	public void validateForgotPasswordErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword error Response ******* ..!");
		Log.info("******* About to validate ForgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword error Response ************");
		Log.info("************Successfully validated ForgotPassword error Response ************");

	}

	/* validateForgotPassword error Response HelperEndPoint */
	public void validateForgotPasswordErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate ForgotPassword error Response ******* ..!");
		Log.info("******* About to validate ForgotPassword error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ForgotPassword error Response ************");
		Log.info("************Successfully validated ForgotPassword error Response ************");

	}
	
}