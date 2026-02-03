package tests.elements;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.elements.WebTablesPage;
import tests.base.BaseTest;

public class WebTablesTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void addNewRecord_shouldAppearInTable() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        String email = "user" + System.currentTimeMillis() + "@mail.com";

        page.clickAdd()
                .fillForm("Ana", "Ivic", email, "25", "500", "QA")
                .submitForm()
                .search(email);

        Assert.assertTrue(page.tableContainsText(email), "Table should contain newly added email");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void searchExistingRecord_shouldFilterResults() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        page.search("Cierra");
        Assert.assertTrue(page.tableContainsText("Cierra"), "Table should contain filtered name");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void editFirstRow_shouldUpdateDepartment() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        page.clickEditByRowIndex(1)
                .fillForm("Cierra", "Vega", "cierra@example.com", "39", "10000", "Automation")
                .submitForm()
                .search("Automation");

        Assert.assertTrue(page.tableContainsText("Automation"), "Updated department should appear");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void deleteSecondRow_shouldRemoveRecordFromFilteredView() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        page.search("Alden");
        Assert.assertTrue(page.tableContainsText("Alden"));

        page.clickDeleteByRowIndex(2);

        page.search("Alden");
        Assert.assertFalse(page.tableContainsText("Alden"), "Record should be deleted (not visible after filtering)");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void addInvalidEmail_shouldNotBeAdded() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        page.clickAdd()
                .fillForm("Test", "User", "not-an-email", "20", "100", "Dept")
                .submitForm();

        page.search("not-an-email");
        Assert.assertFalse(page.tableContainsText("not-an-email"), "Invalid email should not appear in table");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void searchNoResults_shouldShowEmpty() {
        WebTablesPage page = new WebTablesPage(driver, wait).open(config.getBaseUrl());

        page.search("no_such_user_12345");
        Assert.assertFalse(page.tableContainsText("no_such_user_12345"));
    }
}
