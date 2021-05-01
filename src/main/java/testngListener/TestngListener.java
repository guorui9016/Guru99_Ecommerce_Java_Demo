package testngListener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import helper.ReportHelper;
import helper.ScreenShotHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngListener implements ITestListener {
    private Logger logger = LogManager.getLogger(TestngListener.class);
    private ExtentReports reports = ReportHelper.getExtentReport();
    private ExtentTest extentTest;
    private ThreadLocal<ExtentTest> extentTestPool = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Start the test case: " + result.getMethod().getMethodName());
        extentTest = reports.createTest(result.getMethod().getMethodName());
        extentTestPool.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " result is: Passed" );
        extentTestPool.get().log(Status.PASS, "Pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " result is: Failed" );
        extentTestPool.get().log(Status.FAIL, result.getThrowable());

        try{
            WebDriver driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
            String screenShotPath = ScreenShotHelper.getScreenShot(driver, result.getMethod().getMethodName());
            extentTestPool.get().addScreenCaptureFromPath(screenShotPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " result is: Skipped" );
        extentTest = reports.createTest(result.getMethod().getMethodName());
        extentTest.log(Status.SKIP, "Skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " result is: Timeout" );
        extentTestPool.get().log(Status.INFO, "Timeout");
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }

}
