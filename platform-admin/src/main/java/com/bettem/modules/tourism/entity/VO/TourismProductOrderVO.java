package com.bettem.modules.tourism.entity.VO;

import com.bettem.modules.tourism.entity.TourismProductOrderEntity;
import com.bettem.modules.tourism.entity.TourismProductOrderPeopleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 订单详情信息
 */
public class TourismProductOrderVO extends TourismProductOrderEntity {
    /**
     * 订单出行人
     */
    private List<TourismProductOrderPeopleEntity>  productOrderPeopleList=new ArrayList<>();

    public void setProductOrderPeopleList(List<TourismProductOrderPeopleEntity> productOrderPeopleList) {
        this.productOrderPeopleList = productOrderPeopleList;
    }

    public List<TourismProductOrderPeopleEntity> getProductOrderPeopleList() {
        return productOrderPeopleList;
    }
}
