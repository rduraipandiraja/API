package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class Logout extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public Logout(ExtentTest logger) {

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
	public static final String ENDPOINTLOGOUT;

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                                            
	String id									= "data.id";                                        
	String message								= "meta.message";  
	String mesage								= "message";                                     
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String parameter							= "errors.source.parameter";                
	String pointer								= "errors.source.pointer";  
	String links								= "errors.links.about";                                  
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTLOGOUT		= Utils.getRestApiTestData(2, "logout");
		
	}

	/* postDataForSignUpOTP */
	public String postDataForLogout(String type) {

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* postDataForSignUpOTP */
	public String postDataForLogout(String type, String fcmID, String deviceUniqueID) {

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"	  \"fcm_id\":\""+fcmID+"\",\r\n" + 
							"      \"device_unique_id\":\""+deviceUniqueID+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* logoutUserEndpoint */
	public Response logoutUserEndpoint(String baseURL, String accessToken, String AcceptValue, String ContentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		String authorizationValue	= accessToken;

		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}

	/* logoutUserEndpoint */
	public Response logoutUserendpoint(String baseURL, String accessToken, String dataType, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		String authorizationValue	= accessToken;
		String postData				= null;

		postData = postDataForLogout(dataType);
		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}
	
	/* logoutUserEndpoint */
	public Response logoutUserEndpoint(String baseURL, String accessToken, String dataType, String fcmID, String deviceUniqueID, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		String authorizationValue	= accessToken;
		String postData				= null;

		postData = postDataForLogout(dataType, fcmID, deviceUniqueID);
		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}

	/* logoutUserEndpoint */
	public Response logoutUserEndpoint(String baseURL, String accessToken, String AcceptValue, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		String authorizationValue	= accessToken;
		
		response = getRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}

	/* logoutUserEndpoint */
	public Response logoutUserEndpoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		
		response = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}
	
	/* logoutUserEndpoint */
	public Response logoutUserEndpoint(String baseURL, String accessToken, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to do logout using logout endpoint ..! *******");
		Log.info("******* About to do logout using logout endpoint ..! *******");
		
		// Assigning values
		Response response			= null;
		String completedURL			= baseURL+ENDPOINTUSERS+ENDPOINTLOGOUT+deviceType;
		String authorizationValue	= accessToken;
		
		response = getRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "Logout respone :  " + response.asString());
		Log.info("Logout respone :  " + response.asString());

		return response;

	}
	
	/* validate Logout Sucess Response UserEndpoint */
	public void validateLogoutSucessResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String userMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate logout Sucess Response ******* ..!");
		Log.info("******* About to validate UserSignUp logout Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, userMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated logout Sucess Response ************");
		Log.info("************Successfully validated logout Sucess Response ************");

	}

	/*validatelogoutErrorResponseUserEndpoint */
	public void validateLogoutErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate logout error Response ******* ..!");
		Log.info("******* About to validate logout error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated logout error Response ************");
		Log.info("************Successfully validated logout error Response ************");

	}

	/*validatelogoutErrorResponseUserEndpoint */
	public void validateLogoutErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate logout error Response ******* ..!");
		Log.info("******* About to validate logout error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated logout error Response ************");
		Log.info("************Successfully validated logout error Response ************");

	}

	/*validatelogoutErrorResponseUserEndpoint */
	public void validateLogoutErrorResponseUserEndpoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate logout error Response ******* ..!");
		Log.info("******* About to validate logout error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated logout error Response ************");
		Log.info("************Successfully validated logout error Response ************");

	}

	/*validatelogoutErrorResponseUserEndpoint */
	public void validateLogoutErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate logout error Response ******* ..!");
		Log.info("******* About to validate logout error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated logout error Response ************");
		Log.info("************Successfully validated logout error Response ************");

	}

}