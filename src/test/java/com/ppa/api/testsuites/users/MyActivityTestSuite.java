package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AffillateNetwork;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.Stores;
import com.admin.pages.Vouchers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.payments.NEFTPost;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.endpoints.users.ExitClicks;
import com.ppa.api.endpoints.users.MyActivity;
import com.ppa.api.endpoints.users.RaiseTicket;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class MyActivityTestSuite extends Base {

	String storeID = "1999";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";
	String voucherName = "";
	String affiliateNetworkID = "";
	String orderIDFormat = Utils.generateRandomAlphabets(10);
	String ticketAcceptHours = "72";
	
	String storeIDTwo = "2277";
	String voucherIDTwo = "";
	String productIDTwo = "";
	String linkTwo = "";
	String storeNameTwo = "";
	String voucherNameTwo = "";
	String affiliateNetworkIDTwo = "";
	String orderIDFormatTwo = Utils.generateRandomAlphabets(10);
	String ticketAcceptHoursTwo = "72";

	@Test(priority=0)	
	public void storeCreationOne() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", ticketAcceptHours, "ACTIVATE CASHBACK", "90", orderIDFormat, "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", "72", "ACTIVATE CASHBACK", "90", "orderID", "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeID = objAddStore.getStoreID(driver);
			
			storeName = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeID);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherID = objVouchers.getVoucherID(driver, 1);

			voucherName = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkID = objAffillateNetwork.getAffiliateNetworkID(driver);

		}

	}

	@Test(priority=1)	
	public void storeCreationTwo() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", ticketAcceptHours, "ACTIVATE CASHBACK", "90", orderIDFormat, "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeIDTwo = objAddStore.getStoreID(driver);
			
			storeNameTwo = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeIDTwo);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherIDTwo = objVouchers.getVoucherID(driver, 1);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);

			/*************************************************************** STORE CREATION COMPLETED **************************************************************************/

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			/********************************************************************* ALL STORE PAGE ******************************************************************************/

			objDashboard.clickMainMenuStore(driver);

			objAddStore.clickSubMenuStores(driver);

			objAddStore.clickAddNewStore(driver);

			objAddStore.clickStoresTab(driver);

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "", "", "Terms and conditions", "All", "999041", "72", "ACTIVATE CASHBACK", "90", "orderID", "Within 4 Days of Shipment", "check", "check", "check", "check");

			objAddStore.clickCategoryCheckbox(driver);

			objAddStore.clickSEOTab(driver);

			objAddStore.enterKeywordsDetails(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterProductMetaTags(driver, arrayNewStoreDetails_One[0]);

			objAddStore.enterSocialMediaMessageDetails(driver, "This is "+arrayNewStoreDetails_One[0]+" social message");

			objAddStore.enterNormalPrimaryCashbackDetails(driver, "Percentage", "INR", 1500, 75, "This Is Test User Entering normal Primary cashback");

			objAddStore.enterNormalSecondaryCashbackDetails(driver, 0, "Percentage", 1250, "This Is Test User Entering Normal Secondary cashback", "All", 0);

			objAddStore.enterOthers(driver, "Terms and conditions");

			objAddStore.StoreContent_Creation(driver, arrayNewStoreDetails_One[0]);

			objAddStore.click_Button_AddStore(driver);

			/********************************************************************** STORE PAGE *********************************************************************************/

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeIDTwo = objAddStore.getStoreID(driver);
			
			storeNameTwo = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickPickOfTheDayPickOfTheWeek_SpecificStore(driver, arrayNewStoreDetails_One[0].trim());
			
			driver.navigate().refresh();

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			/******************************************************************* ALL VOUCHER PAGE ******************************************************************************/

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeIDTwo);

			/********************************************************************** VOUCHER PAGE *******************************************************************************/

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherIDTwo = objVouchers.getVoucherID(driver, 1);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);

		}

	}

	@Test(priority=2)
	public void validateMyActivityResponseForNewUserMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(5, "refID");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "0";
		String pageNumber				= "1";
		String pageSize					= "10";
		String data						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid	= (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, data);

		objSoftAssertion.assertAll();

	}

	@Test(priority=3)
	public void validateMyActivityResponseAfterReachingTicketAcceptHoursMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "10";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_true");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=4)
	public void validateMyActivityResponseForStatusPendingConfirmRequestedPaidMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		NEFTPost objNEFTPost = new NEFTPost(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "10";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_true");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String typeNeft					= Utils.getRestApiTestData(32, "type_neft");
		String paymentType				= Utils.getRestApiTestData(32, "payment_type");
		String passcode					= Utils.getRestApiTestData(32, "password");
		String paymentMethodID			= Utils.getRestApiTestData(32, "payment_method_id");
		String accountHolderName		= Utils.getRestApiTestData(32, "account_holder_name");
		String bankName					= Utils.getRestApiTestData(32, "bank_name");
		String branch					= Utils.getRestApiTestData(32, "branch");
		String accountNumber			= Utils.getRestApiTestData(32, "account_number");
		String ifscCode					= Utils.getRestApiTestData(32, "ifsc_code");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String confirmStatus			= Utils.getRestApiTestData(31, "confirm");
		String requestedStatus			= Utils.getRestApiTestData(31, "request");
		String paidStatus				= Utils.getRestApiTestData(31, "paid");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, pendingStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objAdminCommonFunctions.changeStatusPendingToConfirmForExitClick(exitClick);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, confirmStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objNEFTPost.validateNEFTPostSucessResponsePaymentEndPoint(baseURL, accessTokenValue, typeNeft, paymentType, passcode, paymentMethodID, accountHolderName, bankName, branch, accountNumber, ifscCode, statusCode200);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, requestedStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objAdminCommonFunctions.createCashout(email);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, paidStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=5)
	public void validateMyActivityResponseForStatusPendingCancelledMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "10";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_true");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String cashbackType				= Utils.getRestApiTestData(31, "cashback");
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String commision				= Utils.getRestApiTestData(31, "five_hundered");
		String cashback					= Utils.getRestApiTestData(31, "two_fifty");
		String pendingStatus			= Utils.getRestApiTestData(31, "pending");
		String cancelStatus				= Utils.getRestApiTestData(31, "cancel");
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objAdminCommonFunctions.setStatusPendingForExitClick(exitClick, cashbackType, orderValue, commision, cashback);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, pendingStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objAdminCommonFunctions.changeStatusPendingToCancelForExitClick(exitClick);

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, cancelStatus, cashback, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=6)
	public void validateMyActivityResponseAfterChangingToAllMarkTicketStatusMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		RaiseTicket objRaiseTicket = new RaiseTicket(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "10";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_true");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;
		String orderValue				= Utils.getRestApiTestData(31, "thousand");
		String typeTicket				= Utils.getRestApiTestData(34, "type_ticket");
		String message					= Utils.getRestApiTestData(34, "message");
		String couponCode				= Utils.getRestApiTestData(34, "coupon_code");
		String transactionDetails		= Utils.getRestApiTestData(34, "transaction_details");
		String receivedStatus			= Utils.getRestApiTestData(35, "received");
		String sentToRetailerStatus		= Utils.getRestApiTestData(35, "sent_to_retailer");
		String resolvedStatus			= Utils.getRestApiTestData(35, "resolved");
		String adminReply				= Utils.generateRandomAlphabets(20);
		voucherID						= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);String pathParameter = "/"+exitClickCreatedDate+"/"+storeID+"/"+exitClick;
		
		Response raiseTicketResponse = objRaiseTicket.raiseMissingTicketRaiseTicketEndPoint(baseURL, accessTokenValue, pathParameter, orderIDFormat, orderValue, couponCode, transactionDetails, statusCode201);
		
		String ticketID = objRaiseTicket.getTicketID(raiseTicketResponse);
				
		objRaiseTicket.validateRaiseTicketSucessResponseHelperEndPoint(objSoftAssertion, raiseTicketResponse, typeTicket, message);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, ticketID, receivedStatus, latestUpdated, currentDate);
		
		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, sentToRetailerStatus);
		
		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, ticketID, sentToRetailerStatus, latestUpdated, currentDate);
		
		objAdminCommonFunctions.changeStatusForTicket(exitClick, adminReply, resolvedStatus);
		
		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, ticketID, resolvedStatus, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=7)
	public void validateMyActivityResponseAfterDifferentStoreDifferentExitClickMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"2";
		String totalRecords				= "2";
		String pageNumber				= "1";
		String pageSize					= "2";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"2";
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"2";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"2";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_true");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");

		Object[] createShareLinkResponseTwo = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeIDTwo, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURLTwo = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponseTwo);
		
		objExitClicks.createExitClick(storeIDTwo, userid, shortURLTwo, "amazon", "desktop");

		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.getExitClick(email);
		
		String exitClickOne = objAdminCommonFunctions.getExitClickBasedOnIndex("3");
		
		objExitClicks.exitClickBackDate(email, exitClickOne, exitClickCreatedDate);
		
		String exitClickTwo = objAdminCommonFunctions.getExitClickBasedOnIndex("2");

		objExitClicks.exitClickBackDate(email, exitClickTwo, exitClickCreatedDate);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClickTwo, storeNameTwo, storeIDTwo, merchantAcceptTicket, ticketAcceptHoursTwo, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "1", typeActivity, exitClickOne, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=8)
	public void validateMyActivityResponseAfterPassingInvalidQueryParameterMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String errorStatus				= Utils.getRestApiTestData(35, "error_status_400");
		String errorCode				= Utils.getRestApiTestData(35, "error_code_11001");
		String errorTitle				= Utils.getRestApiTestData(35, "error_title_invalid_page_number_size");
		String errorDetail				= Utils.getRestApiTestData(35, "error_detail_invalid_page_number_size");
		String errorParameterPageNumber	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_number");
		String errorParameterPageSize	= Utils.getRestApiTestData(35, "error_pointer_invalid_page_size");

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		queryParameter = Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"number&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"1";

		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, myActivityResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageNumber);
		
		queryParameter = Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"size";

		myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode400);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, myActivityResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameterPageSize);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=9)
	public void validateMyActivityResponseAfterUncheckingAcceptMissingTicketMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		ExitClicks objExitClicks = new ExitClicks(driver, logger);
		MyActivity objMyActivity = new MyActivity(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "app");
		String authType					= ConfigurationSetup.APPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
		String typeAuth					= Utils.getRestApiTestData(10, "type");
		String fullName					= Utils.getRestApiTestData(5, "username");
		String password					= Utils.getRestApiTestData(5, "password");
		String referralID				= Utils.getRestApiTestData(8, "double_quote");
		String ipAddress				= Utils.getRestApiTestData(10, "ipaddress");
		String fcmID					= Utils.getRestApiTestData(10, "fcm_id");
		String deviceUniqueID			= Utils.getRestApiTestData(10, "device_unique_id");
		String imeiNumber				= Utils.getRestApiTestData(10, "imei_number");
		String email					= Utils.generateNewEmail();
		String mobileNumber				= Utils.generateMobileNumber(10);
		int statusCode200				= Integer.parseInt(Utils.getRestApiTestData(9, "status_200"));
		int statusCode201				= Integer.parseInt(Utils.getRestApiTestData(9, "status_201"));
		String baseURL					= ConfigurationSetup.APPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String exitClickCreatedDate		= Utils.requiredDateFormat(-4);
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String queryParameter			= Utils.getRestApiTestData(8, "emptyValue");
		String totalRecords				= "1";
		String pageNumber				= "1";
		String pageSize					= "10";
		String self			 			= baseURL+Utils.getRestApiTestData(2, "users")+Utils.getRestApiTestData(2, "recentclicks");
		String selfURL					= self;
		String firstURL					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String lastURL 					= self+Utils.getRestApiTestData(38, "pagenumber_query_parameter")+"1&"+Utils.getRestApiTestData(38, "pagesize_query_parameter")+"10";
		String prevURL					= Utils.getRestApiTestData(31, "null");
		String nextURL					= Utils.getRestApiTestData(31, "null");
		String typeActivity				= Utils.getRestApiTestData(40, "activity_type");
		String merchantAcceptTicket		= Utils.getRestApiTestData(40, "merchant_accept_ticket_false");
		String emptyValue				= Utils.getRestApiTestData(8, "emptyValue");
		String cashbackAmount			= "0";
		String trackingidCreatedAt		= Utils.requiredDateFormat(-4);
		String currentDate				= Utils.currentDateFormat();
		String latestUpdated			= Utils.getRestApiTestData(40, "latest_updated");
		String clickDays				= Utils.getRestApiTestData(40, "click_days");
		String currency					= ConfigurationSetup.CURRENCYTYPE;

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, fcmID, deviceUniqueID, imeiNumber, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		int userid = objSignUpV1WithOTP.getUserID(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Object[] createShareLinkResponse = objCreateShareLink.createShareLinkUserEndPoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		String shortURL = objCreateShareLink.returnCreateSharedLinkResponse(createShareLinkResponse);
		
		objExitClicks.createExitClick(storeID, userid, shortURL, "amazon", "desktop");
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCommonFunctions.uncheckAcceptMissingTicket(storeName);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);

		objExitClicks.exitClickBackDate(email, exitClick, exitClickCreatedDate);
		
		Response myActivityResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, accessTokenValue, queryParameter, statusCode200);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, totalRecords, pageNumber, pageSize);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, selfURL, firstURL, lastURL, prevURL, nextURL);
		
		objMyActivity.validateMyActivitySuccessResponseHelperEndPoint(objSoftAssertion, myActivityResponse, "0", typeActivity, exitClick, storeName, storeID, merchantAcceptTicket, ticketAcceptHours, emptyValue, cashbackAmount, currency, trackingidCreatedAt, clickDays, emptyValue, emptyValue, latestUpdated, currentDate);

		objSoftAssertion.assertAll();

	}

	@Test(priority=10)
	public void validateEmptyHeaderMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response exitClickResponse = objMyActivity.getMyActivityResponseHelperEndPoint(baseURL, statusCode401);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=11)
	public void validateInvalidAuthorizationKeyValueMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String accessTokenValue			= Utils.getRestApiTestData(0, "auth_invalid_value");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		

		Response exitClickResponse = objMyActivity.getMyActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=12)
	public void validateInvalidAcceptTypeKeyValueMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response exitClickResponse = objMyActivity.getMyActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode415);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=13)
	public void validateEmptyAuthorizationKeyValueMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String errorMessage				= Utils.getRestApiTestData(12, "message");
		

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		Response exitClickResponse = objMyActivity.getMyActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode401);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	@Test(priority=14)
	public void validateEmptyAcceptTypeKeyValueMyActivityEndpointDeviceTypeApp() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		MyActivity objMyActivity = new MyActivity(logger);
		
		// Assigning values
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String authType					= ConfigurationSetup.DESKTOPAUTHTYPE;
		String typeUserOTP				= Utils.getRestApiTestData(11, "type");
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
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");
		

		Response helperAccessTokenResponse = objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 
		
		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		Response exitClickResponse = objMyActivity.getMyActivityResponseHelperEndpoint(baseURL, accessTokenValue, acceptValue, statusCode406);
		
		objMyActivity.validateMyActivityErrorResponseHelperEndPoint(objSoftAssertion, exitClickResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		objSoftAssertion.assertAll();

	}


}