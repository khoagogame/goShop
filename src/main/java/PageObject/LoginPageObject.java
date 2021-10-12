package PageObject;

import Commons.AbstractPage;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public void clickLoginButton() {
        waitForElementClickable(driver,"//button");
        clickToElement(driver,"//button");
    }
}
