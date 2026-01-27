package hr.ferit.framework.pages;

import hr.ferit.framework.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingExample1Page {
    private final Waits waits;

    private final By startBtn = By.cssSelector("#start button");
    private final By finish = By.cssSelector("#finish h4");

    public DynamicLoadingExample1Page(WebDriver driver) {
        this.waits = new Waits(driver);
    }

    public void start() {
        waits.clickable(startBtn).click();
    }

    public String finishText() {
        return waits.visible(finish).getText();
    }
}
