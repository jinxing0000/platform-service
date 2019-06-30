package com.bettem.common.excel.constant;

/**
 * @Auther: duandy
 * @Date: 2019/1/4 17:56
 * @Description:
 */
public class ExcelConstant {

    /**
     * 表格默认名称
     */
    public static final String FILE_NAME = "TEST.xls";
    /**
     * 表格默认名称
     */
    public static final String MESSAGE_FILENAME = "公众参与统计";
    /**
     * 表格默认名称
     */
    public static final String PUBLICINFO_FILENAME = "政府信息公开统计";
    /**
     * 表格默认名称
     */
    public static final String ARTICLE_FILENAME = "文档查询";
    /**
     * 表格默认名称
     */
    public static final String SITEARTICLECLASS_FILENAME = "文档统计";


    /**
     * 每个sheet存储的记录数 100W
     */
    public static final Integer PER_SHEET_ROW_COUNT = 1000000;

    /**
     * 每次向EXCEL写入的记录数(查询每页数据大小) 20W
     */
    public static final Integer PER_WRITE_ROW_COUNT = 200000;
    /**
     * 每个sheet的写入次数 5
     */
    public static final Integer PER_SHEET_WRITE_COUNT = PER_SHEET_ROW_COUNT / PER_WRITE_ROW_COUNT;

}
