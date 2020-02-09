package com.ppa.api.endpoints.users;

import java.awt.AWTException;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class MyEarningsRewards extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public MyEarningsRewards(ExtentTest logger) {

		this.logger = logger;
	}
	
	/******************************************************** Object Creation started ****************************************************************/

	
	/******************************************************** Variable Creation started ***************************************************************/

	public static final String AUTHORIZATIONKEY;
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String ENDPOINTUSER;
	public static final String ENDPOINTMYEARNINGS;
	public static final String ENDPOINTREWARDS;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String typeData							= "data.type";
	String idData							= "data.id";	
	String totalRewardsEarnedData			= "data.attributes.total_rewards_earned";		
	String paidRewardsData					= "data.attributes.paid_rewards";		
	String rewardsAvailablePaymentData		= "data.attributes.rewards_available_payment";		
	String pendingRewardsData				= "data.attributes.pending_rewards";		
	String authorizedRewardsData			= "data.attributes.authorized_rewards";		
	String currencyData						= "data.attributes.currency";		
	String dataRelationship					= "data.relationships.months.data";	     
	String typeRelationship					= "data.relationships.months.data.type";		
	String idRelationship					= "data.relationships.months.data.id";		
	String monthRelationship				= "data.relationships.months.data.attributes.month";		
	String yearRelationship					= "data.relationships.months.data.attributes.year";		
	String selfRelationship					= "data.relationships.months.data.links.self";	
	String totalRecords						= "meta.total_records";     
	String pageNumber						= "meta.page_number";     
	String pageSize							= "meta.page_size";     
	String self								= "links.self";     
	String first							= "links.first";     
	String last								= "links.last";     
	String prev								= "links.prev";     
	String next								= "links.next";     
	String cashbackCreatedDate				= "data.attributes.cashback_created_date";  
	String merchantName						= "data.attributes.merchant_name";  
	String orderAmount						= "data.attributes.order_amount";  
	String orderID							= "data.attributes.order_id";  
	String cashbackAmount					= "data.attributes.cashback_amount";  
	String cashbackStatus					= "data.attributes.cashback_status";  
	String expectedConfirmationDate			= "data.attributes.expected_confirmation_date";  
	String currency							= "data.attributes.currency";                                
	String status							= "errors.status";                              
	String code								= "errors.code";                                                    
	String title							= "errors.title";                                         
	String detail							= "errors.detail";                           
	String parameter						= "errors.source.parameter";  
	String mesage							= "message";                             
	String links							= "errors.links.about";                   
	String data								= "data";                        

	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSER		= Utils.getRestApiTestData(2, "users");
		ENDPOINTMYEARNINGS	= Utils.getRestApiTestData(2, "myearnings");
		ENDPOINTREWARDS		= Utils.getRestApiTestData(2, "rewards");
		
	}

	/* getMyEarningsRewardsResponseUserEndPoint */
	public Response getMyEarningsRewardsResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings Rewards response ******* ..!");
		Log.info("******* About to get My Earnings Rewards response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTMYEARNINGS+ENDPOINTREWARDS;

		// AuthType setup based on conditions
		Response MyEarningsRewardsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());
		Log.info("************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());

		return MyEarningsRewardsResponse;

	}

	/* getMyEarningsRewardsResponseUserEndPoint */
	public Response getMyEarningsRewardsResponseUserEndPoint(String baseURL, String signUpUserToken, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings Rewards response ******* ..!");
		Log.info("******* About to get My Earnings Rewards response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTMYEARNINGS+ENDPOINTREWARDS;

		// AuthType setup based on conditions
		Response MyEarningsRewardsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());
		Log.info("************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());

		return MyEarningsRewardsResponse;

	}

	/* getMyEarningsResponseUserEndPoint */
	public Response getMyEarningsRewardsResponseUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings response ******* ..!");
		Log.info("******* About to get My Earnings response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTMYEARNINGS+ENDPOINTREWARDS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getMyEarningsCashbackResponseUserEndPoint */
	public Response getMyEarningsRewardsResponseUserEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings Rewards response ******* ..!");
		Log.info("******* About to get My Earnings Rewards response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSER+ENDPOINTMYEARNINGS+ENDPOINTREWARDS+queryParameter;

		// AuthType setup based on conditions
		Response MyEarningsRewardsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());
		Log.info("************Successfully got My Earnings Rewards END POINT  response :  " + MyEarningsRewardsResponse.asString());

		return MyEarningsRewardsResponse;

	}

	/* validateMyEarningsRewardsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String totalRewardsEarned, String paidRewards, String rewardsAvailablePayment, String pendingRewards, String authorizedRewards, String currency, String datarelationship) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalRewardsEarnedData, totalRewardsEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(paidRewardsData, paidRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(rewardsAvailablePaymentData, rewardsAvailablePayment, objSoftAssertion, response);
		assertionContainsUsingSubstring(pendingRewardsData, pendingRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(authorizedRewardsData, authorizedRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(currencyData, currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring(dataRelationship, datarelationship, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsRewardsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String totalrecords, String pagenumber, String pagesize, String dataValue) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalRecords, totalrecords, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageNumber, pagenumber, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageSize, pagesize, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(data, dataValue, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsRewardsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String totalrecords, String pagenumber, String pagesize, String selfUrl, String firstUrl, String lastUrl, String prevUrl, String nextUrl, String type, String id, String cashbackcreateddate, String merchantname, String orderamount, String orderid, String cashbackamount, String cashbackstatus, String expectedconfirmationdate, String curency) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(totalRecords, totalrecords, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageNumber, pagenumber, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pageSize, pagesize, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(self, selfUrl, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(first, firstUrl, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(last, lastUrl, objSoftAssertion, response);
		assertionForNullValues(prev, prevUrl, objSoftAssertion, response);
		assertionForNullValues(next, nextUrl, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsUsingSubstring(cashbackCreatedDate, cashbackcreateddate, objSoftAssertion, response);
		assertionContainsUsingSubstring(merchantName, merchantname, objSoftAssertion, response);
		assertionContainsUsingSubstring(orderAmount, orderamount, objSoftAssertion, response);
		assertionContainsUsingSubstring(orderID, orderid, objSoftAssertion, response);
		assertionContainsUsingSubstring(cashbackAmount, cashbackamount, objSoftAssertion, response);
		assertionContainsUsingSubstring(cashbackStatus, cashbackstatus, objSoftAssertion, response);
		assertionContainsUsingSubstring(expectedConfirmationDate, expectedconfirmationdate, objSoftAssertion, response);
		assertionContainsUsingSubstring(currency, curency, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsRewardsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String totalRewardsEarned, String paidRewards, String rewardsAvailablePayment, String pendingRewards, String authorizedRewards, String currency, String typerelationship, String idrelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalRewardsEarnedData, totalRewardsEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(paidRewardsData, paidRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(rewardsAvailablePaymentData, rewardsAvailablePayment, objSoftAssertion, response);
		assertionContainsUsingSubstring(pendingRewardsData, pendingRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(authorizedRewardsData, authorizedRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(currencyData, currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring(typeRelationship, typerelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring(idRelationship, idrelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring(monthRelationship, month, objSoftAssertion, response);
		assertionContainsUsingSubstring(yearRelationship, year, objSoftAssertion, response);
		assertionContainsUsingSubstring(selfRelationship, self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsRewardsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String type, String id, String totalRewardsEarned, String paidRewards, String rewardsAvailablePayment, String pendingRewards, String authorizedRewards, String currency, String datarelationship) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalRewardsEarnedData, totalRewardsEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(paidRewardsData, paidRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(rewardsAvailablePaymentData, rewardsAvailablePayment, objSoftAssertion, response);
		assertionContainsUsingSubstring(pendingRewardsData, pendingRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(authorizedRewardsData, authorizedRewards, objSoftAssertion, response);
		assertionContainsUsingSubstring(currencyData, currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring(dataRelationship, datarelationship, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalCashbackEarned, String paidCashback, String cashbackAvailablePayment, String pendingCashback, String referralCashback, String authorizedCashback, String currency, String typerelationship, String idrelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].total_cashback_earned", totalCashbackEarned, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].paid_cashback", paidCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].cashback_available_payment", cashbackAvailablePayment, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].pending_cashback", pendingCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].referral_cashback", referralCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_cashback", authorizedCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.type", typerelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.id", idrelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.month", month, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.year", year, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.links.self", self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalCashbackEarned, String paidCashback, String cashbackAvailablePayment, String pendingCashback, String referralCashback, String authorizedCashback, String currency, String datarelationship) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].total_cashback_earned", totalCashbackEarned, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].paid_cashback", paidCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].cashback_available_payment", cashbackAvailablePayment, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].pending_cashback", pendingCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].referral_cashback", referralCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_cashback", authorizedCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data", datarelationship, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String authorizedReferral, String currency, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_referral_cashback", authorizedReferral, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsRewardsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String authorizedReferral, String currency, String typerelationship, String idrelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings Rewards response ******* ..!");
		Log.info("******* About to validate My Earnings Rewards response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_referral_cashback", authorizedReferral, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.type", typerelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.id", idrelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.month", month, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.year", year, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.links.self", self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings Rewards END POINT  response************");
		Log.info("************Successfully validated My Earnings Rewards END POINT  response************");

	}

	/* validateIncludeQueryParameterCountResponseobjMyEarningsRewardsEndPoint */
	public void validateIncludeQueryParameterCountResponseMyEarningsRewardsEndPoint(SoftAssert objSoftAssertion, Response response, int expectedCount) throws InterruptedException, AWTException {

		List <String> includeParameterCount = response.path("included.findAll {it.type}");

		int actualCount = includeParameterCount.size();

		if(actualCount == expectedCount) {
			
			logger.log(LogStatus.PASS, "************Successfully validated number of count in response based on include query parameter************");
			Log.info("************Successfully validated number of count in response based on include query parameter************");

		}else {
			
			logger.log(LogStatus.FAIL, "************Number of count in response is not based on given include query parameter************");
			Log.error("************Number of count in response is not based on given include query parameter************");

		}

	}

	/* validateMyEarningsRewardsErrorResponseUserEndpoint */
	public void validateMyEarningsRewardsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings error response ******* ..!");
		Log.info("******* About to validate My Earnings error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT error response************");
		Log.info("************Successfully validated My Earnings END POINT error response************");

	}

	/* validateMyEarningsRewardsErrorResponseUserEndpoint */
	public void validateMyEarningsRewardsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings error response ******* ..!");
		Log.info("******* About to validate My Earnings error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT error response************");
		Log.info("************Successfully validated My Earnings END POINT error response************");

	}

	/* validateMyEarningsRewardsErrorResponseUserEndpoint */
	public void validateMyEarningsRewardsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings error response ******* ..!");
		Log.info("******* About to validate My Earnings error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT error response************");
		Log.info("************Successfully validated My Earnings END POINT error response************");

	}

}