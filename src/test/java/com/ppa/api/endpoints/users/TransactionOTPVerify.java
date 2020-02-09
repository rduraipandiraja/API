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

public class TransactionOTPVerify extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public TransactionOTPVerify(ExtentTest logger) {

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
	public static final String EndPointTransactionOTPVerify;
	public static final String FrontEndURL;
	public static final String OtpFlag;

	/********************************************************** Gpath Creation started ***************************************************************/

	String type									= "data.type";                              
	String id									= "data.id";                               
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
		
		AuthorizationKey				= Utils.getRestApiTestData(0, "auth_Key");
		AcceptKey						= Utils.getRestApiTestData(0, "accept_key");
		AcceptValue						= Utils.getRestApiTestData(0, "accept_value");
		ContentTypeKey					= Utils.getRestApiTestData(0, "content_type_key");
		ContentTypeValue				= Utils.getRestApiTestData(0, "content_type_value");
		EndPointUser					= Utils.getRestApiTestData(2, "users");
		EndPointTransactionOTPVerify	= Utils.getRestApiTestData(2, "transaction_otp_verify");
		OtpFlag							= ConfigurationSetup.OTPFLAG;
		FrontEndURL						= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* SignUpOTPPostData */
	public String postDataForTransactionOTPVerify(String type, String otpGUID, String otp) {
		
		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated otp GUID : " +otpGUID.trim());
		logger.log(LogStatus.INFO, "Generated otp : " +otp.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated otp guid : " +otpGUID.trim());
		Log.info("Generated otp : " +otp.trim());

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"otp_guid\": \""+otpGUID+"\",\r\n" + 
							"      \"otp\":"+otp+"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}\r\n" + 
							"";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* getOTPForMobileNumber */
	public String getOTPForMobileNumber(String mobileNumber) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to Get OTP with the help GETCODE With Mobile Number ..! *******");
		Log.info("******* About to Get OTP with the help GETCODE With Mobile Number ..! *******");
		
		// Assigning Values
		int statusCode						= 201;
		String queryParameterPage			= Utils.getRestApiTestData(3, "queryparamOne_key");
		String queryParameterPageValue		= Utils.getRestApiTestData(3, "queryparamOne_value");
		String queryParameterMobile			= Utils.getRestApiTestData(3, "queryparamtwo_key");
		String queryParameterMobileValue	= mobileNumber;
		String baseURL						= FrontEndURL+Utils.getRestApiTestData(3, "basepath");
		String signUpOTP					= null;
		Response response					= null;
		
		response = getOTP(queryParameterPage, queryParameterPageValue, queryParameterMobile, queryParameterMobileValue, baseURL, statusCode);
		
		signUpOTP = response.jsonPath().getString("message");

		logger.log(LogStatus.PASS, "************ Successfully got OTP value as  :"+ signUpOTP+"   ************");
		Log.info("************ Successfully got OTP value as  :"+ signUpOTP+"   ************");
		
		return signUpOTP;

	}

	/* TransactionOTPVerifyUserEndPoint */
	public Response TransactionOTPVerifyUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after not passing header ..! *******");
		Log.info("******* About to get sign up OTP response after not passing header ..! *******");

		// Assigning Values
		String completedURL			= baseURL+EndPointUser+EndPointTransactionOTPVerify;
		Response response			= null;
		
		response = postRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
		Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
		
		return response;

	}

	/* getTransactionOTPVerifyResponseUserEndPoint */
	public Response getTransactionOTPVerifyResponseUserEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response  ..! *******");
		Log.info("******* About to get sign up OTP response  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+EndPointUser+EndPointTransactionOTPVerify;
		Response response			= null;

		response = postRequestSpecification(completedURL, AuthorizationKey, authorizationValue, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
		Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
		
		return response;

	}
	
	/* getTransactionOTPVerifyResponseUserEndPoint */
	public Response getTransactionOTPVerifyResponseUserEndPoint(String baseURL, String accessTokenValue, String dataType, String otpGUID, String otp, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+EndPointUser+EndPointTransactionOTPVerify;
		String postData				= null;
		Response response			= null;

		postData = postDataForTransactionOTPVerify(dataType, otpGUID, otp);
		response = postRequestSpecification(completedURL, AuthorizationKey, authorizationValue, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
		Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
		
		return response;

	}

	/* validateUserSignUp Sucess Response HelperEndPoint */
	public void validateTransactionOTPVerifySucessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String dataType, String dataID, String dataMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp Sucess Response ******* ..!");
		Log.info("******* About to validate UserSignUp Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, dataType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, dataID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, dataMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp Sucess Response ************");
		Log.info("************Successfully validated UserSignUp Sucess Response ************");

	}

	/* validateTransactionOTPVerify Error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorTitle) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyInvalidAcceptErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}
	
	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	/* validateTransactionOTPVerify error Response UserEndPoint */
	public void validateTransactionOTPVerifyErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate TransactionOTPVerify error Response ******* ..!");
		Log.info("******* About to validate TransactionOTPVerify error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated TransactionOTPVerify error Response ************");
		Log.info("************Successfully validated TransactionOTPVerify error Response ************");

	}

	
}