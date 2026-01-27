package hr.ferit.framework.tests;

import hr.ferit.framework.core.BaseTest;
import hr.ferit.framework.pages.LoginPage;
import hr.ferit.framework.pages.SecureAreaPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void login_success_should_open_secure_area() {
        LoginPage login = new LoginPage(driver).open()
                .typeUsername("tomsmith")
                .typePassword("SuperSecretPassword!");

        login.submit();

        SecureAreaPage secure = new SecureAreaPage(driver);
        Assert.assertEquals(secure.headerText(), "Secure Area");
    }

    @Test
    public void login_wrong_password_should_show_error_flash() {
        LoginPage login = new LoginPage(driver).open()
                .typeUsername("tomsmith")
                .typePassword("wrong");

        login.submit();

        Assert.assertTrue(login.flashMessage().contains("Your password is invalid!"));
    }

    @Test
    public void login_empty_username_should_show_error_flash() {
        LoginPage login = new LoginPage(driver).open()
                .typeUsername("")
                .typePassword("SuperSecretPassword!");

        login.submit();

        Assert.assertTrue(login.flashMessage().contains("Your username is invalid!"));
    }
}
