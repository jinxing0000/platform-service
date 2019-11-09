package com.bettem.modules.tourism.controller;

import java.util.Map;
import java.util.Date;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.UpdateGroup;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderDetailsVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.bettem.common.annotation.SysLog;

import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.service.TourismProductOrderService;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;

/**
 * 旅游产品订单信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
@RestController
@RequestMapping("/api/tourism/productOrder/")
public class TourismProductOrderController {
    @Autowired
    private TourismProductOrderService tourismProductOrderService;
    @Autowired
    private ShiroTokenUtils shiroTokenUtils;
    /**
      * @Param [params]
      * @Return: R
      * @Decription: 分页查询数据
      * @CreateDate: 2019-10-13 21:15:20
      * @Author: 颜金星
      */
    @SysLog("分页查询旅游产品订单信息表数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productOrder:getPageList")
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = tourismProductOrderService.queryPage(params);
        return R.ok(page);
    }
    /**
     * @Param [id]
     * @Return: com.bettem.common.utils.R
     * @Decription: 按照id查询信息详情
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("按照id查询旅游产品订单信息表详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    @RequiresPermissions("tourism:productOrder:info")
    public R info(@RequestParam("id") String id){
        TourismProductOrderDetailsVO productOrderDetailsVO = tourismProductOrderService.getTourismProductOrderDetails(id);
        return R.ok(productOrderDetailsVO);
    }
    /**
     * @Param [tourismProductOrder]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品订单信息表数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrder:save")
    public R save(@RequestBody TourismProductOrderEntity tourismProductOrder){
        ValidatorUtils.validateEntity(tourismProductOrder, AddGroup.class);
        tourismProductOrder.setCreateDate(new Date());
        tourismProductOrder.setCreateUserId(shiroTokenUtils.getUserId());
        tourismProductOrder.setDeleteState(Constant.DELETE_STATE_NO);
        tourismProductOrderService.insert(tourismProductOrder);
        return R.ok();
    }
    /**
     * @Param [tourismProductOrder]
     * @Return: com.bettem.common.utils.R
     * @Decription: 修改数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品订单信息表数据")
    @RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrder:update")
    public R update(@RequestBody TourismProductOrderEntity tourismProductOrder){
        ValidatorUtils.validateEntity(tourismProductOrder, AddGroup.class);
        tourismProductOrder.setModifyDate(new Date());
        tourismProductOrder.setModifyUserId(shiroTokenUtils.getUserId());
        //全部更新
        tourismProductOrderService.updateById(tourismProductOrder);
        return R.ok();
    }
    /**
     * @Param [ids]
     * @Return: com.bettem.common.utils.R
     * @Decription: 批量删除数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("删除旅游产品订单信息表数据")
    @RequestMapping(value = "delete",method = RequestMethod.DELETE)
    @RequiresPermissions("tourism:productOrder:delete")
    public R delete(@RequestBody String[] ids){
        tourismProductOrderService.deleteByIds(ids);
        return R.ok();
    }

    /**
     *  订单处理
     * @param tourismProductOrder
     * @return
     */
    @SysLog("订单处理")
    @RequestMapping(value = "handle",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrder:handle")
    public R handle(@RequestBody TourismProductOrderEntity tourismProductOrder){
        ValidatorUtils.validateEntity(tourismProductOrder, UpdateGroup.class);
        tourismProductOrder.setModifyDate(new Date());
        tourismProductOrder.setModifyUserId(shiroTokenUtils.getUserId());
        tourismProductOrder.setState("02");
        //全部更新
        tourismProductOrderService.updateById(tourismProductOrder);
        return R.ok();
    }

    /**
     *  订单完成
     * @param tourismProductOrder
     * @return
     */
    @SysLog("订单完成")
    @RequestMapping(value = "complete",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrder:complete")
    public R complete(@RequestBody TourismProductOrderEntity tourismProductOrder){
        ValidatorUtils.validateEntity(tourismProductOrder, UpdateGroup.class);
        tourismProductOrder.setModifyDate(new Date());
        tourismProductOrder.setModifyUserId(shiroTokenUtils.getUserId());
        tourismProductOrder.setState("03");
        //全部更新
        tourismProductOrderService.updateById(tourismProductOrder);
        return R.ok();
    }

    /**
     *  订单取消
     * @param tourismProductOrder
     * @return
     */
    @SysLog("订单取消")
    @RequestMapping(value = "cancel",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @RequiresPermissions("tourism:productOrder:cancel")
    public R cancel(@RequestBody TourismProductOrderEntity tourismProductOrder){
        ValidatorUtils.validateEntity(tourismProductOrder, UpdateGroup.class);
        tourismProductOrder.setModifyDate(new Date());
        tourismProductOrder.setModifyUserId(shiroTokenUtils.getUserId());
        tourismProductOrder.setState("04");
        //全部更新
        tourismProductOrderService.updateById(tourismProductOrder);
        return R.ok();
    }
}
