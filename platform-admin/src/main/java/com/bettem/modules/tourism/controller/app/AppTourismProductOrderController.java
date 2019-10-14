package com.bettem.modules.tourism.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.Constant;
import com.bettem.common.utils.R;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.common.validator.group.AddGroup;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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
        return R.ok();
    }
}
