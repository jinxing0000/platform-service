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

package com.bettem.modules.sys.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.MapUtils;
import com.bettem.modules.sys.dao.SysMenuDao;
import com.bettem.modules.sys.entity.SysMenuEntity;
import com.bettem.modules.sys.service.SysMenuService;
import com.bettem.modules.sys.service.SysRoleMenuService;
import com.bettem.modules.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysMenuService")
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenuEntity> implements SysMenuService {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	
	@Override
	public List<SysMenuEntity> queryListParentId(String parentId, List<String> menuIdList) {
		List<SysMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		
		List<SysMenuEntity> userMenuList = new ArrayList<>();
		for(SysMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<SysMenuEntity> queryListParentId(String parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<SysMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<SysMenuEntity> getUserMenuList(String userId) {
		Map<String,Object> params=new HashMap<>();
		//系统管理员，拥有最高权限，判断不是系统管理员
		if(!Constant.SUPER_ADMIN.equals(userId)){
			params.put("userId",userId);
		}
		params.put("type","menu");
		List<SysMenuEntity> menuList=this.baseMapper.queryListMenu(params);
		List<SysMenuEntity> menuTree=new ArrayList<>();
		for(SysMenuEntity sysMenu:menuList){
			String parentId=sysMenu.getParentId();
			//判断是否为根节点
			if("0".equals(parentId)){
				List<SysMenuEntity> childrenList=setChildren(sysMenu.getMenuId(),menuList);
				sysMenu.setChildren(childrenList);
				menuTree.add(sysMenu);
			}
		}
		return menuTree;
	}

	@Override
	public void delete(String menuId){
		//删除菜单
		this.deleteById(menuId);
		//删除菜单与角色关联
		sysRoleMenuService.deleteByMap(new MapUtils().put("menu_id", menuId));
	}

	/**
	 * 获取所有菜单列表
	 */
	private List<SysMenuEntity> getAllMenuList(List<String> menuIdList){
		//查询根菜单列表
		List<SysMenuEntity> menuList = queryListParentId("0", menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);
		return menuList;
	}

	/**
	 * 递归
	 */
	private List<SysMenuEntity> getMenuTreeList(List<SysMenuEntity> menuList, List<String> menuIdList){
		List<SysMenuEntity> subMenuList = new ArrayList<SysMenuEntity>();

		for(SysMenuEntity entity : menuList){
			//目录
			if(entity.getType() == Constant.MenuType.CATALOG.getValue()){
				entity.setChildren(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}
		return subMenuList;
	}
	/**
	 * 递归生成菜单Tree
	 * @param id
	 * @param menuVOList
	 * @return
	 */
	public List<SysMenuEntity> setChildren(String id, List<SysMenuEntity> menuVOList){
		List<SysMenuEntity> menuList=new ArrayList<>();
		/**
		 * 循环菜单
		 */
		for(SysMenuEntity sysMenuVO:menuVOList){
			//有下级子节点
			if(id.equals(sysMenuVO.getParentId())){
				List<SysMenuEntity> childrenList=setChildren(sysMenuVO.getMenuId(),menuVOList);
				sysMenuVO.setChildren(childrenList);
				menuList.add(sysMenuVO);
			}
		}
		return menuList;
	}
}
