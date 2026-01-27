package hr.ferit.framework.tests;

import hr.ferit.framework.core.BaseTest;
import hr.ferit.framework.pages.DynamicLoadingExample1Page;
import hr.ferit.framework.pages.DynamicLoadingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DynamicLoadingTests extends BaseTest {

    @Test
    public void should_show_hello_world_after_loading() {
        DynamicLoadingPage page = new DynamicLoadingPage(driver).open();
        page.openExample1();

        DynamicLoadingExample1Page ex1 = new DynamicLoadingExample1Page(driver);
        ex1.start();

        Assert.assertEquals(ex1.finishText(), "Hello World!");
    }
}
