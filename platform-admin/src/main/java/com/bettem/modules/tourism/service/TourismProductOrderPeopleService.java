package com.bettem.modules.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;

import java.util.Map;

/**
 * 旅游产品订单出行人员表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-13 21:15:20
 */
public interface TourismProductOrderPeopleService extends IService<TourismProductOrderPeopleEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-10-13 21:15:20
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);
}

