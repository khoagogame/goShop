package TestCase;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import Commons.PageGenerator;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageUI.LoginPageUI;
import org.apache.commons.logging.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;

    @Parameters({"browser"})
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);
        loginPage = openLoginPage(driver);
    }

    @Test
    public void TC01_Check_Email_Requirement() {
        log.info("TC01_Check_Email_Requirement: Verify tooltip alert display when user let Email empty");
        loginPage.clickLoginButton();
//        verifyEquals(loginPage.getToolTipAlert("email"), "Please fill out this field.");
        Assert.assertEquals(loginPage.getToolTipAlert("email"), "Please fill out this field.");

        log.info("TC01_Check_Email_Requirement: Verify incorrect email format 'text'");
        loginPage.inputTextToTextboxByTitle("email","text");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The email must be a valid email address.");

        log.info("TC01_Check_Email_Requirement: Verify incorrect email format 'text@'");
        loginPage.inputTextToTextboxByTitle("email","text@");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The email must be a valid email address.");

        log.info("TC01_Check_Email_Requirement: Verify incorrect email format 'text@text'");
        loginPage.inputTextToTextboxByTitle("email","text@text");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"Incorrect email address or password.");

        log.info("TC01_Check_Email_Requirement: Verify incorrect email format 'text@text.'");
        loginPage.inputTextToTextboxByTitle("email","text@text.");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The email must be a valid email address.");

        log.info("TC01_Check_Email_Requirement: Verify incorrect email format 'text@text.text@'");
        loginPage.inputTextToTextboxByTitle("email","text@text.text@");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The email must be a valid email address.");

        log.info("TC01_Check_Email_Requirement: Verify warning message not display after click on close button");
        loginPage.clickOnWarningMessageCloseButton();
        verifyFalse(loginPage.isWarningMessageDisplay());
    }

    @Test
    public void TC02_Check_PASSWORD_Requirement() {
        log.info("TC02_Check_PASSWORD_Requirement: Verify tooltip alert display when user let Email empty");
        loginPage.inputTextToTextboxByTitle("email","text@text.text");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getToolTipAlert("password"), "Please fill out this field.");

        log.info("TC02_Check_PASSWORD_Requirement: Password less than 8 characters");
        loginPage.inputTextToTextboxByTitle("email","text@text.text");
        loginPage.inputTextToTextboxByTitle("password", "1");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The password must be at least 8 characters.");

        log.info("TC02_Check_PASSWORD_Requirement: Password less than 8 characters");
        loginPage.inputTextToTextboxByTitle("email","text@text.text");
        loginPage.inputTextToTextboxByTitle("password", "1234567");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"The password must be at least 8 characters.");

        log.info("TC02_Check_PASSWORD_Requirement: Verify warning message not display after click on close button");
        loginPage.clickOnWarningMessageCloseButton();
        verifyFalse(loginPage.isWarningMessageDisplay());
    }

    @Test
    public void TC03_Check_Incorrect_Email() {
        log.info("TC03_Check_Incorrect_Email: input email = qatest@gmail.com, password = 12345678");
        loginPage.inputTextToTextboxByTitle("email", "qatest@gmail.com");
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"Incorrect email address or password.");

        log.info("TC03_Check_Incorrect_Email: Verify warning message not display after click on close button");
        loginPage.clickOnWarningMessageCloseButton();
        verifyFalse(loginPage.isWarningMessageDisplay());
    }

    @Test
    public void TC04_Check_Incorrect_Password() {
        log.info("TC04_Check_Incorrect_Password: input email = khoatran852005@live.com, password = 11111111");
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", "11111111");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"Incorrect email address or password.");

        log.info("TC04_Check_Incorrect_Password: Verify warning message not display after click on close button");
        loginPage.clickOnWarningMessageCloseButton();
        verifyFalse(loginPage.isWarningMessageDisplay());
    }

    @Test
    public void TC05_Login_Unpublish_Account() {
        log.info("TC05_Login_Unpublish_Account: login un-publish account ");
        loginPage.inputTextToTextboxByTitle("email", "vinabook001@gmail.com");
        loginPage.inputTextToTextboxByTitle("password", "12345678");
        loginPage.clickLoginButton();
        verifyEquals(loginPage.getWarningMessage(),"Incorrect email address or password.");

        log.info("TC05_Login_Unpublish_Account: Verify warning message not display after click on close button");
        loginPage.clickOnWarningMessageCloseButton();
        verifyFalse(loginPage.isWarningMessageDisplay());
    }

//    @Test
    public void TC06_Verify_Clicking_On_goShop_Logo_Will_Open_Partner_Sign_In_Page() {
        log.info("TC06_Verify_Clicking_On_goShop_Logo_Will_Open_Partner_Sign_In_Page: Click on goShop logo");
        loginPage.clickOnGoShopLogo();
        System.out.println(getCurrentURL(driver));
        log.info("TC06_Verify_Clicking_On_goShop_Logo_Will_Open_Partner_Sign_In_Page: Verify partner page login is opened");
        verifyEquals(loginPage.getCurrentURL(driver),"https://goshop-v2-dev.gogame.net/partner/login");
    }

    @Test
    public void TC07_Verify_Login_Success() {
        log.info("TC07_Verify_Login_Success: Login correct email & password");
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        dashboardPage = loginPage.clickLoginButton();

        log.info("TC07_Verify_Login_Success: Click on goShop logo");
        verifyEquals(dashboardPage.getCurrentURL(driver),"https://goshop-v2-dev.gogame.net/partner");
        verifyTrue(dashboardPage.isDashBoardPageTitleDisplay());
    }
}
