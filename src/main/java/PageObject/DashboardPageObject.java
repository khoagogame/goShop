package PageObject;

import Commons.AbstractPage;
import PageUI.DashboardPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class DashboardPageObject extends AbstractPage {
    WebDriver driver;

    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDashBoardPageTitleDisplay() {
        return isElementDisplay(driver, DashboardPageUI.DASHBOARD_PAGE_TITLE);
    }

    public void numberOrderByDate(){
//

    }




}
