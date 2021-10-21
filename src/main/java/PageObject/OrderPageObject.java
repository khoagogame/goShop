package PageObject;

import Commons.AbstractPage;
import PageUI.AbstractPageUI;
import PageUI.OrderPageUI;
import PageUI.ProductPageUI;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class OrderPageObject extends AbstractPage {
    WebDriver driver;

    public OrderPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isOrderPageTitleDisplay() {
        return isElementDisplay(driver, OrderPageUI.ORDER_PAGE_TITLE);
    }

    public String getSelectedValueInShowEntryDropdown() {
        return getSelectedValueInDropdown(driver, OrderPageUI.ENTRY_DISPLAY_DROPDOWN);
    }


    public boolean isExportCSVButtonDisplay() {
        return isElementDisplay(driver, OrderPageUI.EXPORT_CSV_BUTTON);
    }

    public boolean isExportExcelButtonDisplay() {
        return isElementDisplay(driver, OrderPageUI.EXPORT_EXCEL_BUTTON);
    }

    public int getTotalItemInBottomText() {
        String[] cutText = getElementText(driver, ProductPageUI.TOTAL_PRODUCT_IN_BOTTOM_TEXT).split("of | entries");
        System.out.println(Integer.valueOf(cutText[1].replaceAll(",", "")));
        return Integer.valueOf(cutText[1].replaceAll(",", ""));
    }

    public void selectValueInShowEntryDropdown(String value) {
        selectItemInDropdownList(driver, OrderPageUI.ENTRY_DISPLAY_DROPDOWN, value);
        waitForProcessBarDisappear(driver);
    }

    public int countTotalOrdersInPage() {
        return countElement(driver, OrderPageUI.TOTAL_ORDER_IN_PAGE);
    }

    public void selectTypeOfSearch(String value) {
        selectItemInDropdownList(driver, OrderPageUI.TYPE_OF_SEARCH_DROPDOWN, value);
    }

    public void inputTextToSearchTextbox(String value) {
        inputText(driver, OrderPageUI.SEARCH_TEXTBOX, value);
        waitForProcessBarDisappear(driver);
    }

    public void clearAllTextInSearchBox() {
        clearAllText(driver, OrderPageUI.SEARCH_TEXTBOX);
        findElement(driver, OrderPageUI.SEARCH_TEXTBOX).sendKeys(Keys.ENTER);
        waitForProcessBarDisappear(driver);
    }

    public boolean isCellByColumnNameContainsText(String columnName, String searchKey) {
        boolean status = true;
        int index = findElements(driver, AbstractPageUI.COLUMN_INDEX_BY_NAME, columnName).size() + 1;
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        System.out.println("total page :" + totalPage);
        if (totalPage != 1) {
            for (int i = 1; i < totalPage + 1; i++) {
                clickToElement(driver, AbstractPageUI.PAGE_NUMBER, String.valueOf(i));
                waitForProcessBarDisappear(driver);
                List<WebElement> totalValue = findElements(driver, AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement e : totalValue) {
                    System.out.println("text = " + e.getText());

                    if (columnName.equals("Order By")| columnName.equals("Product")) {
                        if (!(e.getText().contains(searchKey))) {
                            return false;
                        }
                        System.out.println("page > 1 = " + !(e.getText().contains("(#") && e.getText().contains(searchKey) && e.getText().contains(")")));
                        if (!(e.getText().contains("(#") && e.getText().contains(searchKey) && e.getText().contains(")"))) {
                            return false;
                        }
                    }
                }
            }
        } else {
            List<WebElement> elements = findElements(driver, AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
            for (WebElement e : elements) {
                System.out.println(e.getText());
                System.out.println(columnName.equals("Order By"));
                if (columnName.equals("Order By")|columnName.equals("Product")) {
                    if (!(e.getText().contains(searchKey))) {
                        return false;
                    }
                    System.out.println("page=1 = " + !(e.getText().contains(searchKey)));
                    if (!(e.getText().contains(searchKey))) {
                        return false;
                    }
                }
            }
        }
        return status;
    }

    public int countTotalOrders() {
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        clickToElement(driver, AbstractPageUI.TOTAL_PAGES);
        waitForProcessBarDisappear(driver);
        int totalOrderInlastPage = countElement(driver, AbstractPageUI.TOTAL_PRODUCT_IN_PAGE);
        System.out.println((totalPage - 1) * 10 + totalOrderInlastPage);
        return (totalPage - 1) * 10 + totalOrderInlastPage;
    }


    public ArrayList<String> getAllValuesByColumnName(String columnName) {
        ArrayList<String> arr = new ArrayList<>();
        int totalPage = Integer.valueOf(findElement(driver, AbstractPageUI.TOTAL_PAGES).getText());
        int index = findElements(driver, AbstractPageUI.COLUMN_INDEX_BY_NAME, columnName).size() + 1;
        if (totalPage != 1) {
            for (int i = 1; i < totalPage + 1; i++) {
                clickToElement(driver, AbstractPageUI.PAGE_NUMBER, String.valueOf(i));
                waitForProcessBarDisappear(driver);
                List<WebElement> elements = findElements(driver, AbstractPageUI.COLUMN_VALUE_BY_INDEX, String.valueOf(index));
                for (WebElement e : elements) {
                    arr.add(e.getText());
                }
            }
        }
        System.out.println(arr);
        return arr;
    }

    public int getTotalOrderIDBySearchKey(ArrayList<String> allValuesByColumnName, String searchKey) {
        ArrayList<String> resultList = new ArrayList<>();
        ArrayList<String> cutTextList = new ArrayList<>();

        for (String a : allValuesByColumnName) {
            String[] alltext = a.split(" ");
            cutTextList.add(alltext[1]);
        }
        for (String a : cutTextList) {
            System.out.println(a + "contain =" + a.contains(searchKey));
            if (a.contains(searchKey)) {
                resultList.add(a);
            }
        }
        System.out.println(resultList.size());
        return resultList.size();
    }

    public Object getTotalOrderCodeBySearchKey(ArrayList<String> allValuesByColumnName, String searchKey) {
        ArrayList<String> resultList = new ArrayList<>();
        for (String a : allValuesByColumnName) {
            System.out.println(a + "contain =" + a.contains(searchKey));
            if (a.contains(searchKey)) {
                resultList.add(a);
            }
        }
        System.out.println(resultList.size());
        return resultList.size();
    }

    public boolean isOrderEMailContains(String email) {
        boolean status = true;
        List<WebElement> elements = findElements(driver,OrderPageUI.VIEW_DETAILS_BUTTON);
        for(WebElement e: elements){
            openLinkInNewTab(e);
            switchToBrowserTab(driver);
            if(!getElementText(driver,OrderPageUI.ORDER_DETAILS_CUSTOMER_INFO_EMAIL).contains(email)){
                return false;
            }
            closeCurrentTabAndSwitchBackToMainTab(driver);
        }
        return status;
    }

    public boolean isOrderOutletContains(String outletName) {
        boolean status = true;
        List<WebElement> elements = findElements(driver,OrderPageUI.VIEW_DETAILS_BUTTON);
        for(WebElement e: elements){
            openLinkInNewTab(e);
            switchToBrowserTab(driver);
            if(!getElementText(driver,OrderPageUI.ORDER_DETAILS_CUSTOMER_INFO_OUTLET).contains(outletName)){
                return false;
            }
            closeCurrentTabAndSwitchBackToMainTab(driver);
        }
        return status;
    }
}
