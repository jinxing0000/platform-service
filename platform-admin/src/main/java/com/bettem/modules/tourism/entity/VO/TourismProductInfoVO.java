package com.bettem.modules.tourism.entity.VO;

import com.bettem.modules.tourism.entity.TourismProductInfoEntity;
import com.bettem.modules.tourism.entity.TourismProductPicEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 产品信息VO
 */
public class TourismProductInfoVO extends TourismProductInfoEntity {
    /**
     *  产品图片数据
     */
    List<TourismProductPicEntity>  picList=new ArrayList<>();

    public void setPicList(List<TourismProductPicEntity> picList) {
        this.picList = picList;
    }

    public List<TourismProductPicEntity> getPicList() {
        return picList;
    }
}
