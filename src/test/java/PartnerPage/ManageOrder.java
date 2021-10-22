package PartnerPage;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageObject.OrderPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
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

//        @Test
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

//        @Test
    public void TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text() {
        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Get total order in bottom text");
        int totalOrderInbottomText = orderPage.getTotalItemInBottomText();

        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Count total orders");
        int totalCountOrder = orderPage.countTotalOrders();

        log.info("TC02_Verify_Count_Total_Order_Equal_Total_In_Bottom_Text: Verify total order count = total order in bottom text");
        verifyEquals(totalCountOrder, totalOrderInbottomText);

    }

//        @Test
    public void TC03_Verify_Search_Function_Work_Correctly() {
        ArrayList<String> allValuesByColumnName;
        int countTotalOrderInSearchResult;
        //get all Order Code values

        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order Code");

        //Verify search by order ID

        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order ID in dropdown");
        orderPage.selectTypeOfSearch("Order ID");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Input order ID= 494 into search textbox");
        orderPage.inputTextToSearchTextbox("4");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
        countTotalOrderInSearchResult = orderPage.countTotalOrders();
        verifyEquals(orderPage.getTotalOrderIDBySearchKey(allValuesByColumnName, "4"), countTotalOrderInSearchResult);

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only order ID = 494 displays");
        verifyTrue(orderPage.isCellByColumnNameContainsText("Order Code", "4"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

//Verify search by order Order Code : GO-20211004-130-001

        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order Code");


        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
        orderPage.selectTypeOfSearch("Order Code");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Order code= GO-20211004-130-001 into search textbox");
        orderPage.inputTextToSearchTextbox("GO-20211004-130-001");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
        countTotalOrderInSearchResult = orderPage.countTotalOrders();
        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "GO-20211004-130-001"), countTotalOrderInSearchResult);

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Order code = GO-20211004-130-001 displays");
        verifyTrue(orderPage.isCellByColumnNameContainsText("Order Code", "GO-20211004-130-001"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

//Verify search by First/Last Name : Quoc
        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
        allValuesByColumnName = orderPage.getAllValuesByColumnName("Order By");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
        orderPage.selectTypeOfSearch("First/Last Name");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Order code= GO-20211004-130-001 into search textbox");
        orderPage.inputTextToSearchTextbox("Quốc");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
        countTotalOrderInSearchResult = orderPage.countTotalOrders();
        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "Quốc"), countTotalOrderInSearchResult);

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Order code = Quốc");
        verifyTrue(orderPage.isCellByColumnNameContainsText("Order By", "Quốc"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

//Verify search by Email Address : khoatran852005@live.com

        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
        orderPage.selectTypeOfSearch("Email Address");

        log.info("TC03_Verify_Search_Function_Work_Correctly:  Email = khoatran852005@live.com into search textbox");
        orderPage.inputTextToSearchTextbox("khoatran852005@live.com");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify email = khoatran852005@live.com");
        verifyTrue(orderPage.isOrderEMailContains("khoatran852005@live.com"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

//Verify search by Outlet : Tiki
        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Outlet in dropdown");
        orderPage.selectTypeOfSearch("Outlet");

        log.info("TC03_Verify_Search_Function_Work_Correctly:  Outlet = Tiki into search textbox");
        orderPage.inputTextToSearchTextbox("Tiki");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Outlet = Tiki");
        verifyTrue(orderPage.isOrderOutletContains("Tiki"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

        //Verify search by Outlet : Shopee
        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Outlet in dropdown");
        orderPage.selectTypeOfSearch("Outlet");

        log.info("TC03_Verify_Search_Function_Work_Correctly:  Outlet = Shopee into search textbox");
        orderPage.inputTextToSearchTextbox("Shopee");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Outlet = Shopee");
        verifyTrue(orderPage.isOrderOutletContains("Shopee"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

        //Verify search by Outlet : Lazada

        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Outlet in dropdown");
        orderPage.selectTypeOfSearch("Outlet");

        log.info("TC03_Verify_Search_Function_Work_Correctly:  Outlet = Lazada into search textbox");
        orderPage.inputTextToSearchTextbox("Lazada");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Outlet = Lazada");
        verifyTrue(orderPage.isOrderOutletContains("Lazada"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();

//Verify search by Product name: Journey of a Roach
        log.info("TC03_Verify_Search_Function_Work_Correctly:get all Order Code values");
        allValuesByColumnName = orderPage.getAllValuesByColumnName("Products");


        log.info("TC03_Verify_Search_Function_Work_Correctly: Select Order code in dropdown");
        orderPage.selectTypeOfSearch("Product Name");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Input Products= King of Dragon Pass into search textbox");
        orderPage.inputTextToSearchTextbox("Journey of a Roach");

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify Total result correct");
        countTotalOrderInSearchResult = orderPage.countTotalOrders();
        verifyEquals(orderPage.getTotalOrderCodeBySearchKey(allValuesByColumnName, "Journey of a Roach"), countTotalOrderInSearchResult);

        log.info("TC03_Verify_Search_Function_Work_Correctly: Verify only Products = King of Dragon Pass displays");
        verifyTrue(orderPage.isCellByColumnNameContainsText("Products", "Journey of a Roach"));

        //Delete all search key
        orderPage.clearAllTextInSearchBox();
    }

//    @Test
    public void TC04_Verify_Export_File_CSV() {
        log.info("TC04_Verify_Export_File_CSV: Click on Export CSV button");
        orderPage.clickToExportCSVButton();

        log.info("TC04_Verify_Export_File_CSV: Verify file export succcessfully");
        verifyEquals(orderPage.countFilesInDirectory("csv"), 1);
        verifyTrue(orderPage.isFileExtensionCorrect("csv"));
        deleteAllFileInFolder();
    }

//    @Test
    public void TC05_Verify_Export_File_Excel() {
        log.info("TC05_Verify_Export_File_Excel: Click on Export Excel button");
        orderPage.clickOnExportExcelButton();

        log.info("TC05_Verify_Export_File_Excel: Verify file export succcessfully");
        verifyEquals(orderPage.countFilesInDirectory("xlsx"), 1);
        verifyTrue(orderPage.isFileExtensionCorrect("xlsx"));
        deleteAllFileInFolder();
    }

//    @Test
    public void TC06_Verify_Clicking_Home_Icon_Will_Open_Partner_Home_Page() {
        log.info("TC06_Verify_Clicking_Home_Icon_Will_Open_Partner_Home_Page: Click on home icon");
        dashboardPage = orderPage.clickToHomeIcon(driver);

        log.info("TC06_Verify_Clicking_Home_Icon_Will_Open_Partner_Home_Page: Verify dashboard page display");
        verifyEquals(dashboardPage.getCurrentURL(driver), "https://goshop-v2-dev.gogame.net/partner");
        verifyTrue(dashboardPage.isDashBoardPageTitleDisplay());
    }

//    @Test
    public void TC07_Verify_Show_More_Item_Per_Page_Work_Correctly(){
        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 25 entries per page");
        orderPage.selectValueInShowEntryDropdown("25");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 25 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(25), 25);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 25 entries per page");
        orderPage.selectValueInShowEntryDropdown("50");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 50 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(50), 50);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 100 entries per page");
        orderPage.selectValueInShowEntryDropdown("100");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 100 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(100), 100);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 200 entries per page");
        orderPage.selectValueInShowEntryDropdown("200");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 200 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(200), 200);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 300 entries per page");
        orderPage.selectValueInShowEntryDropdown("300");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 300 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(300), 300);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 400 entries per page");
        orderPage.selectValueInShowEntryDropdown("400");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 400 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(400), 400);

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Select show 500 entries per page");
        orderPage.selectValueInShowEntryDropdown("500");

        log.info("TC07_Verify_Show_More_Item_Per_Page_Work_Correctly: Verify 500 items display in page");
        verifyEquals(orderPage.getTotalItemsInPage(500), 500);

    }

    @AfterClass
    public void AfterClass(){
        closeBrowserAndDriver(driver);
    }
}


