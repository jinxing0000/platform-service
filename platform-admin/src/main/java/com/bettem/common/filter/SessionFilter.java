package com.bettem.common.filter;


import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: bettem-security
 * @CreateDate: Created in 2019/1/23 14:34 <br>
 * @Author: 颜金星
 */
public class SessionFilter extends FormAuthenticationFilter {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(SessionFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //判断用户会话是否有效
        if (this.isLoginRequest(servletRequest, servletResponse)) {
            if (this.isLoginSubmission(servletRequest, servletResponse)) {
                return this.executeLogin(servletRequest, servletResponse);
            } else {
                return true;
            }
        } else {
            logger.debug("请求地址："+request.getRequestURL());
            //判断是否为ajax请求
            if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
                logger.debug("该请求为Ajax请求！！");
                response.setHeader("sessionstatus", "sessionInvalid");
            }
            else {
                this.saveRequestAndRedirectToLogin(servletRequest, servletResponse);
            }
            return false;
        }
    }
}
