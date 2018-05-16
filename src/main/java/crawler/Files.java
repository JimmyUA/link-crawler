package crawler;

import crawler.urls.ProcessedAmountSaver;
import crawler.urls.URLsGetter;
import crawler.util.Account;
import crawler.walk.SeleniumWalker;
import crawler.walk.driver.ChromeDriverInitializer;

import java.util.List;

public class Files {


       private static String filePath = "D:/link-crawler/input/sample_urls.xlsx";

    public static void main(String[] args) throws Exception {

            int count = 0;
        try {
            List<String> urls = URLsGetter.get(filePath);
            Account account = new Account("john.cramer.voip@gmail.com", "Vika_Ruban");

            new SeleniumWalker(new ChromeDriverInitializer()).walkRound(account, urls.subList(0, 80));
            count++;
        } catch (Exception e){
            ProcessedAmountSaver.save(filePath, count);
            throw new  RuntimeException(e);
        }
        ProcessedAmountSaver.save(filePath, count);

    }

}

