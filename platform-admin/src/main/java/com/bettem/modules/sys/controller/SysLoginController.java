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
import com.bettem.common.utils.*;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysUserService;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.bettem.modules.sys.shiro.ShiroUtils;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 登录相关
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月10日 下午1:15:31
 */
@Controller
@RequestMapping("/api/sys/")
public class SysLoginController{
	@Autowired
	private Producer producer;

    @Autowired
    private SysUserService sysUserService;

	@Autowired
	private ShiroTokenUtils shiroTokenUtils;

	@Value("${bettem.isOpenSms}")
	private boolean isOpenSms;
	/**
	 * 获取验证码
	 * @param response
	 */
	@RequestMapping(value = "getVerifyCode" ,method = RequestMethod.GET)
	@ResponseBody
	public R getVerify(HttpServletRequest request, HttpServletResponse response){
		try {
			RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
			String base64Code=randomValidateCode.getRandcode(response);//输出验证码图片方法
			return R.ok().put("img",base64Code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
    /**
     * @Param [response]
     * @Return: com.bettem.common.utils.R
     * @Decription: 获取手机验证码
     * @CreateDate: Created in 2018/10/24 9:48
     * @Author: 颜金星
     */
    @RequestMapping("getMobileVerifyCode")
    @ResponseBody
	public R getVerifyCode(HttpServletResponse response,@RequestParam("phone") String phone){
		response.setHeader("Cache-Control", "no-store, no-cache");
		String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY+"_"+phone);
		if(kaptcha!=null){
			throw new RRException("手机验证码15分钟内有效，请勿重复发送！！");
		}
		RandomValidateCodeUtil randomValidateCodeUtil=new RandomValidateCodeUtil();
		String text=randomValidateCodeUtil.getMobileVerifyCode();
		if(isOpenSms){
			SMSUtils.sendSMSVerifyCode(phone,text,Constant.SMS_TEMPLATE_LOGIN);
		}
		//保存到shiro session
		Session Session=SecurityUtils.getSubject().getSession();
		Session.setAttribute(Constants.KAPTCHA_SESSION_KEY+"_"+phone, text);
		//设置session过期事件
		//Session.setTimeout(1000*60);
		Map<String,Object> data=new HashMap<>();
		data.put("verifyCode",text);
		data.put("state",isOpenSms);
		return R.ok(data);
	}
    /**
     * @Param [response, username]
     * @Return: com.bettem.common.utils.R
     * @Decription: 根据用户名获取手机号
     * @CreateDate: Created in 2018/10/24 10:17
     * @Author: 颜金星
     */
    @RequestMapping("getUserInfoByUserName")
    @ResponseBody
    public R getUserInfoByUserName(HttpServletResponse response,@RequestParam("username") String username,@RequestParam("isPhone") boolean isPhone){
        SysUserEntity sysUserEntity=sysUserService.selectOne(new EntityWrapper<SysUserEntity>().eq(!isPhone,"username",username).eq(isPhone,"mobile",username));
        return R.ok().put("sysUserEntity",sysUserEntity);
    }
	
	/**
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	//@SysLog("用户登陆")
	public R login(@RequestBody Map<String, Object> params, HttpServletResponse response,HttpServletRequest request) {
		String token=request.getHeader("verifyCodeToken");
		sysUserService.login(response,params,token);
		return R.ok();
	}
	
	/**
	 * 退出登陆
	 */
	@ResponseBody
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	public R logout() {
		shiroTokenUtils.deleteUserInfo();
		return R.ok();
	}
	/**
	 * @Param []
	 * @Return: com.bettem.common.utils.R
	 * @Decription: 获取按钮权限
	 * @CreateDate: Created in 2019/3/14 18:50
	 * @Author: 颜金星
	 */
	@ResponseBody
	@RequestMapping(value = "getButtonAuthority", method = RequestMethod.GET)
	@SysLog("用户获取按钮权限")
	public R getButtonAuthority() {
		//获取用户信息
		SysUserEntity sysUserEntity=shiroTokenUtils.getUserInfo();
		Set<String> permsSet=sysUserEntity.getPermsSet();
		Map<String,Boolean> permsMap=new HashedMap();
		for(String perm:permsSet){
			permsMap.put(perm,true);
		}
		return R.ok().put("permsMap",permsMap);
	}
	
}
