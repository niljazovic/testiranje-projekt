package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.base.BasePage;
import utils.WaitUtil;

public class ButtonsPage extends BasePage {

    private final By doubleClickBtn = By.id("doubleClickBtn");
    private final By rightClickBtn = By.id("rightClickBtn");
    private final By dynamicClickBtn = By.id("dynamicClickBtn");
    private final By clickMeFallback = By.xpath("//button[normalize-space()='Click Me']");

    private final By doubleClickMessage = By.id("doubleClickMessage");
    private final By rightClickMessage = By.id("rightClickMessage");
    private final By dynamicClickMessage = By.id("dynamicClickMessage");

    public ButtonsPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public ButtonsPage open(String baseUrl) {
        safeGet(baseUrl + "/buttons");
        wait.urlContains("/buttons");
        return this;
    }

    public ButtonsPage doubleClick() {
        scrollIntoView(doubleClickBtn);
        new Actions(driver).doubleClick(wait.clickable(doubleClickBtn)).perform();
        return this;
    }

    public ButtonsPage rightClick() {
        scrollIntoView(rightClickBtn);
        new Actions(driver).contextClick(wait.clickable(rightClickBtn)).perform();
        return this;
    }

    public ButtonsPage dynamicClick() {
        if (driver.findElements(dynamicClickBtn).size() > 0) {
            try {
                wait.clickable(dynamicClickBtn).click();
            } catch (Exception e) {
                jsClick(dynamicClickBtn);
            }
            return this;
        }
        scrollIntoViewJs(clickMeFallback);
        try {
            wait.clickable(clickMeFallback).click();
        } catch (Exception e) {
            jsClick(clickMeFallback);
        }
        return this;
    }


    public String getDoubleClickMessage() {
        return text(doubleClickMessage);
    }

    public String getRightClickMessage() {
        return text(rightClickMessage);
    }

    public String getDynamicClickMessage() {
        return text(dynamicClickMessage);
    }
}
