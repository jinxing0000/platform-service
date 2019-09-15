package com.bettem.modules.tourism.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bettem.modules.tourism.entity.VO.TourismProductInfoVO;

import java.util.List;
import java.util.Map;

/**
 * 旅游产品信息表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
public interface TourismProductInfoDao extends BaseMapper<TourismProductInfoEntity> {
    /**
     * 按照id查询产品信息
     * @param id
     * @return
     */
    TourismProductInfoVO selectProductInfoVOById(String id);

    /**
     * 按照条件查询产品信息
     * @param params
     * @param page
     * @return
     */
    List<Map<String,Object>> selectProductListByParams(Map<String, Object> params, Page<Map<String,Object>> page);
}
