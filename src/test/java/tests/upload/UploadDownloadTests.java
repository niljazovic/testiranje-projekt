package tests.upload;

import listeners.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.upload.UploadDownloadPage;
import tests.base.BaseTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UploadDownloadTests extends BaseTest {

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void uploadFile_shouldShowUploadedFilePath() throws IOException {
        Path tempFile = Files.createTempFile("demoqa-upload-", ".txt");
        Files.writeString(tempFile, "hello demoqa");

        UploadDownloadPage page = new UploadDownloadPage(driver, wait)
                .open(config.getBaseUrl())
                .uploadFile(tempFile.toAbsolutePath().toString());

        Assert.assertTrue(page.getUploadedPathText().contains(tempFile.getFileName().toString()));
    }

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void clickDownload_shouldNotCrashPage() {
        UploadDownloadPage page = new UploadDownloadPage(driver, wait).open(config.getBaseUrl());
        page.clickDownload();

        Assert.assertTrue(driver.getCurrentUrl().contains("/upload-download"));
    }
}
