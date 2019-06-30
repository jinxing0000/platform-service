package com.bettem.common.utils;

import com.bettem.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/21 17:16 <br>
 * @Author: 颜金星
 */
@Component
public class ShiroTokenUtils {

    @Autowired
    private RedisUtils redisUtils;
    /**
     * @Param []
     * @Return: java.lang.String
     * @Decription: 获取用户id
     * @CreateDate: Created in 2019/3/21 17:22
     * @Author: 颜金星
     */
    public String getUserId(){
        Map<String,Object>  data=this.getJwtTokenData();
        if(data==null){
            return null;
        }
        return (String)data.get("userId");
    }
    /**
     * @Param []
     * @Return: java.lang.String
     * @Decription: 获取用户名
     * @CreateDate: Created in 2019/3/21 17:23
     * @Author: 颜金星
     */
    public String getUserName(){
        Map<String,Object>  data=this.getJwtTokenData();
        if(data==null){
            return null;
        }
        return (String)data.get("userName");
    }
    /**
     * @Param []
     * @Return: com.bettem.modules.sys.entity.SysUserEntity
     * @Decription:  获取用户信息
     * @CreateDate: Created in 2019/3/21 17:26
     * @Author: 颜金星
     */
    public SysUserEntity getUserInfo(){
        Map<String,Object> data=this.getJwtTokenData();
        String userId=(String)data.get("userId");
        String token=(String)data.get("token");
        SysUserEntity sysUserEntity=redisUtils.get(RedisKeys.getShiroSessionKey(userId,token),SysUserEntity.class);
        return sysUserEntity;
    }
    /**
     * @Param []
     * @Return: java.util.Map<java.lang.String,java.lang.Object>
     * @Decription:  获取shiro 权限认证中的数据
     * @CreateDate: Created in 2019/3/21 17:20
     * @Author: 颜金星
     */
    public Map<String,Object> getJwtTokenData(){
        return (Map<String,Object>)SecurityUtils.getSubject().getPrincipal();
    }
    /**
     * @Param [userId]
     * @Return: boolean
     * @Decription:  判断用户信息是否存在redis中
     * @CreateDate: Created in 2019/3/22 9:11
     * @Author: 颜金星
     */
    public boolean isUserInfoForRedis(String userId,String token){
        return redisUtils.hasKey(RedisKeys.getShiroSessionKey(userId,token));
    }
    /**
     * @Param []
     * @Return: void
     * @Decription:  将用户信息从redis中删除
     * @CreateDate: Created in 2019/3/22 9:18
     * @Author: 颜金星
     */
    public void deleteUserInfo(){
        Map<String,Object> data=this.getJwtTokenData();
        String token=(String)data.get("token");
        String userId=(String)data.get("userId");
        redisUtils.delete(RedisKeys.getShiroSessionKey(userId,token));
    }
}
