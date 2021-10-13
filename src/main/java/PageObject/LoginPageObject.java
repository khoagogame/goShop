package PageObject;

import Commons.AbstractPage;
import Commons.PageGenerator;
import PageUI.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends AbstractPage {
    WebDriver driver;
    public LoginPageObject(WebDriver driver){
        this.driver = driver;
    }

    public DashboardPageObject clickLoginButton() {
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getDashboardPage(driver);
    }


    public String getToolTipAlert(String textboxName) {
        return getHtml5ValidationMessage(driver,LoginPageUI.TEXTBOX_BY_TITLE, textboxName);
    }

    public void inputTextToTextboxByTitle(String textboxName, String value) {
        inputText(driver,LoginPageUI.TEXTBOX_BY_TITLE,value, textboxName);
    }


    public String getWarningMessage() {
        return getElementText(driver,LoginPageUI.WARNING_MESSAGE);
    }

    public void clickOnWarningMessageCloseButton() {
        clickToElement(driver,LoginPageUI.WARNING_MESSAGE_CLOSE_BUTTON);
    }

    public boolean isWarningMessageDisplay() {
        return isElementDisplay(driver,LoginPageUI.WARNING_MESSAGE);
    }

    public void clickOnGoShopLogo() {
        clickToElement(driver,LoginPageUI.GOSHOP_LOGO);
    }

    public void clickOnReMemberMeCheckBox() {
        clickToElement(driver,LoginPageUI.REMEMBER_CHECKBOX);
    }

    public String getTextInTextboxByTitle(String textboxName) {
        return getInnerTextinTextbox(driver,LoginPageUI.TEXTBOX_BY_TITLE, textboxName);
    }

}
