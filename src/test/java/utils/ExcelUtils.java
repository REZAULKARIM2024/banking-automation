package utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;

public class ExcelUtils {

    public static Object[][] getTestData(String path, String sheetName) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = WorkbookFactory.create(fis);
        Sheet sheet = wb.getSheet(sheetName);

        int rows = sheet.getPhysicalNumberOfRows();
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] data = new Object[rows - 1][cols];

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i - 1][j] = sheet.getRow(i).getCell(j).toString();
            }
        }
        return data;
    }
}
