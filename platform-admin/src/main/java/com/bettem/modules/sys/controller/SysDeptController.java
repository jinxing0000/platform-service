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

import com.bettem.common.exception.RRException;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ErrorCodeConstant;
import com.bettem.common.utils.R;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.modules.sys.entity.SysDeptEntity;
import com.bettem.modules.sys.service.SysDeptService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * 部门管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-06-20 15:23:47
 */
@RestController
@RequestMapping("/api/sys/dept/")
public class SysDeptController{
	@Autowired
	private SysDeptService sysDeptService;

	@Autowired
	private ShiroTokenUtils shiroTokenUtils;
	/**
	 * 列表
	 */
	@RequestMapping(value="list",method = RequestMethod.GET)
	@RequiresPermissions({"sys:dept:list"})
	public R list(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
		return R.ok(deptList);
	}

	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping(value = "select",method = RequestMethod.GET)
	@RequiresPermissions({"sys:dept:select"})
	public R select(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(new HashMap<String, Object>());
		//添加一级部门
		if(Constant.SUPER_ADMIN.equals(shiroTokenUtils.getUserId())){
			SysDeptEntity root = new SysDeptEntity();
			root.setDeptId("0");
			root.setName("一级部门");
			root.setParentId("-1");
			deptList.add(root);
		}
		return R.ok().put("deptList", deptList);
	}

	/**
	 * 信息
	 */
	@RequestMapping(value="info",method = RequestMethod.GET)
	@RequiresPermissions("sys:dept:info")
	public R info(@RequestParam String  deptId){
		SysDeptEntity dept = sysDeptService.selectById(deptId);
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDeptEntity dept){
		ValidatorUtils.validateEntity(dept, AddGroup.class);
		dept.setDelFlag(0);
		sysDeptService.insert(dept);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDeptEntity dept){
		sysDeptService.updateById(dept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete",method = RequestMethod.DELETE)
	@RequiresPermissions("sys:dept:delete")
	public R delete(@RequestParam String deptId){
		//判断是否有子部门
		List<String> deptList = sysDeptService.queryDetpIdList(deptId);
		if(deptList.size() > 0){
			return R.error("请先删除子部门");
		}
		sysDeptService.deleteById(deptId);
		return R.ok();
	}
}
