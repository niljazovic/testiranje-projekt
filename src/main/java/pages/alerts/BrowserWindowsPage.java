package pages.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class BrowserWindowsPage extends BasePage {

    private final By newTabButton = By.id("tabButton");
    private final By newWindowButton = By.id("windowButton");
    private final By newWindowMessageButton = By.id("messageWindowButton");

    public BrowserWindowsPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public BrowserWindowsPage open(String baseUrl) {
        safeGet(baseUrl + "/browser-windows");
        wait.urlContains("/browser-windows");
        return this;
    }

    public void openNewTab() {
        scrollIntoView(newTabButton);
        click(newTabButton);
    }

    public void openNewWindow() {
        scrollIntoView(newWindowButton);
        click(newWindowButton);
    }

    public void openNewWindowMessage() {
        scrollIntoView(newWindowMessageButton);
        click(newWindowMessageButton);
    }
}
