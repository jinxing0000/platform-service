package com.bettem.modules.tourism.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 旅游产品图片信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-23 17:11:10
 */
@TableName("tourism_product_pic")
public class TourismProductPicEntity extends BaseEntity {

	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 图片展示路径
	 */
	private String thumbUrl;
	/**
	 * minio下载路径
	 */
	private String minioPath;

	/**
	 * 设置：产品id
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品id
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 设置：图片展示路径
	 */
	public void setThumbUrl(String thumbUrl) {
		this.thumbUrl = thumbUrl;
	}
	/**
	 * 获取：图片展示路径
	 */
	public String getThumbUrl() {
		return thumbUrl;
	}
	/**
	 * 设置：minio下载路径
	 */
	public void setMinioPath(String minioPath) {
		this.minioPath = minioPath;
	}
	/**
	 * 获取：minio下载路径
	 */
	public String getMinioPath() {
		return minioPath;
	}
}
