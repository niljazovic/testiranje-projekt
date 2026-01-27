package hr.ferit.framework.pages;

import hr.ferit.framework.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {
    private final Waits waits;
    private final By header = By.cssSelector("div.example h2");

    public SecureAreaPage(WebDriver driver) {
        this.waits = new Waits(driver);
    }

    public String headerText() {
        return waits.visible(header).getText();
    }
}
