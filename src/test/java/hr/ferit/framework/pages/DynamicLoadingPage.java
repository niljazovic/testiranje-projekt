package hr.ferit.framework.pages;

import hr.ferit.framework.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage {
    private final WebDriver driver;
    private final Waits waits;

    private final By example1 = By.cssSelector("a[href='/dynamic_loading/1']");

    public DynamicLoadingPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public DynamicLoadingPage open() {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");
        waits.waitForDocumentReady();
        return this;
    }

    public void openExample1() {
        waits.clickable(example1).click();
        waits.waitForDocumentReady();
    }
}
