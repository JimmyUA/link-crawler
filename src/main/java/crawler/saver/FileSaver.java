package crawler.saver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileSaver {

    private static String outputPath = "D:/link-crawler/output/";

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
        return url.replace("https://www.linkedin.com/in/", "");
    }

    private static void createDir() {
        File file = new File(outputPath);
        if (!file.exists()) {
            file.mkdir();
        }
    }
}
