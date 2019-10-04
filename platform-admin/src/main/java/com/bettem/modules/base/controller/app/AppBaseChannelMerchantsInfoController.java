package com.bettem.modules.base.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.R;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

    @SysLog("新增渠道商信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public R save(@RequestBody BaseChannelMerchantsInfoEntity baseChannelMerchantsInfo){
        ValidatorUtils.validateEntity(baseChannelMerchantsInfo, AddGroup.class);
        baseChannelMerchantsInfoService.saveChannelMerchantsInfo(baseChannelMerchantsInfo);
        return R.ok();
    }
}
