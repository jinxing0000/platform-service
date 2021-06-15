package com.bettem.modules.base.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bettem.common.annotation.SysLog;

import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 渠道商信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-04 18:43:12
 */
@RestController
@RequestMapping("/api/base/channelMerchantsInfo/")
public class BaseChannelMerchantsInfoController {
    @Autowired
    private BaseChannelMerchantsInfoService baseChannelMerchantsInfoService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-10-04 18:43:12
      * @Author: 颜金星
      */
    @SysLog("分页查询渠道商信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("base:channelMerchantsInfo:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = baseChannelMerchantsInfoService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("按照id查询渠道商信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("base:channelMerchantsInfo:info")
    public R info(@RequestParam("id") String id){
        BaseChannelMerchantsInfoEntity baseChannelMerchantsInfo = baseChannelMerchantsInfoService.selectById(id);
        return R.ok(baseChannelMerchantsInfo);
    }
    /**
     * @Param [baseChannelMerchantsInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("新增渠道商信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("base:channelMerchantsInfo:save")
    public R save(@RequestBody BaseChannelMerchantsInfoEntity baseChannelMerchantsInfo){
        ValidatorUtils.validateEntity(baseChannelMerchantsInfo, AddGroup.class);
        baseChannelMerchantsInfo.setCreateDate(new Date());
        baseChannelMerchantsInfo.setCreateUserId(shiroTokenUtils.getUserId());
        baseChannelMerchantsInfo.setDeleteState(Constant.DELETE_STATE_NO);
        baseChannelMerchantsInfoService.insert(baseChannelMerchantsInfo);
        return R.ok();
    }
    /**
     * @Param [baseChannelMerchantsInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("新增渠道商信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("base:channelMerchantsInfo:update")
    public R update(@RequestBody BaseChannelMerchantsInfoEntity baseChannelMerchantsInfo){
        ValidatorUtils.validateEntity(baseChannelMerchantsInfo, AddGroup.class);
        baseChannelMerchantsInfo.setModifyDate(new Date());
        baseChannelMerchantsInfo.setModifyUserId(shiroTokenUtils.getUserId());
        baseChannelMerchantsInfoService.updateById(baseChannelMerchantsInfo);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("删除渠道商信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("base:channelMerchantsInfo:delete")
    public R delete(@RequestBody String[] ids){
        baseChannelMerchantsInfoService.deleteByIds(ids);
        return R.ok();
    }

    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    @SysLog("按照id查询下级数据")
    @RequestMapping(value = "getChildrenList",method = RequestMethod.GET)
    @RequiresPermissions("base:channelMerchantsInfo:getChildrenList")
    public R getChildrenList(@RequestParam("id") String id){
        List<BaseChannelMerchantsInfoEntity> list= baseChannelMerchantsInfoService.getChildrenList(id);
        return R.ok(list);
    }
}
