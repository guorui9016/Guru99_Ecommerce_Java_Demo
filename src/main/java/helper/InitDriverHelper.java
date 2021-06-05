package helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    private void initSafariDriver(boolean headless) {

    }

    private void initEdgeDriver(boolean headless) {

    }

    private void initGekoDriver(boolean headless) {
        if (osName.contains("Windows")) {
            URL driverPath = this.getClass().getResource("/driver/windows/geckodriver.exe");
            System.setProperty("webdriver.gecko.driver", driverPath.getPath());
            logger.info("System is: Windows");
        } else if (osName.contains("Mac")) {
            URL driverPath = this.getClass().getResource("/driver/macos/geckodriver");
            System.setProperty("webdriver.gecko.driver", driverPath.getPath());
            logger.info("System is: Mac OS");
        }else {
            logger.error("Do not support the system: " + osName);
            throw new IllegalStateException("Do not support the system" + osName);
        }
        //setup option
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //close the info bar
        firefoxOptions.setHeadless(headless);
        driver = new FirefoxDriver(firefoxOptions);
        logger.info("Create a chromedriver");
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
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(chromeOptions);
        logger.info("Create a chromedriver");
    }
}
