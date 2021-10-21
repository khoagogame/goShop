package PageObject;

import Commons.AbstractPage;
import Commons.GlobalConstant;
import PageUI.AbstractPageUI;
import PageUI.ProductPageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ProductPageObject extends AbstractPage {
    WebDriver driver;

    public ProductPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getSelectedValueInCountryDropdown() {
        return getSelectedValueInDropdown(driver, ProductPageUI.COUNTRY_DROPDOWN);
    }

    public String getSelectedValueInShowEntryDropdown() {
        return getSelectedValueInDropdown(driver, ProductPageUI.ENTRY_DISPLAY_DROPDOWN);
    }

    public boolean isExportCSVButtonDisplay() {
        return isElementDisplay(driver, ProductPageUI.EXPORT_CSV_BUTTON);
    }

    public boolean isExportExcelButtonDisplay() {
        return isElementDisplay(driver, ProductPageUI.EXPORT_EXCEL_BUTTON);
    }

    public void selectValueInShowEntryDropdown(String value) {
        selectItemInDropdownList(driver, ProductPageUI.ENTRY_DISPLAY_DROPDOWN, value);
        waitForProcessBarDisappear(driver);
    }

    public int countTotalProductInPage() {
        return countElement(driver, AbstractPageUI.TOTAL_PRODUCT_IN_PAGE);
    }

    public void selectValueInCountryDropdown(String countryName) {
        selectItemInDropdownList(driver, ProductPageUI.COUNTRY_DROPDOWN, countryName);
        waitForProcessBarDisappear(driver);
    }

    public boolean isPriceHasCurrency(String currency) {
        boolean status = true;
        ArrayList<String> priceList = new ArrayList<>();
        int index = findElements(driver, AbstractPageUI.COLUMN_INDEX_BY_NAME, "Price").size() + 1;
        List<WebElement> productPriceTotalItems = findElements(driver, AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
        for (WebElement e : productPriceTotalItems) {
            priceList.add(e.getText());
        }
        System.out.println(priceList);

        for (String a : priceList) {
            System.out.println("status = " + a.contains(currency));
            if (!a.contains(currency)) {
                return false;
            }
        }
        return status;
    }

    public void inputAndSearchText(String value) {
        inputText(driver, ProductPageUI.SEARCH_INPUT_TEXT, value);
        waitForProcessBarDisappear(driver);
    }

    public boolean isProductNameDisplay(String productName) {
        boolean status = true;
        ArrayList<String> allProductName = getAllValueByColumnName("Name");

        for (String a : allProductName) {
            System.out.println(a);
            if (productName.equalsIgnoreCase(a)) {
                return false;
            }
        }
        return status;
    }

    public ArrayList<String> getAllValueByColumnName(String columnName) {
        ArrayList<String> allItemValue = new ArrayList<>();
        int index = findElements(driver, AbstractPageUI.COLUMN_INDEX_BY_NAME, columnName).size() + 1;
        List<WebElement> allItemsByColumnName = findElements(driver, AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
        for (WebElement e : allItemsByColumnName) {
            allItemValue.add(e.getText());
        }
        return allItemValue;
    }

    public void clearTextInSearch() {
        clearAllText(driver, ProductPageUI.SEARCH_INPUT_TEXT);
        findElement(driver, ProductPageUI.SEARCH_INPUT_TEXT).sendKeys(Keys.ENTER);
        waitForProcessBarDisappear(driver);

    }

    public boolean isNoProductFoundMessageDisplay() {
        return isElementDisplay(driver, ProductPageUI.PRODUCT_NOT_FOUND_MESSAGE);
    }

    public void clickOnExportCSVButton() throws Exception {
        clickToElement(driver, ProductPageUI.EXPORT_CSV_BUTTON);
        waitForDownloadFileFullnameCompleted();
    }

    public void clickOnExportExcelButton() throws Exception {
        clickToElement(driver, ProductPageUI.EXPORT_EXCEL_BUTTON);
        waitForDownloadFileFullnameCompleted();
    }

    public String getClassValue(String columnName){
        return getAttributeValue(driver,ProductPageUI.COLUMN_NAME,"class",columnName);
    }

    public boolean AreValueByColumnNameSortASC(String columnName) {
        return sortStringASC(driver,columnName);
    }

    public boolean AreValueByColumnNameSortDESC(String columnName) {
        return sortStringDESC(driver,columnName);
    }

    public boolean AreIntegerByColumnNameSortASC(String columnName) {
        return sortIntASC(driver,columnName);
    }
    public boolean AreIntegerByColumnNameSortDESC(String columnName) {
        return sortIntDESC(driver,columnName);
    }

    //==============================DOWNLOAD FILE==========================================

    public void waitForDownloadFileFullnameCompleted() throws Exception {
        int i = 0;
        while (i < 30) {
            boolean exist = isFileExists();
            System.out.println("is file exist :" + exist);
            if (exist == true) {
                i = 30;
            }
            Thread.sleep(500);
            i = i + 1;
        }
    }

    public boolean isFileExists() {
        try {
            File files = new File(GlobalConstant.DOWNLOAD_FOLDER);
            boolean exists = files.exists();
            return exists;
        } catch (Exception e) {
            System.out.print(e.getMessage());
            return false;
        }
    }

    public int countFilesInDirectory(String fileExtension) {
        File file = new File(GlobalConstant.DOWNLOAD_FOLDER);
        int i = 0;
        for (File listOfFiles : file.listFiles()) {
            if (listOfFiles.getName().contains(fileExtension)) {
                i++;
            }
        }
        return i;
    }

    public boolean isFileExtensionCorrect(String fileExtension){
        boolean status = true;
        File file = new File(GlobalConstant.DOWNLOAD_FOLDER);
        for(File item: file.listFiles()){
            System.out.println("file name = " +item.getName());
            if(!item.getName().contains(fileExtension)){
                return false;
            }
        }
        return status;
    }

    public boolean isDateSortASC(String columnName) {
        return AreDateSortASC(driver, columnName);
    }
    public boolean isDateSortDESC(String columnName) {
        return AreDateSortDESC(driver, columnName);
    }

    public int getTotalItemInBottomText() {
        String [] cutText = getElementText(driver,ProductPageUI.TOTAL_PRODUCT_IN_BOTTOM_TEXT).split("of | entries");
        System.out.println(Integer.valueOf(cutText[1].replaceAll(",","")));
        return Integer.valueOf(cutText[1].replaceAll(",",""));
    }

    public int countTotalItems() {
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        clickToElement(driver,AbstractPageUI.TOTAL_PAGES);
        waitForProcessBarDisappear(driver);
        int totalProductInlastPage = countElement(driver,AbstractPageUI.TOTAL_PRODUCT_IN_PAGE);
        System.out.println((totalPage - 1)*10 + totalProductInlastPage);
        return (totalPage - 1)*10 + totalProductInlastPage;
    }


//==============================================================================================




}