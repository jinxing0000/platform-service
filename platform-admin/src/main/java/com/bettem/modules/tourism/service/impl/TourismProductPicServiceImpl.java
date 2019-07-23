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

import com.bettem.modules.tourism.dao.TourismProductPicDao;
import com.bettem.modules.tourism.entity.TourismProductPicEntity;
import com.bettem.modules.tourism.service.TourismProductPicService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductPicService")
public class TourismProductPicServiceImpl extends ServiceImpl<TourismProductPicDao, TourismProductPicEntity> implements TourismProductPicService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<TourismProductPicEntity> page = this.selectPage(
                new Query<TourismProductPicEntity>(params).getPage(),
                new EntityWrapper<TourismProductPicEntity>().eq("delete_state",Constant.DELETE_STATE_NO)
        );

        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByIds(String[] ids) {
        List<TourismProductPicEntity> TourismProductPicList=new ArrayList<>();
        TourismProductPicEntity tourismProductPicEntity=null;
        for(int i=0;i<ids.length;i++){
            tourismProductPicEntity=new TourismProductPicEntity();
            tourismProductPicEntity.setId(ids[i]);
            tourismProductPicEntity.setModifyUserId(shiroTokenUtils.getUserId());
            tourismProductPicEntity.setModifyDate(new Date());
            tourismProductPicEntity.setDeleteState(Constant.DELETE_STATE_YES);
            TourismProductPicList.add(tourismProductPicEntity);
        }
        this.updateBatchById(TourismProductPicList);
    }

    @Override
    public void deleteProductPicByProductId(Map<String, Object> params) {
        this.baseMapper.deleteProductPicByProductId(params);
    }

}
