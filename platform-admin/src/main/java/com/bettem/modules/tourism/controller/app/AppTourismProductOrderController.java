package com.bettem.modules.tourism.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderDetailsVO;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderVO;
import com.bettem.modules.tourism.service.TourismProductOrderService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * 旅游产品订单信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
@RestController
@RequestMapping("/app/tourism/productOrder/")
public class AppTourismProductOrderController {


    @Autowired
    private TourismProductOrderService tourismProductOrderService;

    /**
     * @Param [tourismProductOrder]
     * @Return: com.bettem.common.utils.R
     * @Decription: 新增订单数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("新增旅游产品订单信息数据")
    @RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public R save(@RequestBody TourismProductOrderVO tourismProductOrderVO){
        ValidatorUtils.validateEntity(tourismProductOrderVO, AddGroup.class);
        tourismProductOrderService.saveTourismProductOrderVO(tourismProductOrderVO);
        return R.ok();
    }


    /**
     * @Param [params]
     * @Return: R
     * @Decription: 分页查询数据
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    @SysLog("分页查询旅游产品订单信息数据")
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
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
    @SysLog("按照id查询旅游产品订单信息详情")
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public R info(@RequestParam("id") String id){
        TourismProductOrderDetailsVO productOrderDetailsVO = tourismProductOrderService.getTourismProductOrderDetails(id);
        return R.ok(productOrderDetailsVO);
    }
}
