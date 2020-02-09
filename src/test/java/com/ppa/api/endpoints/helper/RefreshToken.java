package com.ppa.api.endpoints.helper;

import java.awt.AWTException;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class RefreshToken extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public RefreshToken(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	

	/****************************************************** Variable Creation started ****************************************************************/

	// Assigning header Values
	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINT;
	public static final String FRONTENDURL;

	/********************************************************** Gpath Creation started ***************************************************************/

	String token							= "data.attributes.token";     
	String accessToken						= "data.attributes.access_token";      
	String about							= "errors.links.about";                                                
	String status							= "errors.status";                              
	String code								= "errors.code";                                                    
	String title							= "errors.title";                                         
	String detail							= "errors.detail";                           
	String pointer							= "errors.source.pointer";                 
	String parameter						= "errors.source.parameter";                
	String message							= "message";                             
	String links							= "errors.links.about";        

	/******************************************************** Methods Creation started ****************************************************************/
	
	static {

		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINT			= Utils.getRestApiTestData(2, "refresh_token");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* postDataForrefreshToken */
	public String postDataForrefreshToken(String type, String refreshToken) {

		logger.log(LogStatus.INFO, "Generated type : " +type.trim());
		logger.log(LogStatus.INFO, "Generated refreshToken : " +refreshToken.trim());

		Log.info("Generated type : " +type.trim());
		Log.info("Generated refreshToken : " +refreshToken.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"      \"refresh_token\":\""+refreshToken+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}
	
	/* refreshTokenHelperEndPoint */
	public Response refreshTokenHelperEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to request access token using refresh token EndPoint ..! *******");
		Log.info("******* About to request access token using refresh token EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINT;
		Response refreshTokenResponse	= null;
		
		refreshTokenResponse = postRequestSpecification(completedURL, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());
		Log.info("************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());

		return refreshTokenResponse;

	}

	/* refreshTokenHelperEndPoint */
	public Response refreshtokenHelperEndPoint(String baseURL, String accessTokenValue, String AcceptValue, String ContentTypeValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to Request access token using refreshToken EndPoint ..! *******");
		Log.info("******* About to Request access token using refreshToken EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT;
		Response refreshTokenResponse	= null;

		refreshTokenResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, ContentTypeValue, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());
		Log.info("************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());

		return refreshTokenResponse;

	}
	
	/* refreshTokenHelperEndPoint */
	public Response refreshTokenHelperEndPoint(String baseURL, String accessTokenValue, String dataType, String refreshToken, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to Request access token using refreshToken EndPoint ..! *******");
		Log.info("******* About to Request access token using refreshToken EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINT;
		Response refreshTokenResponse	= null;
		String postData					= null;

		postData = this.postDataForrefreshToken(dataType, refreshToken);
		refreshTokenResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());
		Log.info("************ Successfully created refreshTokenResponse : " + refreshTokenResponse.asString());

		return refreshTokenResponse;

	}
	
	/* validate refreshToken ErrorResponse HelperEndPoint */
	public void validateRefreshTokenErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(message, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}

	/* validaterefreshToken error Response HelperEndPoint */
	public void validateRefreshTokenInvalidHeaderErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}
	
	/* validaterefreshToken error Response HelperEndPoint */
	public void validateRefreshTokenErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}

	/* validaterefreshToken error Response HelperEndPoint */
	public void validateRefreshTokenErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}

	/* validaterefreshToken error Response HelperEndPoint */
	public void validateRefreshTokenErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}

	/* validaterefreshToken error Response HelperEndPoint */
	public void validateRefreshTokenErrorResponseHelperEndPoint_(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate refreshToken error Response ******* ..!");
		Log.info("******* About to validate refreshToken error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated refreshToken error Response ************");
		Log.info("************Successfully validated refreshToken error Response ************");

	}

	/* getAccessToken */
	public String getAccessToken(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get Access token ..! *******");
		Log.info("******* About to get Access token ..! *******");
		
		String accesstoken = "Bearer " + response.path(accessToken);
		
		logger.log(LogStatus.PASS, "Access token value is :  " + accesstoken);
		Log.info("Access token value is :  " + accesstoken);

		return accesstoken;

	}

	
}