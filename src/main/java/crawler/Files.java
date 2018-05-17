package crawler;

import static crawler.Constants.Values.*;
import crawler.accounts.AccountsGetter;
import crawler.urls.ProcessedAmountSaver;
import crawler.urls.URLsGetter;
import crawler.util.Account;
import crawler.walk.SeleniumWalker;
import crawler.walk.driver.ChromeDriverInitializer;

import java.util.List;

public class Files {


       private static String inputFilePath = INPUT_PATH.get();
       private static String accountsFilePath = ACCOUNTS_FILE_PATH.get();

    public static void main(String[] args) throws Exception {

            int count = 0;
        try {
            List<Account> accounts = AccountsGetter.get(accountsFilePath);
            List<String> urls = URLsGetter.get(inputFilePath);

            for (Account account : accounts
                    ) {
                new SeleniumWalker(new ChromeDriverInitializer()).walkRound(account, urls.subList(count, count + 80));
                count += 80;
            }
        }catch (WalkerException e){
            int proceeded = e.getAmount();
            ProcessedAmountSaver.save(inputFilePath, count + proceeded);
            throw new  RuntimeException(e);
        } catch (Exception e){
            ProcessedAmountSaver.save(inputFilePath, count);
            throw new  RuntimeException(e);
        }
        ProcessedAmountSaver.save(inputFilePath, count);

    }

}

