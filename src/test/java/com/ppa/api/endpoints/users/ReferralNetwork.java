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

public class ReferralNetwork extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public ReferralNetwork(ExtentTest logger) {

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
	public static final String ENDPOINTREFERRALNETWORK;
	public static final String FRONTENDURL;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String totalRecordsMeta		= "meta.total_records";
	String pageNumberMeta		= "meta.page_number";
	String pageSizeMeta			= "meta.page_size";
	String selfLink				= "links.self"; 
	String firstLink			= "links.first"; 
	String lastLink				= "links.last"; 
	String prevLink				= "links.prev"; 
	String nextLink				= "links.next"; 
	String typeData				= "data.type"; 
	String idData				= "data.id"; 
	String referralCbData		= "data.attributes.total_referral_cashback_earned"; 
	String currencyData			= "data.attributes.currency"; 
	String status				= "errors.status";                              
	String code					= "errors.code";                                                    
	String title				= "errors.title";                                         
	String detail				= "errors.detail";                           
	String parameter			= "errors.source.parameter";  
	String mesage				= "message";                             
	String links				= "errors.links.about";    ;                   
	String data					= "data";           
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY			= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY					= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE					= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY				= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE			= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS				= Utils.getRestApiTestData(2, "users");
		ENDPOINTREFERRALNETWORK		= Utils.getRestApiTestData(2, "referralnetworks");
		FRONTENDURL					= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* getReferralNetworkResponseUserEndPoint */
	public Response getReferralNetworkResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get referral network response ******* ..!");
		Log.info("******* About to get referral network response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTREFERRALNETWORK;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getReferralNetworkResponseUserEndPoint */
	public Response getReferralNetworkResponseUserEndPoint(String baseURL, String signUpUserToken, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get referral network response ******* ..!");
		Log.info("******* About to get referral network response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTREFERRALNETWORK;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getReferralNetworkResponseUserEndPoint */
	public Response getReferralNetworkResponseUserEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get referral network response ******* ..!");
		Log.info("******* About to get referral network response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTREFERRALNETWORK+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getReferralNetworkResponseUserEndPoint */
	public Response getReferralNetworkResponseUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get referral network response ******* ..!");
		Log.info("******* About to get referral network response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTREFERRALNETWORK;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got referral network END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateReferralNetworkSuccessResponseUserEndPoint */
	public void validateReferralNetworkSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String totalRecords, String pageNumber, String pageSize) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network response ******* ..!");
		Log.info("******* About to validate referral network response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalRecordsMeta, totalRecords, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageNumberMeta, pageNumber, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageSizeMeta, pageSize, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT  response************");
		Log.info("************Successfully validated referral network END POINT  response************");

	}

	/* validateReferralNetworkSuccessResponseUserEndPoint */
	public void validateReferralNetworkSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String selfURL, String firstURL, String lastURL, String prevURL, String nextURL) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network response ******* ..!");
		Log.info("******* About to validate referral network response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(selfLink, selfURL, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(firstLink, firstURL, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(lastLink, lastURL, objSoftAssertion, response);
		assertionForNullValues(prevLink, prevURL, objSoftAssertion, response);
		assertionForNullValues(nextLink, nextURL, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT  response************");
		Log.info("************Successfully validated referral network END POINT  response************");

	}

	/* validateReferralNetworkSuccessResponseUserEndPoint */
	public void validateReferralNetworkSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String referralCashback, String currency) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network response ******* ..!");
		Log.info("******* About to validate referral network response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(referralCbData, referralCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(currencyData, currency, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT  response************");
		Log.info("************Successfully validated referral network END POINT  response************");

	}

	/* validateReferralNetworkSuccessResponseUserEndPoint */
	public void validateReferralNetworkSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String joinDate, String userName, String status) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network response ******* ..!");
		Log.info("******* About to validate referral network response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.relationships.referral.data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.referral.data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.referral.data.attributes.join_date["+index+"]", joinDate, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.referral.data.attributes.username["+index+"]", userName, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.relationships.referral.data.attributes.status["+index+"]", status, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT  response************");
		Log.info("************Successfully validated referral network END POINT  response************");

	}

	/* validateReferralNetworkErrorResponseUserEndpoint */
	public void validateReferralNetworkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network error response ******* ..!");
		Log.info("******* About to validate referral network error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT error response************");
		Log.info("************Successfully validated referral network END POINT error response************");

	}

	/* validateReferralNetworkErrorResponseUserEndpoint */
	public void validateReferralNetworkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network error response ******* ..!");
		Log.info("******* About to validate referral network error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT error response************");
		Log.info("************Successfully validated referral network END POINT error response************");

	}

	/* validateReferralNetworkErrorResponseUserEndpoint */
	public void validateReferralNetworkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate referral network error response ******* ..!");
		Log.info("******* About to validate referral network error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated referral network END POINT error response************");
		Log.info("************Successfully validated referral network END POINT error response************");

	}

}