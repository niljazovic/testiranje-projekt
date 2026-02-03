package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class CheckBoxPage extends BasePage {

    private final By expandAllBtn = By.cssSelector("button[title='Expand all']");
    private final By collapseAllBtn = By.cssSelector("button[title='Collapse all']");
    private final By result = By.id("result");

    private final By homeLabel = By.cssSelector("label[for='tree-node-home']");

    private final By desktopLabel = By.cssSelector("label[for='tree-node-desktop']");
    private final By documentsLabel = By.cssSelector("label[for='tree-node-documents']");

    public CheckBoxPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public CheckBoxPage open(String baseUrl) {
        safeGet(baseUrl + "/checkbox");
        wait.urlContains("/checkbox");
        wait.present(expandAllBtn);
        return this;
    }

    public CheckBoxPage expandAll() {
        removeFixedBanIfPresent();
        wait.present(expandAllBtn);
        scrollIntoViewJs(expandAllBtn);
        jsClick(expandAllBtn);

        return this;
    }


    public CheckBoxPage collapseAll() {
        removeFixedBanIfPresent();
        wait.present(collapseAllBtn);
        scrollIntoViewJs(collapseAllBtn);
        jsClick(collapseAllBtn);

        return this;
    }


    public CheckBoxPage selectHome() {
        scrollIntoView(homeLabel);
        click(homeLabel);
        return this;
    }

    public CheckBoxPage selectDesktop() {
        scrollIntoView(desktopLabel);
        click(desktopLabel);
        return this;
    }

    public CheckBoxPage selectDocuments() {
        scrollIntoView(documentsLabel);
        click(documentsLabel);
        return this;
    }

    public String getResultText() {
        return text(result).toLowerCase();
    }
}
