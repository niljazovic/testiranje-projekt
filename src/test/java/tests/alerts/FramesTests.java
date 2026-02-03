package tests.alerts;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.alerts.FramesPage;
import tests.base.BaseTest;

public class FramesTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void frame1_shouldContainSampleHeading() {
        FramesPage page = new FramesPage(driver, wait).open(config.getBaseUrl());
        String heading = page.getHeadingFromFrame1();
        Assert.assertTrue(heading.toLowerCase().contains("sample page"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void frame2_shouldContainSampleHeading() {
        FramesPage page = new FramesPage(driver, wait).open(config.getBaseUrl());
        String heading = page.getHeadingFromFrame2();
        Assert.assertTrue(heading.toLowerCase().contains("sample page"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void afterReadingFrame_shouldReturnToDefaultContent() {
        FramesPage page = new FramesPage(driver, wait).open(config.getBaseUrl());
        page.getHeadingFromFrame1();
        Assert.assertTrue(page.isBackOnDefaultContent());
    }
}
