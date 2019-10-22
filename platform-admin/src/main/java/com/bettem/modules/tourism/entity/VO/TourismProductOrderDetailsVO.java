package com.bettem.modules.tourism.entity.VO;

import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情
 */
public class TourismProductOrderDetailsVO {
    /**
     * 产品详情
     */
    private TourismProductInfoEntity productInfo;
    /**
     * 订单详情
     */
    private TourismProductOrderEntity productOrderInfo;
    /**
     * 订单出行人列表
     */
    private List<TourismProductOrderPeopleEntity>  productOrderPeopleList=new ArrayList<>();

    public TourismProductInfoEntity getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(TourismProductInfoEntity productInfo) {
        this.productInfo = productInfo;
    }

    public TourismProductOrderEntity getProductOrderInfo() {
        return productOrderInfo;
    }

    public void setProductOrderInfo(TourismProductOrderEntity productOrderInfo) {
        this.productOrderInfo = productOrderInfo;
    }

    public List<TourismProductOrderPeopleEntity> getProductOrderPeopleList() {
        return productOrderPeopleList;
    }

    public void setProductOrderPeopleList(List<TourismProductOrderPeopleEntity> productOrderPeopleList) {
        this.productOrderPeopleList = productOrderPeopleList;
    }
}
