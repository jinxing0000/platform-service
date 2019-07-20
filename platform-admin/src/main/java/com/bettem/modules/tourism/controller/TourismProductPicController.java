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

import com.bettem.modules.tourism.entity.TourismProductPicEntity;
import com.bettem.modules.tourism.service.TourismProductPicService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 旅游产品图片信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
@RestController
@RequestMapping("/api/tourism/productPic/")
public class TourismProductPicController {
    @Autowired
    private TourismProductPicService tourismProductPicService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-07-20 16:24:27
      * @Author: 颜金星
      */
    @SysLog("分页查询旅游产品图片信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productPic:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = tourismProductPicService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("按照id查询旅游产品图片信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productPic:info")
    public R info(@RequestParam("id") String id){
        TourismProductPicEntity tourismProductPic = tourismProductPicService.selectById(id);
        return R.ok(tourismProductPic);
    }
    /**
     * @Param [tourismProductPic]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品图片信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productPic:save")
    public R save(@RequestBody TourismProductPicEntity tourismProductPic){
        ValidatorUtils.validateEntity(tourismProductPic, AddGroup.class);
        tourismProductPic.setCreateDate(new Date());
        tourismProductPic.setCreateUserId(shiroTokenUtils.getUserId());
        tourismProductPic.setDeleteState(Constant.DELETE_STATE_NO);
        tourismProductPicService.insert(tourismProductPic);
        return R.ok();
    }
    /**
     * @Param [tourismProductPic]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品图片信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productPic:update")
    public R update(@RequestBody TourismProductPicEntity tourismProductPic){
        ValidatorUtils.validateEntity(tourismProductPic, AddGroup.class);
        tourismProductPic.setModifyDate(new Date());
        tourismProductPic.setModifyUserId(shiroTokenUtils.getUserId());
        tourismProductPicService.updateById(tourismProductPic);//全部更新
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @SysLog("删除旅游产品图片信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("tourism:productPic:delete")
    public R delete(@RequestBody String[] ids){
        tourismProductPicService.deleteByIds(ids);
        return R.ok();
    }
}
