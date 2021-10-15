package PageUI;

public class DashboardPageUI {
    public static final String DASHBOARD_PAGE_TITLE = "//a[contains(text(),'Dashboard')]";
//    public static final String ORDER_BY_DATE = "(//*[name()='path' and @visibility='hidden' and @fill='#00bcd4'])[1]/following-sibling::*[@opacity='1']";
    public static final String ORDER_BY_DATE = "(//*[name()='path' and @visibility='hidden' and @fill='#00bcd4'])[1]/following-sibling::*[@opacity='1'][4]";
    public static final String STATUS_BY_NAME_FILTER_IN_HIGHCHART = "//*[name()='tspan' and text()='%s']";
    public static final String TOOLTIP_TEXT_CONTENT = "//div[contains(@class,'highcharts-label')]/span";
    public static final String POINT_IN_HIGHCHART_BY_INDEX = "//*[name()='g' and contains(@class,'highcharts-markers highcharts-series-1 highcharts-spline-series highcharts-color-1  highcharts-tracker')]/*[name()='path'][%s]";
    public static final String DAY_IN_HIGHCHART = "//*[name()='tspan' and text()='%s']/parent::*/preceding-sibling::*";
    public static final String HIGH_CHART = "//*[name()='rect' and @class='highcharts-background']";
    public static final String TOTAL_PAGES = "//ul[@class='pagination']//li[not(contains(@class,'next')) and not(contains(@class,'previous'))]";
    public static final String COLUMN_INDEX_BY_TITLE = "//th[text()='%s']/preceding-sibling::th";
    public static final String VALUE_BY_COLUMN_INDEX = "//td[%s]";

}
