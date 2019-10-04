package com.bettem.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.bettem.common.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信小程序api接口开发能力
 */
public class WeChatApiUtils {

    private static Logger logger = LoggerFactory.getLogger(WeChatApiUtils.class);
    /**
     *  根据登陆的code获取openId的URL
     */
    private static String GET_USER_OPENID_URL="https://api.weixin.qq.com/sns/jscode2session";

    /**
     * 根据登陆code获取小程序openId
     * @param appId
     * @param appSecret
     * @param jsCode
     * @return
     */
    public static  String getWeChatOpenId(String  appId,String appSecret,String jsCode){
        Map<String, Object> params=new HashMap<>();
        params.put("appid",appId);
        params.put("secret",appSecret);
        params.put("js_code",jsCode);
        params.put("grant_type","authorization_code");
        JSONObject resultJson=HttpClientUtil.getDataByGetMethod(GET_USER_OPENID_URL,params);
        String openId=resultJson.getString("openid");
        if(openId==null){
            throw new RRException(ErrorCodeConstant.ERROR,"获取openId失败！！");
        }
        return openId;
    }

    public static void main(String[] args) {
        logger.debug(WeChatApiUtils.getWeChatOpenId("wxff3949a3c4fb6334","4d553abfebf8a502ecb0dba3151dcdab","011v94ql0rVM1s1WuJpl0PdQpl0v94qh"));
    }
}
