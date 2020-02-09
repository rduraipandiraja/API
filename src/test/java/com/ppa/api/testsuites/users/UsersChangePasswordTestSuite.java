package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.UserLogin;
import com.ppa.api.endpoints.helper.UserSignUp;
import com.ppa.api.endpoints.users.UsersChangePassword;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UsersChangePasswordTestSuite extends Base {

	@Test(priority=0)
	public void validateTypeMandatoryFieldUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(5, "password");
		String newPassword				= Utils.getRestApiTestData(5, "password");
		String confirmPassword			= Utils.getRestApiTestData(5, "password");
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_valid_type");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_valid_type");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		typeUser	= Utils.getRestApiTestData(8, "emptyValue");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validatecurrentPasswordMandatoryFieldUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(8, "emptyValue");
		String newPassword				= Utils.getRestApiTestData(5, "password");
		String confirmPassword			= Utils.getRestApiTestData(5, "password");
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_current_password_required");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_current_password_required");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateNewPasswordMandatoryFieldUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(5, "password");
		String newPassword				= Utils.getRestApiTestData(8, "emptyValue");
		String confirmPassword			= Utils.getRestApiTestData(5, "password");
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_new_password_required");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_new_password_required");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateConfirmPasswordMandatoryFieldUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(5, "password");
		String newPassword				= Utils.getRestApiTestData(5, "password");
		String confirmPassword			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_confirm_password_required");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_confirm_password_required");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=4)
	public void validateUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		UserLogin objUserLogin = new UserLogin(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(5, "password");
		String newPassword				= Utils.generateRandomAlphabets(6);
		String confirmPassword			= newPassword;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Getting Access Token for Helper end point - GET Request
		helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		// User Login - POST Request
		Response userLoginResponse = objUserLogin.newUserLoginHelperEndPoint(baseURL, accessTokenValue, typeAuth, email, newPassword, deviceType, statusCode200);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserLogin.validateUserLoginSucessResponseHelperEndPoint(objSoftAssertion, userLoginResponse, type, fullName);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	@Test(priority=5)
	public void validateBVAForCurrentPasswordWithMinimumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateBVAForCurrentPasswordWithMinimumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.generateRandomAlphabets(5);
		String newPassword				= password;
		String confirmPassword			= password;
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_current_password_atlease_6_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_current_password_atlease_6_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateBVAForCurrentPasswordWithMinimumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(7);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateBVAForCurrentPasswordWithMaximumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(20);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateBVAForCurrentPasswordWithMaximumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(19);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateBVAForCurrentPasswordWithMaximumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.generateRandomAlphabets(21);
		String newPassword				= password;
		String confirmPassword			= password;
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_current_password_greater_20_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_current_password_greater_20_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=11)
	public void validateBVAForNewPasswordWithMinimumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateBVAForNewPasswordWithMinimumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= Utils.generateRandomAlphabets(5);
		String confirmPassword			= password;
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_new_password_atlease_6_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_new_password_atlease_6_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateBVAForNewPasswordWithMinimumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(7);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateBVAForNewPasswordWithMaximumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(20);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=15)
	public void validateBVAForNewPasswordWithMaximumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(19);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=16)
	public void validateBVAForNewPasswordWithMaximumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= Utils.generateRandomAlphabets(21);
		String confirmPassword			= password;
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_new_password_greater_20_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_new_password_greater_20_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=17)
	public void validateBVAForConfirmPasswordWithMinimumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=18)
	public void validateBVAForConfirmPasswordWithMinimumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= Utils.generateRandomAlphabets(5);
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_confirm_password_atlease_6_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_confirm_password_atlease_6_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=19)
	public void validateBVAForConfirmPasswordWithMinimumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(7);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=20)
	public void validateBVAForConfirmPasswordWithMaximumCharactersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(20);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=21)
	public void validateBVAForConfirmPasswordWithMaximumCharactersMinusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(19);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= password;
		String message					= Utils.getRestApiTestData(23, "message");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=22)
	public void validateBVAForConfirmPasswordWithMaximumCharactersPlusOneUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.generateRandomAlphabets(6);
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= password;
		String confirmPassword			= Utils.generateRandomAlphabets(21);
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_confirm_password_greater_20_char");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_confirm_password_greater_20_char");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=23)
	public void validateInvalidPasswordUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.generateRandomAlphabets(6);
		String newPassword				= password;
		String confirmPassword			= password;
		String errorStatus				= Utils.getRestApiTestData(23, "status_400");
		String errorCode				= Utils.getRestApiTestData(23, "code_4000");
		String errorTitle				= Utils.getRestApiTestData(23, "title_password_doesnot_match");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_password_doesnot_match");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_password_doesnot_match");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode400);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint_(objSoftAssertion, changePasswordResponse, errorStatus, errorCode, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=24)
	public void validateWrongPasswordUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= password;
		String newPassword				= Utils.generateRandomAlphabets(6);
		String confirmPassword			= Utils.generateRandomAlphabets(6);
		String errorStatus				= Utils.getRestApiTestData(23, "status_422");
		String errorTitle				= Utils.getRestApiTestData(23, "title_invalid_attribute");
		String errorDetail				= Utils.getRestApiTestData(23, "detail_new_and_confirm_password_match");
		String errorPointer				= Utils.getRestApiTestData(23, "pointer_new_and_confirm_password_match");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode422);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	
	@Test(priority=25)
	public void validateContentTypeResponseForUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
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
		String typeUser					= Utils.getRestApiTestData(23, "type");
		String currentPassword			= Utils.getRestApiTestData(5, "password");
		String newPassword				= Utils.generateRandomAlphabets(6);
		String confirmPassword			= newPassword;
		String message					= Utils.getRestApiTestData(23, "message");
		String contentType				= Utils.getRestApiTestData(0, "accept_value");

		// Getting Access Token for Helper end point - GET Request
		Response helperAccessTokenResponse		= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200);       
		String  accessTokenValue		= objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		// New User Signup - POST Request
		Response userSignUpResponse = objUserSignUp.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, referralID, deviceType, ipAddress, statusCode201);
		
		// Validate SignupV1WithOTP success response - POST Request
		objUserSignUp.validateUserSignUpSucessResponseHelperEndPoint(objSoftAssertion, userSignUpResponse, type, fullName);
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		int userid = objUserSignUp.getUserID(userSignUpResponse);
		String userID = Integer.toString(userid);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, typeUser, currentPassword, newPassword, confirmPassword, statusCode200);
		
		objUsersChangePassword.validateUsersChangePasswordSuccessResponseUserEndPoint(objSoftAssertion, changePasswordResponse, typeUser, userID, message);
		
		objRestutils.validateReponseContentType(objSoftAssertion, changePasswordResponse, contentType);

		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}


	
	@Test(priority=26)
	public void validateEmptyHeaderUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, statusCode401);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=27)
	public void validateInvalidAuthorizationKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	//@Test(priority=28)
	public void validateInvalidContentTypeKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objUsersChangePassword.validateUsersChangePasswordInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=29)
	public void validateInvalidAcceptTypeKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objUsersChangePassword.validateUsersChangePasswordInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=30)
	public void validateEmptyAuthorizationKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode401);
		
		objUsersChangePassword.validateUsersChangePasswordErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	@Test(priority=31)
	public void validateEmptyAcceptTypeKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode406);
		
		objUsersChangePassword.validateUsersChangePasswordInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	//@Test(priority=32)
	public void validateEmptyContentTypeKeyValueUsersChangePasswordEndpointDeviceTypeDesktop() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		UserSignUp objUserSignUp = new UserSignUp(logger);
		UsersChangePassword objUsersChangePassword = new UsersChangePassword(logger);
		
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
		
		accessTokenValue = objUserSignUp.getAccessToken(userSignUpResponse);
		
		// Getting Refresh Token from refresh token end point - POST Request
		Response changePasswordResponse = objUsersChangePassword.getUsersChangePasswordResponseUserEndPoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, statusCode415);
		
		objUsersChangePassword.validateUsersChangePasswordInvalidAcceptErrorResponseUserEndPoint(objSoftAssertion, changePasswordResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

}