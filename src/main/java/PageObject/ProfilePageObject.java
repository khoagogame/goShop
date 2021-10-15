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

    public void clickToPasswordButton() {
        clickToElement(driver,ProfilePageUI.PASSWORD_BUTTON);
    }

    public boolean isChangePasswordPageDisplay() {
        return isElementDisplay(driver,ProfilePageUI.CHANGE_PARTNER_PASSWORD_PAGE_TITLE);
    }

    public void inputTextToTextboxByTitle(String textboxTitle, String value) {
        inputText(driver,ProfilePageUI.INPUT_TEXTBOX_BY_TITLE,value, textboxTitle);
    }

    public void clickOnUpdateButton() {
        clickToElement(driver,ProfilePageUI.UPDATE_BUTTON);
    }

    public String getWarningMessage() {
        return getElementText(driver,ProfilePageUI.WARNING_MESSAGE);
    }

    public void clickToWarningMessageCloseButton() {
        clickToElement(driver,ProfilePageUI.WARNING_MESSAGE_CLOSE_BUTTON);
    }

    public boolean isWarningMessageDisplay() {
        return isElementDisplay(driver,ProfilePageUI.WARNING_MESSAGE);
    }

    public void clickToPartnerProfileHyperlink() {
        clickToElement(driver,ProfilePageUI.PARTNER_PROFILE_HYPERLINK);
    }

    public String getToolTipWarningMessageByBoxTitle(String boxTitle) {
        return getHtml5ValidationMessage(driver,ProfilePageUI.INPUT_TEXTBOX_BY_TITLE, boxTitle);
    }

    public void clickOnEditButton() {
        clickToElement(driver,ProfilePageUI.EDIT_BUTTON);
    }

    public boolean isEditProfilePageDisplay() {
        return isElementDisplay(driver,ProfilePageUI.EDIT_PARTNER_PROFILE_PAGE_TITLE);
    }

    public String getValueByTitle(String title) {
        return getElementText(driver,ProfilePageUI.INFO_VALUE_BY_TITLE_NAME, title);
    }
}
