package pages.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class AlertsPage extends BasePage {

    private final By alertButton = By.id("alertButton");
    private final By timerAlertButton = By.id("timerAlertButton");
    private final By confirmButton = By.id("confirmButton");
    private final By promptButton = By.id("promtButton");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public AlertsPage open(String baseUrl) {
        safeGet(baseUrl + "/alerts");
        wait.urlContains("/alerts");
        return this;
    }

    public void clickSimpleAlert() {
        scrollIntoView(alertButton);
        click(alertButton);
    }

    public void clickTimerAlert() {
        scrollIntoView(timerAlertButton);
        click(timerAlertButton);
    }

    public void clickConfirmAlert() {
        scrollIntoView(confirmButton);
        click(confirmButton);
    }

    public void clickPromptAlert() {
        scrollIntoView(promptButton);
        click(promptButton);
    }

    public String getConfirmResult() {
        return text(confirmResult);
    }

    public String getPromptResult() {
        return text(promptResult);
    }
}
