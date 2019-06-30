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

package com.bettem.common.xss;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * XSS过滤处理
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-04-01 11:29
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {

    // 本地异常日志记录对象
    private static final Logger logger = LoggerFactory
            .getLogger(XssHttpServletRequestWrapper.class);


    //保存文档URL
    private final String  CMS_SAVE_URL="/cms/article/save";
    //修改文档URL
    private final String  CMS_UPDATE_URL="/cms/article/update";
    //保存公开信息URL
    private final String  PUBLICINFO_SAVE_URL="/publicinfo/article/save";
    //修改公开信息URL
    private final String  PUBLICINFO_UPDATE_URL="/publicinfo/article/update";
    //保存图片广告URL
    private final String  IMAGE_ADVERT_SAVE_URL="/site/imageAdvert/save";
    //保存图片广告URL
    private final String  IMAGE_ADVERT_UPDATE_URL="/site/imageAdvert/update";
    //留言板审核
    private final String  PARTAKE_MESSAGE_AUDIT="/partake/message/audit";
    //留言板发布
    private final String  PARTAKE_MESSAGE_PUBLISH="/partake/message/publish";
    //留言板留档
    private final String  PARTAKE_MESSAGE_ARCHIVES="/partake/message/archives";

    //没被包装过的HttpServletRequest（特殊场景，需要自己过滤）
    HttpServletRequest orgRequest;
    //html过滤
    private final static HTMLFilter htmlFilter = new HTMLFilter();

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
        orgRequest = request;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        if(!MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(super.getHeader(HttpHeaders.CONTENT_TYPE))){
            return super.getInputStream();
        }

        //为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), "utf-8");
        if (StringUtils.isBlank(json)) {
            return super.getInputStream();
        }
        String url=orgRequest.getRequestURI();
        //放行文档保存接口
//        if(PARTAKE_MESSAGE_ARCHIVES.equals(url)||PARTAKE_MESSAGE_PUBLISH.equals(url)||PARTAKE_MESSAGE_AUDIT.equals(url)||CMS_SAVE_URL.equals(url)||PUBLICINFO_SAVE_URL.equals(url)||CMS_UPDATE_URL.equals(url)||PUBLICINFO_UPDATE_URL.equals(url)||IMAGE_ADVERT_SAVE_URL.equals(url)||IMAGE_ADVERT_UPDATE_URL.equals(url)) {
        if(url.contains(PARTAKE_MESSAGE_ARCHIVES)||url.contains(PARTAKE_MESSAGE_PUBLISH)||url.contains(PARTAKE_MESSAGE_AUDIT)||url.contains(CMS_SAVE_URL)||url.contains(PUBLICINFO_SAVE_URL)||url.contains(CMS_UPDATE_URL)||url.contains(PUBLICINFO_UPDATE_URL)||url.contains(IMAGE_ADVERT_SAVE_URL)||url.contains(IMAGE_ADVERT_UPDATE_URL)) {
            logger.debug("不进行XSS过滤请求参数！！");
            logger.debug("通行URL:"+url);
        }else{
            //xss过滤
            logger.debug("进行xss过滤请求参数！！");
            json = xssEncode(json);
//            json = SQLFilter.sqlInject(json);
        }
        final ByteArrayInputStream bis = new ByteArrayInputStream(json.getBytes("utf-8"));
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return true;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
            }

            @Override
            public int read() throws IOException {
                return bis.read();
            }
        };
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] parameters = super.getParameterValues(name);
        if (parameters == null || parameters.length == 0) {
            return null;
        }

        for (int i = 0; i < parameters.length; i++) {
            parameters[i] = xssEncode(parameters[i]);
        }
        return parameters;
    }

    @Override
    public Map<String,String[]> getParameterMap() {
        Map<String,String[]> map = new LinkedHashMap<>();
        Map<String,String[]> parameters = super.getParameterMap();
        for (String key : parameters.keySet()) {
            String[] values = parameters.get(key);
            for (int i = 0; i < values.length; i++) {
                values[i] = xssEncode(values[i]);
            }
            map.put(key, values);
        }
        return map;
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(xssEncode(name));
        if (StringUtils.isNotBlank(value)) {
            value = xssEncode(value);
        }
        return value;
    }

    private String xssEncode(String input) {
        String str=htmlFilter.filter(input);
        return str;
    }

    /**
     * 获取最原始的request
     */
    public HttpServletRequest getOrgRequest() {
        return orgRequest;
    }

    /**
     * 获取最原始的request
     */
    public static HttpServletRequest getOrgRequest(HttpServletRequest request) {
        if (request instanceof XssHttpServletRequestWrapper) {
            return ((XssHttpServletRequestWrapper) request).getOrgRequest();
        }

        return request;
    }

}
