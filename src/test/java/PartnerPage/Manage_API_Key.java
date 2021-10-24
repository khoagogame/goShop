package PartnerPage;

import Commons.AbstractTest;
import Commons.GlobalConstant;
import PageObject.APIKeyPageObject;
import PageObject.DashboardPageObject;
import PageObject.LoginPageObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Manage_API_Key extends AbstractTest {
    WebDriver driver;
    LoginPageObject loginPage;
    DashboardPageObject dashboardPage;
    APIKeyPageObject apiKeyPage;
    String keyName = "QA test";
    String newKeyName = "Partner123";
    String hostName = "gogame.net";
    String newHostName = "google.com";
    String status_Active = "Active";
    String status_Inactive = "Inactive";
    @Parameters({"browser"})
    @BeforeClass
    public void BeforeTest(String browserName){
        driver = getBrowserDriver(browserName);

        log.info("Precondition: Login account");
        loginPage = openLoginPage(driver);
        loginPage.inputTextToTextboxByTitle("email", GlobalConstant.EMAIL);
        loginPage.inputTextToTextboxByTitle("password", GlobalConstant.PASSWORD);
        dashboardPage = loginPage.clickLoginButton();

        log.info("Precondition: Click on Manage API Key page");
        apiKeyPage = dashboardPage.clickOnManageAPIKeyMenu(driver);
    }

//    @Test
    public void TC01_Verify_Manage_API_Keys_Page_Display(){
        log.info("TC01_Verify_Manage_API_Keys_Page_Display: Verify Api Key page title display");
        verifyTrue(apiKeyPage.isAPIKeyPageTitleDisplay());
    }

//    @Test
    public void TC02_Verify_Add_New_API_Key_Button_Function(){
        log.info("TC02_Verify_Add_New_API_Key_Button_Function: Click on Add new button");
        apiKeyPage.clickOnAddNewButton();

        log.info("TC02_Verify_Add_New_API_Key_Button_Function: Verify Api Key page title display");
        verifyTrue(apiKeyPage.isAddNewFormDisplay());
    }

//    @Test
    public void TC03_Verify_Input_Field_Validation(){
        log.info("TC03_Verify_Input_Field_Validation: Let all fields empty");
        apiKeyPage.inputTextToKeyName("");
        apiKeyPage.inputTextToListHost("");
        apiKeyPage.clickOnSubmitButton();

        log.info("TC03_Verify_Input_Field_Validation: Verify warning message display in earch field");
        verifyEquals(apiKeyPage.getKeyNameWarningMessage(),"This field is required.");
        verifyEquals(apiKeyPage.getHostNameWarningMessage(),"This field is required.");
        verifyEquals(apiKeyPage.getStatusWarningMessage(),"This field is required.");

        log.info("TC03_Verify_Input_Field_Validation: Let Key Name is empty");
        apiKeyPage.inputTextToKeyName("");
        apiKeyPage.inputTextToListHost(hostName);
        apiKeyPage.selectStatusRadioButton(status_Active);
        apiKeyPage.clickOnSubmitButton();

        log.info("TC03_Verify_Input_Field_Validation: Verify warning message display in Key Name field");
        verifyEquals(apiKeyPage.getKeyNameWarningMessage(),"This field is required.");

        log.info("TC03_Verify_Input_Field_Validation: Let Host name is empty");
        apiKeyPage.inputTextToKeyName(keyName);
        apiKeyPage.inputTextToListHost("");
        apiKeyPage.selectStatusRadioButton(status_Active);
        apiKeyPage.clickOnSubmitButton();

        log.info("TC03_Verify_Input_Field_Validation: Verify warning message display in Host name field");
        verifyEquals(apiKeyPage.getHostNameWarningMessage(),"This field is required.");

        log.info("TC03_Verify_Input_Field_Validation: input incorrect format host name");
        apiKeyPage.inputTextToKeyName(keyName);
        apiKeyPage.inputTextToListHost("gogame");
        apiKeyPage.selectStatusRadioButton(status_Active);
        apiKeyPage.clickOnSubmitButton();

        log.info("TC03_Verify_Input_Field_Validation: Verify warning message display in Host name field");
        verifyEquals(apiKeyPage.getHostNameWarningMessage(),"Please check all Hosts/IPs again");

        log.info("TC03_Verify_Input_Field_Validation: Not select Status");
        apiKeyPage.reloadPage(driver);
        apiKeyPage.inputTextToKeyName(keyName);
        apiKeyPage.inputTextToListHost(hostName);
        apiKeyPage.clickOnSubmitButton();

        log.info("TC03_Verify_Input_Field_Validation: Verify warning message display in status field");
        verifyEquals(apiKeyPage.getStatusWarningMessage(),"This field is required.");


    }
//    @Test
    public void TC04_Verify_Create_API_Key_Success(){
        log.info("TC04_Verify_Create_API_Key_Success: input valid format");
        apiKeyPage.inputTextToKeyName(keyName);
        apiKeyPage.inputTextToListHost(hostName);
        apiKeyPage.selectStatusRadioButton(status_Active);
        apiKeyPage.clickOnSubmitButton();

        log.info("TC04_Verify_Create_API_Key_Success: input incorrect format");
        verifyTrue(apiKeyPage.getAPIKeyGeneratedSuccessfullyMessage().contains("API key pair has been Generated successfully"));
        verifyEquals(apiKeyPage.getKeyNameOfNewestAPIKey(), keyName);
        verifyEquals(apiKeyPage.getHostNameOfNewestAPIKey(), hostName);
        verifyEquals(apiKeyPage.getStatusOfNewestAPIKey(), "Activated");

    }

//    @Test
    public void TC05_Verify_Edit_API_Key_Name_Success(){
        log.info("TC05_Verify_Edit_API_Key_Name_Success: Click on Edit button by API Key name");
        apiKeyPage.clickToEditButtonByAPIKeyName(keyName);

        log.info("TC05_Verify_Edit_API_Key_Name_Success: Edit Key name");
        apiKeyPage.inputTextToKeyName(newKeyName);

        log.info("TC05_Verify_Edit_API_Key_Name_Success: Click to Submit");
        apiKeyPage.clickOnSubmitButton();

        log.info("TC05_Verify_Edit_API_Key_Name_Success: Verify API key update successfully");
        verifyTrue(apiKeyPage.getAPIKeyGeneratedSuccessfullyMessage().contains("API key pair has been Updated successfully"));
        verifyEquals(apiKeyPage.getKeyNameOfNewestAPIKey(), newKeyName);
        verifyEquals(apiKeyPage.getHostNameOfNewestAPIKey(), hostName);
        verifyEquals(apiKeyPage.getStatusOfNewestAPIKey(), "Activated");
    }

//    @Test
    public void TC06_Verify_Edit_Host_Name_Success(){
        log.info("TC06_Verify_Edit_Host_Name_Success: Click on Edit button by API Key name");
        apiKeyPage.clickToEditButtonByAPIKeyName(newKeyName);

        log.info("TC06_Verify_Edit_Host_Name_Success: Edit Host name");
        apiKeyPage.inputTextToListHost(newHostName);

        log.info("TC06_Verify_Edit_Host_Name_Success: Click to Submit");
        apiKeyPage.clickOnSubmitButton();

        log.info("TC06_Verify_Edit_Host_Name_Success: Verify API key update successfully");
        verifyTrue(apiKeyPage.getAPIKeyGeneratedSuccessfullyMessage().contains("API key pair has been Updated successfully"));
        verifyEquals(apiKeyPage.getKeyNameOfNewestAPIKey(), newKeyName);
        verifyEquals(apiKeyPage.getHostNameOfNewestAPIKey(), newHostName);
        verifyEquals(apiKeyPage.getStatusOfNewestAPIKey(), "Activated");

    }

//    @Test
    public void TC07_Verify_Edit_Host_Name_Success(){
        log.info("TC07_Verify_Edit_Host_Name_Success: Click on Edit button by API Key name");
        apiKeyPage.clickToEditButtonByAPIKeyName(newKeyName);

        log.info("TC07_Verify_Edit_Host_Name_Success: select status = inactive");
        apiKeyPage.selectStatusRadioButton(status_Inactive);

        log.info("TC07_Verify_Edit_Host_Name_Success: Click to Submit");
        apiKeyPage.clickOnSubmitButton();

        log.info("TC07_Verify_Edit_Host_Name_Success: Verify API key update successfully");
        verifyTrue(apiKeyPage.getAPIKeyGeneratedSuccessfullyMessage().contains("API key pair has been Updated successfully"));
        verifyEquals(apiKeyPage.getKeyNameOfNewestAPIKey(), newKeyName);
        verifyEquals(apiKeyPage.getHostNameOfNewestAPIKey(), newHostName);
        verifyEquals(apiKeyPage.getStatusOfNewestAPIKey(), "Inactivated");
    }

//    @Test
    public void TC08_Verify_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink(){
        log.info("TC08_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Click on add new button");
        apiKeyPage.clickOnAddNewButton();

        log.info("TC08_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: input valid fields");
        apiKeyPage.inputTextToKeyName("HCM Partner");
        apiKeyPage.inputTextToListHost("goplay.vn");
        apiKeyPage.selectStatusRadioButton("Active");

        log.info("TC08_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Click on Manage API Keys hyperlink");
        apiKeyPage.clickToManageAPIKeysHyperlink();

        log.info("TC08_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Verify back to API key page");
        verifyTrue(apiKeyPage.isAPIKeyPageTitleDisplay());

        log.info("TC08_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Verify api key is not created successfully");
        verifyFalse(apiKeyPage.isAPIKeyNameDisplay("HCM Partner"));
    }

//    @Test
    public void TC9_Verify_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink(){
        log.info("TC9_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Click on add new button");
        apiKeyPage.clickOnAddNewButton();

        log.info("TC9_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: input valid fields");
        apiKeyPage.inputTextToKeyName("HN Partner");
        apiKeyPage.inputTextToListHost("goplay.vn");
        apiKeyPage.selectStatusRadioButton("Active");

        log.info("TC9_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Click Cancel button");
        apiKeyPage.clickOnCancelButton();

        log.info("TC9_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Verify back to API key page");
        verifyTrue(apiKeyPage.isAPIKeyPageTitleDisplay());

        log.info("TC9_Veriy_API_Key_Not_Create_After_Clicking_On_Manage_API_Key_HyperLink: Verify api key is not created successfully");
        verifyFalse(apiKeyPage.isAPIKeyNameDisplay("HN Partner"));
    }

//    @Test
    public void TC10_Verify_Delete_Button_Function(){
        log.info("TC10_Verify_Delete_Button_Function: Click to Delete button");
        apiKeyPage.clickOnDeleteButtonByKeyName("HCM Partner");

        log.info("TC10_Verify_Delete_Button_Function: Click Cancel at Delete confirmation popup");
        apiKeyPage.clickOnDeleteConfirmationCancelButton();

        log.info("TC10_Verify_Delete_Button_Function: Verify Delete confirmation popup disappears and API key still exist");
        verifyFalse(apiKeyPage.isDeleteConfirmationPopupDisplay());
        verifyTrue(apiKeyPage.isAPIKeyNameDisplay("HCM Partner"));

        log.info("TC10_Verify_Delete_Button_Function: Click to Delete button");
        apiKeyPage.clickOnDeleteButtonByKeyName("HCM Partner");

        log.info("TC10_Verify_Delete_Button_Function: Click OK at Delete confirmation popup");
        apiKeyPage.clickOnDeleteConfirmationOKButton();

        log.info("TC10_Verify_Delete_Button_Function: Verify API key is deleted successfully popup display");
        verifyEquals(apiKeyPage.getDeleteAPIKeySuccessPopupMessage(),"API key pair has been Deleted successfully");

        log.info("TC10_Verify_Delete_Button_Function: Click on OK button at deleted successfully popup");
        apiKeyPage.clickOnDeleteAPIKeySuccessPopupOKButton();

        log.info("TC10_Verify_Delete_Button_Function: Verify api key is deleted successfully");
        verifyFalse(apiKeyPage.isDeleteConfirmationPopupDisplay());
        verifyFalse(apiKeyPage.isAPIKeyNameDisplay("HCM Partner"));
    }

//    @Test
    public void TC11_Verify_Search_Function(){
        log.info("TC11_Verify_Search_Function: Input keyword = gogame.net");
        int totalHostByName = apiKeyPage.getAllApiKeyNameByName("gogame.net");

        log.info("TC11_Verify_Search_Function: Input keyword = gogame.net");
        apiKeyPage.inputKeyWordToSearchTextbox("gogame.net");

        log.info("TC11_Verify_Search_Function: Count all api key name in search result");
        int totalHostNameInSearchResult =  apiKeyPage.getAllApiKeyName();


        log.info("TC11_Verify_Search_Function: Verify total host by name = total in search result");
        verifyEquals(totalHostNameInSearchResult,totalHostByName);
    }

    @Test
    public void TC12_Deleted_All_API_keys(){
        log.info("TC12_Deleted_All_API_keys: Delete all api keys");
        apiKeyPage.deleteAllAPIKeys();

        log.info("TC12_Deleted_All_API_keys: Verify no item display in API key page");
//        verifyTrue(apiKeyPage.isAllAPIKeyDeletedSuccessfully());

    }
//@Test(invocationCount = 70)
    public void TC013_dump_Data(){
        log.info("TC04_Verify_Create_API_Key_Success: input valid format");
    apiKeyPage.clickOnAddNewButton();
        apiKeyPage.inputTextToKeyName(keyName + randomNumber());
        apiKeyPage.inputTextToListHost(hostName+ randomNumber());
        apiKeyPage.selectStatusRadioButton(status_Active);
        apiKeyPage.clickOnSubmitButton();

    }
}
