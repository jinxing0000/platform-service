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

import com.bettem.modules.tourism.dao.TourismProductOrderPeopleDao;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;
import com.bettem.modules.tourism.service.TourismProductOrderPeopleService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductOrderPeopleService")
public class TourismProductOrderPeopleServiceImpl extends ServiceImpl<TourismProductOrderPeopleDao, TourismProductOrderPeopleEntity> implements TourismProductOrderPeopleService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TourismProductOrderPeopleEntity> page = this.selectPage(
                new Query<TourismProductOrderPeopleEntity>(params).getPage(),
                new EntityWrapper<TourismProductOrderPeopleEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<TourismProductOrderPeopleEntity> TourismProductOrderPeopleList=new ArrayList<>();
        TourismProductOrderPeopleEntity tourismProductOrderPeopleEntity=null;
        for(int i=0;i<ids.length;i++){
            tourismProductOrderPeopleEntity=new TourismProductOrderPeopleEntity();
            tourismProductOrderPeopleEntity.setId(ids[i]);
            tourismProductOrderPeopleEntity.setModifyUserId(shiroTokenUtils.getUserId());
            tourismProductOrderPeopleEntity.setModifyDate(new Date());
            tourismProductOrderPeopleEntity.setDeleteState(Constant.DELETE_STATE_YES);
            TourismProductOrderPeopleList.add(tourismProductOrderPeopleEntity);
        }
        this.updateBatchById(TourismProductOrderPeopleList);
    }

}
