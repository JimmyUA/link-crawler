package crawler.urls;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class ProcessedAmountSaver {
    public static void save(String filePath, int amount){
        File file = new File(filePath);
        try(FileInputStream sourceSteam = new FileInputStream(file)){
            Workbook workbook = new XSSFWorkbook(sourceSteam);
            Sheet sheet = workbook.getSheetAt(0);

            sheet.getRow(0).getCell(0).setCellValue(amount);
            saveWorkBook(workbook,file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void saveWorkBook(Workbook workbook, File file) {
        try(FileOutputStream outputStream = new FileOutputStream(file)) {
            workbook.write(outputStream);
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
