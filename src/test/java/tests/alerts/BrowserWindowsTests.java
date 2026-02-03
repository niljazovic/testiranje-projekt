package tests.alerts;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.alerts.BrowserWindowsPage;
import tests.base.BaseTest;

import java.util.ArrayList;

public class BrowserWindowsTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void newTab_shouldOpenAndHaveText() {
        BrowserWindowsPage page = new BrowserWindowsPage(driver, wait).open(config.getBaseUrl());

        String original = driver.getWindowHandle();
        page.openNewTab();

        wait.numberOfWindowsToBe(2);

        var handles = new ArrayList<>(driver.getWindowHandles());
        for (String h : handles) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }
        Assert.assertTrue(driver.getPageSource().contains("This is a sample page"));

        driver.close();
        driver.switchTo().window(original);
        Assert.assertTrue(driver.getCurrentUrl().contains("/browser-windows"));
    }
}
