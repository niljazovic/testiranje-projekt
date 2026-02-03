package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.ButtonsPage;
import tests.base.BaseTest;

public class ButtonsTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void doubleClick_shouldShowMessage() {
        ButtonsPage page = new ButtonsPage(driver, wait).open(config.getBaseUrl())
                .doubleClick();

        Assert.assertTrue(page.getDoubleClickMessage().toLowerCase().contains("double click"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void rightClick_shouldShowMessage() {
        ButtonsPage page = new ButtonsPage(driver, wait).open(config.getBaseUrl())
                .rightClick();

        Assert.assertTrue(page.getRightClickMessage().toLowerCase().contains("right click"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void dynamicClick_shouldShowMessage() {
        ButtonsPage page = new ButtonsPage(driver, wait).open(config.getBaseUrl())
                .dynamicClick();

        Assert.assertTrue(page.getDynamicClickMessage().toLowerCase().contains("dynamic click"));
    }
}
