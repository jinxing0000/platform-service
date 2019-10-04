package com.bettem.modules.base.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.R;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/base/channelMerchantsInfo/")
public class AppBaseChannelMerchantsInfoController {

    @Autowired
    private BaseChannelMerchantsInfoService baseChannelMerchantsInfoService;

    /**
     * 按照id查询渠道商信息接口
     * @param id
     * @return
     */
    @SysLog("按照id查询渠道商信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public R info(@RequestParam("id") String id){
        BaseChannelMerchantsInfoEntity baseChannelMerchantsInfo = baseChannelMerchantsInfoService.selectById(id);
        return R.ok(baseChannelMerchantsInfo);
    }
}
