package TestCase;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import PageObject.ProductPageObject;
import PageUI.ProductPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ManageProduct extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    ProductPageObject productPage;

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

    @Test
    public void TC01_Verify_Product_Page_Display() {
        log.info("TC01_Verify_Product_Page_Display: Click on Manage Product menu");
        productPage = dashboardPage.clickOnManageProductMenu(driver);

        log.info("TC01_Verify_Product_Page_Display: Verify Manage product page is opened");
        verifyTrue(dashboardPage.isProductPageTitleDisplay());

        log.info("TC01_Verify_Product_Page_Display: Verify default country = Vietnam");
        verifyEquals(productPage.getSelectedValueInCountryDropdown(), "Viet Nam");

        log.info("TC01_Verify_Product_Page_Display: Verify default Show = 10");
        verifyEquals(productPage.getSelectedValueInShowEntryDropdown(), "10");

        log.info("TC01_Verify_Product_Page_Display: Verify Export CSV button display");
        verifyTrue(productPage.isExportCSVButtonDisplay());

        log.info("TC01_Verify_Product_Page_Display: Verify Export Excel button display");
        verifyTrue(productPage.isExportExcelButtonDisplay());

    }

//    @Test
    public void TC02_Verify_Show_Entries_Dropdown_Work_Correctly() {
        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 25 items per page");
        productPage.selectValueInShowEntryDropdown("25");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 25 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 25);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 50 items per page");
        productPage.selectValueInShowEntryDropdown("50");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 50 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 50);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 100 items per page");
        productPage.selectValueInShowEntryDropdown("100");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 100 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 100);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 200 items per page");
        productPage.selectValueInShowEntryDropdown("200");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 200 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 200);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 300 items per page");
        productPage.selectValueInShowEntryDropdown("300");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 300 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 300);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 400 items per page");
        productPage.selectValueInShowEntryDropdown("400");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 400 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 400);

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Select show 500 items per page");
        productPage.selectValueInShowEntryDropdown("500");

        log.info("TC02_Verify_Show_Entries_Dropdown_Work_Correctly: Verify 500 items show in table");
        verifyEquals(productPage.countTotalProductInPage(), 500);

        // reload page to change to default content
        productPage.reloadPage(driver);

    }

