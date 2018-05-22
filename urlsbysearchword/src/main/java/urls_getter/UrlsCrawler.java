package urls_getter;

import crawler.util.Account;
import crawler.walk.driver.ChromeDriverInitializer;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static crawler.accounts.Loginer.logIn;
import static java.lang.Thread.sleep;
import static urls_getter.util.ByWordSearchConstants.Values.EMAIL;
import static urls_getter.util.ByWordSearchConstants.Values.PASS;

public class UrlsCrawler {

    private static List<String> links = new ArrayList<>();
    private static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        driver = new ChromeDriverInitializer().initDriver();
        Account account = new Account(EMAIL.get(), PASS.get());
        driver.get("http://linkedin.com");
        logIn(account, driver);

        setFilters();

        saveAllLinks();

        while (driver.findElement(By.className("next")) != null){
            driver.findElement(By.className("next")).click();
            sleep(500);
            saveAllLinks();
        }
    }

    private static void setFilters() throws InterruptedException {
        WebElement searchField = driver.findElement(By.tagName("input"));
        searchField.sendKeys("I'm here");
        sleep(1000);
        searchField.sendKeys(Keys.ENTER);
        driver.findElement(By.className("search-s-facet__form relative")).click();
        List<WebElement> allCheckBoxes = driver.findElements(By.xpath("//*li[2]/input"));
        WebElement united_kingdom = allCheckBoxes.stream()
                .filter(box -> box.findElement(By.xpath("//*/label/p/span")).getText().equals("United Kingdom"))
                .collect(Collectors.toList()).get(0);
        united_kingdom.click();
        driver.findElement(By.className("button-primary-medium facet-collection-list__apply-button")).click();
        setFirstNameFirstLetter("a");
    }

    private static void setFirstNameFirstLetter(String firstLetter) {
        driver.findElement(By.className("search-filters-bar__all-filters button-tertiary-medium-muted mr3")).click();
        driver.findElement(By.id("search-advanced-firstName")).sendKeys(firstLetter+ "*");
    }

    private static void saveAllLinks() {
        List<WebElement> allLinks = driver.findElements(By.className("search-result__result-link ember-view"));
        allLinks.forEach(UrlsCrawler::saveLink);
    }

    private static void saveLink(WebElement element) {
        String link = element.getAttribute("href");
        links.add(link);
    }
}
