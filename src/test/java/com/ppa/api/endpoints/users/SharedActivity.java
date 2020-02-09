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

public class SharedActivity extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public SharedActivity(ExtentTest logger) {

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
	public static final String ENDPOINTSHAREDACTIVITY;
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
	String createdDateData		= "data.attributes.created_date"; 
	String merchantNameData		= "data.attributes.merchant_name"; 
	String transactionAmountData= "data.attributes.transaction_amount"; 
	String currencyData			= "data.attributes.currency"; 
	String statusData			= "data.attributes.status"; 
	String selfData				= "data.links.self";                                
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
		
		AUTHORIZATIONKEY				= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY						= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE						= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY					= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE				= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS					= Utils.getRestApiTestData(2, "users");
		ENDPOINTSHAREDACTIVITY			= Utils.getRestApiTestData(2, "mysharedactivities");
		FRONTENDURL						= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* getSharedActivityResponseUserEndPoint */
	public Response getSharedActivityResponseHelperEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get shared activity response ******* ..!");
		Log.info("******* About to get shared activity response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTSHAREDACTIVITY;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getSharedActivityResponseUserEndPoint */
	public Response getSharedActivityResponseHelperEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get shared activity response ******* ..!");
		Log.info("******* About to get shared activity response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTSHAREDACTIVITY+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getSharedActivityResponseUserEndPoint */
	public Response getSharedActivityResponseHelperEndpoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get shared activity response ******* ..!");
		Log.info("******* About to get shared activity response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTSHAREDACTIVITY;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got shared activity END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateSharedActivitySuccessResponseUserEndPoint */
	public void validateSharedActivitySuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity response ******* ..!");
		Log.info("******* About to validate shared activity response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT  response************");
		Log.info("************Successfully validated shared activity END POINT  response************");

	}

	/* validateSharedActivitySuccessResponseUserEndPoint */
	public void validateSharedActivitySuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String totalRecords, String pageNumber, String pageSize) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity response ******* ..!");
		Log.info("******* About to validate shared activity response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalRecordsMeta, totalRecords, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageNumberMeta, pageNumber, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageSizeMeta, pageSize, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT  response************");
		Log.info("************Successfully validated shared activity END POINT  response************");

	}

	/* validateSharedActivitySuccessResponseUserEndPoint */
	public void validateSharedActivitySuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String selfURL, String firstURL, String lastURL, String prevURL, String nextURL) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity response ******* ..!");
		Log.info("******* About to validate shared activity response ******* ..!");
		
		assertionForNullValues(selfLink, selfURL, objSoftAssertion, response);
		assertionForNullValues(firstLink, firstURL, objSoftAssertion, response);
		assertionForNullValues(lastLink, lastURL, objSoftAssertion, response);
		assertionForNullValues(prevLink, prevURL, objSoftAssertion, response);
		assertionForNullValues(nextLink, nextURL, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT  response************");
		Log.info("************Successfully validated shared activity END POINT  response************");

	}

	/* validateSharedActivitySuccessResponseUserEndPoint */
	public void validateSharedActivitySuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity response ******* ..!");
		Log.info("******* About to validate shared activity response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.month["+index+"]", month, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.year["+index+"]", year, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.links.self["+index+"]", self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT  response************");
		Log.info("************Successfully validated shared activity END POINT  response************");

	}

	/* validateSharedActivitySuccessResponseUserEndPoint */
	public void validateSharedActivitySuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String merchantName, String createdAt, String clicksCount, String transactionCount, String totalEarnings, String currency) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity response ******* ..!");
		Log.info("******* About to validate shared activity response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.merchant_name["+index+"]", merchantName, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.created_at["+index+"]", createdAt, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.clicks_count["+index+"]", clicksCount, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.transaction_count["+index+"]", transactionCount, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.total_earnings["+index+"]", totalEarnings, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.currency["+index+"]", currency, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT  response************");
		Log.info("************Successfully validated shared activity END POINT  response************");

	}

	/* validateSharedActivityErrorResponseUserEndpoint */
	public void validateSharedActivityErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity error response ******* ..!");
		Log.info("******* About to validate shared activity error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT error response************");
		Log.info("************Successfully validated shared activity END POINT error response************");

	}

	/* validateSharedActivityErrorResponseUserEndpoint */
	public void validateSharedActivityErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity error response ******* ..!");
		Log.info("******* About to validate shared activity error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT error response************");
		Log.info("************Successfully validated shared activity END POINT error response************");

	}

	/* validateSharedActivityErrorResponseUserEndpoint */
	public void validateSharedActivityErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate shared activity error response ******* ..!");
		Log.info("******* About to validate shared activity error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated shared activity END POINT error response************");
		Log.info("************Successfully validated shared activity END POINT error response************");

	}

}