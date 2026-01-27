package hr.ferit.framework.pages;

import hr.ferit.framework.core.Waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;
    private final Waits waits;

    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.cssSelector("button[type='submit']");
    private final By flash = By.id("flash");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waits = new Waits(driver);
    }

    public LoginPage open() {
        driver.get("https://the-internet.herokuapp.com/login");
        waits.waitForDocumentReady();
        return this;
    }

    public LoginPage typeUsername(String value) {
        waits.visible(username).clear();
        waits.visible(username).sendKeys(value);
        return this;
    }

    public LoginPage typePassword(String value) {
        waits.visible(password).clear();
        waits.visible(password).sendKeys(value);
        return this;
    }

    public void submit() {
        waits.clickable(loginBtn).click();
        waits.waitForDocumentReady();
    }

    public String flashMessage() {
        return waits.visible(flash).getText();
    }
}
