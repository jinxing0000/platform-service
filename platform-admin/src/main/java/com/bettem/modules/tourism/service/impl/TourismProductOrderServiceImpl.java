package com.bettem.modules.tourism.service.impl;

import com.bettem.common.exception.RRException;
import com.bettem.common.utils.*;
import com.bettem.modules.base.entity.BaseChannelMerchantsInfoEntity;
import com.bettem.modules.base.service.BaseChannelMerchantsInfoService;
import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;
import com.bettem.modules.tourism.entity.VO.TourismProductOrderDetailsVO;
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

    @Autowired
    private TourismProductInfoService tourismProductInfoService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String state= (String) params.get("state");
        //订单状态状态，全部订单
        if("0".equals(state)){
            state=null;
        }
        //渠道商
        String channelMerchantsId= (String) params.get("channelMerchantsId");
        Page<TourismProductOrderEntity> page = this.selectPage(
                new Query<TourismProductOrderEntity>(params).getPage(),
                new EntityWrapper<TourismProductOrderEntity>()
                .eq("delete_state",Constant.DELETE_STATE_NO)
                .eq(state!=null,"state",state)
                .eq(channelMerchantsId!=null,"channel_merchants_id",channelMerchantsId)
                .orderBy("create_date desc")
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

    @Override
    public TourismProductOrderDetailsVO getTourismProductOrderDetails(String id) {
        TourismProductOrderDetailsVO productOrderDetailsVO=new TourismProductOrderDetailsVO();
        TourismProductOrderEntity productOrderInfo=this.selectById(id);
        if(productOrderInfo==null){
            throw new RRException(ErrorCodeConstant.ERROR,"订单id有误！！");
        }
        productOrderDetailsVO.setProductOrderInfo(productOrderInfo);
        List<TourismProductOrderPeopleEntity>  productOrderPeopleList=tourismProductOrderPeopleService.selectList(
                new EntityWrapper<TourismProductOrderPeopleEntity>()
                .eq("delete_state",Constant.DELETE_STATE_NO)
                .eq("order_id",id)
                .orderBy("create_date desc")
        );
        productOrderDetailsVO.setProductOrderPeopleList(productOrderPeopleList);
        TourismProductInfoEntity productInfo=tourismProductInfoService.selectById(productOrderInfo.getProductId());
        productOrderDetailsVO.setProductInfo(productInfo);
        return productOrderDetailsVO;
    }

}
