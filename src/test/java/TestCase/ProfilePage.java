package TestCase;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageObject.ProfilePageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ProfilePage extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    ProfilePageObject profilePage;

    @Parameters({"browser"})
    @BeforeClass
    public void BeforeClass(String browserName){
        driver = getBrowserDriver(browserName);

        log.info("Precondition : Login partner account");
        loginPage = openLoginPage(driver);
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        dashboardPage = loginPage.clickLoginButton();
    }

    @Test
    public void TC01_Verify_Profile_Page_Is_Opened(){
        log.info("TC01_Verify_Profile_Page_Is_Opened: Click to profile icon");
        dashboardPage.clickOnPartnerName(driver);
        profilePage = dashboardPage.clickOnViewProfileIcon(driver);

        log.info("TC01_Verify_Profile_Page_Is_Opened: Verify profile page is opened");
        verifyTrue(profilePage.isPasswordButtonDisplay());
        verifyTrue(profilePage.isEditButtonDisplay());
        verifyTrue(profilePage.isInformationBoardDisplay());

    }

    @Test
    public void TC02_Verify_Password_Format_Requirements(){
        log.info("TC02_Verify_Password_Format_Requirements: Verify Change Password page display");
        profilePage.clickToPasswordButton();
        verifyTrue(profilePage.isChangePasswordPageDisplay());
        verifyEquals(profilePage.getCurrentURL(driver),"https://goshop-v2-dev.gogame.net/partner/change-password");

        log.info("TC02_Verify_Password_Format_Requirements: Verify Current password empty");
        profilePage.inputTextToTextboxByTitle("Current Password", "");
        profilePage.inputTextToTextboxByTitle("New Password", "12345678");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "12345678");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The current password field is required."));


        log.info("TC02_Verify_Password_Format_Requirements: Verify Current password length require");
        profilePage.inputTextToTextboxByTitle("Current Password", "123");
        profilePage.inputTextToTextboxByTitle("New Password", "12345678");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "12345678");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The current password must be at least 8 characters."));

        log.info("TC02_Verify_Password_Format_Requirements: Verify incorrect Current password input");
        profilePage.inputTextToTextboxByTitle("Current Password", "123456789");
        profilePage.inputTextToTextboxByTitle("New Password", "12345678");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "12345678");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("Current password does not match"));

        log.info("TC02_Verify_Password_Format_Requirements: Verify New Password empty");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "123456789");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The password field is required."));
        verifyTrue(profilePage.getWarningMessage().contains("Password and Confirm Password do not match"));


        log.info("TC02_Verify_Password_Format_Requirements: Verify New Password length require");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "123");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "123456789");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The password must be at least 8 characters."));
        verifyTrue(profilePage.getWarningMessage().contains("Password and Confirm Password do not match"));

        log.info("TC02_Verify_Password_Format_Requirements: Verify Confirm Password empty");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "123456789");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The password confirmation field is required."));


        log.info("TC02_Verify_Password_Format_Requirements: Verify Confirm Password length require");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "123456789");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "123");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The password confirmation must be at least 8 characters."));
        verifyTrue(profilePage.getWarningMessage().contains("Password and Confirm Password do not match"));

        log.info("TC02_Verify_Password_Format_Requirements: Verify Confirm Password is not match");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "123456789");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "123456788");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("Password and Confirm Password do not match"));

        log.info("TC02_Verify_Password_Format_Requirements: 3 all passwords empty");
        profilePage.inputTextToTextboxByTitle("Current Password", "");
        profilePage.inputTextToTextboxByTitle("New Password", "");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The current password field is required."));
        verifyTrue(profilePage.getWarningMessage().contains("The password field is required."));
        verifyTrue(profilePage.getWarningMessage().contains("The password confirmation field is required."));

        log.info("TC02_Verify_Password_Format_Requirements: input new password same as current password");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "12345678");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "12345678");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The new password must not be the same as the current password"));

        log.info("TC02_Verify_Password_Format_Requirements: verify warning message is close correctly");
        profilePage.clickToWarningMessageCloseButton();
        verifyFalse(profilePage.isWarningMessageDisplay());

    }

    @Test
    public void TC03_Verify_Password_Can_Be_Change_Successfully() {
        log.info("TC02_Verify_Password_Format_Requirements: Change password successfully");
        profilePage.inputTextToTextboxByTitle("Current Password", "12345678");
        profilePage.inputTextToTextboxByTitle("New Password", "123456789");
        profilePage.inputTextToTextboxByTitle("Confirm Password", "123456789");
        profilePage.clickOnUpdateButton();

        log.info("TC02_Verify_Password_Format_Requirements: verify password is changed");
        verifyTrue(profilePage.getWarningMessage().contains("Password changed successfully"));

        log.info("TC02_Verify_Password_Format_Requirements: verify warning message is close correctly");
        profilePage.clickToWarningMessageCloseButton();
        verifyFalse(profilePage.isWarningMessageDisplay());

        //change password to original
        profilePage.inputTextToTextboxByTitle("Current Password", "123456789");
        profilePage.inputTextToTextboxByTitle("New Password", GlobalConstant.PASSWORD);
        profilePage.inputTextToTextboxByTitle("Confirm Password", GlobalConstant.PASSWORD);
        profilePage.clickOnUpdateButton();


    }

    @Test
    public void TC04_Verify_Partner_Profile_HyperLink_Is_Clickable() {
        log.info("TC04_Verify_Partner_Profile_HyperLink_Is_Clickable: Click On partner profile hyperlink");
        profilePage.clickToPartnerProfileHyperlink();

        log.info("TC04_Verify_Partner_Profile_HyperLink_Is_Clickable: Verify partner profile page is opened");
        verifyTrue(profilePage.isPasswordButtonDisplay());
        verifyTrue(profilePage.isEditButtonDisplay());
        verifyTrue(profilePage.isInformationBoardDisplay());

    }

    @Test
    public void TC05_Verify_Edit_Partner_Fields_Requirement(){
        log.info("TC05_Verify_Edit_Partner_Fields_Requirement: Verify Edit profile page is opened");
        profilePage.clickOnEditButton();
        verifyTrue(profilePage.isEditProfilePageDisplay());
        verifyEquals(profilePage.getCurrentURL(driver), "https://goshop-v2-dev.gogame.net/partner/edit-profile");

        log.info("TC05_Verify_Edit_Partner_Fields_Requirement: Empty FullName");
        profilePage.inputTextToTextboxByTitle("FullName", "");
        profilePage.clickOnUpdateButton();
        verifyEquals(profilePage.getToolTipWarningMessageByBoxTitle("FullName"), "Please fill out this field.");

        log.info("TC05_Verify_Edit_Partner_Fields_Requirement: Empty Phone");
        profilePage.inputTextToTextboxByTitle("Phone", "");
        profilePage.clickOnUpdateButton();
        verifyEquals(profilePage.getToolTipWarningMessageByBoxTitle("Phone"), "Please fill out this field.");

        log.info("TC05_Verify_Edit_Partner_Fields_Requirement: Incorrect phone format");
        profilePage.inputTextToTextboxByTitle("FullName", "text");
        profilePage.inputTextToTextboxByTitle("Phone", "abc");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The phone format is invalid."));
        verifyTrue(profilePage.getWarningMessage().contains("The phone must be at least 9 characters."));


        profilePage.inputTextToTextboxByTitle("Phone", "0903910613a");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The phone format is invalid."));

        log.info("TC05_Verify_Edit_Partner_Fields_Requirement: less than length required : 9");
        profilePage.inputTextToTextboxByTitle("Phone", "12345678");
        profilePage.clickOnUpdateButton();
        verifyTrue(profilePage.getWarningMessage().contains("The phone must be at least 9 characters."));

    }
    @Test
    public void TC06_Verify_Edit_Partner_Successfully(){
        String fullName = "Trần Ngọc Khoa";
        String phone = "0903910613";
        String address = "29 cao thang";
        String website = "zing.vn";
        String notes = "QA notes";

        log.info("TC06_Verify_Edit_Partner_Successfully: Input all valid value to fields");
        profilePage.inputTextToTextboxByTitle("FullName", fullName);
        profilePage.inputTextToTextboxByTitle("Phone", phone);
        profilePage.inputTextToTextboxByTitle("Address", address);
        profilePage.inputTextToTextboxByTitle("Website", website);
        profilePage.inputTextToTextboxByTitle("Notes", notes);

        log.info("TC06_Verify_Edit_Partner_Successfully: Click on Update button");
        profilePage.clickOnUpdateButton();

        log.info("TC06_Verify_Edit_Partner_Successfully: Click on Partner profile hyperlink");
        profilePage.clickToPartnerProfileHyperlink();

        log.info("TC06_Verify_Edit_Partner_Successfully: Verify partner profile page is opened");
        verifyTrue(profilePage.isPasswordButtonDisplay());
        verifyTrue(profilePage.isEditButtonDisplay());
        verifyTrue(profilePage.isInformationBoardDisplay());

        log.info("TC06_Verify_Edit_Partner_Successfully: Verify all info are updated successfully");
        verifyEquals(profilePage.getValueByTitle("FullName"),fullName);
        verifyEquals(profilePage.getValueByTitle("Phone"),phone);
        verifyEquals(profilePage.getValueByTitle("Address"),address);
        verifyEquals(profilePage.getValueByTitle("Website"),website);
        verifyEquals(profilePage.getValueByTitle("Notes"),notes);

    }

}
