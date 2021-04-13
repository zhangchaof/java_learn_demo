package com.zhang.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AnalysisExcel
 * @Description: TODO
 * @Author clark
 * @Date 2021/1/28 10:25
 **/
public class AnalysisPOIExcel {
    public static void main(String[] args) {
        //String filePath2 = "C:\\Users\\张超凡\\Desktop\\test.xlsx";
      //  updateTwo(filePath2);
        String filePath = "C:\\Users\\张超凡\\Desktop\\stander_desp.xlsx";
        updateOne(filePath);

    }

    public static void updateTwo(String filePath) {
        String sql = "update standard_config set standard_code='%s',content = '%s',update_time = now() where standard_code='%s';";
        int whereCol = 1;
        int updateCol = 2;
        int updateCol2 = 3;
        compose2Sql(filePath, sql, whereCol, updateCol, updateCol2);
    }

    private static void compose2Sql(String filePath, String sql, int whereCol, int updateCol, int updateCol2) {
        Workbook wb;
        Sheet sheet;
        Row row;
        String cellData;

        wb = readExcel(filePath);
        List<WhereColumn> allList = new ArrayList<>();
        if (wb != null) {
            //用来存放表中数据
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    WhereColumn whereColumn = new WhereColumn();
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        if (j == 0) {
                            whereColumn.setWhereCond(cellData);
                        }
                        if (j == 1) {
                            whereColumn.setUpdateOne(cellData);
                        }
                        if (j == 2) {
                            whereColumn.setUpdateTwo(cellData);
                        }
                    }
                    allList.add(whereColumn);
                } else {
                    break;
                }
            }
            allList.forEach(vo -> {
                String format = String.format(sql, vo.getUpdateOne(), vo.getUpdateTwo().replace("\n", "<br/>"), vo.getWhereCond().replace(" ", ""));
                System.out.println(format);
            });
        }
    }


    /**
     * <dependency>
     * <groupId>org.apache.poi</groupId>
     * <artifactId>poi-ooxml</artifactId>
     * <version>3.17</version>
     * </dependency>
     * <p>
     * <p>
     * ps：特指拼接检查项导入sql，
     * update check_item_config set description='%s' where item_name= '%s';
     * 其他自行修改
     * <p>
     * 拼接检查项导入sql
     *
     * @param filePath  文件地址
     * @param sql       需要拼接sql
     * @param whereCol  where条件
     * @param updateCol update 列
     */
    public static void updateOne(String filePath) {
        String sql = "update check_item_config set standard_description='%s',update_time = now() where item_name= '%s';";
        int whereCol = 0;
        int updateCol = 2;
        composeSql(filePath, sql, whereCol, updateCol);
    }

    private static void composeSql(String filePath, String sql, int whereCol, int updateCol) {
        Workbook wb;
        Sheet sheet;
        Row row;
        String cellData;

        wb = readExcel(filePath);
        List<WhereColumn> allList = new ArrayList<>();
        if (wb != null) {
            //用来存放表中数据
            //获取第一个sheet
            sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            int colnum = row.getPhysicalNumberOfCells();
            for (int i = 1; i < rownum; i++) {
                List<String> list = new ArrayList<>();
                row = sheet.getRow(i);
                WhereColumn whereColumn = new WhereColumn();
                if (row != null) {
                    for (int j = 0; j < colnum; j++) {
                        cellData = (String) getCellFormatValue(row.getCell(j));
                        if(j == whereCol) {
                            whereColumn.setWhereCond(cellData);
                        }
                        if(j == updateCol) {
                            whereColumn.setUpdateOne(cellData);
                        }
                    }
                } else {
                    break;
                }
                allList.add(whereColumn);
            }
            allList.forEach(vo -> {
                String format = String.format(sql, vo.getUpdateOne().replace("\n", "<br/>"), vo.getWhereCond().replace(" ", ""));
                System.out.println(format);
            });
        }
    }


    //读取excel
    public static Workbook readExcel(String filePath) {
        Workbook wb = null;
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return wb = new XSSFWorkbook(is);
            } else {
                return wb = null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if (cell != null) {
            //判断cell类型
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC: {
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }
                case Cell.CELL_TYPE_FORMULA: {
                    //判断cell是否为日期格式
                    if (DateUtil.isCellDateFormatted(cell)) {
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    } else {
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue;
    }
}
