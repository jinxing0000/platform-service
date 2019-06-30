package com.bettem.modules.sys.controller;

import com.alibaba.fastjson.JSONObject;
import com.bettem.common.config.UeditorConfig;
import com.bettem.common.utils.SMSUtils;
import com.bettem.common.utils.UploadFileUtil;
import com.bettem.modules.sys.entity.VO.SysUeditorVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: bettem-security
 * @CreateDate: Created in 2018/12/21 10:56 <br>
 * @Author: 颜金星
 */
@RestController
@RequestMapping("/statics/plugins/ueditor/")
public class SysUeditorController{

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(SysUeditorController.class);

    @Value("${bettem.ftpServerIP}")
    private String ftpServerIP;
    @Value("${bettem.ftpServerPort}")
    private int ftpServerPort;
    @Value("${bettem.ftpUserName}")
    private String  ftpUserName;
    @Value("${bettem.ftpPassword}")
    private String  ftpPassword;
    @Value("${bettem.imagePath}")
    private String  imagePath;
    @Value("${bettem.uploadFileType}")
    private String uploadFileType;
    @Value("${bettem.uploadFileSize}")
    private long uploadFileSize;
    @Value("${bettem.ftpPath}")
    private String ftpPath;
    /**
     *  富文本编辑器上传文件
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "ueditorConfig", method = RequestMethod.GET)
    public String ueditorUploadFile(HttpServletRequest request, String action, HttpServletResponse response) throws UnsupportedEncodingException {
        request.setCharacterEncoding( "utf-8" );
        response.setHeader("Content-Type" , "text/html");
        logger.debug("请求action:"+action);
        UploadFileUtil uploadFileUtil=new UploadFileUtil(ftpServerIP,ftpServerPort,ftpUserName,ftpPassword,uploadFileSize,ftpPath);
        SysUeditorVO sysUeditorVO=null;
        JSONObject data =null;
        switch (action) {
            case "config": // 此处即为获取配置文件的case
                String myresult = UeditorConfig.CONFIG.getConfigStr();
                logger.debug("配置文件内容："+myresult);
                return myresult;
            //图片上传
            case "uploadimage":
                sysUeditorVO=uploadFileUtil.ueditorUploadFileFTP(uploadFileType);
                sysUeditorVO.setUrl(imagePath+sysUeditorVO.getUrl());
                data = (JSONObject)  JSONObject.toJSON(sysUeditorVO);
                return data.toJSONString();
            //文件上传
            case "uploadfile":
                sysUeditorVO=uploadFileUtil.ueditorUploadFileFTP(uploadFileType);
                sysUeditorVO.setUrl(imagePath+sysUeditorVO.getUrl());
                data = (JSONObject)  JSONObject.toJSON(sysUeditorVO);
                return data.toJSONString();
            //上传视频
            case "uploadvideo":
                 sysUeditorVO=uploadFileUtil.ueditorUploadFileFTP(uploadFileType);
                 sysUeditorVO.setUrl(imagePath+sysUeditorVO.getUrl());
                 data = (JSONObject)  JSONObject.toJSON(sysUeditorVO);
                 return data.toJSONString();
        }
        logger.debug("【调用接口失败】，没有匹配到操作！！");
        return "【调用接口失败】，没有匹配到操作！！";
    }
}
