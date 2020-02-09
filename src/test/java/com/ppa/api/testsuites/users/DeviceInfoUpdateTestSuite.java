package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.DeviceInfoUpdate;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeviceInfoUpdateTestSuite extends Base {

	/**
	 * 
	 * Validating 200 status code with its response after passing app domain as base URI
	 *	
	 */	
	@Test(priority=0, description = "Validating 200 status code its response after passing app domain as base URI")
	public void validateDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(17, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(29, "success_message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateSucessResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for type in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=1, description = "Passing empty string for type in post data and validating 422 status code with its response")
	public void validateTypeMandatoryDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(8, "emptyValue");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode422);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for device info in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=2, description = "Passing empty string for device info in post data and validating 422 status code with its response")
	public void validateDeviceInfoMandatoryDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(17, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorCode				= Utils.getRestApiTestData(10, "code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail");
		String errorPointer				= Utils.getRestApiTestData(10, "deviceinfo_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, deviceType, statusCode422);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint_(objSoftAssertion, deviceInfoUpdateResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid fcm id and invalid unqiue id device info in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=3, description = "Passing invalid fcm id and invalid unqiue id device info in post data and validating 422 status code with its response")
	public void validateInvalidFcmidAndDeviceUniqueIDDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(17, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(10, "deviceinfo_status");
		String errorCode				= Utils.getRestApiTestData(10, "deviceinfo_code");
		String errorTitle				= Utils.getRestApiTestData(10, "deviceinfo_title1");
		String errorDetail				= Utils.getRestApiTestData(10, "deviceinfo_detail1");
		String errorParameter			= Utils.getRestApiTestData(10, "deviceinfo_parameter");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		fcmID					= Utils.getRestApiTestData(8, "emptyValue");
		deviceUniqueID			= Utils.getRestApiTestData(8, "emptyValue");
		imeiNumber				= Utils.getRestApiTestData(8, "emptyValue");
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode422);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	//@Test(priority=4)
	public void validateDeviceInfoUpdateEndpointInvalidDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(17, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		deviceType				= Utils.getRestApiTestData(4, "invalid_desktop");
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty header and validating 401 status code with its response
	 *
	 */
	@Test(priority=5, description = "Passing empty header and validating 401 status code with its response")
	public void validateEmptyHeaderDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, deviceType, statusCode401);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=6, description = "Passing invalid auth type and validating 401 status code with its response")
	public void validateInvalidAuthorizationKeyValueDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=7, description = "Passing invalid content type and validating 415 status code with its response")
	public void validateInvalidAcceptTypeKeyValueDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=8, description = "Passing empty string auth type and validating 401 status code with its response")
	public void validateEmptyAuthorizationKeyValueDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string accept type and validating 406 status code with its response
	 *
	 */
	@Test(priority=9, description = "Passing empty string accept type and validating 406 status code with its response")
	public void validateEmptyAcceptTypeKeyValueDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode406);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Validating 201 status code and response value for content type key
	 *	
	 */	
	@Test(priority=10, description = "Validating 201 status code and response value for content type key")
	public void validateContentTypeResponseDeviceInfoUpdateEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		DeviceInfoUpdate objDeviceInfoUpdate = new DeviceInfoUpdate(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(17, "type");
		String fcmID					= Utils.generateRandomAlphabets(15);
		String deviceUniqueID			= Utils.generateRandomAlphabets(15);
		String imeiNumber				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(29, "success_message");
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Object[] signUpOTPResponse		= objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		// Assigning signUpOTP response values to variables
		String guid						= (String) signUpOTPResponse[1] ;

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		// New User Signup - POST Request
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response deviceInfoUpdateResponse = objDeviceInfoUpdate.deviceInfoUpdateUserEndPoint(baseURL, accessTokenValue, typeUser, fcmID, deviceUniqueID, imeiNumber, deviceType, statusCode200);
		
		objDeviceInfoUpdate.validateDeviceInfoUpdateSucessResponseHelperEndPoint(objSoftAssertion, deviceInfoUpdateResponse, typeUser, userID, message);

		objRestutils.validateReponseContentType(objSoftAssertion, signUpV1WithOTPResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}