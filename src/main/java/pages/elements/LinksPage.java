package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class LinksPage extends BasePage {

    private final By homeLink = By.id("simpleLink");
    private final By dynamicHomeLink = By.id("dynamicLink");

    private final By createdLink = By.id("created");
    private final By noContentLink = By.id("no-content");
    private final By linkResponse = By.id("linkResponse");

    public LinksPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public LinksPage open(String baseUrl) {
        safeGet(baseUrl + "/links");
        wait.urlContains("/links");
        return this;
    }

    public void clickHome() {
        scrollIntoView(homeLink);
        click(homeLink);
    }

    public void clickDynamicHome() {
        scrollIntoView(dynamicHomeLink);
        click(dynamicHomeLink);
    }

    public void clickCreated() {
        scrollIntoView(createdLink);
        click(createdLink);
    }

    public void clickNoContent() {
        scrollIntoView(noContentLink);
        click(noContentLink);
    }

    public String getApiResponseText() {
        return text(linkResponse);
    }
}
