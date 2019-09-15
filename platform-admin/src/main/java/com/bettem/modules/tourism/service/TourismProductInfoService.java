package com.bettem.modules.tourism.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductInfoVO;

import java.util.Map;

/**
 * 旅游产品信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-20 16:24:27
 */
public interface TourismProductInfoService extends IService<TourismProductInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * app端查询产品
     * @param params
     * @return
     */
    PageUtils queryPageApp(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-07-20 16:24:27
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);

    /**
     * 新增产品接口
     * @param tourismProductInfoVO
     */
    void saveTourismProductInfo(TourismProductInfoVO tourismProductInfoVO);

    /**
     * 按照id查询产品详情内容
     * @param id
     * @return
     */
    TourismProductInfoVO findProductInfoVOById(String id);

    /**
     * 修改产品信息
     * @param tourismProductInfoVO
     */
    void editTourismProductInfo(TourismProductInfoVO tourismProductInfoVO);

    /**
     * 旅游产品上架、下架修改状态
     * @param params
     */
    void editTourismProductInfoStateById(Map<String, Object> params);
}

