package TestCase;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageUI.DashboardPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import javax.swing.*;
import java.sql.Array;
import java.util.*;

public class Dashboard extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;


    @Parameters({"browser"})
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        log.info("Precondition: Login account");
        loginPage = openLoginPage(driver);
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        dashboardPage = loginPage.clickLoginButton();
    }

    //    @Test
    public void TC01_Get_Number_Order_In_GraphChart() {
        Actions actions = new Actions(driver);
        sleepInSecond(5);
        Set<String> a = new HashSet<String>();
//        List<WebElement> element = findElements(driver, DashboardPageUI.ORDER_BY_DATE);
        WebElement element = findElement(driver, DashboardPageUI.ORDER_BY_DATE);
//        System.out.println("total = " + element.size());
        System.out.println(element);
//        for(WebElement e: element){
        actions.moveToElement(element).perform();
//            String text = getHtml5ValidationMessage11(driver,findElement(driver,"(//*[name()='path' and @class='highcharts-label-box highcharts-tooltip-box highcharts-shadow'])[1]"));
        System.out.println(findElement(driver, "//div[contains(@class,'highcharts-label')]/span").getText());
        sleepInSecond(1);
    }


    @Test
    public void getTotalOrderByDate() {
        Actions actions = new Actions(driver);
        clickToElement(driver, "//span[text()='Manage Orders']");
        sleepInSecond(2);
        selectItemInDropdownList(driver,"//select[@name='partnerOrderTable_length']","25");
        sleepInSecond(2);
        Set<String> date = new HashSet<String>();
        int column = findElements(driver, "//th[text()='Order Date']/preceding-sibling::th").size() + 1;
        String valueByColumn = findElement(driver, "//td[" + column + "]/preceding-sibling::td[3]").getText();
        List<WebElement> totalPage = findElements(driver, "//ul[@class='pagination']//li[not(contains(@class,'next')) and not(contains(@class,'previous'))]");
        System.out.println(totalPage.size());
        List<WebElement> e = findElements(driver, "//td[6]");
//        for (WebElement page : totalPage) {
//            page.click();
//            sleepInSecond(4);
//            {
                for (WebElement a : e) {
                    date.add(a.getText());
                    System.out.println(a.getText());
                }
//            }
            System.out.println(date);
        ArrayList<String> cutDate = new ArrayList<String>();
        for(String d: date){
            String [] j = d.split(",");
            cutDate.add(j[0]);
        }
        System.out.println(cutDate);

                for(String a: cutDate){
                    int orderByDate = findElements(driver,"//td[contains(text(),'"+ a + "')]").size();
                    System.out.println(a + ":" + orderByDate);

                    clickToElement(driver, "//span[text()='Dashboard']");
                    sleepInSecond(5);

                    int graphpo = findElements(driver,"//*[name()='tspan' and text()='"+ a +"']/parent::*/preceding-sibling::*").size() + 1;
                    WebElement element = findElement(driver,"//*[name()='g' and contains(@class,'highcharts-markers highcharts-series-1 highcharts-spline-series highcharts-color-1  highcharts-tracker')]/*[name()='path']["+ graphpo + "]");
                    actions.moveToElement(element).perform();
                    System.out.println(findElement(driver, "//div[contains(@class,'highcharts-label')]/span").getText());
                }
        }




    }
//*[name()='tspan' and text()='Oct 4']/parent::*/preceding-sibling::*

