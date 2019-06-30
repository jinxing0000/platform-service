package com.bettem.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bettem.common.exception.RRException;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.CellType.STRING;

/**
 * 读取Excel表数据工具类
 */
public class ReadExcelUtils {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(ReadExcelUtils.class);

    public static Map<String,Object> readExcelData(MultipartFile file, Map<String,Object> analysisParams, boolean isGetOtherInfo){
        Map<String,Object> resultMap=new HashMap<>();
        List<Map<String,Object>> errorList=new LinkedList<>();
        boolean flag=true;
        try{
            //获取Exel表文件名称
            String fileName=file.getOriginalFilename();
            //用于接收列表数据
            JSONArray dataList=new JSONArray();
            //获取文件流
            InputStream fileIs=file.getInputStream();
            HSSFWorkbook wookbook = new HSSFWorkbook(fileIs);  // 创建对Excel工作簿文件的引用
            HSSFSheet sheet = wookbook.getSheetAt(0);//取第一个sheet内容
            //获取表头设置信息项
            Map<Integer,Map<String,Object>> titleMaps=(Map<Integer,Map<String,Object>>)analysisParams.get("titleMap");
            //获取表头行
            HSSFRow headRow = sheet.getRow((Integer)analysisParams.get("headTitleNum")-1);
            //检查表头是否正确
            for (Map.Entry<Integer,Map<String,Object>> entry : titleMaps.entrySet()) {
                HSSFCell cell = headRow.getCell(entry.getKey());
                Map<String,Object> title=entry.getValue();
                //校验表头是否符合
                if(!title.get("titleName").equals(getCellValue(cell))){
                   // logger.debug("表头："+cell.toString()+"++++++++++++++");
                    flag=false;
                    Map<String,Object> errorMap=new HashMap<>();
                    errorMap.put("excelName",fileName);
                    errorMap.put("errorRow",((Integer)analysisParams.get("headTitleNum")));
                    errorMap.put("errorCol",(entry.getKey()+1));
                    errorMap.put("errorMag","表头标题有误！！");
                    errorList.add(errorMap);
                }
            }
            //获取Excel表所有数据行
            int rowNum = sheet.getPhysicalNumberOfRows();
            //获取列表开始行索引
            int rowStart=(Integer)analysisParams.get("headTitleNum");
            //获取列表结束行索引
            int rowEnd=rowNum-(Integer)analysisParams.get("lastTitleNum")-1;
            //循环所有行
            for(int i=rowStart;i<=rowEnd;i++){
                JSONObject data=new JSONObject();
                HSSFRow row = sheet.getRow(i);
                for (Map.Entry<Integer,Map<String,Object>> entry : titleMaps.entrySet()) {
                    HSSFCell cell = row.getCell(entry.getKey());
                    Map<String,Object> title=entry.getValue();
                    //获取Excel单元格数据值
                    String value=getCellValue(cell);
                    //返回错误信息
                    String errorMag=checkData(value,title);
                    //校验数据通过，返回null说明校验成功
                    if(errorMag==null){
                        //将Excel数据放入jsonObject
                        data.put((String)title.get("attribute"),value);
                    }else{
                        //设置Excel读取失败标识
                        flag=false;
                        //设置错误信息
                        Map<String,Object> errorMap=new HashMap<>();
                        errorMap.put("excelName",fileName);
                        errorMap.put("errorRow",(i+1));
                        errorMap.put("errorCol",(entry.getKey()+1));
                        errorMap.put("errorMag",errorMag);
                        errorList.add(errorMap);
                        logger.debug("第"+(i+1)+"行，第"+(entry.getKey()+1)+"列，不能为空！！");
                    }
                }
                dataList.add(data);
            }
            //存放其他不是列表中数据
            Map<String,Object> otherInfoData=new HashMap<>();
            //判断是否读取其他数据
            if(isGetOtherInfo){
                Map<String,Integer[]> otherInfoMap=(Map<String,Integer[]>)analysisParams.get("OtherInfo");
                //循环需要读取的数据
                for (Map.Entry<String, Integer[]> entry : otherInfoMap.entrySet()) {
                    HSSFRow row = sheet.getRow(entry.getValue()[0]);
                    HSSFCell cell = row.getCell(entry.getValue()[1]);
                    otherInfoData.put(entry.getKey(),getCellValue(cell));
                }
            }
            resultMap.put("flag",flag);
            resultMap.put("errorList",errorList);
            resultMap.put("dataList",dataList);
            resultMap.put("otherInfoData",otherInfoData);
            fileIs.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RRException("Excel表格式不对，请重新上传！！");
        }
        return resultMap;
    }

