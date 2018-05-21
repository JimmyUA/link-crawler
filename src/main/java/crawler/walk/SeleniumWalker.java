package crawler.walk;

import crawler.WalkerException;
import crawler.saver.FileSaver;
import crawler.util.Account;
import crawler.walk.driver.DriverInitializer;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

public class SeleniumWalker {
    private DriverInitializer driverInitializer;
    private WebDriver driver;
    private int count;

    public SeleniumWalker(DriverInitializer driverInitializer) {
        this.driverInitializer = driverInitializer;
    }

    public void walkRound(Account account, List<String> urlsPortion) throws InterruptedException, IOException {
        try {
            driver = driverInitializer.initDriver();
            String firtsUrl = urlsPortion.get(0);
            driver.get(firtsUrl);
            sleep(1000);
            logIn(account);

            expandSections();

            sleep(1000);
            String html = driver.getPageSource();
            FileSaver.save(html, firtsUrl);
            String url;
            for (int i = 1; i < urlsPortion.size(); i++) {
                url = urlsPortion.get(i);
                driver.get(url);
                expandSections();
                sleep(1000);
                String htmll = driver.getPageSource();
                FileSaver.save(htmll, url);
                count++;
            }

        } catch (Exception e) {
            throw new WalkerException(e, count);
        }
    }

    private void expandSections() throws InterruptedException {
        sleep(1000);

        expandField();

        sleep(1000);

        expandFieldByButton();
        sleep(1000);
        scrollDown(800);
        sleep(1000);
        expandField();
    }

    private void expandFieldByButton() {
        List<WebElement> allButtons = driver.findElements(By.tagName("button"));
        List<WebElement> showMores = allButtons.stream().filter(button -> {
            String text = "";
            try {
                text = button.getText();
            }catch (Exception e){

            }
            return text.equals("Show more");
        }).collect(Collectors.toList());

        showMores.forEach(showMore -> {
            Actions actions = new Actions(driver);
            actions.moveToElement(showMore);
            actions.perform();
            try {
                showMore.click();
            } catch (Exception e){

            }
        });
    }

    private void scrollDown(int length) {
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0," + length + ")", "");
    }

    private void expandField() throws InterruptedException {
        List<WebElement> allSpans = driver.findElements(By.tagName("span"));
        List<WebElement> showMores = allSpans.stream().filter(span -> {
            String text = "";
            try {
                text = span.getText();
            }catch (Exception e){

            }
            return text.equals("Show more");
        }).collect(Collectors.toList());

        sleep(1000);

        showMores.forEach(showMore -> {
            Actions actions = new Actions(driver);
            actions.moveToElement(showMore);
            actions.perform();
            try {
                showMore.findElement(By.xpath("..")).click();
            } catch (Exception e){

            }
        });
    }

    private void tryToClose() {
        try {
            driver.findElement(By.xpath("//*[starts-with(@id,'ember')]/artdeco-modal/button/li-icon/svg")).click();
        } catch (Exception e){

        }
    }

    private void logIn(Account account) throws InterruptedException {
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
