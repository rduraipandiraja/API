package com.ppa.api.endpoints.users;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.ppa.api.base.Log;
import com.ppa.api.utilities.Restutils;
import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.response.Response;

public class CreateExternalProfitLink extends Restutils {

	/************************************************************** Constructor ***********************************************************************/

	public CreateExternalProfitLink(ExtentTest logger) {

		this.logger = logger;
	}

	/******************************************************** Object Creation started ****************************************************************/
	

	/****************************************************** Variable Creation started ****************************************************************/

	// Assigning header Values
	public static final String ACCEPTKEY;
	public static final String ACCEPTVALUE;
	public static final String CONTENTTYPEKEY;
	public static final String CONTENTTYPEVALUE;
	public static final String USERNAME;
	public static final String PASSWORD;
	public static final String APPVERSIONKEY;
	public static final String APPVERSIONVALUE;
	public static final String ENDPOINTCREATEEXTERNALPROFITLINK;
	
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

		ACCEPTKEY							= Utils.getRestApiTestData(0, "accept_key");
		ACCEPTVALUE							= Utils.getRestApiTestData(0, "accept_value");
		CONTENTTYPEKEY						= Utils.getRestApiTestData(0, "content_type_key");
		CONTENTTYPEVALUE					= Utils.getRestApiTestData(0, "content_type_value");
		USERNAME							= Utils.getConfigurationSetupTestData(0, "createlink_username");
		PASSWORD							= Utils.getConfigurationSetupTestData(0, "createlink_password");
		APPVERSIONKEY						= Utils.getRestApiTestData(0, "app_version_key");
		APPVERSIONVALUE						= Utils.getRestApiTestData(0, "app_Version_value");
		ENDPOINTCREATEEXTERNALPROFITLINK	= Utils.getRestApiTestData(2, "createexternalearnlinknew");
		
	}
	
	/* createShareLinkPostData */
	public String createShareLinkPostData(String type, String userID, String link, String ipAddress) {

		logger.log(LogStatus.INFO, "Generated userID : " +userID.trim());
		logger.log(LogStatus.INFO, "Generated link : " +link.trim());
		logger.log(LogStatus.INFO, "Generated ipAddress : " +ipAddress.trim());

		Log.info("Generated userID : " +userID.trim());
		Log.info("Generated link : " +link.trim());
		Log.info("Generated ipAddress : " +ipAddress.trim());
		
		logger.log(LogStatus.INFO, "About to post data for POST request");
		Log.info("About to post data for POST request");
		
		String postData =	"{\r\n" + 
							"  \"data\": {\r\n" + 
							"    \"type\": \""+type+"\",\r\n" + 
							"    \"attributes\": {\r\n" + 
							"        \"userid\": "+userID+",\r\n" + 
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
	public Response getCreateExternalProfitLinkHelperEndpoint(String baseURL, String type, String userID, String link, String ipAddress, String deviceType, String apiKeyValue, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to create share link using create share link EndPoint ..! *******");
		Log.info("******* About to create share link using create share link EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINTCREATEEXTERNALPROFITLINK+deviceType+"&"+apiKeyValue;
		Response response				= null;
		String postData					= null;
		
		postData = createShareLinkPostData(type, userID, link, ipAddress);
		response = postRequestSpecificationBasicAuth(completedURL, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, USERNAME, PASSWORD, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully got create share link response : " + response);
		Log.info("************ Successfully got create share link response : " + response);

		return response;

	}

	/* createShareLinkCreateShareLinkEndPoint */
	public Response getCreateExternalProfitLinkHelperEndpoint(String baseURL, String type, String userID, String link, String ipAddress, String deviceType, String apiKeyValue, String userName, String password, int statusCode) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to create share link using create share link EndPoint ..! *******");
		Log.info("******* About to create share link using create share link EndPoint ..! *******");
		
		// Assigning value
		String completedURL				= baseURL+ENDPOINTCREATEEXTERNALPROFITLINK+deviceType+"&"+apiKeyValue;
		Response response				= null;
		String postData					= null;
		
		postData = createShareLinkPostData(type, userID, link, ipAddress);
		response = postRequestSpecificationBasicAuth(completedURL, ACCEPTKEY, ACCEPTVALUE, CONTENTTYPEKEY, CONTENTTYPEVALUE, userName, password, postData, statusCode);

		logger.log(LogStatus.PASS, "************ Successfully got create share link response : " + response);
		Log.info("************ Successfully got create share link response : " + response);

		return response;

	}

	/* getCreateExternalProfitLinkHelperEndpoint */
	public Response getCreateExternalProfitLinkHelperEndpoint(String baseURL, String deviceType, String apiKeyValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get write testimonials response ******* ..!");
		Log.info("******* About to get write testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTCREATEEXTERNALPROFITLINK+deviceType+"&"+apiKeyValue;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecification(completedURL, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}

	/* getCreateExternalProfitLinkHelperEndpoint */
	public Response getCreateExternalProfitLinkHelperEndpoint(String baseURL, String AcceptValue, String contentTypeValue, String deviceType, String apiKeyValue, int statusCode) throws InterruptedException, AWTException {
		

		logger.log(LogStatus.INFO, "******* About to get write testimonials response ******* ..!");
		Log.info("******* About to get write testimonials response ******* ..!");
		
		// Assigning values
		String completedURL = baseURL+ENDPOINTCREATEEXTERNALPROFITLINK+deviceType+"&"+apiKeyValue;

		// AuthType setup based on conditions
		Response myEarningsResponse = postRequestSpecificationBasicAuth(completedURL, ACCEPTKEY, AcceptValue, CONTENTTYPEKEY, contentTypeValue, USERNAME, PASSWORD, statusCode);
		
		logger.log(LogStatus.PASS, "************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());
		Log.info("************Successfully got ticket details END POINT  response :  " + myEarningsResponse.asString());

		return myEarningsResponse;

	}
		
	/* validateCreateExternalProfitLinkSuccessResponseHelperEndPoint */
	public void validateCreateExternalProfitLinkSuccessResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String type) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateExternalProfitLink response ******* ..!");
		Log.info("******* About to validate CreateExternalProfitLink response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(typeData, type, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated ticket details END POINT  response************");
		Log.info("************Successfully validated ticket details END POINT  response************");

	}

	/* validateCreateExternalProfitLinkErrorResponseHelperEndpoint */
	public void validateCreateExternalProfitLinkErrorResponseHelperEndpoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorCode, String errorTitle, String errorDetail, String errorParameter) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateExternalProfitLink error response ******* ..!");
		Log.info("******* About to validate CreateExternalProfitLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(code, errorCode, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(parameter, errorParameter, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateExternalProfitLink END POINT error response************");
		Log.info("************Successfully validated CreateExternalProfitLink END POINT error response************");

	}

	/* validateCreateExternalProfitLinkErrorResponseHelperEndpoint */
	public void validateCreateExternalProfitLinkErrorResponseHelperEndpoint(SoftAssert objSoftAssertion, Response response, String errorMessage) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateExternalProfitLink error response ******* ..!");
		Log.info("******* About to validate CreateExternalProfitLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(mesage, errorMessage, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateExternalProfitLink END POINT error response************");
		Log.info("************Successfully validated CreateExternalProfitLink END POINT error response************");

	}

	/* validateCreateExternalProfitLinkErrorResponseHelperEndpoint */
	public void validateCreateExternalProfitLinkErrorResponseHelperEndpoint(SoftAssert objSoftAssertion, Response response, String errorLinks, String errorStatus, String errorTitle, String errorDetail) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateExternalProfitLink error response ******* ..!");
		Log.info("******* About to validate CreateExternalProfitLink error response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(links, errorLinks, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateExternalProfitLink END POINT error response************");
		Log.info("************Successfully validated CreateExternalProfitLink END POINT error response************");

	}

	/* validateCreateExternalProfitLinkErrorResponseHelperEndPoint */
	public void validateCreateExternalProfitLinkErrorResponseHelperEndPoint(SoftAssert objSoftAssertion, Response response, String errorStatus, String errorTitle, String errorDetail, String errorPointer) throws InterruptedException, AWTException {

		logger.log(LogStatus.INFO, "******* About to validate CreateExternalProfitLink error Response ******* ..!");
		Log.info("******* About to validate CreateExternalProfitLink error Response ******* ..!");
		
		assertionContainsWithoutUsingSubstring(status, errorStatus, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(title, errorTitle, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(detail, errorDetail, objSoftAssertion, response);
		assertionContainsWithoutUsingSubstring(pointer, errorPointer, objSoftAssertion, response);
		
		logger.log(LogStatus.PASS, "************Successfully validated CreateExternalProfitLink error Response ************");
		Log.info("************Successfully validated CreateExternalProfitLink error Response ************");

	}

	/* getShortURL */
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