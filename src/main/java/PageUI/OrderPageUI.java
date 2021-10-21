package PageUI;

public class OrderPageUI {
    public static final String ORDER_PAGE_TITLE = "//h5[text()='Orders List']";
    public static final String EXPORT_CSV_BUTTON = "//span[text()='Export CSV']/parent::button";
    public static final String EXPORT_EXCEL_BUTTON = "//span[text()='Export Excel']/parent::button";
    public static final String ENTRY_DISPLAY_DROPDOWN = "//select[@name='partnerOrderTable_length']";
    public static final String TOTAL_ORDER_IN_PAGE = "//tbody/tr";
    public static final String TYPE_OF_SEARCH_DROPDOWN = "//select[@id='filterBoxInput']";
    public static final String SEARCH_TEXTBOX = "//input[@type='search']";
    public static final String VIEW_DETAILS_BUTTON = "//td[8]/a";
    public static final String ORDER_DETAILS_CUSTOMER_INFO_EMAIL = "//h6[text()='Customer Information']/following-sibling::p/a";
    public static final String ORDER_DETAILS_CUSTOMER_INFO_OUTLET = "//h6[text()='Customer Information']/following-sibling::p[position() = (last())]";

}
