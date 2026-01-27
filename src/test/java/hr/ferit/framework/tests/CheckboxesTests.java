package hr.ferit.framework.tests;

import hr.ferit.framework.core.BaseTest;
import hr.ferit.framework.pages.CheckboxesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckboxesTests extends BaseTest {

    @Test
    public void should_toggle_first_checkbox_on() {
        CheckboxesPage page = new CheckboxesPage(driver).open();
        if (!page.checkbox(1).isSelected()) page.checkbox(1).click();
        Assert.assertTrue(page.checkbox(1).isSelected());
    }
}
