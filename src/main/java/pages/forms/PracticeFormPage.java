package pages.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.base.BasePage;
import utils.WaitUtil;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracticeFormPage extends BasePage {

    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By userEmail = By.id("userEmail");
    private final By userNumber = By.id("userNumber");
    private final By currentAddress = By.id("currentAddress");

    private final By genderMale = By.cssSelector("label[for='gender-radio-1']");
    private final By genderFemale = By.cssSelector("label[for='gender-radio-2']");
    private final By genderOther = By.cssSelector("label[for='gender-radio-3']");

    private final By dateOfBirthInput = By.id("dateOfBirthInput");

    private final By subjectsInput = By.id("subjectsInput");

    private final By hobbySports = By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By hobbyReading = By.cssSelector("label[for='hobbies-checkbox-2']");
    private final By hobbyMusic = By.cssSelector("label[for='hobbies-checkbox-3']");

    private final By uploadPicture = By.id("uploadPicture");

    private final By stateInput = By.id("react-select-3-input");
    private final By cityInput = By.id("react-select-4-input");

    private final By submit = By.id("submit");

    private final By modalTitle = By.id("example-modal-sizes-title-lg");
    private final By modalTable = By.cssSelector(".table-responsive table");

    private final By stateContainer = By.id("state");
    private final By cityContainer = By.id("city");

    private final By subjectsValueContainer = By.cssSelector(".subjects-auto-complete__value-container");
    private final By subjectsMenu = By.cssSelector(".subjects-auto-complete__menu");
    private final By subjectsFirstOption = By.cssSelector(".subjects-auto-complete__option");




    public PracticeFormPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public PracticeFormPage open(String baseUrl) {
        safeGet(baseUrl + "/automation-practice-form");
        wait.urlContains("/automation-practice-form");
        removeAdsIfPresent();
        closeAnyOverlays();
        wait.present(firstName);
        scrollIntoViewJs(firstName);

        return this;
    }


    public PracticeFormPage setFirstName(String v) { type(firstName, v); return this; }
    public PracticeFormPage setLastName(String v) { type(lastName, v); return this; }
    public PracticeFormPage setEmail(String v) { type(userEmail, v); return this; }
    public PracticeFormPage setMobile(String v) { type(userNumber, v); return this; }
    public PracticeFormPage setAddress(String v) { type(currentAddress, v); return this; }

    public PracticeFormPage selectGender(String gender) {
        String g = gender.toLowerCase();
        By target = switch (g) {
            case "male" -> genderMale;
            case "female" -> genderFemale;
            default -> genderOther;
        };
        scrollIntoView(target);
        click(target);
        return this;
    }

    public PracticeFormPage setDateOfBirthText(String value) {
        scrollIntoView(dateOfBirthInput);
        WebElement el = wait.visible(dateOfBirthInput);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(value);
        el.sendKeys(Keys.ENTER);
        el.sendKeys(Keys.ESCAPE);
        return this;
    }

    public PracticeFormPage addSubject(String subject) {
        closeAnyOverlays();
        removeAdsIfPresent();

        scrollIntoView(subjectsInput);

        WebElement el = wait.visible(subjectsInput);
        el.click();
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(subject);
        wait.present(subjectsMenu);
        jsClick(subjectsFirstOption);
        wait.untilTextPresent(subjectsValueContainer, subject);

        return this;
    }



    public PracticeFormPage selectHobby(String hobby) {
        String h = hobby.toLowerCase();
        By target = switch (h) {
            case "sports" -> hobbySports;
            case "reading" -> hobbyReading;
            default -> hobbyMusic;
        };
        scrollIntoView(target);
        jsClick(target);
        return this;
    }

    public PracticeFormPage uploadPicture(Path absolutePath) {
        scrollIntoView(uploadPicture);
        wait.visible(uploadPicture).sendKeys(absolutePath.toAbsolutePath().toString());
        return this;
    }

    public PracticeFormPage selectState(String state) {
        scrollIntoView(stateContainer);
        jsClick(stateContainer);
        WebElement el = wait.visible(stateInput);
        el.sendKeys(state);
        el.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        scrollIntoView(cityContainer);
        jsClick(cityContainer);
        WebElement el = wait.visible(cityInput);
        el.sendKeys(city);
        el.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage submitForm() {
        removeAdsIfPresent();
        scrollIntoView(submit);
        jsClick(submit);
        return this;
    }

    public boolean isModalVisible() {
        try {
            wait.visible(modalTitle);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTitle() {
        return text(modalTitle);
    }

    public Map<String, String> getModalTableAsMap() {
        Map<String, String> map = new HashMap<>();

        By rows = By.cssSelector(".modal-content tbody tr");
        List<WebElement> trList = driver.findElements(rows);

        for (WebElement tr : trList) {
            var tds = tr.findElements(By.tagName("td"));
            if (tds.size() >= 2) {
                String key = tds.get(0).getText().trim();
                String val = tds.get(1).getText().trim();
                map.put(key, val);
            }
        }
        return map;
    }

    private void removeAdsIfPresent() {
        try {
            if (driver instanceof org.openqa.selenium.JavascriptExecutor js) {
                js.executeScript(
                        "var e=document.getElementById('fixedban'); if(e){e.remove();}" +
                                "var f=document.querySelector('footer'); if(f){f.remove();}" +
                                "var a=document.querySelectorAll('iframe');" +
                                "for(var i=0;i<a.length;i++){try{a[i].remove();}catch(e){}}"
                );
            }
        } catch (Exception ignored) {}
    }

    private void closeAnyOverlays() {
        try {
            driver.switchTo().activeElement().sendKeys(Keys.ESCAPE);
        } catch (Exception ignored) {}

        try {
            if (driver instanceof org.openqa.selenium.JavascriptExecutor js) {
                js.executeScript("document.body.click();");
            }
        } catch (Exception ignored) {}

        removeAdsIfPresent();
    }

}
