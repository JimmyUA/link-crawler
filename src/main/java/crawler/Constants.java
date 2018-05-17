package crawler;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants {

    private static String filePath = "/home/jimmy/link-crawler.properties";
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Values {

        OUTPUT_PATH("output_path"), INPUT_PATH("input_path"), ACCOUNTS_FILE_PATH("accounts_file_path"),
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

