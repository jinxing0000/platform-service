package com.bettem.common.exception;


import com.bettem.common.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 山西百得科技开发股份有限公司 版权所有 © Copyright 2018<br>
 *
 * @Description:
 * @Project: atsp-security
 * @CreateDate: Created in 2019/3/21 09:40 <br>
 * @Author: 颜金星
 */
@RestController
public class RestErrorEndpoint implements ErrorController {
    private static final String PATH = "/error";


    @Autowired
    private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH,  produces = {MediaType.APPLICATION_JSON_VALUE})
    public R error(HttpServletRequest request, HttpServletResponse response) {
        response.setStatus(200);
        return buildBody(request,true);
    }

    private R buildBody(HttpServletRequest request,Boolean includeStackTrace){
        Map<String,Object> errorAttributes = getErrorAttributes(request, includeStackTrace);
        String messageFound=(String)errorAttributes.get("message");
        String[] messages=messageFound.split("-");
        if(messages.length==2){
            throw new RRException(Integer.parseInt(messages[1]),messages[0]);
        }
        throw new RRException(500,"系统异常，请联系管理员！！");
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    protected Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        WebRequest webRequest = new ServletWebRequest(request);
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }
}
