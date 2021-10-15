package PageObject;

import Commons.AbstractPage;
import PageUI.ProductPageUI;
import org.openqa.selenium.WebDriver;

public class ProductPageObject extends AbstractPage {
    WebDriver driver;
    public ProductPageObject(WebDriver driver){
        this.driver = driver;
    }

    public String getSelectedValueInCountryDropdown() {
        return getSelectedValueInDropdown(driver, ProductPageUI.COUNTRY_DROPDOWN);
    }

    public String getSelectedValueInShowEntryDropdown() {
        return getSelectedValueInDropdown(driver,ProductPageUI.ENTRY_DISPLAY_DROPDOWN);
    }

    public boolean isExportCSVButtonDisplay() {
        return isElementDisplay(driver,ProductPageUI.EXPORT_CSV_BUTTON);
    }

    public boolean isExportExcelButtonDisplay() {
        return isElementDisplay(driver,ProductPageUI.EXPORT_EXCEL_BUTTON);
    }
}
