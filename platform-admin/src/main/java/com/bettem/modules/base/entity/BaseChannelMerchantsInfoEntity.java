package com.bettem.modules.base.entity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

/**
 * 渠道商信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-04 11:43:19
 */
@TableName("base_channel_merchants_info")
public class BaseChannelMerchantsInfoEntity extends BaseEntity {

	/**
	 * 渠道商名称
	 */
	private String channelName;
	/**
	 * 联系人
	 */
	private String contactsName;
	/**
	 * 联系电话
	 */
	private String contactNumber;
	/**
	 * 微信号
	 */
	private String wechatNumber;
	/**
	 * 身份证号
	 */
	private String cardNumber;
	/**
	 * 微信小程序唯一识别码
	 */
	private String openid;
	/**
	 * 状态，1为未开通，2为开通，3为禁用
	 */
	private String state;

	/**
	 * 设置：渠道商名称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	/**
	 * 获取：渠道商名称
	 */
	public String getChannelName() {
		return channelName;
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
	 * 设置：微信号
	 */
	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}
	/**
	 * 获取：微信号
	 */
	public String getWechatNumber() {
		return wechatNumber;
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
	 * 设置：微信小程序唯一识别码
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * 获取：微信小程序唯一识别码
	 */
	public String getOpenid() {
		return openid;
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
}
