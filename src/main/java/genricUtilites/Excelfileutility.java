package genricUtilites;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfileutility {

    /**
     * Reads test data from an Excel sheet and returns it as Object[][] suitable for TestNG DataProvider.
     * Tries to read ./src/test/resources/EmployeeData.xlsx and if not present returns a small default dataset.
     */
    public Object[][] getData(String sheetName) {
        String path = "./src/test/resources/EmployeeData.xlsx";
        File f = new File(path);
        if (!f.exists()) {
            // Fallback: return some default hard-coded data so tests can still run
            return new Object[][] { { "Ankit", "Kumar", "869551" }, { "Abhijeet", "Kumar", "8996514" },
                    { "Piku", "chiku", "1335619" } };
        }

        try (FileInputStream fis = new FileInputStream(f); Workbook wb = WorkbookFactory.create(fis)) {
            Sheet sh = wb.getSheet(sheetName);
            if (sh == null) {
                return new Object[0][0];
            }

            int lastRow = sh.getLastRowNum();
            // assume first row is header
            Row header = sh.getRow(0);
            int cols = header == null ? 0 : header.getLastCellNum();

            int dataRows = lastRow; // excluding header
            if (dataRows <= 0 || cols <= 0) {
                return new Object[0][0];
            }

            Object[][] data = new Object[dataRows][cols];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i <= lastRow; i++) {
                Row r = sh.getRow(i);
                if (r == null) {
                    for (int c = 0; c < cols; c++)
                        data[i - 1][c] = "";
                    continue;
                }
                for (int c = 0; c < cols; c++) {
                    data[i - 1][c] = formatter.formatCellValue(r.getCell(c));
                }
            }
            return data;
        } catch (Throwable e) {
            e.printStackTrace();
            // on error, return default dataset
            return new Object[][] { { "Ankit", "Kumar", "869551" }, { "Abhijeet", "Kumar", "8996514" },
                    { "Piku", "chiku", "1335619" } };
        }
    }
}