    /**
     * 根据Excel表获取的文字解析行政村名称
     * @param countyId   县id
     * @param villageName   Excel表中获取的名称
     * @return  行政村名称，返回空字符串，解析失败
     */
    public static String analysisVillageName(String countyId,String villageName,String townName){
        String village="";
        Map<String,Object> analysisParams=(Map<String,Object>)ConstantGround.SPECIAL_COUNTY_EXCEL_MAP.get(countyId);
        if(analysisParams!=null){
            String text=(String)analysisParams.get("villageText");
            village=villageName;
            village=village.replace(" ","");
            village=village.replace(townName,"");
            String strs[]=text.split(",");
            for(int i=0;i<strs.length;i++){
                village=village.replace(strs[i],"");
            }
        }else{
            String[] strs=villageName.split("-");
            if(strs.length==3){
                village=strs[2];
            }
        }
        return village;
    }

    /**
     * 根据Excel表获取的文字解析行政村名称
     * @param countyId   县id
     * @param year   Excel表中获取的年度字符串
     * @return  补贴年度，返回空字符串，解析失败
     */
    public static String analysisSubsidyYear(String countyId,String year){
        String subsidyYear="";
        Map<String,Object> analysisParams=(Map<String,Object>)ConstantGround.SPECIAL_COUNTY_EXCEL_MAP.get(countyId);
        if(analysisParams!=null){
            String text=(String)analysisParams.get("titleText");
            subsidyYear=year;
            String[] strs=text.split(",");
            for(int i=0;i<strs.length;i++){
                subsidyYear=subsidyYear.replace(strs[i],"");
            }
        }else{
            subsidyYear=year.replace("年农业支持保护补贴面积农户分户登记表","");
        }
        return subsidyYear;
    }

    /**
     * 比较两个Double类型数据是否相等
     * @param newObj
     * @param oldObj
     * @return
     */
    public static boolean checkDoubleEqual(Double newObj,Double oldObj){
        boolean flag=false;
        //两个都为null，表示没有变化
        if(newObj==null&&oldObj==null){
            flag=true;
        }
        //两个都不为null
        else if(newObj!=null&&oldObj!=null){
            flag=newObj.equals(oldObj);
        }
        return flag;
    }

    /**
     * 获取Cell单元格中的值
     * @param cell
     * @return
     */
    public static String getCellValue(HSSFCell cell){
        String cellValue=null;
        //如果单元格为null，直接返回null
        if(cell==null){
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            // 数字
            case NUMERIC:
                // 处理日期格式、时间格式
                if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
                    SimpleDateFormat sdf = null;
                    // 验证short值
                    if (cell.getCellStyle().getDataFormat() == 14) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    } else if (cell.getCellStyle().getDataFormat() == 21) {
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    } else if (cell.getCellStyle().getDataFormat() == 22) {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    } else {
                        return cellValue;
                    }
                    Date date = cell.getDateCellValue();
                    cellValue = sdf.format(date);
                }
                //处理数值格式
                else if (cell.getCellStyle().getDataFormat() == 0) {
                    cell.setCellType(STRING);
                    cellValue = String.valueOf(cell.getRichStringCellValue().getString());
                }
                break;
            case STRING: // 字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                cellValue = replaceBlank(cellValue);
                break;
            case BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case BLANK: // 空值
                cellValue = null;
                break;
            case ERROR: // 故障
                cellValue = null;
                break;
            default:
                cellValue = null;
                break;
        }
        return cellValue;
    }

    /**
     * 去除单元格中空格
     * @param str
     * @return
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            //空格\t、回车\n、换行符\r、制表符\t
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 校验数据类型
     * @param value
     * @param dataTypeMap
     * @return
     */
    public static String checkData(String value,Map<String,Object> dataTypeMap){
        String  resultStr=null;
        //是否判断数据不为空
        boolean isNotNull=(Boolean)dataTypeMap.get("isNotNull");
        if(isNotNull){
            //数据为null
            if(value==null){
                resultStr="该单元格为必填项！！";
            }
        }
        //数据为null
        if(value==null){
            //是否校验数据
            if(isNotNull){
                resultStr="该单元格为必填项！！";
            }
            return resultStr;
        }
        //要求数据类型
        String dataType=(String)dataTypeMap.get("type");
        //要求数据长度
        String dataLength=(String)dataTypeMap.get("length");
        switch (dataType){
            //字符类型
            case "String":
                int length=Integer.parseInt(dataLength);
                //判断数据字符长度大于要求长度
                if(length<value.length()){
                    resultStr="该单元格最大长度为："+length;
                }
                break;
            case "number":
                String reg = "^[0-9]+(.[0-9]+)?$";
                //判断为数值类型
                if(!value.matches(reg)){
                    resultStr="该单元格必须为数值类型！！";
                }
                String[] strs=dataLength.split(",");
                //总长度
                int totalLength=Integer.parseInt(strs[0]);
                //精度
                int accuracy=Integer.parseInt(strs[1]);
                String[] values=value.split("\\.");
                if(values[0].length()>(totalLength+accuracy+1)){
                    resultStr="该单元格最大长度为："+totalLength+"！！";
                }
                //判断有小数位
                if(values.length==2){
                    if(values[1].length()>accuracy){
                        resultStr="该单元格最多有"+accuracy+"位小数！！";
                    }
                }
                break;
            default:
                break;
        }
        return resultStr;
    }
}
