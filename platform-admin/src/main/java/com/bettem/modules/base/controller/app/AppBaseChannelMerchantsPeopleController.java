package com.bettem.modules.base.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.modules.base.entity.BaseChannelMerchantsPeopleEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsPeopleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/app/base/channelMerchantsPeople/")
public class AppBaseChannelMerchantsPeopleController {

    @Autowired
    private BaseChannelMerchantsPeopleService baseChannelMerchantsPeopleService;

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    /**
     * @Param [params]
     * @Return: R
     * @Decription: 分页查询数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("分页查询渠道商出行人信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = baseChannelMerchantsPeopleService.queryPage(params);
        return R.ok(page);
    }


    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("按照id查询渠道商出行人信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public R info(@RequestParam("id") String id){
        BaseChannelMerchantsPeopleEntity baseChannelMerchantsPeople = baseChannelMerchantsPeopleService.selectById(id);
        return R.ok(baseChannelMerchantsPeople);
    }

    /**
     * @Param [baseChannelMerchantsPeople]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("新增渠道商出行人信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public R save(@RequestBody BaseChannelMerchantsPeopleEntity baseChannelMerchantsPeople){
        ValidatorUtils.validateEntity(baseChannelMerchantsPeople, AddGroup.class);
        baseChannelMerchantsPeople.setCreateDate(new Date());
        baseChannelMerchantsPeople.setCreateUserId(shiroTokenUtils.getUserId());
        baseChannelMerchantsPeople.setDeleteState(Constant.DELETE_STATE_NO);
        baseChannelMerchantsPeopleService.insert(baseChannelMerchantsPeople);
        return R.ok();
    }


    /**
     * @Param [baseChannelMerchantsPeople]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("修改渠道商出行人信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    public R update(@RequestBody BaseChannelMerchantsPeopleEntity baseChannelMerchantsPeople){
        ValidatorUtils.validateEntity(baseChannelMerchantsPeople, AddGroup.class);
        baseChannelMerchantsPeople.setModifyDate(new Date());
        baseChannelMerchantsPeople.setModifyUserId(shiroTokenUtils.getUserId());
        baseChannelMerchantsPeopleService.updateById(baseChannelMerchantsPeople);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("删除渠道商出行人信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    public R delete(@RequestBody String[] ids){
        baseChannelMerchantsPeopleService.deleteByIds(ids);
        return R.ok();
    }
}
