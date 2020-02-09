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

public class VerifyOTP extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public VerifyOTP(ExtentTest logger) {

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
	public static final String EndPointVerifyOTP;
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
		
		AuthorizationKey	= Utils.getRestApiTestData(0, "auth_Key");
		AcceptKey			= Utils.getRestApiTestData(0, "accept_key");
		AcceptValue			= Utils.getRestApiTestData(0, "accept_value");
		ContentTypeKey		= Utils.getRestApiTestData(0, "content_type_key");
		ContentTypeValue	= Utils.getRestApiTestData(0, "content_type_value");
		EndPointUser		= Utils.getRestApiTestData(2, "users");
		EndPointVerifyOTP	= Utils.getRestApiTestData(2, "verify_otp");
		OtpFlag				= ConfigurationSetup.OTPFLAG;
		FrontEndURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* SignUpOTPPostData */
	public String postDataForVerifyOTP(String type, String otp) {
		
		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated otp : " +otp.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated otp : " +otp.trim());

		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");

		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"otp\": "+otp+"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

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
		String emptyValue					= Utils.getRestApiTestData(8, "emptyValue");
		
		// OTP enabled based on conditions
		switch (OtpFlag) {
		case "enable":
			
			response = getOTP(queryParameterPage, queryParameterPageValue, queryParameterMobile, queryParameterMobileValue, baseURL, statusCode);
			
			signUpOTP = response.jsonPath().getString("message");

			logger.log(LogStatus.PASS, "************ Successfully got OTP value as  :"+ signUpOTP+"   ************");
			Log.info("************ Successfully got OTP value as  :"+ signUpOTP+"   ************");
			
			break;
			
		case "disable":

			signUpOTP = emptyValue;

			logger.log(LogStatus.PASS, "************ signUpOTP not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ signUpOTP not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
			
		}
		
		return signUpOTP;

	}
	
	/* verifyOTPUserEndPoint */
	public Response getVerifyOTPResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after not passing header ..! *******");
		Log.info("******* About to get sign up OTP response after not passing header ..! *******");

		// Assigning Values
		String completedURL			= baseURL+EndPointUser+EndPointVerifyOTP;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OtpFlag) {
		case "enable":
			
			response = postRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());
			
			break;
			
		case "disable":
						
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}
		
		return response;

	}

	/* getVerifyOTPResponseUserEndPoint */
	public Response getVerifyOtpResponseUserEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+EndPointUser+EndPointVerifyOTP;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OtpFlag) {
		case "enable":

			response = postRequestSpecification(completedURL, AuthorizationKey, authorizationValue, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());

			break;

		case "disable":

			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);

			break;
			
		}
		
		return response;

	}
	
	/* getVerifyOTPResponseUserEndPoint */
	public Response getVerifyOTPResponseUserEndPoint(String baseURL, String accessTokenValue, String dataType, String otp, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to get sign up OTP response after posting data  ..! *******");
		Log.info("******* About to get sign up OTP response after posting data  ..! *******");

		// Assigning Values
		String authorizationValue	= accessTokenValue;
		String completedURL			= baseURL+EndPointUser+EndPointVerifyOTP;
		String postData				= null;
		Response response			= null;
		
		// OTP enabled based on conditions
		switch (OtpFlag) {
		case "enable":

			postData = postDataForVerifyOTP(dataType, otp);
			response = postRequestSpecification(completedURL, AuthorizationKey, authorizationValue, AcceptKey, AcceptValue, ContentTypeKey, ContentTypeValue, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created signupOTPResponse :  " + response.asString());
			Log.info("************ Successfully created signupOTPResponse :  " + response.asString());

			break;

		case "disable":

			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);

			break;
			
		}
		
		return response;

	}

	/* validateUserSignUp Sucess Response HelperEndPoint */
	public void validateVerifyOTPSucessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String dataType, String dataID, String dataMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate UserSignUp Sucess Response ******* ..!");
		Log.info("******* About to validate UserSignUp Sucess Response ******* ..!");
		
		
		assertionContainsWithoutUsingSubstring(type, dataType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, dataID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, dataMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated UserSignUp Sucess Response ************");
		Log.info("************Successfully validated UserSignUp Sucess Response ************");

	}

	/* validateVerifyOTP Error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorCode, String errorTitle) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPInvalidHeaderErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}
	
	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	/* validateVerifyOTP error Response UserEndPoint */
	public void validateVerifyOTPErrorResponseUserEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate VerifyOTP error Response ******* ..!");
		Log.info("******* About to validate VerifyOTP error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated VerifyOTP error Response ************");
		Log.info("************Successfully validated VerifyOTP error Response ************");

	}

	
}