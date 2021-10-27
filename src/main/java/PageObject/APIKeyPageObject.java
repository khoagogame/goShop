package PageObject;

import Commons.AbstractPage;
import PageUI.APIKeyPageUI;
import PageUI.AbstractPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class APIKeyPageObject extends AbstractPage {
    WebDriver driver;

    public APIKeyPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isAPIKeyPageTitleDisplay() {
        String title = getElementText(driver, APIKeyPageUI.API_KEYS_PAGE_TITLE);
        return title.contains("Viewing APIs Keys of Partner");
    }

    public void clickOnAddNewButton() {
        clickToElement(driver, APIKeyPageUI.ADD_NEW_BUTTON);
    }

    public boolean isAddNewFormDisplay() {
        return isElementDisplay(driver, APIKeyPageUI.CREATE_NEW_API_KEY_FORM_TITLE);
    }

    public void inputTextToKeyName(String value) {
        inputText(driver, APIKeyPageUI.KEY_NAME_TEXT_BOX, value);
    }

    public void inputTextToListHost(String value) {
        inputText(driver, APIKeyPageUI.INPUT_LIST_HOST_TEXTAREA, value);
    }


    public void clickOnSubmitButton() {
        clickToElement(driver, APIKeyPageUI.SUBMIT_BUTTON);
    }


    public void selectStatusRadioButton(String buttonName) {
        clickToElement(driver, APIKeyPageUI.STATUS_RADIO_BUTTON, buttonName);
    }

    public String getKeyNameWarningMessage() {
        return getElementText(driver, APIKeyPageUI.KEY_NAME_WARNING);
    }

    public Object getHostNameWarningMessage() {
        return getElementText(driver, APIKeyPageUI.LIST_HOST_WARNING);
    }

    public Object getStatusWarningMessage() {
        return getElementText(driver, APIKeyPageUI.STATUS_WARNING);
    }

    public String getAPIKeyGeneratedSuccessfullyMessage() {
        return getElementText(driver, APIKeyPageUI.API_KEY_GENERATED_SUCCESSFULLY_MESSAGE);
    }

    public String getKeyNameOfNewestAPIKey() {
        List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_KEY_NAME);
        return elements.get(0).getText();
    }

    public String getHostNameOfNewestAPIKey() {
        List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_HOST_NAME);
        return elements.get(0).getText();
    }

    public String getStatusOfNewestAPIKey() {
        List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_API_STATUS);
        return elements.get(0).getText();
    }

    public void clickToEditButtonByAPIKeyName(String keyName) {
        clickToElement(driver, APIKeyPageUI.EDIT_BUTTON_BY_KEY_NAME, keyName);
    }

    public void clickToManageAPIKeysHyperlink() {
        clickToElement(driver, APIKeyPageUI.MANAGE_API_KEYS_HYPERLINK);
    }

    public boolean isAPIKeyNameDisplay(String keyName) {
        return isElementDisplay(driver, APIKeyPageUI.API_KEY_PAGE_KEY_NAME, keyName);
    }

    public void clickOnCancelButton() {
        clickToElement(driver, APIKeyPageUI.CANCEL_BUTTON);
    }

    public void clickOnDeleteButtonByKeyName(String keyName) {
        clickToElement(driver, APIKeyPageUI.DELETE_API_KEY_BUTTONS_BY_KEY_NAME, keyName);
    }

    public void clickOnDeleteConfirmationCancelButton() {
        clickToElement(driver, APIKeyPageUI.DELETE_CONFIRMATION_CANCEL_BUTTON);
    }

    public boolean isDeleteConfirmationPopupDisplay() {
        return isElementDisplay(driver, APIKeyPageUI.DELETE_API_KEY_CONFIRMATION_POPUP);
    }

    public void clickOnDeleteConfirmationOKButton() {
        clickToElement(driver, APIKeyPageUI.DELETE_CONFIRMATION_OK_BUTTON);
//        sleepInSecond(2);
    }

    public Object getDeleteAPIKeySuccessPopupMessage() {
        return getElementText(driver, APIKeyPageUI.DELETE_API_KEY_SUCCESS_MESSAGE);
    }

    public void clickOnDeleteAPIKeySuccessPopupOKButton() {
        waitForElementPresence(driver, APIKeyPageUI.DELETE_API_KEY_SUCCESS_POPUP);
        clickToElement(driver, APIKeyPageUI.DELETE_API_KEY_SUCCESS_POPUP_OK_BUTTON);
    }

    public void inputKeyWordToSearchTextbox(String value) {
        inputText(driver, APIKeyPageUI.SEARCH_TEXTBOX, value);
        sleepInSecond(2);
    }

    public int getAllApiKeyNameByName(String apiKeyName) {
        ArrayList<String> itemList = new ArrayList<>();
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        if (totalPage > 1) {
            for (int i = 1; i < totalPage + 1; i++) {
                clickToElement(driver, AbstractPageUI.PAGE_NUMBER, String.valueOf(i));
                waitForProcessBarOfAPIKeyPageDisappear(driver);
                List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_HOST_NAME);
                for (WebElement element : elements) {
                    System.out.println(element.getText());
                    if (element.getText().contains(apiKeyName)) {
                        itemList.add(element.getText());
                    }
                }
            }
        } else if (totalPage == 1) {
            List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_HOST_NAME);
            for (WebElement element : elements) {
                System.out.println(element.getText());
                if (element.getText().contains(apiKeyName)) {
                    itemList.add(element.getText());
                }
            }
        }
        System.out.println(itemList.size());
        return itemList.size();
    }


    public int getAllApiKeyName() {
        ArrayList<String> itemList = new ArrayList<>();
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());

        if (totalPage > 1) {
            for (int i = 1; i < totalPage + 1; i++) {
                clickToElement(driver, AbstractPageUI.PAGE_NUMBER, String.valueOf(i));
                waitForProcessBarOfAPIKeyPageDisappear(driver);
                List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_HOST_NAME);
                for (WebElement element : elements) {
                    System.out.println(element.getText());
                    itemList.add(element.getText());
                }
            }
        } else if (totalPage == 1) {
            List<WebElement> elements = findElements(driver, APIKeyPageUI.API_KEY_PAGE_HOST_NAME);
            for (WebElement element : elements) {
                System.out.println(element.getText());
                itemList.add(element.getText());
            }
        }
        System.out.println(itemList.size());
        return itemList.size();
    }

    public void deleteAllAPIKeys() {
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        System.out.println(totalPage);
//        for (int i = 0; i < totalPage+1; i++) {
//            List<WebElement> elements = findElements(driver, APIKeyPageUI.DELETE_API_KEY_BUTTONS);
//            for (WebElement e : elements) {
//                e.click();
//                clickOnDeleteConfirmationOKButton();
//                clickOnDeleteAPIKeySuccessPopupOKButton();
//            }
//            reloadPage(driver);
//            i++;
//            if(isElementDisplay(driver,"//td[text()='No data available in table']")==true){
//                break;
//            }
//        }
        int i = 1;
        while (i < totalPage) {
            List<WebElement> elements = findElements(driver, APIKeyPageUI.DELETE_API_KEY_BUTTONS);
            for (WebElement e : elements) {
                e.click();
                clickOnDeleteConfirmationOKButton();
                clickOnDeleteAPIKeySuccessPopupOKButton();
            }
            reloadPage(driver);
            if (isElementDisplay(driver, "//td[text()='No data available in table']") == true) {
                break;
            }
        }

    }

}