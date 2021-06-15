package com.bettem.modules.tourism.controller.app;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.modules.tourism.entity.TourismProductPicEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductInfoVO;
import com.bettem.modules.tourism.service.TourismProductInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 *  手机端旅游产品信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
@RestController
@RequestMapping("/app/tourism/productInfo/")
public class AppTourismProductInfoController {

    @Autowired
    private TourismProductInfoService tourismProductInfoService;

    //图片地址
    @Value("${bettem.imagePath}")
    private String imagePath;

    /**
     * @Param [params]
     * @Return: R
     * @Decription: 分页查询数据
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    @RequestMapping(value = "getPageList",method = RequestMethod.GET)
    public R getPageList(@RequestParam Map<String, Object> params){
        PageUtils page = tourismProductInfoService.queryPageApp(params);
        return R.ok(page);
    }

    /**
     * 获取产品详情接口
     * @param id
     * @return
     */
    @RequestMapping(value = "info",method = RequestMethod.GET)
    public R info(@RequestParam("id") String id){
        TourismProductInfoVO productInfoVO=tourismProductInfoService.findProductInfoVOById(id);
        List<TourismProductPicEntity> picList=productInfoVO.getPicList();
        for(TourismProductPicEntity tourismProductPic:picList){
            tourismProductPic.setThumbUrl(imagePath+tourismProductPic.getThumbUrl());
        }
        return R.ok(productInfoVO);
    }
}
