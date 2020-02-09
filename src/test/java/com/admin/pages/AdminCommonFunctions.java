package com.admin.pages;

import java.awt.AWTException;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;

import com.ppa.api.utilities.Utils;
import com.relevantcodes.extentreports.ExtentTest;

public class AdminCommonFunctions extends Utils {
	
	/************************************************************** Constructor ***********************************************************************/

	public AdminCommonFunctions(WebDriver driver, ExtentTest logger) {

		this.driver = driver;
		this.logger = logger;
	}

	/******************************************************** Methods Creation started ****************************************************************/

	public void redirectToAdmin(String adminURL, String adminDropDown, String adminUsername, String adminPassword) throws InterruptedException {

		Login objLogin = new Login(driver, logger); 
		
		Utils.loadURL(driver, adminURL);
		
		objLogin.selectValueAdminDropdown(driver, adminDropDown);
		
		objLogin.enterUserName(driver, adminUsername);
		
		objLogin.enterPassword(driver, adminPassword);
		
		objLogin.clickLogin(driver);
		
	}

	public void setUserSignUpBonus(String email, String userReferralBonus) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Users objUsers = new Users(driver, logger);
		
		objDashboard.clickMainMenuUsers(driver);

		objAdminCoreFunction.clickUsersSubMenu();
		
		Thread.sleep(1500);
		
		objUsers.selectSearchByDropDown("User Email");
		
		objUsers.enterKeyword(email);
		
		objUsers.clickSubmitButton();
		
		objUsers.clickEditButton();
		
		Thread.sleep(1500);
		
		objUsers.enterReferralBonus(userReferralBonus);
		
