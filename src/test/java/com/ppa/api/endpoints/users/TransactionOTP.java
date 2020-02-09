package com.ppa.api.endpoints.users;

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

public class TransactionOTP extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public TransactionOTP(ExtentTest logger) {

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
	public static final String OTPFLAGTRANSACTIONOTP;
	public static final String FRONTENDURL;
	public static final String OTPFLAG;

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                              
	String id									= "data.id";                              
	String otpExpireSeconds						= "data.attribute.otp_expire_sec";                                
	String message								= "meta.message";      
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String pointer								= "errors.source.pointer";                 
	String parameter							= "errors.source.parameter";                                         
	String mesage								= "message";                                               
	String links								= "errors.links.about";                                      
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY		= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY				= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE				= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY			= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE		= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSER			= Utils.getRestApiTestData(2, "users");
		OTPFLAGTRANSACTIONOTP	= Utils.getRestApiTestData(2, "transaction_otp");
		OTPFLAG					= ConfigurationSetup.OTPFLAG;
		FRONTENDURL				= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* postDataForTransactionOTP */
	public String postDataForTransactionOTP(String type, String notificationName) {
		
		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated notification_name : " +notificationName.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated notification_name : " +notificationName.trim());

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"notification_name\": \""+notificationName+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}
	
	/* getTransactionotpResponseUserEndPoint */
	public Object[] getTransactionotpResponseUserEndPoint(String baseURL, String accessTokenValue, String dataType, String notificationName, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get TransactionOTP response after posting data  ..! *******");
		Log.info("******* About to get TransactionOTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINTUSER+OTPFLAGTRANSACTIONOTP;
		Response response			= null;
		String postData				= null;
		Object[] transactionOTPResponse	= null;

		postData = postDataForTransactionOTP(dataType, notificationName);
		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		// Assigning Values from response
		String type = response.path("data.type");
		int id = response.path("data.id");
		String otpGuid = response.path("data.attribute.otp_guid");
		String otpExpireSeconds = response.path("data.attribute.otp_expire_sec");
		String successMessage = response.path("meta.message");
		
		transactionOTPResponse = new Object[] {type, id, otpGuid, otpExpireSeconds, successMessage};

		logger.log(LogStatus.PASS, "************ Successfully created transactionOTPResponse :  " + Arrays.toString(transactionOTPResponse));
		Log.info("************ Successfully created transactionOTPResponse :  " + Arrays.toString(transactionOTPResponse));
		
		return transactionOTPResponse;

	}
	
	/* getTransactionOTPForMobileNumber */
	public String getTransactionOTPForMobileNumber(String mobileNumber) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to Get Transaction OTP with the help GETCODE With Mobile Number ..! *******");
		Log.info("******* About to Get Transaction OTP with the help GETCODE With Mobile Number ..! *******");
		
		// Assigning Values
		int statusCode						= 201;
		String queryParameterPage			= Utils.getRestApiTestData(3, "queryparamOne_key");
		String queryParameterPageValue		= Utils.getRestApiTestData(3, "queryparamOne_value");
		String queryParameterMobile			= Utils.getRestApiTestData(3, "queryparamtwo_key");
		String queryParameterMobileValue	= mobileNumber;
		String baseURL						= FRONTENDURL+Utils.getRestApiTestData(3, "basepath");
		String signUpOTP					= null;
		Response response					= null;
		
		response = getOTP(queryParameterPage, queryParameterPageValue, queryParameterMobile, queryParameterMobileValue, baseURL, statusCode);
		
		signUpOTP = response.jsonPath().getString("message");

		logger.log(LogStatus.PASS, "************ Successfully got Transaction OTP value as  :"+ signUpOTP+"   ************");
		Log.info("************ Successfully got Transaction OTP value as  :"+ signUpOTP+"   ************");
		
		return signUpOTP;

	}

	/* TransactionOTPUserEndPoint */
	public Response getTransactionOTPResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get TransactionOTP response after not passing header ..! *******");
		Log.info("******* About to get TransactionOTP response after not passing header ..! *******");

		// Assigning Values
		String completedURL			= baseURL+ENDPOINTUSER+OTPFLAGTRANSACTIONOTP;
		Response response			= null;
		
		response = postRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created transactionOTPResponse :  " + response.asString());
		Log.info("************ Successfully created transactionOTPResponse :  " + response.asString());
		
		return response;

	}

	/* getTransactionOTPResponseUserEndPoint */
	public Response getTransactionOTPResponseUserEndPointWithoutPostingData(String baseURL, String authorizationValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get TransactionOTP response  ..! *******");
		Log.info("******* About to get TransactionOTP response ..! *******");

		// Assigning Values
		String completedURL			= baseURL+ENDPOINTUSER+OTPFLAGTRANSACTIONOTP;
		Response response			= null;

		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created transactionOTPResponse :  " + response.asString());
		Log.info("************ Successfully created transactionOTPResponse :  " + response.asString());
		
		return response;

	}
	
	/* getTransactionOTPResponseUserEndPoint */
	public Response getTransactionOTPResponseUserEndPoint(String baseURL, String accessTokenValue, String dataType, String notificationName, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get TransactionOTP response after posting data  ..! *******");
		Log.info("******* About to get TransactionOTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+ENDPOINTUSER+OTPFLAGTRANSACTIONOTP;
		String postData				= null;
		Response response			= null;

		postData = postDataForTransactionOTP(dataType, notificationName);
		response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created transactionOTPResponse :  " + response.asString());
		Log.info("************ Successfully created transactionOTPResponse :  " + response.asString());
		
		return response;

	}

	/* validateTransactionOTP Sucess Response HelperEndPoint */
	public void validateTransactionOTPSucessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String dataType, String dataID, String otpExpiresInSeconds, String dataMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP Sucess Response ******* ..!");
		Log.info("******* About to validate TransactionOTP Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, dataType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, dataID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(otpExpireSeconds, otpExpiresInSeconds, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, dataMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP Sucess Response ************");
		Log.info("************Successfully validated TransactionOTP Sucess Response ************");

	}

	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPInvalidAcceptErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}
	
	/* validateTransactionOTP Error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}

	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorTitle) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}
	
	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}

	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}

	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}

	/* validateTransactionOTP error Response UserEndPoint */
	public void validateTransactionOTPErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTP error Response ******* ..!");
		Log.info("******* About to validate TransactionOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTP error Response ************");
		Log.info("************Successfully validated TransactionOTP error Response ************");

	}

	
}