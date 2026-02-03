package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class TextBoxPage extends BasePage {

    private final By fullName = By.id("userName");
    private final By email = By.id("userEmail");
    private final By currentAddress = By.id("currentAddress");
    private final By permanentAddress = By.id("permanentAddress");
    private final By submit = By.id("submit");

    private final By output = By.id("output");
    private final By outputName = By.id("name");
    private final By outputEmail = By.id("email");

    public TextBoxPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public TextBoxPage open(String baseUrl) {
        safeGet(baseUrl + "/text-box");
        wait.urlContains("/text-box");
        return this;
    }

    public TextBoxPage setFullName(String value) {
        type(fullName, value);
        return this;
    }

    public TextBoxPage setEmail(String value) {
        type(email, value);
        return this;
    }

    public TextBoxPage setCurrentAddress(String value) {
        type(currentAddress, value);
        return this;
    }

    public TextBoxPage setPermanentAddress(String value) {
        type(permanentAddress, value);
        return this;
    }

    public TextBoxPage submit() {
        scrollIntoView(submit);
        jsClick(submit);
        return this;
    }

    public boolean isOutputVisible() {
        try {
            wait.visible(output);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getOutputName() {
        return text(outputName);
    }

    public String getOutputEmail() {
        return text(outputEmail);
    }
}
