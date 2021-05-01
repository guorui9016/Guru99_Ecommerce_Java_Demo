package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;

public class InitDriverHelper {
    private final Logger logger = LogManager.getLogger(InitDriverHelper.class);
    private enum Browser {CHROME, FIREFOX, EDGE, SAFARI}
    private Browser browser = Browser.CHROME;       //default browser
    private final String osName = System.getProperty("os.name");
    private WebDriver driver;

    public WebDriver createDriver(String browser, boolean headless){
        try {
            this.browser = Browser.valueOf(browser);
        }catch (Exception e){
            logger.warn(e);
            logger.warn("Using default browser: Chrome");
        }

        switch (this.browser){
            case FIREFOX:
                initGekoDriver(headless);
                break;
            case EDGE:
                initEdgeDriver(headless);
                break;
            case SAFARI:
                initSafariDriver(headless);
                break;
            case CHROME:
                initChromeDriver(headless);
                break;
        }
        return driver;
    }

    private void initSafariDriver(boolean headless) {

    }

    private void initEdgeDriver(boolean headless) {

    }

    private void initGekoDriver(boolean headless) {

    }

    private void initChromeDriver(boolean headless) {
        if (osName.contains("Windows")) {
            URL driverPath = this.getClass().getResource("/driver/windows/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", driverPath.getPath());
            logger.info("System is: Windows");
        } else if (osName.contains("Mac")) {

            URL driverPath = this.getClass().getResource("/driver/macos/chromedriver");
            System.setProperty("webdriver.chrome.driver", driverPath.getPath());
            logger.info("System is: Mac OS");
        }else {
            logger.error("Do not support the system: " + osName);
            throw new IllegalStateException("Do not support the system" + osName);
        }
        //setup option
        ChromeOptions chromeOptions = new ChromeOptions();
        //close the info bar
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.setHeadless(headless);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        logger.info("Create a chromedriver");
    }
}
