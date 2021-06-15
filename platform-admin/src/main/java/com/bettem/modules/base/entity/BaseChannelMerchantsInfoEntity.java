package com.bettem.modules.base.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 渠道商信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-04 18:43:12
 */
@TableName("base_channel_merchants_info")
public class BaseChannelMerchantsInfoEntity extends BaseEntity {

	/**
	 * 渠道商名称
	 */
	@NotBlank(message="渠道商名称不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String channelName;
	/**
	 * 联系人
	 */
	@NotBlank(message="联系人不能为空！！", groups = {AddGroup.class,UpdateGroup.class})
	private String contactsName;
	/**
	 * 联系电话
	 */
	@NotBlank(message="联系电话不能为空！！", groups = {AddGroup.class,UpdateGroup.class})
	private String contactNumber;
	/**
	 * 微信号
	 */
	@NotBlank(message="微信号不能为空！！", groups = {AddGroup.class,UpdateGroup.class})
	private String wechatNumber;
	/**
	 * 身份证号
	 */
	@NotBlank(message="身份证号不能为空！！", groups = {AddGroup.class,UpdateGroup.class})
	private String cardNumber;
	/**
	 * 微信小程序唯一识别码
	 */
	@NotBlank(message="openId不能为空！！", groups = {AddGroup.class,})
	private String openId;
	/**
	 * 头像地址
	 */
	@NotBlank(message="头像地址不能为空！！", groups = {AddGroup.class,UpdateGroup.class})
	private String headPortraitUrl;
	/**
	 * 级别，后期使用
	 */
	private String level;
	/**
	 * 状态，1为未开通，2为开通，3为禁用
	 */
	private String state;
	/**
	 * 二维码地址
	 */
	private String qrcodeUrl;
	/**
	 * 父级用户id
	 */
	private String parentUserId;
	/**
	 * 查询下级节点
	 */
	@TableField(exist=false)
	private List<BaseChannelMerchantsInfoEntity> children=new ArrayList<>();

	public List<BaseChannelMerchantsInfoEntity> getChildren() {
		return children;
	}

	public void setChildren(List<BaseChannelMerchantsInfoEntity> children) {
		this.children = children;
	}

	public String getParentUserId() {
		return parentUserId;
	}

	public void setParentUserId(String parentUserId) {
		this.parentUserId = parentUserId;
	}

	public String getQrcodeUrl() {
		return qrcodeUrl;
	}

	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}

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
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：微信小程序唯一识别码
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：头像地址
	 */
	public void setHeadPortraitUrl(String headPortraitUrl) {
		this.headPortraitUrl = headPortraitUrl;
	}
	/**
	 * 获取：头像地址
	 */
	public String getHeadPortraitUrl() {
		return headPortraitUrl;
	}
	/**
	 * 设置：级别，后期使用
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	/**
	 * 获取：级别，后期使用
	 */
	public String getLevel() {
		return level;
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
