package tests.alerts;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.alerts.AlertsPage;
import tests.base.BaseTest;

public class AlertsTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void simpleAlert_accept_shouldCloseAlert() {
        AlertsPage page = new AlertsPage(driver, wait).open(config.getBaseUrl());
        page.clickSimpleAlert();

        wait.alertPresent().accept();

        Assert.assertTrue(true);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void timerAlert_waitThenAccept_shouldWork() {
        AlertsPage page = new AlertsPage(driver, wait).open(config.getBaseUrl());
        page.clickTimerAlert();

        wait.alertPresent().accept();
        Assert.assertTrue(true);
    }

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void confirmAlert_dismiss_shouldShowCancelText() {
        AlertsPage page = new AlertsPage(driver, wait).open(config.getBaseUrl());
        page.clickConfirmAlert();

        wait.alertPresent().dismiss();
        Assert.assertTrue(page.getConfirmResult().toLowerCase().contains("cancel"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void promptAlert_sendText_shouldShowItInResult() {
        AlertsPage page = new AlertsPage(driver, wait).open(config.getBaseUrl());
        page.clickPromptAlert();

        var alert = wait.alertPresent();
        alert.sendKeys("Marko");
        alert.accept();

        Assert.assertTrue(page.getPromptResult().contains("Marko"));
    }
}
