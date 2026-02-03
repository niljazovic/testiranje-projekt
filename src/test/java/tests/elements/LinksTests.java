package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.LinksPage;
import tests.base.BaseTest;

import java.util.ArrayList;

public class LinksTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void homeLink_shouldOpenNewTab() {
        LinksPage page = new LinksPage(driver, wait).open(config.getBaseUrl());

        String original = driver.getWindowHandle();
        page.clickHome();

        wait.numberOfWindowsToBe(2);

        var handles = new ArrayList<>(driver.getWindowHandles());
        for (String h : handles) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }

        wait.urlContains("demoqa.com");

        Assert.assertTrue(driver.getCurrentUrl().contains("demoqa.com"));

        driver.close();
        driver.switchTo().window(original);

        Assert.assertTrue(driver.getCurrentUrl().contains("/links"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void dynamicHomeLink_shouldOpenNewTab() {
        LinksPage page = new LinksPage(driver, wait).open(config.getBaseUrl());

        String original = driver.getWindowHandle();
        page.clickDynamicHome();

        wait.numberOfWindowsToBe(2);

        var handles = new ArrayList<>(driver.getWindowHandles());
        for (String h : handles) {
            if (!h.equals(original)) {
                driver.switchTo().window(h);
                break;
            }
        }

        wait.urlContains("demoqa.com");

        Assert.assertTrue(driver.getCurrentUrl().contains("demoqa.com"));
        driver.close();
        driver.switchTo().window(original);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void createdLink_shouldShowApiResponseMessage() {
        LinksPage page = new LinksPage(driver, wait).open(config.getBaseUrl());

        page.clickCreated();
        String resp = page.getApiResponseText().toLowerCase();

        Assert.assertTrue(resp.contains("created") || resp.contains("201"),
                "Expected API response to mention Created/201 but was: " + resp);
    }
}
