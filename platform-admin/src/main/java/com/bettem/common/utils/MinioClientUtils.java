package com.bettem.common.utils;

import com.bettem.common.exception.RRException;
import io.minio.MinioClient;

import java.io.*;

/**
 * minio 上传下载工具类
 */
public class MinioClientUtils {

    private MinioClient minioClient;
    //minio 服务端地址
    private String minioServerUrl;
    //minio用户名
    private String accessKey;
    //minio密码
    private String secretKey;
    //存储桶名称
    private String bucketName;

    /**
     * 构造函数
     * @param minioServerUrl minio 服务端地址
     * @param accessKey minio用户名
     * @param secretKey minio密码
     * @param bucketName 存储桶名称
     */
    public MinioClientUtils(String minioServerUrl,String accessKey,String secretKey,String bucketName){
        this.minioServerUrl=minioServerUrl;
        this.accessKey=accessKey;
        this.secretKey=secretKey;
        this.bucketName=bucketName;
    }

    /**
     * 获取minio客户端
     */
    public void getMinioClient(){
        try {
            // 使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象
            minioClient = new MinioClient(minioServerUrl, accessKey, secretKey);
        }catch(Exception e){
            throw new RRException(ErrorCodeConstant.ERROR,"minio 服务端无法链接！！");
        }
    }

    /**
     *  上传文件到minio服务端
     * @param fileInputStream   上传文件流
     * @param filePath   上传文件地址
     * @param contentType  上传文件多媒体类型
     * @return  返回访问路径
     */
    public String uploadFile(InputStream fileInputStream,String filePath,String contentType){
        String path="";
        try {
            getMinioClient();
            // 检查存储桶是否已经存在
            boolean isExist = minioClient.bucketExists(bucketName);
            if(!isExist){
                // 创建一个名为bettem的存储桶
                minioClient.makeBucket(bucketName);
            }
            minioClient.putObject(bucketName,filePath,fileInputStream, contentType);
            path=minioClient.presignedGetObject(bucketName,filePath);
        }catch(Exception e){
            e.printStackTrace();
            throw new RRException(ErrorCodeConstant.ERROR,"上传文件失败！！");
        }
        return path;
    }

    /**
     * 从minio服务端下载文件
     * @param filePath   下载文件的地址
     * @return  文件流
     */
    public InputStream downFile(String filePath){
        InputStream in=null;
        try {
            getMinioClient();
            in=minioClient.getObject(bucketName,filePath);
        }catch(Exception e){
            throw new RRException(ErrorCodeConstant.ERROR,"下载文件失败！！");
        }
        return in;
    }
}
