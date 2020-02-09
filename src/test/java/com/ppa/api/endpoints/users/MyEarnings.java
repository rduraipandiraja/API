package com.ppa.api.endpoints.users;

import java.awt.AWTException;
import java.util.List;

import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class MyEarnings extends Restutils {

	/************************************************************** Constructor **********************************************************************/

	public MyEarnings(ExtentTest logger) {

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
	public static final String ENDPOINTMYEARNINGS;
	public static final String FRONTENDURL;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String typeData								= "data.type";                                            
	String idData								= "data.id";                                              
	String firstNameData						= "data.attributes.firstname";                
	String emailData							= "data.attributes.email";                        
	String totalEarnedData						= "data.attributes.total_earned";           
	String totalCashbackEarnedData				= "data.attributes.total_cashback_earned";                     
	String totalRewardsEarnedData				= "data.attributes.total_rewards_earned";       
	String currencyData							= "data.attributes.currency";  	                                   
	String status								= "errors.status";                              
	String code									= "errors.code";                                                    
	String title								= "errors.title";                                         
	String detail								= "errors.detail";                           
	String parameter							= "errors.source.parameter";  
	String mesage								= "message";                             
	String links								= "errors.links.about";  	

	/******************************************************** Methods Creation started ****************************************************************/
	
	static {
		
		AUTHORIZATIONKEY	= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY			= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE			= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY		= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE	= Utils.getRestApiTestData(0, "content_type_value");
		ENDPOINTUSERS		= Utils.getRestApiTestData(2, "users");
		ENDPOINTMYEARNINGS	= Utils.getRestApiTestData(2, "myearnings");
		FRONTENDURL			= ConfigurationSetup.FRONTENDURL;
		
	}
	
	/* getOTPForMobileNumber */
	public void addBonus(String email, String cashback, String cashbackType) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to Get OTP with the help GETCODE With Mobile Number ..! *******");
		Log.info("******* About to Get OTP with the help GETCODE With Mobile Number ..! *******");
		
		// Assigning Values
		int statusCode						= 201;
		String queryParameterPage			= Utils.getRestApiTestData(31, "queryparamOne_key");
		String queryParameterPageValue		= Utils.getRestApiTestData(31, "queryparamOne_value");
		String queryParameterEmail			= Utils.getRestApiTestData(31, "queryparamtwo_key");
		String queryParameterEmailValue		= email;
		String queryParameterCashback		= Utils.getRestApiTestData(31, "queryparamthree_key");
		String queryParameterCashbackValue	= cashback;
		String queryParameterCashbackType	= Utils.getRestApiTestData(31, "queryparamfour_key");
		String queryParameterCbTypeValue	= cashbackType;
		String baseURL						= FRONTENDURL+Utils.getRestApiTestData(3, "basepath");
		Response response					= null;
		
		response = addBonus(queryParameterPage, queryParameterPageValue, queryParameterEmail, queryParameterEmailValue, queryParameterCashback, queryParameterCashbackValue, queryParameterCashbackType, queryParameterCbTypeValue, baseURL, statusCode);
		
		logger.log(LogStatus.PASS, "************ Add bonus response: "+response.asString()+"************");
		Log.info("************ Add bonus response: "+response.asString()+"************");
		
	}

	/* getMyEarningsResponseUserEndPoint */
	public Response getMyEarningsResponseUserEndPoint(String baseURL, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings response ******* ..!");
		Log.info("******* About to get My Earnings response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTMYEARNINGS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* getMyEarningsResponseUserEndPoint */
	public Response getMyEarningsResponseUserEndPoint(String baseURL, String signUpUserToken, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings response ******* ..!");
		Log.info("******* About to get My Earnings response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTMYEARNINGS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getMyEarningsResponseUserEndPoint */
	public Response getMyEarningsResponseUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings response ******* ..!");
		Log.info("******* About to get My Earnings response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTMYEARNINGS;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getMyEarningsResponseUserEndPoint */
	public Response getMyEarningsResponseUserEndPoint(String baseURL, String signUpUserToken, String queryParameter, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get My Earnings response ******* ..!");
		Log.info("******* About to get My Earnings response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTMYEARNINGS+queryParameter;

		// AuthType setup based on conditions
		Response myEarningsResponse = getRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, ACCEPTVALUE, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got My Earnings END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* validateIncludeQueryParameterCountResponseMyEarningsEndPoint */
	public void validateIncludeQueryParameterCountResponseMyEarningsEndPoint(SoftAssert objSoftAssertion, Response response, int expectedCount) throws InterruptedException, AWTException {

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
	
	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String userEmail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsUsingSubstring(emailData, userEmail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type, String id, String firstName, String email, String totalEarned, String totalCashbackEarned, String totalRewardsEarned, String currency) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsUsingSubstring(typeData, type, objSoftAssertion, response);
		assertionContainsUsingSubstring(idData, id, objSoftAssertion, response);
		assertionContainsUsingSubstring(firstNameData, firstName, objSoftAssertion, response);
		assertionContainsUsingSubstring(emailData, email, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalEarnedData, totalEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalCashbackEarnedData, totalCashbackEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(totalRewardsEarnedData, totalRewardsEarned, objSoftAssertion, response);
		assertionContainsUsingSubstring(currencyData, currency, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalCashbackEarned, String paidCashback, String cashbackAvailablePayment, String pendingCashback, String referralCashback, String authorizedCashback, String currency, String typerelationship, String idrelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
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
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalCashbackEarned, String paidCashback, String cashbackAvailablePayment, String pendingCashback, String referralCashback, String authorizedCashback, String currency, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].total_cashback_earned", totalCashbackEarned, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].paid_cashback", paidCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].cashback_available_payment", cashbackAvailablePayment, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].pending_cashback", pendingCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].referral_cashback", referralCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_cashback", authorizedCashback, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalRewardsEarned, String paidRewards, String rewardsAvailablePayment, String pendingRewards, String authorizedRewards, String currency, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].total_rewards_earned", totalRewardsEarned, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].paid_rewards", paidRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].rewards_available_payment", rewardsAvailablePayment, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].pending_rewards", pendingRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_rewards", authorizedRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String totalRewardsEarned, String paidRewards, String rewardsAvailablePayment, String pendingRewards, String authorizedRewards, String currency, String typeRelationship, String idRelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].total_rewards_earned", totalRewardsEarned, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].paid_rewards", paidRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].rewards_available_payment", rewardsAvailablePayment, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].pending_rewards", pendingRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_rewards", authorizedRewards, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.type", typeRelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.id", idRelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.month", month, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.year", year, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.links.self", self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String authorizedReferral, String currency, String data) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_referral_cashback", authorizedReferral, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data", data, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsSuccessResponseUserEndPoint */
	public void validateMyEarningsSuccessResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String index, String type, String id, String authorizedReferral, String currency, String typeRelationship, String idRelationship, String month, String year, String self) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings response ******* ..!");
		Log.info("******* About to validate My Earnings response ******* ..!");
		
		assertionContainsWithoutUsingSubstring("included.type["+index+"]", type, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.id["+index+"]", id, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].authorized_referral_cashback", authorizedReferral, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring("included.attributes["+index+"].currency", currency, objSoftAssertion, response);
		
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.type", typeRelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.id", idRelationship, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.month", month, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.attributes.year", year, objSoftAssertion, response);
		assertionContainsUsingSubstring("included.relationships["+index+"].months.data.links.self", self, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT  response************");
		Log.info("************Successfully validated My Earnings END POINT  response************");

	}

	/* validateMyEarningsErrorResponseUserEndpoint */
	public void validateMyEarningsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings error response ******* ..!");
		Log.info("******* About to validate My Earnings error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT error response************");
		Log.info("************Successfully validated My Earnings END POINT error response************");

	}

	/* validateMyEarningsErrorResponseUserEndpoint */
	public void validateMyEarningsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate My Earnings error response ******* ..!");
		Log.info("******* About to validate My Earnings error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated My Earnings END POINT error response************");
		Log.info("************Successfully validated My Earnings END POINT error response************");

	}

	/* validateMyEarningsErrorResponseUserEndpoint */
	public void validateMyEarningsErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

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