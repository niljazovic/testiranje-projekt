package hr.ferit.framework.pages;

import hr.ferit.framework.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckboxesPage {
    private final WebDriver driver;
    private final Waits waits;

    private final By checkboxes = By.cssSelector("#checkboxes input[type='checkbox']");

    public CheckboxesPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public CheckboxesPage open() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        waits.waitForDocumentReady();
        return this;
    }

    public WebElement checkbox(int index1Based) {
        return driver.findElements(checkboxes).get(index1Based - 1);
    }
}
