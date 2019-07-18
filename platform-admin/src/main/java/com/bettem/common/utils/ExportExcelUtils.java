package com.bettem.common.utils;

import com.bettem.common.exception.RRException;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.transform.poi.PoiTransformer;
import org.jxls.util.JxlsHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * jxls 导出Excel工具类
 */
public class ExportExcelUtils {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(ExportExcelUtils.class);


    /**
     * 按照模板导出Excel表
     * @param fileName   导出下载名称
     * @param model  map数据区
     * @param response 响应
     * @param request  请求
     * @return
     */
    public static void createExcelFile(String templatePath,String fileName,Map<String, Object> model,HttpServletResponse response, HttpServletRequest request,boolean shouldSaveToDisk){
        try{

            OutputStream os = null;
            //是否建立本地文件
            if(shouldSaveToDisk){
                //文件夹路径
                String diskDirectory = (String)model.get("diskDirectory");// d:/aaa/
                //文件名
                String diskFileName = (String)model.get("diskFileName");// d:/aaa/bb.xls
                File diskDirectoryF = new File(diskDirectory);
                if(!diskDirectoryF.exists()){
                    diskDirectoryF.mkdirs();
                }
                File diskFileNameF = new File(diskDirectory + File.separator + diskFileName);
                os = new FileOutputStream(diskFileNameF);
            }else{
                os=response.getOutputStream();
                //解决乱码
                String agent = request.getHeader("USER-AGENT");
                if (null != agent){
                    if (-1 != agent.indexOf("Firefox")) {//Firefox
                        fileName = "=?UTF-8?B?" + (new String(org.apache.commons.codec.binary.Base64.encodeBase64(fileName.getBytes("UTF-8"))))+ "?=";
                    }else if (-1 != agent.indexOf("Chrome")) {//Chrome
                        fileName = new String(fileName.getBytes(), "ISO8859-1");
                    } else {//IE7+
                        fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
                        fileName = StringUtils.replace(fileName, "+", "%20");//替换空格
                    }
                }
                // 设置response的编码方式
                response.setContentType("application/x-msdownload");
                // 设置附加文件名
                response.setHeader("Content-Disposition","attachment;filename="+fileName);
            }
            //导出Excel表设置数据
            Context context = PoiTransformer.createInitialContext();
            if (model != null) {
                for (Map.Entry<String, Object> entry : model.entrySet()){
                    context.putVar(entry.getKey(), entry.getValue());
                }
            }
            InputStream is = ExportExcelUtils.class.getClassLoader().getResourceAsStream(templatePath);
            JxlsHelper jxlsHelper = JxlsHelper.getInstance();
            Transformer transformer  = jxlsHelper.createTransformer(is, os);
            jxlsHelper.setUseFastFormulaProcessor(false).processTemplate(context, transformer);
            //jxlsHelper.setUseFastFormulaProcessor(false).processTemplateAtCell(is,os ,context ,sheetName+"!A1");
            os.close();
        }catch(Exception e){
            e.printStackTrace();
            throw new RRException("导出Excel表失败！！");
        }
    }


    /**
     * 获取下载模板的路径
     * @param params
     * @return
     */
    public static String getTemplatePathForDownload(Map<String,Object> params){
        params.put("templatePathForDownload","templatesForDownload/");
        String templatePath = getTemplatePath(params);

        return templatePath;
    }

    /**
     * 获取导出Excel表模板路径
     * @param params
     * @return
     */
    public static String getTemplatePath(Map<String,Object> params){
        String templatePath="exportTemplates/";
        //region 下载模板的处理
        try{
            String templatePathForDownload = (String)params.get("templatePathForDownload");
            if(templatePathForDownload!=null){
                templatePath = templatePathForDownload;
            }
        }catch (Exception e){
        }
        //endregion

        String subsidyType=(String)params.get("subsidyType");
        templatePath+=subsidyType+"/";
        String subsidyYear=(String)params.get("subsidyYear");
        //region
        String rootPath = ExportExcelUtils.class.getClassLoader().getResource("").getFile();
        File tf = new File(rootPath+templatePath + File.separator + subsidyYear);
        //判断该年度下是否有文件夹
        if(!tf.exists()){
            subsidyYear = "base";
        }
        templatePath+=subsidyYear+"/";
        //region 是否是包含修正值的模板的处理
        boolean isModified = false;//是否是包含修正值的模板
        try{
            isModified = (boolean)params.get("isModified");
        }catch (Exception e){
        }
        if(isModified){//m表示修正值   ground/2019/m/
            templatePath += "m/";
        }
        //endregion
        String countyId=(String)params.get("countyId");
        String groundTemplateType=(String)params.get("groundTemplateType");
        Map<String,Object> analysisParams=(Map<String,Object>)ConstantGround.SPECIAL_COUNTY_EXCEL_MAP.get(countyId);
//        //判断为dtl
//        if(Constant.GroundTemplateType.DTL.getValue().equals(groundTemplateType)){
//            if(analysisParams!=null){
//                templatePath+=groundTemplateType+"_"+countyId+".xls";
//            }else{
//                templatePath+=groundTemplateType+"_000000.xls";
//            }
//        }
//        // 判断为publicity
//        else if(Constant.GroundTemplateType.PUBLICITY.getValue().equals(groundTemplateType)){
//            if(analysisParams!=null){
//                templatePath+=groundTemplateType+"_"+countyId+".xls";
//            }else{
//                templatePath+=groundTemplateType+".xls";
//            }
//        }
//        //其他类型
//        else{
//            templatePath+=groundTemplateType+".xls";
//        }
        return templatePath;
    }
}
