package com.bettem.modules.tourism.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 旅游产品订单出行人员表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
@TableName("tourism_product_order_people")
public class TourismProductOrderPeopleEntity extends BaseEntity {

	/**
	 * 订单id
	 */
	private String orderId;
	/**
	 * 游客姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String cardNumber;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 性别
	 */
	private String sex;
	/**
	 * 出生日期
	 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;
	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 出发日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 设置：订单id
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取：订单id
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 设置：游客姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：游客姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：身份证号
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * 获取：身份证号
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * 设置：手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * 设置：出生日期
	 */
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	/**
	 * 获取：出生日期
	 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	public Date getBirthDate() {
		return birthDate;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
}
