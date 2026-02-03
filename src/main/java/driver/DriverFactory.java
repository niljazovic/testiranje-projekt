package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.PageLoadStrategy;

public class DriverFactory {

    public static WebDriver create(BrowserType browser, boolean headless) {
        return switch (browser) {
            case FIREFOX -> createFirefox(headless);
            case EDGE -> createEdge(headless);
            case CHROME -> createChrome(headless, false);
            case CHROMIUM -> createChrome(headless, true);
        };
    }

    private static WebDriver createFirefox(boolean headless) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        options.addArguments("--width=1400");
        options.addArguments("--height=900");
        if (headless) options.addArguments("-headless");
        return new org.openqa.selenium.firefox.FirefoxDriver(options);
    }

    private static WebDriver createEdge(boolean headless) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
        return new org.openqa.selenium.edge.EdgeDriver(options);
    }

    private static WebDriver createChrome(boolean headless, boolean isChromium) {
        WebDriverManager.chromedriver()
                .driverVersion("144")
                .forceDownload()
                .setup();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        if (headless) options.addArguments("--headless=new");
        options.addArguments("--window-size=1400,900");
        options.addArguments("--disable-gpu");

        if (isChromium) {
            String bin = System.getenv("CHROMIUM_BINARY");
            if (bin != null && !bin.isBlank()) {
                options.setBinary(bin.trim());
            }
        }

        return new org.openqa.selenium.chrome.ChromeDriver(options);
    }
}
