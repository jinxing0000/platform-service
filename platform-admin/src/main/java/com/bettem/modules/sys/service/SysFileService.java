package com.bettem.modules.sys.service;

import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface SysFileService {
    /**
     * 上传文件
     * @param file
     * @return
     */
    Map<String,Object> uploadFile(MultipartFile file);

    void mongdbTest();
}