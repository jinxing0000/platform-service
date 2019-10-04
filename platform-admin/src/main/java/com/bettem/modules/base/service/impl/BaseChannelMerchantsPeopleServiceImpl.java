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

import com.bettem.modules.base.dao.BaseChannelMerchantsPeopleDao;
import com.bettem.modules.base.entity.BaseChannelMerchantsPeopleEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsPeopleService;
import org.springframework.transaction.annotation.Transactional;


@Service("baseChannelMerchantsPeopleService")
public class BaseChannelMerchantsPeopleServiceImpl extends ServiceImpl<BaseChannelMerchantsPeopleDao, BaseChannelMerchantsPeopleEntity> implements BaseChannelMerchantsPeopleService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<BaseChannelMerchantsPeopleEntity> page = this.selectPage(
                new Query<BaseChannelMerchantsPeopleEntity>(params).getPage(),
                new EntityWrapper<BaseChannelMerchantsPeopleEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<BaseChannelMerchantsPeopleEntity> BaseChannelMerchantsPeopleList=new ArrayList<>();
        BaseChannelMerchantsPeopleEntity baseChannelMerchantsPeopleEntity=null;
        for(int i=0;i<ids.length;i++){
            baseChannelMerchantsPeopleEntity=new BaseChannelMerchantsPeopleEntity();
            baseChannelMerchantsPeopleEntity.setId(ids[i]);
            baseChannelMerchantsPeopleEntity.setModifyUserId(shiroTokenUtils.getUserId());
            baseChannelMerchantsPeopleEntity.setModifyDate(new Date());
            baseChannelMerchantsPeopleEntity.setDeleteState(Constant.DELETE_STATE_YES);
            BaseChannelMerchantsPeopleList.add(baseChannelMerchantsPeopleEntity);
        }
        this.updateBatchById(BaseChannelMerchantsPeopleList);
    }

}
