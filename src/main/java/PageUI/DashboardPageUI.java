package PageUI;

public class DashboardPageUI {
    public static final String DASHBOARD_PAGE_TITLE = "//a[contains(text(),'Dashboard')]";
//    public static final String ORDER_BY_DATE = "(//*[name()='path' and @visibility='hidden' and @fill='#00bcd4'])[1]/following-sibling::*[@opacity='1']";
    public static final String ORDER_BY_DATE = "(//*[name()='path' and @visibility='hidden' and @fill='#00bcd4'])[1]/following-sibling::*[@opacity='1'][4]";
    public static final String STATUS_BY_NAME_FILTER_IN_HIGHCHART = "//*[name()='tspan' and text()='%s']";
    public static final String TOOLTIP_TEXT_CONTENT = "//div[contains(@class,'highcharts-label')]/span";

}
