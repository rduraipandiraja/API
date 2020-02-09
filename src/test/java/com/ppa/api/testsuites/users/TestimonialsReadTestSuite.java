package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.helper.TestimonialsRead;
import com.ppa.api.endpoints.users.WriteTestimonials;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestimonialsReadTestSuite extends Base {

	@Test(priority=0)
	public void validateReadTestimonialsResponseAfterChangingActiveStatusAdminQueryParameterTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(8);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String totalRecords				= Utils.getRestApiTestData(38, "total_records");
		String pageNumber				= Utils.getRestApiTestData(38, "page_number");
		String pageSize					= Utils.getRestApiTestData(38, "page_number");
		String self			 			= baseURL+Utils.getRestApiTestData(2, "testimonials");
		String selfURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"2&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		String queryParameter			= Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		String status					= Utils.getRestApiTestData(38, "status_active"); 
		String title					= Utils.generateRandomAlphabets(10);
		String postedDate				= Utils.currentDateFormat();

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		String ticketID = objWriteTestimonials.replyTicketID(writeTestimonialsResponse);
		
		String emailTwo = Utils.generateNewEmail();
		
		String mobileNumberTwo = Utils.generateMobileNumber(10);

		String fullNameTwo = Utils.generateRandomAlphabets(8);
		
		helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Response testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		int totalRecordsValue = objTestimonialsRead.getTotalRecords(testimonialReadResponse);
		
		signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumberTwo, statusCode201);

		guid = (String) signUpOTPResponse[1] ;

		signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumberTwo);

		signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullNameTwo, emailTwo, password, mobileNumberTwo, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullNameTwo);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		String testimonialsTwo = Utils.generateRandomAlphabets(15);

		String ratingsTwo = Utils.getRestApiTestData(39, "ratings_1");
		
		writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratingsTwo, testimonialsTwo, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		String ticketIDTwo = objWriteTestimonials.replyTicketID(writeTestimonialsResponse);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.changeStatusForTestimonials(email, status, title);
		
		String titleTwo = Utils.generateRandomAlphabets(10);
		
		objAdminCommonFunctions.changeStatusForTestimonialsAfterClickingExpandCollapse(emailTwo, status, titleTwo);

		totalRecords = objTestimonialsRead.incrementTotalRecords(totalRecordsValue, 2);
		
		String lastURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+totalRecords+"&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, totalRecords, pageNumber, pageSize);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, "0", typeTestimonial, ticketIDTwo, ratingsTwo, titleTwo, testimonialsTwo, fullNameTwo, postedDate);
		
		queryParameter = Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"2&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";

		selfURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"2&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		firstURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		lastURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+totalRecords+"&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";

		prevURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		nextURL = self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"3&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";
		
		testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, totalRecords, "2", pageSize);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, "0", typeTestimonial, ticketID, ratings, title, testimonials, fullName, postedDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=1)
	public void validateReadTestimonialsResponseAfterNotChangingActiveStatusAdminQueryParameterTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		WriteTestimonials objWriteTestimonials = new WriteTestimonials(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.generateRandomAlphabets(8);
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeTestimonial			= Utils.getRestApiTestData(39, "testimonial_type");
		String testimonials				= Utils.generateRandomAlphabets(15);
		String message					= Utils.getRestApiTestData(39, "message");
		String ratings					= Utils.getRestApiTestData(39, "ratings_5");
		String totalRecords				= Utils.getRestApiTestData(38, "total_records");
		String pageNumber				= Utils.getRestApiTestData(38, "page_number");
		String pageSize					= Utils.getRestApiTestData(38, "page_number");
		String queryParameter			= Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Response testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		int totalRecordsValue = objTestimonialsRead.getTotalRecords(testimonialReadResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response writeTestimonialsResponse = objWriteTestimonials.getWriteTestimonialsResponseUserEndPoint(baseURL, accessTokenValue, typeTestimonial, ratings, testimonials, ipAddress, statusCode201);
		
		objWriteTestimonials.validateWriteTestimonialsSuccessResponseUserEndPoint(objSoftAssertion, writeTestimonialsResponse, typeTestimonial, message);
		
		totalRecords = objTestimonialsRead.incrementTotalRecords(totalRecordsValue, 0);
		
		helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objTestimonialsRead.validateTestimonialsReadSuccessResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, totalRecords, pageNumber, pageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=2)
	public void validateReadTestimonialsResponseAfterPassingInvalidQueryParameterTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorStatus				= Utils.getRestApiTestData(35, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(35, "error_code_11001");
		String errorTitle				= Utils.getRestApiTestData(35, "error_title_invalid_page_number_size");
		String errorDetail				= Utils.getRestApiTestData(35, "error_detail_invalid_page_number_size");
		String errorParameterPageNumber	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_number");
		String errorParameterPageSize	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_size");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		String queryParameter = Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"number&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"1";
		
		Response testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageNumber);
		
		queryParameter = Utils.getRestApiTestData(35, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(35, "pagesize_query_parameter")+"size";
		
		testimonialReadResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, testimonialReadResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateEmptyHeaderTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response myEarningsResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndPoint(baseURL, statusCode401);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateInvalidAuthorizationKeyValueTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);

		accessTokenValue = Utils.getRestApiTestData(0, "auth_invalid_value");
		
		Response myEarningsResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateInvalidAcceptTypeKeyValueTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Response myEarningsResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateEmptyAuthorizationKeyValueTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		Response myEarningsResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, myEarningsResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateEmptyAcceptTypeKeyValueTestimonialsReadEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		TestimonialsRead objTestimonialsRead = new TestimonialsRead(logger);
		
		// Assigning values
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Response myEarningsResponse = objTestimonialsRead.getTestimonialsReadResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objTestimonialsRead.validateTestimonialsReadErrorResponseHelperEndPoint(objSoftAssertion, myEarningsResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

}