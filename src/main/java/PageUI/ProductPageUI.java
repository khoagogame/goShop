package PageUI;

public class ProductPageUI {
    public static final String PRODUCT_PAGE_TITLE = "//h5[text()='Product']";
    public static final String EXPORT_CSV_BUTTON = "//span[text()='Export CSV']/parent::button";
    public static final String EXPORT_EXCEL_BUTTON = "//span[text()='Export Excel']/parent::button";
    public static final String SEARCH_INPUT_TEXT = "//input[@type='search']";
    public static final String TOTAL_PRODUCT_IN_PAGE = "//tbody/tr";
    public static final String COUNTRY_DROPDOWN = "//select[@id='product_country']";
    public static final String ENTRY_DISPLAY_DROPDOWN = "//select[@name='product-table_length']";
    public static final String COLUMN_INDEX_BY_NAME = "//th[text()='%s']/preceding-sibling::th";
    public static final String COLUMN_VALUE_BY_INDEX = "//td[%s]";
    public static final String PRODUCT_NOT_FOUND_MESSAGE = "//td[text()='No matching records found']";
    public static final String COLUMN_NAME = "//th[text()='%s']";

}
