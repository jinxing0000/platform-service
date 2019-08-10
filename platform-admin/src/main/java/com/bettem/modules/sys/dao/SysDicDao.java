package com.bettem.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bettem.modules.sys.entity.SysDicEntity;

import java.util.List;

/**
 * 数据字典表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2019-07-30 10:48:34
 */
public interface SysDicDao extends BaseMapper<SysDicEntity> {
    /**
     * 按照ids删除下级字典数据
     * @param ids
     */
	void deleteSysDicByIds(String[] ids);

    /**
     * 按照ids查询数据
     * @param ids
     * @return
     */
	List<SysDicEntity> selectSysDicByIds(String[] ids);
}
