package tests.forms;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.forms.PracticeFormPage;
import tests.base.BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class PracticeFormTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void happyPath_fullForm_shouldShowModal() throws IOException {
        Path pic = Files.createTempFile("demoqa-pic-", ".txt");
        Files.writeString(pic, "fake picture");

        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Marko")
                .setLastName("Markovic")
                .setEmail("marko@mail.com")
                .selectGender("male")
                .setMobile("0912345678")
                .setDateOfBirthText("10 Feb 1999")
                .addSubject("Maths")
                .addSubject("English")
                .selectHobby("sports")
                .selectHobby("music")
                .uploadPicture(pic)
                .setAddress("Zagreb")
                .selectState("NCR")
                .selectCity("Delhi")
                .submitForm();

        Assert.assertTrue(page.isModalVisible(), "Modal should appear after submit");
        Assert.assertTrue(page.getModalTitle().toLowerCase().contains("thanks"));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void minimalRequired_shouldShowModal() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Ana")
                .setLastName("Ivic")
                .selectGender("female")
                .setMobile("0911111111")
                .submitForm();

        Assert.assertTrue(page.isModalVisible(), "Modal should appear with minimal required fields");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void missingMobile_shouldNotSubmit() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Ana")
                .setLastName("Ivic")
                .selectGender("female")
                .submitForm();

        Assert.assertFalse(page.isModalVisible(), "Modal should NOT appear if mobile is missing");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void invalidMobile_shouldNotSubmit() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Ana")
                .setLastName("Ivic")
                .selectGender("female")
                .setMobile("123") // premalo
                .submitForm();

        Assert.assertFalse(page.isModalVisible(), "Modal should NOT appear for invalid mobile");
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void setDate_shouldAppearInModalTable() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Ivo")
                .setLastName("Ivic")
                .selectGender("male")
                .setMobile("0912345678")
                .setDateOfBirthText("01 Jan 2000")
                .submitForm();

        Assert.assertTrue(page.isModalVisible());
        Map<String, String> data = page.getModalTableAsMap();

        String dob = data.getOrDefault("Date of Birth", "");
        Assert.assertTrue(dob.contains("01") || dob.toLowerCase().contains("jan"), "DOB should be present in modal: " + dob);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void subjects_shouldAppearInModalTable() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Iva")
                .setLastName("Ivic")
                .selectGender("female")
                .setMobile("0912345678")
                .addSubject("Computer Science")
                .addSubject("Maths")
                .submitForm();

        Assert.assertTrue(page.isModalVisible());
        Map<String, String> data = page.getModalTableAsMap();

        String subjects = data.getOrDefault("Subjects", "");
        Assert.assertTrue(subjects.toLowerCase().contains("computer") || subjects.toLowerCase().contains("maths"),
                "Subjects should be present: " + subjects);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void hobbies_shouldAppearInModalTable() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Pero")
                .setLastName("Peric")
                .selectGender("male")
                .setMobile("0912345678")
                .selectHobby("reading")
                .selectHobby("music")
                .submitForm();

        Assert.assertTrue(page.isModalVisible());
        Map<String, String> data = page.getModalTableAsMap();

        String hobbies = data.getOrDefault("Hobbies", "");
        Assert.assertTrue(hobbies.toLowerCase().contains("reading") || hobbies.toLowerCase().contains("music"),
                "Hobbies should be present: " + hobbies);
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void stateAndCity_shouldAppearInModalTable() {
        PracticeFormPage page = new PracticeFormPage(driver, wait).open(config.getBaseUrl());

        page.setFirstName("Luka")
                .setLastName("Lukic")
                .selectGender("other")
                .setMobile("0912345678")
                .selectState("NCR")
                .selectCity("Delhi")
                .submitForm();

        Assert.assertTrue(page.isModalVisible());
        Map<String, String> data = page.getModalTableAsMap();

        String stCity = data.getOrDefault("State and City", "");
        Assert.assertTrue(stCity.toLowerCase().contains("ncr") || stCity.toLowerCase().contains("delhi"),
                "State and City should be present: " + stCity);
    }
}
