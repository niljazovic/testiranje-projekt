package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.TextBoxPage;
import tests.base.BaseTest;

public class TextBoxTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void submitValidData_shouldShowOutput() {
        TextBoxPage page = new TextBoxPage(driver, wait).open(config.getBaseUrl());

        page.setFullName("Marko Markovic")
                .setEmail("marko@mail.com")
                .setCurrentAddress("Zagreb 1")
                .setPermanentAddress("Zagreb 2")
                .submit();

        Assert.assertTrue(page.isOutputVisible(), "Output should be visible after submit");
        Assert.assertTrue(page.getOutputName().contains("Marko Markovic"));
        Assert.assertTrue(page.getOutputEmail().contains("marko@mail.com"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void submitWithoutEmail_shouldShowOutputWithoutEmailLine() {
        TextBoxPage page = new TextBoxPage(driver, wait).open(config.getBaseUrl());

        page.setFullName("Test User")
                .setCurrentAddress("Addr")
                .setPermanentAddress("Addr2")
                .submit();

        Assert.assertTrue(page.isOutputVisible(), "Output should be visible");
        Assert.assertFalse(driver.getPageSource().contains("Email:"), "Email should not be shown in output when not provided");
    }


    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void submitInvalidEmail_shouldNotShowOutput() {
        TextBoxPage page = new TextBoxPage(driver, wait).open(config.getBaseUrl());

        page.setFullName("Test User")
                .setEmail("not-an-email")
                .setCurrentAddress("Addr")
                .setPermanentAddress("Addr2")
                .submit();

        Assert.assertFalse(page.isOutputVisible(), "Output should not be visible when email is invalid");
    }
}
