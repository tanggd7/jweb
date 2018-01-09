package net.core.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel工具类
 *
 * @author 汤国栋
 * @version 1.0
 * @date 2016年12月13日 下午4:20:19
 */
public final class ExcelUtil {

    /**
     * 获取.xls或.xlsx的poi对象
     *
     * @param inputStream
     * @param suffix
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午4:11:44
     */
    public static Workbook getWorkbook(InputStream inputStream, String suffix) {
        try {
            Workbook workbook = null;
            if ("xls".equals(suffix)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if ("xlsx".equals(suffix)) {
                workbook = new XSSFWorkbook(inputStream);
            }
            return workbook;
        } catch (IOException e) {
            throw new RuntimeException("获取Excel对象出错", e);
        }
    }

    /**
     * 获取工作簿对象
     *
     * @param workbook
     * @param sheetNumber
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午4:13:38
     */
    public static Sheet getSheet(Workbook workbook, int sheetNumber) {
        try {
            return workbook.getSheetAt(sheetNumber);
        } catch (Exception e) {
            throw new RuntimeException("获取工作簿对象出错", e);
        }
    }

    /**
     * 获取行对象
     *
     * @param sheet
     * @param rowNumber
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午4:14:01
     */
    public static Row getRow(Sheet sheet, int rowNumber) {
        try {
            return sheet.getRow(rowNumber);
        } catch (Exception e) {
            throw new RuntimeException("获取表格行对象出错", e);
        }
    }

    /**
     * 获取单元格对象
     *
     * @param row
     * @param cellNumber
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午4:14:12
     */
    public static Cell getCell(Row row, int cellNumber) {
        try {
            return row.getCell(cellNumber);
        } catch (Exception e) {
            throw new RuntimeException("获取单元格对象出错", e);
        }
    }

    /**
     * 获取单元格格式和数据
     *
     * @param cell
     * @return
     * @author 汤国栋
     * @date 2016年12月26日 下午4:14:25
     */
    public static String getCellValue(Cell cell) {
        try {
            String cellValue = "";
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                    } else {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                        cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                    }
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    cellValue = String.valueOf(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_BLANK:
                    cellValue = cell.getStringCellValue();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    // cellValue = cell.getCellFormula(); // 得到对应单元格的公式
                    cell.setCellType(Cell.CELL_TYPE_STRING); // 得到对应单元格的字符串
                    cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    cellValue = "错误";
                    break;
                default:
                    cellValue = "#";
                    break;
            }
            return cellValue;
        } catch (Exception e) {
            throw new RuntimeException("获取单元格数据出错", e);
        }
    }

    /**
     * 获取单元格数据列表
     *
     * @param inputStream
     * @param suffix
     * @param ExcelColNumber 从第一行开始，ExcelColNumber表示第几列，如果为0，则选择所有列。
     * @return
     */
    public static List<List<String>> getExcelValue(InputStream inputStream, String suffix, int ExcelColNumber) {
        try {

            Workbook workbook = ExcelUtil.getWorkbook(inputStream, suffix);
            Sheet sheet = ExcelUtil.getSheet(workbook, 1);
            int rowNumber = sheet.getLastRowNum();
            List<List<String>> sheetValue = new ArrayList<List<String>>();
            for (int i = 1; i <= rowNumber; i++) {
                Row row = ExcelUtil.getRow(sheet, rowNumber);
                List<String> cellValue = new ArrayList<String>();
                int colNumber = ExcelColNumber == 0 ? row.getLastCellNum() : ExcelColNumber - 1;
                for (int j = 0; j <= colNumber; j++) {
                    cellValue.add(ExcelUtil.getCellValue(ExcelUtil.getCell(row, j)));
                }
                sheetValue.add(cellValue);
            }
            return sheetValue;
        } catch (Exception e) {
            throw new RuntimeException("获取Excel数据出错", e);
        }
    }

}
