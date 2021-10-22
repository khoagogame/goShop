package Commons;

import PageObject.*;
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
    public static ProductPageObject getProductPage(WebDriver driver){
        return new ProductPageObject(driver);
    }
    public static OrderPageObject getOrderPage(WebDriver driver){
        return new OrderPageObject(driver);
    }
    public static APIKeyPageObject getApiKeyPage(WebDriver driver){
        return new APIKeyPageObject(driver);
    }
}
