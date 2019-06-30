package com.bettem.modules.sys.dao;

import com.bettem.modules.sys.entity.SysEnclosureEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * 系统附件表
 * 
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2018-10-17 16:10:48
 */
public interface SysEnclosureDao extends BaseMapper<SysEnclosureEntity> {
    /**
     * @Author 张瑞
     * @Description //TODO  保存
     * @Date 17:23 2019/4/30
     * @Param [params]
     * @return void
     **/
	void saveUploadData(Map<String, Object> params);

    List<Map<String,Object>> selectUploadData(Map<String,Object> params);

    void deleteByParams(Map<String,Object> params);

    Map<String,Object> selectByParams(Map<String,Object> params);
}
