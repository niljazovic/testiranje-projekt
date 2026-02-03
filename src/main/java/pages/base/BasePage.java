package pages.base;

import org.openqa.selenium.*;
import utils.WaitUtil;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WaitUtil wait;

    protected BasePage(WebDriver driver, WaitUtil wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected void type(By by, String text) {
        removeFixedBanIfPresent();

        WebElement el;
        try {
            el = wait.visible(by);
        } catch (TimeoutException e) {
            wait.present(by);
            scrollIntoViewJs(by);
            el = driver.findElement(by);
        }

        try {
            el.clear();
        } catch (Exception ignored) {
            el.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        }

        el.sendKeys(text);
    }


    protected void click(By by) {
        wait.clickable(by).click();
    }

    protected void jsClick(By by) {
        WebElement el = wait.visible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected String text(By by) {
        return wait.visible(by).getText();
    }

    protected void scrollIntoView(By by) {
        WebElement el = wait.visible(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    protected void scrollIntoViewJs(By by) {
        var el = wait.present(by);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    protected void removeFixedBanIfPresent() {
        try {
            if (driver instanceof JavascriptExecutor js) {
                js.executeScript(
                        "var e=document.getElementById('fixedban'); if(e){e.remove();}" +
                                "var f=document.querySelector('footer'); if(f){f.remove();}"
                );
            }
        } catch (Exception ignored) {}
    }

    protected void safeGet(String url) {
        int attempts = 0;
        while (attempts < 5) {
            try {
                driver.get(url);

                String current = driver.getCurrentUrl();
                if (current != null && current.startsWith("about:neterror")) {
                    attempts++;
                    sleep(500);
                    continue;
                }
                return;
            } catch (WebDriverException e) {
                attempts++;
                sleep(500);
            }
        }
        throw new RuntimeException("DemoQA not reachable after retries");
    }


    protected void sleep(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }


}
