package com.bettem.modules.base.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.base.entity.BaseSupplierInfoEntity;

import java.util.Map;

/**
 * 供应商信息表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-18 14:50:33
 */
public interface BaseSupplierInfoService extends IService<BaseSupplierInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2019-07-18 14:50:33
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);

    /**
     * 新增供应商信息并且添加用户信息和角色关联
     * @param supplierInfoEntity
     */
    void saveBaseSupplierInfo(BaseSupplierInfoEntity supplierInfoEntity);

    /**
     * 供应商禁用操作
     * @param ids
     */
    void disabling(String[] ids);

    /**
     * 供应商启用操作
     * @param ids
     */
    void enabling(String[] ids);
}

