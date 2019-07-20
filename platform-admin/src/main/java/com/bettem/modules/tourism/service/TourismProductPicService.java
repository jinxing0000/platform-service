package com.bettem.modules.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.tourism.entity.TourismProductPicEntity;

import java.util.Map;

/**
 * 旅游产品图片信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
public interface TourismProductPicService extends IService<TourismProductPicEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);
}

