package crawler.walk.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverInitializer implements DriverInitializer {

    public  WebDriver initDriver(){
        System.setProperty("webdriver.chrome.driver", "D:/link-crawler/selenium/winda/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
