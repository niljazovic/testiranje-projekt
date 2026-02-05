package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtil {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public WaitUtil(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    public WebElement visible(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement clickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public void urlContains(String part) {
        wait.until(ExpectedConditions.urlContains(part));
    }

    public Alert alertPresent() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public void numberOfWindowsToBe(int n) {
        wait.until(driver -> driver.getWindowHandles().size() == n);
    }

    public void frameAvailableAndSwitchToIt(By frameBy) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameBy));
    }

    public WebElement present(By by) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void untilTextPresent(By by, String text) {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(by, text));
    }
    public void pageSourceContains(String text) {
        wait.until(driver -> driver.getPageSource().contains(text));
    }


}
