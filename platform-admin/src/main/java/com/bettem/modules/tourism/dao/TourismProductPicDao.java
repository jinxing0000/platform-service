package com.bettem.modules.tourism.dao;

import com.bettem.modules.tourism.entity.TourismProductPicEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * 旅游产品图片信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
public interface TourismProductPicDao extends BaseMapper<TourismProductPicEntity> {
    /**
     * 按照产品id删除图片信息
     * @param params
     */
	void deleteProductPicByProductId(Map<String,Object> params);
}
