package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class SignUpOTPTestSuite extends Base {
	
	@Test(priority=0)
	public void validateBVAForMobileNumberWithMaximumCharactersSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateBVAForMobileNumberWithMaximumCharactersMinusOneSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(11);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateBVAForMobileNumberWithMaximumCharactersPlusOneSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(9);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_least_char");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=3)
	public void validateMobileNumberMandatoryFieldSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.getRestApiTestData(8, "double_quote");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=4)
	public void validateExistingMobileNumberFieldSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		
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
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_existing");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

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

		// Get GUID for mobile number - POST Request
		Response signupOTPResponse			= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
		
		// Validate SignupV1WithOTP success response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signupOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=5)
	public void validateInvalidMobileNumberFormatSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= "5" + Utils.generateMobileNumber(9);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "mobilenumber_detail_invalid");
		String errorPointer				= Utils.getRestApiTestData(10, "mobilenumber_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber				= "4" + Utils.generateMobileNumber(9);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber				= "3" + Utils.generateMobileNumber(9);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber				= "2" + Utils.generateMobileNumber(9);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		mobileNumber				= "1" + Utils.generateMobileNumber(9);
		
		// Get GUID for mobile number - POST Request
		signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
	
	
	@Test(priority=6)
	public void validateTypeMandatoryFieldSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(8, "emptyValue");
		String mobileNumber				= Utils.getRestApiTestData(8, "double_quote");
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse		= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode422);
	
		// Validate SignupV1WithOTP error response - POST Request
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}
			
	
	@Test(priority=7)
	public void validateResponseHeaderFieldSignUpOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		Restutils objRestutils = new Restutils(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// Get GUID for mobile number - POST Request
		Response response				= objSignUpOTP.getGUIDEntireJSONResponseHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		objRestutils.validateReponseContentType(objSoftAssertion, response, contentType);

		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=8)
	public void validateEmptyHeaderSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, statusCode401);
		
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateInvalidAuthorizationKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateInvalidContentTypeKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objSignUpOTP.validateSignUpOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateInvalidAcceptTypeKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objSignUpOTP.validateSignUpOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateEmptyAuthorizationKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// Getting Access Token for Helper end point - GET Request
		objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);    
		
		String accessTokenValue		= Utils.getRestApiTestData(8, "emptyValue");
		
		// Get GUID for mobile number - POST Request
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objSignUpOTP.validateSignUpOTPErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorMessage);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateEmptyAcceptTypeKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objSignUpOTP.validateSignUpOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateEmptyContentTypeKeyValueSignupWithOTPEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
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
		Response signUpOTPResponse = objSignUpOTP.getGUIDEntireJsonResponseHelperEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objSignUpOTP.validateSignUpOTPInvalidHeaderErrorResponseHelperEndPoint(objSoftAssertion, signUpOTPResponse, errorLinks, errorStatus, errorTitle, errorDetail);
			
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}