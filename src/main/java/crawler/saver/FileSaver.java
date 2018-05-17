package crawler.saver;

import crawler.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static crawler.Constants.Values.*;

public class FileSaver {

    private static String outputPath = OUTPUT_PATH.get();
    private static String urlPartToReplace = "https://www.linkedin.com/in/";


    public static void save(String html, String url) throws IOException {

        String additionPath = getAdditionalPath(url);
        additionPath = additionPath.replace("/", "_");
        createDir();
        File file = new File(outputPath + additionPath + ".html");
        if (!file.exists()) {
            file.createNewFile();
        }
        try (OutputStream outputStream = new FileOutputStream(file)) {
          html.chars().forEach(c -> {
              try {
                  outputStream.write(c);
              } catch (Exception e){
                  e.printStackTrace();
              }
          });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getAdditionalPath(String url) {
        return url.replace(urlPartToReplace, "");
    }

    private static void createDir() {
        File file = new File(outputPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
