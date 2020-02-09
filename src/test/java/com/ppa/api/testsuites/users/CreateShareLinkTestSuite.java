package com.ppa.api.testsuites.users;

import java.awt.AWTException;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.admin.pages.AdminCommonFunctions;
import com.admin.pages.AdminCoreFunction;
import com.admin.pages.AffillateNetwork;
import com.admin.pages.Dashboard;
import com.admin.pages.Login;
import com.admin.pages.NetworkStoreDomainMapping;
import com.admin.pages.ProductBrowserEditMode;
import com.admin.pages.Stores;
import com.admin.pages.Vouchers;
import com.ppa.api.base.Base;
import com.ppa.api.base.ConfigurationSetup;
import com.ppa.api.base.MakeLinkUrls;
import com.ppa.api.endpoints.helper.HelperAccessToken;
import com.ppa.api.endpoints.helper.SignUpOTP;
import com.ppa.api.endpoints.helper.SignUpV1WithOTP;
import com.ppa.api.endpoints.users.CreateShareLink;
import com.ppa.api.utilities.Utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CreateShareLinkTestSuite extends Base {

	String storeID = "2";
	String voucherID = "";
	String productID = "";
	String link = "";
	String storeName = "";
	String voucherName = "";
	String affiliateNetworkID = "";
	String orderIDFormat = Utils.generateRandomAlphabets(10);
	String ticketAcceptHours = "72";
	
	String storeIDTwo = "";
	String voucherIDTwo = "";
	String productIDTwo = "";
	String linkTwo = "";
	String storeNameTwo = "";
	String voucherNameTwo = "";
	String affiliateNetworkIDTwo = "";
	String orderIDFormatTwo = Utils.generateRandomAlphabets(10);
	String ticketAcceptHoursTwo = "72";

	@Test(priority=0, description="cashback store one creation")	
	public void storeCreationOne() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			ProductBrowserEditMode objProductBrowserEditMode = new ProductBrowserEditMode(driver, logger);
			
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
			
			objDashboard.clickMainMenuProductFeeds(driver);
			
			objProductBrowserEditMode.clickSubMenuProductBrowserEditMode(driver);
			
			objProductBrowserEditMode.selectValueFromDropdown("Amazon");
			
			objProductBrowserEditMode.clickSubmitButton();
			
			productID = objProductBrowserEditMode.getProductID(driver, 1);

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			ProductBrowserEditMode objProductBrowserEditMode = new ProductBrowserEditMode(driver, logger);
			
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
			
			
			objDashboard.clickMainMenuProductFeeds(driver);
			
			objProductBrowserEditMode.clickSubMenuProductBrowserEditMode(driver);
			
			objProductBrowserEditMode.selectValueFromDropdown("Amazon");
			
			objProductBrowserEditMode.clickSubmitButton();
			
			productID = objProductBrowserEditMode.getProductID(driver, 1);

		}

	}

	@Test(priority=0, description="cashback store two creation")	
	public void storeCreationTwo() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			
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

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "In-Active", "Adda52", "", "https://www.amazon.com/", "https://www.myntra.com/", "", "Terms and conditions", "All", "999041", ticketAcceptHoursTwo, "GRAB DEAL", "90", orderIDFormatTwo, "Within 4 Days of Shipment", "check", "check", "check", "check");

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

			//********************************************************************** STORE PAGE *********************************************************************************//*

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			storeIDTwo = objAddStore.getStoreID(driver);
			
			storeNameTwo = arrayNewStoreDetails_One[0];

			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

		} catch (Exception e) {


			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			
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

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

		}

	}

	@Test(priority=0, description="cashback store two creation")	
	public void storeCreationThree() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			Dashboard objDashboard = new Dashboard(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			/*ProductBrowserEditMode objProductBrowserEditMode = new ProductBrowserEditMode(driver, logger);*/
			
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

			String arrayNewStoreDetails_One[] = objAddStore.enterCashbackStoreDetails(driver, "Active", "Adda52", "", "https://www.amazon.com/", "https://www.myntra.com/", "", "Terms and conditions", "All", "999041", ticketAcceptHoursTwo, "GRAB DEAL", "90", orderIDFormatTwo, "Within 4 Days of Shipment", "check", "check", "check", "check");

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

			//********************************************************************** STORE PAGE *********************************************************************************//*

			objAddStore.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objAddStore.clickSearch(driver);

			String storeIDTwo = objAddStore.getStoreID(driver);
			
			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

			objAddStore.clickEdit(driver);

			objAddStore.clickSecondaryCashbackTab(driver);

			objAddStore.getSecondaryCashbackID(driver, 1);

			//******************************************************************* ALL VOUCHER PAGE ******************************************************************************//*

			objVouchers.clickSubMenuVoucher(driver);

			String arrayNewVoucher_Without_Code_One[] = objVouchers.enterVoucherWithCodeDetails_APITesting(driver, arrayNewStoreDetails_One, "Increased Rate", "COUPON CODE", "All", storeIDTwo);
			
			//********************************************************************** VOUCHER PAGE *******************************************************************************//*

			//click_Clear_Until_Default_Dropdown_Value_SelectDateRange_By_JavaScriptExecutor(driver, "btn_Clear");

			objVouchers.enterSearchCriteria(driver);

			objVouchers.enterStoreName(driver, arrayNewStoreDetails_One[0]);

			objVouchers.clickSearch(driver);

			voucherIDTwo = objVouchers.getVoucherID(driver, 1);
			
			objVouchers.clickEditOneVoucher(driver);
			
			objVouchers.change_StatusActiveToInactiveVoucherWithCode(driver, arrayNewStoreDetails_One[0], storeIDTwo);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			//******************************************************************* AFFILIATE NW PAGE *****************************************************************************//*

			objDashboard.clickMainMenuSettings(driver);

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);

			//*************************************************************** STORE CREATION COMPLETED **************************************************************************//*
			
			/*objDashboard.clickMainMenuProductFeeds(driver);
			
			objProductBrowserEditMode.clickSubMenuProductBrowserEditMode(driver);
			
			objProductBrowserEditMode.selectValueFromDropdown("Amazon");
			
			objProductBrowserEditMode.clickSubmitButton();
			
			productIDTwo = objProductBrowserEditMode.getProductID(driver, 2);
			
			objProductBrowserEditMode.setStatusForProduct(driver, 1);*/

		} catch (Exception e) {


			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Dashboard objDashboard = new Dashboard(driver, logger);
			Vouchers objVouchers = new Vouchers(driver, logger);
			Stores objAddStore = new Stores(driver, logger);
			AffillateNetwork objAffillateNetwork = new AffillateNetwork(driver, logger);
			/*ProductBrowserEditMode objProductBrowserEditMode = new ProductBrowserEditMode(driver, logger);*/
			
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

			String storeIDTwo = objAddStore.getStoreID(driver);
			
			objAddStore.getStoreType(driver, arrayNewStoreDetails_One[0].trim());

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
			
			objVouchers.clickEditOneVoucher(driver);
			
			objVouchers.change_StatusActiveToInactiveVoucherWithCode(driver, arrayNewStoreDetails_One[0], storeIDTwo);

			voucherNameTwo = arrayNewVoucher_Without_Code_One[1];

			/******************************************************************* AFFILIATE NW PAGE *****************************************************************************/

			/*objDashboard.clickMainMenuSettings(driver);*/

			objAffillateNetwork.clickSubMenuAffiliateNetwork(driver);	

			objAffillateNetwork.clickAffiliateNetworkEditIcon(driver, arrayNewStoreDetails_One[3].trim());

			affiliateNetworkIDTwo = objAffillateNetwork.getAffiliateNetworkID(driver);
			
			
			/*objDashboard.clickMainMenuProductFeeds(driver);
			
			objProductBrowserEditMode.clickSubMenuProductBrowserEditMode(driver);
			
			objProductBrowserEditMode.selectValueFromDropdown("Amazon");
			
			objProductBrowserEditMode.clickSubmitButton();
			
			productIDTwo = objProductBrowserEditMode.getProductID(driver, 2);
			
			objProductBrowserEditMode.setStatusForProduct(driver, 2);*/

		}

	}

	/**
	 * 
	 * Updating shorten domain URL in nw domain mapping admin panel 
	 * Shorten domain URL: bit.do,bit.ly,tiny.cc,rebrand.ly,j.mp,amzn.to,clnk.in,tinyurl.com,urlzs.com,fkrt.it
	 *	
	 */	
	@Test(priority=1, description="Updating shorten domain URL in nw domain mapping admin panel")	
	public void configurationShortenURLToNetworkStoreDomain() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			NetworkStoreDomainMapping objNetworkStoreDomainMapping = new NetworkStoreDomainMapping(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String shortDomainUrls = Utils.getRestApiTestData(42, "shorten_domain_urls");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objNetworkStoreDomainMapping.clickOnNetworkDomainMapping();
			objNetworkStoreDomainMapping.clickOnToolsButton();
			objNetworkStoreDomainMapping.clickOnUpdateShortenURLDomain();
			objNetworkStoreDomainMapping.enterShortenURL(shortDomainUrls);
			objNetworkStoreDomainMapping.clickOnSubmit();
			objNetworkStoreDomainMapping.validateShortenURLSuccessMessage();

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			NetworkStoreDomainMapping objNetworkStoreDomainMapping = new NetworkStoreDomainMapping(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String shortDomainUrls = Utils.getRestApiTestData(42, "shorten_domain_urls");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(1000);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objNetworkStoreDomainMapping.clickOnNetworkDomainMapping();
			objNetworkStoreDomainMapping.clickOnToolsButton();
			objNetworkStoreDomainMapping.clickOnUpdateShortenURLDomain();
			objNetworkStoreDomainMapping.enterShortenURL(shortDomainUrls);
			objNetworkStoreDomainMapping.clickOnSubmit();
			objNetworkStoreDomainMapping.validateShortenURLSuccessMessage();


			

		}

	}

	/**
	 * 
	 * Updating amazon product cashback URL in affiliate nw admin panel
	 * Product URL: {PRDURL}&tag={AFFLTID}&ascsubtag={EXTID}{(ihre_partner_id)AFFLTID}
	 *
	 */
	@Test(priority=2, description="Updating amazon product cashback URL in affiliate nw admin panel")	
	public void configurationAmazonNewCashbackURL() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameAmazon = Utils.getRestApiTestData(42, "AmazonNew");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameAmazon);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.AMAZON_NEW_PRODUCT_CASHABACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameAmazon = Utils.getRestApiTestData(42, "AmazonNew");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameAmazon);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.AMAZON_NEW_PRODUCT_CASHABACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();


			

		}

	}

	/**
	 * 
	 * Updating flipkart product cashback URL in affiliate nw admin panel
	 * Product URL: {PRDURL}&affid=rohanpouri&affExtParam1={EXTID}&aid={AFFLTID}
	 *
	 */
	@Test(priority=4, description="Updating flipkart product cashback URL in affiliate nw admin panel")	
	public void configurationFlipKartNewCashbackURL() throws InterruptedException, AWTException{

		try {

			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameFlipkart = Utils.getRestApiTestData(42, "FlipKart");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameFlipkart);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.FLIPKART_PRODUCT_CASHBACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		} catch (Exception e) {



			/**************************************************************** STORE CREATION STARTED ***************************************************************************/
			/******************************************************************** OBJECT CREATION ******************************************************************************/

			Login objLogin = new Login(driver, logger); 
			AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
			
			/******************************************************************** VARIABLE CREATION ******************************************************************************/

			String adminURL	= Utils.getConfigurationSetupTestData(0, "admin_url");
			String adminDropDown = Utils.getConfigurationSetupTestData(0, "dropDown");
			String adminUsername = Utils.getConfigurationSetupTestData(0, "admin_username");
			String adminPassword = Utils.getConfigurationSetupTestData(0, "admin_password");
			String storeNameFlipkart = Utils.getRestApiTestData(42, "FlipKart");
			
			/*********************************************************************** LOGIN PAGE*********************************************************************************/

			// Redirecting to admin panel
			Utils.loadURL(driver, adminURL);
			
			Thread.sleep(500);
			
			objLogin.selectValueAdminDropdown(driver, adminDropDown);
			objLogin.enterUserName(driver, adminUsername);
			objLogin.enterPassword(driver, adminPassword);
			objLogin.clickLogin(driver);

			objAdminCoreFunction.clickOnSettings();
			objAdminCoreFunction.clickOnAffiliateNetworks();
			Thread.sleep(500);
			objAdminCoreFunction.editTheAffiliateNetwork(storeNameFlipkart);
			Thread.sleep(500);
			objAdminCoreFunction.enterProductCashbackURL(MakeLinkUrls.FLIPKART_PRODUCT_CASHBACK_URL);
			objAdminCoreFunction.clickOnSubmitButton();
			objAdminCoreFunction.successMessageValidation();

		}

	}

	/**
	 * 
	 * Passing newly created store ID in post data and validating 200 status code with its response
	 *
	 */
	@Test(priority=5, description="Passing newly created store ID in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingStoreIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);
		
		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing newly created voucher ID in post data and validating 200 status code with its response
	 *
	 */
	@Test(priority=6, description="Passing newly created voucher ID in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingVoucherIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing newly created product ID in post data and validating 200 status code with its response
	 *
	 */
	@Test(priority=7, description="Passing newly created product ID in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingProductIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID					= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing amazon product link URL without tags in post data and validating 200 status code with its response
	 * Amazon product URL: https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031
	 *
	 */
	@Test(priority=8, description= "Passing amazon product link URL without tags in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonProductLinkWithoutTagsCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDPRODUCTURL;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid ipaddress in post data and validating 422 status code with its response
	 * ipaddress: 10.10.10.101010
	 *
	 */
	@Test(priority=9, description="Passing invalid ipaddress in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingInvalidIPAddressCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_Valid");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		ipAddress = Utils.getRestApiTestData(10, "invalid_ipaddress");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing non working domain URL in post data and validating 400 status code with its response
	 * Non working domain URL: https://www.google.com, https://www.amazon.comm, https://www.flipkart.co, https://www.cashkaro.com, https://www.myntraa.com, http://fkrt.ito/3IH~b2NNNN, https://amzn.tio/2FphiBB, https://www.45amazon.in & https://amazon.org
	 *
	 */
	@Test(priority=10, description = "Passing non working domain URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingNonWorkingDomainURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		String link = Utils.getRestApiTestData(42, "non_working_domain_url_1");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_2");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_3");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_4");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_5");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_6");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_7");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_8");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "non_working_domain_url_9");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "invalid_link_format_9");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = MakeLinkUrls.DOUBLEQUESTIONMARK_INVALID_URL;
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid URL format in post data and validating 422 status code with its response
	 * Invalid URL format: 55https://..com, http//:..cmo, 127.0.0.1, hptts://amazon.com, https://www. amazon .in, https:// www.amazon.in, amazon.in, http://www..com
	 *
	 */
	@Test(priority=11, description = "Passing invalid URL format in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingInvalidLinkFormatCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_link_format");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_link_format");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_link_format");
		String errorPointer				= Utils.getRestApiTestData(42, "pointer_invalid_link_format");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		String link = Utils.getRestApiTestData(42, "invalid_link_format_1");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_2");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_3");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_4");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_5");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_6");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);

		link = Utils.getRestApiTestData(42, "invalid_link_format_8");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing amazon product link URL with tags in post data and validating 200 status code with its response
	 * Amazon product URL: https://www.amazon.in/gp/product/B00KAUFMQC/ref=s9_acsd_al_bw_c_x_5_w?pf_rd_m=A1K21FY43GMZF8&pf_rd_s=merchandised-search-5&pf_rd_r=M7HJTAWSWTRT4EGVC8CX&pf_rd_t=101&pf_rd_p=d16aaf23-0405-4b0c-83b2-c37c0c9137ad&pf_rd_i=6802110031&tag=coupo07-21&ascsubtag=CU3265d8c9d45cd1
	 *
	 */
	@Test(priority=12, description = "Passing amazon product link URL with tags in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonProductLinkWithTagsCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDPRODUCTURLWITHTAGS;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing flipkart product link URL having coupondunia tags in post data and validating 200 status code with its response
	 * Flipkart product URL: https://www.flipkart.com/?affid=contact&affExtParam1=CU79a9ea080f05ba
	 *
	 */
	@Test(priority=13, description = "Passing flipkart product link URL having coupondunia tags in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingFlipkartProductLinkWithCouponDuniaTagsCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "flipkart");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.FLIPKARTMAKELINKSWITHCOUOPONDUNIATAG_AFTERREPLACED + exitClick + "&aid=rohanpouri");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing flipkart product link URL without tags in post data and validating 200 status code with its response
	 * Flipkart product URL: https://www.flipkart.com/lg-6-2-kg-inverter-fully-automatic-top-load-silver-white/p/itmewv2u8wddak9h?pid=WMNEWV2UQWXURHJQ&srno=b_1_3&otracker=hp_bannerads_4_deskt-homep-3bcff_Whirlpool_70N0DC8LWDVG&lid=LSTWMNEWV2UQWXURHJQ1C8VIZ&fm=organic&iid=e8d169f4-d3f3-4f04-a1a8-5dc3f28a4d0a.WMNEWV2UQWXURHJQ.SEARCH&ppt=Homepage&ppn=Homepage&ssid=vn0tor8yts0000001555309381050
	 *
	 */
	@Test(priority=14, description = "Passing flipkart product link URL without tags in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingFlipkartProductLinkWithoutTagsCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.FLIPKARTMAKELINK;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "flipkart");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.FLIPKARTMAKELINK_SELLER + exitClick + "&aid=rohanpouri");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing non working shorten domain URL in post data and validating 400 status code with its response
	 * Non working domain shorten URL: http://bit.ly/2UCExCF, http://tiny.cc/e3634y, http://bit.do/ePaCA, https://rebrand.ly/e8e42, https://urlzs.com/TkAL & http://tinyurl.com/y6wpyvl7
	 *
	 */
	@Test(priority=15, description = "Passing non working shorten domain URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingNonWorkingDomainShortenURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		String link = Utils.getRestApiTestData(42, "shortenbitly_google_Invalid");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortentinycc_google_Invalid");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenbitdo_google_Invalid");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenrebrandly_google_Invalid");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortenurlZs_google_Invalid");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		link = Utils.getRestApiTestData(42, "shortentinyURL_google_Invalid");
		
		createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);

		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing amazon bitly URL in post data and validating 200 status code with its response
	 * Bitly URL: https://amzn.to/2UCs5mf
	 *
	 */
	@Test(priority=16, description = "Passing amazon bitly URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonBitlyProductLinkShortenURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_BITLY;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bitdo URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://bit.do/ePp7p
	 *
	 */
	@Test(priority=17, description = "Passing bitdo URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonBitdoProductLinkShortenURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_BITDO;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bit tiny cc URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://tiny.cc/xwm74y
	 *
	 */
	@Test(priority=18, description = "Passing bit tiny cc URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonBitTinyCCProductLinkShortenURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_TINYCC;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing bit tiny URL in post data and validating 200 status code with its response
	 * Bitdo URL: http://tinyurl.com/y2jn3m48
	 *
	 */
	@Test(priority=19, description = "Passing bit tiny URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingAmazonBitTinyURLProductLinkShortenURLCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		AdminCommonFunctions objAdminCommonFunctions = new AdminCommonFunctions(driver, logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.VALIDSHORTENURLWITHTAGS_TINYURL;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String adminURL					= Utils.getConfigurationSetupTestData(0, "admin_url");
		String adminDropDown 			= Utils.getConfigurationSetupTestData(0, "dropDown");
		String adminUsername 			= Utils.getConfigurationSetupTestData(0, "admin_username");
		String adminPassword 			= Utils.getConfigurationSetupTestData(0, "admin_password");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "amazon");
		
		String sellerURL = Utils.getCurrentURL(driver);
		
		objAdminCommonFunctions.redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		String exitClick = objAdminCommonFunctions.getExitClick(email);
		
		objCreateShareLink.assertProfitLinkURLWithSellerLandedURL(sellerURL, MakeLinkUrls.VALIDPRODUCTURL_SELLER + exitClick);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra product URL in post data and validating 200 status code with its response
	 * Myntra URL: https://www.myntra.com/bedsheets/storyhome/storyhome-teal-blue--white-flat-120-tc-cotton-1-queen-bedsheet-with-2-pillow-covers/6786809/buy
	 *
	 */
	@Test(priority=20, description = "Passing myntra product URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingMyntraProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.MYNTRAVALIDURL;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra product shorten URL in post data and validating 200 status code with its response
	 * Myntra URL: https://bit.ly/2VOrIRU
	 *
	 */
	@Test(priority=21, description = "Passing myntra product shorten URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingMyntraShortenProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.MYNTRAVALIDURL_BITLY;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product URL one valid other invalid URL in post data and validating 200 status code with its response
	 * Multiple URL: https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.myntra.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=22, description = "Passing multiple product URL one valid other invalid URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingMyntraMultipleProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.MYNTRAMULTIPLEURLWITHONLYONEVALID;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product shorten URL having one valid and other invalid in post data and validating 200 status code with its response
	 * Multiple URL: https://bit.ly/2KHSJph
	 *
	 */
	@Test(priority=23, description = "Passing multiple product shorten URL having one valid and other invalid in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingMyntraMultipleShortenProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.MYNTRAMULTIPLEURLWITHONLYONEVALID_BILTY;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "myntra");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing jabong product URL in post data and validating 200 status code with its response
	 * Jabong URL: https://www.jabong.com/roadster-navy-blue-colourblocked-regular-fit-polo-t-shirt-4828564.htm?pos=2
	 *
	 */
	@Test(priority=24, description = "Passing jabong product URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingJabongProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGVALIDURL;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing jabong product shorten URL in post data and validating 200 status code with its response
	 * Jabong URL: https://bit.ly/2VOwRtg
	 *
	 */
	@Test(priority=25, description = "Passing jabong product shorten URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingJabongShortenProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGVALIDURL_BITLY;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple jabong product URL one valid and other invalid URL in post data and validating 200 status code with its response
	 * Jabong URL: https://jabong.go2affise.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=26, description = "Passing multiple jabong product URL one valid and other invalid URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingJabongMultipleProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGMULTIPLEURLWITHONLYONEVALID;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple jabong product shorten URL having one valid and other invalid URL in post data and validating 200 status code with its response
	 * Jabong URL: https://bit.ly/2D4rKOf
	 *
	 */
	@Test(priority=27, description = "Passing multiple jabong product shorten URL having one valid and other invalid URL in post data and validating 200 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingJabongMultipleShortenProductLinkCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGMULTIPLEURLWITHONLYONEVALID_BILTY;
		String typeUser					= Utils.getRestApiTestData(42, "type_user");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode200);
		
		objCreateShareLink.validateCreateSharedLinkSuccessResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, typeUser);

		String shortLinkURL = objCreateShareLink.getShortURL(createShareLinkResponse);

		Utils.loadURL(driver, shortLinkURL);
		
		objCreateShareLink.waitingTillRequiredURLVisible(driver, "jabong");
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product URL in post data and validating 400 status code with its response
	 * Jabong URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=28, description = "Passing myntra & jabong product URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingTooManyLongValidURLErrorWhenHavingTooManyValidURLWithDifferentDomainCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGANDMYNTRA_TWO_VALID_URL;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product shorten URL in post data and validating 400 status code with its response
	 * Jabong URL: https://bit.ly/2GaXuSw
	 *
	 */
	@Test(priority=29, description = "Passing myntra & jabong product shorten URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithDifferentDomainCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.JABONGANDMYNTRA_TWO_VALID_URL_BITLY;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing multiple product URL in post data and validating 400 status code with its response
	 * Multiple URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=30, description = "Passing multiple product URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingTooManyLongValidURLErrorWhenHavingTooManyValidURLWithSameDomainCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing myntra & jabong product URL in post data and validating 400 status code with its response
	 * Multiple URL: https://myntra.com/click?pid=6&offer_id=1&sub1=69e421139ec9664d7c32eb6496e615d2&sub2=296692&path=https%3A%2F%2Fwww.jabong.com%2Froadster-maroon-striped-polo-collar-tshirt-
	 *
	 */
	@Test(priority=31, description = "Passing myntra & jabong product URL in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingTooManyShortenValidURLErrorWhenHavingTooManyValidURLWithSameDomainCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= MakeLinkUrls.HAVING_MULTIPLE_URLS_WITH_SAME_VALID_DOMAIN_BITLY;
		String errorStatus				= Utils.getRestApiTestData(42, "status_too_may_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_too_may_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_too_may_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_too_may_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_too_may_url");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for ipaddress in post data and validating 422 status code with its response
	 * ipaddress: ""
	 *
	 */
	@Test(priority=32, description = "Passing empty string for ipaddress in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseMandatoryIPAddressCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(10, "status");
		String errorTitle				= Utils.getRestApiTestData(10, "title");
		String errorDetail				= Utils.getRestApiTestData(10, "ip_detail_mandatory");
		String errorPointer				= Utils.getRestApiTestData(10, "ip_pointer");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		ipAddress = Utils.getRestApiTestData(8, "emptyValue");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string for type in post data and validating 422 status code with its response
	 * Type: ""
	 *
	 */
	@Test(priority=33, description = "Passing empty string for type in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseMandatoryTypeCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(11, "type_status");
		String errorTitle				= Utils.getRestApiTestData(11, "type_title");
		String errorDetail				= Utils.getRestApiTestData(11, "type_detail");
		String errorPointer				= Utils.getRestApiTestData(11, "type_pointer");
		String typeCreateShareLink		= Utils.getRestApiTestData(8, "emptyValue");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid device type and validating 400 status code with its response
	 * device type: desk
	 *
	 */
	@Test(priority=33, description = "Passing invalid device type and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterPassingInvalidDeviceTypeCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(10, "status_invalid_device");
		String errorCode				= Utils.getRestApiTestData(10, "code_invalid_device");
		String errorTitle				= Utils.getRestApiTestData(10, "title_invalid_device");
		String errorDetail				= Utils.getRestApiTestData(10, "detail_invalid_device");
		String errorParameter			= Utils.getRestApiTestData(10, "parameter_invalid_device");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		deviceType = Utils.getRestApiTestData(4, "invalid_desktop");
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty header and validating 401 status code with its response
	 *
	 */
	@Test(priority=34, description = "Passing empty header and validating 401 status code with its response")
	public void validateEmptyHeaderCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
		// Assigning values
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String deviceType				= Utils.getRestApiTestData(4, "desktop");
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, deviceType, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=35, description = "Passing invalid auth type and validating 401 status code with its response")
	public void validateInvalidAuthorizationKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String errorMessage				= Utils.getRestApiTestData(12, "message_invalid_json");
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		accessTokenValue = Utils.getRestApiTestData(0, "auth_invalid_value");
		
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorMessage);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=36, description = "Passing invalid content type and validating 415 status code with its response")
	public void validateInvalidContentTypeKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_invalid_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid accept type and validating 415 status code with its response
	 *
	 */
	@Test(priority=37, description = "Passing invalid accept type and validating 415 status code with its response")
	public void validateInvalidAcceptTypeKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_invalid_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string auth type and validating 401 status code with its response
	 *
	 */
	@Test(priority=38, description = "Passing empty string auth type and validating 401 status code with its response")
	public void validateEmptyAuthorizationKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode401				= Integer.parseInt(Utils.getRestApiTestData(9, "status_401"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorMessage				= Utils.getRestApiTestData(12, "message");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		accessTokenValue = Utils.getRestApiTestData(8, "emptyValue");

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode401);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorMessage);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string accept type and validating 406 status code with its response
	 *
	 */
	@Test(priority=39, description = "Passing empty string accept type and validating 406 status code with its response")
	public void validateEmptyAcceptTypeKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode406				= Integer.parseInt(Utils.getRestApiTestData(9, "status_406"));
		String acceptValue				= Utils.getRestApiTestData(8, "emptyValue");
		String contentTypeValue			= Utils.getRestApiTestData(0, "content_type_value");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status_406");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title_notacceptable");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail_accept_header");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode406);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing empty string content type and validating 415 status code with its response
	 *
	 */
	@Test(priority=40, description = "Passing empty string content type and validating 415 status code with its response")
	public void validateEmptyContentTypeKeyValueCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		int statusCode415				= Integer.parseInt(Utils.getRestApiTestData(9, "status_415"));
		String acceptValue				= Utils.getRestApiTestData(0, "accept_value");
		String contentTypeValue			= Utils.getRestApiTestData(8, "emptyValue");
		String errorStatus				= Utils.getRestApiTestData(12, "error_status");
		String errorTitle				= Utils.getRestApiTestData(12, "error_title");
		String errorDetail				= Utils.getRestApiTestData(12, "error_detail");
		String errorLinks				= Utils.getRestApiTestData(12, "error_about");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);

		// New User Signup - POST Request
		Response CreateSharedLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, acceptValue, contentTypeValue, deviceType, statusCode415);
		
		// Validate SignupV1WithOTP success response - POST Request
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, CreateSharedLinkResponse, errorLinks, errorStatus, errorTitle, errorDetail);
		
		// Collecting all the soft assert values
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid store ID in post data and validating 422 status code with its response
	 *
	 */
	@Test(priority=41, description = "Passing newly invalid store ID in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseAfterInvalidStoreIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.generateRandomAlphabets(5);
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_store_id_must_be_integer");
		String errorTitle				= Utils.getRestApiTestData(42, "title_store_id_must_be_integer");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_store_id_must_be_integer");
		String errorPointer				= Utils.getRestApiTestData(42, "pointer_store_id_must_be_integer");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid voucher ID in post data and validating 422 status code with its response
	 *
	 */
	@Test(priority=42, description = "Passing newly invalid voucher ID in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseAfterInvalidVoucherIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.generateRandomAlphabets(5);
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_voucher_id_must_be_integer");
		String errorTitle				= Utils.getRestApiTestData(42, "title_voucher_id_must_be_integer");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_voucher_id_must_be_integer");
		String errorPointer				= Utils.getRestApiTestData(42, "pointer_voucher_id_must_be_integer");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing invalid product ID in post data and validating 422 status code with its response
	 *
	 */
	@Test(priority=43, description = "Passing newly invalid product ID in post data and validating 422 status code with its response")
	public void validateCreateShareLinkResponseAfterInvalidProductIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode422				= Integer.parseInt(Utils.getRestApiTestData(9, "status_422"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= "@#$";
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_pdt_id_format_invalid");
		String errorTitle				= Utils.getRestApiTestData(42, "title_pdt_id_format_invalid");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_pdt_id_format_invalid");
		String errorPointer				= Utils.getRestApiTestData(42, "pointer_pdt_id_format_invalid");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productID, link, ipAddress, deviceType, statusCode422);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndPoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorTitle, errorDetail, errorPointer);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing inactive store ID in post data and validating 400 status code with its response
	 *
	 */
	@Test(priority=41, description = "Passing inactive store ID in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterInActiveStoreIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeIDTwo, voucherID, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	/**
	 * 
	 * Passing inactive voucher ID in post data and validating 400 status code with its response
	 *
	 */
	@Test(priority=42, description = "Passing inactive voucher ID in post data and validating 400 status code with its response")
	public void validateCreateShareLinkResponseAfterInActiveVoucherIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String productID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeIDTwo, voucherIDTwo, productID, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

	//@Test(priority=43)
	public void validateCreateShareLinkResponseAfterInActiveProductIDCreateShareLinkEndpoint() throws InterruptedException, AWTException {

		// Object creations
		SoftAssert objSoftAssertion = new SoftAssert();
		HelperAccessToken objHelperAccessToken = new HelperAccessToken(logger);
		SignUpOTP objSignUpOTP = new SignUpOTP(logger);
		SignUpV1WithOTP objSignUpV1WithOTP = new SignUpV1WithOTP(logger);
		CreateShareLink objCreateShareLink = new CreateShareLink(logger);
		
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
		int statusCode400				= Integer.parseInt(Utils.getRestApiTestData(9, "status_400"));
		String baseURL					= ConfigurationSetup.DEKSTOPDOMAINURL + RestAssured.basePath;
		String type						= Utils.getRestApiTestData(10, "type");
		String storeID					= Utils.getRestApiTestData(8, "emptyValue");
		String voucherID				= Utils.getRestApiTestData(8, "emptyValue");
		String link						= Utils.getRestApiTestData(8, "emptyValue");
		String typeCreateShareLink		= Utils.getRestApiTestData(42, "type_create_share_link");
		String errorStatus				= Utils.getRestApiTestData(42, "status_invalid_retailer_url");
		String errorCode				= Utils.getRestApiTestData(42, "code_invalid_retailer_url");
		String errorTitle				= Utils.getRestApiTestData(42, "title_invalid_retailer_url");
		String errorDetail				= Utils.getRestApiTestData(42, "detail_invalid_retailer_url");
		String errorParameter			= Utils.getRestApiTestData(42, "parameter_invalid_retailer_url");

		Response helperAccessTokenResponse	= objHelperAccessToken.getAccessTokenHelperEndPoint(baseURL, authType, statusCode200); 

		String  accessTokenValue = objHelperAccessToken.getAccessToken(helperAccessTokenResponse);
		
		Object[] signUpOTPResponse = objSignUpOTP.getGUIDHelperEndPoint(baseURL, accessTokenValue, typeUserOTP, mobileNumber, statusCode201);

		String guid = (String) signUpOTPResponse[1] ;

		String signUpOTP = objSignUpV1WithOTP.getOTPForMobileNumber(mobileNumber);

		Response signUpV1WithOTPResponse = objSignUpV1WithOTP.newUserSignUpHelperEndPoint(baseURL, accessTokenValue, typeAuth, fullName, email, password, mobileNumber, guid, signUpOTP, referralID, deviceType, ipAddress, statusCode201);
		
		objSignUpV1WithOTP.validateSignUpV1WithOTPSucessResponseHelperEndPoint(objSoftAssertion, signUpV1WithOTPResponse, type, fullName);
		
		accessTokenValue = objSignUpV1WithOTP.getAccessToken(signUpV1WithOTPResponse);
		
		Response createShareLinkResponse = objCreateShareLink.getCreateShareLinkUserEndpoint(baseURL, accessTokenValue, typeCreateShareLink, storeID, voucherID, productIDTwo, link, ipAddress, deviceType, statusCode400);
		
		objCreateShareLink.validateCreateSharedLinkErrorResponseUserEndpoint(objSoftAssertion, createShareLinkResponse, errorStatus, errorCode, errorTitle, errorDetail, errorParameter);
		
		objSoftAssertion.assertAll();

	}

}