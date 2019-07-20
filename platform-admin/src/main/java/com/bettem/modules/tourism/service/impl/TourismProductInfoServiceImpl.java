package com.bettem.modules.tourism.service.impl;

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

import com.bettem.modules.tourism.dao.TourismProductInfoDao;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.service.TourismProductInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductInfoService")
public class TourismProductInfoServiceImpl extends ServiceImpl<TourismProductInfoDao, TourismProductInfoEntity> implements TourismProductInfoService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TourismProductInfoEntity> page = this.selectPage(
                new Query<TourismProductInfoEntity>(params).getPage(),
                new EntityWrapper<TourismProductInfoEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<TourismProductInfoEntity> TourismProductInfoList=new ArrayList<>();
        TourismProductInfoEntity tourismProductInfoEntity=null;
        for(int i=0;i<ids.length;i++){
            tourismProductInfoEntity=new TourismProductInfoEntity();
            tourismProductInfoEntity.setId(ids[i]);
            tourismProductInfoEntity.setModifyUserId(shiroTokenUtils.getUserId());
            tourismProductInfoEntity.setModifyDate(new Date());
            tourismProductInfoEntity.setDeleteState(Constant.DELETE_STATE_YES);
            TourismProductInfoList.add(tourismProductInfoEntity);
        }
        this.updateBatchById(TourismProductInfoList);
    }

}
