package TestCase;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageUI.AbstractPageUI;
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

        clickToElement(driver, AbstractPageUI.LEFT_MENU_BY_NAME, "Manage Orders");
        ArrayList<String> orderDate = new ArrayList<String>();
        int columnIndex = findElements(driver, DashboardPageUI.COLUMN_INDEX_BY_TITLE, "Order Date").size() + 1;
        List<WebElement> totalPage = findElements(driver, AbstractPageUI.TOTAL_PAGES);
        System.out.println(totalPage.size());
        for (WebElement page : totalPage) {
            page.click();
            sleepInSecond(4);
            {
                List<WebElement> orderDateList = findElements(driver, DashboardPageUI.VALUE_BY_COLUMN_INDEX, String.valueOf(columnIndex));
                for (WebElement a : orderDateList) {
                    String[] item = a.getText().split(",");
                    orderDate.add(item[0]);
                }
            }
        }
        System.out.println(orderDate);

        //remove duplicate data and add to new array
        ArrayList<String> orderDateRemoveDup = new ArrayList<String>();
        for (int i = 0; i < orderDate.size(); i++) {
            if (!orderDateRemoveDup.contains(orderDate.get(i))) {
                orderDateRemoveDup.add(orderDate.get(i));
            }
        }
        System.out.println(orderDateRemoveDup);

        for (String a : orderDateRemoveDup) {
            //total order by date
            int totalOrderByDate = Collections.frequency(orderDate, a);
            String totalOrderInOrderManageByDate = String.valueOf(totalOrderByDate);
            System.out.println(a + " total :" + totalOrderInOrderManageByDate + " orders");

            clickToElement(driver, AbstractPageUI.LEFT_MENU_BY_NAME, "Dashboard");
            //select total Ordered
            clickOnFilterNameInHightChart(driver, "All");
            clickOnFilterNameInHightChart(driver, "Processing");
            clickOnFilterNameInHightChart(driver, "Done");
            clickOnFilterNameInHightChart(driver, "Failed");
            clickOnFilterNameInHightChart(driver, "Cancelled");
            clickOnFilterNameInHightChart(driver, "Deleted");
            clickToElement(driver, DashboardPageUI.HIGH_CHART);

            String index = String.valueOf(findElements(driver, DashboardPageUI.DAY_IN_HIGHCHART, a).size() + 1);
            moveToElement(driver, DashboardPageUI.POINT_IN_HIGHCHART_BY_INDEX, index);
            String tooltipText = findElement(driver, DashboardPageUI.TOOLTIP_TEXT_CONTENT).getText();
            System.out.println(tooltipText);
            verifyTrue(tooltipText.contains(totalOrderInOrderManageByDate));

        }
    }
}