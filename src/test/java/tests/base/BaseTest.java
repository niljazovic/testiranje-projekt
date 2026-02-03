package tests.base;

import config.Config;
import config.ConfigLoader;
import driver.BrowserType;
import driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.WaitUtil;

import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;
    protected WaitUtil wait;
    protected Config config;

    @Parameters({"browser", "headless"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("firefox") String browser,
                      @Optional("false") String headless) {
        browser = System.getProperty("browser", browser);
        headless = System.getProperty("headless", headless);

        String envHeadless = System.getenv("HEADLESS");
        if (envHeadless != null && !envHeadless.isBlank()) {
            headless = envHeadless;
        }

        config = ConfigLoader.load();

        BrowserType bt = BrowserType.from(browser);
        boolean isHeadless = Boolean.parseBoolean(headless);

        System.out.println("Starting tests on browser: " + bt +
                " | headless=" + isHeadless);

        driver = DriverFactory.create(bt, isHeadless);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0)); // only explicit waits
        driver.manage().window().maximize();

        wait = new WaitUtil(driver, config.getDefaultTimeoutSeconds());
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
