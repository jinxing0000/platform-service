package com.bettem.modules.sys.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.R;
import com.bettem.common.utils.WeChatApiUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app/weChat/")
public class AppWeChatController {

    //微信小程序appId
    @Value("${WeChat.appId}")
    private String appId;

    //微信小程序appSecret
    @Value("${WeChat.appSecret}")
    private String appSecret;


    /**
     * 根据code获取openId
     * @param params
     * @return
     */
    @SysLog("根据code获取openId")
    @RequestMapping(value = "getWeChatOpenId",method = RequestMethod.GET)
    public R getWeChatOpenId(@RequestParam Map<String, Object> params){
        String code= (String) params.get("code");
        String openId= WeChatApiUtils.getWeChatOpenId(appId,appSecret,code);
        Map<String,String> result=new HashMap<>();
        result.put("openId",openId);
        return R.ok(result);
    }
}
