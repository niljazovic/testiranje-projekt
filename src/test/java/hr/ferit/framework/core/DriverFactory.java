package hr.ferit.framework.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    public static WebDriver createDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        if ("firefox".equals(browser)) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions fo = new FirefoxOptions();
            if (headless) fo.addArguments("-headless");
            return new FirefoxDriver(fo);
        }

        WebDriverManager.chromedriver().setup();
        ChromeOptions co = new ChromeOptions();

        co.setBinary("/usr/bin/chromium-browser");

        if (headless) co.addArguments("--headless=new");

        return new ChromeDriver(co);
    }
}
