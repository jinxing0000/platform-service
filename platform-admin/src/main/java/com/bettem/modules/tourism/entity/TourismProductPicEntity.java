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
 * @date 2019-07-20 16:24:27
 */
@TableName("tourism_product_pic")
public class TourismProductPicEntity extends BaseEntity {

	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 图片路径
	 */
	private String picPath;
	/**
	 * 图片类型
	 */
	private String picType;

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
	 * 设置：图片路径
	 */
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	/**
	 * 获取：图片路径
	 */
	public String getPicPath() {
		return picPath;
	}
	/**
	 * 设置：图片类型
	 */
	public void setPicType(String picType) {
		this.picType = picType;
	}
	/**
	 * 获取：图片类型
	 */
	public String getPicType() {
		return picType;
	}
}
