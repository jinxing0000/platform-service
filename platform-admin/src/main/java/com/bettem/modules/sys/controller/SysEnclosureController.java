package com.bettem.modules.sys.controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.modules.sys.entity.SysUserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bettem.modules.sys.service.SysEnclosureService;
import com.bettem.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统附件表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2018-10-17 16:50:12
 */
@RestController
@RequestMapping("sys/enclosure/")
public class SysEnclosureController{

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(SysEnclosureController.class);

    @Autowired
    private SysEnclosureService sysEnclosureService;

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Value("${bettem.uploadFilePath}")
    private String  path;

    @Value("${bettem.imagePath}")
    private String  imagePath;
    /**
     *  上传文件到本地
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "uploadFile")
    public R uploadFile(@RequestParam("file") MultipartFile file,@RequestParam Map<String, Object> params) throws Exception {
        params.put("file",file);
        List<Map<String,Object>> resultData=sysEnclosureService.uploadFile(params);
        return  R.ok().put("data",resultData);
    }

    /**
     *  根据业务类型获取文件列表
     * @param {业务数据id，业务数据类型，业务类型}
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getFileList")
    public R getFileList(@RequestParam Map<String, Object> params){
        List<Map<String,Object>> resultData=sysEnclosureService.selectUploadData(params);
        Map<String,Object> map=new HashMap<>();
        map.put("data",resultData);
        map.put("imagePath",imagePath);
        return  R.ok(map);
    }

    /**
     *  获取文件列表
     * @param
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "getFiles")
    public R getFiles(){
        //List<Map<String,Object>> resultData=sysEnclosureService.selectFileData();
        //Map<String,Object> map=new HashMap<>();
        //map.put("data",resultData);

        Map<String,Object> map=new HashMap<>();
        SysUserEntity u = shiroTokenUtils.getUserInfo();
        String regionCode = "";
        int length = 0;
        if(regionCode !=null && regionCode.length()>0){
            length = regionCode.length();
        }
        List<String> opList = new ArrayList();
        String coutyVideo = "山西农业补贴资金管理平台使用视频（县级）.mov";
        String townVideo =  "山西农业补贴资金管理平台使用视频（乡级）.mov";
        String coutyDoc = "山西农业补贴资金管理平台使用手册（县级）.docx";
        String townDoc =  "山西农业补贴资金管理平台使用手册（乡级）.docx";

        //if(length==2){//省
        //}else if(length==4){//市
        //}else if(length==6){//县
        //
        //}else if(length==9){//乡
        //
        //}else if(length==12){//村
        //}
        opList.add(coutyDoc);
        opList.add(coutyVideo);
        opList.add(townDoc);
        opList.add(townVideo);
        map.put("data",opList);
        return  R.ok(map);
    }

    /**
     * @Param [request, response, params]
     * @Return: java.lang.String
     * @Decription: 按照附件id下载文件
     * @CreateDate: Created in 2018/10/19 14:07
     * @Author: 颜金星
     */
    @RequestMapping(value = "downloadFileById")
    public String downloadFileById(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> params){
        sysEnclosureService.downloadFileByParams(request,response,params);
        return null;
    }

    /**
     * @Param [request, response, params]
     * @Return: java.lang.String
     * @Decription: 下载文件
     * @CreateDate: Created in 2019-5-27 14:59:47
     * @Author: duandy
     */
    @RequestMapping(value = "downloadFile")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String,Object> params){
        sysEnclosureService.downloadFile(request,response,params);
        return null;
    }

    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 删除附件
     * @CreateDate: 2019-04-28 15:31:29
     * @Author: 张瑞
     */
    @SysLog("删除附件表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestParam String id,@RequestParam String dataId,@RequestParam String dataType,@RequestParam String businessType){
        Map<String,Object> params = new HashMap<>();
        params.put("dataId",dataId);
        params.put("dataType",dataType);
        params.put("businessType",businessType);
        params.put("id",id);
        List<Map<String,Object>> resultData=sysEnclosureService.deleteByIdParams(params);
        return R.ok().put("data",resultData);
    }
}
