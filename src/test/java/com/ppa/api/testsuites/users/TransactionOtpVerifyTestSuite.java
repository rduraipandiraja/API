package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.TransactionOTP;
import com.ppa.api.endpoints.users.TransactionOTPVerify;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TransactionOtpVerifyTestSuite extends Base {
	
	@Test(priority=0)
	public void validateTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String message					= Utils.getRestApiTestData(28, "success_message");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode200);
		
		objTransactionOTPVerify.validateTransactionOTPVerifySucessResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, typeUserOTP, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=1)
	public void validateOTPExpiryTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "otp_status");
		String errorCode				= Utils.getRestApiTestData(10, "otp_code");
		String errorTitle				= Utils.getRestApiTestData(10, "otp_title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "otp_parameter");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;
		
		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Thread.sleep(181000);

		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode400);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=2)
	public void validateTyepMandatoryTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		typeUserOTP				= Utils.getRestApiTestData(8, "emptyValue");
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode422);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateOTPGUIDMandatoryTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "guid_pointer");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);
		
		// Assigning signUpOTP response values to variables
		guid						= Utils.getRestApiTestData(8, "emptyValue");

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode422);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateOTPMandatoryTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= Utils.getRestApiTestData(8, "double_quote");
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode422);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=5)
	public void validateBVAForOTPMaximumTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String message					= Utils.getRestApiTestData(28, "success_message");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode200);
		
		objTransactionOTPVerify.validateTransactionOTPVerifySucessResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, typeUserOTP, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateBVAForOTPMaximumMinusOneTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= Utils.generateRandomNumber(5);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode422);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateBVAForOTPMaximumPlusOneTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= Utils.generateRandomNumber(7);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode422);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=8)
	public void validateInvalidOTPGUIDMandatoryTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(28, "otp_guid_status");
		String errorCode				= Utils.getRestApiTestData(28, "otp_guid_code");
		String errorTitle				= Utils.getRestApiTestData(28, "otp_guid_title");
		String errorDetail				= Utils.getRestApiTestData(28, "otp_guid_detail");
		String errorParameter			= Utils.getRestApiTestData(28, "otp_guid_parameter");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= Utils.generateRandomAlphabets(20);

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode400);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=9)
	public void validateSecondGeneratedOTPGuidAndOTPTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String message					= Utils.getRestApiTestData(28, "success_message");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		String userID = Integer.toString(userid);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode200);
		
		objTransactionOTPVerify.validateTransactionOTPVerifySucessResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, typeUserOTP, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateFirstGeneratedGUIDandSecondGeneratedOTPCombinationTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
		String errorStatus				= Utils.getRestApiTestData(10, "guid_status");
		String errorCode				= Utils.getRestApiTestData(10, "guid_code");
		String errorTitle				= Utils.getRestApiTestData(10, "guid_title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "guid_parameter");

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Get OTP for mobile number - GET Request
		transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode400);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=11)
	public void validateContentTypeResponseHeaderFieldTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTP objTransactionOTP = new TransactionOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String notificationName			= Utils.getRestApiTestData(27, "notification_name");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Object[] transactionOTPResponse = objTransactionOTP.getTransactionotpResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, notificationName, statusCode201);

		// Assigning signUpOTP response values to variables
		guid						= (String) transactionOTPResponse[2] ;

		// Get OTP for mobile number - GET Request
		String transactionOTP		= objTransactionOTP.getTransactionOTPForMobileNumber(mobileNumber);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, guid, transactionOTP, statusCode200);
		
		objRestutils.validateReponseContentType(objSoftAssertion, transactionOTPVerifyResponse, contentType);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=12)
	public void validateEmptyHeaderTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.TransactionOTPVerifyUserEndPoint(baseURL, statusCode401);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateInvalidAuthorizationKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateInvalidContentTypeKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateInvalidAcceptTypeKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateEmptyAuthorizationKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		

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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=17)
	public void validateEmptyAcceptTypeKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateEmptyContentTypeKeyValueTransactionalOTPVerifyEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		TransactionOTPVerify objTransactionOTPVerify = new TransactionOTPVerify(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
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
		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue		= objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response transactionOTPVerifyResponse = objTransactionOTPVerify.getTransactionOTPVerifyResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objTransactionOTPVerify.validateTransactionOTPVerifyInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, transactionOTPVerifyResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
}