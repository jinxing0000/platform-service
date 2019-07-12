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

package com.bettem.modules.sys.controller;


import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.Assert;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysUserRoleService;
import com.bettem.modules.sys.service.SysUserService;
import com.bettem.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/api/sys/user/")
public class SysUserController{
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private ShiroTokenUtils shiroTokenUtils;
	
	/**
	 * 所有用户列表
	 */
	@RequestMapping(value = "list",method = RequestMethod.GET)
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysUserService.queryPage(params);
		return R.ok(page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping(value ="sessionInfo",method = RequestMethod.GET)
	public R info(){
		return R.ok().put("user", shiroTokenUtils.getUserInfo());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@RequestMapping(value = "password",method = RequestMethod.PUT,produces = "application/json;charset=UTF-8")
	public R password(@RequestBody Map<String, Object> params){
		params.put("userId",shiroTokenUtils.getUserId());
		sysUserService.updatePassword(params);
		return R.ok();
	}
	/**
	 * 用户信息
	 */
	@RequestMapping(value = "info",method = RequestMethod.GET)
	@RequiresPermissions("sys:user:info")
	public R info(@RequestParam String userId){
		SysUserEntity user = sysUserService.selectById(userId);
		//获取用户所属的角色列表
		List<SysRoleEntity> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return R.ok(user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@RequestMapping(value= "save",method = RequestMethod.POST,produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		sysUserService.save(user);
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@RequestMapping(value = "update",method = RequestMethod.PUT,produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);
		sysUserService.update(user);
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@RequestMapping(value = "delete",method = RequestMethod.DELETE)
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody String[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, shiroTokenUtils.getUserId())){
			return R.error("当前用户不能删除");
		}
		sysUserService.deleteBatchIds(Arrays.asList(userIds));
		return R.ok();
	}

	/**
	 *  条件查询用户个数
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "findCountByParam",method = RequestMethod.GET)
	@RequiresPermissions("sys:user:findCountByParam")
	@SysLog("条件查询用户个数")
	public R findCountByParam(@RequestParam Map<String, Object> params){
		int count = sysUserService.findCountByParam(params);
		return R.ok().put("count", count);
	}
}
