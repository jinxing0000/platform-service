package com.bettem.common.excel.util;

import org.apache.commons.httpclient.util.DateUtil;
//import com.alibaba.excel.ExcelWriter;
//import com.alibaba.excel.context.WriteContext;
//import com.alibaba.excel.metadata.Sheet;
//import com.alibaba.excel.metadata.TableStyle;
//import com.alibaba.excel.support.ExcelTypeEnum;
//import com.alibaba.excel.write.ExcelBuilderImpl;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @ClassName EasyExcelUtil
 * @Decription: EasyExcel补全工具
 * @Date: 2019/5/517:20
 * @Author: 高敏
 * @Version: 1.0
 */
public class EasyExcelUtil {
//    private static final Logger logger = LoggerFactory.getLogger(EasyExcelUtil.class);
//    private static Map<Workbook, Map<String, CellStyle>> cellStyles = new HashMap<>();
//
//
//
//
//    /**
//     * **获取workbook**
//     * 因为EasyExcel这个库设计的原因
//     * 只能使用反射获取workbook
//     *
//     * @param writer
//     * @return
//     */
//    public static Workbook getWorkbook(ExcelWriter writer) {
//        Workbook workbook = null;
//        try {
//            Class<?> clazz1 = Class.forName("com.alibaba.excel.ExcelWriter");
//            Field[] fs = clazz1.getDeclaredFields();
//            for (Field field : fs) {
//                // 要设置属性可达，不然会抛出IllegalAccessException异常
//                field.setAccessible(true);
//                if ("excelBuilder".equals(field.getName())) {
//                    ExcelBuilderImpl excelBuilder = (ExcelBuilderImpl) field.get(writer);
//                    Class<?> clazz2 = Class.forName("com.alibaba.excel.write.ExcelBuilderImpl");
//                    Field[] fs2 = clazz2.getDeclaredFields();
//                    for (Field field2 : fs2) {
//                        field2.setAccessible(true);
//                        if ("context".equals(field2.getName())) {
//                            WriteContext context = (WriteContext) field2.get(excelBuilder);
//                            workbook = context.getWorkbook();
//                        }
//                    }
//                }
//            }
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }
//
//    /**
//     * @Author 高敏
//     * @Description 导出excel工具类
//     *  params包含该方法所需的参数:
//     *      必填:sheetName(工作表名称) sheetNo(工作表页号) headLineMun(表头占行数) tableHand(表头数据) dataList(内容数据) fileName(导出文件名)
//     *      选填:tableStyle(表通用样式) columnWidth(手动设置列宽) definedStyle(自定义样式方法名)
//     *
//     * @Date 2019/5/7 15:45
//     * @Param [response, params]
//     * @return void
//     **/
//    public static void downExcel(HttpServletResponse response, Map<String,Object> params) throws Exception {
//        logger.info("开始导出：" + DateUtil.formatDate(new Date(), "YYYY_MM_DD HH_MM_SS"));
//
//        List<List<Object>> dataList = (List<List<Object>>)params.get("dataList");   //要导出的数据  List(行)-->object(列)
//
//        String fileName = (String)params.get("fileName");                           //导出文件名
//        ServletOutputStream out = null;                                             //输出流
//
//        try {
//            out = response.getOutputStream();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX, true);
//        Workbook wb = EasyExcelUtil.getWorkbook(writer);                                        //获取Workbook对象,用来调整样式
//        String sheetName = (String)params.get("sheetName");                                     //工作表名称
//        int sheetNo = (int)params.get("sheetNo");                                               //sheet页
//        int headLineMun  = (int)params.get("headLineMun");                                      //表头占行数
//        Sheet sheet1 = new Sheet(sheetNo,headLineMun);                                          //创建工作表1
//        sheet1.setSheetName(sheetName);                                                         //设置工作表名称
//        TableStyle tableStyle = (TableStyle)params.get("tableStyle");                           //通用表样式
//        if(tableStyle != null){
//            sheet1.setTableStyle(tableStyle);
//        }
//        Map columnWidth = (Map)params.get("columnWidth");                                       //列宽
//        if(columnWidth == null){                                                                //设置列宽
//            sheet1.setAutoWidth(true);                                                          //设置自适应宽度
//        }else{
//            sheet1.setColumnWidthMap(columnWidth);                                              //自定义列宽
//        }
//
//        List tableHand = (List)params.get("tableHand");                                         //表头
//        int columnNum = tableHand.size();                                                       //总列数
//        sheet1.setHead(tableHand);                                                              //设置表头
//        writer.write1(dataList, sheet1);                                                        //将内容写入工作表1
//        String definedStyleMethodName = (String)params.get("definedStyle");                     //获取自定义样式的方法名
//        if(definedStyleMethodName != null) {                                                                 //添加自定义样式
//            Class<?> excelHeadTempClass = ExcelHeadTemp.class;                                  //获取Excel表头模板类
//            Method excelTempMethod = excelHeadTempClass.getMethod(definedStyleMethodName, Workbook.class);   //获取对应的模板方法
//            Map definedStyle = (Map) excelTempMethod.invoke(null, wb);                     //获取到对应的自定义样式集合
//            org.apache.poi.ss.usermodel.Sheet s = wb.getSheet(sheetName);                        //根据工作表名称从workbook获取工作表
//            //调整表头样式
//            if(definedStyle.get("headStyles") != null) {
//                List<Map<String,CellStyle>> headStyles = (List)definedStyle.get("headStyles");   //获取表头样式
//                for (int i = 0; i < headLineMun; i++) {                                          //遍历表头行
//                    Row row = s.getRow(i);
//                    Map<String,CellStyle> headStyle = headStyles.get(i);
//                    row.getCell(0).setCellStyle(headStyle.get("headStyleLeft"));
//                    for (int j = 1; j < columnNum - 1; j++) {
//                        row.getCell(j).setCellStyle(headStyle.get("headStyleCenter"));
//                    }
//                    row.getCell(columnNum - 1).setCellStyle(headStyle.get("headStyleRight"));
//                }
//            }
//            //调整内容样式
//            if(definedStyle.get("contentStyle") != null) {
//                Map<String,CellStyle> contentStyle = (Map)definedStyle.get("contentStyle");        //获取内容样式
//                for (int i = headLineMun; i < dataList.size()+ headLineMun; i++) {                 //遍历数据行
//                    Row row = s.getRow(i);
//                    row.setHeight((short)500);
//                    row.getCell(0).setCellStyle(contentStyle.get("contentStyleLeft"));
//                    for (int j = 1; j < columnNum - 1; j++) {
//                        row.getCell(j).setCellStyle(contentStyle.get("contentStyleCenter"));
//                    }
//                    row.getCell(columnNum - 1).setCellStyle(contentStyle.get("contentStyleRight"));
//                }
//            }
//            //调整尾行样式
//            if(definedStyle.get("endStyle") != null) {
//                Map<String,CellStyle> endStyle = (Map)definedStyle.get("endStyle");                //获取尾行样式
//                Row lastRow = s.getRow(s.getLastRowNum());                                         //获取尾行
//                lastRow.getCell(0).setCellStyle(endStyle.get("endStyleLeft"));
//                for (int j = 1; j < columnNum - 1; j++) {
//                    lastRow.getCell(j).setCellStyle(endStyle.get("endStyleCenter"));
//                }
//                lastRow.getCell(columnNum - 1).setCellStyle(endStyle.get("endStyleRight"));
//            }
//            if(definedStyle.get("setHeight") != null){
//                Map<Integer,Short> setHeight = (Map)definedStyle.get("setHeight");
//                Set<Integer> rows = setHeight.keySet();
//                for (Integer rowNumber:rows) {
//                    Row row = s.getRow(rowNumber);
//                    row.setHeight(setHeight.get(rowNumber));
//                }
//            }
//            if(definedStyle.get("setBottomSign") != null){
//                Row bottomRow = s.createRow(s.getLastRowNum()+1);
//                Map<Integer,String> setHeight = (Map)definedStyle.get("setBottomSign");
//                Set<Integer> cols = setHeight.keySet();
//                for (Integer colNumber:cols) {
//                    bottomRow.createCell(colNumber).setCellValue(setHeight.get(colNumber));
//                }
//            }
//        }
//        //添加响应头信息
//        try {
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
//            out.flush();
//        } catch (Exception e) {
//            //导出失败
//            e.printStackTrace();
//            logger.info("导出失败 \n"+ e.toString());
//        } finally {
//            writer.finish();
//            try {
//                out.close();
//            } catch (IOException e) {
//                //流关闭失败
//                e.printStackTrace();
//                logger.info("流关闭失败 \n"+ e.toString());
//            }
//        }
//        logger.info("导出完成：" + DateUtil.formatDate(new Date(), "YYYY_MM_DD HH_MM_SS"));
//    }
//
//
//    /**
//     * 20190528 zgq
//     * 主要内容来自 downExcel ,后期优化整合为一个方法
//     * @Author 高敏
//     * @Description 导出excel工具类
//     *  params包含该方法所需的参数:
//     *      必填:sheetName(工作表名称) sheetNo(工作表页号) headLineMun(表头占行数) tableHand(表头数据) dataList(内容数据) fileName(导出文件名)
//     *      选填:tableStyle(表通用样式) columnWidth(手动设置列宽) definedStyle(自定义样式方法名)
//     *
//     * @Date 2019/5/7 15:45
//     * @Param [response, params]
//     * @return void
//     **/
//    public static void createExcelAndAddToZip( Map<String,Object> params) throws Exception {
//        logger.info("开始往zip添加文件：" + DateUtil.formatDate(new Date(), "YYYY_MM_DD HH_MM_SS"));
//
//        List<List<Object>> dataList = (List<List<Object>>)params.get("dataList");   //要导出的数据  List(行)-->object(列)
//
//
//        //String fileName = (String)params.get("fileName")+".xlsx";                           //导出文件名
//        //String filePath = tmpdir + File.separator+fileName;
//        String filePath = (String)params.get("filePath");
//        //logger.info("filepath:"+filePath);
//        FileOutputStream fos = new FileOutputStream(filePath);
//
//        ExcelWriter writer = new ExcelWriter(fos, ExcelTypeEnum.XLSX, true);
//        Workbook wb = EasyExcelUtil.getWorkbook(writer);                                        //获取Workbook对象,用来调整样式
//        String sheetName = (String)params.get("sheetName");                                     //工作表名称
//        int sheetNo = (int)params.get("sheetNo");                                               //sheet页
//        int headLineMun  = (int)params.get("headLineMun");                                      //表头占行数
//        Sheet sheet1 = new Sheet(sheetNo,headLineMun);                                          //创建工作表1
//        sheet1.setSheetName(sheetName);                                                         //设置工作表名称
//        TableStyle tableStyle = (TableStyle)params.get("tableStyle");                           //通用表样式
//        if(tableStyle != null){
//            sheet1.setTableStyle(tableStyle);
//        }
//        Map columnWidth = (Map)params.get("columnWidth");                                       //列宽
//        if(columnWidth == null){                                                                //设置列宽
//            sheet1.setAutoWidth(true);                                                          //设置自适应宽度
//        }else{
//            sheet1.setColumnWidthMap(columnWidth);                                              //自定义列宽
//        }
//
//        List tableHand = (List)params.get("tableHand");                                         //表头
//        int columnNum = tableHand.size();                                                       //总列数
//        sheet1.setHead(tableHand);                                                              //设置表头
//        writer.write1(dataList, sheet1);                                                        //将内容写入工作表1
//        String definedStyleMethodName = (String)params.get("definedStyle");                     //获取自定义样式的方法名
//        if(definedStyleMethodName != null) {                                                                 //添加自定义样式
//            Class<?> excelHeadTempClass = ExcelHeadTemp.class;                                  //获取Excel表头模板类
//            Method excelTempMethod = excelHeadTempClass.getMethod(definedStyleMethodName, Workbook.class);   //获取对应的模板方法
//            Map definedStyle = (Map) excelTempMethod.invoke(null, wb);                     //获取到对应的自定义样式集合
//            org.apache.poi.ss.usermodel.Sheet s = wb.getSheet(sheetName);                        //根据工作表名称从workbook获取工作表
//            //调整表头样式
//            if(definedStyle.get("headStyles") != null) {
//                List<Map<String,CellStyle>> headStyles = (List)definedStyle.get("headStyles");   //获取表头样式
//                for (int i = 0; i < headLineMun; i++) {                                          //遍历表头行
//                    Row row = s.getRow(i);
//                    Map<String,CellStyle> headStyle = headStyles.get(i);
//                    row.getCell(0).setCellStyle(headStyle.get("headStyleLeft"));
//                    for (int j = 1; j < columnNum - 1; j++) {
//                        row.getCell(j).setCellStyle(headStyle.get("headStyleCenter"));
//                    }
//                    row.getCell(columnNum - 1).setCellStyle(headStyle.get("headStyleRight"));
//                }
//            }
//            //调整内容样式
//            if(definedStyle.get("contentStyle") != null) {
//                Map<String,CellStyle> contentStyle = (Map)definedStyle.get("contentStyle");        //获取内容样式
//                for (int i = headLineMun; i < dataList.size()+ headLineMun; i++) {                 //遍历数据行
//                    Row row = s.getRow(i);
//                    row.setHeight((short)500);
//                    row.getCell(0).setCellStyle(contentStyle.get("contentStyleLeft"));
//                    for (int j = 1; j < columnNum - 1; j++) {
//                        row.getCell(j).setCellStyle(contentStyle.get("contentStyleCenter"));
//                    }
//                    row.getCell(columnNum - 1).setCellStyle(contentStyle.get("contentStyleRight"));
//                }
//            }
//            //调整尾行样式
//            if(definedStyle.get("endStyle") != null) {
//                Map<String,CellStyle> endStyle = (Map)definedStyle.get("endStyle");                //获取尾行样式
//                Row lastRow = s.getRow(s.getLastRowNum());                                         //获取尾行
//                lastRow.getCell(0).setCellStyle(endStyle.get("endStyleLeft"));
//                for (int j = 1; j < columnNum - 1; j++) {
//                    lastRow.getCell(j).setCellStyle(endStyle.get("endStyleCenter"));
//                }
//                lastRow.getCell(columnNum - 1).setCellStyle(endStyle.get("endStyleRight"));
//            }
//            if(definedStyle.get("setHeight") != null){
//                Map<Integer,Short> setHeight = (Map)definedStyle.get("setHeight");
//                Set<Integer> rows = setHeight.keySet();
//                for (Integer rowNumber:rows) {
//                    Row row = s.getRow(rowNumber);
//                    row.setHeight(setHeight.get(rowNumber));
//                }
//            }
//            if(definedStyle.get("setBottomSign") != null){
//                Row bottomRow = s.createRow(s.getLastRowNum()+1);
//                Map<Integer,String> setHeight = (Map)definedStyle.get("setBottomSign");
//                Set<Integer> cols = setHeight.keySet();
//                for (Integer colNumber:cols) {
//                    bottomRow.createCell(colNumber).setCellValue(setHeight.get(colNumber));
//                }
//            }
//        }
//        writer.finish();
//        fos.flush();
//        fos.close();
//        ////添加响应头信息
//        //try {
//        //    //response.setCharacterEncoding("utf-8");
//        //    //response.setContentType("application/vnd.ms-excel");
//        //    //response.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "utf-8"));
//        //    out.flush();
//        //} catch (Exception e) {
//        //    //导出失败
//        //    e.printStackTrace();
//        //    logger.info("导出失败 \n"+ e.toString());
//        //} finally {
//        //    writer.finish();
//        //    try {
//        //        out.close();
//        //    } catch (IOException e) {
//        //        //流关闭失败
//        //        e.printStackTrace();
//        //        logger.info("流关闭失败 \n"+ e.toString());
//        //    }
//        //}
//        logger.info("往zip添加文件完成：" + DateUtil.formatDate(new Date(), "YYYY_MM_DD HH_MM_SS"));
//    }
//
//    public static Map<Workbook, Map<String, CellStyle>> getCellStyles() {
//        return cellStyles;
//    }

}
