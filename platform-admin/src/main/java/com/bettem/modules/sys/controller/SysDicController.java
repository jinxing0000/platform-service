package com.bettem.modules.sys.controller;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.group.UpdateGroup;
import com.bettem.modules.sys.entity.SysDicEntity;
import com.bettem.modules.sys.service.SysDicService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 数据字典表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-30 10:48:34
 */
@RestController
@RequestMapping("/api/sys/dic/")
public class SysDicController {
    @Autowired
    private SysDicService sysDicService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-07-30 10:48:34
      * @Author: 颜金星
      */
    @SysLog("分页查询数据字典表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("sys:dic:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = sysDicService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-07-30 10:48:34
     * @Author: 颜金星
     */
    @SysLog("按照id查询数据字典表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("sys:dic:info")
    public R info(@RequestParam("id") String id){
        SysDicEntity sysDic = sysDicService.selectById(id);
        return R.ok(sysDic);
    }
    /**
     * @Param [sysDic]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-07-30 10:48:34
     * @Author: 颜金星
     */
    @SysLog("新增数据字典表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("sys:dic:save")
    public R save(@RequestBody SysDicEntity sysDic){
        ValidatorUtils.validateEntity(sysDic, AddGroup.class);
        sysDicService.saveSysDic(sysDic);
        return R.ok();
    }
    /**
     * @Param [sysDic]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-07-30 10:48:34
     * @Author: 颜金星
     */
    @SysLog("新增数据字典表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("sys:dic:update")
    public R update(@RequestBody SysDicEntity sysDic){
        ValidatorUtils.validateEntity(sysDic, UpdateGroup.class);
        sysDicService.editSysDic(sysDic);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-07-30 10:48:34
     * @Author: 颜金星
     */
    @SysLog("删除数据字典表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("sys:dic:delete")
    public R delete(@RequestBody String[] ids){
        sysDicService.deleteByIds(ids);
        return R.ok();
    }

    /**
     * 按照条件查询数据字典列表
     * @param params
     * @return
     */
    @SysLog("按照条件查询数据字典列表")
    @RequestMapping(value = "findList",method = RequestMethod.GET)
    public R findList(@RequestParam Map<String, Object> params){
        Map<String,Object> map=sysDicService.findListByParams(params);
        return R.ok(map);
    }
}
