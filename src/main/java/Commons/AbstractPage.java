package Commons;

import PageObject.*;
import PageUI.AbstractPageUI;
import PageUI.DashboardPageUI;
import PageUI.ProductPageUI;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public void waitForElementUndisplay(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(xpath(locator)));
    }
    public void waitForElementPresence(WebDriver driver, String locator){
        explicitWait = new WebDriverWait(driver, GlobalConstant.SHORT_TIMEOUT);
        explicitWait.until(ExpectedConditions.presenceOfElementLocated(xpath(locator)));
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
    public WebElement findElement(WebDriver driver, String locator, String...values) {
        return driver.findElement(xpath(castToObject(locator,values)));
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
    public boolean isElementDisplay(WebDriver driver, String locator,String...values) {
        List<WebElement> elements = findElements(driver, castToObject(locator,values));
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

    public void switchToBrowserTab(WebDriver driver){
        String mainTab = driver.getWindowHandle();
        Set<String> allTabs = driver.getWindowHandles();
        for (String tab: allTabs){
            if(!tab.equals(mainTab)){
                driver.switchTo().window(tab);
                break;
            }
        }
    }

    public void closeCurrentTabAndSwitchBackToMainTab(WebDriver driver){
        Set<String> allTabs = driver.getWindowHandles();
        List<String> list = new ArrayList<>(allTabs);
        driver.switchTo().window(list.get(1)).close();
        driver.switchTo().window(list.get(0));
    }

    public void openLinkInNewTab(WebElement element){
        element.sendKeys(Keys.chord(Keys.CONTROL,Keys.RETURN));
    }

    public int randomNumber(){
        Random random = new Random();
        return random.nextInt(999);
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
    public OrderPageObject clickOnManageOrderMenu(WebDriver driver){
        clickToElement(driver,AbstractPageUI.LEFT_MENU_BY_NAME,"Manage Orders");
        waitForProcessBarDisappear(driver);
        return PageGenerator.getOrderPage(driver);

    }
    public APIKeyPageObject clickOnManageAPIKeyMenu(WebDriver driver){
        clickToElement(driver,AbstractPageUI.LEFT_MENU_BY_NAME,"Manage API Keys");
        waitForProcessBarOfAPIKeyPageDisappear(driver);
        return PageGenerator.getApiKeyPage(driver);

    }

    public void waitForProcessBarDisappear(WebDriver driver){
        waitForElementPresence(driver, AbstractPageUI.PRODUCT_PAGE_PROCESSING_LOADING);
    }

    public void waitForProcessBarOfAPIKeyPageDisappear(WebDriver driver) {
        waitForElementPresence(driver,AbstractPageUI.API_KEYS_PROCESSING_LOADING);
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

//========================================= SORT ===================================================================
    public boolean sortStringASC(WebDriver driver, String columnName){
        ArrayList<String> allValue = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
//        List<WebElement> totalPage = findElements(driver,AbstractPageUI.TOTAL_PAGES);
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
                    allValue.add(a.getText());
                }
            }
        }

        ArrayList<String> sortASCItems = (ArrayList<String>) allValue.clone();
        Collections.sort(sortASCItems, String.CASE_INSENSITIVE_ORDER);
        return sortASCItems.equals(allValue);
    }

    public boolean sortStringDESC(WebDriver driver, String columnName){
        ArrayList<String> allValue = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
//        List<WebElement> totalPage = findElements(driver,AbstractPageUI.TOTAL_PAGES);
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
//                    String[] item = a.getText().split("SKU");
                    allValue.add(a.getText());
                }
            }
        }
        System.out.println(allValue);
        ArrayList<String> sortDESCItems = (ArrayList<String>) allValue.clone();
        Collections.sort(sortDESCItems, String.CASE_INSENSITIVE_ORDER);
        Collections.reverse(sortDESCItems);
        System.out.println(sortDESCItems);
        return sortDESCItems.equals(allValue);
    }

    public boolean sortIntASC(WebDriver driver, String columnName){
        List<Integer> allValue = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
                    allValue.add(Integer.valueOf(a.getText().replaceAll("[^\\d.]", "").replaceAll(",","").replaceAll("[.]","")));
                }
            }
        }
        System.out.println(allValue);
        List<Integer> sortASCItems = new ArrayList<Integer>(allValue);
        Collections.sort(sortASCItems);
        System.out.println(sortASCItems);
        return sortASCItems.equals(allValue);
    }

    public boolean sortIntDESC(WebDriver driver, String columnName){
        List<Integer> allValue = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
                    allValue.add(Integer.valueOf(a.getText().replaceAll("[^\\d.]", "").replaceAll(",","").replaceAll("[.]","")));
                }
            }
        }
        System.out.println(allValue);
        List<Integer> sortDESCItems = new ArrayList<Integer>(allValue);
        Collections.sort(sortDESCItems);
        Collections.reverse(sortDESCItems);
        System.out.println(sortDESCItems);
        return sortDESCItems.equals(allValue);
    }

    public static Date convertStringToDate(String dateInString) {
        dateInString = dateInString.replaceAll(",", "");
        SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
        Date date = null;
        try {
            date = formatter.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public boolean AreDateSortASC(WebDriver driver, String columnName){
        ArrayList<Date> listOfDates = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
                    listOfDates.add(convertStringToDate(a.getText().replaceAll(",","")));
                }
            }
        }
        System.out.println(listOfDates);
        ArrayList<Date> listSortedDates = new ArrayList<Date>(listOfDates);
        Collections.sort(listSortedDates);
        System.out.println(listSortedDates);
        return listSortedDates.equals(listOfDates);
    }

    public boolean AreDateSortDESC(WebDriver driver, String columnName){
        ArrayList<Date> listOfDates = new ArrayList<>();
        int index = findElements(driver,AbstractPageUI.COLUMN_INDEX_BY_NAME,columnName).size()+1;
        int totalPage = Integer.valueOf(findElement(driver,AbstractPageUI.TOTAL_PAGES).getText());
        for (int i =1; i< totalPage+1; i++){
            findElement(driver,AbstractPageUI.PAGE_NUMBER, String.valueOf(i)).click();
            waitForProcessBarDisappear(driver);
            {
                List<WebElement> valueByColumnName = findElements(driver,AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement a : valueByColumnName) {
                    listOfDates.add(convertStringToDate(a.getText().replaceAll(",","")));
                }
            }
        }
        System.out.println(listOfDates);
        ArrayList<Date> listSortedDates = new ArrayList<Date>(listOfDates);
        Collections.sort(listSortedDates);
        Collections.reverse(listSortedDates);
        System.out.println(listSortedDates);
        return listSortedDates.equals(listOfDates);
    }
//==================================================================================================================

    public DashboardPageObject clickToHomeIcon(WebDriver driver){
        clickToElement(driver,AbstractPageUI.HOME_ICON);
        return PageGenerator.getDashboardPage(driver);
    }
}


