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
        //
    }
}
