package crawler.urls;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class URLsGetter {

    public static List<String> get(String filePath){
        List<String> result = new ArrayList<>();
        File source = new File(filePath);
        try(FileInputStream sourceSteam = new FileInputStream(source)){
            Workbook workbook = new XSSFWorkbook(sourceSteam);
            Sheet sheet = workbook.getSheetAt(0);


            Iterator<Row> iterator = sheet.iterator();
            int startFrom = getStartFromValue(iterator);
            skipUrls(iterator, startFrom);
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                getUrlFromCell(result, currentRow);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result.size());
        return result;
    }

    private static void skipUrls(Iterator<Row> iterator, int startFrom) {
        for (int i = 0; i < startFrom; i++) {
            iterator.next();
        }
    }

    private static int getStartFromValue(Iterator<Row> iterator) {
        Cell firstCell = iterator.next().getCell(0);
        return Double.valueOf(firstCell.getNumericCellValue()).intValue();
    }

    private static void getUrlFromCell(List<String> result, Row currentRow) {
        result.add(currentRow.cellIterator().next().getStringCellValue());
    }

    public static void main(String[] args) {
        get("D:/link-crawler/input/sample_urls.xlsx");
        ProcessedAmountSaver.save("D:/link-crawler/input/sample_urls.xlsx", 600);
    }
}
