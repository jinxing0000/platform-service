package com.bettem.modules.sys.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.base.entity.BaseEntity;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;

import javax.validation.constraints.NotBlank;

/**
 * 数据字典表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-30 10:48:34
 */
@TableName("sys_dic")
public class SysDicEntity extends BaseEntity {

	/**
	 * 字典code
	 */
	@NotBlank(message="字典code不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String code;
	/**
	 * 字典value
	 */
	@NotBlank(message="字典value不能为空！！", groups = {AddGroup.class, UpdateGroup.class})
	private String value;
	/**
	 * 字典排序号
	 */
	private int sort;

	/**
	 * 父级字典code
	 */
	private String parentCode;
	/**
	 * 备注
	 */
	@JSONField(serialize=false)
	private String remarks;

	/**
	 * 设置：字典code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：字典code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：字典value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * 获取：字典value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * 设置：父级字典code
	 */
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	/**
	 * 获取：父级字典code
	 */
	public String getParentCode() {
		return parentCode;
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
	@JSONField(serialize=false)
	public String getRemarks() {
		return remarks;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getSort() {
		return sort;
	}
}
