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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bettem.common.annotation.SysLog;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.R;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.modules.sys.entity.SysMenuEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysMenuService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统菜单
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午9:58:15
 */
@RestController
@RequestMapping("/api/sys/menu/")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private ShiroTokenUtils shiroTokenUtils;

	/**
	 * 导航菜单
	 */
	@RequestMapping(value = "nav",method = RequestMethod.GET)
	public R nav(){
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(shiroTokenUtils.getUserId());
		return R.ok().put("menuList", menuList);
	}
	
	/**
	 * 所有菜单列表
	 */
	@RequestMapping(value = "list",method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:list")
	public List<SysMenuEntity> list(){
		List<SysMenuEntity> menuList = sysMenuService.queryMenuTreeList();
		return menuList;
	}
	
	/**
	 * 菜单信息
	 */
	@RequestMapping(value = "info",method = RequestMethod.GET)
	@RequiresPermissions("sys:menu:info")
	public R info(@RequestParam String menuId){
		SysMenuEntity menu = sysMenuService.selectById(menuId);
		return R.ok().put("menu", menu);
	}
	
	/**
	 * 保存
	 */
	@SysLog("保存菜单")
	@RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:menu:save")
	public R save(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		sysMenuService.insert(menu);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@SysLog("修改菜单")
	@RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:menu:update")
	public R update(@RequestBody SysMenuEntity menu){
		//数据校验
		verifyForm(menu);
		sysMenuService.updateById(menu);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@SysLog("删除菜单")
	@RequestMapping(value = "delete",method = RequestMethod.DELETE)
	@RequiresPermissions("sys:menu:delete")
	public R delete(@RequestParam String menuId){
		//判断是否有子菜单或按钮
		List<SysMenuEntity> menuList = sysMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.error("请先删除子菜单或按钮！！");
		}
		sysMenuService.delete(menuId);
		return R.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysMenuEntity menu){
		if(StringUtils.isBlank(menu.getName())){
			throw new RRException("菜单名称不能为空！！");
		}
		if(menu.getParentId() == null){
			throw new RRException("上级菜单不能为空！！");
		}
		//菜单
		if(menu.getType() == Constant.MenuType.MENU.getValue()){
			if(StringUtils.isBlank(menu.getPath())){
				throw new RRException("菜单URL不能为空！！");
			}
		}
		//上级菜单类型
		String  parentType = Constant.MenuType.CATALOG.getValue();
		if(!"0".equals(menu.getParentId())){
			SysMenuEntity parentMenu = sysMenuService.selectById(menu.getParentId());
			parentType = parentMenu.getType();
		}
		//目录、菜单
		if(Constant.MenuType.CATALOG.getValue().equals(menu.getType() ) ||
				 Constant.MenuType.MENU.getValue().equals(menu.getType())){
			if(!Constant.MenuType.CATALOG.getValue().equals(parentType)){
				throw new RRException("上级菜单只能为目录类型！！");
			}
			return ;
		}
		//按钮
		if(menu.getType() == Constant.MenuType.BUTTON.getValue()){
			if(parentType != Constant.MenuType.MENU.getValue()){
				throw new RRException("上级菜单只能为菜单类型！！");
			}
			return ;
		}
	}
}
