package com.bettem.modules.sys.service.impl;

import com.bettem.common.utils.UploadFileUtil;
import com.bettem.modules.sys.service.SysFileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;



@Service("sysFileService")
public class SysFileServiceImpl implements SysFileService {

    //minio 服务端地址
    @Value("${bettem.minio.serverUrl}")
    private String minioServerUrl;
    //minio用户名
    @Value("${bettem.minio.accessKey}")
    private String accessKey;
    //minio密码
    @Value("${bettem.minio.secretKey}")
    private String secretKey;
    //存储桶名称
    @Value("${bettem.minio.bucketName}")
    private String bucketName;
    //最大文件大小
    @Value("${bettem.uploadFileSize}")
    private long uploadFileSize;
    //可以上传文件后缀
    @Value("${bettem.uploadFileType}")
    private String uploadFileType;

    @Override
    public Map<String, Object> uploadFile(MultipartFile file) {
        UploadFileUtil uploadFileUtil=new UploadFileUtil(minioServerUrl,accessKey,secretKey,bucketName,uploadFileSize,uploadFileType);
        Map<String, Object> resultMap=uploadFileUtil.uploadFile(file);
        return resultMap;
    }
}
