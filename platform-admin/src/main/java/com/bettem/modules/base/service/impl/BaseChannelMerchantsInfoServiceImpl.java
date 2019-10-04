package com.bettem.modules.base.service.impl;

import com.bettem.common.utils.Constant;
import com.bettem.common.utils.ShiroTokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.Query;

import com.bettem.modules.base.dao.BaseChannelMerchantsInfoDao;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("baseChannelMerchantsInfoService")
public class BaseChannelMerchantsInfoServiceImpl extends ServiceImpl<BaseChannelMerchantsInfoDao, BaseChannelMerchantsInfoEntity> implements BaseChannelMerchantsInfoService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseChannelMerchantsInfoEntity> page = this.selectPage(
                new Query<BaseChannelMerchantsInfoEntity>(params).getPage(),
                new EntityWrapper<BaseChannelMerchantsInfoEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<BaseChannelMerchantsInfoEntity> BaseChannelMerchantsInfoList=new ArrayList<>();
        BaseChannelMerchantsInfoEntity baseChannelMerchantsInfoEntity=null;
        for(int i=0;i<ids.length;i++){
            baseChannelMerchantsInfoEntity=new BaseChannelMerchantsInfoEntity();
            baseChannelMerchantsInfoEntity.setId(ids[i]);
            baseChannelMerchantsInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseChannelMerchantsInfoEntity.setModifyDate(new Date());
            baseChannelMerchantsInfoEntity.setDeleteState(Constant.DELETE_STATE_YES);
            BaseChannelMerchantsInfoList.add(baseChannelMerchantsInfoEntity);
        }
        this.updateBatchById(BaseChannelMerchantsInfoList);
    }

}
