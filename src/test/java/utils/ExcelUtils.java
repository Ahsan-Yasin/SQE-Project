package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static File createSampleLoginExcel(String path) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("logins");

        String[][] data = {
            {"username", "password"},
            {"standard_user", "secret_sauce"},
            {"locked_out_user", "secret_sauce"},
            {"wrong_user", "wrong_pass"},
            {"", ""}
        };

        for (int r = 0; r < data.length; r++) {
            Row row = sheet.createRow(r);
            for (int c = 0; c < data[r].length; c++) {
                Cell cell = row.createCell(c);
                cell.setCellValue(data[r][c]);
            }
        }

        File file = new File(path);
        file.getParentFile().mkdirs();
        try (FileOutputStream out = new FileOutputStream(file)) {
            workbook.write(out);
        }
        workbook.close();
        return file;
    }

    public static List<String[]> readLoginData(File file) throws Exception {
        List<String[]> rows = new ArrayList<>();
        String name = file.getName().toLowerCase();
        // support CSV fallback so committed test-data can be plain-text and visible to TAs
        if (name.endsWith(".csv")) {
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.FileReader(file))) {
                String line;
                // skip header
                boolean headerSkipped = false;
                while ((line = br.readLine()) != null) {
                    if (!headerSkipped) { headerSkipped = true; continue; }
                    if (line.trim().isEmpty()) continue;
                    String[] parts = line.split(",", -1);
                    String u = parts.length > 0 ? parts[0].trim() : "";
                    String p = parts.length > 1 ? parts[1].trim() : "";
                    rows.add(new String[]{u, p});
                }
            }
            return rows;
        }
        try (FileInputStream fis = new FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                String u = row.getCell(0).getStringCellValue();
                String p = row.getCell(1).getStringCellValue();
                rows.add(new String[]{u, p});
            }
        }
        return rows;
    }
}
