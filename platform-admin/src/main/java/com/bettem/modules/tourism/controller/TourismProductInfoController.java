package com.bettem.modules.tourism.controller;

import java.util.Map;
import java.util.Date;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.modules.tourism.entity.VO.TourismProductInfoVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bettem.common.annotation.SysLog;

import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.service.TourismProductInfoService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 旅游产品信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
@RestController
@RequestMapping("/api/tourism/productInfo/")
public class TourismProductInfoController {
    @Autowired
    private TourismProductInfoService tourismProductInfoService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-07-20 16:24:27
      * @Author: 颜金星
      */
    @SysLog("分页查询旅游产品信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productInfo:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = tourismProductInfoService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("按照id查询旅游产品信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productInfo:info")
    public R info(@RequestParam("id") String id){
        TourismProductInfoVO tourismProductInfoVO=tourismProductInfoService.findProductInfoVOById(id);
        return R.ok(tourismProductInfoVO);
    }
    /**
     * @Param [tourismProductInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productInfo:save")
    public R save(@RequestBody TourismProductInfoVO tourismProductInfoVO){
        ValidatorUtils.validateEntity(tourismProductInfoVO, AddGroup.class);
        tourismProductInfoService.saveTourismProductInfo(tourismProductInfoVO);
        return R.ok();
    }
    /**
     * @Param [tourismProductInfo]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productInfo:update")
    public R update(@RequestBody TourismProductInfoVO tourismProductInfoVO){
        ValidatorUtils.validateEntity(tourismProductInfoVO, AddGroup.class);
        tourismProductInfoService.editTourismProductInfo(tourismProductInfoVO);
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("删除旅游产品信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("tourism:productInfo:delete")
    public R delete(@RequestBody String[] ids){
        tourismProductInfoService.deleteByIds(ids);
        return R.ok();
    }
}
