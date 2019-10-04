package com.bettem.modules.base.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;

import java.util.Map;

/**
 * 渠道商信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-10-04 18:43:12
 */
public interface BaseChannelMerchantsInfoService extends IService<BaseChannelMerchantsInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-10-04 18:43:12
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);
}

