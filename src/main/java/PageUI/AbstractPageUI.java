package PageUI;

public class AbstractPageUI {
    public static final String PARTNER_NAME= "//div[@id='more-details']";
    public static final String LOGOUT_BUTTON= "//a[@data-original-title='Logout']";
    public static final String VIEW_PROFILE_ICON= "//div[@id='nav-user-link']//i[@class='feather icon-user']";
    public static final String LEFT_MENU_BY_NAME= "//span[text()='%s']";
    public static final String PROCESSING_LOADING = "//div[@id='product-table_processing' and @style='display: block;']";
    public static final String TOTAL_PAGES = "//ul[@class='pagination']/li[position() = (last() - 1)]";
    public static final String PAGE_NUMBER= "//li/a[text()='%s']";
    public static final String COLUMN_INDEX_BY_NAME = "//th[text()='%s']/preceding-sibling::th";
    public static final String COLUMN_VALUE_BY_INDEX = "//td[%s]";
    public static final String TOTAL_PRODUCT_IN_PAGE = "//tbody/tr";

}
