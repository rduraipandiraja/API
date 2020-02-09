package com.ppa.api.endpoints.users;

import java.awt.AWTException;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class CreateShareLink extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public CreateShareLink(ExtentTest logger) {

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
	public static final String APPVERSIONKEY;
	public static final String APPVERSIONVALUE;
	public static final String ENDPOINTUSERS;
	public static final String ENDPOINTSHARELINK;
	public static final String CREATESHARELINK;
	
	/********************************************************** Gpath Creation started ***************************************************************/

	String typeData				= "data.type"; 
	String idData				= "data.id"; 
	String shortURL				= "data.attributes.shorturl"; 
	String message				= "meta.message"; 
	String status				= "errors.status";                              
	String code					= "errors.code";                                                    
	String title				= "errors.title";                                         
	String detail				= "errors.detail";                           
	String parameter			= "errors.source.parameter";  
	String mesage				= "message";                             
	String links				= "errors.links.about";                   
	String data					= "data";                     
	String pointer				= "errors.source.pointer";      
	
	/******************************************************** Methods Creation started ****************************************************************/
	
	static {

		AUTHORIZATIONKEY= Utils.getRestApiTestData(0, "auth_Key");
		ACCEPTKEY		= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE		= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY	= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE= Utils.getRestApiTestData(0, "content_type_value");
		APPVERSIONKEY	= Utils.getRestApiTestData(0, "app_version_key");
		APPVERSIONVALUE	= Utils.getRestApiTestData(0, "app_Version_value");
		ENDPOINTUSERS	= Utils.getRestApiTestData(2, "users");
		ENDPOINTSHARELINK= Utils.getRestApiTestData(2, "sharelink");
		CREATESHARELINK	= ConfigurationSetup.CREATESHARELINK;
		
	}
	
	/* createShareLinkPostData */
	public String createShareLinkPostData(String type, String storeID, String voucherID, String productID, String link, String ipAddress) {

		logger.log(LogStatus.INFO, "Generated storeID : " +storeID.trim());
		logger.log(LogStatus.INFO, "Generated voucherID : " +voucherID.trim());
		logger.log(LogStatus.INFO, "Generated productID : " +productID.trim());
		logger.log(LogStatus.INFO, "Generated link : " +link.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());

		Log.info("Generated storeID : " +storeID.trim());
		Log.info("Generated voucherID : " +voucherID.trim());
		Log.info("Generated productID : " +productID.trim());
		Log.info("Generated link : " +link.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"    	\"storeid\":\""+storeID+"\",\r\n" + 
							"    	\"voucherid\":\""+voucherID+"\",\r\n" + 
							"        \"productid\": \""+productID+"\",\r\n" + 
							"        \"link\": \""+link+"\",\r\n" + 
							"        \"ip_address\": \""+ipAddress+"\"\r\n" + 
							"    }\r\n" + 
							"  }\r\n" + 
							"}\r\n" + 
							"";

		logger.log(LogStatus.PASS, "Post Data for POST request : " + postData.trim());
		Log.info("Post Data for POST request : " + postData.trim());
		
		return postData;
		
	}

	/* createShareLinkCreateShareLinkEndPoint */
	public Object[] createShareLinkUserEndPoint(String baseURL, String accessTokenValue, String type, String storeID, String voucherID, String productID, String link, String ipAddress, String deviceType, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to create share link using create share link EndPoint ..! *******");
		Log.info("******* About to create share link using create share link EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTSHARELINK+deviceType;
		Response response				= null;
		String postData					= null;
		Object[] createShareLinkResponse= null;
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		
		// create share link based on conditions
		switch (CREATESHARELINK) {
		case "enable":
			
			postData = createShareLinkPostData(type, storeID, voucherID, productID, link, ipAddress);
			response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			// Getting response Values from respective EndPoint response
			Object typee			= response.path("data.type");
			Object id				= response.path("data.id");
			Object  shortURL		= response.path("data.attributes.shorturl");
			
			logger.log(LogStatus.INFO, "Generated type as : " + typee);
			logger.log(LogStatus.INFO, "Generated id as : " + id);
			logger.log(LogStatus.INFO, "Generated short URL as : " + shortURL);
			
			Log.info("Generated type as : " + typee);
			Log.info("Generated id as : " + id);
			Log.info("Generated short URL as : " + shortURL);

			createShareLinkResponse  = new Object[] {typee, id, shortURL};
			
			logger.log(LogStatus.PASS, "************ Successfully created shortURL : " + Arrays.toString(createShareLinkResponse));
			Log.info("************ Successfully created shortURL : " + Arrays.toString(createShareLinkResponse));

			break;
			
		case "disable":
			
			createShareLinkResponse  = new Object[] {emptyValue, emptyValue, emptyValue};
			
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}


		return createShareLinkResponse;

	}

	/* createShareLinkCreateShareLinkEndPoint */
	public Response getCreateShareLinkUserEndpoint(String baseURL, String accessTokenValue, String type, String storeID, String voucherID, String productID, String link, String ipAddress, String deviceType, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to create share link using create share link EndPoint ..! *******");
		Log.info("******* About to create share link using create share link EndPoint ..! *******");
		
		// Assigning value
		String authorizationValue		= accessTokenValue;
		String completedURL				= baseURL+ENDPOINTUSERS+ENDPOINTSHARELINK+deviceType;
		Response response				= null;
		String postData					= null;

		// create share link based on conditions
		switch (CREATESHARELINK) {
		case "enable":
			
			postData = createShareLinkPostData(type, storeID, voucherID, productID, link, ipAddress);
			response = postRequestSpecification(completedURL, AUTHORIZATIONKEY, authorizationValue, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, postData, statusCode);

			logger.log(LogStatus.PASS, "************ Successfully got create share link response : " + response);
			Log.info("************ Successfully got create share link response : " + response);

			break;
			
		case "disable":
			
			logger.log(LogStatus.PASS, "************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ EndPoint not required for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}

		return response;

	}

	/* getCreateShareLinkUserEndpoint */
	public Response getCreateShareLinkUserEndpoint(String baseURL, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get write testimonials response ******* ..!");
		Log.info("******* About to get write testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTSHARELINK+deviceType;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getCreateShareLinkUserEndpoint */
	public Response getCreateShareLinkUserEndpoint(String baseURL, String signUpUserToken, String AcceptValue, String contentTypeValue, String deviceType, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get write testimonials response ******* ..!");
		Log.info("******* About to get write testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTUSERS+ENDPOINTSHARELINK+deviceType;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecification(completedURL, AUTHORIZATIONKEY, signUpUserToken, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, contentTypeValue, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
	
	/* returnCreateSharedLinkResponse */
	public String returnCreateSharedLinkResponse(Object[] createShareLinkResponse) {

		logger.log(LogStatus.INFO, "******* About to returnCreateSharedLinkResponse ..! *******");
		Log.info("******* About to returnCreateSharedLinkResponse ..! *******");
		
		String shareLinkResponse	= null;
		String emptyValue			= Utils.getRestApiTestData(8, "emptyValue");
		String[] sharedExitClick	= null;
		String shortURL				= null;
		
		switch (CREATESHARELINK) {
		case "enable":
			
			shareLinkResponse	= Arrays.toString(createShareLinkResponse).replace("[", "").replace("]", "").trim();
			sharedExitClick	= shareLinkResponse.split(",");
			shortURL = sharedExitClick[2];
	
			logger.log(LogStatus.PASS, "************ Successfully returned CreateSharedLinkResponse : " + sharedExitClick);
			Log.info("************ Successfully returned CreateSharedLinkResponse : " + sharedExitClick);
			
			break;

		case "disable":
			
			sharedExitClick	= new String[] {emptyValue, emptyValue, emptyValue};

			logger.log(LogStatus.PASS, "************ Not required to returnCreateSharedLinkResponse for partner: " + ConfigurationSetup.PARTNERNAME);
			Log.info("************ Not required to returnCreateSharedLinkResponse for partner: " + ConfigurationSetup.PARTNERNAME);
			
			break;
		}
		
		return shortURL;
		
	}
	
	/* validateCreateSharedLinkSuccessResponseUserEndPoint */
	public void validateCreateSharedLinkSuccessResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String type) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateSharedLink response ******* ..!");
		Log.info("******* About to validate CreateSharedLink response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateCreateSharedLinkErrorResponseUserEndpoint */
	public void validateCreateSharedLinkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateSharedLink error response ******* ..!");
		Log.info("******* About to validate CreateSharedLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateSharedLink END POINT error response************");
		Log.info("************Successfully validated CreateSharedLink END POINT error response************");

	}

	/* validateCreateSharedLinkErrorResponseUserEndpoint */
	public void validateCreateSharedLinkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateSharedLink error response ******* ..!");
		Log.info("******* About to validate CreateSharedLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateSharedLink END POINT error response************");
		Log.info("************Successfully validated CreateSharedLink END POINT error response************");

	}

	/* validateCreateSharedLinkErrorResponseUserEndpoint */
	public void validateCreateSharedLinkErrorResponseUserEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateSharedLink error response ******* ..!");
		Log.info("******* About to validate CreateSharedLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateSharedLink END POINT error response************");
		Log.info("************Successfully validated CreateSharedLink END POINT error response************");

	}

	/* validateFBLoginV1 error Response HelperEndPoint */
	public void validateCreateSharedLinkErrorResponseUserEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateSharedLink error Response ******* ..!");
		Log.info("******* About to validate CreateSharedLink error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateSharedLink error Response ************");
		Log.info("************Successfully validated CreateSharedLink error Response ************");

	}

	/* getAccessToken */
	public String getShortURL(Response response) throws InterruptedException, AWTException {
		
		logger.log(LogStatus.INFO, "******* About to get short URL ..! *******");
		Log.info("******* About to get short URL ..! *******");
		
		Object shorturl = response.path("data.attributes.shorturl[0]");
		
		String shortUrl = shorturl.toString();
		
		logger.log(LogStatus.PASS, "Short URL is :  " + shortUrl);
		Log.info("Short URL is :  " + shortUrl);

		return shortUrl;

	}
	
	public void assertProfitLinkURLWithSellerLandedURL(String profitLink, String sellerLink) {

		Log.info("About to Assert the Profit link URL with the Seller URL (Seller URL must contain the Network tags as specified in the Network's Product Cashback Rule  ");

		if (profitLink.equals(sellerLink)) {

			logger.log(LogStatus.INFO, "Actual : " + profitLink + " & Expected : " + sellerLink);
			logger.log(LogStatus.PASS, "Successfully validated the Profilt lInk assertion with the required seller Cashback URL  ");
			
			Log.info("Actual : " + profitLink + " & Expected : " + sellerLink);
			Log.info("Successfully validated the Profilt lInk assertion with the required seller Cashback URL  ");

		} else {

			logger.log(LogStatus.INFO, "Actual : " + profitLink + " & Expected : " + sellerLink);
			logger.log(LogStatus.FAIL, "Failed to  validate the Profilt lInk assertion with the required seller Cashback URL : It is a critical issue check this immediately  ");
			
			Log.info("Actual : " + profitLink + " & Expected : " + sellerLink);
			Log.error("Failed to  validate the Profilt lInk assertion with the required seller Cashback URL : It is a critical issue check this immediately  ");
		}

	}

	public String waitingTillRequiredURLVisible(WebDriver driver, String partOfURL) {

		String currentURL = "";

		try {
			
			logger.log(LogStatus.INFO, "About to validate redirect URL");
			Log.info("About to validate redirect URL");
			
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.urlContains(partOfURL.trim()));

			driver.getCurrentUrl().contains(partOfURL.trim());

			currentURL = driver.getCurrentUrl();
			
			logger.log(LogStatus.PASS, "Validated redirect URL");
			Log.info("Validated redirect URL");

		} catch (Throwable e) {

			System.out.println(e.getMessage());
			logger.log(LogStatus.FAIL, "Not able to redirect to retailer: "+partOfURL);
			Log.error("Not able to redirect to retailer: "+partOfURL);
		}
		
		return currentURL;

	}
}