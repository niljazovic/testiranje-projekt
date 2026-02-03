package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class RadioButtonPage extends BasePage {

    private final By yesLabel = By.cssSelector("label[for='yesRadio']");
    private final By impressiveLabel = By.cssSelector("label[for='impressiveRadio']");
    private final By noInput = By.id("noRadio");

    private final By successText = By.cssSelector("p.mt-3");

    public RadioButtonPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public RadioButtonPage open(String baseUrl) {
        safeGet(baseUrl + "/radio-button");
        wait.urlContains("/radio-button");
        wait.present(yesLabel);
        return this;
    }


    public RadioButtonPage selectYes() {
        removeFixedBanIfPresent();
        wait.present(yesLabel);
        scrollIntoViewJs(yesLabel);
        jsClick(yesLabel);
        return this;
    }


    public RadioButtonPage selectImpressive() {
        removeFixedBanIfPresent();
        wait.present(impressiveLabel);
        scrollIntoViewJs(impressiveLabel);
        jsClick(impressiveLabel);
        return this;
    }


    public boolean isNoDisabled() {
        return !wait.present(noInput).isEnabled();
    }


    public String getSelectedMessage() {
        return text(successText);
    }
}
