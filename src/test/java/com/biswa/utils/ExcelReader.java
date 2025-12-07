package com.biswa.utils;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {

    public static List<String[]> readSheet(String path, String sheetName) {
        List<String[]> rows = new ArrayList<>();
        try(FileInputStream fis = new FileInputStream(path);
            Workbook wb = new XSSFWorkbook(fis)){
            Sheet sheet = wb.getSheet(sheetName);
            if(sheet == null) return rows;
            for(int i=1;i<=sheet.getLastRowNum();i++){
                Row r = sheet.getRow(i);
                if(r==null) continue;
                String a = r.getCell(0)==null?"":r.getCell(0).toString();
                String b = r.getCell(1)==null?"":r.getCell(1).toString();
                rows.add(new String[]{a,b});
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rows;
    }
}
