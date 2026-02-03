package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.CheckBoxPage;
import tests.base.BaseTest;

public class CheckBoxTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void selectHome_shouldShowManyResults() {
        CheckBoxPage page = new CheckBoxPage(driver, wait).open(config.getBaseUrl())
                .expandAll()
                .selectHome();

        String result = page.getResultText();
        Assert.assertTrue(result.contains("home"), "Result should contain 'home'");
        Assert.assertTrue(result.contains("desktop") || result.contains("documents"),
                "Result should include child nodes too");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void selectDesktop_shouldShowDesktopInResult() {
        CheckBoxPage page = new CheckBoxPage(driver, wait).open(config.getBaseUrl())
                .expandAll()
                .selectDesktop();

        Assert.assertTrue(page.getResultText().contains("desktop"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void selectDocuments_shouldShowDocumentsInResult() {
        CheckBoxPage page = new CheckBoxPage(driver, wait).open(config.getBaseUrl())
                .expandAll()
                .selectDocuments();

        Assert.assertTrue(page.getResultText().contains("documents"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void collapseAll_thenExpandAll_shouldWork() {
        CheckBoxPage page = new CheckBoxPage(driver, wait).open(config.getBaseUrl())
                .collapseAll()
                .expandAll()
                .selectDesktop();

        Assert.assertTrue(page.getResultText().contains("desktop"));
    }
}
