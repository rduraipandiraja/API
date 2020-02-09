package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.CreateNewOtp;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateNewOtpTestSuite extends Base {

	/**
	 * 
	 * Boundary value analysis for mobile number by posting mobile number having 10 digits and validating 201 status code with its response
	 *	
	 */	
	@Test(priority=0, description="Boundary value analysis for mobile number by posting mobile number having 10 digits and validating 201 status code with its response")
	public void validateBVAForMobileNumberWithMaximumCharactersCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		String message					= Utils.getRestApiTestData(24, "new_otp_generated");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		objCreateNewOtp.validateCreateNewOtpSucessResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, typeUserOTP, userID, message);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Boundary value analysis for mobile number by posting mobile number having 9 digits and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=1, description="Boundary value analysis for mobile number by posting mobile number having 9 digits and validating 422 status code with its response")
	public void validateBVAForMobileNumberWithMaximumCharactersMinusOneCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);

		mobileNumber				= Utils.generateMobileNumber(9);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Boundary value analysis for mobile number by posting mobile number having 11 digits and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=2, description="Boundary value analysis for mobile number by posting mobile number having 11 digits and validating 422 status code with its response")
	public void validateBVAForMobileNumberWithMaximumCharactersPlusOneCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);

		mobileNumber				= Utils.generateMobileNumber(11);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing empty string for mobile number in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=3, description="Passing empty string for mobile number and validating 422 status code with its response")
	public void validateMobileNumberMandatoryFieldCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);

		mobileNumber			= Utils.getRestApiTestData(8, "double_quote");
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing existing mobile number in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=4, description="Passing existing mobile number and validating 422 status code with its response")
	public void validateExistingMobileNumberFieldCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String errorStatus				= Utils.getRestApiTestData(24, "mobilenumber_status_existing");
		String errorCode				= Utils.getRestApiTestData(24, "mobilenumber_code_existing");
		String errorTitle				= Utils.getRestApiTestData(24, "mobilenumber_title_existing");
		String errorDetail				= Utils.getRestApiTestData(24, "mobilenumber_detail_existing");
		String errorPointer				= Utils.getRestApiTestData(24, "mobilenumber_pointer_existing");

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

		accessTokenValue		= objUserSignUp.getAccessToken(signUpV1WithOTPResponse);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint_(objSoftAssertion, createNewOtpResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing invalid mobile number digits starting with 0, 1, 2, 3, 4 & 5 and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=5, description="Passing invalid mobile number digits starting with 0, 1, 2, 3, 4 & 5 and validating 422 status code with its response")
	public void validateInvalidMobileNumberFormatCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_invalid");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		mobileNumber			= "5" + Utils.generateMobileNumber(9);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber			= "4" + Utils.generateMobileNumber(9);
		
		createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber			= "3" + Utils.generateMobileNumber(9);
		
		createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber			= "2" + Utils.generateMobileNumber(9);
		
		createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber			= "1" + Utils.generateMobileNumber(9);
		
		createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing empty string for type in post data and validating 422 status code with its response
	 *	
	 */	
	@Test(priority=6, description="Passing empty string for type in post data and validating 422 status code with its response")
	public void validateTypeMandatoryFieldCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		String typeUserOTP				= Utils.getRestApiTestData(8, "emptyValue");
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
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

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Validating 201 status code and response value for content type key
	 *	
	 */	
	@Test(priority=7, description="Validating 201 status code and response value for content type key")
	public void validateResponseHeaderFieldCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
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
		String contentType				= Utils.getRestApiTestData(0, "accept_value");
		String message					= Utils.getRestApiTestData(24, "new_otp_generated");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		objCreateNewOtp.validateCreateNewOtpSucessResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, typeUserOTP, userID, message);

		objRestutils.validateReponseContentType(objSoftAssertion, createNewOtpResponse, contentType);

		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Validating 201 status code its response after passing desktop domain base URI
	 *	
	 */	
	@Test(priority=8, description="Validating 201 status code its response after passing desktop domain base URI")
	public void validateCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		String message					= Utils.getRestApiTestData(24, "new_otp_generated");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Get Fullname for new user
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
		
		objCreateNewOtp.validateCreateNewOtpSucessResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, typeUserOTP, userID, message);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Validating 400 status code its response after passing app domain base URI
	 *	
	 */	
	@Test(priority=9, description="Validating 400 status code its response after passing app domain base URI")
	public void validateCreateNewOTPEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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

		accessTokenValue		= objUserSignUp.getAccessToken(userSignUpResponse);
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode400);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorCode, errorTitle);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing empty header and validating 401 status code with its response
	 *
	 */
	@Test(priority=10, description="Passing empty header and validating 401 status code with its response")
	public void validateEmptyHeaderCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOtpUserEndPoint(baseURL, statusCode401);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=11, description="Passing invalid auth type and validating 401 status code with its response")
	public void validateInvalidAuthorizationKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=12, description="Passing invalid content type and validating 415 status code with its response")
	public void validateInvalidContentTypeKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objCreateNewOtp.validateCreateNewOtpInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid accept type and validating 415 status code with its response
	 *
	 */
	@Test(priority=13, description="Passing invalid accept type and validating 415 status code with its response")
	public void validateInvalidAcceptTypeKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objCreateNewOtp.validateCreateNewOtpInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=14, description="Passing empty string auth type and validating 401 status code with its response")
	public void validateEmptyAuthorizationKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objCreateNewOtp.validateCreateNewOtpErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	/**
	 * 
	 * Passing empty string accept type and validating 406 status code with its response
	 *
	 */
	@Test(priority=15, description="Passing empty string accept type and validating 406 status code with its response")
	public void validateEmptyAcceptTypeKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objCreateNewOtp.validateCreateNewOtpInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=16, description="Passing empty string content type and validating 415 status code with its response")
	public void validateEmptyContentTypeKeyValueCreateNewOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		CreateNewOtp objCreateNewOtp = new CreateNewOtp(logger);
		
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
		
		Response createNewOtpResponse = objCreateNewOtp.newCreateNewOTPUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objCreateNewOtp.validateCreateNewOtpInvalidHeaderErrorResponseUserEndPoint(objSoftAssertion, createNewOtpResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}