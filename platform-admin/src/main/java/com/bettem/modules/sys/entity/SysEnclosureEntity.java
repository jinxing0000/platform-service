package com.bettem.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 系统附件表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2018-10-17 16:10:48
 */
@TableName("SYS_ENCLOSURE")
public class SysEnclosureEntity extends BaseEntity {

	/**
	 * 文件名称
	 */
	private String fileName;
	/**
	 * 文件后缀
	 */
	private String fileSuffix;
	/**
	 * 文件路径
	 */
	private String filePath;
    /**
     * @Param
     * @Return: 
     * @Decription:  文件大小
     * @CreateDate: Created in 2018/10/19 11:28
     * @Author: 颜金星
     */
    
	private double fileSize;
	/**
	 * 上传时间
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date uploadDate;

	/**
	 * 设置：文件名称
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * 获取：文件名称
	 */
	public String getFileName() {
		return fileName;
	}
	/**
	 * 设置：文件后缀
	 */
	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}
	/**
	 * 获取：文件后缀
	 */
	public String getFileSuffix() {
		return fileSuffix;
	}
	/**
	 * 设置：文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	/**
	 * 获取：文件路径
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * 设置：上传时间
	 */
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	/**
	 * 获取：上传时间
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getUploadDate() {
		return uploadDate;
	}

	public void setFileSize(double fileSize) {
		this.fileSize = fileSize;
	}

	public double getFileSize() {
		return fileSize;
	}
}
