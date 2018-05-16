package crawler.walk;

import crawler.saver.FileSaver;
import crawler.util.Account;
import crawler.walk.driver.ChromeDriverInitializer;
import crawler.walk.driver.DriverInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

public class SeleniumWalker {
    private DriverInitializer driverInitializer;
    private WebDriver driver;

    public SeleniumWalker(DriverInitializer driverInitializer) {
        this.driverInitializer = driverInitializer;
    }

    public void walkRound(Account account, List<String> urlsPortion) throws InterruptedException, IOException {
        driver = driverInitializer.initDriver();
        String firtsUrl = urlsPortion.get(0);
        driver.get(firtsUrl);
        sleep(1000);
        logIn(account);

        sleep(1000);

        String html = driver.getPageSource();
        FileSaver.save(html, firtsUrl);

        urlsPortion.stream().skip(1).forEach(url -> {
            driver.get(url);
            String htmll = driver.getPageSource();
            try {
                FileSaver.save(htmll, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void logIn(Account account) throws InterruptedException {
        try{
            driver.findElement(By.xpath("//*[@id=\"join-form\"]/p[3]/a")).click();
        } catch (Exception e){
            e.printStackTrace();
        }

        sleep(1000);
        WebElement emailField = driver.findElement(By.id("login-email"));
        emailField.sendKeys(account.getEmail());
        driver.findElement(By.id("login-password")).sendKeys(account.getPass());
        driver.findElement(By.id("login-submit")).click();
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        List<String> list = Arrays.asList("https://www.linkedin.com/in/greg-guilliams-973399b");
        new SeleniumWalker(new ChromeDriverInitializer())
                .walkRound(new Account("john.cramer.voip@gmail.com", "VIka_Ruban"), list);
    }
}
