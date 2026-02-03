package pages.upload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.base.BasePage;
import utils.WaitUtil;

public class UploadDownloadPage extends BasePage {

    private final By uploadInput = By.id("uploadFile");
    private final By uploadedPath = By.id("uploadedFilePath");
    private final By downloadButton = By.id("downloadButton");

    public UploadDownloadPage(WebDriver driver, WaitUtil wait) {
        super(driver, wait);
    }

    public UploadDownloadPage open(String baseUrl) {
        safeGet(baseUrl + "/upload-download");
        wait.urlContains("/upload-download");
        return this;
    }

    public UploadDownloadPage uploadFile(String absolutePath) {
        scrollIntoView(uploadInput);
        wait.visible(uploadInput).sendKeys(absolutePath);
        return this;
    }

    public String getUploadedPathText() {
        return text(uploadedPath);
    }

    public void clickDownload() {
        scrollIntoView(downloadButton);
        click(downloadButton);
    }
}
