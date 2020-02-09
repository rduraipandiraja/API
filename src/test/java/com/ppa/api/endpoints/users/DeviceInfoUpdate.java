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

public class DeviceInfoUpdate extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public DeviceInfoUpdate(ExtentTest logger) {

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
	public static final String ENDPOINTDEVICEINFO;
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
	String message								= "meta.message";                                                       
	String mesage								= "message";                                  
	String links								= "errors.links.about";    
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTDEVICEINFO	= Utils.getRestApiTestData(2, "device_info");
		OTPFLAG				= ConfigurationSetup.OTPFLAG;
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* PostDataWithDeviceInfoForDeviceInfoUpdate */
	public String PutDataForDeviceInfoUpdate(String type, String fcmID, String deviceUniqueID, String imeiNumber) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated fcmID : " +fcmID.trim());
		logger.log(LogStatus.INFO, "Generated deviceUniqueID : " +deviceUniqueID.trim());
		logger.log(LogStatus.INFO, "Generated imeiNumber : " +imeiNumber.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated fcmID : " +fcmID.trim());
		Log.info("Generated deviceUniqueID : " +deviceUniqueID.trim());
		Log.info("Generated imeiNumber : " +imeiNumber.trim());
		
		logger.log(LogStatus.INFO, "About to Put data for POST request");
		Log.info("About to Put data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"		\"device_info\": {\"fcm_id\": \""+fcmID+"\",\"device_unique_id\": \""+deviceUniqueID+"\",\"imei_number\": \""+imeiNumber+"\"}\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Put data for POST request : " + postData.trim());
		Log.info("Put data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* PostDataWithDeviceInfoForDeviceInfoUpdate */
	public String PutDataForDeviceInfoUpdate(String type) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());

		Log.info("Generated type : " +type.trim());
		
		logger.log(LogStatus.INFO, "About to Put data for POST request");
		Log.info("About to Put data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"		\"device_info\": {}\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Put data for POST request : " + postData.trim());
		Log.info("Put data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* deviceInfoUpdateUserEndPoint */
	public Response deviceInfoUpdateUserEndPoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		Log.info("******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTDEVICEINFO+deviceType;
		Response DeviceInfoUpdateResponse= null;

		switch (OTPFLAG) {
		case "enable":
			
			DeviceInfoUpdateResponse = putRequestSpecification(completedURL, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			Log.info("************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			
			break;
			
		case "disable":
			
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
			
		}

		return DeviceInfoUpdateResponse;

	}

	/* deviceInfoUpdateUserEndPoint */
	public Response deviceInfoUpdateUserEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		Log.info("******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTDEVICEINFO+deviceType;
		Response DeviceInfoUpdateResponse= null;

		switch (OTPFLAG) {
		case "enable":
			
			DeviceInfoUpdateResponse = putRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			Log.info("************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			
			break;
			
		case "disable":

			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
			
		}

		return DeviceInfoUpdateResponse;

	}
	
	/* deviceInfoUpdateUserEndPoint */
	public Response deviceInfoUpdateUserEndPoint(String baseURL, String accessTokenValue, String dataType, String fcmID, String deviceUniqueID, String imeiNumber, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		Log.info("******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue			= accessTokenValue;
		String completedURL					= baseURL+ENDPOINTUSERS+ENDPOINTDEVICEINFO+deviceType;
		Response DeviceInfoUpdateResponse	= null;
		String postData						= null;

		switch (OTPFLAG) {
		case "enable":
			
			postData = this.PutDataForDeviceInfoUpdate(dataType, fcmID, deviceUniqueID, imeiNumber);
			DeviceInfoUpdateResponse = putRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			Log.info("************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			
			break;
			
		case "disable":
			
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
			
		}

		return DeviceInfoUpdateResponse;

	}

	/* deviceInfoUpdateUserEndPoint */
	public Response deviceInfoUpdateUserEndPoint(String baseURL, String accessTokenValue, String dataType, String deviceType, int statusCode) throws InterruptedException, AWTException {

		// Getting the OTP with the help Profile setting GETCODE - With Email & Mobile Number
		logger.log(LogStatus.INFO, "******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		Log.info("******* About to sign up as new user using signupv1 with OTP EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue			= accessTokenValue;
		String completedURL					= baseURL+ENDPOINTUSERS+ENDPOINTDEVICEINFO+deviceType;
		Response DeviceInfoUpdateResponse	= null;
		String postData						= null;

		switch (OTPFLAG) {
		case "enable":
			
			postData = this.PutDataForDeviceInfoUpdate(dataType);
			DeviceInfoUpdateResponse = putRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			Log.info("************ Successfully created DeviceInfoUpdateResponse : " + DeviceInfoUpdateResponse.asString());
			
			break;
			
		case "disable":
			
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
			
		}

		return DeviceInfoUpdateResponse;

	}

	/* validateDeviceInfoUpdate Sucess Response HelperEndPoint */
	public void validateDeviceInfoUpdateSucessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String userType, String userID, String mesage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate Sucess Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate Sucess Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(type, userType, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(id, userID, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(message, mesage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate Sucess Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate Sucess Response ************");

	}

	/* validateDeviceInfoUpdate Error Response HelperEndPoint */
	public void validateDeviceInfoUpdateErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}

	/* validateDeviceInfoUpdate error Response HelperEndPoint */
	public void validateDeviceInfoUpdateInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}
	
	/* validateDeviceInfoUpdate error Response HelperEndPoint */
	public void validateDeviceInfoUpdateErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}

	/* validateDeviceInfoUpdate error Response HelperEndPoint */
	public void validateDeviceInfoUpdateErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}

	/* validateDeviceInfoUpdate error Response HelperEndPoint */
	public void validateDeviceInfoUpdateErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}

	/* validateDeviceInfoUpdate error Response HelperEndPoint */
	public void validateDeviceInfoUpdateErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate DeviceInfoUpdate error Response ******* ..!");
		Log.info("******* About to validate DeviceInfoUpdate error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated DeviceInfoUpdate error Response ************");
		Log.info("************Successfully validated DeviceInfoUpdate error Response ************");

	}
	
}