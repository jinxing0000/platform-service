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

import com.bettem.modules.tourism.dao.TourismProductOrderDao;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.service.TourismProductOrderService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductOrderService")
public class TourismProductOrderServiceImpl extends ServiceImpl<TourismProductOrderDao, TourismProductOrderEntity> implements TourismProductOrderService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TourismProductOrderEntity> page = this.selectPage(
                new Query<TourismProductOrderEntity>(params).getPage(),
                new EntityWrapper<TourismProductOrderEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<TourismProductOrderEntity> TourismProductOrderList=new ArrayList<>();
        TourismProductOrderEntity tourismProductOrderEntity=null;
        for(int i=0;i<ids.length;i++){
            tourismProductOrderEntity=new TourismProductOrderEntity();
            tourismProductOrderEntity.setId(ids[i]);
            tourismProductOrderEntity.setModifyUserId(shiroTokenUtils.getUserId());
            tourismProductOrderEntity.setModifyDate(new Date());
            tourismProductOrderEntity.setDeleteState(Constant.DELETE_STATE_YES);
            TourismProductOrderList.add(tourismProductOrderEntity);
        }
        this.updateBatchById(TourismProductOrderList);
    }

}
