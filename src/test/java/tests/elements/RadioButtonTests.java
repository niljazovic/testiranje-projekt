package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.RadioButtonPage;
import tests.base.BaseTest;

public class RadioButtonTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void selectYes_shouldShowYesMessage() {
        RadioButtonPage page = new RadioButtonPage(driver, wait).open(config.getBaseUrl())
                .selectYes();

        Assert.assertTrue(page.getSelectedMessage().toLowerCase().contains("yes"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void selectImpressive_shouldShowImpressiveMessage() {
        RadioButtonPage page = new RadioButtonPage(driver, wait).open(config.getBaseUrl())
                .selectImpressive();

        Assert.assertTrue(page.getSelectedMessage().toLowerCase().contains("impressive"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void noRadio_shouldBeDisabled() {
        RadioButtonPage page = new RadioButtonPage(driver, wait).open(config.getBaseUrl());

        Assert.assertTrue(page.isNoDisabled(), "No radio should be disabled");
    }
}
