/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bettem.modules.sys.entity;


import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:28:55
 */
@TableName("sys_user")
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
	private String userId;

	/**
	 * 用户名
	 */
	@NotBlank(message="用户名不能为空", groups = {AddGroup.class,})
	private String username;

	/**
	 * 密码
	 */
	@NotBlank(message="密码不能为空", groups = AddGroup.class)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@JSONField(serialize=false)
	private String password;

	/**
	 * 昵称
	 */
	@NotBlank(message="真实姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String nickName;

	/**
	 * 盐
	 */
	@JSONField(serialize=false)
	private String salt;

	/**
	 * 邮箱
	 */
//	@NotBlank(message="邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
//	@Email(message="邮箱格式不正确", groups = {AddGroup.class, UpdateGroup.class})
	private String email;

	/**
	 * 手机号
	 */
	@NotBlank(message="手机号不能为空", groups = AddGroup.class)
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	private String status;
	
	/**
	 * 角色ID列表
	 */
	@TableField(exist=false)
	private List<SysRoleEntity> roleIdList;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 部门ID
	 */
	@NotNull(message="部门不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String deptId;

	/**
	 * 部门名称
	 */
	@TableField(exist=false)
	private String deptName;

	/**
	 *  权限set
	 */
	@TableField(exist=false)
	private Set<String> permsSet=new HashSet<>();
	/**
	 *   用户菜单数据
	 */
	@TableField(exist=false)
	private List<SysMenuEntity> menuList=new ArrayList<>();

	//区域编码
	@TableField(exist=false)
	private String regionCode;
	/**
	 *  职位名称
	 */
	private String position;
	/**
	 *  身份证号
	 */
	private String cardNumber;

	/**
	 * 设置：List<SysMenuEntity>
	 * @param userId 
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 获取：
	 * @return Long
	 */
	public String getUserId() {
		return userId;
	}
	
	/**
	 * 设置：用户名
	 * @param username 用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：用户名
	 * @return String
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 设置：密码
	 * @param password 密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 * @return String
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * 设置：邮箱
	 * @param email 邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取：邮箱
	 * @return String
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * 设置：手机号
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 获取：手机号
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * 设置：创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 获取：创建时间
	 * @return Date
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public List<SysRoleEntity> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<SysRoleEntity> roleIdList) {
		this.roleIdList = roleIdList;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setPermsSet(Set<String> permsSet) {
		this.permsSet = permsSet;
	}

	public Set<String> getPermsSet() {
		return permsSet;
	}

	public void setMenuList(List<SysMenuEntity> menuList) {
		this.menuList = menuList;
	}

	public List<SysMenuEntity> getMenuList() {
		return menuList;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPosition() {
		return position;
	}
}
