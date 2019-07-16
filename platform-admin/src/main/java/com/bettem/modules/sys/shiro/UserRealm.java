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

package com.bettem.modules.sys.shiro;


import java.util.*;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.sys.entity.*;
import com.bettem.modules.sys.dao.SysMenuDao;
import com.bettem.modules.sys.dao.SysUserDao;
import com.bettem.modules.sys.entity.VO.SysUserVO;
import com.bettem.modules.sys.service.SysDeptService;
import com.bettem.modules.sys.service.SysUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 上午11:55:49
 */
@Component
public class UserRealm extends AuthorizingRealm {


	// 本地异常日志记录对象
	private static final Logger logger = LoggerFactory
			.getLogger(UserRealm.class);


	@Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysMenuDao sysMenuDao;

	@Autowired
	private SysDeptService sysDeptService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private ShiroTokenUtils shiroTokenUtils;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JwtTokenEntity;
	}

	/**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUserVO sysUserVO = shiroTokenUtils.getUserInfo();
		logger.debug("校验用户权限++++++++++++++++++++++++++++++++++++++++");
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(sysUserVO.getPermsSet());
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		String token = (String) authcToken.getCredentials();
		Map<String,Object> data=JwtTokenUtils.getUserInfoByToken(token);
		//将新的token放入shiro数据中
		data.put("token",token);
		//redis 中校验用户信息是否存在
		if(!shiroTokenUtils.isUserInfoForRedis((String)data.get("userId"),token)){
			throw new RRException(ErrorCodeConstant.SESSION_FAILURE,"会话失效，请重新登陆！！"+"-"+ErrorCodeConstant.SESSION_FAILURE);
		}
		return new SimpleAuthenticationInfo(data, token, "userRealm");
	}
}
