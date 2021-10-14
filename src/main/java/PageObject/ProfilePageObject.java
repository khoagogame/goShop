package PageObject;

import Commons.AbstractPage;
import PageUI.ProfilePageUI;
import org.openqa.selenium.WebDriver;

public class ProfilePageObject extends AbstractPage {
    WebDriver driver;
    public ProfilePageObject(WebDriver driver){
        this.driver = driver;
    }

    public boolean isPasswordButtonDisplay() {
        return isElementDisplay(driver, ProfilePageUI.PASSWORD_BUTTON);
    }

    public boolean isEditButtonDisplay() {
        return isElementDisplay(driver, ProfilePageUI.EDIT_BUTTON);
    }

    public boolean isInformationBoardDisplay() {
        return isElementDisplay(driver, ProfilePageUI.INFORMATION_BOARD);
    }
}
