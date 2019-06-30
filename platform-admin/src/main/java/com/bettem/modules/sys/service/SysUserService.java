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

package com.bettem.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.sys.entity.SysUserEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:43:39
 */
public interface SysUserService extends IService<SysUserEntity> {

	PageUtils queryPage(Map<String, Object> params);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<String> queryAllMenuId(String userId);
	
	/**
	 * 保存用户
	 */
	void save(SysUserEntity user);
	
	/**
	 * 修改用户
	 */
	void update(SysUserEntity user);

	/**
	 * 修改密码
	 */
	void updatePassword(Map<String, Object> params);
    /**
     * @Param
     * @Return: 
     * @Decription:  初始化密码
     * @CreateDate: Created in 2018/12/19 18:17
     * @Author: 颜金星
     */
	void initPassword();
    /**
     * @Param
     * @Return: 
     * @Decription:  用户执行登陆操作
     * @CreateDate: Created in 2019/3/21 11:37
     * @Author: 颜金星
     */
	SysUserEntity login(HttpServletResponse response,Map<String,Object> params,String token);

	/**
	 * 条件查询用户个数
	 * @param params
	 * @return
	 */
	int findCountByParam(Map<String,Object> params);
}
