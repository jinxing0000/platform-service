package com.bettem.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.sys.entity.SysDicEntity;

import java.util.List;
import java.util.Map;

/**
 * 数据字典表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-30 10:48:34
 */
public interface SysDicService extends IService<SysDicEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-07-30 10:48:34
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);

    /**
     * 保存数据字典
     * @param sysDic
     */
    void saveSysDic(SysDicEntity sysDic);

    /**
     * 修改数据字典
     * @param sysDic
     */
    void editSysDic(SysDicEntity sysDic);

    /**
     * 按照条件查询数据字典
     * @param params
     * @return
     */
    Map<String,Object> findListByParams(Map<String, Object> params);

    /**
     * 将系统字典表
     */
    void findSysDicDataToRedis();
}