//    @Test
    public void TC03_Verify_Country_Dropdown_Work_Correctly() {
        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Vietnam");
        productPage.selectValueInCountryDropdown("Viet Nam");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'VND'");
        verifyTrue(productPage.isPriceHasCurrency("VND"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Singapore");
        productPage.selectValueInCountryDropdown("Singapore");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'SGD'");
        verifyTrue(productPage.isPriceHasCurrency("SGD"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Thailand");
        productPage.selectValueInCountryDropdown("Thailand");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'THB'");
        verifyTrue(productPage.isPriceHasCurrency("THB"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Indonesia");
        productPage.selectValueInCountryDropdown("Indonesia");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'IDR'");
        verifyTrue(productPage.isPriceHasCurrency("IDR"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Malaysia");
        productPage.selectValueInCountryDropdown("Malaysia");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'MYR'");
        verifyTrue(productPage.isPriceHasCurrency("MYR"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in Philippines");
        productPage.selectValueInCountryDropdown("Philippines");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'PHP'");
        verifyTrue(productPage.isPriceHasCurrency("PHP"));

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Select show all items in England");
        productPage.selectValueInCountryDropdown("England");

        log.info("TC03_Verify_Country_Dropdown_Work_Correctly: Verify prize has 'EUR'");
        verifyTrue(productPage.isPriceHasCurrency("EUR"));

    }

//    @Test
    public void TC04_Verify_Search_Function() {
        String productName = "monster";
        log.info("TC04_Verify_Search_Function: input text and enter");
        //get total entries per page in dropdown
        String totalItem = productPage.getSelectedValueInShowEntryDropdown();

        productPage.inputAndSearchText(productName);

        log.info("TC04_Verify_Search_Function: Verify 6 items display and name contain 'monster'");
        verifyEquals(productPage.countTotalProductInPage(), 6);
        verifyTrue(productPage.isProductNameDisplay(productName));

        log.info("TC04_Verify_Search_Function: Clear all text in search box");
        productPage.clearTextInSearch();

        log.info("TC04_Verify_Search_Function: Verify table is show as before search");
        verifyEquals(String.valueOf(productPage.countTotalProductInPage()), totalItem);

        log.info("TC04_Verify_Search_Function: Search not exist product");
        productPage.inputAndSearchText("abc123");

        log.info("TC04_Verify_Search_Function: Verify no item display");
        verifyTrue(productPage.isNoProductFoundMessageDisplay());
        //clear search text
        productPage.clearTextInSearch();
    }

//    @Test
    public void TC05_Verify_ExportCSV_Function() throws Exception {
        String fileName = "Partner Products   goShop.csv";
        log.info("TC05_Verify_ExportCSV_Function: Click on Export CSV button");
        productPage.clickOnExportCSVButton();

        log.info("TC05_Verify_ExportCSV_Function: Verify file type downloaded is .csv");
        verifyEquals(productPage.countFilesInDirectory("csv"), 1);
        verifyTrue(productPage.isFileExtensionCorrect("csv"));

        deleteAllFileInFolder();
    }

//    @Test
    public void TC06_Verify_ExportExcel_Function() throws Exception {
        log.info("TC06_Verify_ExportExcel_Function: Click on Export Excel button");
        productPage.clickOnExportExcelButton();

        log.info("TC06_Verify_ExportExcel_Function: Verify file type downloaded is .xlsx");
        verifyEquals(productPage.countFilesInDirectory("xlsx"), 1);
        verifyTrue(productPage.isFileExtensionCorrect("xlsx"));

        deleteAllFileInFolder();
    }

    @Test
    public void TC07_Verify_Sort_Function() {
        //Change country = England
        productPage.selectValueInCountryDropdown("England");

        //Sort by product name
        log.info("TC07_Verify_Sort_Function: Click on sort by name");
        productPage.clickOnColumnNameToSort(driver,"Name");

        log.info("TC07_Verify_Sort_Function: Verify product names are sort ASC");
        verifyTrue(productPage.getClassValue( "Name").contains("asc"));
//        verifyTrue(productPage.isItemSortASC("Name"));


        log.info("TC07_Verify_Sort_Function: Click on sort by name");
        productPage.clickOnColumnNameToSort(driver,"Name");

        log.info("TC07_Verify_Sort_Function: Verify product names are sort DSC");
        verifyTrue(productPage.getClassValue("Name").contains("desc"));

//        verifyTrue(productPage.isItemSortASC("Name"));
//
//        //Sort by product Price
//        log.info("TC07_Verify_Sort_Function: Click on sort by price");
//        productPage.clickOnSortButtonByColumnName("price");
//
//        log.info("TC07_Verify_Sort_Function: Verify product price are sort ASC");
//        verifyTrue(productPage.isItemSortASC("Price"));
//
//
//        log.info("TC07_Verify_Sort_Function: Click on sort by Price");
//        productPage.clickOnSortButtonByColumnName("Price");
//
//        log.info("TC07_Verify_Sort_Function: Verify product Price are sort DSC");
//        verifyTrue(productPage.isItemSortDSC("Price"));
//
////Sort by product Status
//        log.info("TC07_Verify_Sort_Function: Click on sort by Status");
//        productPage.clickOnSortButtonByColumnName("Status");
//
//        log.info("TC07_Verify_Sort_Function: Verify product Status are sort ASC");
//        verifyTrue(productPage.isItemSortASC("Status"));
//
//
//        log.info("TC07_Verify_Sort_Function: Click on sort by Status");
//        productPage.clickOnSortButtonByColumnName("Status");
//
//        log.info("TC07_Verify_Sort_Function: Verify product Status are sort DSC");
//        verifyTrue(productPage.isItemSortDSC("Status"));
//
//        //Sort by Updated at
//        log.info("TC07_Verify_Sort_Function: Click on sort by Updated at");
//        productPage.clickOnSortButtonByColumnName("Updated at");
//
//        log.info("TC07_Verify_Sort_Function: Verify Updated at are sort ASC");
//        verifyTrue(productPage.isItemSortASC("Updated at"));
//
//
//        log.info("TC07_Verify_Sort_Function: Click on sort by Updated at");
//        productPage.clickOnSortButtonByColumnName("Updated at");
//
//        log.info("TC07_Verify_Sort_Function: Verify Updated at are sort DSC");
//        verifyTrue(productPage.isItemSortDSC("Updated at"));
//    }
//
////    @Test
//    public void TC08_Verify_Total_Items_In_Country() {
//        int totalItemsByText;
//        int totalItemCount;
//        //Country = vietnam
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Viet nam");
//        productPage.selectValueInCountryDropdown("Viet Nam");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = Singapore
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Singapore");
//        productPage.selectValueInCountryDropdown("Singapore");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = Thailand
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Thailand");
//        productPage.selectValueInCountryDropdown("Thailand");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = Indonesia
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Indonesia");
//        productPage.selectValueInCountryDropdown("Indonesia");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = Malaysia
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Malaysia");
//        productPage.selectValueInCountryDropdown("Malaysia");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = Philippines
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = Philippines");
//        productPage.selectValueInCountryDropdown("Philippines");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);
//
//        //Country = England
//        log.info("TC08_Verify_Total_Items_In_Country: Select country = England");
//        productPage.selectValueInCountryDropdown("England");
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total item by text");
//        totalItemsByText = productPage.getTotalItemInText();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Get total items by counting in table");
//        totalItemCount = productPage.countItemInTable();
//
//        log.info("TC08_Verify_Total_Items_In_Country: Verify item counting = item in text");
//        verifyEquals(totalItemCount, totalItemsByText);

    }


}
