package com.bettem.modules.tourism.service.impl;

import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderVO;
import com.bettem.modules.tourism.service.TourismProductInfoService;
import com.bettem.modules.tourism.service.TourismProductOrderPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.bettem.modules.tourism.dao.TourismProductOrderDao;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.service.TourismProductOrderService;
import org.springframework.transaction.annotation.Transactional;


@Service("tourismProductOrderService")
public class TourismProductOrderServiceImpl extends ServiceImpl<TourismProductOrderDao, TourismProductOrderEntity> implements TourismProductOrderService {

    @Autowired
    private ShiroTokenUtils shiroTokenUtils;

    @Autowired
    private BaseChannelMerchantsInfoService baseChannelMerchantsInfoService;

    @Autowired
    private TourismProductOrderPeopleService tourismProductOrderPeopleService;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveTourismProductOrderVO(TourismProductOrderVO tourismProductOrderVO) {
        Date toDay=new Date();
        String channelMerchantsId=tourismProductOrderVO.getChannelMerchantsId();
        BaseChannelMerchantsInfoEntity channelMerchantsInfo=baseChannelMerchantsInfoService.selectById(channelMerchantsId);
        if(channelMerchantsInfo==null){
            throw new RRException(ErrorCodeConstant.ERROR,"请先注册成为渠道商！！");
        }
        //订单id
        String orderId= PrimaryKeyUtils.createId();
        tourismProductOrderVO.setId(orderId);
        tourismProductOrderVO.setChannelMerchantsName(channelMerchantsInfo.getChannelName());
        tourismProductOrderVO.setState(Constant.ORDER_STATE_PENDING_DISPOSAL);
        tourismProductOrderVO.setCreateDate(toDay);
        tourismProductOrderVO.setCreateUserId(tourismProductOrderVO.getChannelMerchantsId());
        tourismProductOrderVO.setDeleteState(Constant.DELETE_STATE_NO);
        List<TourismProductOrderPeopleEntity>  productOrderPeopleList=tourismProductOrderVO.getProductOrderPeopleList();
        for(TourismProductOrderPeopleEntity productOrderPeople:productOrderPeopleList){
            productOrderPeople.setId(PrimaryKeyUtils.createId());
            productOrderPeople.setOrderId(orderId);
            productOrderPeople.setCreateUserId(tourismProductOrderVO.getChannelMerchantsId());
            productOrderPeople.setCreateDate(toDay);
            productOrderPeople.setDeleteState(Constant.DELETE_STATE_NO);
        }
        this.insert(tourismProductOrderVO);
        if(productOrderPeopleList.size()>0){
            tourismProductOrderPeopleService.insertBatch(productOrderPeopleList);
        }
    }

}
