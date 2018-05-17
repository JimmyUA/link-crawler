package crawler.walk.driver;

import crawler.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static crawler.Constants.Values.CHROME_DRIVER_PATH;

public class ChromeDriverInitializer implements DriverInitializer {

    private String driverPath = CHROME_DRIVER_PATH.get();


    public WebDriver initDriver() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
