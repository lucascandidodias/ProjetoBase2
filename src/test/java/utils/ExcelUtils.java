package utils;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.List;
import java.util.function.Consumer;

public class ExcelUtils {

    public static void insereItem(String arquivo, List<String> produto, String qtd) throws IOException {
        FileInputStream excel = null;
        XSSFWorkbook workbook = null;

        try {
            File arquivoExcel = new File(System.getProperty("user.dir") + "/src/main/resources/" + arquivo + ".xlsx");
            excel = new FileInputStream(arquivoExcel);
            workbook = new XSSFWorkbook(excel);
            XSSFSheet sheet = workbook.getSheetAt(0);
            XSSFCellStyle produtoCellStyle = sheet.getRow(1).getCell(0).getCellStyle();
            XSSFCellStyle qtdCellStyle = sheet.getRow(1).getCell(1).getCellStyle();
            for (int i = 0; i < produto.size(); i++) {
                XSSFRow row = sheet.createRow(i + 1);
                XSSFCell produtoCell = row.createCell(0);
                XSSFCell qtdCell = row.createCell(1);
                produtoCell.setCellValue(produto.get(i));
                qtdCell.setCellValue(qtd);
                produtoCell.setCellStyle(produtoCellStyle);
                qtdCell.setCellStyle(qtdCellStyle);
            }
            FileOutputStream fos = new FileOutputStream(arquivoExcel);
            workbook.write(fos);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
           workbook.close();
           excel.close();
        }

    }
}
