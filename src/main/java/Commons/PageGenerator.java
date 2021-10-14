package Commons;

import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageObject.ProfilePageObject;
import org.openqa.selenium.WebDriver;

public class PageGenerator {
    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static DashboardPageObject getDashboardPage(WebDriver driver){
        return new DashboardPageObject(driver);
    }

    public static ProfilePageObject getProfilePage(WebDriver driver){
        return new ProfilePageObject(driver);
    }
}