		objUsers.clickSubmitButtonUserForm();
		
	}

	public void changeStatusInactiveForUser(String email) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Users objUsers = new Users(driver, logger);
		
		objDashboard.clickMainMenuUsers(driver);

		objAdminCoreFunction.clickUsersSubMenu();
		
		objUsers.selectSearchByDropDown("User Email");
		
		objUsers.enterKeyword(email);
		
		objUsers.clickSubmitButton();
		
		objUsers.clickEditButton();
		
		objUsers.selectStatusFromDropDown("In-Active");
		
		objUsers.clickSubmitButtonUserForm();
		
	}

	public void updateSignUpReferralBonusBonus(String frontEndURl, String signUpBonus, String referralSignUpBonus) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PartnerSettingsPage objPartnerSettingsPage = new PartnerSettingsPage(driver, logger);

		objDashboard.clickMainMenuSettings(driver);
		
		Thread.sleep(1500);

		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPartnerSettingsPage.enterSignUpBonus(signUpBonus);
		
		objPartnerSettingsPage.enterReferralSignUpBonus(referralSignUpBonus);
		
		objPartnerSettingsPage.clickSubmit();
		
	}

	public void setSignUpBonus(String frontEndURl, String signUpBonus) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PartnerSettingsPage objPartnerSettingsPage = new PartnerSettingsPage(driver, logger);

		objDashboard.clickMainMenuSettings(driver);

		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPartnerSettingsPage.enterSignUpBonus(signUpBonus);
		
		objPartnerSettingsPage.clickSubmit();
		
	}

	public void setReferralSignUpBonus(String frontEndURl, String referralSignUpBonus) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PartnerSettingsPage objPartnerSettingsPage = new PartnerSettingsPage(driver, logger);

		objDashboard.clickMainMenuSettings(driver);

		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPartnerSettingsPage.enterReferralSignUpBonus(referralSignUpBonus);
		
		objPartnerSettingsPage.clickSubmit();
		
	}

	public void updateSignUpBonus(String frontEndURl, String signUpBonus) throws InterruptedException, AWTException {

		PartnerSettingsPage objPartnerSettingsPage = new PartnerSettingsPage(driver, logger);
		
		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPartnerSettingsPage.enterSignUpBonus(signUpBonus);
		
		objPartnerSettingsPage.clickSubmit();
		
	}

	public void updateReferralSignUpBonus(String frontEndURl, String referralSignUpBonus) throws InterruptedException, AWTException {

		PartnerSettingsPage objPartnerSettingsPage = new PartnerSettingsPage(driver, logger);

		objPartnerSettingsPage.clickEditPartnerDetails(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPartnerSettingsPage.enterReferralSignUpBonus(referralSignUpBonus);
		
		objPartnerSettingsPage.clickSubmit();
		
	}

	public void setRequiredPaymentActive(String frontEndURl) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		PaymentSettingsPage objPaymentSettingsPage = new PaymentSettingsPage(driver, logger);
		
		objDashboard.clickMainMenuSettings(driver);
		
		Thread.sleep(1500);

		objAdminCoreFunction.clickOnPartnerSettings();
		
		objPaymentSettingsPage.clickEditPaymentMethod(driver, StringUtils.substringAfter(frontEndURl, "://"));
		
		objPaymentSettingsPage.editPaymentMethod_Select_Status(1, "Active");
		
		objPaymentSettingsPage.dropDown_Select_CashbackType(1, "All");
		
		objPaymentSettingsPage.editPaymentMethod_Select_Status(3, "Active");
		
		objPaymentSettingsPage.dropDown_Select_CashbackType(3, "All");
		
		objPaymentSettingsPage.editPaymentMethod_Select_Status(12, "Active");
		
		objPaymentSettingsPage.dropDown_Select_CashbackType(12, "All");
		
		objPaymentSettingsPage.clickOnUpdatePaymentSettingsButton();
		
	}

	public String getExitClick(String email) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Reports objReports = new Reports(driver, logger);
		
		objAdminCoreFunction.clickOnReports(driver);
		
		objReports.clickExitClickSubMenu(driver);
		
		Thread.sleep(1500);
		
		objReports.selectSearchByDropDown(driver, "User Email");
		
		objReports.enterKeyword(driver, email);
		
		objReports.clickSubmit(driver);
		
		String exitClick = objReports.extractExitClickValueFromResultstableFirstRow(driver);
		
		return exitClick;
	
	}

	public String getExitClickAfterClickingExpandCollapse(String email) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Reports objReports = new Reports(driver, logger);
		
		objAdminCoreFunction.clickOnReports(driver);
		
		objReports.clickExitClickSubMenu(driver);
		
		Thread.sleep(1500);
		
		objReports.clickExpandCollapseButton(driver);
		
		objReports.selectSearchByDropDown(driver, "User Email");
		
		objReports.enterKeyword(driver, email);
		
		objReports.clickSubmit(driver);
		
		String exitClick = objReports.extractExitClickValueFromResultstableFirstRow(driver);
		
		return exitClick;
	
	}

	public String getExitClickBasedOnIndex(String index) throws InterruptedException, AWTException {

		Reports objReports = new Reports(driver, logger);
		
		String exitClick = objReports.extractExitClickValueFromResultstableFirstRow(driver, index);
		
		return exitClick;
	
	}

	public String setStatusPendingForExitClick(String exitClick, String cashbackType, String orderValue, String commision, String cashback) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		Cashback objCashback = new Cashback(driver, logger);
		BonusApprovalConfiguration objBonusApprovalConfiguration = new BonusApprovalConfiguration(driver, logger);
		CashbackApprovalConfiguration objCashbackApprovalConfiguration = new CashbackApprovalConfiguration(driver, logger);
		
		objAdminCoreFunction.clickCashbackMenu(driver);
		
		/*objBonusApprovalConfiguration.clickBonusApprovalConfigurationSubMenu(driver);
		
		Thread.sleep(1000);
		
		objBonusApprovalConfiguration.enterMinimumAmount("100000");
		
		objBonusApprovalConfiguration.clickSubmit(driver);
		
		objCashbackApprovalConfiguration.clickCashbackApprovalConfigurationSubMenu(driver);
		
		Thread.sleep(1000);
		
		objCashbackApprovalConfiguration.enterMinimumAmount("100000");
		
		objCashbackApprovalConfiguration.clickSubmit(driver);*/
		
		objCashback.clickCashbackSubMenu(driver);
		
		objCashback.clickAddNewButton(driver);
		
		objCashback.enterExitId(driver, exitClick);
		
		objCashback.entertransactionId(driver, exitClick);
		
		objCashback.enterOrderId(driver, exitClick);
		
		String transactionID = objCashback.getTransactionID(driver);
		
		objCashback.setOrderDate(driver);
		
		objCashback.enterOrderValue(driver, orderValue);
		
		objCashback.enterconfirmCommisionNetwork(driver, commision);
		
		objCashback.entercashback(driver, cashback);
		
		objCashback.selectCashbackTypeFromDropDown(driver, cashbackType);
		
		objCashback.selectCashbackStatusFromDropDown("Pending");
		
		objCashback.setPendingDate(driver);
		
		objCashback.enterDetails(driver, exitClick);
		
		objCashback.clickSubmit(driver);
		
		objCashback.validateSuccessMessage(driver);
		
		return transactionID.trim();
		
	}

	public String getCashbackID(String exitClick) throws InterruptedException, AWTException {

		Cashback objCashback = new Cashback(driver, logger);
		
		objCashback.selectSearchByStatusFromDropDown("Exit ID");
		
		objCashback.enterKeyword(exitClick);
		
		objCashback.selectCashbackStatusFromDropDown("Pending");
		
		objCashback.clickSubmit(driver);
		
		String cashbackID = objCashback.getCashbackID(driver);
		
		objCashback.clickClear(driver);
		
		return cashbackID;
		
	}

	public String getCashbackID(String email, String index) throws InterruptedException, AWTException {

		Cashback objCashback = new Cashback(driver, logger);
		
		objCashback.clickCashbackSubMenu(driver);

		objCashback.clickClear(driver);
		
		Thread.sleep(500);
		
		objCashback.selectSearchByStatusFromDropDown("User Email");
		
		objCashback.enterKeyword(email);
		
		objCashback.selectCashbackStatusFromDropDown("All Status");
		
		objCashback.clickSubmit(driver);
		
		String cashbackID = objCashback.getCashbackID(driver, index);
		
		objCashback.clickClear(driver);
		
		return cashbackID;
		
	}

	public void changeStatusPendingToConfirmForExitClick(String exitClick) throws InterruptedException, AWTException {

		Cashback objCashback = new Cashback(driver, logger);
		
		objCashback.clickCashbackSubMenu(driver);
		
		objCashback.selectSearchByStatusFromDropDown("Exit ID");
		
		objCashback.enterKeyword(exitClick);
		
		objCashback.selectCashbackStatusFromDropDown("Pending");
		
		objCashback.clickSubmit(driver);
		
		objCashback.clickEdit(driver);
		
		objCashback.selectCashbackStatusFromDropDown("Confirmed");
		
		objCashback.setConfirmDate(driver);
		
		objCashback.clickSubmit(driver);
		
		objCashback.validateSuccessMessage(driver);
		
	}

	public void changeStatusPendingToCancelForExitClick(String exitClick) throws InterruptedException, AWTException {

		Cashback objCashback = new Cashback(driver, logger);
		
		objCashback.clickCashbackSubMenu(driver);
		
		objCashback.selectSearchByStatusFromDropDown("Exit ID");
		
		objCashback.enterKeyword(exitClick);
		
		objCashback.selectCashbackStatusFromDropDown("Pending");
		
		objCashback.clickSubmit(driver);
		
		objCashback.clickEdit(driver);
		
		objCashback.selectCashbackStatusFromDropDown("Cancelled");
		
		objCashback.setFailDate(driver);
		
		objCashback.clickSubmit(driver);
		
		objCashback.validateSuccessMessage(driver);
		
		
	}

	public void uncheckAcceptMissingTicket(String storeID) throws InterruptedException, AWTException {

		Dashboard objDashboard = new Dashboard(driver, logger);
		Stores objAddStore = new Stores(driver, logger);
		
		objDashboard.clickMainMenuStore(driver);

		objAddStore.clickSubMenuStores(driver);
		
		Thread.sleep(2000);
		
		objAddStore.enterStoreName(driver, storeID);

		objAddStore.clickSearch(driver);
		
		objAddStore.clickEdit(driver);
		
		objAddStore.clickAcceptMissingTicket(driver, "uncheck");
		
		objAddStore.click_Button_AddStore(driver);
		
	}

	public void changeStatusForTicket(String exitClick, String adminReply, String status) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminFunctions = new AdminCoreFunction(driver, logger);	
		MissingCashback objMissingCashback = new MissingCashback(driver, logger);

		objAdminFunctions.clickOnsubMenuUserTickets();
		
		Thread.sleep(500);
		
		objMissingCashback.selectSearchbyExitClickIDFromDropDown();
		
		objMissingCashback.enerKeyWord(exitClick);
		
		objMissingCashback.clickOnSearchButton();
		
		objMissingCashback.clickOnEditButton(exitClick);

		Thread.sleep(500);
		
		objMissingCashback.enterAdminReply(adminReply);
		
		objMissingCashback.selectTicketStatus(status);
		
		objMissingCashback.clickOnSubmitButton();
		
		objAdminFunctions.clickOnsubMenuUserTickets();
		
		objMissingCashback.clickClear(driver);
		
	}

	public void changeStatusForTestimonials(String email, String status, String title) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminFunctions = new AdminCoreFunction(driver, logger);	
		Testimonial objTestimonial = new Testimonial(driver, logger);
		
		objAdminFunctions.clickOnInteractiveMainMenu();

		objAdminFunctions.clickOnSubMenuTestimonials();
		
		objTestimonial.selectValueInSearchByDropDown("User Email");
		
		objTestimonial.enterKeyWordAsUserEmail(email);
		
		objTestimonial.clickOnSearchButton();
		
		objTestimonial.clickOnEditButton();
		
		Thread.sleep(250);
		
		objTestimonial.changeStatus(status);
		
		objTestimonial.enterTitle(title);
		
		objTestimonial.clickOnSaveTestimonial();
		
	}

	public void changeStatusForTestimonialsAfterClickingExpandCollapse(String email, String status, String title) throws InterruptedException, AWTException {

		Testimonial objTestimonial = new Testimonial(driver, logger);
		Reports objReports = new Reports(driver, logger);

		Thread.sleep(500);
		
		objReports.clickExpandCollapseButton(driver);
		
		objTestimonial.selectValueInSearchByDropDown("User Email");
		
		objTestimonial.enterKeyWordAsUserEmail(email);
		
		objTestimonial.clickOnSearchButton();
		
		objTestimonial.clickOnEditButton();
		
		Thread.sleep(500);
		
		objTestimonial.changeStatus(status);
		
		objTestimonial.enterTitle(title);
		
		objTestimonial.clickOnSaveTestimonial();
		
	}

	public void createCashout(String email) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		
		PendingCashout objPendingCashout = new PendingCashout(driver, logger);
		
		objAdminCoreFunction.clickPendingCashoutsSubMenu();
		
		Thread.sleep(500);
		
		objPendingCashout.selectSearchByDropDown("User Email");
		
		objPendingCashout.enterKeyword(email);
		
		Thread.sleep(500);
		
		objPendingCashout.clickSubmit();
		
		Thread.sleep(500);
		
		objPendingCashout.clickEmail(email);
		
		Thread.sleep(500);
		
		objPendingCashout.clickCreateCashout();
		
		Thread.sleep(1200);
		
		driver.switchTo().alert().accept();
		
		objPendingCashout.validateSuccessMessage();
		
	}

	public void createCashoutAfterLoggingin(String adminURL, String adminDropDown, String adminUsername, String adminPassword, String email) throws InterruptedException, AWTException {

		AdminCoreFunction objAdminCoreFunction = new AdminCoreFunction(driver, logger);
		
		PendingCashout objPendingCashout = new PendingCashout(driver, logger);
		
		redirectToAdmin(adminURL, adminDropDown, adminUsername, adminPassword);
		
		objAdminCoreFunction.clickPendingCashoutsSubMenu();
		
		Thread.sleep(500);
		
		objPendingCashout.selectSearchByDropDown("User Email");
		
		objPendingCashout.enterKeyword(email);
		
		Thread.sleep(500);
		
		objPendingCashout.clickSubmit();
		
		Thread.sleep(500);
		
		objPendingCashout.clickEmail(email);
		
		Thread.sleep(500);
		
		objPendingCashout.clickCreateCashout();
		
		Thread.sleep(1200);
		
		driver.switchTo().alert().accept();
		
		objPendingCashout.validateSuccessMessage();
		
	}

}