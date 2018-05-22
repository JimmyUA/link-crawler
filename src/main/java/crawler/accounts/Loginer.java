package crawler.accounts;

import crawler.util.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class Loginer {

    public static void logIn(Account account, WebDriver driver) throws InterruptedException {
        sleep(1000);
        try {
            driver.findElement(By.xpath("//*[@id=\"join-form\"]/p[3]/a")).click();
        } catch (Exception e) {
            e.printStackTrace();
        }

        sleep(1000);
        WebElement emailField = driver.findElement(By.id("login-email"));
        emailField.sendKeys(account.getEmail());
        driver.findElement(By.id("login-password")).sendKeys(account.getPass());
        driver.findElement(By.id("login-submit")).click();
    }
}
