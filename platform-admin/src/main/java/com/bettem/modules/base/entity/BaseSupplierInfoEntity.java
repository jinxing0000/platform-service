package com.bettem.modules.base.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 供应商信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-18 14:50:33
 */
@TableName("base_supplier_info")
public class BaseSupplierInfoEntity extends BaseEntity {

	/**
	 * 供应商名称
	 */
	private String supplierName;
	/**
	 * 法定代表人
	 */
	private String legalPerson;
	/**
	 * 联系人
	 */
	private String contactsName;
	/**
	 * 联系电话
	 */
	private String contactNumber;
	/**
	 * 统一社会信用代码
	 */
	private String creditCode;
	/**
	 * 注册资金
	 */
	private String registeredFunds;
	/**
	 * 供应商地址
	 */
	private String supplierAddress;
	/**
	 * 状态，1为未开通，2为开通，3为禁用
	 */
	private String state;
	/**
	 * 备注
	 */
	private String remarks;

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
	 * 设置：法定代表人
	 */
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	/**
	 * 获取：法定代表人
	 */
	public String getLegalPerson() {
		return legalPerson;
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
	 * 设置：联系电话
	 */
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	/**
	 * 获取：联系电话
	 */
	public String getContactNumber() {
		return contactNumber;
	}
	/**
	 * 设置：统一社会信用代码
	 */
	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}
	/**
	 * 获取：统一社会信用代码
	 */
	public String getCreditCode() {
		return creditCode;
	}
	/**
	 * 设置：注册资金
	 */
	public void setRegisteredFunds(String registeredFunds) {
		this.registeredFunds = registeredFunds;
	}
	/**
	 * 获取：注册资金
	 */
	public String getRegisteredFunds() {
		return registeredFunds;
	}
	/**
	 * 设置：供应商地址
	 */
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	/**
	 * 获取：供应商地址
	 */
	public String getSupplierAddress() {
		return supplierAddress;
	}
	/**
	 * 设置：状态，1为未开通，2为开通，3为禁用
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * 获取：状态，1为未开通，2为开通，3为禁用
	 */
	public String getState() {
		return state;
	}
	/**
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
