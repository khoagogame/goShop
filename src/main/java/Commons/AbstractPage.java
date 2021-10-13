package Commons;

import PageObject.LoginPageObject;
import PageUI.AbstractPageUI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.internal.thread.IThreadFactory;
import org.testng.internal.thread.graph.IThreadWorkerFactory;

import java.util.List;

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


//===========================================================================================================

    //Commons method

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

    public void clickToElement(WebDriver driver, String locator) {
        waitForElementClickable(driver, locator);
        findElement(driver, locator).click();
    }

    public void openURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getElementText(WebDriver driver, String locator) {
        return findElement(driver, locator).getText().trim();
    }

    public void getElementText(WebDriver driver, String locator, String... values) {
        findElement(driver, castToObject(locator, values)).getText().trim();
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
        waitForPageLoadComplete(driver);
        return driver.getCurrentUrl();
    }


    public void waitForPageLoadComplete(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(pageLoadCondition);
    }


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


    public void clickOnPartnerName(WebDriver driver){
        clickToElement(driver, AbstractPageUI.PARTNER_NAME);
    }

    public LoginPageObject clickOnLogoutButton(WebDriver driver){
        clickToElement(driver, AbstractPageUI.LOGOUT_BUTTON);
        return PageGenerator.getLoginPage(driver);
    }
}

