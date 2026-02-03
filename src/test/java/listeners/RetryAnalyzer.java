package listeners;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private int retryCount = 0;
    private static final int MAX_RETRY = 1;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount >= MAX_RETRY) return false;

        Throwable t = result.getThrowable();
        if (t == null) return false;

        if (!isFlaky(t)) return false;

        Object instance = result.getInstance();
        WebDriver driver = extractDriver(instance);
        if (driver != null) {
            try {
                driver.navigate().refresh();
            } catch (Exception ignored) {}
        }

        retryCount++;
        return true;
    }

    private boolean isFlaky(Throwable t) {
        if (t instanceof TimeoutException) return true;

        String msg = String.valueOf(t.getMessage()).toLowerCase();
        if (msg.contains("about:neterror")) return true;
        if (msg.contains("connectionfailure")) return true;
        if (msg.contains("elementclickintercepted")) return true;
        if (msg.contains("stale element reference")) return true;

        if (t instanceof WebDriverException && (
                msg.contains("reached error page") ||
                        msg.contains("disconnected") ||
                        msg.contains("unable to connect")
        )) return true;

        return t.getCause() != null && isFlaky(t.getCause());
    }

    private WebDriver extractDriver(Object testInstance) {
        try {
            var f = testInstance.getClass().getSuperclass().getDeclaredField("driver");
            f.setAccessible(true);
            Object v = f.get(testInstance);
            return (v instanceof WebDriver) ? (WebDriver) v : null;
        } catch (Exception e) {
            return null;
        }
    }
}
