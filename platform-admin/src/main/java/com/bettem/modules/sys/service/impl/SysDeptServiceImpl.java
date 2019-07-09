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

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.annotation.DataFilter;
import com.bettem.common.utils.Constant;
import com.bettem.modules.sys.dao.SysDeptDao;
import com.bettem.modules.sys.entity.SysDeptEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.entity.SysUserRoleEntity;
import com.bettem.modules.sys.service.SysDeptService;
import com.bettem.modules.sys.service.SysUserRoleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service("sysDeptService")
public class SysDeptServiceImpl extends ServiceImpl<SysDeptDao, SysDeptEntity> implements SysDeptService {

	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Override
	public List<SysDeptEntity> queryList(Map<String, Object> params){
		List<SysDeptEntity> resultDeptTreeList=new ArrayList<>();
		List<SysDeptEntity> deptList =
			this.selectList(new EntityWrapper<SysDeptEntity>()
			.addFilterIfNeed(params.get(Constant.SQL_FILTER) != null, (String)params.get(Constant.SQL_FILTER))
			.eq("DEL_FLAG",Constant.DELETE_STATE_NO)
		    .orderBy("ORDER_NUM ")
			);
        for(SysDeptEntity dept:deptList){
        	if("0".equals(dept.getParentId())){
				List<SysDeptEntity> resultList=getLowerDept(deptList,dept.getDeptId());
				dept.setChildren(resultList);
				resultDeptTreeList.add(dept);
			}
		}
		return resultDeptTreeList;
	}

	@Override
	public List<String> queryDetpIdList(String parentId) {
		return baseMapper.queryDetpIdList(parentId);
	}

	@Override
	public List<String> getSubDeptIdList(String deptId){
		//部门及子部门ID列表
		List<String> deptIdList = new ArrayList<>();

		//获取子部门ID
		List<String> subIdList = queryDetpIdList(deptId);
		getDeptTreeList(subIdList, deptIdList);

		return deptIdList;
	}

	@Override
	public void saveBatch() {
		String str="莲花县、修水县、赣  县、上犹县、安远县、宁都县、于都县、兴国县、会昌县、寻乌县、石城县、瑞金市、南康市、吉安县、遂川县、万安县、永新县、井冈山市、乐安县、广昌县、上饶县、横峰县、余干县、鄱阳县";
		String dept[]= str.split("、");
		SysDeptEntity sysDeptEntity=null;
		List<SysDeptEntity> deptList=new ArrayList<>();
		for(int i=11;i<dept.length;i++){
			sysDeptEntity=new SysDeptEntity();
			sysDeptEntity.setName(dept[i]);
			sysDeptEntity.setParentId("0c8a200343464a6684b9d9950b859e17");
			sysDeptEntity.setOrderNum(i+1);
			deptList.add(sysDeptEntity);
		}
		if(deptList.size()>0){
           this.insertBatch(deptList);
		}
	}

	/**
	 * 递归
	 */
	private void getDeptTreeList(List<String> subIdList, List<String> deptIdList){
		for(String deptId : subIdList){
			List<String> list = queryDetpIdList(deptId);
			if(list.size() > 0){
				getDeptTreeList(list, deptIdList);
			}
			deptIdList.add(deptId);
		}
	}
	/**
	 * 递归寻找下级部门
	 */
	private List<SysDeptEntity> getLowerDept(List<SysDeptEntity> deptList,String deptId){
		List<SysDeptEntity> resultList=new ArrayList<>();
		for(SysDeptEntity dept:deptList){
			if(deptId.equals(dept.getParentId())){
				List<SysDeptEntity> list=this.getLowerDept(deptList,dept.getDeptId());
				dept.setChildren(list);
				resultList.add(dept);
			}
		}
		return resultList;
	}
}
