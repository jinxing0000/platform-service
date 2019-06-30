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

package com.bettem.common.aspect;


import com.bettem.common.annotation.DataFilter;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.modules.sys.entity.SysRoleEntity;
import com.bettem.modules.sys.entity.SysUserEntity;
import com.bettem.modules.sys.service.SysUserRoleService;
import com.bettem.modules.sys.shiro.ShiroUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 数据过滤，切面处理类
 *
 * @author Mark sunlightcs@gmail.com
 * @since 3.0.0 2017-09-17
 */
@Aspect
@Component
public class DataFilterAspect {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(DataFilterAspect.class);

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Pointcut("@annotation(com.bettem.common.annotation.DataFilter)")
    public void dataFilterCut() {

    }

    @Before("dataFilterCut()")
    public void dataFilter(JoinPoint point) throws Throwable {
        Object params = point.getArgs()[0];
        if(params != null && params instanceof Map){
            SysUserEntity user = shiroTokenUtils.getUserInfo();
            //如果不是超级管理员，则进行数据过滤
            if(user.getUserId() != Constant.SUPER_ADMIN){
                Map map = (Map)params;
                map.put(Constant.SQL_FILTER, getSQLFilter(user, point));
            }
            return ;
        }
        throw new RRException("数据权限接口，只能是Map类型参数，且不能为NULL");
    }

    /**
     * 获取数据过滤的SQL
     */
    private String getSQLFilter(SysUserEntity user, JoinPoint point){
        MethodSignature signature = (MethodSignature) point.getSignature();
        StringBuilder sqlFilter = new StringBuilder();
        DataFilter dataFilter = signature.getMethod().getAnnotation(DataFilter.class);
        //业务类型
        String businessType=dataFilter.businessType();
        logger.debug("业务类型："+businessType);
        //角色信息
        SysRoleEntity roleInfo=user.getRoleIdList().get(0);
        //角色code
        String roleCode=roleInfo.getRoleCode();
        //角色id
        String roleId=roleInfo.getRoleId();
        logger.debug("用户角色Id："+roleId+"；角色code:"+roleCode);
        //用户区域code
        String regionCode=user.getRegionCode();
        logger.debug("用户区域code："+regionCode);
        //判断业务
        switch (businessType){
            default:
                break;
        }
        logger.debug("用户数据过滤SQL:"+sqlFilter.toString());
        return sqlFilter.toString();
    }
}
