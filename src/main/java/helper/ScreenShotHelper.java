package helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScreenShotHelper {

    public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //save the screenshot
        String relativePath = "FailedTestsScreenshots/"+screenshotName + "-" + time+".png";
        String path = System.getProperty("user.dir") + "/testReport/FailedTestsScreenshots/"+screenshotName + "-" + time+".png";
        File finalDestination = new File(path);
        FileUtils.copyFile(source, finalDestination);
        return relativePath;
    }
}
