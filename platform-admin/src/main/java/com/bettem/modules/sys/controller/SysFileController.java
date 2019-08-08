package com.bettem.modules.sys.controller;

import com.bettem.common.utils.R;
import com.bettem.modules.sys.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/sys/file/")
public class SysFileController {



    @Autowired
    private SysFileService sysFileService;

    /**
     * 上传文件接口
     * @param file
     * @return
     */
    @RequestMapping(value="uploadFile",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public R uploadFile(@RequestParam("file") MultipartFile file){
        Map<String,Object> resultMap=sysFileService.uploadFile(file);
        return R.ok(resultMap);
    }

    /**
     * ckeditor  上传文件
     * @param file
     * @return
     */
    @RequestMapping(value="ckEditorUploadFile",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
    public Map<String,Object> ckEditorUploadFile(@RequestParam("upload") MultipartFile file){
        Map<String,Object> resultMap=sysFileService.uploadFile(file);
        Map<String,Object> result=new HashMap<>();
        result.put("uploaded",1);
        result.put("fileName",resultMap.get("fileName"));
        result.put("url",resultMap.get("fileUrl"));
        return result;
    }

    @RequestMapping(value="mongdb",method = RequestMethod.GET)
    public R mongdb(){
        sysFileService.mongdbTest();
        return R.ok();
    }
}
