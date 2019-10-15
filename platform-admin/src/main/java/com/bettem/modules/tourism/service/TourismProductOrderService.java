package com.bettem.modules.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderVO;

import java.util.Map;

/**
 * 旅游产品订单信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
public interface TourismProductOrderService extends IService<TourismProductOrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);

    /**
     * 下订单操作，生成订单，关联出行人
     * @param tourismProductOrderVO
     */
    void saveTourismProductOrderVO(TourismProductOrderVO tourismProductOrderVO);
}

