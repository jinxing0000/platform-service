package com.bettem.modules.base.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 渠道商出行人信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-04 18:43:12
 */
@TableName("base_channel_merchants_people")
public class BaseChannelMerchantsPeopleEntity extends BaseEntity {

	/**
	 * 渠道商id
	 */
	private String channelId;
	/**
	 * 游客姓名
	 */
	@NotBlank(message="游客姓名不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	/**
	 * 身份证号
	 */
	@NotBlank(message="身份证号不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String cardNumber;
	/**
	 * 手机号码
	 */
	@NotBlank(message="手机号码不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String mobile;
	/**
	 * 性别
	 */
	@NotBlank(message="性别不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
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
	 * 设置：渠道商id
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	/**
	 * 获取：渠道商id
	 */
	public String getChannelId() {
		return channelId;
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
