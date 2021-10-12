package Commons;

import PageObject.LoginPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public abstract class AbstractPage {
    WebDriverWait explicitWait;
    JavascriptExecutor js;


    //wait
    public void waitForElementVisible(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(xpath(locator)));
    }

    public void waitForElementClickable(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.elementToBeClickable(xpath(locator)));
    }







    //Commons method

    public By xpath(String locator){
        return By.xpath(locator);
    }

    public WebElement findElement(WebDriver driver, String locator){
       return driver.findElement(xpath(locator));
    }

    public List<WebElement> findElements(WebDriver driver, String locator){
        return driver.findElements(xpath(locator));
    }

    public void inputText(WebDriver driver, String locator, String value){
        findElement(driver,locator).clear();
        findElement(driver,locator).sendKeys(value);
    }

    public void clickToElement(WebDriver driver, String locator){
        findElement(driver,locator).click();
    }

    public void openURL(WebDriver driver, String url){
        driver.get(url);
    }
























// get tooltip text
    public String getHtml5ValidationMessage(WebDriver driver, String locator) {
        WebElement element = findElement(driver,locator);
        js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return arguments[0].validationMessage;", element);
    }

//====================================================================================================================
    //Use in project goShop

    public LoginPageObject openLoginPage(WebDriver driver){
        openURL(driver,GlobalConstant.PARTNER_LOGIN_PAGE);
        return PageGenerator.getLoginPage(driver);
    }



}

