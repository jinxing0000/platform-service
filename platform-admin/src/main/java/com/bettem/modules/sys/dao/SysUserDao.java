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

package com.bettem.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.entity.VO.SysUserVO;

import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:34:11
 */
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(String userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	/**
	 * @Param
	 * @Return: 
	 * @Decription:  按照条件分页查询数据
	 * @CreateDate: Created in 2018/12/18 16:56
	 * @Author: 颜金星
	 */

	List<SysUserVO> selectByPage(Page<SysUserVO> page, Map<String,Object> params);

	/**
	 * 按照userId查询用户信息
	 * @param userId
	 * @return
	 */
	SysUserVO selectUserById(String userId);

	/**
	 * 按照用户名或者手机号查询
	 * @param userName
	 * @param isMobile
	 * @return
	 */
	SysUserVO selectByUserNameOrMobile(String userName,boolean isMobile);

	/**
	 * 删除用户信息
	 * @param userIds
	 */
	void deleteUserByUserIds(String[] userIds);

}
