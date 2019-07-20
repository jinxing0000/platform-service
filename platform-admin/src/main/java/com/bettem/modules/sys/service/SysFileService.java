package com.bettem.modules.sys.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SysFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Map<String,Object> uploadFile(MultipartFile file);
}
