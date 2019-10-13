package com.bettem.modules.tourism.controller;

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

import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;
import com.bettem.modules.tourism.service.TourismProductOrderPeopleService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 旅游产品订单出行人员表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
@RestController
@RequestMapping("/api/tourism/productOrderPeople/")
public class TourismProductOrderPeopleController {
    @Autowired
    private TourismProductOrderPeopleService tourismProductOrderPeopleService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-10-13 21:15:20
      * @Author: 颜金星
      */
    @SysLog("分页查询旅游产品订单出行人员表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productOrderPeople:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = tourismProductOrderPeopleService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("按照id查询旅游产品订单出行人员表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productOrderPeople:info")
    public R info(@RequestParam("id") String id){
        TourismProductOrderPeopleEntity tourismProductOrderPeople = tourismProductOrderPeopleService.selectById(id);
        return R.ok(tourismProductOrderPeople);
    }
    /**
     * @Param [tourismProductOrderPeople]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品订单出行人员表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrderPeople:save")
    public R save(@RequestBody TourismProductOrderPeopleEntity tourismProductOrderPeople){
        ValidatorUtils.validateEntity(tourismProductOrderPeople, AddGroup.class);
        tourismProductOrderPeople.setCreateDate(new Date());
        tourismProductOrderPeople.setCreateUserId(shiroTokenUtils.getUserId());
        tourismProductOrderPeople.setDeleteState(Constant.DELETE_STATE_NO);
        tourismProductOrderPeopleService.insert(tourismProductOrderPeople);
        return R.ok();
    }
    /**
     * @Param [tourismProductOrderPeople]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品订单出行人员表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrderPeople:update")
    public R update(@RequestBody TourismProductOrderPeopleEntity tourismProductOrderPeople){
        ValidatorUtils.validateEntity(tourismProductOrderPeople, AddGroup.class);
        tourismProductOrderPeople.setModifyDate(new Date());
        tourismProductOrderPeople.setModifyUserId(shiroTokenUtils.getUserId());
        tourismProductOrderPeopleService.updateById(tourismProductOrderPeople);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("删除旅游产品订单出行人员表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("tourism:productOrderPeople:delete")
    public R delete(@RequestBody String[] ids){
        tourismProductOrderPeopleService.deleteByIds(ids);
        return R.ok();
    }
}
