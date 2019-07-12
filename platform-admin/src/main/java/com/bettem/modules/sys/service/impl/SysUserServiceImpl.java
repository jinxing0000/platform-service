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


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.annotation.DataFilter;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.sys.dao.SysUserDao;
import com.bettem.modules.sys.entity.SysDeptEntity;
import com.bettem.modules.sys.entity.SysMenuEntity;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysDeptService;
import com.bettem.modules.sys.service.SysMenuService;
import com.bettem.modules.sys.service.SysUserRoleService;
import com.bettem.modules.sys.service.SysUserService;
import com.bettem.modules.sys.shiro.ShiroUtils;
import com.google.code.kaptcha.Constants;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.regex.Pattern;


/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;

	@Autowired
	private SysMenuService sysMenuService;

	@Autowired
	private RedisUtils redisUtils;

	@Value("${bettem.jwtExpiration}")
	private int jwtExpiration;

	@Autowired
	private ShiroTokenUtils shiroTokenUtils;
    //手机号验证码
	public final static String PHONE_PATTERN="^(((13[0-9]{1})|(14[0-9]{1})|(17[0]{1})|(15[0-3]{1})|(15[5-9]{1})|(18[0-9]{1}))+\\d{8})$";

	@Override
	public List<String> queryAllMenuId(String userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		Page<SysUserEntity> page=new Query<SysUserEntity>(params).getPage();
		List<SysUserEntity> list=this.baseMapper.selectByPage(page,params);
		page.setRecords(list);
		return new PageUtils(page);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setSalt(salt);
		user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		this.insert(user);
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysUserEntity user) {
		if(StringUtils.isBlank(user.getPassword())){
			user.setPassword(null);
		}else{
			user.setPassword(ShiroUtils.sha256(user.getPassword(), user.getSalt()));
		}
		this.updateById(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}


	@Override
	public void updatePassword(Map<String, Object> params) {
        String newPassword=(String)params.get("newPassword");
		if(StringUtils.isEmpty(newPassword)){
			throw new RRException(ErrorCodeConstant.IS_EMPTY,"新密码为空！！");
		}
		String oldPassword=(String)params.get("oldPassword");
		if(StringUtils.isEmpty(oldPassword)){
			throw new RRException(ErrorCodeConstant.IS_EMPTY,"原密码为空！！");
		}
		String userId=(String)params.get("userId");
		SysUserEntity user=this.selectOne(new EntityWrapper<SysUserEntity>().eq("user_id",userId));
		newPassword = ShiroUtils.sha256(newPassword,user.getSalt());
		oldPassword = ShiroUtils.sha256(oldPassword,user.getSalt());
		//判断原密码
		if(!user.getPassword().equals(oldPassword)){
			throw new RRException(ErrorCodeConstant.USER_PASSWORD_ERROR,"原密码错误！！");
		}
		user=new SysUserEntity();
		user.setUserId(userId);
		user.setPassword(newPassword);
		this.updateById(user);
		shiroTokenUtils.deleteUserInfo();
    }

	@Override
	public void initPassword() {
		List<SysUserEntity> userList=this.selectList(new EntityWrapper<SysUserEntity>().notLike("username", "admin"));
		for(SysUserEntity sysUserEntity:userList){
			/**
			 * 获取shiro盐
			 */
			String salt=RandomStringUtils.randomAlphanumeric(20);
			sysUserEntity.setSalt(salt);
			/**
			 * 初始化密码：123456
			 */
			sysUserEntity.setPassword(ShiroUtils.sha256("123456", salt));
		}
		this.updateBatchById(userList);
	}

	@Override
	public SysUserEntity login(HttpServletResponse response, Map<String, Object> params,String token) {
		//用户名
		String userName=(String)params.get("userName");
		//密码
		String password=(String)params.get("password");
		//验证码
		String captcha=(String)params.get("captcha");

		if(StringUtils.isEmpty(userName)){
			throw new RRException(ErrorCodeConstant.IS_EMPTY,"用户名为空！！");
		}
		if(StringUtils.isEmpty(password)){
			throw new RRException(ErrorCodeConstant.IS_EMPTY,"密码为空！！");
		}
		if(StringUtils.isEmpty(captcha)){
			throw new RRException(ErrorCodeConstant.IS_EMPTY,"验证码为空！！");
		}
		String kaptcha = JwtTokenUtils.getVerifyCodeByToken(token);
		if(kaptcha == null){
			throw new RRException(ErrorCodeConstant.CAPTCHA_INVALID,"验证码已失效！！");
		}
		if(!captcha.equalsIgnoreCase(kaptcha)){
			throw new RRException(ErrorCodeConstant.CAPTCHA_ERROR,"验证码错误！！");
		}
		//判断是否为手机号
		boolean isPhone = Pattern.compile(PHONE_PATTERN).matcher(userName).matches();
		//查询用户
		SysUserEntity user=this.selectOne(new EntityWrapper<SysUserEntity>().eq(!isPhone,"username",userName).eq(isPhone,"mobile",userName));
		if(user==null){
			throw new RRException(ErrorCodeConstant.USER_NONE,"用户或者手机号不存在！！");
		}
		//登陆用户密码加密
		String loginPassword=ShiroUtils.sha256(password, user.getSalt());
		//判断用户密码是否正确
		if(!loginPassword.equals(user.getPassword())){
			throw new RRException(ErrorCodeConstant.USER_PASSWORD_ERROR,"密码不匹配！！");
		}
		String state=user.getStatus();
		if(!"01".equals(state)){
			throw new RRException(ErrorCodeConstant.ERROR,"该用户已被禁用，请联系管理员！！");
		}
		String deptId=user.getDeptId();
		String userId=user.getUserId();
		//用户部门数据
		SysDeptEntity sysDeptEntity=sysDeptService.selectOne(new EntityWrapper<SysDeptEntity>().eq("dept_id",deptId));
		if(sysDeptEntity!=null){
			user.setDeptName(sysDeptEntity.getName());
			user.setRegionCode(sysDeptEntity.getRegionCode());
		}
		//获取用户所属的角色列表
		List<SysRoleEntity> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
		user.setRoleIdList(roleIdList);
		//获取权限
		List<String> permsList;
		//系统管理员，拥有最高权限
		if(Constant.SUPER_ADMIN.equals(userId)){
			List<SysMenuEntity> menuList = sysMenuService.selectList(null);
			permsList = new ArrayList<>(menuList.size());
			for(SysMenuEntity menu : menuList){
				permsList.add(menu.getPerms());
			}
		}else{
			permsList = this.baseMapper.queryAllPerms(userId);
		}
		//用户权限列表
		Set<String> permsSet = new HashSet<>();
		for(String perms : permsList){
			if(StringUtils.isBlank(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		//用户权限数据
		user.setPermsSet(permsSet);
		//用户菜单数据
		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(user.getUserId());
		user.setMenuList(menuList);
		Map<String,Object> data=new HashedMap();
		data.put("userId",user.getUserId());
		data.put("userName",user.getUsername());
		String sessionToken=JwtTokenUtils.generateToken(data,60*60*jwtExpiration);
		redisUtils.set(RedisKeys.getShiroSessionKey(userId,sessionToken),user,60*60*jwtExpiration);
		response.setHeader("Authorization", sessionToken);
		return user;
	}

	@Override
	public int findCountByParam(Map<String, Object> params) {
		int count=this.selectCount(new EntityWrapper<SysUserEntity>()
				         .eq("mobile".equals(params.get("checkType")),"mobile",params.get("param"))
		                 .eq("userName".equals(params.get("checkType")),"username",params.get("param"))
		              );
		return count;
	}

}
