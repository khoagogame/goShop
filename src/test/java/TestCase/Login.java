package TestCase;

import Commons.AbstractTest;
import PageObject.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;


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
        System.out.println(loginPage.getHtml5ValidationMessage(driver,"//input[@id='email']"));
    }
}
