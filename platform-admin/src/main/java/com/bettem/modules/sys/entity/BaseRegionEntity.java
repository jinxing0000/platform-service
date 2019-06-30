package com.bettem.modules.sys.entity;

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
import java.util.Date;
import java.util.List;

/**
 * 区域信息表
 * 
 * @author 张瑞
 * @email sunlightcs@gmail.com
 * @date 2019-04-11 13:07:34
 */
@TableName("BASE_REGION")
public class BaseRegionEntity extends BaseEntity {

	/**
	 * 区域编号
	 */
	@NotBlank(message="区域编号不能为空！！！", groups = {AddGroup.class, UpdateGroup.class})
	private String regionCode;
	/**
	 * 区域名称
	 */
	@NotBlank(message="区域名称不能为空！！！", groups = {AddGroup.class, UpdateGroup.class})
	private String regionName;
	/**
	 * 上级编号
	 */
	private String parentId;
	/**
	 * 区域级别
	 */
	private int regionLevel;
	/**
	 * 备注
	 */
	private String remarks;
	/**
	 * ztree属性
	 */
	@TableField(exist=false)
	private Boolean isParent=true;

	@TableField(exist=false)
	private Boolean open;
	@TableField(exist=false)
	private List<?> list;


    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	/**
	 * 设置：区域编号
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	/**
	 * 获取：区域编号
	 */
	public String getRegionCode() {
		return regionCode;
	}
	/**
	 * 设置：区域名称
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	/**
	 * 获取：区域名称
	 */
	public String getRegionName() {
		return regionName;
	}
	/**
	 * 设置：上级编号
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：上级编号
	 */
	public String getParentId() {
		return parentId;
	}
	/**
	 * 设置：区域级别
	 */
	public void setRegionLevel(int regionLevel) {
		this.regionLevel = regionLevel;
	}
	/**
	 * 获取：区域级别
	 */
	public int getRegionLevel() {
		return regionLevel;
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
