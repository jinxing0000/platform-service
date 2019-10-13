package com.bettem.modules.tourism.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 旅游产品订单信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
@TableName("tourism_product_order")
public class TourismProductOrderEntity extends BaseEntity {

	/**
	 * 产品id
	 */
	private String productId;
	/**
	 * 订单类型，1为旅游产品订单
	 */
	private String orderType;
	/**
	 * 成人数量
	 */
	private Integer adultNumber;
	/**
	 * 儿童数量
	 */
	private Integer childrenNumber;
	/**
	 * 单房差数量
	 */
	private Integer singleRoomNumber;
	/**
	 * 订单总价
	 */
	private BigDecimal orderTotal;
	/**
	 * 实际成交价格
	 */
	private BigDecimal transactionPrice;
	/**
	 * 联系人
	 */
	private String contactsName;
	/**
	 * 联系手机号
	 */
	private String contactNumber;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 留言信息
	 */
	private String leavingMessage;
	/**
	 * 状态，0为未提交，1为待处理，2为处理完成
	 */
	private String state;
	/**
	 * 渠道商id
	 */
	private String channelMerchantsId;
	/**
	 * 渠道商名称
	 */
	private String channelMerchantsName;
	/**
	 * 供应商id
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;

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
	 * 设置：订单类型，1为旅游产品订单
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取：订单类型，1为旅游产品订单
	 */
	public String getOrderType() {
		return orderType;
	}
	/**
	 * 设置：成人数量
	 */
	public void setAdultNumber(Integer adultNumber) {
		this.adultNumber = adultNumber;
	}
	/**
	 * 获取：成人数量
	 */
	public Integer getAdultNumber() {
		return adultNumber;
	}
	/**
	 * 设置：儿童数量
	 */
	public void setChildrenNumber(Integer childrenNumber) {
		this.childrenNumber = childrenNumber;
	}
	/**
	 * 获取：儿童数量
	 */
	public Integer getChildrenNumber() {
		return childrenNumber;
	}
	/**
	 * 设置：单房差数量
	 */
	public void setSingleRoomNumber(Integer singleRoomNumber) {
		this.singleRoomNumber = singleRoomNumber;
	}
	/**
	 * 获取：单房差数量
	 */
	public Integer getSingleRoomNumber() {
		return singleRoomNumber;
	}
	/**
	 * 设置：订单总价
	 */
	public void setOrderTotal(BigDecimal orderTotal) {
		this.orderTotal = orderTotal;
	}
	/**
	 * 获取：订单总价
	 */
	public BigDecimal getOrderTotal() {
		return orderTotal;
	}
	/**
	 * 设置：实际成交价格
	 */
	public void setTransactionPrice(BigDecimal transactionPrice) {
		this.transactionPrice = transactionPrice;
	}
	/**
	 * 获取：实际成交价格
	 */
	public BigDecimal getTransactionPrice() {
		return transactionPrice;
	}
	/**
	 * 设置：联系人
	 */
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}
	/**
	 * 获取：联系人
	 */
	public String getContactsName() {
		return contactsName;
	}
	/**
	 * 设置：联系手机号
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * 获取：联系手机号
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：留言信息
	 */
	public void setLeavingMessage(String leavingMessage) {
		this.leavingMessage = leavingMessage;
	}
	/**
	 * 获取：留言信息
	 */
	public String getLeavingMessage() {
		return leavingMessage;
	}
	/**
	 * 设置：状态，0为未提交，1为待处理，2为处理完成
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态，0为未提交，1为待处理，2为处理完成
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：渠道商id
	 */
	public void setChannelMerchantsId(String channelMerchantsId) {
		this.channelMerchantsId = channelMerchantsId;
	}
	/**
	 * 获取：渠道商id
	 */
	public String getChannelMerchantsId() {
		return channelMerchantsId;
	}
	/**
	 * 设置：渠道商名称
	 */
	public void setChannelMerchantsName(String channelMerchantsName) {
		this.channelMerchantsName = channelMerchantsName;
	}
	/**
	 * 获取：渠道商名称
	 */
	public String getChannelMerchantsName() {
		return channelMerchantsName;
	}
	/**
	 * 设置：供应商id
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * 获取：供应商id
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * 设置：供应商名称
	 */
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	/**
	 * 获取：供应商名称
	 */
	public String getSupplierName() {
		return supplierName;
	}
}
