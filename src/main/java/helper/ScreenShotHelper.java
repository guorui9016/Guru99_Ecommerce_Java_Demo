package helper;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotHelper {
    private static String path;

    public static String getScreenShot(WebDriver driver, String screenshotName) throws Exception {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //save the screenshot
        path = System.getProperty("user.dir") + "/testReport/FailedTestsScreenshots/"+screenshotName+dateName+".png";
        File finalDestination = new File(path);
        FileUtils.copyFile(source, finalDestination);
        return path;
    }
}
