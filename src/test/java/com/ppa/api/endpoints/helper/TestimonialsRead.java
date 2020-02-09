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

public class TestimonialsRead extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public TestimonialsRead(ExtentTest logger) {

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
	public static final String ENDPOINTREADTESTIMONIALS;
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
		ENDPOINTREADTESTIMONIALS		= Utils.getRestApiTestData(2, "testimonials");
		FRONTENDURL						= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* getTestimonialsReadResponseUserEndPoint */
	public Response getTestimonialsReadResponseHelperEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get read testimonials response ******* ..!");
		Log.info("******* About to get read testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTREADTESTIMONIALS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getTestimonialsReadResponseUserEndPoint */
	public Response getTestimonialsReadResponseHelperEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get read testimonials response ******* ..!");
		Log.info("******* About to get read testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTREADTESTIMONIALS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getTestimonialsReadResponseUserEndPoint */
	public Response getTestimonialsReadResponseHelperEndpoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get read testimonials response ******* ..!");
		Log.info("******* About to get read testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTREADTESTIMONIALS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got read testimonials END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateTestimonialsReadSuccessResponseUserEndPoint */
	public void validateTestimonialsReadSuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String totalRecords, String pageNumber, String pageSize) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read read testimonials response ******* ..!");
		Log.info("******* About to validate read read testimonials response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalRecordsMeta, totalRecords, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageNumberMeta, pageNumber, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageSizeMeta, pageSize, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT  response************");
		Log.info("************Successfully validated read testimonials END POINT  response************");

	}

	/* validateTestimonialsReadSuccessResponseUserEndPoint */
	public void validateTestimonialsReadSuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String selfURL, String firstURL, String lastURL, String prevURL, String nextURL) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read read testimonials response ******* ..!");
		Log.info("******* About to validate read read testimonials response ******* ..!");
		
		assertionForNullValues(selfLink, selfURL, objSoftAssertion, response);
		assertionForNullValues(firstLink, firstURL, objSoftAssertion, response);
		assertionForNullValues(lastLink, lastURL, objSoftAssertion, response);
		assertionForNullValues(prevLink, prevURL, objSoftAssertion, response);
		assertionForNullValues(nextLink, nextURL, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT  response************");
		Log.info("************Successfully validated read testimonials END POINT  response************");

	}

	/* validateTestimonialsReadSuccessResponseUserEndPoint */
	public void validateTestimonialsReadSuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String rating, String title, String details, String postedBy, String postedDate) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read read testimonials response ******* ..!");
		Log.info("******* About to validate read read testimonials response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("data.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.rating["+index+"]", rating, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.title["+index+"]", title, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.details["+index+"]", details, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.posted_by["+index+"]", postedBy, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("data.attributes.posted_date["+index+"]", postedDate, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT  response************");
		Log.info("************Successfully validated read testimonials END POINT  response************");

	}

	/* validateTestimonialsReadErrorResponseUserEndpoint */
	public void validateTestimonialsReadErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read testimonials error response ******* ..!");
		Log.info("******* About to validate read testimonials error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT error response************");
		Log.info("************Successfully validated read testimonials END POINT error response************");

	}

	/* validateTestimonialsReadErrorResponseUserEndpoint */
	public void validateTestimonialsReadErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read testimonials error response ******* ..!");
		Log.info("******* About to validate read testimonials error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT error response************");
		Log.info("************Successfully validated read testimonials END POINT error response************");

	}

	/* validateTestimonialsReadErrorResponseUserEndpoint */
	public void validateTestimonialsReadErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate read testimonials error response ******* ..!");
		Log.info("******* About to validate read testimonials error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated read testimonials END POINT error response************");
		Log.info("************Successfully validated read testimonials END POINT error response************");

	}

	/* getTotalRecords */
	public int getTotalRecords(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get total_records ..! *******");
		Log.info("******* About to get total_records ..! *******");
		
		int totalRecords = response.path(totalRecordsMeta);
		
		logger.log(LogStatus.PASS, "TotalRecords value is :  " + totalRecords);
		Log.info("TotalRecords value is :  " + totalRecords);

		return totalRecords;

	}

	/* incrementTotalRecords */
	public String incrementTotalRecords(int totalRecordValue, int count) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to increment total_records ..! *******");
		Log.info("******* About to increment total_records ..! *******");
		
		int totalRecord = totalRecordValue+count;
		String totalRecords = Integer.toString(totalRecord);
		
		logger.log(LogStatus.PASS, "TotalRecords value after incrementing is :  " + totalRecords);
		Log.info("TotalRecords value after incrementing is :  " + totalRecords);

		return totalRecords;

	}

}