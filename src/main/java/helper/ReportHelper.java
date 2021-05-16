package helper;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportHelper {

    private static ExtentReports extentReports = new ExtentReports();

    public static ExtentReports getExtentReport(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss"));
        String path = System.getProperty("user.dir") + "/testReport/testReport_"+ time + ".html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Guru99 Ecommerce Automation Test Demo Results");
        reporter.config().setDocumentTitle("Guru99 Ecommerce Automation Test Demo Results");
        extentReports.setSystemInfo("Report", "Rui Guo");
        extentReports.setSystemInfo("Report date", time);
        extentReports.attachReporter(reporter);
        return extentReports;
    }

}
