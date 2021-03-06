package urls_getter.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ByWordSearchConstants {

    private static String filePath = "D:/link-crawler/searchByWord/search.txt";
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Values {

        EMAIL("account_email"), PASS("acount_pass"),
        CHROME_DRIVER_PATH("chrome_driver_path");

        private String value;

        Values(String key) {
            value = properties.getProperty(key);
        }

        public String get() {
            return value;
        }
    }
}
