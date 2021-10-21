package PartnerPage;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageObject.OrderPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class ManageOrder extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    OrderPageObject orderPage;


    @Parameters({"browser"})
    @BeforeClass
    public void BeforeClass(String browserName) {
        driver = getBrowserDriver(browserName);

        log.info("Precondition: login account");
        loginPage = openLoginPage(driver);
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        dashboardPage = loginPage.clickLoginButton();
        orderPage = dashboardPage.clickOnManageOrderMenu(driver);
    }

    //    @Test
    public void TC01_Verify_Manage_Order_Page_Display() {
        log.info("TC01_Verify_Manage_Order_Page_Display: Verify Manage Order page is opened");
        verifyTrue(orderPage.isOrderPageTitleDisplay());

        log.info("TC01_Verify_Product_Page_Display: Verify default Show = 10");
        verifyEquals(orderPage.getSelectedValueInShowEntryDropdown(), "10");

        log.info("TC01_Verify_Product_Page_Display: Verify Export CSV button display");
        verifyTrue(orderPage.isExportCSVButtonDisplay());

        log.info("TC01_Verify_Product_Page_Display: Verify Export Excel button display");
        verifyTrue(orderPage.isExportExcelButtonDisplay());
    }

    //    @Test
    public void TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text() {
        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Get total order in bottom text");
        int totalOrderInbottomText = orderPage.getTotalItemInBottomText();

        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Count total orders");
        int totalCountOrder = orderPage.countTotalOrders();

        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Verify total order count = total order in bottom text");
        verifyEquals(totalCountOrder, totalOrderInbottomText);

    }

    @Test
    public void TC03_Verify_Search_Function_Work_Correctly() {
        ArrayList<String> allValuesByColumnName;
        int countTotalOrderInSearchResult;
        //get all Order Code values

//        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
//        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order Code");
//
//        //Verify search by order ID
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order ID in dropdown");
//        orderPage.selectTypeOfSearch("Order ID");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Input order ID= 494 into search textbox");
//        orderPage.inputTextToSearchTextbox("4");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
//        countTotalOrderInSearchResult = orderPage.countTotalOrders();
//        verifyEquals(orderPage.getTotalOrderIDBySearchKey(allValuesByColumnName, "4"), countTotalOrderInSearchResult);
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only order ID = 494 displays");
//        verifyTrue(orderPage.isCellByColumnNameContainsText("Order Code", "4"));
//
//        //Delete all search key
//        orderPage.clearAllTextInSearchBox();
//
////Verify search by order Order Code : GO-20211004-130-001
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
//        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order Code");
//
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
//        orderPage.selectTypeOfSearch("Order Code");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Order code= GO-20211004-130-001 into search textbox");
//        orderPage.inputTextToSearchTextbox("GO-20211004-130-001");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
//        countTotalOrderInSearchResult = orderPage.countTotalOrders();
//        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "GO-20211004-130-001"), countTotalOrderInSearchResult);
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Order code = GO-20211004-130-001 displays");
//        verifyTrue(orderPage.isCellByColumnNameContainsText("Order Code", "GO-20211004-130-001"));
//
//        //Delete all search key
//        orderPage.clearAllTextInSearchBox();
//
////Verify search by First/Last Name : Quoc
//        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
//        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order By");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
//        orderPage.selectTypeOfSearch("First/Last Name");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Order code= GO-20211004-130-001 into search textbox");
//        orderPage.inputTextToSearchTextbox("Quốc");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
//        countTotalOrderInSearchResult = orderPage.countTotalOrders();
//        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "Quốc"), countTotalOrderInSearchResult);
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Order code = Quốc");
//        verifyTrue(orderPage.isCellByColumnNameContainsText("Order By", "Quốc"));
//
//        //Delete all search key
//        orderPage.clearAllTextInSearchBox();

////Verify search by Email Address : khoatran852005@live.com
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
//        orderPage.selectTypeOfSearch("Email Address");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly:  Email = khoatran852005@live.com into search textbox");
//        orderPage.inputTextToSearchTextbox("khoatran852005@live.com");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify email = khoatran852005@live.com");
//        verifyTrue(orderPage.isOrderEMailContains("khoatran852005@live.com"));
//
//        //Delete all search key
//        orderPage.clearAllTextInSearchBox();

////Verify search by Outlet : Tiki

//        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Outlet in dropdown");
//        orderPage.selectTypeOfSearch("Outlet");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly:  Outlet = Tiki into search textbox");
//        orderPage.inputTextToSearchTextbox("Tiki");
//
//        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Outlet = Tiki");
//        verifyTrue(orderPage.isOrderOutletContains("Tiki"));
//
//        //Delete all search key
//        orderPage.clearAllTextInSearchBox();


        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
        allValuesByColumnName = orderPage.getAllValuesByColumnName("Products");


        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
        orderPage.selectTypeOfSearch("Products");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Products= King of Dragon Pass into search textbox");
        orderPage.inputTextToSearchTextbox("King of Dragon Pass");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
        countTotalOrderInSearchResult = orderPage.countTotalOrders();
        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "King of Dragon Pass"), countTotalOrderInSearchResult);

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Products = King of Dragon Pass displays");
        verifyTrue(orderPage.isCellByColumnNameContainsText("Products", "King of Dragon Pass"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();
    }

}
