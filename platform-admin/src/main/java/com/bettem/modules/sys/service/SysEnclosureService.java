package com.bettem.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.bettem.common.utils.PageUtils;
import com.bettem.modules.sys.entity.SysEnclosureEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 系统附件表
 *
 * @author 颜金星
 * @email sunlightcs@gmail.com
 * @date 2018-10-17 16:50:12
 */
public interface SysEnclosureService extends IService<SysEnclosureEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * @Param [ids]
     * @Return:
     * @Decription: 按照ids批量逻辑删除
     * @CreateDate: 2018-10-17 16:50:12
     * @Author: 颜金星
     */
    void deleteByIds(String[] ids);
    /**
     * @Author 张瑞
     * @Description //TODO 上传文件组件
     * @Date 16:46 2019/4/30
     * @Param [params]
     * @return void
     **/
    List<Map<String,Object>> uploadFile(Map<String,Object> params) throws Exception;

    List<Map<String,Object>> selectUploadData(Map<String,Object> params);

    List<Map<String,Object>> selectFileData();

    List<Map<String,Object>> deleteByIdParams(Map<String,Object> params);

    Map<String,Object> selectByParams(Map<String,Object> params);

    void downloadFileByParams(HttpServletRequest request, HttpServletResponse response, Map<String,Object> params);

    void downloadFile(HttpServletRequest request, HttpServletResponse response, Map<String,Object> params);
}

