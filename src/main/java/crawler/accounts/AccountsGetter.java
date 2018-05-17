package crawler.accounts;

import crawler.util.Account;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AccountsGetter {
    public static List<Account> get(String filePath){
        List<Account> result = new ArrayList<>();
        File source = new File(filePath);
        try(FileInputStream sourceSteam = new FileInputStream(source)){
            Workbook workbook = new XSSFWorkbook(sourceSteam);
            Sheet sheet = workbook.getSheetAt(0);


            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                String email = currentRow.getCell(0).getStringCellValue();
                String pass = currentRow.getCell(1).getStringCellValue();
                result.add(new Account(email, pass));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(result.size());
        return result;
    }
}
