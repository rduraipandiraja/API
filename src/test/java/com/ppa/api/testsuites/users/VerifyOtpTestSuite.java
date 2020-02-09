package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.ResendOTP;
import com.ppa.api.endpoints.users.VerifyOTP;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class VerifyOtpTestSuite extends Base {
	
	@Test(priority=0)
	public void validateVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String mesage					= Utils.getRestApiTestData(26, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode200);
		
		objVerifyOTP.validateVerifyOTPSucessResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, typeUserOTP, userID, mesage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateVerifyOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String errorCode				= Utils.getRestApiTestData(24, "code_11007");
		String errorTitle				= Utils.getRestApiTestData(24, "title_write_to_ck");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode400);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorCode, errorTitle);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=2)
	public void validateVerifyOTPAfterResendingOtpEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		ResendOTP objResendOTP = new ResendOTP(logger);
		
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
		String mesage					= Utils.getRestApiTestData(26, "message");
		String mesageResendOTP			= Utils.getRestApiTestData(25, "message");
		String errorStatus				= Utils.getRestApiTestData(10, "guid_status");
		String errorCode				= Utils.getRestApiTestData(10, "guid_code");
		String errorTitle				= Utils.getRestApiTestData(10, "guid_title");
		String errorDetail				= Utils.getRestApiTestData(10, "guid_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "guid_parameter");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response resendOTPResponse = objResendOTP.ResendOtpResponseUserEndPoint(baseURL, accessTokenValue, statusCode200);
		
		objResendOTP.validateResendOtpSuccessResponseUserEndPoint(objSoftAssertion, resendOTPResponse, typeUserOTP, userID, mesageResendOTP);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode400);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		// Get OTP for mobile number - GET Request
		signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);
		
		verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode200);
		
		objVerifyOTP.validateVerifyOTPSucessResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, typeUserOTP, userID, mesage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateOTPExpiryVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "otp_status");
		String errorCode				= Utils.getRestApiTestData(10, "otp_code");
		String errorTitle				= Utils.getRestApiTestData(10, "otp_title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail");
		String errorParameter			= Utils.getRestApiTestData(10, "otp_parameter");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);

		Thread.sleep(181000);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode400);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=4)
	public void validateTypeMandatoryVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String typeUserOTP				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode422);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateOTPMandatoryVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		// Reassigning values
		signUpOTP					= Utils.getRestApiTestData(8, "double_quote");

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode422);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=5)
	public void validateContentTypeResponseVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
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
		String mesage					= Utils.getRestApiTestData(26, "message");
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode200);
		
		objVerifyOTP.validateVerifyOTPSucessResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, typeUserOTP, userID, mesage);

		objRestutils.validateReponseContentType(objSoftAssertion, verifyOTPResponse, contentType);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=6)
	public void validateBVAForOTPMaximumVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String mesage					= Utils.getRestApiTestData(26, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode200);
		
		objVerifyOTP.validateVerifyOTPSucessResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, typeUserOTP, userID, mesage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateBVAForOTPMaximumminusOneVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		// Reassigning values
		signUpOTP				= Utils.generateRandomNumber(5);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode422);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateBVAForOTPMaximumPlusOneVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "otp_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "otp_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		// Get OTP for mobile number - GET Request
		String signUpOTP				= objVerifyOTP.getOTPForMobileNumber(mobileNumber);

		// Reassigning values
		signUpOTP				= Utils.generateRandomNumber(7);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, accessTokenValue, typeUserOTP, signUpOTP, statusCode422);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=9)
	public void validateEmptyHeaderVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOTPResponseUserEndPoint(baseURL, statusCode401);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateInvalidAuthorizationKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateInvalidContentTypeKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objVerifyOTP.validateVerifyOTPInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateInvalidAcceptTypeKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objVerifyOTP.validateVerifyOTPInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateEmptyAuthorizationKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String type						= Utils.getRestApiTestData(10, "type");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objVerifyOTP.validateVerifyOTPErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateEmptyAcceptTypeKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objVerifyOTP.validateVerifyOTPInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateEmptyContentTypeKeyValueVerifyOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		VerifyOTP objVerifyOTP = new VerifyOTP(logger);
		
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
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response verifyOTPResponse = objVerifyOTP.getVerifyOtpResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objVerifyOTP.validateVerifyOTPInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, verifyOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
}