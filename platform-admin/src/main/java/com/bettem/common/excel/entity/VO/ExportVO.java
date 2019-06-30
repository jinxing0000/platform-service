package com.bettem.common.excel.entity.VO;

import java.util.Map;
import java.io.Serializable;

public class ExportVO implements Serializable {

    private static final long serialVersionUID = 1L;

    //导出的excel信息[fileName,titles]
    private Map<String,Object> excelParam;
    //数据信息
    private Map<String,Object> tableParam;


    public Map<String, Object> getExcelParam() {
        return excelParam;
    }

    public void setExcelParam(Map<String, Object> excelParam) {
        this.excelParam = excelParam;
    }

    public Map<String, Object> getTableParam() {
        return tableParam;
    }

    public void setTableParam(Map<String, Object> tableParam) {
        this.tableParam = tableParam;
    }
}
