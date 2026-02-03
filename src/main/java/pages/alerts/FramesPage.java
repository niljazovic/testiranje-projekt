package pages.alerts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class FramesPage extends BasePage {

    private final By frame1 = By.id("frame1");
    private final By frame2 = By.id("frame2");
    private final By frameHeading = By.id("sampleHeading");

    public FramesPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public FramesPage open(String baseUrl) {
        safeGet(baseUrl + "/frames");
        wait.urlContains("/frames");
        return this;
    }

    public String getHeadingFromFrame1() {
        wait.frameAvailableAndSwitchToIt(frame1);
        String txt = text(frameHeading);
        driver.switchTo().defaultContent();
        return txt;
    }

    public String getHeadingFromFrame2() {
        wait.frameAvailableAndSwitchToIt(frame2);
        String txt = text(frameHeading);
        driver.switchTo().defaultContent();
        return txt;
    }

    public boolean isBackOnDefaultContent() {
        return wait.visible(frame1).isDisplayed();
    }
}
