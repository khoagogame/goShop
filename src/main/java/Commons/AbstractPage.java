package Commons;

import PageObject.LoginPageObject;
import PageObject.ProductPageObject;
import PageObject.ProfilePageObject;
import PageUI.AbstractPageUI;
import PageUI.DashboardPageUI;
import PageUI.ProductPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;

public abstract class AbstractPage {
    WebDriverWait explicitWait;
    JavascriptExecutor js;
    WebElement element;


//===========================================Wait=================================================================
    public void waitForElementVisible(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(xpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator) {
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(xpath(locator)));
    }

    public void waitForElementUndisplay(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.LONG_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(xpath(locator)));
    }


//===========================================================================================================

    //Commons methods

    public By xpath(String locator) {
        return By.xpath(locator);
    }

    public String castToObject(String locator, String... values) {
        return String.format(locator, (Object[]) values);
    }

    public WebElement findElement(WebDriver driver, String locator) {
        return driver.findElement(xpath(locator));
    }


    public List<WebElement> findElements(WebDriver driver, String locator) {
        return driver.findElements(xpath(locator));
    }
    public List<WebElement> findElements(WebDriver driver, String locator, String...values) {
        return driver.findElements(xpath(castToObject(locator,values)));
    }

    public void inputText(WebDriver driver, String locator, String value) {
        waitForElementVisible(driver, locator);
        findElement(driver, locator).clear();
        findElement(driver, locator).sendKeys(value);
    }

    public void inputText(WebDriver driver, String locator, String value, String... values) {
        element = findElement(driver, castToObject(locator, values));
        waitForElementVisible(driver, castToObject(locator, values));
        element.clear();
        element.sendKeys(value);
    }

    public void clearAllText(WebDriver driver, String locator){
        findElement(driver,locator).clear();
    }

    public void clickToElement(WebDriver driver, String locator) {
        waitForElementClickable(driver, locator);
        findElement(driver, locator).click();
    }

    public void clickToElement(WebDriver driver, String locator, String...values) {
        waitForElementClickable(driver, castToObject(locator,values));
        findElement(driver, castToObject(locator,values)).click();
    }

    public void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getElementText(WebDriver driver, String locator) {
        return findElement(driver, locator).getText().trim();
    }

    public String getElementText(WebDriver driver, String locator, String... values) {
        return findElement(driver, castToObject(locator, values)).getText().trim();
    }

    public String getInnerTextinTextbox(WebDriver driver, String locator) {
        return findElement(driver, locator).getAttribute("value");
    }

    public String getInnerTextinTextbox(WebDriver driver, String locator, String... values) {
        return findElement(driver, castToObject(locator, values)).getAttribute("value");
    }

    public boolean isElementDisplay(WebDriver driver, String locator) {
        List<WebElement> elements = findElements(driver, locator);
        if (elements.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Object getCurrentURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public void selectItemInDropdownList(WebDriver driver, String locator, String value) {
        Select select = new Select(findElement(driver, locator));
        select.selectByVisibleText(value);
    }

    public void moveToElement(WebDriver driver, String locator){
        waitForElementVisible(driver,locator);
        Actions action = new Actions(driver);
        action.moveToElement(findElement(driver,locator)).perform();
    }
    public void moveToElement(WebDriver driver, String locator, String...values){
        waitForElementVisible(driver,castToObject(locator, values));
        Actions action = new Actions(driver);
        action.moveToElement(findElement(driver,castToObject(locator, values))).perform();
    }

    public void reloadPage(WebDriver driver){
        driver.navigate().refresh();
    }

    public String getSelectedValueInDropdown(WebDriver driver, String locator){
        Select select = new Select(findElement(driver,locator));
       return select.getFirstSelectedOption().getText();
    }

    public int countElement(WebDriver driver, String locator){
        return findElements(driver,locator).size();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName, String...values){
       return findElement(driver,castToObject(locator, values)).getAttribute(attributeName);
    }





    //sleep
    public void sleepInMiliSecond(long timeOut) {
        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleepInSecond(long timeOut) {
        try {
            Thread.sleep(timeOut * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // get tooltip text
    public String getHtml5ValidationMessage(WebDriver driver, String locator) {
        WebElement element = findElement(driver, locator);
        js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }

    public String getHtml5ValidationMessage(WebDriver driver, String locator, String... values) {
        WebElement element = findElement(driver, castToObject(locator, values));
        js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }


//====================================================================================================================
    //Use in project goShop

    public LoginPageObject openLoginPage(WebDriver driver) {
        openURL(driver, GlobalConstant.PARTNER_LOGIN_PAGE);
        return PageGenerator.getLoginPage(driver);
    }


    public void clickOnPartnerName(WebDriver driver) {
        clickToElement(driver, AbstractPageUI.PARTNER_NAME);
    }

    public LoginPageObject clickOnLogoutButton(WebDriver driver) {
        clickToElement(driver, AbstractPageUI.LOGOUT_BUTTON);
        return PageGenerator.getLoginPage(driver);
    }

    public ProfilePageObject clickOnViewProfileIcon(WebDriver driver){
        clickToElement(driver, AbstractPageUI.VIEW_PROFILE_ICON);
        return PageGenerator.getProfilePage(driver);
    }



    public void clickOnFilterNameInHightChart(WebDriver driver, String filterName) {
        clickToElement(driver, DashboardPageUI.STATUS_BY_NAME_FILTER_IN_HIGHCHART, filterName);
    }

    public ProductPageObject clickOnManageProductMenu(WebDriver driver){
        clickToElement(driver,AbstractPageUI.LEFT_MENU_BY_NAME,"Manage Products");
        waitForProcessBarDisappear(driver);
        return PageGenerator.getProductPage(driver);

    }

    public void waitForProcessBarDisappear(WebDriver driver){
        waitForElementUndisplay(driver, AbstractPageUI.PROCESSING_LOADING);
    }

    public void clickOnColumnNameToSort(WebDriver driver, String columnName) {
        clickToElement(driver, ProductPageUI.COLUMN_NAME, columnName);
        waitForProcessBarDisappear(driver);
    }


    public ArrayList<String> allValuesByColumnName(WebDriver driver, String locator, String columnName, String...values){
        ArrayList<String> allValuesBeforeSortByColumnName = new ArrayList<>();
        List<WebElement> elements = findElements(driver,locator, values);

        for (WebElement e: elements){
            allValuesBeforeSortByColumnName.add(e.getText());
        }
        return allValuesBeforeSortByColumnName;
    }


    public boolean areAllValuesSortASC (WebDriver driver, String locator, String columnName){
        ArrayList<String> allOriginalValue = new ArrayList<>();
        List<WebElement> nameList = findElements(driver,locator, columnName);
        List<WebElement> totalPage = findElements(driver,AbstractPageUI.TOTAL_PAGES);

        for (WebElement page : totalPage) {
            page.click();
            sleepInSecond(4);
            {
//                List<WebElement> orderDateList = findElements(driver, DashboardPageUI.VALUE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
                for (WebElement a : nameList) {
                    String[] item = a.getText().split(",");
                    allOriginalValue.add(item[0]);
                }
            }
        }
        System.out.println(allOriginalValue);

        ArrayList<String> sortASCItems = Collections.sort(allOriginalValue);
    }
}


