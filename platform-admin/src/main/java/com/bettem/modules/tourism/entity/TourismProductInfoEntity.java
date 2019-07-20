package com.bettem.modules.tourism.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 旅游产品信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
@TableName("tourism_product_info")
public class TourismProductInfoEntity extends BaseEntity {

	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 产品引导图地址
	 */
	private String productGuidePicUrl;
	/**
	 * 线路类型，1为一日游，2为国内游，3为赴台游，4为出境游
	 */
	private String lineType;
	/**
	 * 出发城市
	 */
	private String startingCity;
	/**
	 * 行程天数
	 */
	private Integer tripDays;
	/**
	 * 行程晚数
	 */
	private Integer tripNightNum;
	/**
	 * 日期范围开始
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;
	/**
	 * 日期范围结束
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	/**
	 * 成人价格
	 */
	private BigDecimal adultPrice;
	/**
	 * 儿童价格
	 */
	private BigDecimal childrenPrice;
	/**
	 * 单房差
	 */
	private BigDecimal singleRoomPrice;
	/**
	 * 产品特色
	 */
	private String productCharacteristic;
	/**
	 * 行程介绍
	 */
	private String travelInfo;
	/**
	 * 费用包含
	 */
	private String costInclusion;
	/**
	 * 费用不含
	 */
	private String costExcluded;
	/**
	 * 预定须知
	 */
	private String reservationNotes;
	/**
	 * 退改规则
	 */
	private String returnRules;
	/**
	 * 咨询电话
	 */
	private String contactNumber;
	/**
	 * 供应商id
	 */
	private String supplierId;
	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 状态，0为未发布，1为发布，2为下架
	 */
	private String state;

	/**
	 * 设置：产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * 获取：产品名称
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * 设置：产品引导图地址
	 */
	public void setProductGuidePicUrl(String productGuidePicUrl) {
		this.productGuidePicUrl = productGuidePicUrl;
	}
	/**
	 * 获取：产品引导图地址
	 */
	public String getProductGuidePicUrl() {
		return productGuidePicUrl;
	}
	/**
	 * 设置：线路类型，1为一日游，2为国内游，3为赴台游，4为出境游
	 */
	public void setLineType(String lineType) {
		this.lineType = lineType;
	}
	/**
	 * 获取：线路类型，1为一日游，2为国内游，3为赴台游，4为出境游
	 */
	public String getLineType() {
		return lineType;
	}
	/**
	 * 设置：出发城市
	 */
	public void setStartingCity(String startingCity) {
		this.startingCity = startingCity;
	}
	/**
	 * 获取：出发城市
	 */
	public String getStartingCity() {
		return startingCity;
	}
	/**
	 * 设置：行程天数
	 */
	public void setTripDays(Integer tripDays) {
		this.tripDays = tripDays;
	}
	/**
	 * 获取：行程天数
	 */
	public Integer getTripDays() {
		return tripDays;
	}
	/**
	 * 设置：行程晚数
	 */
	public void setTripNightNum(Integer tripNightNum) {
		this.tripNightNum = tripNightNum;
	}
	/**
	 * 获取：行程晚数
	 */
	public Integer getTripNightNum() {
		return tripNightNum;
	}
	/**
	 * 设置：日期范围开始
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * 获取：日期范围开始
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * 设置：日期范围结束
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * 获取：日期范围结束
	 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getEndDate() {
		return endDate;
	}
	/**
	 * 设置：成人价格
	 */
	public void setAdultPrice(BigDecimal adultPrice) {
		this.adultPrice = adultPrice;
	}
	/**
	 * 获取：成人价格
	 */
	public BigDecimal getAdultPrice() {
		return adultPrice;
	}
	/**
	 * 设置：儿童价格
	 */
	public void setChildrenPrice(BigDecimal childrenPrice) {
		this.childrenPrice = childrenPrice;
	}
	/**
	 * 获取：儿童价格
	 */
	public BigDecimal getChildrenPrice() {
		return childrenPrice;
	}
	/**
	 * 设置：单房差
	 */
	public void setSingleRoomPrice(BigDecimal singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}
	/**
	 * 获取：单房差
	 */
	public BigDecimal getSingleRoomPrice() {
		return singleRoomPrice;
	}
	/**
	 * 设置：产品特色
	 */
	public void setProductCharacteristic(String productCharacteristic) {
		this.productCharacteristic = productCharacteristic;
	}
	/**
	 * 获取：产品特色
	 */
	public String getProductCharacteristic() {
		return productCharacteristic;
	}
	/**
	 * 设置：行程介绍
	 */
	public void setTravelInfo(String travelInfo) {
		this.travelInfo = travelInfo;
	}
	/**
	 * 获取：行程介绍
	 */
	public String getTravelInfo() {
		return travelInfo;
	}
	/**
	 * 设置：费用包含
	 */
	public void setCostInclusion(String costInclusion) {
		this.costInclusion = costInclusion;
	}
	/**
	 * 获取：费用包含
	 */
	public String getCostInclusion() {
		return costInclusion;
	}
	/**
	 * 设置：费用不含
	 */
	public void setCostExcluded(String costExcluded) {
		this.costExcluded = costExcluded;
	}
	/**
	 * 获取：费用不含
	 */
	public String getCostExcluded() {
		return costExcluded;
	}
	/**
	 * 设置：预定须知
	 */
	public void setReservationNotes(String reservationNotes) {
		this.reservationNotes = reservationNotes;
	}
	/**
	 * 获取：预定须知
	 */
	public String getReservationNotes() {
		return reservationNotes;
	}
	/**
	 * 设置：退改规则
	 */
	public void setReturnRules(String returnRules) {
		this.returnRules = returnRules;
	}
	/**
	 * 获取：退改规则
	 */
	public String getReturnRules() {
		return returnRules;
	}
	/**
	 * 设置：咨询电话
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * 获取：咨询电话
	 */
	public String getContactNumber() {
		return contactNumber;
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
	/**
	 * 设置：状态，0为未发布，1为发布，2为下架
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态，0为未发布，1为发布，2为下架
	 */
	public String getState() {
		return state;
	}
}
