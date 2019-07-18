package com.bettem.modules.base.controller;

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

import com.bettem.modules.base.entity.BaseSupplierInfoEntity;
import com.bettem.modules.base.service.BaseSupplierInfoService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 供应商信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-18 14:50:33
 */
@RestController
@RequestMapping("/api/base/supplierInfo/")
public class BaseSupplierInfoController {
    @Autowired
    private BaseSupplierInfoService baseSupplierInfoService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-07-18 14:50:33
      * @Author: 颜金星
      */
    @SysLog("分页查询供应商信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("base:supplierInfo:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = baseSupplierInfoService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-07-18 14:50:33
     * @Author: 颜金星
     */
    @SysLog("按照id查询供应商信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("base:supplierInfo:info")
    public R info(@RequestParam("id") String id){
        BaseSupplierInfoEntity baseSupplierInfo = baseSupplierInfoService.selectById(id);
        return R.ok(baseSupplierInfo);
    }
    /**
     * @Param [baseSupplierInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-07-18 14:50:33
     * @Author: 颜金星
     */
    @SysLog("新增供应商信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("base:supplierInfo:save")
    public R save(@RequestBody BaseSupplierInfoEntity baseSupplierInfo){
        ValidatorUtils.validateEntity(baseSupplierInfo, AddGroup.class);
        baseSupplierInfoService.saveBaseSupplierInfo(baseSupplierInfo);
        return R.ok();
    }
    /**
     * @Param [baseSupplierInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-07-18 14:50:33
     * @Author: 颜金星
     */
    @SysLog("新增供应商信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("base:supplierInfo:update")
    public R update(@RequestBody BaseSupplierInfoEntity baseSupplierInfo){
        ValidatorUtils.validateEntity(baseSupplierInfo, AddGroup.class);
        baseSupplierInfo.setModifyDate(new Date());
        baseSupplierInfo.setModifyUserId(shiroTokenUtils.getUserId());
        baseSupplierInfoService.updateById(baseSupplierInfo);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-07-18 14:50:33
     * @Author: 颜金星
     */
    @SysLog("删除供应商信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("base:supplierInfo:delete")
    public R delete(@RequestBody String[] ids){
        baseSupplierInfoService.deleteByIds(ids);
        return R.ok();
    }

    /**
     * 供应商禁用操作
     * @param ids
     * @return
     */
    @SysLog("供应商禁用操作")
    @RequestMapping(value = "disabling",method = RequestMethod.PUT)
    @RequiresPermissions("base:supplierInfo:disabling")
    public R disabling(@RequestBody String[] ids){
        baseSupplierInfoService.disabling(ids);
        return R.ok();
    }

    /**
     * 供应商启用操作
     * @param ids
     * @return
     */
    @SysLog("供应商启用操作")
    @RequestMapping(value = "enabling",method = RequestMethod.PUT)
    @RequiresPermissions("base:supplierInfo:enabling")
    public R enabling(@RequestBody String[] ids){
        baseSupplierInfoService.enabling(ids);
        return R.ok();
    }
}
