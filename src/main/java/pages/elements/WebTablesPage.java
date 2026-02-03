package pages.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class WebTablesPage extends BasePage {

    private final By addBtn = By.id("addNewRecordButton");
    private final By searchBox = By.id("searchBox");

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By age = By.id("age");
    private final By salary = By.id("salary");
    private final By department = By.id("department");
    private final By submit = By.id("submit");

    private final By tableBody = By.cssSelector(".rt-tbody");

    public WebTablesPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public WebTablesPage open(String baseUrl) {
        safeGet(baseUrl + "/webtables");
        wait.urlContains("/webtables");
        wait.present(addBtn);

        return this;
    }

    public WebTablesPage clickAdd() {
        removeFixedBanIfPresent();
        wait.present(addBtn);
        scrollIntoViewJs(addBtn);
        jsClick(addBtn);

        return this;
    }


    public WebTablesPage fillForm(String fn, String ln, String em, String ag, String sal, String dep) {
        type(firstName, fn);
        type(lastName, ln);
        type(email, em);
        type(age, ag);
        type(salary, sal);
        type(department, dep);
        return this;
    }

    public WebTablesPage submitForm() {
        jsClick(submit);
        return this;
    }

    public WebTablesPage search(String text) {
        type(searchBox, text);
        return this;
    }

    public boolean tableContainsText(String text) {
        return wait.visible(tableBody).getText().toLowerCase().contains(text.toLowerCase());
    }

    public WebTablesPage clickEditByRowIndex(int rowIndexOneBased) {
        By edit = By.id("edit-record-" + rowIndexOneBased);
        scrollIntoView(edit);
        jsClick(edit);
        return this;
    }

    public WebTablesPage clickDeleteByRowIndex(int rowIndexOneBased) {
        By del = By.id("delete-record-" + rowIndexOneBased);
        scrollIntoView(del);
        jsClick(del);
        return this;
    }
}
