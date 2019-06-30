package com.bettem.common.filter;

import com.alibaba.fastjson.JSONObject;
import com.bettem.common.exception.RRException;
import com.bettem.common.utils.ErrorCodeConstant;
import com.bettem.common.utils.R;
import com.bettem.modules.sys.entity.JwtTokenEntity;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/20 11:40 <br>
 * @Author: 颜金星
 */
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        try {
            return executeLogin(request, response);
        }
        catch (RRException e) {
            throw new RRException(e.getCode(),e.getMsg());
        }
        catch (Exception e) {
            RRException rre=(RRException)e.getCause();
            throw new RRException(rre.getCode(),rre.getMsg());
        }
    }

    /**
     *  执行用户token信息登陆
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response){
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        String tokenParam=httpServletRequest.getParameter("Authorization");
        if(token==null&&tokenParam==null){
            throw new RRException(ErrorCodeConstant.IS_EMPTY,"Authorization Token 为空！！"+"-"+ErrorCodeConstant.IS_EMPTY);
        }
        if(token==null){
            token=tokenParam;
        }
        JwtTokenEntity jwtToken = new JwtTokenEntity(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }
    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}
